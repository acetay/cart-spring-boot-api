package sg.edu.ntu.cart_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.cart_api.helper.PaymentHelper;

import java.text.DecimalFormat;

@RestController
public class PaymentController {

    @Autowired PaymentHelper helper;

    final DecimalFormat df = new DecimalFormat("0.00");
    
    /* 
    Path Variable - /payment/sgd
    Request Parameter - ?payable=5.2
    http://localhost:8080/payment?payable=5.30&currency=SGD
    */ 
    @RequestMapping(value="/payment", method=RequestMethod.POST)
    public ResponseEntity<ResponseMessage> payment(float payable){

        try{
            if(helper.hasMinimumPayable(payable)){
                return ResponseEntity.ok().body(new ResponseMessage("Payment successful"));
            }
            return ResponseEntity.badRequest().body(new ResponseMessage("Payable is below minimum purchase."));
            
        }catch(Exception e){
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



 