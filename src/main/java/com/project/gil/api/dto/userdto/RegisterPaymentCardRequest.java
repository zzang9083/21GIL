package com.project.gil.api.dto.userdto;

import lombok.Data;

@Data
public class RegisterPaymentCardRequest {

    private Long userId; // 사용자id

    private String cardCode; // 카드사코드

    private String cardNumber; // 카드번호
}
