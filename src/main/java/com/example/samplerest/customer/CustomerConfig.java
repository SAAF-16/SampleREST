package com.example.samplerest.customer;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer max = new Customer(
                    "Max",
                    "maxpower@gmail.com"
            );

            Customer mario = new Customer(
                    "Mario",
                    "supermario@gmail.com"
            );

            Customer justin = new Customer(
                    "Justin",
                    "justintime@gmail.com"
            );
            customerRepository.saveAll(List.of(max, mario, justin));
        };
    }
}
