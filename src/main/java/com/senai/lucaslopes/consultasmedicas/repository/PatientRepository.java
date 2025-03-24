package com.senai.lucaslopes.consultasmedicas.repository;

import com.senai.lucaslopes.consultasmedicas.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("select pc from Patient pc where pc.CPF like concat('%',:CPF, '%') ")
    List<Patient> findByPacienteCPF(@Param("CPF") String CPF);

    @Query("select pn from Patient pn where pn.nome like concat('%',:NAME, '%') ")
    List<Patient> findByNomePaciente(@Param("NAME") String nome);

}
