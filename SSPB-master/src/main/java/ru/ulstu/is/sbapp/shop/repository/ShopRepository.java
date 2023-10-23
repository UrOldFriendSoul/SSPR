package ru.ulstu.is.sbapp.shop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.shop.model.Shop;
public interface ShopRepository extends JpaRepository<Shop, Long>{
}
