package ru.ulstu.is.sbapp.shop.controller;
import org.springframework.web.bind.annotation.*;

import ru.ulstu.is.sbapp.WebConfiguration;
import ru.ulstu.is.sbapp.shop.model.Customer;
import ru.ulstu.is.sbapp.shop.model.Shop;
import ru.ulstu.is.sbapp.shop.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return new CustomerDto(customerService.findCustomer(id));
    }

    @GetMapping
    public List<CustomerDto> getCustomers() {
        return customerService.findAllCustomers().stream()
                .map(CustomerDto::new)
                .toList();
    }

    @PostMapping
    public CustomerDto createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        return new CustomerDto(customerService.addCustomer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getPhoneNumb()));
    }

    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id,
                                      @RequestBody @Valid CustomerDto customerDto) {
        return new CustomerDto(customerService.updateCustomer(id, customerDto.getFirstName(), customerDto.getLastName(), customerDto.getPhoneNumb()));
    }

    @DeleteMapping("/{id}")
    public CustomerDto deleteCustomer(@PathVariable Long id) {
        return new CustomerDto(customerService.deleteCustomer(id));
    }
}

