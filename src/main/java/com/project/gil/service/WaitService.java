package com.project.gil.service;

import com.project.gil.domain.Wait;
import com.project.gil.repository.WaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;




@Service
@Transactional
public class WaitService {

    @Autowired
    WaitRepository waitRepository;

    public void save(Wait wait) {
        waitRepository.save(wait);
    }


    public List<Wait> findUnmatchListByUserId(Long userId) {
        return waitRepository.findUnmatchListByUserId(userId);
    }


}
