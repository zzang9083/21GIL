package com.project.gil.api.dto.partydto;

import lombok.AllArgsConstructor;
import lombok.Data;


/*
 * class name    : JoinPartyRequest
 * description   : 결제카드 보기 요청 dto
 * last modified : 2022.08.22 - 클래스 및 필드 데이터 추가(초안)
 *
 * */


@Data
@AllArgsConstructor
public class JoinPartyRequest {

    private Long userId; // 사용자 id

    private Long ottId; // ott id

    private Long duration; // 지속기간(개월)


}
