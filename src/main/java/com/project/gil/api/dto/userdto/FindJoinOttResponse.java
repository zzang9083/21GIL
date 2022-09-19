package com.project.gil.api.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FindJoinOttResponse {

    private Long ottId;         // ottId
    private String joinStatus;  // ott 가입상태 (0- 가입완료 / 1- 매칭중)


    FindJoinOttResponse(){}
    public FindJoinOttResponse(Long ottId, String joinStatus) {
        this.ottId = ottId;
        this.joinStatus = joinStatus;
    }
}
