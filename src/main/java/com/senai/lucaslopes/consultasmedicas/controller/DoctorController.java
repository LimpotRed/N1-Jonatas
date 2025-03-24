package com.senai.lucaslopes.consultasmedicas.controller;

import com.senai.lucaslopes.consultasmedicas.entity.Doctor;
import com.senai.lucaslopes.consultasmedicas.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> listarTodosMedicos() {
        return doctorService.findAllMedico();
    }

    @GetMapping("/{id}")
    public Doctor obterMedicoPorId(@PathVariable int id) {
        return doctorService.findByIdMedico(id);
    }

    @GetMapping("/especialidade/{especialidade}")
    public List<Doctor> obterMedicosPorEspecialidade(@PathVariable String especialidade) {
        return doctorService.findByEspecialidade(especialidade);
    }

    @GetMapping("/nome/{nome}")
    public List<Doctor> obterMedicosPorNome(@PathVariable String nome) {
        return doctorService.findByNomeMedico(nome);
    }

    @GetMapping("/crm/{crm}")
    public List<Doctor> obterMedicosPorCrm(@PathVariable Integer crm) {
        return doctorService.findByCrm(crm);
    }

    @PostMapping
    public Doctor cadastrarMedico(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }

    @PutMapping
    public Doctor atualizarMedico(@RequestBody Doctor doctor) {
        return doctorService.update(doctor);
    }

    @DeleteMapping("/{id}")
    public void excluirMedico(@PathVariable int id) {
        doctorService.delete(id);
    }
}