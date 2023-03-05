package sg.edu.ntu.cart_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.ntu.cart_api.entity.Product;
import sg.edu.ntu.cart_api.repository.ProductRepository;

// @Service
// public class ProductService {
    
//     public Product create(){
//         Product p = new Product();
//         p.setId(1);
//         p.setName("Mock Name");
//         p.setDescription(("Mock Desc"));
//         p.setPrice(10f);
//         return p;
//     }
// }

// This class defines a service for creating and managing Product entities
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // This method creates a new Product entity by saving it to the repository
    public Product create(Product product) {
        return repository.save(product);
    }
}


