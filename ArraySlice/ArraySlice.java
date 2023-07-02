package Week1.ArraySlice;
import BasicIO.*;
import static java.lang.Math.*;
import static BasicIO.Formats.*;


public class ArraySlice {


    int [][] x;
    int [] y,z;


    public ArraySlice(){
        x = new int [3][4];
        initA(x);
        print(x);
        y=x[0];
        z=x[1];
        x[0]=z;
        x[1]=y;
        print(x);

    }

    private void initA( int[][] x){
        for (int i=0;i<x.length;i++)
            for (int j=0; j<x[0].length;j++)
                x[i][j]=(i*(x.length+1)) + j;
    }

    private void print(int [][] x){
        for (int i=0;i<x.length;i++) {
            for (int j = 0; j < x[0].length; j++)
                System.out.print(x[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new ArraySlice();
        System.out.println("End ArraySlice");

    }


}

