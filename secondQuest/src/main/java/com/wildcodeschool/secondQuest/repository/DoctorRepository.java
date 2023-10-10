package com.wildcodeschool.secondQuest.repository;

import com.wildcodeschool.secondQuest.model.Doctor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorRepository {


    private final List<Doctor> theDoctors = new ArrayList<>();

    public DoctorRepository(){
        this.theDoctors.add(new Doctor(11,"Matt Smith"));
        this.theDoctors.add(new Doctor(12,"Peter Capaldi"));
        this.theDoctors.add(new Doctor(13,"Jodie Whittaker"));
        this.theDoctors.add(new Doctor(14,"David Tennant"));
        this.theDoctors.add(new Doctor(15,"Ncuti Gatwa"));
    }

    public Doctor getDoctorByIncarnationNumber(int incarnationNumber) {
        Doctor theDoctor = null;
        for(Doctor doc : theDoctors) {
           if (doc.getNumber() == incarnationNumber) theDoctor = doc;
        }
        return theDoctor;
    }
}