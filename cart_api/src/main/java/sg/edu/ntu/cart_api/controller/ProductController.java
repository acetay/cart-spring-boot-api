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

    // Inject the ProductService and ProductRepository dependencies
    @Autowired
    ProductService service;

    @Autowired
    ProductRepository repo;

    
    // This method handles a GET request to retrieve all products from the database
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll() {

        // Fetch all products from the database using the repository
        List<Product> products = (List<Product>) repo.findAll();

        // Return an HTTP response with a status code of 200 OK and the list of products as the response body
        return ResponseEntity.ok().body(products);
    }
    

    // This method handles a GET request to retrieve a product by ID
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findById(@PathVariable int id) {

        // Fetch the product from the database by ID using the repository
        Optional<Product> optionalProduct = repo.findById(id);

        // Check if the product was found
        if (optionalProduct.isPresent()) {
            // Get the product from the Optional container
            Product product = optionalProduct.get();
            // Return an HTTP response with a status code of 200 OK and the product as the response body
            return ResponseEntity.ok().body(product);
        } else {
            // Return an HTTP response with a status code of 404 Not Found if the product with the ID is not found in the database
            return ResponseEntity.notFound().build();
        }
    }


    // This method handles a POST request to create a new product
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody Product product) {

        // Create the product using a service
        Product createdProduct = service.create(product);

        // Return an HTTP response with a status code of 201 Created and the created product as the response body
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    
    // This method handles a PUT request to update an existing product by ID
    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable int id) {

        // Fetch the existing product from the database by ID using the repository
        Optional<Product> optionalProduct = repo.findById(id);

        // Check if the product was found
        if (optionalProduct.isPresent()) {
            // Get the existing product from the Optional container
            Product existingProduct = optionalProduct.get();
            // Update the existing product with the new product's data
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            // Save the updated product to the database using the repository
            repo.save(existingProduct);
            // Return an HTTP response with a status code of 200 OK and the updated product as the response body
            return new ResponseEntity<>(existingProduct, HttpStatus.OK);
        } else {
            // Return an HTTP response with a status code of 404 Not Found if the product with the ID is not found in the database
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // This method handles a DELETE request to delete a product by ID
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable int id) {

        // Check if the product exists in the database by ID using the repository
        if (repo.existsById(id)) {
            // Delete the product from the database by ID using the repository
            repo.deleteById(id);
            // Return an HTTP response with a status code of 200 OK if the product is deleted
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Return an HTTP response with a status code of 404 Not Found if the product with the ID is not found in the database
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}