public class SparseMatrices {

    public SparseMatrices() {

    }

    static SparseMatrix add(SparseMatrix a, SparseMatrix b) {
        if(a.rowCount() != b.rowCount() || a.colCount() != b.colCount()) {
            //System.out.println("null");
            return null;
        }
        else {
            SparseMatrix SM = a;
            //SM.clear();
            if (a.getClass() == SparseMatrixLIL.class && b.getClass() == SparseMatrixLIL.class) {
               //LIL+LIL
            }else if(a.getClass() == SparseMatrixDOK.class && b.getClass() == SparseMatrixDOK.class){
                //DOK+DOK
            }
            else if(a.getClass() == SparseMatrixLIL.class && b.getClass() == SparseMatrixDOK.class) {
                //LIL+DOK
            }
            else {
               //DOK+LIL
            }
            return SM;
        }
    }
}
