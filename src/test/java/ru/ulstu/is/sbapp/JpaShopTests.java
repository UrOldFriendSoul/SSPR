package ru.ulstu.is.sbapp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.ulstu.is.sbapp.shop.model.Customer;
import ru.ulstu.is.sbapp.shop.service.CustomerService;
import ru.ulstu.is.sbapp.shop.service.ShopNotFoundException;
import ru.ulstu.is.sbapp.shop.service.CustomerNotFoundException;
import ru.ulstu.is.sbapp.shop.model.Shop;
import ru.ulstu.is.sbapp.shop.service.ShopService;

import java.util.List;
@SpringBootTest
public class JpaShopTests {
    private static final Logger log = LoggerFactory.getLogger(JpaShopTests.class);

    @Autowired
    private CustomerService customerService;

    @Test
    void testCustomerCreate() {
        customerService.deleteAllCustomers();
        final Customer customer = customerService.addCustomer("Иван", "Иванов", 456608);
        log.info(customer.toString());
        Assertions.assertNotNull(customer.getId());

    }

    @Test
    void testCustomerRead() {
        customerService.deleteAllCustomers();
        final Customer customer = customerService.addCustomer("Иван", "Иванов", 456608);
        log.info(customer.toString());
        final Customer findCustomer = customerService.findCustomer(customer.getId());
        log.info(findCustomer.toString());
        Assertions.assertEquals(customer, findCustomer);

    }

    @Test
    void testCustomerReadNotFound() {
        customerService.deleteAllCustomers();
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomer(-1L));

    }

    @Test
    void testCustomerReadAll() {
        customerService.deleteAllCustomers();
        customerService.addCustomer("Иван", "Иванов", 456608);
        customerService.addCustomer("Петр", "Петров", 880044);
        final List<Customer> customers = customerService.findAllCustomers();
        log.info(customers.toString());
        Assertions.assertEquals(customers.size(), 2);

    }

    @Test
    void testCustomerReadAllEmpty() {
        customerService.deleteAllCustomers();
        final List<Customer> customers = customerService.findAllCustomers();
        log.info(customers.toString());
        Assertions.assertEquals(customers.size(), 0);

    }

    @Autowired
    private ShopService shopService;

    @Test
    void testShopCreate() {
        shopService.deleteAllShops();
        final Shop shop = shopService.addShop("молоко", "еда", 85);
        log.info(shop.toString());
        Assertions.assertNotNull(shop.getId());

    }

    @Test
    void testShopRead() {
        shopService.deleteAllShops();
        final Shop shop = shopService.addShop("молоко", "еда", 85);
        log.info(shop.toString());
        final Shop findShop = shopService.findShop(shop.getId());
        log.info(findShop.toString());
        Assertions.assertEquals(shop, findShop);

    }

    @Test
    void testShopReadNotFound() {
        shopService.deleteAllShops();
        Assertions.assertThrows(ShopNotFoundException.class, () -> shopService.findShop(-1L));

    }

    @Test
    void testShopReadAll() {
        shopService.deleteAllShops();
        shopService.addShop("молоко", "еда", 85);
        shopService.addShop("помада", "косметика", 1299);
        final List<Shop> shops = shopService.findAllShops();
        log.info(shops.toString());
        Assertions.assertEquals(shops.size(), 2);

    }

    @Test
    void testShopReadAllEmpty() {
        shopService.deleteAllShops();
        final List<Shop> shops = shopService.findAllShops();
        log.info(shops.toString());
        Assertions.assertEquals(shops.size(), 0);

    }
    
}
