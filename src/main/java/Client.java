public class Client {
    private String firstName, lastName, email, phoneNumber, address;

    public Client(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String toString() {
        return "First Name: " + firstName +
                ", Last Name: " + lastName +
                ", Email: " + email +
                ", Phone Number: " + phoneNumber +
                ", Address: " + address;
    }
}