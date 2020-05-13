package tests;

import model.ActiveBooking;
import model.Booking;
import model.ListOfBookingInfo;
import model.exceptions.ContainsMoreThanOne;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.*;

public class TestListOfBooking {

    private Booking bi1 = new ActiveBooking("Yuchi", "Portraits", "7:30am",
            "UBC", "7783457801", "75", "Active");
    private Booking bi2 = new ActiveBooking("Justin", "Products", "9:30am",
            "Kerrisdale", "7782230280", "150", "Active");
    private Booking bi3 = new ActiveBooking("Alex", "Building", "4:00pm",
            "Downtown", "6045346799", "50", "Active");

    // Test addBookingInfo()
    @Test
    void testAddBookingInfo() {
        ListOfBookingInfo lobi = new ListOfBookingInfo();
        lobi.addBookingInfo(bi1);

        assertEquals(lobi.getBookingInfo(0), bi1);
    }

    // Test GetBookingInfo()
    @Test
    void testGetBookingInfo() {
        ListOfBookingInfo lobi = new ListOfBookingInfo();
        lobi.addBookingInfo(bi2);

        assertEquals(bi2, lobi.getBookingInfo(0));
    }

    // Test GetBookingInfo() at index out of bounds. Should return null instead of an exception
    @Test
    void testGetBookingInfoOUB() {
        ListOfBookingInfo lobi = new ListOfBookingInfo();
        lobi.addBookingInfo(bi1);
        lobi.addBookingInfo(bi2);

        assertEquals(null, lobi.getBookingInfo(2));
    }

    // Test getSize()
    @Test
    void testGetSize() {
        ListOfBookingInfo lobi = new ListOfBookingInfo();
        lobi.addBookingInfo(bi1);
        lobi.addBookingInfo(bi2);
        lobi.addBookingInfo(bi3);

        assertEquals(3, lobi.getSize());
    }

    // Test GetInfoViaKey
    @Test
    void testGetInfoViaKey() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);

        assertEquals(bi2, info.getBookingInfoViaKey("Justin"));
    }

    // Test GetInfoViaKey no Booking
    @Test
    void testGetInfoViaKeyNull() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);

        assertEquals(null, info.getBookingInfoViaKey("Alex"));
    }

    // Test setObserverCount count and getObservedCount
    @Test
    void testSetObserverCount() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);

        assertEquals(2, info.getObservedCount());
    }

    // Test ContainsBookingInfo True and False case
    @Test
    void testContainsBookingInfo() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);

        assertTrue(info.containsBookingInfo("Yuchi"));
        assertFalse(info.containsBookingInfo("Alex"));
    }

    // Test returnMultiple
    @Test
    void testReturnMultiple() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);

        ListOfBookingInfo multiple = info.returnMultiple("Yuchi");
        assertEquals(2, multiple.getSize());
    }

    // Test returnMultiple null
    @Test
    void testReturnMultipleNull() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);

        ListOfBookingInfo multiple = info.returnMultiple("Alex");
        assertEquals(0, multiple.getSize());
    }

    // Test containsMultiple
    @Test
    void testContainsMultiple() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);

        assertEquals(2, info.containsMultiple("Yuchi"));
    }

    // Test containsMultiple null
    @Test
    void testContainsMultipleNull() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);

        assertEquals(0, info.containsMultiple("Alex"));
    }

    // Test removeMultiple. No null case as there will always be multiple same name Bookings if called
    @Test
    void testRemoveMultiple() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);

        info.removeMultiple(bi1);
        assertEquals(2, info.getSize());
    }

    // Test removeBookingInfo normal case
    @Test
    void testRemoveBookingInfoNormal() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);
        info.addBookingInfo(bi3);
        try {
            info.removeBookingInfo("Yuchi");
        } catch (ConcurrentModificationException e) {
                //
        } catch (Exception e) {
            System.out.println("Something went wrong in testRemoveBookingInfoNormal");
        }

        assertEquals(2, info.getSize());
    }

    // Test removeBookingInfo DoesNotContain case.
    @Test
    void testRemoveBookingInfoDoesNotContain() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);
        info.addBookingInfo(bi3);
        try {
            info.removeBookingInfo("Patrick");
        } catch (ConcurrentModificationException e) {
            //
        } catch (Exception e) {
            System.out.println("Something went wrong in testRemoveBookingInfoNormal");
        }

        assertEquals(3, info.getSize());
    }

    // Test removeBookingInfo ContainsMoreThanOne case
    @Test
    void testRemoveBookingInfoContainsMoreThanOne() {
        ListOfBookingInfo info = new ListOfBookingInfo();
        info.addBookingInfo(bi1);
        info.addBookingInfo(bi2);
        info.addBookingInfo(bi2);
        try {
            info.removeBookingInfo("Justin");
        } catch (ContainsMoreThanOne e) {
            info.removeMultiple(bi2);
        } catch (ConcurrentModificationException e) {
            //
        } catch (Exception e) {
            System.out.println("Something went wrong in testRemoveBookingInfoNormal");
        }

        assertEquals(2, info.getSize());
    }
}