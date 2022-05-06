package models;

public class Contact {
    private String country;
    private String name;
    private int mobileNumber;
    int zipCode;
    String address;
    String city;
    String state;

    private Contact(ContactBuilder b) {
        this.country = (b.country == null) ? "Default country" : b.country;
        this.name = (b.name == null) ? "Default name" : b.name;
        this.mobileNumber = (b.mobileNumber == 0) ? 1911025 : b.mobileNumber;
        this.zipCode = (b.zipCode == 0) ? 199 : b.zipCode;
        this.address = (b.address == null) ? "Default address" : b.address;
        this.city = (b.city == null) ? "Default city" : b.city;
        this.state = (b.state == null) ? "Default state" : b.state;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public static class ContactBuilder {
        String country;
        String name;
        int mobileNumber;
        int zipCode;
        String address;
        String city;
        String state;

        public ContactBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public ContactBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ContactBuilder setMobileNumber(int mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public ContactBuilder setZipCode(int zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public ContactBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public ContactBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public ContactBuilder setState(String state) {
            this.state = state;
            return this;
        }

        public Contact buildContact() {
            return new Contact(this);
        }

    }


}
