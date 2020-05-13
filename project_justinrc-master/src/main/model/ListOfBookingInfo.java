package model;

import model.exceptions.ContainsMoreThanOne;
import model.exceptions.DoesNotContain;
import model.observer.AddBookingObserver;

import java.util.*;

public class ListOfBookingInfo extends Observable {
    private List<Booking> info;
    private Map<String, Booking> bookingMap;
    private int observerCount = 0;

    // EFFECTS: constructs an empty list of type Booking
    public ListOfBookingInfo() {
        info = new ArrayList<>();
        bookingMap = new HashMap<>();
        Observer observer = new AddBookingObserver();
        addObserver(observer);
    }

    // EFFECTS: returns Booking at given index of ListOfBookingInfo
    public Booking getBookingInfo(int index) {
        if (index < getSize()) {
            return info.get(index);
        }
        return null;
    }

    // EFFECTS:
    public Booking getBookingInfoViaKey(String s) {
        return bookingMap.get(s);
    }

    // MODIFIES: This
    // EFFECTS: Creates new Booking with given param and adds it to ListOfBookingInfo
    public void addBookingInfo(Booking bi) {
        info.add(bi);
        bookingMap.put(bi.getContact(), bi);
        setChanged();
        notifyObservers(bi);
    }

    // Effects: sets observer statistic of added bookings
    public void setObserverCount() {
        observerCount += 1;
    }

    // Effects: returns observer statistic of added bookings
    public int getObservedCount() {
        return observerCount;
    }

    // EFFECTS: Returns true if list contains contact, else false.
    public boolean containsBookingInfo(String name) {
        for (Booking bi : info) {
            if (name.equals(bi.getContact())) {
                return true;
            }
        }
        return false;
    }

    // Effects: returns a ListOfBookingInfo with Bookings with given name
    public ListOfBookingInfo returnMultiple(String name) {
        ListOfBookingInfo multiple = new ListOfBookingInfo();

        for (Booking bi : info) {
            if (name.equals(bi.getContact())) {
                multiple.addBookingInfo(bi);
            }
        }
        return multiple;
    }

    // Effects: returns number of bookings with given contact name
    public int containsMultiple(String name) {
        int count = 0;

        for (Booking bi : info) {
            if (name.equals(bi.getContact())) {
                count += 1;
            }
        }
        return count;
    }

    // Effects: removes the multiple booking at given index.
    public void removeMultiple(Booking bi) {
        try {
            for (Booking b : info) {
                if (b.equals(bi)) {
                    info.remove(b);
                }
            }
        } catch (ConcurrentModificationException e) {
            //
        }
    }

    // MODIFIES: This
    // EFFECTS: removes a booking with the given contact name
    public void removeBookingInfo(String name) throws DoesNotContain, ContainsMoreThanOne {

        if (containsMultiple(name) > 1) {
            throw new ContainsMoreThanOne();

        } else if (containsBookingInfo(name)) {
            for (Booking bi : info) {
                if (name.equals(bi.getContact())) {

                    info.remove(bi);
                    bookingMap.remove(bi.getContact());
                }
            }
            System.out.println("Booking has been removed.\n");

        } else if (containsMultiple(name) == 0) {
            System.out.println("There is no booking with that contact name.");
            throw new DoesNotContain();
        }
    }

    // EFFECTS: returns size of list
    public int getSize() {
        return info.size();
    }

}
