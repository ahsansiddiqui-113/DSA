package Week1.ClassAve;

import BasicIO.*;


/** This class represents a student in a course.
  * 
  * @author David Hughes
  * 
  * @version 1.0 (Aug. 2013)                                                     */

public class Student {
    
    
    private String  stNum;  // student number
    private String  name;   // student's name
    private double  mark;   // student's mark
    
    
    /** This constructor creates a student loading the data from a text file.
      * 
      * @param  from  the file to load data from                                 */
    
    public Student ( ASCIIDataFile from ) {
        
        stNum = from.readString();
        if ( ! from.isEOF() ) {
            name = from.readString();
            mark = from.readDouble();
        };
        
    };  // constructor
    
    
    /** This method returns the student's student number.
      * 
      * @return  String  the student's student number.                           */
    
    public String getStNum ( ) {
        
        return stNum;
        
    };  // getStNum
    
    
   /** This method returns the student's name.
     * 
      * @return  String  the student's name.                                     */
    
    public String getName ( ) {
        
        return name;
        
    };  // getName
    
    
    /** This method returns the student's mark in the test.
      * 
      * @return  double  the student's mark.                                     */
    
    public double getTestMark ( ) {
        
        return mark;
        
    };  // getTestMark
    
    
    /** This method updates the student's mark.
      * 
      * @param  m  the new mark.                                                 */
    
    public void setMark ( double m ) {
        
        mark = m;
        
    };  // setMark
    
    
}  // Student