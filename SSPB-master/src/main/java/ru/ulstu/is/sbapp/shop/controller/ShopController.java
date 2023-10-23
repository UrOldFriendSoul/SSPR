package ru.ulstu.is.sbapp.shop.controller;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.WebConfiguration;
import ru.ulstu.is.sbapp.shop.model.Shop;
import ru.ulstu.is.sbapp.shop.service.ShopService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/shop")
public class ShopController {
    private final ShopService customerService;
    public ShopController(ShopService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ShopDto getShop(@PathVariable Long id) {
        return new ShopDto(customerService.findShop(id));
    }

    @GetMapping
    public List<ShopDto> getShops() {
        return customerService.findAllShops().stream()
                .map(ShopDto::new)
                .toList();
    }

    @PostMapping
    public ShopDto createShop(@RequestBody @Valid ShopDto shopDto){
        return new ShopDto(customerService.addShop(shopDto.getNameProduct(), shopDto.getTypeOrder(), shopDto.getCost()));
    }

    @PutMapping("/{id}")
    public ShopDto updateShop(@PathVariable Long id,
                              @RequestBody @Valid ShopDto shopDto) {
        return new ShopDto(customerService.updateShop(id, shopDto.getNameProduct(), shopDto.getTypeOrder(), shopDto.getCost()));
    }

    @DeleteMapping("/{id}")
    public ShopDto deleteShop(@PathVariable Long id) {
        return new ShopDto(customerService.deleteShop(id));
    }

}
