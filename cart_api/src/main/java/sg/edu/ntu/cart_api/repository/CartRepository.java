package sg.edu.ntu.cart_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sg.edu.ntu.cart_api.entity.Cart;
import sg.edu.ntu.cart_api.entity.Product;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>{
    Optional<Cart> findByProductId(int id);
}