package com.wildcodeschool.secondQuest.controller;

import com.wildcodeschool.secondQuest.model.Doctor;
import com.wildcodeschool.secondQuest.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(@Autowired DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping("/{incarnationNumber}")
    @ResponseBody
    public Doctor getDoctor(@PathVariable int incarnationNumber){
        return this.doctorService.getDoctor(incarnationNumber);
    }
}
