package model.observer;

import model.Booking;
import model.ListOfBookingInfo;

import java.util.Observable;
import java.util.Observer;

public class AddBookingObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Booking b = (Booking) arg;
        System.out.println(b.getContact() + "'s booking has been added.");
        ListOfBookingInfo info = (ListOfBookingInfo) o;
        info.setObserverCount();
    }

}
