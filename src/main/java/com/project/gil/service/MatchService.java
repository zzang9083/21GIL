package com.project.gil.service;

import com.project.gil.domain.Match;
import com.project.gil.domain.Party;
import com.project.gil.domain.User;
import com.project.gil.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    UserService userService;


    public List<Match> findByUserId(Long userId) {
        User user = userService.findOne(userId);
        return matchRepository.findByUserId(user);
    }

    public List<Match> findByParty(Party party) {
        return matchRepository.findByParty(party);
    }

    public void save(Match match) {
        matchRepository.save(match);
    }


}
