package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ActiveBooking;
import model.Booking;
import model.ListOfBookingInfo;
import model.exceptions.ContainsMoreThanOne;
import model.exceptions.DoesNotContain;

import java.io.*;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import static java.lang.System.exit;
import static ui.PrintBookings.*;

public class PhotoBookings extends Application {
    /// OPERATION fields
    private static int originalCount = 0;
    private static ListOfBookingInfo info;

    /// JAVA FX fields
    private Stage stage;
    private Scene menu;
    private Scene searchMenu;
    private Scene removeMenu;
    private Scene viewMenu;
    private Scene viewAddMenu;
    private Button viewButton;
    private Button removeButton;
    private Button searchButton;
    private Button exitButton;
    private Button backButton;
    private Button enterButton;
    private Button removeConfirmButton;
    private Button viewAddButton;
    private Button viewBackButton;
    private Button addConfirmButton;
    private TextField name;
    private TextField removeName;
    private TextField contact;
    private TextField description;
    private TextField time;
    private TextField location;
    private TextField phone;
    private TextField price;

    private String searchInput;

    public static void main(String[] args) {

        System.out.println("\nWelcome to PhotoBookings V.1.0.");
        info = new ListOfBookingInfo();
        listOfBookingInfoInstance(info);
        setOriginalCount(info);
        launch(args);
        menu(info);
    }

    //////// JAVA FX METHODS ////////
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        Image image = new Image("file:background_main.jpg");
        ImageView mv = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(mv, menuPane());

