package com.project.gil.api.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * class name    : FindSettleAccountRequest
 * description   : 정산계좌 보기 요청 dto
 * last modified : 2022.08.22 - 클래스 및 필드 데이터 추가(초안)
 *
 * */

@Data
@AllArgsConstructor
public class FindSettleAccountRequest {

    private Long userId; // 사용자id
}
