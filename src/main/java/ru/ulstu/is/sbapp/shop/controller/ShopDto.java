package ru.ulstu.is.sbapp.shop.controller;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.shop.model.Shop;

import javax.validation.constraints.NotBlank;

public class ShopDto {
    private  long id;
    @NotBlank(message = "Name product can't be null or empty")
    private  String nameProduct;
    @NotBlank(message = "Type order can't be null or empty")
    private  String typeOrder;
    private  int cost;
    public ShopDto(){

    }
    public ShopDto(Shop shop) {
        this.id = shop.getId();
        this.nameProduct= shop.getNameProduct();;
        this.typeOrder=shop.getTypeOrder();
        this.cost= shop.getCost();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct){this.nameProduct=nameProduct;}
    public String getTypeOrder(){return typeOrder;}
    public void setTypeOrder(String typeOrder){this.typeOrder=typeOrder;}
    public int getCost(){return cost;}
    public void setCost(int cost){this.cost=cost;}

}
