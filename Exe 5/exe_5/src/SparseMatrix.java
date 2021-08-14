public interface SparseMatrix {
    public int rowCount();
    public int colCount();
    public double get(int r, int c);
    public void set(int r, int c, double element);
    public void zero(int r, int c);
    public void clear();
    public boolean isEmpty();
    public String toString();
}
