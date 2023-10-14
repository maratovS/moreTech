package com.malishnya.moretech;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malishnya.moretech.db.model.ATM;
import com.malishnya.moretech.db.model.Department;
import com.malishnya.moretech.db.repo.ATMRepo;
import com.malishnya.moretech.db.repo.DepartmentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@SpringBootApplication
public class MoreTechApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoreTechApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(DepartmentRepo DedepartmentRepo,
                                 ATMRepo atmRepo) throws IOException {
        return (String[] args) -> {
            if (DedepartmentRepo.findAll().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                Department[] departments = mapper.readValue(new File("src/main/resources/offices.txt"), Department[].class);
                DedepartmentRepo.saveAll(Arrays.asList(departments));
            }
            if (atmRepo.findAll().isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                ATM[] departments = mapper.readValue(new File("src/main/resources/atms.txt"), ATM[].class);
                atmRepo.saveAll(Arrays.asList(departments));
            }
        };
    }

}
