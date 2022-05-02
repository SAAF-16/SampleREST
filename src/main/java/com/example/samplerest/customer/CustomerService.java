package com.example.samplerest.customer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public Customer getCustomer(BigDecimal customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException(
                    "customer with id " + customerId + " doesn't exists");
        }
        return customerRepository.findCustomerById(customerId);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("email already taken");
        }
        System.out.println(customer);
        customerRepository.save(customer);
    }

    public void deleteCustomer(BigDecimal customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException(
                    "customer with id " + customerId + " doesn't exists");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(BigDecimal customerId, String name, String email) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException(
                    "customer with id " + customerId + " doesn't exists");
        }
        Customer customer = customerRepository.findCustomerById(customerId);
        if (name != null && !name.isEmpty() && !name.equals(customer.getName())) {
            customer.setName(name);
        }
        if (email != null && !email.isEmpty()) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("email already taken");
            } else {
                customer.setEmail(email);
            }
        }
    }

    public CustomerDTO entityToDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer dtoToEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

}
