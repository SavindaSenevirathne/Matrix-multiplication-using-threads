import java.util.Arrays;

public class SingleThread {
    public static void main(String [] args) {
        long s = System.currentTimeMillis();

        Input MatA = new Input("A.txt");
        Input MatB = new Input("B.txt");

        int rowA = MatA.getRow();
        int colA = MatA.getColumn();

        int rowB = MatB.getRow();
        int colB = MatB.getColumn();
//        System.out.println("ColA="+colA+" rowB="+rowB);
        if(colA!=rowB){
            System.out.println("Cannot multiply");
            System.exit(-1);
        }

        double [][] result = new double[rowA][colB];

        double val=0;
        for(int i=0;i<rowA;i++){

            for(int l=0;l<colB;l++) {
                for (int j = 0; j < rowB; j++) {
                    val += MatA.getArrayElement(i, j) * MatB.getArrayElement(j, l);
                }
//                System.out.println(val);
                result[i][l] = val;
                val = 0;

            }
        }
        System.out.println(Arrays.deepToString(result));

        long e = System.currentTimeMillis();
        System.out.println("Time = "+(e-s));


    }
}
