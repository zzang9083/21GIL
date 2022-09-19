package com.project.gil.api.dto.userdto;

import lombok.Data;

/*
 * class name    : RegisterAccountRequest
 * description   : 계좌등록 요청 dto
 * last modified : 2022.08.22 - 클래스 및 필드 데이터 추가(초안)
 *
 * */

@Data
public class RegisterAccountRequest {

    private Long userId; // 사용자id

    private String bankCode; // 은행코드

    private String accountNumber; // 계좌번호
}
