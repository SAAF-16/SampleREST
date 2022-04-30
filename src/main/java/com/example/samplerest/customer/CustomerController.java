package com.example.samplerest.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "{customerId}")
    public CustomerDTO getSingleCustomer(@PathVariable("customerId") BigDecimal customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return customerService.entityToDTO(customer);
    }

    @GetMapping("/html/{customerId}")
    public ModelAndView getHtmlSingleCustomer(@PathVariable("customerId") BigDecimal customerId, ModelAndView modelAndView) {
        Customer customer = customerService.getCustomer(customerId);
        modelAndView.addObject("customer", customerService.entityToDTO(customer));
        modelAndView.setViewName("customer/list");

        return modelAndView;
    }

    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomers().stream()
                .map(customerService::entityToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void registerCustomer(@RequestBody CustomerDTO customerDTO) {

        Customer customer = customerService.dtoToEntity(customerDTO);
        customerService.addCustomer(customer);

    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") BigDecimal customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(@PathVariable("customerId") BigDecimal customerId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email) {

        customerService.updateCustomer(customerId, name, email);
    }
}
