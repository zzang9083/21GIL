package com.project.gil.api.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;


/*
 * class name    : FindPaymentCardResponse
 * description   : 결제카드 보기 응답 dto
 * last modified : 2022.08.22 - 클래스 및 필드 데이터 추가(초안)
 *
 * */

@Data
@AllArgsConstructor
public class FindPaymentCardResponse {

    private String cardCode; // 카드사코드

    private String cardNumber; // 카드번호

}
