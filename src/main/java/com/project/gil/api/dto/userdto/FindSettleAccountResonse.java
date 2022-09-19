package com.project.gil.api.dto.userdto;

import com.project.gil.domain.Settle;
import lombok.AllArgsConstructor;
import lombok.Data;


/*
 * class name    : FindSettleAccountResonse
 * description   : 정산계좌 보기 응답 dto
 * last modified : 2022.08.22 - 클래스 및 필드 데이터 추가(초안)
 *
 * */
@Data
@AllArgsConstructor
public class FindSettleAccountResonse {

    private String bankCode; // 은행코드

    private String accountNumber; // 계좌번호


}