        menu = new Scene(root, 400, 855);
        primaryStage.setScene(menu);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Photo Bookings");
        primaryStage.show();
    }


    private GridPane menuPane() {
        GridPane pane = new GridPane();
        pane.add(viewPane(), 0, 0, 1,1);
        pane.add(removePane(), 0, 1, 1, 1);
        pane.add(searchPane(), 0, 2, 1, 1);
        pane.add(exitPane(), 0, 3, 1, 1);
        return pane;
    }

    private HBox exitPane() {
        exitButton = new Button("");
        exitButton.setStyle("-fx-background-color: transparent;");
        exitButton.setPrefWidth(95);
        exitButton.setPrefHeight(15);
        exitButton.setOnAction(e -> exitButtonClick());

        HBox pane = new HBox(10, exitButton);
        pane.setPadding(new Insets(8, 30, 1, 300));
        return pane;
    }

    private HBox searchPane() {
        searchButton = new Button("");
        searchButton.setStyle("-fx-background-color: transparent;");
        searchButton.setPrefWidth(400);
        searchButton.setPrefHeight(123);
        searchButton.setOnAction(e -> searchButtonClick());

        HBox pane = new HBox(10, searchButton);
        pane.setPadding(new Insets(2, 0, 10, 0));
        return pane;
    }

    private HBox removePane() {
        removeButton = new Button("");
        removeButton.setStyle("-fx-background-color: transparent;");
        removeButton.setPrefWidth(400);
        removeButton.setPrefHeight(123);

        removeButton.setOnAction(e -> removeButtonClick());

        HBox pane = new HBox(10, removeButton);
        pane.setPadding(new Insets(5, 0, 5, 0));
        return pane;
    }

    private HBox viewPane() {
        viewButton = new Button("");
        viewButton.setStyle("-fx-background-color: transparent;");
        viewButton.setPrefWidth(400);
        viewButton.setPrefHeight(123);

        viewButton.setOnAction(e -> viewButtonClick());

        HBox pane = new HBox(10, viewButton);
        pane.setPadding(new Insets(405, 0, 5, 0));
        return pane;
    }

    private void exitButtonClick() {
        updateRecords(info);
        System.exit(0);
    }

    private void searchButtonClick() {
        Image image = new Image("file:background_search.jpg");
        ImageView mv = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(mv, searchGrid());

        searchMenu = new Scene(root, 400, 855);
        stage.setScene(searchMenu);
    }

    private GridPane searchGrid() {
        GridPane grid = new GridPane();
        grid.add(searchDisplayPane(), 0, 0, 2,1);
        grid.add(searchTypePane(), 1, 1, 1, 1);
        grid.add(searchEnterPane(), 1, 2, 1, 1);
        grid.add(searchBackPane(), 1, 3, 1, 1);
        return grid;
    }

    private HBox searchDisplayPane() {

        Text text = new Text(createSearchDisplayText(searchInput));
        HBox root = new HBox();
        root.setPrefHeight(600);
        root.setPrefWidth(350);
        root.getChildren().addAll(text);
        root.setPadding(new Insets(150, 75, 230, 60));
        return root;
    }

    private String createSearchDisplayText(String searchInput) {
        int count = 0;
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < info.getSize(); i++) {
            Booking b = info.getBookingInfo(i);

            if (b.getContact().equals(searchInput)) {

                String s = Integer.toString(count += 1) + ":" + "\n" + b.getContact() + "\n" + b.getDescription() + "\n"
                        + b.getLocation() + "\n" + b.getTime() + "\n" + b.getPhone() + "\n" + b.getPrice() + "\n"
                        + b.getActive() + "\n\n";

                string.append(s).toString();
                return string.toString();
            }
        }
        return string.toString();
    }

    private HBox searchTypePane() {
        name = new TextField();
        name.setPrefColumnCount(1);
        name.setPrefHeight(40);
        name.setPrefWidth(200);

        HBox root = new HBox();
        root.setPrefHeight(40);
        root.setPrefWidth(200);
        root.getChildren().addAll(name);
        root.setPadding(new Insets(65, 0, 10, 150));
        return root;
    }

    private HBox searchEnterPane() {
        enterButton = new Button("");
        enterButton.setStyle("-fx-background-color: transparent;");
        enterButton.setPrefWidth(100);
        enterButton.setPrefHeight(40);

        enterButton.setOnAction(e -> enterButtonClick());

        HBox pane = new HBox(10, enterButton);
        pane.setPadding(new Insets(21, 0, 30, 150));
        return pane;
    }

    private void enterButtonClick() {
        searchInput = name.getText();
        backButtonClick();
        searchButtonClick();
    }

    private HBox searchBackPane() {
        backButton = new Button("");
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setPrefWidth(95);
        backButton.setPrefHeight(15);
        backButton.setOnAction(e -> backButtonClick());

        HBox pane = new HBox(10, backButton);
        pane.setPadding(new Insets(0, 30, 1, 295));
        return pane;
    }

    private void backButtonClick() {
        stage.setScene(menu);
    }

    private void removeButtonClick() {
        Image image = new Image("file:background_remove.jpg");
        ImageView mv = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(mv, removeGrid());

        removeMenu = new Scene(root, 400, 855);
        stage.setScene(removeMenu);
    }

    private GridPane removeGrid() {
        GridPane grid = new GridPane();
        grid.add(removeInputPane(), 0, 0, 1,1);
        grid.add(removeEnterPane(), 0, 1, 1, 1);
        return grid;
    }

    private HBox removeInputPane() {
        removeName = new TextField();
        removeName.setPrefHeight(60);
        removeName.setPrefWidth(300);

        HBox root = new HBox();
        root.setPrefHeight(60);
        root.setPrefWidth(300);
        root.getChildren().addAll(removeName);
        root.setPadding(new Insets(680, 0, 30, 150));
        return root;
    }

    private HBox removeEnterPane() {
        removeConfirmButton = new Button("");
        removeConfirmButton.setStyle("-fx-background-color: transparent;");
        removeConfirmButton.setPrefWidth(150);
        removeConfirmButton.setPrefHeight(30);

        removeConfirmButton.setOnAction(e -> removeConfirmButtonClick());

        HBox pane = new HBox(10, removeConfirmButton);
        pane.setPadding(new Insets(21, 0, 30, 120));
        return pane;
    }

    private void removeConfirmButtonClick() {
        String name = removeName.getText();
        try {
            info.removeBookingInfo(name);
        } catch (DoesNotContain e) {
            // Nothing happens
        } catch (ContainsMoreThanOne e) {
            // Reduced Functionality in GUI - Assumes there are not multiple bookings with same name
        } catch (ConcurrentModificationException e) {
            // Nothing happens
        } finally {
            backButtonClick();
        }
    }

    private void viewButtonClick() {
        Image image = new Image("file:background_view.jpg");
        ImageView mv = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(mv, viewGridTop(), viewGridBottomLeft(), viewGridBottomRight());

        viewMenu = new Scene(root, 400, 855);
        stage.setScene(viewMenu);
    }

    private GridPane viewGridTop() {
        GridPane grid = new GridPane();
        grid.add(viewAddPane(), 0, 0, 1,1);
        return grid;
    }

    private GridPane viewGridBottomLeft() {
        GridPane grid = new GridPane();
        grid.add(viewDisplayPane(), 0, 0, 2, 1);
        return grid;
    }

    private GridPane viewGridBottomRight() {
        GridPane grid = new GridPane();
        grid.add(viewBackPane(), 1, 0, 1, 1);
        return grid;
    }

    private HBox viewBackPane() {
        viewBackButton = new Button("");
        viewBackButton.setStyle("-fx-background-color: transparent;");
        viewBackButton.setPrefWidth(100);
        viewBackButton.setPrefHeight(15);
        viewBackButton.setOnAction(e -> backButtonClick());

        HBox pane = new HBox(10, viewBackButton);
        pane.setPadding(new Insets(795, 20, 0, 295));
        return pane;
    }

    private HBox viewDisplayPane() {
        Text text = new Text(createViewDisplayText());
        text.setStyle("-fx-font-size: 9px");
        HBox root = new HBox();
        root.setPrefHeight(550);
        root.setPrefWidth(350);
        root.getChildren().addAll(text);
        root.setPadding(new Insets(110, 75, 230, 60));
        return root;
    }

    private String createViewDisplayText() {
        int count = 0;
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < info.getSize(); i++) {
            Booking b = info.getBookingInfo(i);

            String s = Integer.toString(count += 1) + ":" + "\n" + b.getContact() + "\n" + b.getDescription() + "\n"
                        + b.getLocation() + "\n" + b.getTime() + "\n" + b.getPhone() + "\n" + b.getPrice() + "\n"
                        + b.getActive() + "\n\n";

            string.append(s).toString();
        }
        return string.toString();
    }

    private HBox viewAddPane() {
        viewAddButton = new Button("");
        viewAddButton.setStyle("-fx-background-color: transparent;");
        viewAddButton.setPrefWidth(250);
        viewAddButton.setPrefHeight(40);

        viewAddButton.setOnAction(e -> addButtonClick());

        HBox pane = new HBox(10, viewAddButton);
        pane.setPadding(new Insets(790, 0, 0, 10));
        return pane;
    }

