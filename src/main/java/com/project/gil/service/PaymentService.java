package com.project.gil.service;

import com.project.gil.util.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentUtil paymentUtil;

    public String testPayment() {
        return paymentUtil.requestPayment();
    }
}
