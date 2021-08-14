package gr.uop;

public class data {

    private String arrivalDateTime;
    private String registrationNumber;
    private int cost;
    private String value;
    
    //Receive data through the constructor
    data(String arrivalDateTime, String registrationNumber, int cost, String value) {
        this.arrivalDateTime = arrivalDateTime;
        this.registrationNumber = registrationNumber;
        this.cost = cost;
        this.value = value;
    }

    //Get methods for each field
    public String getArrivalDateTime() {
        return arrivalDateTime;
    }
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public int getCost() {
        return cost;
    }
    public String getValue() {
        return value;
    }
}