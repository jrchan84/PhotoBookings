package tests;

import model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBooking {

    private Booking bi;

    @BeforeEach
    void runBefore() {
        bi = new model.ActiveBooking("Yuchi", "Portraits", "7:30am",
                "UBC", "7783457801", "75", "Active");
    }

    // Test SetDone() method on a completed Booking
    @Test
    void testSetDoneFalse() {
        bi.setDone();
        assertEquals("Completed", bi.getActive());
    }

    // Test GetDescription()
    @Test
    void testGetDescription() {
        assertEquals("Portraits", bi.getDescription());
    }

    // Test GetTime()
    @Test
    void testGetTime() {
        assertEquals("7:30am", bi.getTime());
    }

    // Test GetLocation()
    @Test
    void testGetLocation() {
        assertEquals("UBC", bi.getLocation());
    }

    // Test GetPhone()
    @Test
    void testGetPhone() {
        assertEquals("7783457801", bi.getPhone());
    }

    // Test GetContact()
    @Test
    void testGetPrice() {
        assertEquals("75", bi.getPrice());
    }

    // Test GetContact()
    @Test
    void testGetContact() {
        assertEquals("Yuchi", bi.getContact());
    }

    // Test GetActive()
    @Test
    void testGetActive() {
        assertEquals("Active", bi.getActive());
    }

    // Test overridden hashcode and equals true case
    @Test
    void testHashCodeAndEqualsTrue() {
        Booking bi2 = new model.ActiveBooking("Yuchi", "Portraits", "7:30am",
                "UBC", "7783457801", "75", "Active");
        assertTrue(bi2.equals(bi));
    }

    // Test overridden hashcode and equals false case
    @Test
    void testHashCodeAndEqualsFalse() {
        Booking bi2 = new model.ActiveBooking("Patrick", "Landscape", "8:30am",
                "TRU", "7783457802", "150", "Active");
        assertFalse(bi2.equals(bi));
    }
}
