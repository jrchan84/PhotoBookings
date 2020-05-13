package ui;

import model.Booking;
import model.ListOfBookingInfo;

import java.util.Scanner;

import static ui.PhotoBookings.inputNewBooking;
import static ui.PhotoBookings.menu;

public class PrintBookings {

    public static void displayBookings(ListOfBookingInfo info) {

        System.out.print("You have " + getNumOfBookings(info) + " Bookings. ");

        if (getNumOfBookings(info) != 0) {
            System.out.println("Here are your bookings:\n");
        }
        displayPrint(info);
        inputNewBooking(info);
    }

    public static void displayPrint(ListOfBookingInfo info) {
        int count = 0;

        for (int i = 0; i < info.getSize(); i++) {
            Booking b = info.getBookingInfo(i);
            System.out.println(Integer.toString(count += 1) + ":");
            printInfo(b);
        }
        inputNewBooking(info);
    }

    public static void displayViaHash(ListOfBookingInfo info) {
        String hash;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input hashcode: \n");
        hash = scanner.nextLine();

        printInfo(info.getBookingInfoViaKey(hash));
        menu(info);
    }

    public static void printInfo(Booking b) {

        System.out.println(b.getContact());
        System.out.println(b.getDescription());
        System.out.println(b.getTime());
        System.out.println(b.getLocation());
        System.out.println(b.getPhone());
        System.out.println(b.getPrice());
        System.out.println(b.getActive() + "\n");

    }

    public static int getNumOfBookings(ListOfBookingInfo info) {
        return info.getSize();
    }

}
