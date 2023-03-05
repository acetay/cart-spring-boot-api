package sg.edu.ntu.cart_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.cart_api.helper.PaymentHelper;

import java.text.DecimalFormat;

// This class defines a RESTful web service for handling payment-related requests
@RestController
public class PaymentController {

    // Inject the PaymentHelper dependency using Spring's @Autowired annotation
    @Autowired PaymentHelper helper;

    // Define a Decimal Format to format currency values
    final DecimalFormat df = new DecimalFormat("0.00");
    
    /* 
    Path Variable - /payment/sgd
    Request Parameter - ?payable=5.2
    http://localhost:8080/payment?payable=5.30&currency=SGD
    */ 
    // This method handles a POST request to process a payment
    @RequestMapping(value="/payment", method=RequestMethod.POST)
    public ResponseEntity<ResponseMessage> payment(float payable){

        try{
            // Check if the payable amount is above the minimum purchase amount using a PaymentHelper
            if(helper.hasMinimumPayable(payable)){
                // Return an HTTP response with a status code of 200 OK and a success message if the payment is successful
                return ResponseEntity.ok().body(new ResponseMessage("Payment successful"));
            }
            // Return an HTTP response with a status code of 400 Bad Request and an error message if the payable amount is below the minimum purchase amount
            return ResponseEntity.badRequest().body(new ResponseMessage("Payable is below minimum purchase."));
            
        }catch(Exception e){
            // If an exception occurs, print the stack trace and return an HTTP response with a status code of 400 Bad Request and an error message
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        }
    }

}


class ResponseMessage{
    String message;

    // Constructor
    public ResponseMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}



 