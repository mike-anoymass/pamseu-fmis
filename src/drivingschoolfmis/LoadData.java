package drivingschoolfmis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

class LoadData {
    ObservableList<Receipts> receiptData = FXCollections.observableArrayList();
    ObservableList<Payment> payment = FXCollections.observableArrayList();
    static ObservableList<Employee> employees = FXCollections.observableArrayList();
    ResultSet rs;
    static ResultSet rsE;
    static EmployeeQueries staffQuery = new EmployeeQueries();

    static ObservableList<Employee> loadEmployees(ResultSet rsE){
        try{
            while(rsE.next()){
                employees.add(new Employee(
                        rsE.getString("staffID"),
                        rsE.getString("fullname"),
                        rsE.getString("staffPhone"),
                        rsE.getString("staffPostalAddress"),
                        rsE.getString("staffPAddr"),
                        rsE.getString("department"),
                        rsE.getString("location"),
                        rsE.getString("gPhone"),
                        rsE.getString("employeeStatus"),
                        rsE.getString("salaryDesired"),
                        rsE.getString("workingHours"),
                        rsE.getString("date"),
                        rsE.getString("dateOfEntry"),
                        rsE.getString("gender"),
                        rsE.getString("dob")

                ));
            }

            return employees;

        }catch(SQLException ex){
            System.err.println(ex);
        }

        return null;
    }

    public ObservableList<Receipts> loadReceipts() {

        try{
            rs = ReceiptQueries.getFeesReceipts();
            while(rs.next()){
                receiptData.add(new Receipts(
                        rs.getString("receiptNo"),
                        rs.getString("aDate"),
                        rs.getString("dateOfPayment"),
                        rs.getString("amount"),
                        rs.getString("modeOfPayment"),
                        rs.getString("paymentOf"),
                        rs.getString("user"),
                        rs.getString("reference"),
                        rs.getString("fullname")
                ));
            }

            return receiptData;

        }catch(SQLException ex){
            System.err.println(ex);
        }

        return null;
    }

    ObservableList<Payment> loadPayments() {
        try {
            rs = PaymentQueries.getAllPayments();
            while (rs.next()) {
                payment.add(new Payment(
                        rs.getString("id"),
                        rs.getString("expense"),
                        rs.getString("date"),
                        rs.getString("dateOfPayment"),
                        rs.getString("amount"),
                        rs.getString("mode"),
                        rs.getString("ref"),
                        rs.getString("mirage"),
                        rs.getString("user")
                        )
                );
            }
            return payment;
        } catch (SQLException ex) {
            Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

     ObservableList<Course> loadCourseTableData() {
        ObservableList<Course> courseData = FXCollections.observableArrayList();
        try {
            rs = new CourseQueries().getAllCourses();
            while (rs.next()) {
                courseData.add(new Course(rs.getString("code"), rs.getString("name")));
            }

            return courseData;
        } catch (SQLException ex) {
            Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courseData;

    }
}
