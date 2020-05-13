package tests;

import model.ActiveBooking;
import model.Booking;
import model.ListOfBookingInfo;
import model.exceptions.ContainsMoreThanOne;
import model.exceptions.DoesNotContain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TestExceptions {
    private ListOfBookingInfo info;

    @BeforeEach
    void runBefore() {
        info = new ListOfBookingInfo();
        Booking b1 = new ActiveBooking("Yuchi", "t","t","t","t",
                "t","t");
        Booking b2 = new ActiveBooking("Evan", "t","t","t","t",
                "t","t");
        Booking b3 = new ActiveBooking("Yuchi", "t","t","t","t",
                "t","t");
        info.addBookingInfo(b1);
        info.addBookingInfo(b2);
        info.addBookingInfo(b3);
    }

    // Test no exception thrown
    @Test
    void testDoesNotContainNoThrow() {
        try {
            info.removeBookingInfo("Evan");
            assertEquals(2, info.getSize());

        } catch (ContainsMoreThanOne e) {
            fail("ContainsMoreThanOne thrown");
        } catch (DoesNotContain e) {
            fail("DoesNotContain thrown");
        }
    }

    // Test exception DoesNotContain thrown
    @Test
    void testDoesNotContainThrow() {
        try {
            info.removeBookingInfo("Dave");
            assertEquals(3, info.getSize());

        } catch (ContainsMoreThanOne e) {
            //
        } catch (DoesNotContain e) {
            System.out.println("Does Not Contain");
        }
    }

    // Test no exception thrown
    @Test
    void testContainsMoreThanOneNoThrow() {
        try {
            info.removeBookingInfo("Evan");
            assertEquals(2, info.getSize());

        } catch (ContainsMoreThanOne e) {
            fail("ContainsMoreThanOne thrown");
        } catch (DoesNotContain e) {
            fail("DoesNotContain thrown");
        }
    }

    // Test exception ContainsMoreThanOne thrown
    @Test
    void testContainsMoreThanOne() {
        try {
            info.removeBookingInfo("Yuchi");
            assertEquals(1, info.getSize());

        } catch (ContainsMoreThanOne e) {
            System.out.println("ContainsMoreThanOne");
        } catch (DoesNotContain e) {
            //
        }
    }
}
