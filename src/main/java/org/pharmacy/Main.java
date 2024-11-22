package org.pharmacy;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class Main extends Application {
    private final MedicineDAOimpl medicineController = new MedicineDAOimpl();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System");
        primaryStage.setFullScreen(true); // Set to full screen

        // Create buttons
        Button addMedicineButton = new Button("Add Medicine");
        Button updateMedicineQuantityButton = new Button("Update Medicine Quantity");
        Button getAllMedicineButton = new Button("Get All Medicines");
        Button deleteMedicineButton = new Button("Delete Medicine");
        Button updateMedicineButton = new Button("Update Medicine");
        Button searchMedicineButton = new Button("Search Medicine");
        Button getExpiredMedicineButton = new Button("Get Expired Medicines");

        // Set button actions
        addMedicineButton.setOnAction(e -> openAddMedicineWindow());
        updateMedicineQuantityButton.setOnAction(e -> openUpdateMedicineQuantityWindow());
        getAllMedicineButton.setOnAction(e -> openGetAllMedicinesWindow());
        deleteMedicineButton.setOnAction(e -> openDeleteMedicineWindow());
        updateMedicineButton.setOnAction(e -> openUpdateMedicineWindow());
        searchMedicineButton.setOnAction(e -> openSearchMedicineWindow());
        getExpiredMedicineButton.setOnAction(e -> openGetExpiredMedicinesWindow());

        // Create layout
        VBox layout = new VBox();
        layout.getStyleClass().add("vbox"); // Add CSS class
        layout.getChildren().addAll(addMedicineButton, updateMedicineQuantityButton, getAllMedicineButton,
                deleteMedicineButton, updateMedicineButton, searchMedicineButton, getExpiredMedicineButton);

        // Create scene and apply CSS
        Scene scene = new Scene(layout, 1200, 800);
        //scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm()); // Load CSS
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void openAddMedicineWindow() {
        Stage addMedicineStage = new Stage();

        VBox layout = new VBox(10);

        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        TextField expiryDateField = new TextField();
        expiryDateField.setPromptText("Expiry Date (YYYY-MM-DD)");

        Button addButton = new Button("Add Medicine");
        addButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String expiryDate = expiryDateField.getText();
            medicineController.addMedicine(id, name, price, quantity, expiryDate);
            addMedicineStage.close();
        });

        layout.getChildren().addAll(idField, nameField, priceField, quantityField, expiryDateField, addButton);
        Scene scene = new Scene(layout, 1200, 800);
        addMedicineStage.setScene(scene);
        addMedicineStage.setFullScreen(true);
        addMedicineStage.setTitle("Add Medicine");
        addMedicineStage.show();
    }

    private void openUpdateMedicineQuantityWindow() {
        Stage updateQuantityStage = new Stage();
        VBox layout = new VBox(10);

        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField quantityField = new TextField();
        quantityField.setPromptText("New Quantity");

        Button updateButton = new Button("Update Quantity");
        updateButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            medicineController.updateMedicineQuantity(id, quantity);
            updateQuantityStage.close();
        });

        layout.getChildren().addAll(idField, quantityField, updateButton);
        Scene scene = new Scene(layout, 1200, 800);
        updateQuantityStage.setScene(scene);
        updateQuantityStage.setFullScreen(true);
        updateQuantityStage.setTitle("Update Medicine Quantity");
        updateQuantityStage.show();
    }

    private void openGetAllMedicinesWindow() {
        Stage getAllMedicinesStage = new Stage();
        VBox layout = new VBox(10);

        List<Medicine> medicines = medicineController.getAllMedicines();
        ListView<Medicine> listView = new ListView<>();
        ObservableList<Medicine> observableMedicines = FXCollections.observableArrayList(medicines);
        listView.setItems(observableMedicines);

        layout.getChildren().add(listView);
        Scene scene = new Scene(layout, 1200, 800);
        getAllMedicinesStage.setScene(scene);
        getAllMedicinesStage.setFullScreen(true);
        getAllMedicinesStage.setTitle("All Medicines");
        getAllMedicinesStage.show();
    }

    private void openDeleteMedicineWindow() {
        Stage deleteMedicineStage = new Stage();
        VBox layout = new VBox(10);

        TextField idField = new TextField();
        idField.setPromptText("ID");

        Button deleteButton = new Button("Delete Medicine");
        deleteButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            medicineController.deleteMedicine(id);
            deleteMedicineStage.close();
        });

        layout.getChildren().addAll(idField, deleteButton);
        Scene scene = new Scene(layout, 1200, 800);
        deleteMedicineStage.setScene(scene);
        deleteMedicineStage.setFullScreen(true);
        deleteMedicineStage.setTitle("Delete Medicine");
        deleteMedicineStage.show();
    }

    private void openUpdateMedicineWindow() {
        Stage updateMedicineStage = new Stage();
        VBox layout = new VBox(10);

        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField nameField = new TextField();
        nameField.setPromptText("New Name");
        TextField priceField = new TextField();
        priceField.setPromptText("New Price");
        TextField quantityField = new TextField();
        quantityField.setPromptText("New Quantity");
        TextField expiryDateField = new TextField();
        expiryDateField.setPromptText("New Expiry Date (YYYY-MM-DD)");

        Button updateButton = new Button("Update Medicine");
        updateButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String expiryDate = expiryDateField.getText();
            medicineController.updateMedicine(id, name, price, quantity, expiryDate);
            updateMedicineStage.close();
        });

        layout.getChildren().addAll(idField, nameField, priceField, quantityField, expiryDateField, updateButton);
        Scene scene = new Scene(layout, 1200, 800);
        updateMedicineStage.setScene(scene);
        updateMedicineStage.setFullScreen(true);
        updateMedicineStage.setTitle("Update Medicine");
        updateMedicineStage.show();
    }

    private void openSearchMedicineWindow() {
        Stage searchMedicineStage = new Stage();
        VBox layout = new VBox(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Medicine Name");

        Button searchButton = new Button("Search Medicine");
        ListView<Medicine> listView = new ListView<>();
        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            List<Medicine> medicines = null;
            try {
                medicines = medicineController.searchMedicineByName(name);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ObservableList<Medicine> observableMedicines = FXCollections.observableArrayList(medicines);
            listView.setItems(observableMedicines);
        });

        layout.getChildren().addAll(nameField, searchButton, listView);
        Scene scene = new Scene(layout, 1200, 800);
        searchMedicineStage.setScene(scene);
        searchMedicineStage.setFullScreen(true);
        searchMedicineStage.setTitle("Search Medicine");
        searchMedicineStage.show();
    }

    private void openGetExpiredMedicinesWindow() {
        Stage getExpiredMedicinesStage = new Stage();
        VBox layout = new VBox(10);

        List<Medicine> medicines = medicineController.getExpiredMedicine();
        ListView<Medicine> listView = new ListView<>();
        ObservableList<Medicine> observableMedicines = FXCollections.observableArrayList(medicines);
        listView.setItems(observableMedicines);

        layout.getChildren().add(listView);
        Scene scene = new Scene(layout, 1200, 800);
        getExpiredMedicinesStage.setScene(scene);
        getExpiredMedicinesStage.setFullScreen(true);
        getExpiredMedicinesStage.setTitle("Expired Medicines");
        getExpiredMedicinesStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



















































/*package org.pharmacy;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main extends Application {
    private final MedicineDAOimpl medicineDAO = new MedicineDAOimpl();
    private final TableView<Medicine> table = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System");
        primaryStage.setMaximized(true);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        // UI Components
        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField priceField = new TextField();
        TextField quantityField = new TextField();
        TextField expiryField = new TextField();
        Button addButton = new Button("Add Medicine");
        Button updateButton = new Button("Update Medicine");
        Button deleteButton = new Button("Delete Medicine");
        Button searchButton = new Button("Search Medicine");

        // Table Columns
        TableColumn<Medicine, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Medicine, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Medicine, Double> priceColumn = new TableColumn<>("Price");
        TableColumn<Medicine, Integer> quantityColumn = new TableColumn<>("Quantity");
        TableColumn<Medicine, LocalDate> expiryColumn = new TableColumn<>("Expiry Date");

        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        priceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        quantityColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        expiryColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getExpiry_date()));

        table.getColumns().addAll(idColumn, nameColumn, priceColumn, quantityColumn, expiryColumn);
        table.setItems(FXCollections.observableArrayList(medicineDAO.getAllMedicines()));

        // Layout
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Price:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Quantity:"), 0, 3);
        grid.add(quantityField, 1, 3);
        grid.add(new Label("Expiry Date:"), 0, 4);
        grid.add(expiryField, 1, 4);
        grid.add(addButton, 0, 5);
        grid.add(updateButton, 1, 5);
        grid.add(deleteButton, 0, 6);
        grid.add(searchButton, 1, 6);
        grid.add(table, 0, 7, 2, 1);

        // Button Actions
        addButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            LocalDate expiryDate = LocalDate.parse(expiryField.getText());
            medicineDAO.addMedicine(id, name, price, quantity, String.valueOf(expiryDate));
            refreshTable();
        });

        updateButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            LocalDate expiry_date = LocalDate.parse(expiryField.getText());
            medicineDAO.updateMedicine(id, name, price , quantity, String.valueOf(expiry_date));
            refreshTable();
        });

        deleteButton.setOnAction(e -> {
            int id = Integer.parseInt(idField.getText());
            medicineDAO.deleteMedicine(id);
            refreshTable();
        });

        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            List<Medicine> results = null;
            try {
                results = medicineDAO.searchMedicineByName(name);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            table.setItems(FXCollections.observableArrayList(results));
        });



        Scene scene = new Scene(grid, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Add this line to include the CSS
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(medicineDAO.getAllMedicines()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/




















/*package org.pharmacy;
import java.sql.SQLException;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private MedicineDAOimpl medicineDAO = new MedicineDAOimpl();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System");

        // Main Layout
        VBox mainLayout = new VBox();
        mainLayout.setPadding(new Insets(15));
        mainLayout.setSpacing(10);

        // Title Label
        Label titleLabel = new Label("Pharmacy Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Buttons
        Button addMedicineBtn = new Button("Add Medicine");
        Button viewMedicinesBtn = new Button("View All Medicines");
        Button searchMedicineBtn = new Button("Search Medicines");
        Button expiredMedicineBtn = new Button("Check Expired Medicines");

        // Button Actions
        addMedicineBtn.setOnAction(e -> openAddMedicineDialog());
        viewMedicinesBtn.setOnAction(e -> viewAllMedicines());
        searchMedicineBtn.setOnAction(e -> openSearchMedicineDialog());
        expiredMedicineBtn.setOnAction(e -> checkExpiredMedicines());

        // Adding components to layout
        mainLayout.getChildren().addAll(titleLabel, addMedicineBtn, viewMedicinesBtn, searchMedicineBtn, expiredMedicineBtn);

        // Creating the scene
        Scene scene = new Scene(mainLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openAddMedicineDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Add Medicine");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Creating form fields
        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField priceField = new TextField();
        TextField quantityField = new TextField();
        DatePicker expiryDatePicker = new DatePicker();

        // Labels
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Price:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Quantity:"), 0, 3);
        grid.add(quantityField, 1, 3);
        grid.add(new Label("Expiry Date:"), 0, 4);
        grid.add(expiryDatePicker, 1, 4);

        // Add button
        Button addButton = new Button("Add Medicine");
        grid.add(addButton, 1, 5);

        addButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                String expiryDate = expiryDatePicker.getValue().toString();

                // Adding medicine using DAO
                medicineDAO.addMedicine(id, name, price, quantity, expiryDate);
                dialog.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Invalid input", "Please enter valid details.");
            }
        });

        Scene scene = new Scene(grid, 400, 300);
        dialog.setScene(scene);
        dialog.show();
    }

    private void viewAllMedicines() {
        List<Medicine> medicines = medicineDAO.getAllMedicines();
        StringBuilder output = new StringBuilder();
        for (Medicine med : medicines) {
            output.append(med).append("\n");
        }
        showAlert(Alert.AlertType.INFORMATION, "All Medicines", output.toString());
    }

    private void openSearchMedicineDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Search Medicines");

        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(10);

        TextField searchField = new TextField();
        searchField.setPromptText("Enter medicine name");
        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            String name = searchField.getText();
            List<Medicine> results = null;
            try {
                results = medicineDAO.searchMedicineByName(name);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            StringBuilder output = new StringBuilder();
            for (Medicine med : results) {
                output.append(med).append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "Search Results", output.toString());
            dialog.close();
        });

        layout.getChildren().addAll(searchField, searchButton);
        Scene scene = new Scene(layout, 300, 200);
        dialog.setScene(scene);
        dialog.show();
    }

    private void checkExpiredMedicines() {
        List<Medicine> expiredMedicines = medicineDAO.getExpiredMedicine();
        StringBuilder output = new StringBuilder();
        for (Medicine med : expiredMedicines) {
            output.append(med).append("\n");
        }
        showAlert(Alert.AlertType.INFORMATION, "Expired Medicines", output.toString());
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/













































/*package org.pharmacy;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        MedicineDAOimpl medicineDAO=new MedicineDAOimpl();

//        medicineDAO.addMedicine(2,"Ibuprofen", 15.99, 200, "2025-06-30");
//        medicineDAO.addMedicine(3,"pc", 15.99, 200, "2025-06-30");
//        medicineDAO.addMedicine(4,"dolo", 15.99, 200, "2025-06-30");
//        medicineDAO.addMedicine(5,"vicks", 15.99, 200, "2025-06-30");
//        //List<Medicine> med = medicineDAO.getAllMedicines();
//       // med.forEach(m-> System.out.println(m.getName()+" - "+m.getPrice()));
//
//        medicineDAO.updateMedicine(2, "Ibuprofen", 12.99, 180, "2026-01-01");
//
//        medicineDAO.deleteMedicine(2);
//
//        List<Medicine> med = medicineDAO.getAllMedicines();
//        System.out.println(med);
//
//        Scanner sc=new Scanner(System.in);
//        System.out.println("do you want to delete all records if enter yes: ");
//        String todelete=sc.nextLine();
//        if(todelete.equals("yes")){
//            medicineDAO.refreshTable();
//        }

        Scanner sc=new Scanner(System.in);

        while(true){
            System.out.println("\nPharmacy Management System");
            System.out.println("1. Add Medicine");
            System.out.println("2. View All Medicines");
            System.out.println("3. Update Medicine");
            System.out.println("4. Delete Medicine");
            System.out.println("5. Refresh Table");
            System.out.println("6. Search Medicine");
            System.out.println("7. Update Quntity");
            System.out.println("8. Check Expired Medicine ");
            System.out.println("9. Check Quantity by name");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice=sc.nextInt();

            switch(choice){
                case 1:
                    while(true) {
                        System.out.println("Enter Medicine Id: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter Medicine name");
                        String name = sc.nextLine();
                        System.out.println("Enter price: ");
                        double price = sc.nextDouble();
                        System.out.println("Enter Quantity: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter Expiry Date(YYYY-MM-DD): ");
                        String expiry_date = sc.nextLine();

                        medicineDAO.addMedicine(id, name, price, quantity, expiry_date);
                        System.out.println("Enter yes or no to add more: ");
                        String cont=sc.nextLine();
                        if(cont.equalsIgnoreCase("no")){
                            System.out.println("quitting");
                            break;
                        }

                    }


                case 2:
                    List<Medicine> medicine = medicineDAO.getAllMedicines();
                    System.out.println("\n Medicine List");
                    System.out.printf("%-5s %-15s %-8s %-10s %-12s\n", "ID", "Name", "Price", "Quantity", "Expiry Date");
                    System.out.println("-------------------------------------------------------");
                    for (Medicine med : medicine) {
                        System.out.printf("%-5d %-15s %-8.2f %-10d %-12s\n",
                                med.getId(),
                                med.getName(),
                                med.getPrice(),
                                med.getQuantity(),
                                med.getExpiry_date());
                    }
                    break;

                case 3:
                    System.out.println("ENter the updated Medicine Id");
                    int uid=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter updated Medicine name");
                    String uname=sc.nextLine();
                    System.out.println("Enter updated price: ");
                    double uprice=sc.nextDouble();
                    System.out.println("Enter updated Quantity: ");
                    int uquantity=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter updated Expiry Date(YYYY-MM-DD): ");
                    String uexpiry_date=sc.nextLine();

                    medicineDAO.updateMedicine(uid,uname,uprice,uquantity,uexpiry_date);
                    break;

                case 4:
                    System.out.println("Enter the medicine Id to delete: ");
                    int delete_id=sc.nextInt();

                    medicineDAO.deleteMedicine(delete_id);
                    break;

                case 5:
                    System.out.println("Do you want to delete all records!!!! : Type yes or no");
                    String s=sc.nextLine();
                    if(s.equals("yes")){
                        medicineDAO.refreshTable();
                    }
                    break;
                case 6:
                    System.out.println("Enter the medicine name you want to search!!");
                    sc.nextLine();
                    String medName=sc.nextLine();

                    List<Medicine> searchNameResults=medicineDAO.searchMedicineByName(medName);
                    for (Medicine med : searchNameResults) {
                        System.out.printf("%-5d %-15s %-8.2f %-10d %-12s\n",
                                med.getId(),
                                med.getName(),
                                med.getPrice(),
                                med.getQuantity(),
                                med.getExpiry_date());
                    }

                    break;

                case 8:
                    System.out.println("Expire medicines!!");

                    List<Medicine> expiredMedList = medicineDAO.getExpiredMedicine();
                    for (Medicine med : expiredMedList) {
                        System.out.printf("%-5d %-15s %-8.2f %-10d %-12s\n",
                                med.getId(),
                                med.getName(),
                                med.getPrice(),
                                med.getQuantity(),
                                med.getExpiry_date());
                    }

                case 7:
                    System.out.println("Enter the id and new quantity");
                    int id=sc.nextInt();
                    int quantity=sc.nextInt();
                    medicineDAO.updateMedicineQuantity(id,quantity);
                    break;
                case 9:
                    System.out.println("Enter the med name to check quantity");
                    sc.nextLine();
                    String name=sc.nextLine();
                    List <Medicine> medQuantity=medicineDAO.checkQuantity(name);
                    for (Medicine med : medQuantity) {
                        System.out.printf("%-5d %-15s %-10d\n",
                                med.getId(),
                                med.getName(),
                                med.getQuantity()
                        );
                    }

                case 10:
                    System.out.println("Exiting!!");
                    break;


                default:
                    System.out.println("invaliid choice");





            }



        }

    }
}*/
