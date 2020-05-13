package model;

import java.util.Objects;

public abstract class Booking {
    private String contact;
    private String description;
    private String time;
    private String location;
    private String phone;
    private String price;
    protected String active;


    // MODIFIES: THIS
    // EFFECTS: Constructs a new Booking initializing info to given parameters.
    public Booking(String name, String info, String time, String place, String phone, String price, String active) {
        this.contact = name;
        this.description = info;
        this.time = time;
        this.location = place;
        this.phone = phone;
        this.price = price;
        this.active = active;
    }

    // EFFECT: Returns contact
    public String getContact() {
        return this.contact;
    }

    // EFFECT: Returns Description
    public String getDescription() {
        return this.description;
    }

    // EFFECT: Returns time
    public String getTime() {
        return this.time;
    }

    // EFFECT: Returns location
    public String getLocation() {
        return this.location;
    }

    // EFFECT: Returns phone
    public String getPhone() {
        return this.phone;
    }

    // EFFECT: Returns price
    public String getPrice() {
        return this.price;
    }

    // EFFECTS: Returns active
    public String getActive() {
        return this.active;
    }


    // MODIFIES : This
    // EFFECTS: Changes active value to "Completed"
    public abstract void setDone();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Booking booking = (Booking) o;
        return Objects.equals(contact, booking.contact) && Objects.equals(description, booking.description)
                && Objects.equals(time, booking.time) && Objects.equals(location, booking.location)
                && Objects.equals(phone, booking.phone) && Objects.equals(price, booking.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact, description, time, location, phone, price);
    }
}


