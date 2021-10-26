package com.example.storeunited;

public class Customer {

    String CustomerName, CustomerEmail, CustomerID, CustomerMobileNumber;

    public Customer(){

    }

    public Customer(String customerName, String customerEmail, String customerID, String customerMobileNumber) {
        CustomerName = customerName;
        CustomerEmail = customerEmail;
        CustomerID = customerID;
        CustomerMobileNumber = customerMobileNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerMobileNumber() {
        return CustomerMobileNumber;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        CustomerMobileNumber = customerMobileNumber;
    }
}
