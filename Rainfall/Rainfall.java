import BasicIO.*;


/** This program lists the months of the year with above average rainfall.
  * 
  * @author D. Hughes
  * 
  * @version 1.0 (Jan. 2014)
  *
  * new concepts: arrays of primitive type, array creation, subscripting,
  *               "right-sized" arrays                                           */

public class Rainfall {
    
    
    private ASCIIDataFile   in;   // file with rainfall data
    private ASCIIDisplayer  out;  // displayer for output
    
    
    /** The constructor displays the months with above average rainfall.         */
    
    public Rainfall ( ) {
        
        String    year;      // year
        double[]  rainfall;  // rainfall for each month
        double    totRain;   // total rainfall for the year
        double    aveRain;   // average monthly rainfall
        
        in = new ASCIIDataFile();
        out = new ASCIIDisplayer();
        year = in.readString();
        rainfall = new double[12];
        totRain = 0;
        for ( int i=0 ; i<rainfall.length ; i++ ) {
            rainfall[i] = in.readDouble();
            totRain = totRain + rainfall[i];
        };
        aveRain = totRain / rainfall.length;
        writeHeader(year,aveRain);
        for ( int i=0 ; i<rainfall.length ; i++ ) {
            if ( rainfall[i] > aveRain ) {
                writeDetail(i+1,year,rainfall[i]);
            };
        };
        in.close();
        out.close();
        
    }; // constructor
    
    
    /** This method writes the header for the rainfall display.
      * 
      * @param year     year
      * @param aveRain  average rainfall.                                        */
    
    private void writeHeader ( String year, double aveRain ) {
        
        out.writeString("Average monthly rainfall for "+year+": ");
        out.writeDouble(aveRain,5,2);
        out.newLine();
        out.newLine();
        out.writeString("Months with above average rainfall:");
        out.newLine();
        
    }; // writeHeader
    
    
    /** This method writes the deatil line for the rainfall display.
      * 
      * @param month     month number
      * @param year      year
      * @param rainfall  rainfall amount.                                        */
    
    private void writeDetail ( int month, String year, double rainfall ) {
        
        out.writeString(month+"/"+year);
        out.writeDouble(rainfall,5,2);
        out.newLine();
        
    }; // writeDetail
    
    
    public static void main ( String[] args ) { Rainfall r = new Rainfall(); };
    
    
} // Rainfall
