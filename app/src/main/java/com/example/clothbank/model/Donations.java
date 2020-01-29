package com.example.clothbank.model;

public class Donations {
    private String pickuptime, noofcloth, image;
    private User donor;

    public String getPickuptime() {
        return pickuptime;
    }

    public String getNoofcloth() {
        return noofcloth;
    }

    public String getImage() {
        return image;
    }

    public User getDonor() {
        return donor;
    }
}
