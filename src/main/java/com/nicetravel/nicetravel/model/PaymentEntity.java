package com.nicetravel.nicetravel.model;

import com.nicetravel.nicetravel.util.Constants;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name = "TB_PAYMENT", schema = Constants.SCHEMA)
public class PaymentEntity extends BaseEntity {

    private static final String PK_GENERATOR_NAME = "PK_PAYMENT";

    @Id
    @Column(name = "CO_PAYMENT")
    @GeneratedValue(generator = PK_GENERATOR_NAME, strategy = GenerationType.AUTO)
    private Long cod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_USER", foreignKey = @ForeignKey(name = "FK_PAYMENT_TO_USER"), nullable = false)
    private UserEntity userReceive;

    @Column(name = "ST_PAGO", nullable = false)
    private Boolean pago;

    @Column(name = "DT_CRIACAO")
    private LocalTime dtCreate;

    @Column(name = "DT_PAGAMENTO")
    private LocalTime dtPagamento;

    @Override
    public Long getCod() {
        return cod;
    }

    public PaymentEntity setCod(Long cod) {
        this.cod = cod;
        return this;
    }

    public UserEntity getUserReceive() {
        return userReceive;
    }

    public PaymentEntity setUserReceive(UserEntity userReceive) {
        this.userReceive = userReceive;
        return this;
    }

    public Boolean getPago() {
        return pago;
    }

    public PaymentEntity setPago(Boolean pago) {
        this.pago = pago;
        return this;
    }

    public LocalTime getDtCreate() {
        return dtCreate;
    }

    public PaymentEntity setDtCreate(LocalTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public LocalTime getDtPagamento() {
        return dtPagamento;
    }

    public PaymentEntity setDtPagamento(LocalTime dtPagamento) {
        this.dtPagamento = dtPagamento;
        return this;
    }
}
