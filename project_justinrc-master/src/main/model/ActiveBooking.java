package model;

public class ActiveBooking extends Booking {

    public ActiveBooking(String contact, String description, String time, String location, String phone, String price,
                         String active) {

        super(contact, description, time, location, phone, price, active);
    }

    @Override
    public void setDone() {
        active = "Completed";
    }
}
