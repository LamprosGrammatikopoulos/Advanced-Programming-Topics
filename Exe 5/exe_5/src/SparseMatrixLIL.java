import java.util.ArrayList;
import java.util.Collections;


public class SparseMatrixLIL implements SparseMatrix {
    
    private int rowsNumber;
    private int colsNumber;
    ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();

    public SparseMatrixLIL(int rows, int cols) {
        this.colsNumber=cols;
        this.rowsNumber=rows;
    }

    public int rowCount() {
       return rows.size();
    }

    public int colCount() {
        int maxCols = 0;
        for(int i=0; i<rows.size(); i++) {
            if(rows.get(i).size() >= maxCols) {
                maxCols = rows.get(i).size();
            }
        }
        return maxCols;
    }

    @Override
    public double get(int r, int c) {
        if(r > this.rowsNumber) { 
            throw new IndexOutOfBoundsException("Given row is wrong.");
        }
        else if (c > this.colsNumber) {
            throw new IndexOutOfBoundsException("Given col is wrong.");    
        }
        else {
            if(rows.get(r).size() == 0) {
                return 0;
            }
            else if(rows.get(r).size() >= c) {
                String[] str = rows.get(r).get(c).split(",");
                double num = Double.parseDouble(str[1]);
                return num;
            }
            else {
                return 0;
            }
        }
    }

    @Override
    public void set(int r, int c, double element) {
        if(r > this.rowsNumber) { 
            throw new IndexOutOfBoundsException("Given row is wrong.");
        }
        else if (c > this.colsNumber) {
            throw new IndexOutOfBoundsException("Given col is wrong.");    
        }
        else {
            if(Math.abs(element) < Math.pow(10,-5)) {
                zero(r, c);
            }
            else {
                String tmp = c + "," + element;
                ArrayList<String> arr = new ArrayList<String>();
                if(rows.size() == 0 || rows.size() == r || rows.size()-r < 0) {
                    if( rows.size()-r < 0) {
                        for(int i=0; i<r-rows.size(); i++) {
                            ArrayList<String> arrTmp = new ArrayList<String>();
                            rows.add(arrTmp);
                        }
                        arr.add(tmp);
                        Collections.sort(arr);
                        rows.add(arr);
                    }
                    else {
                        arr.add(tmp);
                        Collections.sort(arr);
                        rows.add(arr);
                    }
                }
                else {
                    for(int i=0;i<rows.get(r).size();i++){
                        String[] str = rows.get(r).get(i).split(",");
                        if(str[0].equals(""+c)) {
                            rows.get(r).remove(i);
                            break;
                        }
                        else {
                            arr.add( rows.get(r).get(i));
                        }
                    }
                    arr.add(tmp);
                    Collections.sort(arr);
                    rows.set(r,arr);
                }           
            }
        }
    }

    public void zero(int r, int c) {
        if(r > this.rowsNumber) { 
            throw new IndexOutOfBoundsException("Given row is wrong.");
        }
        else if (c > this.colsNumber) {
            throw new IndexOutOfBoundsException("Given col is wrong.");    
        }
        else {
            rows.get(r).remove(c);
        }
    }

    public void clear() {
        for(int i=rows.size()-1; i>=0; i--) {
            if(rows.get(i).size() > 0 ) {
                for(int j=rows.get(i).size()-1; j>=0; j--) {
                    rows.get(i).remove(j);
                }
            }
            rows.remove(i);
        }
    }

    @Override
    public boolean isEmpty() {
        for(int i=0; i<rows.size(); i++) {
            if(rows.get(i).size() != 0) { 
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        String s = "";
        for(int i=0; i<rows.size(); i++) {
            if(rows.get(i).size() > 0 ) {
                for(int j=0; j<rows.get(i).size(); j++) {
                    String str[] = rows.get(i).get(j).split(",");
                    s = s + " " + "(" + i + ", " + str[0] + ": " + str[1] + ")";
                }
            }
        }
        return "[" + s + " ]";
    }

    public void print() {
        for(int i=0;i<rows.size();i++) {
            for(int j=0;j<rows.get(i).size();j++) {
                if(j != rows.get(i).size()-1) {
                    System.out.print(rows.get(i).get(j)+"--");
                }
                else {
                    System.out.print(rows.get(i).get(j));
                }
            }
            System.out.println();
        }
    }
}