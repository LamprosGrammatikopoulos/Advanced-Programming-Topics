package gr.uop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Client extends Application {

    private Button Q, W, E, R, T, Y, U, I, O, P, A, S, D, F, G, H, J, K, L, Z, X, C, V, B, N, M, NEXT, BACKSPACE, SPACE, N_1, N_2, N_3, N_4, N_5, N_6, N_7, N_8, N_9, N_0;
    private VBox LettersVbox, NumbersVbox;
    private HBox LettersHbox1, LettersHbox2, LettersHbox3, LettersHbox4, NumbersHbox1, NumbersHbox2, NumbersHbox3, NumbersHbox4, hboxTop, hboxBottom;
    private static TextField textField;

    @Override
    public void start(Stage stage) {


        //---------------------TextField-------------------
        textField = new TextField();
        textField.setPromptText("Enter your registration number here");
        textField.setStyle("-fx-font-size: 2em;");


        //------------------Buttons-----------------------
        Q = new Button("Q");
        Q.setStyle("-fx-font-size: 2em;");
        Q.setPadding(new Insets(5, 20, 5, 20));
        Q.setOnAction((event) -> {
            textField.setText(textField.getText()+"Q");
        });

        W = new Button("W");
        W.setStyle("-fx-font-size: 2em;");
        W.setPadding(new Insets(5, 20, 5, 20));
        W.setOnAction((event) -> {
            textField.setText(textField.getText()+"W");
        });

        E = new Button("E");
        E.setStyle("-fx-font-size: 2em;");
        E.setPadding(new Insets(5, 20, 5, 20));
        E.setOnAction((event) -> {
            textField.setText(textField.getText()+"E");
        });

        R = new Button("R");
        R.setStyle("-fx-font-size: 2em;");
        R.setPadding(new Insets(5, 20, 5, 20));
        R.setOnAction((event) -> {
            textField.setText(textField.getText()+"R");
        });

        T = new Button("T");
        T.setStyle("-fx-font-size: 2em;");
        T.setPadding(new Insets(5, 20, 5, 20));
        T.setOnAction((event) -> {
            textField.setText(textField.getText()+"T");
        });

        Y = new Button("Y");
        Y.setStyle("-fx-font-size: 2em;");
        Y.setPadding(new Insets(5, 20, 5, 20));
        Y.setOnAction((event) -> {
            textField.setText(textField.getText()+"Y");
        });

        U = new Button("U");
        U.setStyle("-fx-font-size: 2em;");
        U.setPadding(new Insets(5, 20, 5, 20));
        U.setOnAction((event) -> {
            textField.setText(textField.getText()+"U");
        });

        I = new Button("I");
        I.setStyle("-fx-font-size: 2em;");
        I.setPadding(new Insets(5, 20, 5, 20));
        I.setOnAction((event) -> {
            textField.setText(textField.getText()+"I");
        });

        O = new Button("O");
        O.setStyle("-fx-font-size: 2em;");
        O.setPadding(new Insets(5, 20, 5, 20));
        O.setOnAction((event) -> {
            textField.setText(textField.getText()+"O");
        });

        P = new Button("P");
        P.setStyle("-fx-font-size: 2em;");
        P.setPadding(new Insets(5, 20, 5, 20));
        P.setOnAction((event) -> {
            textField.setText(textField.getText()+"P");
        });

        A = new Button("A");
        A.setStyle("-fx-font-size: 2em;");
        A.setPadding(new Insets(5, 20, 5, 20));
        A.setOnAction((event) -> {
            textField.setText(textField.getText()+"A");
        });

        S = new Button("S");
        S.setStyle("-fx-font-size: 2em;");
        S.setPadding(new Insets(5, 20, 5, 20));
        S.setOnAction((event) -> {
            textField.setText(textField.getText()+"S");
        });

        D = new Button("D");
        D.setStyle("-fx-font-size: 2em;");
        D.setPadding(new Insets(5, 20, 5, 20));
        D.setOnAction((event) -> {
            textField.setText(textField.getText()+"D");
        });

        F = new Button("F");
        F.setStyle("-fx-font-size: 2em;");
        F.setPadding(new Insets(5, 20, 5, 20));
        F.setOnAction((event) -> {
            textField.setText(textField.getText()+"F");
        });

        G = new Button("G");
        G.setStyle("-fx-font-size: 2em;");
        G.setPadding(new Insets(5, 20, 5, 20));
        G.setOnAction((event) -> {
            textField.setText(textField.getText()+"G");
        });

        H = new Button("H");
        H.setStyle("-fx-font-size: 2em;");
        H.setPadding(new Insets(5, 20, 5, 20));
        H.setOnAction((event) -> {
            textField.setText(textField.getText()+"H");
        });

        J = new Button("J");
        J.setStyle("-fx-font-size: 2em;");
        J.setPadding(new Insets(5, 20, 5, 20));
        J.setOnAction((event) -> {
            textField.setText(textField.getText()+"J");
        });

        K = new Button("K");
        K.setStyle("-fx-font-size: 2em;");
        K.setPadding(new Insets(5, 20, 5, 20));
        K.setOnAction((event) -> {
            textField.setText(textField.getText()+"K");
        });

        L = new Button("L");
        L.setStyle("-fx-font-size: 2em;");
        L.setPadding(new Insets(5, 20, 5, 20));
        L.setOnAction((event) -> {
            textField.setText(textField.getText()+"L");
        });

        Z = new Button("Z");
        Z.setStyle("-fx-font-size: 2em;");
        Z.setPadding(new Insets(5, 20, 5, 20));
        Z.setOnAction((event) -> {
            textField.setText(textField.getText()+"Z");
        });

        X = new Button("X");
        X.setStyle("-fx-font-size: 2em;");
        X.setPadding(new Insets(5, 20, 5, 20));
        X.setOnAction((event) -> {
            textField.setText(textField.getText()+"X");
        });

        C = new Button("C");
        C.setStyle("-fx-font-size: 2em;");
        C.setPadding(new Insets(5, 20, 5, 20));
        C.setOnAction((event) -> {
            textField.setText(textField.getText()+"C");
        });

        V = new Button("V");
        V.setStyle("-fx-font-size: 2em;");
        V.setPadding(new Insets(5, 20, 5, 20));
        V.setOnAction((event) -> {
            textField.setText(textField.getText()+"V");
        });

        B = new Button("B");
        B.setStyle("-fx-font-size: 2em;");
        B.setPadding(new Insets(5, 20, 5, 20));
        B.setOnAction((event) -> {
            textField.setText(textField.getText()+"B");
        });

        N = new Button("N");
        N.setStyle("-fx-font-size: 2em;");
        N.setPadding(new Insets(5, 20, 5, 20));
        N.setOnAction((event) -> {
            textField.setText(textField.getText()+"N");
        });

        M = new Button("M");
        M.setStyle("-fx-font-size: 2em;");
        M.setPadding(new Insets(5, 20, 5, 20));
        M.setOnAction((event) -> {
            textField.setText(textField.getText()+"M");
        });

        BACKSPACE = new Button("<----");
        BACKSPACE.setStyle("-fx-font-size: 2em;");
        BACKSPACE.setPadding(new Insets(5, 20, 5, 20));
        BACKSPACE.setOnAction((event) -> {
            if(textField.getText().length() > 0) {
                //If BACKSPACE is pressed and input length is greater than 0 then erase the last character
                textField.replaceText(0, textField.getText().length(), textField.getText().substring(0, textField.getText().length()-1));
            }
        });

        SPACE = new Button("SPACE");
        SPACE.setStyle("-fx-font-size: 2em;");
        SPACE.setPadding(new Insets(5, 100, 5, 100));
        SPACE.setOnAction((event) -> {
            if(textField.getText().length() > 0) {
                //If input length is not 0 (so that the input do not start with space) add a SPACE
                textField.setText(textField.getText()+" ");
            }
        });

        NEXT = new Button("NEXT");
        NEXT.setStyle("-fx-font-size: 2em;");
        NEXT.setPadding(new Insets(5, 30, 5, 30));
        NEXT.setOnAction((event) -> {
            //Apply necessary restrictions to the input
            if(!textField.getText().matches("[0-9A-Z]{2}[0-9A-Z ]*")) {
                Alert WrongTextField = new Alert(Alert.AlertType.ERROR);
                WrongTextField.setTitle("Warning");
                WrongTextField.setHeaderText("Wrong registration number.");
                WrongTextField.setContentText("Registration number cannot be empty or less that 2 characters.");
                WrongTextField.getButtonTypes().setAll(ButtonType.OK);
                WrongTextField.initModality(Modality.WINDOW_MODAL);
                WrongTextField.initOwner(stage);
                WrongTextField.showAndWait();
            }
            else {
                //If input format is correct and button NEXT is pressed create the new WINDOW through the constructor of services
                services serv = new services();
            }
        });

        N_0 = new Button("0");
        N_0.setStyle("-fx-font-size: 2em;");
        N_0.setPadding(new Insets(5, 20, 5, 20));
        N_0.setOnAction((event) -> {
            textField.setText(textField.getText()+"0");
        });

        N_1 = new Button("1");
        N_1.setStyle("-fx-font-size: 2em;");
        N_1.setPadding(new Insets(5, 20, 5, 20));
        N_1.setOnAction((event) -> {
            textField.setText(textField.getText()+"1");
        });

        N_2 = new Button("2");
        N_2.setStyle("-fx-font-size: 2em;");
        N_2.setPadding(new Insets(5, 20, 5, 20));
        N_2.setOnAction((event) -> {
            textField.setText(textField.getText()+"2");
        });

        N_3 = new Button("3");
        N_3.setStyle("-fx-font-size: 2em;");
        N_3.setPadding(new Insets(5, 20, 5, 20));
        N_3.setOnAction((event) -> {
            textField.setText(textField.getText()+"3");
        });

        N_4 = new Button("4");
        N_4.setStyle("-fx-font-size: 2em;");
        N_4.setPadding(new Insets(5, 20, 5, 20));
        N_4.setOnAction((event) -> {
            textField.setText(textField.getText()+"4");
        });

        N_5 = new Button("5");
        N_5.setStyle("-fx-font-size: 2em;");
        N_5.setPadding(new Insets(5, 20, 5, 20));
        N_5.setOnAction((event) -> {
            textField.setText(textField.getText()+"5");
        });

        N_6 = new Button("6");
        N_6.setStyle("-fx-font-size: 2em;");
        N_6.setPadding(new Insets(5, 20, 5, 20));
        N_6.setOnAction((event) -> {
            textField.setText(textField.getText()+"6");
        });

        N_7 = new Button("7");
        N_7.setStyle("-fx-font-size: 2em;");
        N_7.setPadding(new Insets(5, 20, 5, 20));
        N_7.setOnAction((event) -> {
            textField.setText(textField.getText()+"7");
        });

        N_8 = new Button("8");
        N_8.setStyle("-fx-font-size: 2em;");
        N_8.setPadding(new Insets(5, 20, 5, 20));
        N_8.setOnAction((event) -> {
            textField.setText(textField.getText()+"8");
        });

        N_9 = new Button("9");
        N_9.setStyle("-fx-font-size: 2em;");
        N_9.setPadding(new Insets(5, 20, 5, 20));
        N_9.setOnAction((event) -> {
            textField.setText(textField.getText()+"9");
        });



        //--------------------Contents of first VBox are Hboxes----------------------
        LettersHbox1 = new HBox(Q, W, E, R, T, Y, U, I, O, P, BACKSPACE);
        LettersHbox1.setSpacing(12);
        LettersHbox1.setAlignment(Pos.CENTER);
        
        LettersHbox2 = new HBox(A, S, D, F, G, H, J, K, L);
        LettersHbox2.setSpacing(12);
        LettersHbox2.setAlignment(Pos.CENTER);

        LettersHbox3 = new HBox(Z, X, C, V, B, N, M);
        LettersHbox3.setSpacing(12);
        LettersHbox3.setAlignment(Pos.CENTER);

        LettersHbox4 = new HBox(SPACE);
        LettersHbox4.setSpacing(12);
        LettersHbox4.setAlignment(Pos.CENTER);
        
        LettersVbox = new VBox(LettersHbox1, LettersHbox2, LettersHbox3, LettersHbox4);
        LettersVbox.setSpacing(12);
        LettersVbox.setAlignment(Pos.CENTER);




        //--------------------Contents of second VBox are Hboxes----------------------
        NumbersHbox1 = new HBox(N_7,N_8,N_9);
        NumbersHbox1.setSpacing(12);
        NumbersHbox1.setAlignment(Pos.CENTER);
        
        NumbersHbox2 = new HBox(N_4,N_5,N_6);
        NumbersHbox2.setSpacing(12);
        NumbersHbox2.setAlignment(Pos.CENTER);

        NumbersHbox3 = new HBox(N_1,N_2,N_3);
        NumbersHbox3.setSpacing(12);
        NumbersHbox3.setAlignment(Pos.CENTER);

        NumbersHbox4 = new HBox(N_0);
        NumbersHbox4.setSpacing(12);
        NumbersHbox4.setAlignment(Pos.CENTER);
        
        NumbersVbox = new VBox(NumbersHbox1, NumbersHbox2, NumbersHbox3, NumbersHbox4);
        NumbersVbox.setSpacing(12);
        NumbersVbox.setAlignment(Pos.CENTER);


        //Hbox at the top for the TextField and the button
        hboxTop = new HBox(textField, NEXT);
        hboxTop.setSpacing(30);
        hboxTop.setAlignment(Pos.CENTER);
        hboxTop.setPadding(new Insets(10, 60, 10, 60));
        hboxTop.setHgrow(textField, Priority.ALWAYS);


        //Hbox at the bottom for the letters and numbers
        hboxBottom = new HBox(LettersVbox, NumbersVbox);
        hboxBottom.setSpacing(30);
        hboxBottom.setAlignment(Pos.TOP_CENTER);


        //Vbox to vertically ALIGN the two Hboxes
        VBox vbox = new VBox(hboxTop, hboxBottom);
        vbox.setSpacing(100);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 10, 10, 10));


        var scene = new Scene(vbox, 1280, 720);
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setMaxWidth(1920);
        stage.setMaxHeight(1080);
        stage.setScene(scene);
        stage.setTitle("Registration Number");
        stage.show();
    }

    //Retrieve the input from the TextField
    public static String getRegistrationNumber() {
        return textField.getText();
    } 

    public static void main(String[] args) {
        launch(args);
    }

}