package sg.edu.ntu.cart_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import sg.edu.ntu.cart_api.repository.ProductRepository;
import sg.edu.ntu.cart_api.repository.CartRepository;
import sg.edu.ntu.cart_api.entity.Cart;
import sg.edu.ntu.cart_api.entity.Product;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired 
    ProductRepository productRepo;

    @Autowired
    CartRepository cartRepo;
    
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Cart>> list(){
        List<Cart> cartRecords = (List<Cart>)cartRepo.findAll();

        if(cartRecords.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(cartRecords);
    }

    @RequestMapping(value="/add/{productId}", method = RequestMethod.POST)
    public ResponseEntity add(@RequestParam Optional<Integer> quantity, @PathVariable int productId){

        // Check if product id exist in product table
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isPresent()){
            
            // Check if productId exist in the cart table       
            Optional<Cart> optionalCart = cartRepo.findByProductId(productId);
            if(optionalCart.isPresent()){
                Cart cartItem = optionalCart.get();
                int currentQuantity = cartItem.getQuantity();
                cartItem.setQuantity(quantity.orElseGet(() -> currentQuantity + 1)); // when quantity exist, set it. if not, increase by 1.                
                cartRepo.save(cartItem);                
            }else{
                // create a new record in cart and set quantity to 1
                Cart newCartItem = new Cart();
                newCartItem.setQuantity(1);
                newCartItem.setProduct(optionalProduct.get()); // this optional product exist, and the id value is filled.
                cartRepo.save(newCartItem);
            } 
            return ResponseEntity.ok().build();   
        }

        return ResponseEntity.badRequest().build();
    }
}



    
    