package gr.uop;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Modality;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class services extends Client {
    
    private VBox vbox, vbox1, vbox2, vbox3, vbox4;
    private HBox hboxTop, hboxMiddle, hboxBottom;
    private RadioButton rbcar, rbjeep, rbmoto, rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10;
    private Label LBcar, LBjeep, LBmoto,costLabel;
    private final ToggleGroup group1, group2, group3, group4;
    private Button SEND, CANCEL;
    private int cost;

    private int typeOfVehicle;
    private boolean rb1Flag=false, rb2Flag=false, rb3Flag=false, rb4Flag=false, rb5Flag=false, rb6Flag=false, rb7Flag=false, rb8Flag=false, rb9Flag=false, rb10Flag=false;

    public services() {

        String rn = getRegistrationNumber();            //Get Registration number from Client

        Stage stage2 = new Stage();

        group1 = new ToggleGroup();
        group2 = new ToggleGroup();
        group3 = new ToggleGroup();
        group4 = new ToggleGroup();

        LBcar = new Label("Αυτοκίνητο");
        LBjeep = new Label("Τζιπ");
        LBmoto = new Label("Μοτοσυκλέτα");

        costLabel = new Label("Συνολικό κόστος: ");


        //Add Radio-Buttons for car type to correct ToggleGroups
        rbcar = new RadioButton();
        rbcar.setToggleGroup(group1);

        rbjeep = new RadioButton();
        rbjeep.setToggleGroup(group1);

        rbmoto = new RadioButton();
        rbmoto.setToggleGroup(group1);


        //Add Radio-Buttons for service type to correct ToggleGroups
        rb1 = new RadioButton();
        rb1.setToggleGroup(group2);
        rb1.setDisable(true);

        rb2 = new RadioButton();
        rb2.setToggleGroup(group3);
        rb2.setDisable(true);

        rb3 = new RadioButton();
        rb3.setToggleGroup(group4);
        rb3.setDisable(true);

        rb4 = new RadioButton();
        rb4.setToggleGroup(group2);
        rb4.setDisable(true);

        rb5 = new RadioButton();
        rb5.setToggleGroup(group3);
        rb5.setDisable(true);

        rb6 = new RadioButton();
        rb6.setToggleGroup(group4);
        rb6.setDisable(true);

        rb7 = new RadioButton();
        rb7.setDisable(true);

        rb8 = new RadioButton();
        rb8.setDisable(true);

        rb9 = new RadioButton();
        rb9.setDisable(true);

        rb10 = new RadioButton();
        rb10.setDisable(true);

        /*
            rbcar, rbjeep, rbmoto->group1
            rb1, rb4->group2
            rb2, rb5->group3
            rb3, rb6->group4
        */


        //Restrictions and calculations of radio buttons for car type
        group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                //All radio buttons for the services are DISABLED at first 
                rb1.setSelected(false);
                rb2.setSelected(false);
                rb3.setSelected(false);
                rb4.setSelected(false);
                rb5.setSelected(false);
                rb6.setSelected(false);
                rb7.setSelected(false);
                rb8.setSelected(false);
                rb9.setSelected(false);
                rb10.setSelected(false);
                rb1Flag=false;
                rb2Flag=false;
                rb3Flag=false;
                rb4Flag=false;
                rb5Flag=false;
                rb6Flag=false;
                //If motorcycle is the type of vehicle the ENABLE only some radio buttons
                if(group1.getSelectedToggle() == rbmoto) {
                    rb1.setDisable(false);
                    rb2.setDisable(true);
                    rb3.setDisable(true);
                    rb4.setDisable(false);
                    rb5.setDisable(true);
                    rb6.setDisable(true);
                    rb7.setDisable(true);
                    rb8.setDisable(false);
                    rb9.setDisable(false);
                    rb10.setDisable(true);
                    //Set cost to 0
                    cost=0;
                    costLabel.setText("Συνολικό κόστος: " + cost);
                }
                //Else if car of jeep is the type of vehicle the ENABLE all the radio buttons
                else if(group1.getSelectedToggle() == rbcar || group1.getSelectedToggle() == rbjeep) {
                    rb1.setDisable(false);
                    rb2.setDisable(false);
                    rb3.setDisable(false);
                    rb4.setDisable(false);
                    rb5.setDisable(false);
                    rb6.setDisable(false);
                    rb7.setDisable(false);
                    rb8.setDisable(false);
                    rb9.setDisable(false);
                    rb10.setDisable(false);
                    //Set cost to 0
                    cost=0;
                    costLabel.setText("Συνολικό κόστος: " + cost);
                }
            }
        });

        //Restrictions and calculations of rb1 and rb4
        group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if(group2.getSelectedToggle() != null) {
                    rb3.setSelected(false);
                    rb6.setSelected(false);
                }
                if(group2.getSelectedToggle() == rb1) {
                    rb2.setSelected(false);
                }
                if(group2.getSelectedToggle() == rb4) {
                    rb5.setSelected(false);
                }
            } 
        });

        //Restrictions and calculations of rb2 and rb5
        group3.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if(group3.getSelectedToggle() != null) {
                    rb3.setSelected(false);
                    rb6.setSelected(false);
                }
                if(group3.getSelectedToggle() == rb2) {
                    rb1.setSelected(false);
                }
                if(group3.getSelectedToggle() == rb5) {
                    rb4.setSelected(false);
                }
            } 
        });

        //Restrictions and calculations of rb3 and rb6
        group4.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if(group4.getSelectedToggle() != null) {
                    rb1.setSelected(false);
                    rb2.setSelected(false);
                    rb4.setSelected(false);
                    rb5.setSelected(false);
                }
            } 
        });
        


        //-----------Radio-Button + Label for type of car-------------------
        HBox carHbox= new HBox(rbcar, LBcar);
        carHbox.setSpacing(15);

        HBox jeepHbox= new HBox(rbjeep, LBjeep);
        jeepHbox.setSpacing(15);

        HBox motoHbox= new HBox(rbmoto, LBmoto);
        motoHbox.setSpacing(15);


        //-----------Radio-Button + Label for type of cleaning-------------------
        HBox cleanHbox0= new HBox(new Label(""));

        HBox cleanHbox1= new HBox(rb1, new Label("Πλύσιμο εξωτερικό"));
        cleanHbox1.setSpacing(15);
        
        HBox cleanHbox2= new HBox(rb2, new Label("Πλύσιμο εσωτερικό"));
        cleanHbox2.setSpacing(15);

        HBox cleanHbox3= new HBox(rb3, new Label("Πλύσιμο εξωτ.+εσωτ."));
        cleanHbox3.setSpacing(15);

        HBox cleanHbox4= new HBox(rb4, new Label("Πλύσιμο εξωτ.σπέσιαλ"));
        cleanHbox4.setSpacing(15);

        HBox cleanHbox5= new HBox(rb5, new Label("Πλύσιμο εσωτ.σπέσιαλ"));
        cleanHbox5.setSpacing(15);

        HBox cleanHbox6= new HBox(rb6, new Label("Πλύσιμο εξωτ. + εσωτ. σπέσιαλ"));
        cleanHbox6.setSpacing(15);

        HBox cleanHbox7= new HBox(rb7, new Label("Βιολογικός καθαρισμός εσωτ."));
        cleanHbox7.setSpacing(15);

        HBox cleanHbox8= new HBox(rb8, new Label("Κέρωμα-Γυάλισμα"));
        cleanHbox8.setSpacing(15);

        HBox cleanHbox9= new HBox(rb9, new Label("Καθαρισμός κινητήρα"));
        cleanHbox9.setSpacing(15);

        HBox cleanHbox10= new HBox(rb10, new Label("Πλύσιμο σασί"));
        cleanHbox10.setSpacing(15);





        //------------------------VBox-1----------------------------------
        vbox1 = new VBox(cleanHbox0, cleanHbox1, cleanHbox2, cleanHbox3, cleanHbox4, cleanHbox5, cleanHbox6, cleanHbox7, cleanHbox8, cleanHbox9, cleanHbox10);
        vbox1.setSpacing(15);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setStyle("-fx-font-size: 2em;");

        //------------------------VBox-2----------------------------------
        vbox2 = new VBox(
            carHbox, new Label("7"), new Label("6"), new Label("12"), 
            new Label("9"), new Label("8"), new Label("15"),
            new Label("80"), new Label("80"), new Label("20"), new Label("3")
        );
        vbox2.setSpacing(15);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setStyle("-fx-font-size: 2em;");

        //------------------------VBox-3----------------------------------
        vbox3 = new VBox(
            jeepHbox, new Label("8"), new Label("7"), new Label("14"), 
            new Label("10"), new Label("9"), new Label("17"),
            new Label("80"), new Label("90"), new Label("20"), new Label("3")
        );
        vbox3.setSpacing(15);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setStyle("-fx-font-size: 2em;");

        //------------------------VBox-4----------------------------------
        vbox4 = new VBox(
            motoHbox, new Label("6"), new Label("-"), new Label("-"), 
            new Label("8"), new Label("-"), new Label("-"),
            new Label("-"), new Label("40"), new Label("10"), new Label("-")
        );
        vbox4.setSpacing(15);
        vbox4.setAlignment(Pos.CENTER);
        vbox4.setStyle("-fx-font-size: 2em;");


        //--------------------------Check which radio buttons from vehicle are selected--------------------------
        group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                //-------------------Car-----------------------------
                if(group1.getSelectedToggle() == rbcar) {
                    typeOfVehicle = -1;
                }
                //-------------------Jeep-----------------------------
                else if(group1.getSelectedToggle() == rbjeep) {
                    typeOfVehicle = 0;
                }
                //-------------------Moto-----------------------------
                else {
                    typeOfVehicle = 1;
                }
            }
        });


        //=====================================Calculating total cost=====================================================
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb1.isSelected() && typeOfVehicle==-1) {
                    cost=cost+7;
                    rb1Flag=true;
                    SEND.setDisable(false);
                }
                else if (rb1.isSelected() && typeOfVehicle==0) {
                    cost=cost+8;
                    rb1Flag=true;
                    SEND.setDisable(false);
                }
                else if (rb1.isSelected() && typeOfVehicle==1) {
                    cost=cost+6;
                    rb1Flag=true;
                    SEND.setDisable(false);
                }
                //----------------------------------------
                if (rb2Flag==true && typeOfVehicle==-1) {
                    cost=cost-6;
                    rb2Flag=false;
                }
                else if (rb2Flag==true && typeOfVehicle==0) {
                    cost=cost-7;
                    rb2Flag=false;
                }
                //---------------------------------------
                if (rb3Flag==true && typeOfVehicle==-1) {
                    cost=cost-12;
                    rb3Flag=false;
                }
                else if (rb3Flag==true && typeOfVehicle==0) {
                    cost=cost-14;
                    rb3Flag=false;
                }
                //---------------------------------------
                if (rb4Flag==true && typeOfVehicle==-1) {
                    cost=cost-9;
                    rb4Flag=false;
                }
                else if (rb4Flag==true && typeOfVehicle==0) {
                    cost=cost-10;
                    rb4Flag=false;
                }
                else if (rb4Flag==true && typeOfVehicle==1) {
                    cost=cost-8;
                    rb4Flag=false;
                }
                //---------------------------------------
                // if (rb5Flag==true && typeOfVehicle==-1) {
                //     cost=cost-8;
                //     rb5Flag=false;
                // }
                // else if (rb5Flag==true && typeOfVehicle==0) {
                //     cost=cost-9;
                //     rb5Flag=false;
                // }
                //---------------------------------------
                if (rb6Flag==true && typeOfVehicle==-1) {
                    cost=cost-15;
                    rb6Flag=false;
                }
                else if (rb6Flag==true && typeOfVehicle==0) {
                    cost=cost-17;
                    rb6Flag=false;
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }
        };
        rb1.setOnAction(event1);

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb2.isSelected() && typeOfVehicle==-1) {
                    cost=cost+6;
                    rb2Flag=true;
                    SEND.setDisable(false);
                }
                else if (rb2.isSelected() && typeOfVehicle==0) {
                    cost=cost+7;
                    rb2Flag=true;
                    SEND.setDisable(false);
                }
                //-----------------------------------------
                if (rb1Flag==true && typeOfVehicle==-1) {
                    cost=cost-7;
                    rb1Flag=false;
                }
                else if (rb1Flag==true && typeOfVehicle==0) {
                    cost=cost-8;
                    rb1Flag=false;
                }
                else if (rb1Flag==true && typeOfVehicle==1) {
                    cost=cost-6;
                    rb1Flag=false;
                }
                //------------------------------------------
                if (rb3Flag==true && typeOfVehicle==-1) {
                    cost=cost-12;
                    rb3Flag=false;
                }
                else if (rb3Flag==true && typeOfVehicle==0) {
                    cost=cost-14;
                    rb3Flag=false;
                }
                //-----------------------------------
                // if (rb4Flag==true && typeOfVehicle==-1) {
                //     cost=cost-9;
                //     rb4Flag=false;
                // }
                // else if (rb4Flag==true && typeOfVehicle==0) {
                //     cost=cost-10;
                //     rb4Flag=false;
                // }
                // else if (rb4Flag==true && typeOfVehicle==1) {
                //     cost=cost-8;
                //     rb4Flag=false;
                // }
                //---------------------------------------
                if (rb5Flag==true && typeOfVehicle==-1) {
                    cost=cost-8;
                    rb5Flag=false;
                }
                else if (rb5Flag==true && typeOfVehicle==0) {
                    cost=cost-9;
                    rb5Flag=false;
                }
                //---------------------------------------
                if (rb6Flag==true && typeOfVehicle==-1) {
                    cost=cost-15;
                    rb6Flag=false;
                }
                else if (rb6Flag==true && typeOfVehicle==0) {
                    cost=cost-17;
                    rb6Flag=false;
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }
        };
        rb2.setOnAction(event2);

        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb3.isSelected() && typeOfVehicle==-1) {
                    cost=cost+12;
                    rb3Flag=true;
                    SEND.setDisable(false);
                }
                else if (rb3.isSelected() && typeOfVehicle==0) {
                    cost=cost+14;
                    rb3Flag=true;
                    SEND.setDisable(false);
                }
                //---------------------------------------------
                if (rb2Flag==true && typeOfVehicle==-1) {
                    cost=cost-6;
                    rb2Flag=false;
                }
                else if (rb2Flag==true && typeOfVehicle==0) {
                    cost=cost-7;
                    rb2Flag=false;
                }
                //---------------------------------------------
                if (rb1Flag==true && typeOfVehicle==-1) {
                    cost=cost-7;
                    rb1Flag=false;
                }
                else if (rb1Flag==true && typeOfVehicle==0) {
                    cost=cost-8;
                    rb1Flag=false;
                }
                else if (rb1Flag==true && typeOfVehicle==1) {
                    cost=cost-6;
                    rb1Flag=false;
                }
                //----------------------------------------------
                if (rb4Flag==true && typeOfVehicle==-1) {
                    cost=cost-9;
                    rb4Flag=false;
                }
                else if (rb4Flag==true && typeOfVehicle==0) {
                    cost=cost-10;
                    rb4Flag=false;
                }
                else if (rb4Flag==true && typeOfVehicle==1) {
                    cost=cost-8;
                    rb4Flag=false;
                }
                //---------------------------------------
                if (rb5Flag==true && typeOfVehicle==-1) {
                    cost=cost-8;
                    rb5Flag=false;
                }
                else if (rb5Flag==true && typeOfVehicle==0) {
                    cost=cost-9;
                    rb5Flag=false;
                }
                //---------------------------------------
                if (rb6Flag==true && typeOfVehicle==-1) {
                    cost=cost-15;
                    rb6Flag=false;
                }
                else if (rb6Flag==true && typeOfVehicle==0) {
                    cost=cost-17;
                    rb6Flag=false;
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }
        };
        rb3.setOnAction(event3);

        EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb4.isSelected() && typeOfVehicle==-1) {
                    cost=cost+9;
                    rb4Flag=true;
                    SEND.setDisable(false);
                }
                else if (rb4.isSelected() && typeOfVehicle==0) {
                    cost=cost+10;
                    rb4Flag=true;
                    SEND.setDisable(false);
                }
                else if (rb4.isSelected() && typeOfVehicle==1) {
                    cost=cost+8;
                    rb4Flag=true;
                    SEND.setDisable(false);
                }
                //-----------------------------------------------------
                // if (rb2Flag==true && typeOfVehicle==-1) {
                //     cost=cost-6;
                //     rb2Flag=false;
                // }
                // else if (rb2Flag==true && typeOfVehicle==0) {
                //     cost=cost-7;
                //     rb2Flag=false;
                // }
                //------------------------------------------------
                if (rb3Flag==true && typeOfVehicle==-1) {
                    cost=cost-12;
                    rb3Flag=false;
                }
                else if (rb3Flag==true && typeOfVehicle==0) {
                    cost=cost-14;
                    rb3Flag=false;
                }
                //-----------------------------------------------
                if (rb1Flag==true && typeOfVehicle==-1) {
                    cost=cost-7;
                    rb1Flag=false;
                }
                else if (rb1Flag==true && typeOfVehicle==0) {
                    cost=cost-8;
                    rb1Flag=false;
                }
                else if (rb1Flag==true && typeOfVehicle==1) {
                    cost=cost-6;
                    rb1Flag=false;
                }
                //---------------------------------------
                if (rb5Flag==true && typeOfVehicle==-1) {
                    cost=cost-8;
                    rb5Flag=false;
                }
                else if (rb5Flag==true && typeOfVehicle==0) {
                    cost=cost-9;
                    rb5Flag=false;
                }
                //---------------------------------------
                if (rb6Flag==true && typeOfVehicle==-1) {
                    cost=cost-15;
                    rb6Flag=false;
                }
                else if (rb6Flag==true && typeOfVehicle==0) {
                    cost=cost-17;
                    rb6Flag=false;
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }
        };
        rb4.setOnAction(event4);

        EventHandler<ActionEvent> event5 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb5.isSelected() && typeOfVehicle==-1) {
                    cost=cost+8;
                    rb5Flag=true;
                    SEND.setDisable(false);
                }
                else if (rb5.isSelected() && typeOfVehicle==0) {
                    cost=cost+9;
                    rb5Flag=true;
                    SEND.setDisable(false);
                }
                //-----------------------------------------------------
                if (rb2Flag==true && typeOfVehicle==-1) {
                    cost=cost-6;
                    rb2Flag=false;
                }
                else if (rb2Flag==true && typeOfVehicle==0) {
                    cost=cost-7;
                    rb2Flag=false;
                }
                //------------------------------------------------
                if (rb3Flag==true && typeOfVehicle==-1) {
                    cost=cost-12;
                    rb3Flag=false;
                }
                else if (rb3Flag==true && typeOfVehicle==0) {
                    cost=cost-14;
                    rb3Flag=false;
                }
                // //-----------------------------------------------
                // if (rb1Flag==true && typeOfVehicle==-1) {
                //     cost=cost-7;
                //     rb1Flag=false;
                // }
                // else if (rb1Flag==true && typeOfVehicle==0) {
                //     cost=cost-8;
                //     rb1Flag=false;
                // }
                // else if (rb1Flag==true && typeOfVehicle==1) {
                //     cost=cost-6;
                //     rb1Flag=false;
                // }
                //---------------------------------------
                if (rb4Flag==true && typeOfVehicle==-1) {
                    cost=cost-9;
                    rb4Flag=false;
                }
                else if (rb4Flag==true && typeOfVehicle==0) {
                    cost=cost-10;
                    rb4Flag=false;
                }
                else if (rb4Flag==true && typeOfVehicle==1) {
                    cost=cost-8;
                    rb4Flag=false;
                }
                //---------------------------------------
                if (rb6Flag==true && typeOfVehicle==-1) {
                    cost=cost-15;
                    rb6Flag=false;
                }
                else if (rb6Flag==true && typeOfVehicle==0) {
                    cost=cost-17;
                    rb6Flag=false;
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }
        };
        rb5.setOnAction(event5);
        
        EventHandler<ActionEvent> event6 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb6.isSelected() && typeOfVehicle==-1) {
                    cost=cost+15;
                    rb6Flag=true;
                    SEND.setDisable(false);
                }
                else if (rb6.isSelected() && typeOfVehicle==0) {
                    cost=cost+17;
                    rb6Flag=true;
                    SEND.setDisable(false);
                }
                //-----------------------------------------------------
                 if (rb2Flag==true && typeOfVehicle==-1) {
                    cost=cost-6;
                    rb2Flag=false;
                }
                else if (rb2Flag==true && typeOfVehicle==0) {
                    cost=cost-7;
                    rb2Flag=false;
                }
                //------------------------------------------------
                if (rb3Flag==true && typeOfVehicle==-1) {
                    cost=cost-12;
                    rb3Flag=false;
                }
                else if (rb3Flag==true && typeOfVehicle==0) {
                    cost=cost-14;
                    rb3Flag=false;
                }
                //-----------------------------------------------
                if (rb1Flag==true && typeOfVehicle==-1) {
                    cost=cost-7;
                    rb1Flag=false;
                }
                else if (rb1Flag==true && typeOfVehicle==0) {
                    cost=cost-8;
                    rb1Flag=false;
                }
                else if (rb1Flag==true && typeOfVehicle==1) {
                    cost=cost-6;
                    rb1Flag=false;
                }
                //---------------------------------------
                if (rb4Flag==true && typeOfVehicle==-1) {
                    cost=cost-9;
                    rb4Flag=false;
                }
                else if (rb4Flag==true && typeOfVehicle==0) {
                    cost=cost-10;
                    rb4Flag=false;
                }
                else if (rb4Flag==true && typeOfVehicle==1) {
                    cost=cost-8;
                    rb4Flag=false;
                }
                //---------------------------------------
                if (rb5Flag==true && typeOfVehicle==-1) {
                    cost=cost-8;
                    rb5Flag=false;
                }
                else if (rb5Flag==true && typeOfVehicle==0) {
                    cost=cost-9;
                    rb5Flag=false;
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }
        };
        rb6.setOnAction(event6);
        EventHandler<ActionEvent> event7 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb7.isSelected() && (typeOfVehicle==-1 || typeOfVehicle==0)) {
                    cost=cost+80;
                    SEND.setDisable(false);
                    rb7Flag=true;
                }
                else if(!rb7.isSelected()) {
                    cost=cost-80;
                    rb7Flag=false;
                }
                if (!rb7.isSelected() && rb1Flag==false && rb2Flag==false && rb3Flag==false && rb4Flag==false && rb5Flag==false && rb6Flag==false && rb7Flag==false && rb8Flag==false && rb9Flag==false && rb10Flag==false) {
                    SEND.setDisable(true);
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }
        };
        rb7.setOnAction(event7);

        EventHandler<ActionEvent> event8 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb8.isSelected() && typeOfVehicle==-1) {
                    cost=cost+80;
                    SEND.setDisable(false);
                    rb8Flag=true;
                }
                else if(rb8.isSelected() && typeOfVehicle==0) {
                    cost=cost+90;
                    SEND.setDisable(false);
                    rb8Flag=true;
                }
                else if(rb8.isSelected() && typeOfVehicle==1) {
                    cost=cost+40;
                    SEND.setDisable(false);
                    rb8Flag=true;
                }
                else if(!rb8.isSelected() && typeOfVehicle==-1) {
                    cost=cost-80;
                    rb8Flag=false;
                }
                else if(!rb8.isSelected() && typeOfVehicle==0) {
                    cost=cost-90;
                    rb8Flag=false;
                }
                else if(!rb8.isSelected() && typeOfVehicle==1) {
                    cost=cost-40;
                    rb8Flag=false;
                }
                if (!rb8.isSelected() && rb1Flag==false && rb2Flag==false && rb3Flag==false && rb4Flag==false && rb5Flag==false && rb6Flag==false && rb7Flag==false && rb8Flag==false && rb9Flag==false && rb10Flag==false) {
                    SEND.setDisable(true);
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }    
        };
        rb8.setOnAction(event8);

        EventHandler<ActionEvent> event9 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb9.isSelected() && (typeOfVehicle==-1 || typeOfVehicle==0)) {
                    cost=cost+20;
                    SEND.setDisable(false);
                    rb9Flag=true;
                }
                else if(rb9.isSelected() && typeOfVehicle==1) {
                    cost=cost+10;
                    SEND.setDisable(false);
                    rb9Flag=true;
                }
                else if(!rb9.isSelected() && (typeOfVehicle==-1 || typeOfVehicle==0)) {
                    cost=cost-20;
                    rb9Flag=false;
                }
                else if(!rb9.isSelected() && typeOfVehicle==1) {
                    cost=cost-10;
                    rb9Flag=false;
                }
                if (!rb9.isSelected() && rb1Flag==false && rb2Flag==false && rb3Flag==false && rb4Flag==false && rb5Flag==false && rb6Flag==false && rb7Flag==false && rb8Flag==false && rb9Flag==false && rb10Flag==false) {
                    SEND.setDisable(true);
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }    
        };
        rb9.setOnAction(event9);

        EventHandler<ActionEvent> event10 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (rb10.isSelected() && (typeOfVehicle==-1 || typeOfVehicle==0)) {
                    cost=cost+3;
                    SEND.setDisable(false);
                    rb10Flag=true;
                }
                else if(!rb10.isSelected()) {
                    cost=cost-3;
                    rb10Flag=false;
                }
                if (!rb10.isSelected() && rb1Flag==false && rb2Flag==false && rb3Flag==false && rb4Flag==false && rb5Flag==false && rb6Flag==false && rb7Flag==false && rb8Flag==false && rb9Flag==false && rb10Flag==false) {
                    SEND.setDisable(true);
                }
                costLabel.setText("Συνολικό κόστος: " + cost);
            }
        };
        rb10.setOnAction(event10);





        //------------------------Buttons----------------------------------
        SEND = new Button("ΑΠΟΣΤΟΛΗ");
        SEND.setStyle("-fx-font-size: 2em;");
        SEND.setDisable(true);
      
        CANCEL = new Button("ΑΚΥΡΩΣΗ");
        CANCEL.setStyle("-fx-font-size: 2em;");

        //--------------------Send data to server-------------------------
        SEND.setOnAction((e) -> {
            //Connecting with the server
            try(Socket clientSocket = new Socket("localhost", 7777)) {
                PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
                Date dateTime = new Date();
                toServer.println(dateTime + "|" + rn + "|" + cost + "|" + checkCode());
                stage2.close();
            }
            catch(IOException exception) {
                System.out.println(exception);
            }
        });

        //Prompting users with an ALERT confirmation if button CANCEL is pressed
        CANCEL.setOnAction((event) -> {
            Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            closeConfirmation.setTitle("Επιβεβαίωση");
            closeConfirmation.setHeaderText("Είστε σίγουρος ότι θέλετε να ακυρώσετε την επιλογή σας;");
            closeConfirmation.setContentText("");
            ButtonType YES = new ButtonType("ΝΑΙ", ButtonData.YES);
            ButtonType NO = new ButtonType("ΟΧΙ", ButtonData.NO);  
            closeConfirmation.getButtonTypes().setAll(YES, NO);
            closeConfirmation.initModality(Modality.WINDOW_MODAL);
            closeConfirmation.initOwner(stage2);
            Optional<ButtonType> result = closeConfirmation.showAndWait();
            if (result.get() == NO) {
                closeConfirmation.close();
            }
            if (result.get() == YES) {
                stage2.close();
            }
        });


        //------------------------Three Hboxes----------------------------------
        //For the label
        hboxTop = new HBox(costLabel);
        hboxTop.setAlignment(Pos.CENTER);
        hboxTop.setStyle("-fx-font-size: 2em;");

        //4 Vboxes for the 4 columns 
        hboxMiddle = new HBox(vbox1, vbox2, vbox3, vbox4);
        hboxMiddle.setSpacing(30);
        hboxMiddle.setAlignment(Pos.CENTER);

        //For the two buttons
        hboxBottom = new HBox(SEND, CANCEL);
        hboxBottom.setSpacing(50);
        hboxBottom.setAlignment(Pos.CENTER);

        //For the 3 Hboxes
        vbox = new VBox(hboxTop, hboxMiddle, hboxBottom);
        vbox.setSpacing(50);
        vbox.setAlignment(Pos.CENTER);


        Scene scene2 = new Scene(vbox, 1024, 768);
        stage2.setMinWidth(1024);
        stage2.setMinHeight(768);
        stage2.setMaxWidth(1920);
        stage2.setMaxHeight(1080);
        stage2.setScene(scene2);
        stage2.setTitle("Services");
        stage2.showAndWait();
    }

    //Calculating the correct code using the COMMON STRUCTURE
    public String checkCode() {

        String finalBigCode="";

        Structure treeMap = new Structure();

        if(rb1Flag==true && typeOfVehicle==-1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτερικό 7");
        }
        if(rb1Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτερικό 8");
        }
        if(rb1Flag==true && typeOfVehicle==1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτερικό 6");
        }
        //--------------------------
        if(rb2Flag==true && typeOfVehicle==-1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εσωτερικό 6");
        }
        if(rb2Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εσωτερικό 7");
        }
        //------------------------------
        if(rb3Flag==true && typeOfVehicle==-1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτ.+εσωτ. 12");
        }
        if(rb3Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτ.+εσωτ. 14");
        }
        //-------------------------------
        if(rb4Flag==true && typeOfVehicle==-1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτ. σπέσιαλ 9");
        }
        if(rb4Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτ. σπέσιαλ 10");
        }
        if(rb4Flag==true && typeOfVehicle==1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτ. σπέσιαλ 8");
        }
        //---------------------------------------
        if(rb5Flag==true && typeOfVehicle==-1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εσωτ. σπέσιαλ 8");
        }
        if(rb5Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εσωτ. σπέσιαλ 9");
        }
        //----------------------------------------
        if(rb6Flag==true && typeOfVehicle==-1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτ. + εσωτ. σπέσιαλ 15");
        }
        if(rb6Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο εξωτ. + εσωτ. σπέσιαλ 17");
        }
        //--------------------Biologikos--------------------
        if(rb7Flag==true && typeOfVehicle==-1 || rb7Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Βιολογικός καθαρισμός εσωτ. 80");
        }
        // if(rb7Flag==true && typeOfVehicle==0) {
        //     finalBigCode=finalBigCode+","+treeMap.getCode("Βιολογικός καθαρισμός εσωτ. 80");
        // }
        //----------------------------------------
        if(rb8Flag==true && typeOfVehicle==-1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Κέρωμα‐Γυάλισμα 80");
        }
        if(rb8Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Κέρωμα‐Γυάλισμα 90");
        }
        if(rb8Flag==true && typeOfVehicle==1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Κέρωμα‐Γυάλισμα 40");
        }
        //-------------------Katharismos Kinitira--------------------
        if(rb9Flag==true && typeOfVehicle==-1 || rb9Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Καθαρισμός κινητήρα 20");
        }
        // if(rb9Flag==true && typeOfVehicle==0) {
        //     finalBigCode=finalBigCode+","+treeMap.getCode("Καθαρισμός κινητήρα 20");
        // }
        if(rb9Flag==true && typeOfVehicle==1) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Καθαρισμός κινητήρα 10");
        }
        //------------------Plysimo sasi--------------------
        if(rb10Flag==true && typeOfVehicle==-1 || rb10Flag==true && typeOfVehicle==0) {
            finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο σασί 3");
        }
        // if(rb10Flag==true && typeOfVehicle==0) {
        //     finalBigCode=finalBigCode+","+treeMap.getCode("Πλύσιμο σασί 3");
        // }

        //Cut the First comma (,) from string
        finalBigCode = finalBigCode.substring(1, finalBigCode.length());

        return finalBigCode;
    }
}
