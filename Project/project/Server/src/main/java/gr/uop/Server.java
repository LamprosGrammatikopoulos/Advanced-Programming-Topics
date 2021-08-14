package gr.uop;


import javafx.scene.input.KeyCode;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Server extends Application {

    private TableView<data> table = new TableView();
    private Button pay, cancel;



    @Override
    public void start(Stage stage) {

        //--------------------Receive data from client-------------------------
        final Label label = new Label("Πίνακας αυτοκινήτων");
        label.setFont(new Font("Arial", 30));

        //Create each table column
        TableColumn arrivalDateTime = new TableColumn("Ημερομηνία-Ώρα άφιξης");
        arrivalDateTime.setMinWidth(450);
        arrivalDateTime.setCellValueFactory(new PropertyValueFactory<data, String>("arrivalDateTime"));

        TableColumn registrationNumber = new TableColumn("Αριθμός κυκλοφορίας");
        registrationNumber.setMinWidth(300);
        registrationNumber.setCellValueFactory(new PropertyValueFactory<data, String>("registrationNumber"));

        TableColumn cost = new TableColumn("Kόστος");
        cost.setMinWidth(100);
        cost.setCellValueFactory(new PropertyValueFactory<data, String>("cost"));

        TableColumn value = new TableColumn("Υπηρεσία/τιμή");
        value.setMinWidth(400);
        value.setCellValueFactory(new PropertyValueFactory<data, String>("value"));


        //Fill the table columns
        table.setItems(EchoServerThread.returnList());
        table.getColumns().addAll(arrivalDateTime, registrationNumber, cost, value);
        

        //Buttons for each action
        Button pay = new Button("Πληρωμή");
        pay.setDisable(true);

        Button cancel = new Button("Ακύρωση");
        cancel.setDisable(true);


        //Check if any row from table is selected
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            data selectedLine = newSelection;
            if (newSelection != null) {
                //if a row is selected then enable the buttons
                cancel.setDisable(false);
                pay.setDisable(false);
            }
            else {
                //else disable them
                pay.setDisable(true);
                cancel.setDisable(true);
            }

            //Confirmation alert for cancelation
            cancel.setOnAction((event) -> {
                Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                closeConfirmation.setTitle("Επιβεβαίωση");
                closeConfirmation.setHeaderText("Είστε σίγουρος ότι θέλετε να ακυρώσετε την επιλογή σας;");
                closeConfirmation.setContentText("");
                ButtonType YES = new ButtonType("ΝΑΙ", ButtonData.YES);
                ButtonType NO = new ButtonType("ΟΧΙ", ButtonData.NO);  
                closeConfirmation.getButtonTypes().setAll(YES, NO);
                closeConfirmation.initModality(Modality.WINDOW_MODAL);
                closeConfirmation.initOwner(stage);
                Optional<ButtonType> result = closeConfirmation.showAndWait();
                if (result.get() == NO) {
                    //OXI -> close the alert
                    closeConfirmation.close();
                }
                if (result.get() == YES) {
                    //ΝΑΙ -> delete record from file
                    saveData.delete(EchoServerThread.getArrivalDateTime(selectedLine),EchoServerThread.getValue(selectedLine).replaceAll("\n", ","), EchoServerThread.getRegistrationNumber(selectedLine), EchoServerThread.getCost(selectedLine));
                    //ΝΑΙ -> delete record from table
                    EchoServerThread.deleteFromList(selectedLine);
                }
            });
            
            //Confirmation alert for payment
            pay.setOnAction((event) -> {
                Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                closeConfirmation.setTitle("Επιβεβαίωση");
                closeConfirmation.setHeaderText("Θέλετε να πληρώσετε;");
                closeConfirmation.setContentText("Ημερομηνία-Ώρα άφιξης: " + EchoServerThread.getArrivalDateTime(selectedLine) + "\nΑριθμός κυκλοφορίας: " + EchoServerThread.getRegistrationNumber(selectedLine) + "\nΚόστος: " + EchoServerThread.getCost(selectedLine) + "\nΥπηρεσία/τιμή: " + EchoServerThread.getValue(selectedLine));
                ButtonType YES = new ButtonType("ΝΑΙ", ButtonData.YES);
                ButtonType NO = new ButtonType("ΟΧΙ", ButtonData.NO);  
                closeConfirmation.getButtonTypes().setAll(YES, NO);
                closeConfirmation.initModality(Modality.WINDOW_MODAL);
                closeConfirmation.initOwner(stage);
                Optional<ButtonType> result = closeConfirmation.showAndWait();
                if (result.get() == NO) {
                    //OXI -> close the alert
                    closeConfirmation.close();
                }
                if (result.get() == YES) {
                    //ΝΑΙ -> add extra info to the existing record in file
                    saveData.addData(EchoServerThread.getArrivalDateTime(selectedLine), EchoServerThread.getValue(selectedLine).replaceAll("\n", ","), EchoServerThread.getRegistrationNumber(selectedLine), EchoServerThread.getCost(selectedLine));
                    //ΝΑΙ -> delete record from table
                    EchoServerThread.deleteFromList(selectedLine);
                }
            });

            //Payment using ENTER key  
            table.setOnKeyReleased(event -> {
                if (event.getCode() == KeyCode.ENTER){
                    Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    closeConfirmation.setTitle("Επιβεβαίωση");
                    closeConfirmation.setHeaderText("Θέλετε να πληρώσετε;");
                    closeConfirmation.setContentText("Ημερομηνία-Ώρα άφιξης: " + EchoServerThread.getArrivalDateTime(selectedLine) + "\nΑριθμός κυκλοφορίας: " + EchoServerThread.getRegistrationNumber(selectedLine) + "\nΚόστος: " + EchoServerThread.getCost(selectedLine) + "\nΥπηρεσία/τιμή: " + EchoServerThread.getValue(selectedLine));
                    ButtonType YES = new ButtonType("ΝΑΙ", ButtonData.YES);
                    ButtonType NO = new ButtonType("ΟΧΙ", ButtonData.NO);  
                    closeConfirmation.getButtonTypes().setAll(YES, NO);
                    closeConfirmation.initModality(Modality.WINDOW_MODAL);
                    closeConfirmation.initOwner(stage);
                    Optional<ButtonType> result = closeConfirmation.showAndWait();
                    if (result.get() == NO) {
                        //OXI -> close the alert
                        closeConfirmation.close();
                    }
                    if (result.get() == YES) {
                        //ΝΑΙ -> add extra info to the existing record in file
                        saveData.addData(EchoServerThread.getArrivalDateTime(selectedLine),  EchoServerThread.getValue(selectedLine).replaceAll("\n", ","), EchoServerThread.getRegistrationNumber(selectedLine), EchoServerThread.getCost(selectedLine));
                        //ΝΑΙ -> delete record from table
                        EchoServerThread.deleteFromList(selectedLine);
                    }
                }
            });
        });

        
        //Hbox to horizontally ALIGN the two buttons
        HBox hbox = new HBox(pay, cancel);
        hbox.setSpacing(30);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setAlignment(Pos.TOP_CENTER);


        //Vbox to vertically ALIGN the label, the table and the hbox from above 
        VBox vbox = new VBox(label, table, hbox);
        vbox.setSpacing(30);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setStyle("-fx-font-size: 2em;");
        vbox.setAlignment(Pos.TOP_CENTER);
 

        var scene = new Scene(vbox, 1280, 720);
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setMaxWidth(1920);
        stage.setMaxHeight(1080);
        stage.setScene(scene);
        stage.setTitle("Server");
        stage.show();
        

        //Creating new THREAD so that the server can run
        Thread thread = new Thread(new getData());
        thread.start();
       
        
        //If user tries to close the main window and one or more orders have not been paid, prompt him with an ERROR alert
        stage.setOnCloseRequest(new javafx.event.EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                //Check that no orders are pending
                if(EchoServerThread.returnList().isEmpty()) {
                    System.exit(0);
                    stage.close();
                }
                else {
                    //Else prompt user with the ERROR alert
                    Alert closeError = new Alert(Alert.AlertType.ERROR);
                    closeError.setTitle("Σφάλμα");
                    closeError.setHeaderText("Σφάλμα κατά το κλείσιμο του παραθύρου.");
                    closeError.setContentText("Υπάρχουν απλήρωτες καταχωρήσεις!");
                    closeError.initModality(Modality.WINDOW_MODAL);
                    closeError.initOwner(stage);
                    Optional<ButtonType> result = closeError.showAndWait();
                    //Close the alert once the users presses the OK button
                    if (ButtonType.OK.equals(result.get())) {
                        we.consume();               //CONSUME method signals that processing of the event is complete.
                    }                     
                }
            }
        });   

        
    }

    public static void main(String[] args) {

        launch(args);
       
    }
}