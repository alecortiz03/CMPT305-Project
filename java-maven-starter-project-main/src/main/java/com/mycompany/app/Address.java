public class Address {
    private String houseNum; // Private variable street to store street name
    private String street; // Private variable street to store street name

    public Address(String houseNum, String street) { // Constructor to initialize houseNum and street
        this.houseNum = houseNum; // Assigning houseNum to the instance variable
        this.street = street; // Assigning street to the instance variable
    }
    public Address() { // Default constructor
        this("0", "Unknown"); // Calling the parameterized constructor with default values 0, Unknown
    }
    public String getHouseNum() { // Getter method for houseNum
        return houseNum; // Returning the value of houseNum
    }
    public String getStreet() { // Getter method for street
        return street; // Returning the value of street
    }
    public String toString() { // Overriding toString method
        return this.houseNum + " " + this.street; // Returning the string representation of the address object
    }
    
}
