package com.senai.lucaslopes.consultasmedicas.service;

import com.senai.lucaslopes.consultasmedicas.entity.Patient;
import com.senai.lucaslopes.consultasmedicas.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
    public Patient findById(int id) {
        return patientRepository.findById(id).orElse(null);
    }
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    public void delete(Integer id) {
        patientRepository.deleteById(id);
    }
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }
    public List<Patient> findByPacienteCPF(String CPF) {
        return patientRepository.findByPacienteCPF(CPF);
    }
    public List<Patient> findByPacienteNome(String nome) {
        return patientRepository.findByNomePaciente(nome);
    }

}
