package ru.ulstu.is.sbapp.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ru.ulstu.is.sbapp.shop.model.Customer;
import ru.ulstu.is.sbapp.shop.model.Shop;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import ru.ulstu.is.sbapp.shop.repository.ShopRepository;
import ru.ulstu.is.sbapp.shop.repository.CustomerRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidatorUtil validatorUtil;
    private final ShopRepository shopRepository;

    public CustomerService(CustomerRepository customerRepository,
                          ValidatorUtil validatorUtil, ShopRepository shopRepository) {
        this.customerRepository = customerRepository;
        this.validatorUtil = validatorUtil;
        this.shopRepository=shopRepository;
    }

    @Transactional
    public Customer addCustomer(String firstName, String lastName, int phoneNumb) {
        final Customer customer = new Customer(firstName, lastName, phoneNumb);
        validatorUtil.validate(customer);
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Customer findCustomer(Long id) {
        final Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer updateCustomer(Long id, String firstName, String lastName, int phoneNumb) {
        final Customer currentCustomer = findCustomer(id);
        currentCustomer.setFirstName(firstName);
        currentCustomer.setLastName(lastName);
        currentCustomer.setPhoneNumb(phoneNumb);
        validatorUtil.validate(currentCustomer);
        return customerRepository.save(currentCustomer);
    }

    @Transactional
    public Customer deleteCustomer(Long id) {
        final Customer currentCustomer = findCustomer(id);
        customerRepository.delete(currentCustomer);
        return currentCustomer;
    }

    @Transactional
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

}
