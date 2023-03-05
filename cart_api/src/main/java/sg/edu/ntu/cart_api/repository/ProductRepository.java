package sg.edu.ntu.cart_api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import sg.edu.ntu.cart_api.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    
}
