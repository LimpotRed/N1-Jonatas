package com.senai.lucaslopes.consultasmedicas.service;

import com.senai.lucaslopes.consultasmedicas.entity.Doctor;
import com.senai.lucaslopes.consultasmedicas.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    public Doctor findByIdMedico(Integer id) {
        return doctorRepository.findById(id).orElse(null);
    }
    public List<Doctor> findAllMedico(){
        return doctorRepository.findAll();
    }
    public void delete(int id) {
        doctorRepository.deleteById(id);
    }
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    public List<Doctor> findByEspecialidade(String especialidade) {
        return doctorRepository.findBySpecialty(especialidade);
    }
    public List<Doctor> findByNomeMedico(String nomeMedico) {
        return doctorRepository.findByDoctorName(nomeMedico);
    }
    public List<Doctor> findByCrm(Integer crm) {
        return doctorRepository.findByCrm(crm);
    }

}
