package com.project.gil.api;


import com.project.gil.api.dto.userdto.FindJoinOttListRequest;
import com.project.gil.api.dto.userdto.FindJoinOttListResponse;
import com.project.gil.api.dto.userdto.FindJoinOttResponse;
import com.project.gil.constant.CommonConstant;
import com.project.gil.domain.Match;
import com.project.gil.domain.Party;
import com.project.gil.domain.User;
import com.project.gil.domain.Wait;
import com.project.gil.service.MatchService;
import com.project.gil.service.UserService;
import com.project.gil.service.WaitService;
import com.project.gil.util.KakaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KakaoUtil kakaoUtil;

    @Autowired
    private MatchService matchService;

    @Autowired
    private WaitService waitService;

    @GetMapping("/user/login")
    public void loginUser(@RequestParam String code) {
        String kaKaoAccessToken ="";
        Long id= 0L;
        String email="";

        kaKaoAccessToken = kakaoUtil.getKaKaoAccessToken(code);
        if(!kaKaoAccessToken.isEmpty()) {

            Map<String, Object> userInfo = kakaoUtil.getUserInfoByToken(kaKaoAccessToken);

            if (userInfo != null && !userInfo.isEmpty()) {
                id = (Long) userInfo.get("id");
                email = (String) userInfo.get("email");
            }
        }

        User user = userService.findOne(id);
        if(user == null) {
            user = new User();
            user.setId(id);
            user.setAccount(email);
            user.setStatus(CommonConstant.USER_ACTIVE);
            userService.save(user);
        }



        //return kaKaoAccessToken;

    }

    @PostMapping("/user/join/list")
    public FindJoinOttListResponse loginUser(@RequestBody FindJoinOttListRequest request) {
        List<FindJoinOttResponse> result = new ArrayList<FindJoinOttResponse>();

        Long userId = request.getUserId();

        List<Match> matchList = matchService.findByUserId(userId);
        for(Match m : matchList) {
            Party party = m.getParty();
            result.add(new FindJoinOttResponse(party.getOtt().getId()
                    ,CommonConstant.WAIT_COMPLETE));
        }

        List<Wait> unmatchList = waitService.findUnmatchListByUserId(userId);
        for(Wait w : unmatchList) {
            result.add(new FindJoinOttResponse(w.getOttId()
                    ,CommonConstant.WAIT_ONGOING));
        }

        return new FindJoinOttListResponse(result);

//        List<FindJoinOttResponse> collect = unmatchList.stream()
//                .map(m -> new FindJoinOttResponse(m.))

    }





//    @GetMapping("/card/register")
//    public String registerCard() {
//        return "/js/CardRegistrySample";
//    }
//
//    @GetMapping("/user/account/find")
//    public FindSettleAccountResonse findSettleAccount(@RequestBody FindSettleAccountRequest request)
//    {
//        Settle settleAccount = userService.findSettleAccount(request.getUserId());
//
//        return new FindSettleAccountResonse(settleAccount);
//    }
//
//    @PostMapping("/user/account/register")
//    public int registerAccount(@RequestBody RegisterAccountRequest request)
//    {
//        Settle settle = new Settle();
//        settle.setBankCode(request.getBankCode());
//        settle.setAccountNumber(request.getAccountNumber());
//
//        userService.registerAccount(settle, request.getUserId());
//
//        return 1;
//    }
//
//    @PostMapping("/user/account/delete")
//    public int deleteAccount(@RequestBody Map<String, Long> id)
//    {
//
//        userService.deleteAccount(id.get("id"));
//
//        return 1;
//    }
//
//    @PostMapping("/user/paymentCard/find")
//    public FindPaymentCardResponse findPaymentCard(@RequestBody FindPaymentCardRequest request)
//    {
//        Payment payment = userService.findPaymentCard(request.getUserId());
//
//        return new FindPaymentCardResponse(payment);
//    }
//
//    @PostMapping("/user/paymentCard/register")
//    public int registerPaymentCard(@RequestBody RegisterPaymentCardRequest request)
//    {
//        Payment payment = new Payment();
//        payment.setCardCode(request.getCardCode());
//        payment.setCardNumber(request.getCardNumber());
//
//        userService.registerPaymentCard(payment, request.getUserId());
//
//        return 1;
//    }
//
//    @PostMapping("/user/creditcard/delete")
//    public int deletePaymentCard(@RequestBody Map<String, Long> id)
//    {
//
//        userService.deletePaymentCard(id.get("id"));
//
//        return 1;
//    }

}
