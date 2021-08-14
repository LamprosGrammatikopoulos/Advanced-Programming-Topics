package gr.uop;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class Book extends Dialog {
    static int maxLength=160;
    private Button addButton, removeButton, cancelButton, okButton;
    private VBox FirstListVBox, ButtonsVBox, SecondListVBox;
    private ListView<String> ListLeft, ListRight;
    private ObservableList<String> ObsListLeft, ObsListRight, selectedItems, subentries;
    private String allNumbers="";

    public String Recipients(String str) {

        Stage stage2 = new Stage();
        
        addButton = new Button("Add");                              //add button
        addButton.setMaxWidth(120);
        removeButton = new Button("Remove");                             //remove button 
        removeButton.setMaxWidth(120);
        cancelButton = new Button("Cancel");                              //cancel button
        okButton = new Button("OK");                             //ok button 

        ObsListLeft = FXCollections.<String>observableArrayList();  //Observable List for phone numbers into Left list                     
        int range = 10;
        for (int i=0; i<20; i++) {                                          //Filling Left Observable List with phone numbers
                ObsListLeft.add("69"+(int)(Math.random() * range)+""+(int)(Math.random() * range)+""+(int)(Math.random() * range)+""+(int)(Math.random() * range)+""+(int)(Math.random() * range)+""+(int)(Math.random() * range)+""+(int)(Math.random() * range)+""+(int)(Math.random() * range));
        }
        ListLeft = new ListView<>(ObsListLeft);                                             //List View for the Left Observable List
        ListLeft.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        VBox.setVgrow(ListLeft, Priority.ALWAYS);                               //Expandable Vbox for left list

        ListRight = new ListView<>();                                                       //List View for the Right List
        ListRight.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        VBox.setVgrow(ListRight, Priority.ALWAYS);                               //Expandable Vbox for right list

        FirstListVBox = new VBox(ListLeft);                                        //Vbox for Left List View
        FirstListVBox.setMaxWidth(150);
        FirstListVBox.setAlignment(Pos.CENTER_LEFT);

        SecondListVBox = new VBox(ListRight);                                        //Vbox for Right List View
        SecondListVBox.setMaxWidth(150);
        
        ButtonsVBox = new VBox(addButton, removeButton);                               //Vbox Buttons Add & Remove
        ButtonsVBox.setSpacing(10);
        ButtonsVBox.setAlignment(Pos.CENTER);
                            


        HBox hbox1 = new HBox(FirstListVBox, ButtonsVBox, SecondListVBox);            //Hbox for all Vboxes
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setPadding(new Insets(10, 10, 10, 10));

        HBox hbox2 = new HBox(cancelButton, okButton);                  //Hbox for OK and Cancel buttons
        hbox2.setSpacing(10);
        hbox2.setAlignment(Pos.BOTTOM_RIGHT);
        hbox2.setPadding(new Insets(10, 10, 10, 10));
        

        VBox finalVbox = new VBox(hbox1, hbox2);                        //Final Vbox with all Hboxes
        


        cancelButton.setOnAction((e) -> {                               //If user pressed Cancel button
            stage2.close();
        });
       
        if(str.equals("")) {                                          //If textField with phone numbers and ";" is empty
            removeButton.setDisable(true);                           //Disabling remove button at start
        }
        
        /*----------------------Metafora pros ta DEXIA----------------*/
        ObsListRight = FXCollections.<String>observableArrayList();             //Observable List for right list
        if(!str.equals("")) {                                                      //If textField with phone numbers and ";" isnt empty
            if(str.contains(";")) {                                                 //If textField with phone numbers and ";" contains ";"
                String tmpArray[] = str.split(";");                                 //Temporary array with numbers and ""
                for(int i=0;i<tmpArray.length;i++) {
                    if(!tmpArray[i].equals("")) {
                        ObsListRight.add(tmpArray[i]);
                    }
                }
            }
            else {                                                       //If textField with phone numbers and ";" is empty
                ObsListRight.add(str);
            }
            ListRight.setItems(ObsListRight);  
        }
       
      

        addButton.setOnAction((event) -> {                                        //Action Event for add button
            selectedItems = ListLeft.getSelectionModel().getSelectedItems();
            if (!ListLeft.getSelectionModel().isEmpty()) {                          //If list Left is empty
                removeButton.setDisable(false);
                ObsListRight.addAll(selectedItems);
                ObsListLeft.removeAll(selectedItems);
                ListRight.setItems(ObsListRight);
                if(ListLeft.getSelectionModel().isEmpty()) {
                    addButton.setDisable(true);
                }
            }
        });


        /*------------------------Metafora pros ta ARISTERA-----------------------------*/
        removeButton.setOnAction((event) -> {                                              //Action Event for remove button
            selectedItems = ListRight.getSelectionModel().getSelectedItems();
            if (!ListRight.getSelectionModel().isEmpty()) {                                  //If list Right is empty
                addButton.setDisable(false);    
                ObsListLeft.addAll(selectedItems);
                ObsListRight.removeAll(selectedItems);
                ListLeft.setItems(ObsListLeft);
                if (ListRight.getSelectionModel().isEmpty()) {
                    removeButton.setDisable(true);
                }
            }
        });

      
        

        okButton.setOnAction((e) -> {                                   //Action Event for OK button
            setNewNumbers(ObsListRight);                                //Calling function who concatenate the new string for textField
            stage2.close();
        }); 

        Scene scene2 = new Scene(finalVbox, 420, 340);
        stage2.setMaxWidth(420);
        stage2.setMaxHeight(340);
        stage2.setMinWidth(420);
        stage2.setMinHeight(340);
        stage2.setScene(scene2);
        stage2.setTitle("Add recipients");
        stage2.showAndWait();


        return allNumbers;                                          //Return new string for textField
    }

    public void setNewNumbers(ObservableList<String> ObsListRight) {            //Function who concatenate the new string for textField
      
        for(int i=0;i<ObsListRight.size();i++) {
            if(i<ObsListRight.size()-1) {
                allNumbers=allNumbers+ObsListRight.get(i)+";";
            }
            else {
                allNumbers=allNumbers+ObsListRight.get(i);
            }
        }
    }

    
}