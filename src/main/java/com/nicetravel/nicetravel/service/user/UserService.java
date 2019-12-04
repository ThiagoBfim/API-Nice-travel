package com.nicetravel.nicetravel.service.user;

import com.nicetravel.nicetravel.model.UserEntity;
import com.nicetravel.nicetravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserEntity saveOrUpdateUser(String userUID, String userEmail, String userName) {
        UserEntity userEntity = userRepository.findByUid(userUID).orElse(new UserEntity());
        userEntity.setUid(userUID);
        userEntity.setEmail(userEmail);
        userEntity.setName(userName);
        return userRepository.save(userEntity);
    }

    @Transactional
    public void updateUserWithPaymentId(String paymentId, UserEntity userEntity) {
        userEntity.setPaymentId(paymentId);
        userRepository.save(userEntity);
    }
}
