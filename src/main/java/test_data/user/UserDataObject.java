package test_data.user;

public class UserDataObject {
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String state;
    private String city;
    private String add1;
    private String add2;
    private String zipCode;
    private String phoneNum;

    public UserDataObject(String firstName, String lastName, String email, String country, String state
            , String city, String address1, String address2, String zipCode, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.state = state;
        this.city = city;
        this.add1 = address1;
        this.add2 = address2;
        this.zipCode = zipCode;
        this.phoneNum = phone;
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

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAdd1() {
        return add1;
    }

    public String getAdd2() {
        return add2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
}
