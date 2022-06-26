package drivingschoolfmis;

import java.sql.ResultSet;

import static drivingschoolfmis.NotificationClass.showNotification;

public class AddPayment {
    ResultSet rs;

    public boolean add(Payment p){
        if (new ValidateFieldsClass().validatePaymentFields(p)) {
            boolean added = PaymentQueries.addPayment(p);
            return added;
        }
        return true;
    }
}
