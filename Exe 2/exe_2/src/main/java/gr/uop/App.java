package gr.uop;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private Button LeftButton, RightButton, UpButton, DownButton;
    private VBox FilterListVBox, FisrtButtonsVBox, ListVBox, LastButtonsVBox;
    private TextField Filter;
    private ListView<String> ListLeft, ListRight;
    private ImageView LeftImageView, RightImageView, UpImageView, DownImageView;
    private ObservableList<String> ObsListLeft, ObsListRight, selectedItems, subentries;
    private boolean Flag=false;

    @Override
    public void start(Stage stage) {

        LeftImageView = new ImageView(new Image(getClass().getResourceAsStream("images/left.png")));                //Images 
        RightImageView = new ImageView(new Image(getClass().getResourceAsStream("images/right.png")));
        UpImageView = new ImageView(new Image(getClass().getResourceAsStream("images/up.png")));
        DownImageView = new ImageView(new Image(getClass().getResourceAsStream("images/down.png")));


        LeftButton = new Button();                              //Left button 
        LeftButton.setGraphic(LeftImageView);

        RightButton = new Button();                             //Right button 
        RightButton.setGraphic(RightImageView);

        UpButton = new Button();                                //Up button 
        UpButton.setGraphic(UpImageView);

        DownButton = new Button();                              //Down button
        DownButton.setGraphic(DownImageView);



        Filter = new TextField();                               //TextField for Filter
        Filter.setPromptText("type to filter");
        Filter.textProperty().addListener(new ChangeListener() {                                //When user writes any character
            public void changed(ObservableValue observable, Object oldVal,Object newVal) {
                search((String) oldVal,(String) newVal);
            }
        });

        ObsListLeft = FXCollections.<String>observableArrayList();                       //Observable List for items into Left list
        for (int i=0; i<20; i++) {                                                       //Filling Left Observable List with Items

            ObsListLeft.add("Item "+(i+1));
        }
        ListLeft = new ListView<>(ObsListLeft);                                             //List View for the Left Observable List
        ListLeft.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ListRight = new ListView<>();                                                       //List View for the Right List
        ListRight.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        FilterListVBox = new VBox(Filter, ListLeft);                                        //Vbox for Filter Text and Left List View
        FilterListVBox.setSpacing(5);
        FilterListVBox.setMaxWidth(150);
        
        FisrtButtonsVBox = new VBox(LeftButton, RightButton);                                //Vbox Buttons Left & Right
        FisrtButtonsVBox.setSpacing(8);
        FisrtButtonsVBox.setAlignment(Pos.CENTER);

        LastButtonsVBox = new VBox(UpButton, DownButton);                                      //Vbox Buttons Up & Down 
        LastButtonsVBox.setSpacing(8);
        LastButtonsVBox.setAlignment(Pos.CENTER);

        ListVBox = new VBox(ListRight);                                                       //Vbox for Right List View
        ListVBox.setPadding(new Insets(30, 0, 0, 0));
        ListVBox.setMaxWidth(150);
       
        HBox hbox = new HBox(FilterListVBox, FisrtButtonsVBox, ListVBox, LastButtonsVBox);            //Hbox for all Vboxes
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.setPadding(new Insets(10, 50, 50, 50));

        
        VBox.setVgrow(ListLeft, Priority.ALWAYS);                               //Expandable Vboxes for LeftList and RightList
        VBox.setVgrow(ListRight, Priority.ALWAYS);
        
        VBox.setVgrow(FisrtButtonsVBox, Priority.ALWAYS);                       //Expandable Vboxes for the four buttons
        VBox.setVgrow(LastButtonsVBox, Priority.ALWAYS);

        var scene = new Scene(hbox, 530, 480);
        stage.setMinWidth(530);
        stage.setMinHeight(480);
        stage.setScene(scene);
        stage.setTitle("Lists");
        stage.show();




        UpButton.setDisable(true);                                             //Disabling all buttons
        DownButton.setDisable(true);
        LeftButton.setDisable(true);

        /*----------------------Metafora pros ta DEXIA----------------*/
        ObsListRight = FXCollections.<String>observableArrayList();             //Observable List for RightList

        RightButton.setOnAction((event) -> {                                        //Action Event for Right Button(->)
            selectedItems = ListLeft.getSelectionModel().getSelectedItems();
            if (!ListLeft.getSelectionModel().isEmpty()) {                          //If list Left is empty
                LeftButton.setDisable(false);
                if (Flag == true) {                              // With Filter
                    ObsListRight.addAll(selectedItems);
                    subentries.removeAll(selectedItems);
                    ListRight.setItems(ObsListRight);
                    ObsListLeft.removeAll(ObsListRight);
                    Flag=false;
                    if (ObsListRight.size() > 1) {                                       //If the Right Observable List has more items than 1
                        UpButton.setDisable(false);
                        DownButton.setDisable(false);
                    }
                    if(ListLeft.getSelectionModel().isEmpty()) {
                        RightButton.setDisable(true);
                    }
                }
                else {                                      // Without filter
                    ObsListRight.addAll(selectedItems);
                    ObsListLeft.removeAll(selectedItems);
                    ListRight.setItems(ObsListRight);
                    if (ObsListRight.size() > 1) {
                        UpButton.setDisable(false);
                        DownButton.setDisable(false);
                    }
                    if(ListLeft.getSelectionModel().isEmpty()) {
                        RightButton.setDisable(true);
                    }
                }
                Filter.setText("");
            }
        });
 
       
        /*------------------------Metafora pros ta ARISTERA-----------------------------*/
        LeftButton.setOnAction((event) -> {                                              //Action Event for Right Button(<-)
            selectedItems = ListRight.getSelectionModel().getSelectedItems();
            if (!ListRight.getSelectionModel().isEmpty()) {
                RightButton.setDisable(false);
                ObsListLeft.addAll(selectedItems);
                ObsListRight.removeAll(selectedItems);
                ListLeft.setItems(ObsListLeft);
                if (ListRight.getSelectionModel().isEmpty()) {
                    LeftButton.setDisable(true);
                    UpButton.setDisable(true);
                    DownButton.setDisable(true);
                }
            }
        });

        /*----------------------Metafora pros ta PANW-----------------------------*/
        UpButton.setOnAction((event) -> {                                                        //Action Event for Up Button
            if (!ListRight.getSelectionModel().isEmpty() && ObsListRight.size() > 1) {
                String selected =  ListRight.getSelectionModel().getSelectedItem();             
                int index = ObsListRight.indexOf(selected);
                if (index != 0) {
                    String Before = ObsListRight.get(index-1);                                  //Flipping items with the above item
                    ObsListRight.set(index-1, selected);
                    ObsListRight.set(index, Before);
                    ListRight.setItems(ObsListRight);
                }
            
            } 
            else {
                UpButton.setDisable(true);
            }  
        });

        /*----------------------Metafora pros ta KATW-----------------------------*/        
        DownButton.setOnAction((event) -> {                                                       //Action Event for Down Button
            if (!ListRight.getSelectionModel().isEmpty() && ObsListRight.size() > 1) {
                String selected =  ListRight.getSelectionModel().getSelectedItem();
                int index = ObsListRight.indexOf(selected);
                if (index != ObsListRight.size()-1) {                                            //Flipping items with the below item
                    String Before = ObsListRight.get(index+1);
                    ObsListRight.set(index+1, selected);
                    ObsListRight.set(index, Before);
                    ListRight.setItems(ObsListRight);
                }  
            }
            else {
                DownButton.setDisable(true);
            }   
        });
 
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void search(String oldVal, String newVal) {                          //Filtering Function
        Flag = true;
        if (oldVal != null && (newVal.length() < oldVal.length())) {
            ListLeft.setItems(ObsListLeft);
        }
        String value = newVal.toUpperCase();
        subentries = FXCollections.observableArrayList();
        for (Object entry : ListLeft.getItems()) {
            boolean match = true;
            String entryText = (String) entry;
            if (!entryText.toUpperCase().contains(value)) {
                match = false;
            }
            if (match) {
                subentries.add(entryText);
            }
        }
        ListLeft.setItems(subentries);
    }
}