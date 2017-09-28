import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Execute extends Thread{
    private int start,end;

    private static double [][] result ; //to have access in main method

    public Execute(int start,int end){
        this.start = start; //starting index of Result
        this.end = end; // ending index of Result

    }
    Input MatA = new Input("A.txt");
    Input MatB = new Input("B.txt");

    @Override
    public void run(){
//        Input MatA = new Input("A.txt");
//        Input MatB = new Input("B.txt");

        int rowA = MatA.getRow();
//        System.out.println(rowA);
        int colA = MatA.getColumn();

        int rowB = MatB.getRow();
        int colB = MatB.getColumn();

        if(colA!=rowB){
            System.out.println("Cannot multiply");
            System.exit(-1);
        }
        if(result==null){
            result = new double[rowA][colB];
        }
        double val=0;
        for(int I=start; I <end; I++){

            for(int l=0;l<colB;l++) {
                for (int j = 0; j < rowB; j++) {

                    val += MatA.getArrayElement(I, j) * MatB.getArrayElement(j, l);
                }

                    result[I][l] = val;
                    val = 0;


            }
        }
//        System.out.println(Arrays.deepToString(result)+start+" "+end);




    }


    public static void  main(String [] args){

        long s = System.currentTimeMillis();

        int no_of_threads = Integer.parseInt(args[0]);
//        System.out.println(no_of_threads);

        Input A = new Input("A.txt");
        int rowA = A.getRow();

        if(no_of_threads>rowA){
            System.out.println("No of threads are greater than no. of rows of the final matrix");
            System.exit(-1);
        }


        int diff = rowA/no_of_threads;
//        System.out.println(no_of_threads);
//        System.out.println(diff);
        List <Execute> test = new ArrayList<>();

        int start=0;
        int end=diff;

        for(int k=0;k<no_of_threads-1;k++){
            test.add(new Execute(start,end));
            test.get(k).start();
            start = end;
            end+=diff;
            try {
                test.get(k).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        test.add(new Execute(start,rowA));
        test.get(no_of_threads-1).start();

        try {
            test.get(no_of_threads-1).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.deepToString(result));

        long e = System.currentTimeMillis();
        System.out.println("Time = "+(e-s));

    }


}