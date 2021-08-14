import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) {
        //-------------------------SparseMatrixLIL-----------------------------
        System.out.println("-------------------------SparseMatrixLIL----------------------------");
        System.out.println("Fill LIL matrix");
        SparseMatrixLIL SML = new SparseMatrixLIL(4,6);
        SML.set(0,2,3);
        SML.set(0,3,7);
        SML.set(0,3,8);     //extra to check if the previous number in this position gets replaced 
        SML.set(1,4,2);
        SML.set(1,0,1);
        SML.set(3,3,5);
        SML.set(1,5,3);
        SML.set(3,1,9);
        SML.print();

        System.out.println();
        System.out.println("LIL matrix size");
        System.out.println("Number of rows: " + SML.rowCount());
        System.out.println("Number of cols: " + SML.colCount());

        System.out.println();
        System.out.println("Print contents with toString");
        System.out.println(SML.toString());

        System.out.println();
        System.out.println("Get elements");
        System.out.println(SML.get(0,1));
        System.out.println(SML.get(2,0));
        System.out.println(SML.get(1,5));
        System.out.println(SML.get(1,2));

        System.out.println();
        System.out.println("Remove elements");
        SML.zero(0,1);
        SML.zero(1,2);
        SML.print();

        System.out.println();
        System.out.println("Check if matrix is empty");
        System.out.println(SML.isEmpty());
        System.out.println();

        System.out.println();
        System.out.println("Clear matrix");
        SML.clear();
        SML.print();
        System.out.println();

        System.out.println();
        System.out.println("Check if matrix is empty");
        System.out.println(SML.isEmpty());
        System.out.println();



        //-------------------------SparseMatrixDOK-----------------------------
        System.out.println("-------------------------SparseMatrixDOK----------------------------");
        System.out.println("Fill DOK matrix");
        SparseMatrixDOK SMD = new SparseMatrixDOK(4,6);
        SMD.set(0,2,3);
        SMD.set(0,3,7);
        SMD.set(0,3,8);     //extra to check if the previous number in this position gets replaced 
        SMD.set(1,4,2);
        SMD.set(1,0,1);
        SMD.set(3,3,5);
        SMD.set(1,5,3);
        SMD.set(3,1,9);
        SMD.print();

        System.out.println();
        System.out.println("DOK matrix size");
        System.out.println("Number of rows: " + SMD.rowCount());
        System.out.println("Number of cols: " + SMD.colCount());

        System.out.println();
        System.out.println("Print contents with toString");
        System.out.println(SMD.toString());

        System.out.println();
        System.out.println("Get elements");
        System.out.println(SMD.get(0,1));
        System.out.println(SMD.get(2,0));
        System.out.println(SMD.get(1,5));
        System.out.println(SMD.get(1,2));

        System.out.println();
        System.out.println("Check if matrix is empty");
        System.out.println(SMD.isEmpty());
        System.out.println();

        System.out.println();
        System.out.println("Remove elements");
        SMD.zero(0,2);
        SMD.zero(1,5);
        SMD.print();

        System.out.println();
        System.out.println("Clear matrix");
        SMD.clear();
        SMD.print();
        System.out.println();

        System.out.println();
        System.out.println("Check if matrix is empty");
        System.out.println(SMD.isEmpty());
        System.out.println();
    


        //-------------------------Add two sparse matrices-----------------------------
        System.out.println("-------------------------Add two sparse matrices----------------------------");
        SparseMatrixLIL SML2 = new SparseMatrixLIL(4,6);
        SML2.set(0,2,3);
        SML2.print();
        System.out.println("-------------------------------");
        SparseMatrixDOK SMD2 = new SparseMatrixDOK(4,6);
        SMD2.set(0,2,3);
        SMD2.print();
        System.out.println("-------------------------------");
        System.out.println();
        SparseMatrices.add(SML2, SMD2);
        System.out.println();
    }
}