package gr.uop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class saveData {

    saveData() {
        try {
            // Get the file
            File bookFile = new File("book.txt");
            // Create new file if it does not exist
            if (bookFile.createNewFile()) {
                System.out.println("File created");
            }
            else {
                System.out.println("File already exists");
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    //Write Client incoming data to the file each time!
    public static void writeData(String DateTime, String Services, String rn, int cost) {
        try (RandomAccessFile raf = new RandomAccessFile("book.txt","rw")) {
            raf.seek(raf.length());
            String txt = DateTime + "|" + Services  + "|" + rn + "|" + cost + "\n";
            raf.write(txt.getBytes(StandardCharsets.UTF_8));
            raf.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    //Add extra data to a specific line in file whenever the user presses the button "ΠΛΗΡΩΜΗ"
    public static void addData(String DateTime, String Services, String rn, int cost) {
        Services = Services.substring(0, Services.length()-1);

        try (RandomAccessFile raf = new RandomAccessFile("book.txt","rw")) {
            ArrayList<String> belowLines = new ArrayList<String>();                                         //ArrayList for BELOWS lines
            long current = 0;
            long line = 0;                      //Line of element
            long lineCounter = 0;                   //How many lines i have in file
            //===========================================================================================
            long counter = 0;   
            while (counter < raf.length()) {          //Counts the number of lines
                raf.readLine();                         //<<<--------Den xreiazetai apla gia na kynitai o pointer
                counter = raf.getFilePointer();
                lineCounter++;
            }
            counter=0;
            raf.seek(0);
            while (counter < raf.length()) {            //Counts the LINE of the element                                       
                String inputLine = raf.readLine();
                inputLine = new String(inputLine.getBytes("ISO-8859-1"), "UTF-8");
                String tmp = DateTime + "|" + Services + "|" + rn  + "|" + cost;
                line++;
                if (inputLine.equals(tmp)) {
                   break;
                }
                counter = raf.getFilePointer();
            }
            //===========================================================================================
            current=0;
            for(long i=line; i<lineCounter; i++){           //Saving below(of element) lines
                String arrayCell = raf.readLine(); 
                arrayCell = new String(arrayCell.getBytes("ISO-8859-1"), "UTF-8");
                belowLines.add(arrayCell);
            }
            raf.seek(0);
            while (current < raf.length()) {            //From the start of the file...

                String inputLine = raf.readLine(); 
                inputLine = new String(inputLine.getBytes("ISO-8859-1"), "UTF-8");
                String tmp = DateTime + "|" + Services + "|" + rn  + "|" + cost;

                if (inputLine.equals(tmp)) {

                    Date payDateTime = new Date();
                    raf.seek(raf.getFilePointer()-1);                               //For \n
                    String str = "|---|" + payDateTime + "|" + cost+"\n";               //Adding the extra infos          
                    raf.write(str.getBytes(StandardCharsets.UTF_8));

                    for(int i=0;i<belowLines.size();i++) {                              //Adding belows line of element
                        raf.write(((belowLines.get(i)+"\n").getBytes(StandardCharsets.UTF_8)));
                    }
                    break;
                }
                current = raf.getFilePointer();
            }
            raf.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

    //Remove a record from file whenever the user presses the button "ΑΚΥΡΩΣΗ"
    public static void delete(String DateTime, String Services, String rn, int cost) {
        Services = Services.substring(0, Services.length()-1);
        try (RandomAccessFile raf = new RandomAccessFile("book.txt","rw")) {
            ArrayList<String> belowLines = new ArrayList<String>();                                         //ArrayList for BELOWS lines
            long current = 0;
            long line = 0;                      //Line of element
            long lineCounter = 0;                   //How many lines i have in file
            long upperLinesCounter = 0;         //Characters above the element 
            //===========================================================================================
            long counter = 0;
            while (counter < raf.length()) {           //Counts the number of lines
                String inputLine = raf.readLine();            //<<<--------Den xreiazetai apla gia na kynitai o pointer
                counter = raf.getFilePointer();
                lineCounter++;
            }
            counter=0;
            raf.seek(0);
            while (counter < raf.length()) {             //Counts the LINE of the element               
                String inputLine = raf.readLine(); 
                inputLine = new String(inputLine.getBytes("ISO-8859-1"), "UTF-8");
                String tmp = DateTime + "|" + Services + "|" + rn  + "|" + cost;
                line++;
                if (inputLine.equals(tmp)) {
                    break;
                }
                upperLinesCounter = raf.getFilePointer();   //Counts characters above the element 
                counter = raf.getFilePointer();
            }
            //===========================================================================================
            current=0;
            for(long i=line; i<lineCounter; i++){     ///Saving below(of element) lines
                String arrayCell = raf.readLine(); 
                arrayCell = new String(arrayCell.getBytes("ISO-8859-1"), "UTF-8");
                belowLines.add(arrayCell);
            }
            raf.seek(0);
            while (current < raf.length()) {            //From the start of the file...
                if(line==1 && lineCounter==1){
                    raf.setLength(0);
                    break;
                }
                String inputLine = raf.readLine(); 
                inputLine = new String(inputLine.getBytes("ISO-8859-1"), "UTF-8");
                String tmp = DateTime + "|" + Services + "|" + rn  + "|" + cost;

                if (inputLine.equals(tmp)) {    //If we found the element

                    raf.seek(upperLinesCounter);                     //Going the pointer to the START of the element
                    for(int i=0;i<belowLines.size();i++) {                              //Adding belows lines of element
                        raf.write(((belowLines.get(i) + "\n").getBytes(StandardCharsets.UTF_8)));
                    }
                    raf.setLength(raf.getFilePointer());            //Delete extra lines to the end of filePointer
                    break;
                }
                current = raf.getFilePointer();
            }
            raf.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
