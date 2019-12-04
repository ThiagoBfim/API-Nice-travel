package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.model.UserEntity;
import com.nicetravel.nicetravel.service.user.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentSource;
import com.stripe.net.RequestOptions;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentSourceCollectionCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentResource {
    private static final String STRIPE_SECRET_KEY = "sk_test_DbSxTsw0lvSiQKMc7PWt49s600Z4KYdof2";
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentResource.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public boolean payment(@RequestParam("token") String token,
                           @RequestParam("userUID") String userUid) {
        try {
            Stripe.apiKey = STRIPE_SECRET_KEY;
            Customer customer;
            UserEntity userEntity = userService.saveOrUpdateUser(userUid, null, null);
            if (userEntity.getPaymentId() != null) {
                customer = Customer.retrieve(userEntity.getPaymentId());
            } else {
                customer = createStripeCustomer(userEntity);
            }
            PaymentSource paymentSource = createCustomerPaymentSource(token, customer);
            createCharge(customer, paymentSource);
            return true;
        } catch (StripeException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    private void createCharge(Customer customer, PaymentSource paymentSource) throws StripeException {
        ChargeCreateParams chargeParams = new ChargeCreateParams.Builder()
                .setCurrency("BRL")
                .setAmount(10_00L)
                .setCustomer(customer.getId())
                .setDescription("Pagamento viagem 10 estrelas")
                .build();
        Charge.create(chargeParams, RequestOptions.builder().setIdempotencyKey(paymentSource.getId()).build());
    }

    private PaymentSource createCustomerPaymentSource(@RequestParam("token") String token, Customer customer) throws StripeException {
        PaymentSourceCollectionCreateParams customerPaymentParas = new PaymentSourceCollectionCreateParams.Builder()
                .setSource(token)
                .build();
        return customer.getSources().create(customerPaymentParas);
    }

    private Customer createStripeCustomer(UserEntity userEntity) throws StripeException {
        Customer customer;
        CustomerCreateParams customerParams = new CustomerCreateParams.Builder()
                .setEmail("email@gmail.com")
                .build();
        customer = Customer.create(customerParams);
        userService.updateUserWithPaymentId(customer.getId(), userEntity);
        return customer;
    }
}
