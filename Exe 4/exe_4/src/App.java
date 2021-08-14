import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        System.out.println("Give an integer:");
        Scanner scanner1 = new Scanner(System.in);
        String input1 = scanner1.nextLine();
        BigInt number1 = new BigInt(input1.toString());

        System.out.println("Give another integer:");
        Scanner scanner2 = new Scanner(System.in);
        String input2 = scanner2.nextLine();
        BigInt number2 = new BigInt(input2.toString());

        //----------------------Compare two numbers---------------
        System.out.println("----------Compare------------");
        if (number1.compareTo(number2) == 1) {
            System.out.println("The first number is bigger.");
        }
        else if (number1.compareTo(number2) == -1) {
            System.out.println("The second number is bigger.");
        }
        else {
            System.out.println("The numbers are equal.");
        }

        //----------------------Add two numbers---------------
        System.out.println("----------Add------------");
        BigInt tmp = BigInt.add(number1, number2);
        tmp.MirrorPrint();

        //----------------------Take the new numbers and sort them---------------
        System.out.println("----------Sort------------");
        ArrayList<String> ListOfNumbers = new ArrayList<String>();
        System.out.println("Give some integers:");
        while (true) {
            Scanner scanner3 = new Scanner(System.in);
            String input3 = scanner3.nextLine();
            if (!input3.matches("[0-9]+")) {                //Expect only numbers in the user input
                break;
            }
            if (!ListOfNumbers.contains(input3)) {          //If given number already exists don't store it
                ListOfNumbers.add(input3);
            }
        }
        sortLargeNumbers(ListOfNumbers);
        System.out.println("The sorted numbers are given below:");
        System.out.println(ListOfNumbers);

        //----------------------Count the digits of every number and print the sum of numbers with the same amount of digits---------------
        System.out.println("----------Count digits------------");
        Map<Integer, Integer> lengthCount = new LinkedHashMap<>();
        for (int i=0; i<ListOfNumbers.size(); i++) {
            Integer numberLength = ListOfNumbers.get(i).length();
            if (lengthCount.containsKey(numberLength) == false) {
                lengthCount.put(numberLength, 1);
            }
            else {
                int count = lengthCount.get(numberLength);
                lengthCount.put(numberLength, count+1);
            }
        }
        Set<Integer> keys = lengthCount.keySet();
        for (Integer k : keys) {
            System.out.println(k + " digits: " + lengthCount.get(k));     //Change the way the output is printed out
        }
    }

    static void sortLargeNumbers(ArrayList<String> array) {               //Method for sorting large numbers
        Collections.sort(array, (left, right) -> {
            if (left.length() != right.length()){
                return left.length() - right.length();
            }
            return left.compareTo(right);
        });
    }
}