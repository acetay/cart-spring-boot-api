package sg.edu.ntu.cart_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.cart_api.entity.Product;
import sg.edu.ntu.cart_api.service.ProductService;
import sg.edu.ntu.cart_api.repository.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    ProductRepository repo;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = (List<Product>)repo.findAll(); // fetch all products from database 
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable int id){
        Optional<Product> optionalProduct = repo.findById(id); // fetch product from database by ID
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get(); // get the product from Optional container
            return ResponseEntity.ok().body(product); // return 200 OK status and the product in the response body
        }else {
            return ResponseEntity.notFound().build(); // return 404 Not Found status if product with ID is not found in the database
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody Product product){
        Product createdProduct = service.create(product); // create the product using the service
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct); // return 201 Created status and the created product in the response body
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable int id){
        Optional<Product> optionalProduct = repo.findById(id);
            if (optionalProduct.isPresent()) {
                Product existingProduct = optionalProduct.get();
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                repo.save(existingProduct);
            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
     }else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}