package ru.ulstu.is.sbapp.shop.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name product can't be null or empty")
    String NameProduct;
    @NotBlank(message = "Type order can't be null or empty")
    String TypeOrder;
    int cost;
    @ManyToMany(mappedBy = "shop")
    private List<Customer> customer;
    public Shop(){

    }
    public Shop(String TypeOrder, String NameProduct, int cost){
        this.TypeOrder=TypeOrder;
        this.NameProduct=NameProduct;
        this.cost=cost;
    }
    public Long getId(){return id;}
    public String getTypeOrder(){
        return TypeOrder;
    }
    public void setTypeOrder(String typeOrder){
        this.TypeOrder=typeOrder;
    }
    public String getNameProduct(){
        return NameProduct;
    }
    public void setNameProduct(String NameProduct){
        this.NameProduct=NameProduct;
    }
    public int getCost(){
        return cost;
    }
    public void setCost(int cost){
        this.cost=cost;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(id, shop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", nameProduct='" + NameProduct + '\'' +
                ", typeOrder='" + TypeOrder + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
