package com.senai.lucaslopes.consultasmedicas.repository;

import com.senai.lucaslopes.consultasmedicas.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    boolean existsByPacienteIdAndHorarioConsultaAndDataConsulta(Integer ID, String CONSULTATION_TIME, String CONSULTATION_DATE);


    @Query("select cn from Consultation cn where cn.patient.nome like concat('%',:NAME, '%') ")
    List<Consultation> buscaConsultaPaciente(@Param("NAME") String nome);

    @Query("select cd from Consultation cd where cd.patient.id = :id and cd.horarioConsulta = :horarioConsulta and cd.dataConsulta = :dataConsulta")
    List<Consultation> findByPacienteAndHorario(@Param("ID") Integer id, @Param("CONSULTATION TIME") String horarioConsulta, @Param("CONSULTATION DATE") String dataConsulta);

}
