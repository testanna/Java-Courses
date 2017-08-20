package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String homeTelephone;
    private final String email;

    public ContactData(String firstName, String lastName, String address, String homeTelephone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.homeTelephone = homeTelephone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomeTelephone() {
        return homeTelephone;
    }

    public String getEmail() {
        return email;
    }
}
