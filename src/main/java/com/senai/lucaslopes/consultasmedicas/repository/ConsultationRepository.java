package com.senai.lucaslopes.consultasmedicas.repository;

import com.senai.lucaslopes.consultasmedicas.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    boolean existsByPatientIdAndHorarioConsultaAndDataConsulta(Integer id, String horarioConsulta, String dataConsulta);



    @Query("select cn from Consultation cn where cn.patient.nome like concat('%',:nome, '%') ")
    List<Consultation> buscaConsultaPaciente(@Param("nome") String nome);


}
