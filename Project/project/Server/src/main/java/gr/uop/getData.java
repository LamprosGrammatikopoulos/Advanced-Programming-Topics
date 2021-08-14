package gr.uop;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class EchoServerThread extends Thread {
    
    private Socket clientSocket;
    private static ObservableList<data> dataList = FXCollections.observableArrayList();
    private static ArrayList<String> array = new ArrayList<String>();

    //Socket from Client
    public EchoServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    //Reading the data from Client
    public void run() {
        try(Scanner fromClient = new Scanner(clientSocket.getInputStream());                
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(),true)){
            do {
                String line = fromClient.nextLine();
                String[] tmp = line.split("\\|");           //Splitting the String line from client

                String DateTime = tmp[0];           //Date time
                String rn = tmp[1];                 //Number Plate
                String cost = tmp[2];               //Cost
                String Codes = tmp[3];              //Codes of services

                String[] code = Codes.split("\\,");          //Splitting the codes in ","

                for(int i=0;i<code.length;i++) {            //Saving codes 
                    array.add(code[i]);

                }

                String Services = returnValues();           //Translating Codes -> Services
               
                dataList.add(new data(DateTime, rn, Integer.parseInt(cost), Services));     //Add in dataList(Obs, List) new data
                
                Services = Services.replaceAll("\n", ",");                  
                Services = Services.substring(0, Services.length()-1);

                saveData.writeData(DateTime, Services, rn, Integer.parseInt(cost));         //Add data in File 

                array.clear();
            } while(fromClient.hasNextLine());
        }
        catch(IOException e) {
            System.out.println(e);
        }
    } 

    //Returning the Obs. List to display the table
    public static ObservableList<data> returnList(){
        return dataList;
    }

    //Getters for each field
    public static String getArrivalDateTime(Object selectedLine){
        for(data d : dataList) {
            if(d.equals(selectedLine)){
                return ""+d.getArrivalDateTime();
            }
        }
        return "";
    }
    public static String getRegistrationNumber(Object selectedLine){
        for(data d : dataList) {
            if(d.equals(selectedLine)){
                return ""+d.getRegistrationNumber();
            }
        }
        return "";
    }
    public static int getCost(Object selectedLine){
        for(data d : dataList) {
            if(d.equals(selectedLine)){
                return d.getCost();
            }
        }
        return 0;
    }
    public static String getValue(Object selectedLine){
        for(data d : dataList) {
            if(d.equals(selectedLine)){
                return d.getValue();
            }
        }
        return "";
    }

    //Deleting from the Obs. List
    public static void deleteFromList(Object selectedLine) {
        dataList.remove(selectedLine);
    }

    //Translation of each code into corresponding service and price
    public static String returnValues() {
        String values = "";
        Structure structure = new Structure();
        for(int i= 0;i<array.size();i++) {
            values = values + structure.getValue(array.get(i)) + "\n";
        }
        return values;
    }


}
//Connecting with the Client
public class getData implements Runnable {
    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(7777)) {
            while(true) {
                Socket clientSocket = serverSocket.accept();
                EchoServerThread serverThread = new EchoServerThread(clientSocket);
                serverThread.start();
            }
        }
        catch(IOException exception) {
            System.out.println(exception);
        }
        
    }
}
