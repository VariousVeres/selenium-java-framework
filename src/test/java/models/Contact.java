package models;

public class Contact {
    String country;
    String name;
    int mobileNumber;
    int zipCode;
    String address;
    String city;
    String state;

    private Contact(ContactBuilder b)  {
        this.country=b.country;
        this.name=b.name;
        this.mobileNumber=b.mobileNumber;
        this.zipCode=b.zipCode;
    }


    public static class ContactBuilder {
        String country;
        String name;
        int mobileNumber;
        int zipCode;
        String address;
        String city;
        String state;
    }

    buildContact

}
