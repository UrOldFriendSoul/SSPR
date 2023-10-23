package ru.ulstu.is.sbapp.shop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.shop.model.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
