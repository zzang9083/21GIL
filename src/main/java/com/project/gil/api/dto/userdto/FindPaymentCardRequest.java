package com.project.gil.api.dto.userdto;

import lombok.Data;

/*
 * class name    : FindPaymentCardRequest
 * description   : 결제카드 보기 요청 dto
 * last modified : 2022.08.22 - 클래스 및 필드 데이터 추가(초안)
 *
 * */

@Data
public class FindPaymentCardRequest {

    private Long userId; // 사용자id
}
