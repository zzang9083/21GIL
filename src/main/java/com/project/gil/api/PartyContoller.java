package com.project.gil.api;


import com.project.gil.api.dto.partydto.CreatePartyRequest;
import com.project.gil.api.dto.partydto.JoinPartyRequest;
import com.project.gil.constant.CommonConstant;
import com.project.gil.domain.Ott;
import com.project.gil.domain.Party;
import com.project.gil.domain.Wait;
import com.project.gil.service.OttService;
import com.project.gil.service.PartyService;
import com.project.gil.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api")
public class PartyContoller {

    Logger logger = LoggerFactory.getLogger(PartyContoller.class);

    @Autowired
    private OttService ottService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PartyService partyService;


    /*
     *  method 명   : 파티생성
     *  method 설명 : 파티장이 특정 ott의 파티를 생성한다.
     *  input : JoinPartyRequest(파티가입 dto) - ottId, leaderId, shareId, sharePassword, duration, profile
     *  output: -
     */
    @PostMapping("/party/create")
    public void createParty(@RequestBody CreatePartyRequest request) {

        // 파티 생성
        Ott ott = ottService.findById(request.getOttId());
        Party party = new Party(ott, request);

        partyService.createParty(party);
    }



    /*
     *  method 명   : 파티참여 신청
     *  method 설명 : 파티원이 특정 ott의 파티를 신청을 한다.
     *  input : JoinPartyRequest(파티가입 dto) - userId, ottId, duration
     *  output: -
     */
    @PostMapping("/party/join")
    public boolean joinParty(@RequestBody JoinPartyRequest request) {

        Wait wait = new Wait(request);

        // 파티 참여신청
        try {
            partyService.joinParty(wait, CommonConstant.NORMAL_QUEUE);
        } catch (Exception e) {
            logger.error("{}", e.getMessage());

        }
        return true;
    }

    @GetMapping("/payment")
    public void testPayment() {

        String ret = paymentService.testPayment();
        System.out.println("ret:"+ret);
    }
}
