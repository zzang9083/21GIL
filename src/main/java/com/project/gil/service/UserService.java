package com.project.gil.service;

import com.project.gil.domain.Payment;
import com.project.gil.domain.Settle;
import com.project.gil.domain.User;
import com.project.gil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    // 사용자 찾기
    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }

    public void save(User user) {
        userRepository.save(user);
    }


//    // 정산계좌 조회
//    public Settle findSettleAccount(Long userId) {
//        User user = userRepository.findById(userId)
//                                    .orElseThrow(IllegalArgumentException::new);
//
//        return user.getSettle();
//    }
//
//    // 정산계좌 등록 및 수정
//    public void registerAccount(Settle settle,Long id) {
//        User user = userRepository.findById(id)
//                                    .orElseThrow(IllegalArgumentException::new);
//
//        // 유효계좌 검증 必
//
//        user.setSettle(settle);
//
//        userRepository.save(user);
//    }
//
//    // 정산계좌 삭제
//    public void deleteAccount(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(IllegalArgumentException::new);
//        user.setSettle(null);
//
//        userRepository.save(user);
//    }
//
//    public Payment findPaymentCard(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(IllegalArgumentException::new);
//
//        return user.getPayment();
//    }
//
//    // 결제카드 등록 및 수정
//    public void registerPaymentCard(Payment payment, Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(IllegalArgumentException::new);
//
//        // 유효카드 검증 必
//
//        user.setPayment(payment);
//
//        userRepository.save(user);
//    }
//
//    // 결제카드 삭제
//    public void deletePaymentCard(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(IllegalArgumentException::new);
//        user.setPayment(null);
//
//        userRepository.save(user);
//    }

}
