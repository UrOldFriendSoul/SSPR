package ru.ulstu.is.sbapp.shop.controller;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.shop.model.Customer;

import javax.validation.constraints.NotBlank;

public class CustomerDto {
    private long id;
    @NotBlank(message = "Firstname can't be null or empty")
    private  String firstName;
    @NotBlank(message = "Lastname can't be null or empty")
    private  String lastName;
    private int phoneNumb;
    CustomerDto(){

    }
    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName=customer.getLastName();
        this.phoneNumb= customer.getPhoneNumb();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName){this.firstName=firstName;}
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName){this.lastName=lastName;}
    public int getPhoneNumb(){return phoneNumb;}
    public void setPhoneNumb(int phoneNumb){this.phoneNumb=phoneNumb;}
}
