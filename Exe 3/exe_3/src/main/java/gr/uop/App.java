package gr.uop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.Optional;


public class App extends Application {
    private HBox recipienthbox, buttonhbox; 
    private TextField text;
    private TextArea textArea;
    private Label to, wordsleft;
    private Button add, send;
    private static int maxLength=160;
    private Stage mainStage;

    @Override
    public void start(Stage stage) {

        this.mainStage = stage;                                                                 //<---------------------

        to = new Label("To:");                                                              //Label for "To:"
        to.setStyle("-fx-font-size: 12px;");

        text = new TextField();                                                              //TextField numbers with ";"
        text.setPromptText("Type numbers separated with ';'");      
        text.setFocusTraversable(false);
        text.setTextFormatter(new TextFormatter<>(filter));                             //Calling the function who recognise regex for text inputs
       

        add = new Button("Add..."); //Add to be continued                               //Button for "Add"
        add.setFocusTraversable(false);                                                     
       

        recipienthbox = new HBox(to,text,add);                                          //Hbox for "To:" , textfield and Add button
        recipienthbox.setSpacing(5);
        recipienthbox.setHgrow(text, Priority.ALWAYS);
        recipienthbox.setAlignment(Pos.CENTER);
    

        textArea = new TextArea();                                                      //TextArea for message
        textArea.setPromptText("Type message text.");
        textArea.setFocusTraversable(false);


        send = new Button("Send");                                                      //Send buuton
        send.setMaxWidth(Double.MAX_VALUE);                                 
        send.setFocusTraversable(false);

    
        send.setOnAction((e) -> {                                   

            Boolean flag=false,flag1=false;                                                     //Flags for cases if user has write recipient or he want to write mesage(if this is empty) 

            if(!text.getText().matches("([0-9]+;*)+")) {                                        //If textField has no recipient
                Alert emptyRecipients = new Alert(Alert.AlertType.WARNING);
                emptyRecipients.setTitle("Warning");
                emptyRecipients.setHeaderText("Add recipients.");
                emptyRecipients.setContentText("There is no recipient to send mail to.");
                emptyRecipients.getButtonTypes().setAll(ButtonType.OK);
                emptyRecipients.initModality(Modality.WINDOW_MODAL);
                emptyRecipients.initOwner(stage);
                emptyRecipients.showAndWait();
                flag=true;
            }
            if(textArea.getText().equals("")) {                                                    //If message box has no text mesage
                Alert emptyMessage = new Alert(Alert.AlertType.WARNING);
                emptyMessage.setTitle("Warning");
                emptyMessage.setHeaderText("Your message is empty.");
                emptyMessage.setContentText("Do you want to write something?");
                emptyMessage.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);
                emptyMessage.initModality(Modality.WINDOW_MODAL);
                emptyMessage.initOwner(stage);
                Optional<ButtonType> result = emptyMessage.showAndWait();
                if (result.get() == ButtonType.NO) {
                   
                    flag1=true;
                }
                if (result.get() == ButtonType.YES) {
                    flag=true;
                    flag1=false;
                }
            }
            if(flag!=true || flag1==true){
                Alert messageBeingSend = new Alert(AlertType.INFORMATION);
                messageBeingSend.setTitle("Information");
                messageBeingSend.setHeaderText("Success!");
                messageBeingSend.setContentText("Your message is being send.");
                messageBeingSend.initModality(Modality.WINDOW_MODAL);
                messageBeingSend.initOwner(stage);
                messageBeingSend.showAndWait();
                flag=false;
            }
        });
        
        
        buttonhbox = new HBox(send);                                                //Hbox for Send button
        buttonhbox.setHgrow(send, Priority.ALWAYS);


        wordsleft = new Label("0/160");                                              //Label for "0/160"
        wordsleft.setAlignment(Pos.BOTTOM_RIGHT);
        addTextLimiter(textArea,wordsleft);                                             //Calling the function  who counts characters in textArea


        VBox vbox = new VBox(recipienthbox, textArea, buttonhbox, wordsleft);               //Vbox for all the hboxes
        vbox.setSpacing(5);
        vbox.setVgrow(textArea, Priority.ALWAYS);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.setAlignment(Pos.BOTTOM_RIGHT);

        var scene1 = new Scene(vbox, 420, 340);
        stage.setMinWidth(420);
        stage.setMinHeight(340);
        stage.setScene(scene1);
        stage.setTitle("SMS App");

        add.setOnAction((e) -> {                                                            //Action for Add button
            Book book = new Book();
            String newNumbers=book.Recipients(text.getText().toString());                       //Calling the main method from class 'Book' that opens new stage with book of phone numbers lists
            if(!newNumbers.equals("")) {
               text.setText(newNumbers);                                                    //Setting the new textField after Add button 
            }
        });  

        stage.setOnCloseRequest(new javafx.event.EventHandler<WindowEvent>() {                      //If user presses X button in main window
            public void handle(WindowEvent we) {
                String data=textArea.getText().trim();
                if(!data.equals("")){
                    Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to exit?");                         //Alert that asking user if he wants to close window
                    Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
                    exitButton.setText("Exit");
                    closeConfirmation.setHeaderText("Confirm Exit");
                    closeConfirmation.initModality(Modality.APPLICATION_MODAL);
                    closeConfirmation.initOwner(mainStage);
                    Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
                    if (!ButtonType.OK.equals(closeResponse.get())) {
                        we.consume();
                    }
                }
                else {
                    stage.close();
                }
            }
        });        
       
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void addTextLimiter(final TextArea ta, Label label) {                                             //Function for characters counting in label
        ta.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                if (ta.getText().length() >= maxLength) {
                    String s = ta.getText().substring(0, maxLength);
                    maxLength += 160;
                    ta.setText(s);
                    label.textProperty().bind(ta.textProperty().length().asString("%d/" + maxLength));
                }
                else{
                    label.textProperty().bind(ta.textProperty().length().asString("%d/" + maxLength));
                }
            }
        });
    }
    
    //epitrepontai mono arithmoi kai ellhnika erwthmatika
    UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {             //Function who checks user input in textField
        @Override
        public TextFormatter.Change apply(TextFormatter.Change t) {
            if (t.isReplaced()) {
                if(t.getText().matches("[0-9]{10};")) {
                    t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
                }
            }
            if (t.isAdded()) {
                if(t.getText().matches("[^0-9;]")){
                    t.setText("");
                }
            }
            return t;
        }
    };    
}
