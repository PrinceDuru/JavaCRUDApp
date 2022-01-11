package game_package;


public class Player {
	
	// Define instance variables
    private String playerId;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String province;
    private String phoneNumber;
    
    // Create the constructor and initialize the variables
    public Player(String playerId, String firstName, String lastName, String address, String postalCode,
                    String province, String phoneNumber) {
    		this.playerId = playerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.postalCode = postalCode;
            this.province = province;
            this.phoneNumber = phoneNumber;
    }
    
    // Also create the default constructor
    public Player() {
    }
    
    // Create getter and setters for the instance variables
    public String getPlayerId() {
            return playerId;
    }
    public void setPlayerId(String playerId) {
            this.playerId = playerId;
    }
    public String getFirstName() {
            return firstName;
    }
    public void setFirstName(String firstName) {
            this.firstName = firstName;
    }
    public String getLastName() {
            return lastName;
    }
    public void setLastName(String lastName) {
            this.lastName = lastName;
    }
    public String getAddress() {
            return address;
    }
    public void setAddress(String address) {
            this.address = address;
    }
    public String getPostalCode() {
            return postalCode;
    }
    public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
    }
    public String getProvince() {
            return province;
    }
    public void setProvince(String province) {
            this.province = province;
    }
    public String getPhoneNumber() {
            return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
    }
    
    // Override toString method from the object super class
    @Override
    public String toString() {
        return this.getFirstName()+" "+this.getLastName();
    }
    
}
