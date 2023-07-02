import java.util.*;
import BasicIO.*;
import static BasicIO.Formats.*;


/** This class inputs enrolment statistics from a number of departments at a
  * number of universities and produces a summary table with row, sum and grand
  * totals.
  * 
  * @author D. Hughes
  * 
  * @version 1.0 (Jan. 2014)
  *
  * new concepts: 2-d arrays, row-major and column-major traversal.             */

public class UStats {
    
    
    private ASCIIDataFile   dataFile; // file for input
    private ReportPrinter   report;   // file for report
    private ASCIIDisplayer  msg;      // displayer for messages
    
    
    /** The constructor reads the enrolment stats and generates and displays the
      * summaries.                                                              */
    
    public UStats ( ) {
        
        int       nUniv;    // number of universities
        int       nDept;    // number of departments
        String[]  univ;     // university names
        String[]  dept;     // dept names
        int[][]   enrol;    // enrolment stats
        int[]     uTotals;  // University totals
        int[]     dTotals;  // Department totals
        int       total;    // grand total
        
        dataFile = new ASCIIDataFile();
        report = new ReportPrinter();
        msg = new ASCIIDisplayer();
        msg.writeString("Processing.....");
        nUniv = dataFile.readInt();
        nDept = dataFile.readInt();
        univ = new String[nUniv];
        dept = new String[nDept];
        enrol = new int[nDept][nUniv];
        readStats(univ,dept,enrol);
        uTotals = sumByUniv(enrol);
        dTotals = sumByDept(enrol);
        total = sumAll(enrol);
        initReport(univ);
        writeStats(dept,enrol,dTotals,uTotals,total);
        msg.writeString(".....complete");
        msg.newLine(); 
        dataFile.close();
        report.close();
        msg.close();
        
    }; // constructor
    
    
    /** This method reads the enrollment statistics for the universities and
      * departments.
      * 
      * @param univ  university names
      * @param dept  department names
      * @param enrol the array to read into.                                    */
    
    private void readStats ( String[] univ, String[] dept, int[][] stats ) {
        
        for ( int j=0 ; j<univ.length ; j++ ) {
            univ[j] = dataFile.readString();
        };
        for ( int i=0 ; i<dept.length ; i++ ) {
            dept[i] = dataFile.readString();
        }
        for ( int i=0 ; i<stats.length ; i++ ) {
            for ( int j=0 ; j<stats[i].length ; j++ ) {
                stats[i][j] = dataFile.readInt();
            };
        };
        
    }; // readStats
    
    
    /** This method sums the enrollments by department (row).
      * 
      * @param  stats  the enrollment statistics.
      * 
      * @return  int[]  the department totals.                                  */
    
    private int[] sumByDept ( int[][] stats ) {
        
        int[]  sums;
        
        sums = new int[stats.length];
        for ( int i=0 ; i<stats.length ; i++ ) {
            sums[i] = 0;
            for ( int j=0 ; j<stats[i].length ; j++ ) {
                sums[i] = sums[i] + stats[i][j];
            };
        };
        
        return sums;
        
    }; // sumByDept
    
    
    /** This method sums the enrollments by university (column).
      * 
      * @param  stats  the enrollment statistics.
      * 
      * @return  int[]  the university totals.                                  */
    
    private int[] sumByUniv ( int[][] stats ) {
        
        int[]  sums;
        
        sums = new int[stats[0].length];
        for ( int j=0 ; j<stats[0].length ; j++ ) {
            sums[j] = 0;
            for ( int i=0 ; i<stats.length ; i++ ) {
                sums[j] = sums[j] + stats[i][j];
            };
        };
        return sums;
        
    }; // sumByUniv
    
    
    /** This method computes the grand sum of the enrollment statistics.
      * 
      * @param  stats  the enrollment statistics.
      * 
      * @return  int  the grand total.                                          */
    
    private int sumAll ( int[][] stats ) {
        
        int  sum;
        
        sum = 0;
        for ( int i=0 ; i<stats.length ; i++ ) {
            for ( int j=0 ; j<stats[i].length ; j++ ) {
                sum = sum + stats[i][j];
            };
        };
        return sum;
        
    }; // sumAll
    
    
    /** This method initializes the enrolment report.
      * 
      * @param univ  university names                                           */
    
    private void initReport ( String[] univ ) {
        
        report.setTitle("Enrolment Report",getDateInstance().format(new Date()));
        report.addField("dept","",10);
        for ( int i=0 ; i<univ.length ; i++ ) {
            report.addField("",univ[i],getIntegerInstance(),10);
        };
        report.addField("total","     Total",getIntegerInstance(),10);
        
    };  // initReport
    
    
    /** This method displays the enrollment stats in a tabular format with labels
      * and totals for each row and column and a grand total.
      * 
      * @param  dept   department names
      * @param  stats  the enrollment statistics.
      * @param  rSums  the row sums.
      * @param  cSums  the column sums.
      * @param  sum    the grand sum.                                           */
    
    private void writeStats( String[] dept, int[][] stats,
                             int[] rSums, int[] cSums, int sum ) {
        
        for ( int i=0 ; i<stats.length ; i++ ) {
            report.writeString(dept[i]);
            for ( int j=0 ; j<stats[i].length ; j++ ) {
                report.writeInt(stats[i][j]);
            };
            report.writeInt(rSums[i]);
        };
        report.writeString("Total");
        for ( int j=0 ; j<cSums.length ; j++ ) {
            report.writeInt(cSums[j]);
        };
        report.writeInt(sum);
        
    }; // writeStats
    
    
    public static void main ( String[] args ) { UStats u = new UStats(); };
    
    
} // UStats