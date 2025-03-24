package com.senai.lucaslopes.consultasmedicas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "${info.build.name}",
                version = "${info.build.version}",
                description = "${info.app.description}",
                contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Turma de DEVS Eng5", email = "lucaslopesbastos221@gmail.com")))

@SpringBootApplication
public class ConsultasMedicasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultasMedicasApplication.class, args);
    }

}
