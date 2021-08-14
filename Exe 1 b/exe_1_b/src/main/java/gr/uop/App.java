package gr.uop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class App extends Application {

    private Button FirstButton, SecondButton, ClearButton;
    private VBox  ButtonsVBox;
    private Label CelLabel, FarLabel, ErrorLabel;
    private TextField CelText, FarText;
    private GridPane UpGrid;


    @Override
    public void start(Stage stage) {
        
        CelLabel = new Label("Κελσίου");            //Label for 'Κελσίου'
        CelLabel.setStyle("-fx-font-size:14;");     
        CelText = new TextField();                       //Text Field for 'Κελσίου'
        CelText.setPrefWidth(70);                      


        FarLabel = new Label("Φαρενάιτ");            //Label for 'Φαρενάιτ'
        FarLabel.setStyle("-fx-font-size:14;");       
        FarText = new TextField();                      //Text Field for 'Φαρενάιτ'
        FarText.setPrefWidth(70);                           


        FirstButton = new Button("->");                      //First Button with  '->'
        FirstButton.setStyle("-fx-font-size:13;"); 
        SecondButton = new Button("<-");                        //Second Button with  '<-'
        SecondButton.setStyle("-fx-font-size:13;");


        ButtonsVBox = new VBox(FirstButton, SecondButton);              //Vbox for two buttons ->,<-

        UpGrid = new GridPane();                                                        //GridPane for 'Κελσίου' ,Text Field for 'Κελσίου',Vbox for two buttons (->,<-),Text Field for 'Φαρενάιτ' and Label for 'Φαρενάιτ'
        UpGrid.addRow(0,CelLabel, CelText, ButtonsVBox, FarText, FarLabel);
        UpGrid.setHgap(10);
        UpGrid.setAlignment(Pos.CENTER);
        UpGrid.setPadding(new Insets(0, 0, 0, 8));

        ErrorLabel = new Label();                            //Label for Errors messages
        ErrorLabel.setPadding(new Insets(0, 0, 10, 0));
        ErrorLabel.setStyle("-fx-font-weight: bold;");
        ErrorLabel.setTextFill(Color.web("red"));
        ErrorLabel.setVisible(false);

        ClearButton = new Button("Καθαρισμός");                     //Button for 'Καθαρισμός'
        ClearButton.setStyle("-fx-font-size:13;"); 

      
        VBox vbox = new VBox(UpGrid, ErrorLabel, ClearButton);          //vbOX for the upper GridPane and Button 'Καθαρισμός'
        VBox.setVgrow(UpGrid, Priority.ALWAYS);
        VBox.setVgrow(ClearButton, Priority.ALWAYS);
        vbox.setPadding(new Insets(-10, 0, 10, 0));
        vbox.setAlignment(Pos.CENTER);




        //-----------------------ActionEvents---------------------------//


        FirstButton.setOnAction((event) -> {   //Calculation when Button '->' is pressed
            if (CelText.getText().isEmpty()) {        //If Celsium TextField is empty 
                ErrorLabel.setVisible(true);
                ErrorLabel.setText("Please give an input");
            }
            else if (!CelText.getText().matches("^[-+]?\\d+$")) {   //If Celsium TextField is integer number
                ErrorLabel.setVisible(true);
                ErrorLabel.setText("Please give an integer as input");
            }
            else {
                FarText.setText( "" + (1.8000*Double.parseDouble(CelText.getText())+32) );
                CelText.setText("");
                ErrorLabel.setVisible(false);
            }
        });
        CelText.setOnKeyPressed(event -> {                                     //Calculation when Enter is pressed in textField of Celsium
            if (event.getCode().equals(KeyCode.ENTER)) {
                FirstButton.fire();
            }
        });



        SecondButton.setOnAction((event) -> {     //Calculation when Button '<-' is pressed
            if (FarText.getText().isEmpty()) {       //If Fahrenheit TextField is empty 
                ErrorLabel.setVisible(true);
                ErrorLabel.setText("Please give an input");
            }
            else if (!FarText.getText().matches("^[-+]?\\d+$")) {   //If Fahrenheit TextField is integer number
                ErrorLabel.setVisible(true);
                ErrorLabel.setText("Please give an integer as input");
            }
            else {
                CelText.setText( "" + (0.5555*(Double.parseDouble(FarText.getText())-32)) );
                FarText.setText("");
                ErrorLabel.setVisible(false);
            }
        });
        FarText.setOnKeyPressed(event -> {                                      //Calculation when Enter is pressed in textField of Fahrenheit
            if (event.getCode().equals(KeyCode.ENTER)) {
                SecondButton.fire();
            }
        });




        ClearButton.setOnAction((event) -> {        //Action for Button 'Καθαρισμός'
            CelText.setText("");
            FarText.setText("");
            ErrorLabel.setVisible(false);
        });

        var scene = new Scene(vbox, 350, 130);                 
        stage.setMinWidth(360);
        stage.setMinHeight(190);
        stage.setScene(scene);
        stage.setTitle("Μετατροπές θερμοκρασίας");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}