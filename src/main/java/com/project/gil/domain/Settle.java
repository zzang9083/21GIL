package com.project.gil.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * class name    : Settle
 * description   : 서비스 사용자 정산정보 명세
 * last modified : 2022.08.20 - 필드 데이터 추가(초안)
 *
 * */

@Embeddable
@AllArgsConstructor
@Getter @Setter
public class Settle {

    public Settle() {
    }

    @Column(nullable = true, columnDefinition = "VARCHAR(2)")
    private String bankCode; // 은행코드

    @Column(nullable = true, columnDefinition = "VARCHAR(20)")
    private String accountNumber; // 계좌번호
}
