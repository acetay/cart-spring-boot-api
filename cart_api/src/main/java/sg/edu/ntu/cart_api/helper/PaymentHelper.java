package sg.edu.ntu.cart_api.helper;

import java.text.DecimalFormat;

public class PaymentHelper {
    float minimumPurchase;
    final DecimalFormat df = new DecimalFormat("0.00");

    public PaymentHelper(float minimumPurchase){
        this.minimumPurchase = minimumPurchase;
    }

    // To be called by PaymentController
    // To determine whether the payable amount is above minimumPurchase
    public boolean hasMinimumPayable(float payable) throws Exception {

        // 400
        if(payable < 0) throw new Exception("Payable cannot be a negative value");

        // 400
        if(payable < minimumPurchase) throw new Exception("Payable must be greater than $"+df.format(minimumPurchase));

        // 200
        return true;
    }
}

