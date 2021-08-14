package gr.uop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class App extends Application {

    private Button okButton, cancelButton, helpButton;
    private VBox  ButtonsVBox;
    private TitledPane TPaneGS, TPaneGD;
    private Label Horizontal, Vertical, status;
    private TextField htf, vtf;
    private CheckBox SnapToGrid, ShowGrid;
    private GridPane GridDimensions, GridSettings;

    @Override
    public void start(Stage stage) {

        Horizontal = new Label("Horizontal:");          //
        htf = new TextField();                          // Label and TextField for HORIZONTAL input
        htf.setPrefWidth(80);                           //

        Vertical = new Label("     Vertical:");         //
        vtf = new TextField();                          // Label and TextField for VERTICAL input
        vtf.setPrefWidth(80);                           //


        GridDimensions = new GridPane();                // Grid for the GRID DIMENSIONS
        GridDimensions.addRow(0,Horizontal,htf);
        GridDimensions.addRow(1,Vertical,vtf);
        GridDimensions.setVgap(10);
        GridDimensions.setHgap(5);


        SnapToGrid = new CheckBox("Snap to Grid");
        ShowGrid = new CheckBox("Show Grid");

        okButton = new Button("OK");
        cancelButton = new Button("Cancel");
        helpButton = new Button("Help");


        GridSettings = new GridPane();                      // Grid for the GRID SETTINGS              
        GridSettings.addRow(0,SnapToGrid);
        GridSettings.addRow(1,ShowGrid);  
        GridSettings.setVgap(10);
        GridSettings.setHgap(5);


        TPaneGS = new TitledPane("Grid Settings", GridSettings);        //TitlePane for GRID SETTINGS
        TPaneGS.setCollapsible(false);

        TPaneGD = new TitledPane("Grid Dimensions", GridDimensions);     //TitlePane for GRID DIMENSIONS
        TPaneGD.setCollapsible(false);


        ButtonsVBox = new VBox(okButton,cancelButton,helpButton);       //Vbox for buttons
        ButtonsVBox.setAlignment(Pos.TOP_LEFT);
        ButtonsVBox.setPrefWidth(60);

        okButton.setMinWidth(ButtonsVBox.getPrefWidth());
        cancelButton.setMinWidth(ButtonsVBox.getPrefWidth());
        helpButton.setMinWidth(ButtonsVBox.getPrefWidth());
        
        ButtonsVBox.setSpacing(10);

        okButton.setMaxWidth(40);
        cancelButton.setMaxWidth(40);
        helpButton.setMaxWidth(40);

        HBox hbox = new HBox(TPaneGS,TPaneGD,ButtonsVBox);                  //HBox for GRID SETTINGS,GRID DIMENSIONS and Buttons
        hbox.setSpacing(40);
        HBox.setHgrow(TPaneGS, Priority.ALWAYS);
        HBox.setHgrow(TPaneGD, Priority.ALWAYS);
        HBox.setHgrow(ButtonsVBox, Priority.ALWAYS);
        hbox.setPadding(new Insets(10, 40, 0, 40));
        hbox.setStyle("-fx-border-color: #C0C0C0; -fx-border-width: 0 0 1 0");      //Color for the buttom border of Hbox

        status = new Label("Status");
        status.setPadding(new Insets(0, 0, 0, 5));
        status.setStyle("-fx-font-size: 14px");


        BorderPane bpane = new BorderPane();                    //BorderPane for HBox and STATUS Label
        bpane.setBottom(status);
        bpane.setCenter(hbox);
       
        

        var scene = new Scene(bpane, 500, 180);                 
        stage.setMinWidth(520);
        stage.setMinHeight(200);
        stage.setScene(scene);
        stage.setTitle("Settings");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}