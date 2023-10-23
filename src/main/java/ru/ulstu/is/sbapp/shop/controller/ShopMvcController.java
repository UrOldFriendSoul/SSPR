package ru.ulstu.is.sbapp.shop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ulstu.is.sbapp.shop.service.ShopService;
import javax.validation.Valid;
@Controller
@RequestMapping("/shop")
public class ShopMvcController {
    private final ShopService customerService;

    public ShopMvcController(ShopService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getShops(Model model) {
        model.addAttribute("shops",
                customerService.findAllShops().stream()
                        .map(ShopDto::new)
                        .toList());
        return "shop";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editShop(@PathVariable(required = false) Long id,
                               Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("shopDto", new ShopDto());
        } else {
            model.addAttribute("shopId", id);
            model.addAttribute("shopDto", new ShopDto(customerService.findShop(id)));
        }
        return "shop-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveShop(@PathVariable(required = false) Long id,
                               @ModelAttribute @Valid ShopDto shopDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "shop-edit";
        }
        if (id == null || id <= 0) {
            customerService.addShop(shopDto.getNameProduct(), shopDto.getTypeOrder(), shopDto.getCost());
        } else {
            customerService.updateShop(id, shopDto.getNameProduct(), shopDto.getTypeOrder(), shopDto.getCost());
        }
        return "redirect:/shop";
    }

    @PostMapping("/delete/{id}")
    public String deleteShop(@PathVariable Long id) {
        customerService.deleteShop(id);
        return "redirect:/shop";
    }
}
