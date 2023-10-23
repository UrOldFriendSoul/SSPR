package ru.ulstu.is.sbapp.shop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ulstu.is.sbapp.shop.service.CustomerService;
import javax.validation.Valid;
@Controller
@RequestMapping("/customer")
public class CustomerMvcController {
    private final CustomerService customerService;

    public CustomerMvcController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomers(Model model) {
        model.addAttribute("customers",
                customerService.findAllCustomers().stream()
                        .map(CustomerDto::new)
                        .toList());
        return "customer";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editCustomer(@PathVariable(required = false) Long id,
                              Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("customerDto", new CustomerDto());
        } else {
            model.addAttribute("customerId", id);
            model.addAttribute("customerDto", new CustomerDto(customerService.findCustomer(id)));
        }
        return "customer-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveCustomer(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid CustomerDto customerDto,
                             BindingResult bindingResult,
                              Model model) {
       if (bindingResult.hasErrors()) {
          model.addAttribute("errors", bindingResult.getAllErrors());
            return "customer-edit";
       }
        if (id == null || id <= 0) {
            customerService.addCustomer(customerDto.getFirstName(), customerDto.getLastName(), customerDto.getPhoneNumb());
        } else {
            customerService.updateCustomer(id, customerDto.getFirstName(), customerDto.getLastName(), customerDto.getPhoneNumb());
        }
        return "redirect:/customer";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer";
    }
}
