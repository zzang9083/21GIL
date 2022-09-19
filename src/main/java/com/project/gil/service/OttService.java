package com.project.gil.service;

import com.project.gil.domain.Ott;
import com.project.gil.repository.OttRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OttService {

    @Autowired
    OttRepository ottRepository;


    public Ott findById(Long id) {
        return ottRepository.findOne(id);
    }


}