//    TextArea profile = new TextArea(getProfile());
//        profile.setPrefSize(400, 400);
//
//    HBox pane = new HBox(profile);
//        pane.setPadding(new Insets(400, 30, 5, 50));
//        pane.setSpacing(30);
//        return pane;

    private void addButtonClick() {
        Image image = new Image("file:background_add.jpg");
        ImageView mv = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(mv, addInfoPane());

        viewAddMenu = new Scene(root, 400, 855);
        stage.setScene(viewAddMenu);
        System.out.println("button pushed");
    }

    private GridPane addInfoPane() {
        GridPane grid = new GridPane();
        grid.add(contactPane(), 0, 0, 1,1);
        grid.add(descriptionPane(), 0, 1, 1,1);
        grid.add(timePane(), 0, 2, 1,1);
        grid.add(locationPane(), 0, 3, 1,1);
        grid.add(phonePane(), 0, 4, 1,1);
        grid.add(pricePane(), 0, 5, 1,1);
        grid.add(addConfirmPane(), 0, 6, 1,1);
        return grid;
    }

    private HBox contactPane() {
        contact = new TextField();
        contact.setPrefHeight(40);
        contact.setPrefWidth(150);

        HBox root = new HBox();
        root.setPrefHeight(40);
        root.setPrefWidth(150);
        root.getChildren().addAll(contact);
        root.setPadding(new Insets(400, 0, 0, 150));
        return root;
    }

    private HBox descriptionPane() {
        description = new TextField();
        description.setPrefHeight(40);
        description.setPrefWidth(150);

        HBox root = new HBox();
        root.setPrefHeight(40);
        root.setPrefWidth(150);
        root.getChildren().addAll(description);
        root.setPadding(new Insets(32, 0, 0, 150));
        return root;
    }

    private HBox timePane() {
        time = new TextField();
        time.setPrefHeight(40);
        time.setPrefWidth(150);

        HBox root = new HBox();
        root.setPrefHeight(40);
        root.setPrefWidth(150);
        root.getChildren().addAll(time);
        root.setPadding(new Insets(33, 0, 0, 150));
        return root;
    }

    private HBox locationPane() {
        location = new TextField();
        location.setPrefHeight(40);
        location.setPrefWidth(150);

        HBox root = new HBox();
        root.setPrefHeight(40);
        root.setPrefWidth(150);
        root.getChildren().addAll(location);
        root.setPadding(new Insets(34, 0, 0, 150));
        return root;
    }

    private HBox phonePane() {
        phone = new TextField();
        phone.setPrefHeight(40);
        phone.setPrefWidth(150);

        HBox root = new HBox();
        root.setPrefHeight(40);
        root.setPrefWidth(150);
        root.getChildren().addAll(phone);
        root.setPadding(new Insets(34, 0, 0, 150));
        return root;
    }

    private HBox pricePane() {
        price = new TextField();
        price.setPrefHeight(40);
        price.setPrefWidth(150);

        HBox root = new HBox();
        root.setPrefHeight(40);
        root.setPrefWidth(150);
        root.getChildren().addAll(price);
        root.setPadding(new Insets(35, 0, 0, 150));
        return root;
    }

    private HBox addConfirmPane() {
        addConfirmButton = new Button("");
        addConfirmButton.setStyle("-fx-background-color: transparent;");
        addConfirmButton.setPrefWidth(135);
        addConfirmButton.setPrefHeight(35);

        addConfirmButton.setOnAction(e -> addConfirmButtonClick());

        HBox pane = new HBox(10, addConfirmButton);
        pane.setPadding(new Insets(39, 0, 0, 135));
        return pane;
    }

    private void addConfirmButtonClick() {
        Booking bi = new ActiveBooking(contact.getText(), description.getText(), time.getText(), location.getText(),
                phone.getText(), price.getText(), "Active");
        info.addBookingInfo(bi);
        backButtonClick();
    }

    //////// OPERATION METHODS ////////

    public static void setOriginalCount(ListOfBookingInfo info) {
        originalCount = info.getSize();
    }

    public static void menu(ListOfBookingInfo info) {
        String operation;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWhat would you like to do? \n V = view or add to your bookings \n R = remove a booking"
                + " \n E = update records and end program \n H = view booking with hash");
        operation = scanner.nextLine();

        if (operation.equals("V")) {
            displayBookings(info);
        } else if (operation.equals("R")) {
            removeBooking(info);
        } else if (operation.equals("E")) {
            updateRecords(info);
            exit(0);
        } else if (operation.equals("H")) {
            displayViaHash(info);
        } else {
            System.out.println("You did not input V, R, E, or H");
            menu(info);
        }
    }

    public static void removeBooking(ListOfBookingInfo info) {
        String name;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the contact name of the booking you would like to remove?");
        name = scanner.nextLine();

        try {
            info.removeBookingInfo(name);
            menu(info);
        } catch (ContainsMoreThanOne e) {
            System.out.println("There is more than one booking with that contact name.\n");
            removeWhichOne(info, name);
        } catch (DoesNotContain e) {
            menu(info);
        } catch (ConcurrentModificationException e) {
            System.out.println("Concurrent Mod. The booking has been removed.");
            menu(info);
        } finally {
            System.out.println("Finally the booking has been removed.");
        }
    }

    public static void removeWhichOne(ListOfBookingInfo info, String name) {
        int operation;
        Scanner scanner = new Scanner(System.in);

        ListOfBookingInfo multipleList = info.returnMultiple(name);
        System.out.println("Here are the similar bookings: ");

        for (int i = 0; i < multipleList.getSize(); i++) {
            System.out.println(Integer.toString(i) + ":");
            Booking b = multipleList.getBookingInfo(i);
            printInfo(b);
        }

        System.out.println("Which one do you want to remove? (input number)");
        operation = Integer.parseInt(scanner.nextLine());

        info.removeMultiple(multipleList.getBookingInfo(operation));
        System.out.println("\nBooking has been removed.");
        menu(info);
    }

    // Scanner code taken from B04-LittleCalculatorSStarterLecLab
    public static void inputNewBooking(ListOfBookingInfo info) {
        String operation = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWould you like to add a Booking? (Y = yes, N = no)");
        operation = scanner.nextLine();

        if (operation.equals("Y")) {
            inputNewBookingY(info);
        } else if (operation.equals("N")) {
            menu(info);
        } else {
            System.out.println("You did not input Y or N");
            inputNewBooking(info);
        }
    }

    public static void inputNewBookingY(ListOfBookingInfo info) {

        System.out.println("Please input your booking: \n");
        inputActive(info);
        menu(info);
    }

    public static void inputActive(ListOfBookingInfo info) {
        try {
            Scanner writer = new Scanner(System.in);
            FileWriter data = new FileWriter("data.txt", true);
            BufferedWriter buffWr = new BufferedWriter(data);
            String contact = "";
            String description = "";
            String time = "";
            String location = "";
            String phone = "";
            String price = "";

            inputHelperA(info, contact, description, time, location, phone, price, writer, data, buffWr);

            buffWr.close();
            data.close();

        } catch (Exception e) {
            System.out.println("A problem occurred while this program was running");
        }
    }

    public static String inputHelperB(Scanner writer, FileWriter data, BufferedWriter buffWr,
                                      String line, String info) {
        String lineOfText;

        try {
            System.out.println(line);
            lineOfText = writer.nextLine();
            info = lineOfText;
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();
        } catch (FileNotFoundException e) {
            System.out.println("The date file data.txt was not found or could not be opened.");
        } catch (Exception e) {
            System.out.println("A problem occurred while this program was running");
        }
        return info;
    }

    public static void inputHelperA(ListOfBookingInfo info, String contact, String description, String time,
                                    String location, String phone, String price, Scanner writer, FileWriter data,
                                    BufferedWriter buffWr) {

        contact = inputHelperB(writer, data, buffWr,"Contact Info: ", contact);

        description = inputHelperB(writer, data, buffWr, "Description: ", description);

        time = inputHelperB(writer, data, buffWr, "Time: ", time);

        location = inputHelperB(writer, data, buffWr, "Location: ", location);

        phone = inputHelperB(writer, data, buffWr, "Phone: ", phone);

        price = inputHelperB(writer, data, buffWr, "Price: ", price);

        try {
            buffWr.write("Active", 0, "Active".length());
            buffWr.newLine();
        } catch (Exception e) {
            //
        }

        Booking ab = new ActiveBooking(contact, description, time, location, phone, price, "Active");
        info.addBookingInfo(ab);
    }

    public static void listOfBookingInfoInstance(ListOfBookingInfo info) {

        try {
            FileReader data = new FileReader("data.txt");
            BufferedReader buff = new BufferedReader(data);

            listOfBookingInfoInstanceHelperA(info, buff);

            buff.close();
            data.close();

        } catch (FileNotFoundException e) {
            System.out.println("The date file data.txt was not found or could not be opened.");
        } catch (Exception e) {
            System.out.println("A problem occurred while this program was running");
        }
    }

    public static void listOfBookingInfoInstanceHelperA(ListOfBookingInfo info, BufferedReader buff) {
        try {
            String lineOfData = null;
            lineOfData = buff.readLine();

            while (lineOfData != null) {

                String contact = lineOfData;
                String description = listOfBookingInfoInstanceHelperB(lineOfData, buff);
                String time = listOfBookingInfoInstanceHelperB(lineOfData, buff);
                String location = listOfBookingInfoInstanceHelperB(lineOfData, buff);
                String phone = listOfBookingInfoInstanceHelperB(lineOfData, buff);
                String price = listOfBookingInfoInstanceHelperB(lineOfData, buff);
                String active = listOfBookingInfoInstanceHelperB(lineOfData, buff);
                lineOfData = buff.readLine();

                Booking ab = new ActiveBooking(contact, description, time, location, phone, price, active);
                info.addBookingInfo(ab);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong in listOfBookingInfoInstanceHelperA");
        }
    }

    public static String listOfBookingInfoInstanceHelperB(String line, BufferedReader buff) {
        try {
            line = buff.readLine();
        } catch (Exception e) {
            System.out.println("Something went wrong in listOfBookingInfoInstanceHelperB");
        }
        return line;
    }


    public static void updateRecords(ListOfBookingInfo info) {
        try {
            FileWriter data = new FileWriter("data.txt", false);
            BufferedWriter buffWr = new BufferedWriter(data);

            updateRecordsHelperA(info, buffWr);

            buffWr.close();
            data.close();

        } catch (FileNotFoundException e) {
            System.out.println("The date file data.txt was not found or could not be opened.");
        } catch (IOException e) {
            System.out.println("A problem occurred while this program was running");
        }
    }

    public static void updateRecordsHelperA(ListOfBookingInfo info, BufferedWriter buffWr) {
        try {
            for (int i = 0; i < info.getSize(); i++) {

                updateRecordsHelperB(info.getBookingInfo(i).getContact(), buffWr);
                updateRecordsHelperB(info.getBookingInfo(i).getDescription(), buffWr);
                updateRecordsHelperB(info.getBookingInfo(i).getTime(), buffWr);
                updateRecordsHelperB(info.getBookingInfo(i).getLocation(), buffWr);
                updateRecordsHelperB(info.getBookingInfo(i).getPhone(), buffWr);
                updateRecordsHelperB(info.getBookingInfo(i).getPrice(), buffWr);
                updateRecordsHelperB(info.getBookingInfo(i).getActive(), buffWr);

            }
        } catch (Exception e) {
            System.out.println("Something went wrong in updateRecordsHelperA");
        }
        System.out.println("You've added " + (info.getObservedCount() - originalCount) + " bookings this session.");
    }

    public static void updateRecordsHelperB(String s, BufferedWriter buffWr) {
        try {
            buffWr.write(s);
            buffWr.newLine();
        } catch (Exception e) {
            System.out.println("Something went wrong in updateRecordHelperB");
        }

    }
}
