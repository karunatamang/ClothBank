package com.example.clothbank.model;

public class Donation {
    private String donor, pickuptime, noofcloth, image;

    public Donation(String donor, String pickuptime, String noofcloth, String image) {
        this.donor = donor;
        this.pickuptime = pickuptime;
        this.noofcloth = noofcloth;
        this.image = image;
    }

    public Donation(String donor, String pickuptime, String noofcloth) {
        this.donor = donor;
        this.pickuptime = pickuptime;
        this.noofcloth = noofcloth;
    }

    public String getDonor() {
        return donor;
    }

    public String getPickuptime() {
        return pickuptime;
    }

    public String getNoofcloth() {
        return noofcloth;
    }

    public String getImage() {
        return image;
    }
}
