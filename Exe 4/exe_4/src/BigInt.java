import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class BigInt implements Comparable<BigInt> {
    private LinkedList<Character> list = new LinkedList<Character>();

    public BigInt(String numberStr) {               //-----------1------------- creation of the linked list
        if(numberStr.equals("")){
            throw new InputMismatchException();
        }
        for (int i=numberStr.length()-1; i>=0; i--){
            if (Character.isDigit(numberStr.charAt(i))) {
                list.add(numberStr.charAt(i));
            }
            else {
                throw new InputMismatchException();
            }
        }
    }

    public int countDigits() {                             //----------2------------- number of digits 
        return list.size();
    }

    @Override
    public String toString() {                             //----------3------------- textually represents an object
        return list.toString();
    }

    public boolean equals(BigInt secondStr) {              //----------4------------- check if the numbers are the same or not
        if (list.size() == secondStr.list.size()) {
            for (int i=0; i<list.size(); i++) {
                if (!list.get(i).equals(secondStr.list.get(i))) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

    @Override                                              //----------5------------- comparing the two numbers
    public int compareTo(BigInt secondNumber)
    {
        if (list.size() > secondNumber.list.size()) {               //if first number has more digits
            return 1;
        }
        else if (list.size() < secondNumber.list.size()) {          //if second number has more digits
            return -1;
        }
        else {
            for (int i=list.size()-1; i>=0; i--) {
                if (Character.getNumericValue(list.get(i)) > Character.getNumericValue(secondNumber.list.get(i))) {          //if a digit of first number is bigger that a digit of second number
                    return 1;
                }
                else if (Character.getNumericValue(list.get(i)) < Character.getNumericValue(secondNumber.list.get(i))) {     //if a digit of first number is smaller that a digit of second number
                    return -1;
                }
            }
            return 0;                                               //otherwise the numbers are the same
        }
    }

    public static BigInt add(BigInt a, BigInt b) {                //----------6------------- adding the two numbers

        String str1="",str2="";
        for(int i=a.list.size()-1;i>=0;i--){
            str1=str1+a.list.get(i);
        }
        for(int i=b.list.size()-1;i>=0;i--){
            str2=str2+b.list.get(i);
        }

        if (str1.length() > str2.length()){
            String t = str1;
            str1 = str2;
            str2 = t;
        }
 
        String str = "";
 
        // Calculate length of both String
        int n1 = str1.length(), n2 = str2.length();
    
        // Reverse both of Strings
        str1=new StringBuilder(str1).reverse().toString();
        str2=new StringBuilder(str2).reverse().toString();
 
        int carry = 0;
        for (int i = 0; i < n1; i++)
        {
            // Do school mathematics, compute sum of
            // current digits and carry
            int sum = ((int)(str1.charAt(i) - '0') +
                        (int)(str2.charAt(i) - '0') + carry);
            str += (char)(sum % 10 + '0');
    
            // Calculate carry for next step
            carry = sum / 10;
        }
    
        // Add remaining digits of larger number
        for (int i = n1; i < n2; i++)
        {
            int sum = ((int)(str2.charAt(i) - '0') + carry);
            str += (char)(sum % 10 + '0');
            carry = sum / 10;
        }
    
        // Add remaining carry
        if (carry > 0)
            str += (char)(carry + '0');
    
        // reverse resultant String
        str = new StringBuilder(str).reverse().toString();
    
        BigInt tmp = new BigInt(str);
        return tmp;
      
    }

    //Reverse print
    public void MirrorPrint() {
        for (int i=list.size()-1; i>=0; i--) {
            System.out.print(list.get(i));
        }
        System.out.println();
    }
}