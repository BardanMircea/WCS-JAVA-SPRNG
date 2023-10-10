package com.wildcodeschool.secondQuest.service;

import com.wildcodeschool.secondQuest.model.Doctor;
import com.wildcodeschool.secondQuest.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(@Autowired DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }
    public Doctor getDoctor(int incarnationNumber){
        if(incarnationNumber < 11 && incarnationNumber > 0){
            throw new ResponseStatusException(HttpStatus.SEE_OTHER);
        }
        if(incarnationNumber > 15){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible to retrieve the incarnation " + incarnationNumber);
        }
        return this.doctorRepository.getDoctor(incarnationNumber);
    }
}
