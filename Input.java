import java.io.*;

public class Input {
    private int ROW,COLUMN;
    private double [][] Array;

    public Input(String filename){

        try {
            FileReader read = new FileReader(filename);
            BufferedReader readbuff = new BufferedReader(read);
            String[] size = readbuff.readLine().split(" ");
            int row=Integer.parseInt(size[0]);
            int column=Integer.parseInt(size[1]);

            this.ROW = row;
            this.COLUMN = column;

            double [][] test = new double[row][column];

            String [] tokens;
            int i=0;
            for(String line = readbuff.readLine();line != null;line = readbuff.readLine()) {
                tokens = line.split(" ");
                for (int j=0;j<column;j++) {
                    test[i][j] = Double.parseDouble(tokens[j]);
                }
                i++;
            }

            this.Array = test;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Malformed Matrix");
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        }


    }

    public int getRow(){
        return ROW;
    }
    public int getColumn(){
        return COLUMN;
    }
    public double[][] getArray(){
        return Array;
    }
    public double getArrayElement(int row,int column){
        return Array[row][column];
    }





}
