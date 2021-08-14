import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class SparseMatrixDOK implements SparseMatrix {

    private int rowsNumber;
    private int colsNumber;

    Map<String, Double> DOK = new TreeMap<>();

    public SparseMatrixDOK(int rows, int cols) {
        this.colsNumber=cols;
        this.rowsNumber=rows;
    }

    public int rowCount() {
        Set<Map.Entry<String, Double>> entries = DOK.entrySet();
        int maxRows = 0;
        for (var e : entries) {
            String[] str = e.getKey().split(",");
            if(Integer.parseInt(str[0]) > maxRows) {
                maxRows = Integer.parseInt(str[0]);
            }
        } 
        return maxRows;
    }

    public int colCount() {
        Set<Map.Entry<String, Double>> entries = DOK.entrySet();
        int maxCols = 0;
        for (var e : entries) {
            String[] str = e.getKey().split(",");
            if(Integer.parseInt(str[1]) > maxCols) {
                maxCols = Integer.parseInt(str[1]);
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
            if(DOK.containsKey(r+","+c) == true) {
                return DOK.get(r+","+c);
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
                if(DOK.containsKey(r+","+c) == false) {
                    DOK.put(r+","+c,element);
                    
                }
                else {
                    DOK.replace(r+","+c,element);
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
            DOK.remove(r+","+c, DOK.get(r+","+c));
        }
    }

    public void clear() {
        for(Iterator<Map.Entry<String, Double>> it = DOK.entrySet().iterator(); it.hasNext(); ) {
            it.next();
            it.remove();
        }
    }

    @Override
    public boolean isEmpty() {
        Set<Map.Entry<String, Double>> entries = DOK.entrySet();
        for (var e : entries) {
            if(e.getKey() != "") {
                return false;
            }
        } 
        return true;
    }
    
    @Override
    public String toString() {
        String s = "";

        Set<Map.Entry<String, Double>> entries = DOK.entrySet();
        for (var e : entries) {
            String str[] = e.getKey().split(",");
            s = s + " " + "(" + str[0] + ", " + str[1] + ": " + e.getValue() + ")";
        }  

        return "[" + s + " ]";
    }

    public void print() {
        Set<Map.Entry<String, Double>> entries = DOK.entrySet();
        for (var e : entries) {
            System.out.println("(" + e.getKey() + ")" + " -> " + e.getValue());     //Change the way the output is printed out
        }  
    }
}
