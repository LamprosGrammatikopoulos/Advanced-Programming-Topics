import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Exercise1 {
    public static void main(String[] args) throws FileNotFoundException {
        try
        {
            File inpf = new File("C:\\Users\\lambr\\Documents\\Exercise1\\src\\inputfile.txt");
            Scanner s = new Scanner(inpf);
            ArrayList<String> arr = new ArrayList<String>();
            while (s.hasNextLine()){
                arr.add(s.next());
            }
            s.close();

            for (int i=0; i<arr.size(); i++) {
                System.out.println(arr.get(i));
            }
            System.out.println();


            int count = 0;
            ArrayList<String> words = new ArrayList<String>();
            ArrayList<Integer> times = new ArrayList<Integer>();
            for (int i=0; i<arr.size(); i++) {
                if (!words.contains(arr.get(i))) {
                    words.add(arr.get(i));
                    times.add(0);
                  
                    for (int j=0; j<arr.size(); j++) {
                        if (words.get(count).equals(arr.get(j))) {
                            times.set(count,times.get(count)+1);
                        }
                    }
                    count++;
                }
            }


            System.out.println();
            for (int i=0; i<words.size(); i++) {
                System.out.println(words.get(i) + " " + times.get(i));
            }
            System.out.println();


            for (int i=0; i<words.size(); i++) {
                for (int j=0; j<words.size()-i-1;j++) {
                    if(words.get(j).compareTo(words.get(j+1)) > 0 ){
                        String tmp = words.get(j);
                        words.set(j,words.get(j+1));
                        words.set(j+1,tmp);
                        int timesTmp=times.get(j+1);
                        times.set(j,times.get(j+1));
                        times.set(j+1,timesTmp);
                    }
                }
            }

            
            System.out.println();
            for (int i=0; i<words.size(); i++) {
                System.out.println(words.get(i) + " " + times.get(i));
            }
            System.out.println();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}