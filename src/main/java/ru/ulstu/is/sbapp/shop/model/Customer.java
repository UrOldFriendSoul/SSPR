package ru.ulstu.is.sbapp.shop.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Firstname can't be null or empty")
    private String firstName;
    @NotBlank(message = "Lastname can't be null or empty")
    private String lastName;
    private int phoneNumb;
    @ManyToMany
    @JoinTable(name = "customers_shop",
            joinColumns = @JoinColumn(name = "customer_fk"),
            inverseJoinColumns = @JoinColumn(name = "shop_fk"))
    private List<Shop> shop;
    public Customer(){

    }
    public Customer(String firstName, String lastName, int phoneNumb) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumb=phoneNumb;
    }

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getPhoneNumb(){
        return phoneNumb;
    }
    public void setPhoneNumb(int phoneNumb){
        this.phoneNumb=phoneNumb;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumb='" + phoneNumb + '\'' +
                '}';
    }
}
