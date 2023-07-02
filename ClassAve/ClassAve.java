package Week1.ClassAve;
import BasicIO.*;
import static java.lang.Math.*;
import static BasicIO.Formats.*;


/** This class is a program to calculate a class average and standard deviation.
  * 
  * @author David Hughes
  * 
  * @version 1.4 (Jan. 2014)
  *
  * new concepts: array of objects, "variable-sized" array                       */

public class ClassAve {
    
    
    private static final int  MAX_STD = 100;  // max number of students
    
    private ReportPrinter  report;   // printer for report
    private ASCIIDataFile  stData;   // data file for student marks
    
    
    /** The constructor generates the report, reading the student data,
      * computing the average and standard deviation and displaying the results. */
    
    public ClassAve ( ) {
        
        String     course;    // course name
        Student[]  students;  // the class of students
        Student    aStudent;  // one student
        int        numStd;    // number of students
        double     sum;       // sum of marks
        double     ave;       // average mark
        double     std;       // standard deviation
        
        report = new ReportPrinter();
        stData = new ASCIIDataFile();
        course = stData.readString();
        setUpReport(course,"Term Test");
        students = new Student[MAX_STD];
        numStd = 0;
        sum = 0;
        for ( ; ; ) {
            aStudent = new Student(stData);
        if ( stData.isEOF() | numStd >= MAX_STD ) break;
            students[numStd] = aStudent;
            numStd = numStd + 1;
            sum = sum + aStudent.getTestMark();
            writeDetail(aStudent);
        };
        ave = sum / numStd;
        std = computeStd(students,numStd,ave);
        writeSummary(ave,std);
        report.close();
        stData.close();
        
    };  // constructor
    
    
    /** This method computes the standard deviation of the marks for the students.
      * 
      * @param  students  students in the class
      * @param  num       number of students
      * @param  ave       average mark
      * 
      * @return  double  the standard devisation of the marks                    */
    
    private double computeStd ( Student[] students, int numStd, double ave ) {
        
        double  sum;    // sum of squares of deviations
        double  aMark;  // a student mark
        
        sum = 0;
        for ( int i=0 ; i<numStd ; i++ ) {
            aMark = students[i].getTestMark();
            sum = sum + pow(aMark-ave,2);
        };
        return sqrt(sum/numStd);
        
    };  // computeStd
    
    
    /** This method sets up the report, adding all fields.
      * 
      * @param  courseName  name of the course
      * @param  workName    name of the piece of work                            */
    
    private void setUpReport ( String courseName, String workName ) {
        
        report.setTitle(courseName,workName);
        report.addField("stNum","Student #",10);
        report.addField("name","Name",20);
        report.addField("mark","Mark",getDecimalInstance(1),5);
        
    };  // setUpReport
    
    
    /** This method generates a report detail line.
      * 
      * @param  aStudent  a student                                              */
    
    private void writeDetail ( Student aStudent ) {
        
        report.writeString("stNum",aStudent.getStNum());
        report.writeString("name",aStudent.getName());
        report.writeDouble("mark",aStudent.getTestMark());
        
    };  // writeDetail
    
    
    /** This method generates the report summary.
      * 
      * @param  average  average mark in course
      * @param  std      standard deviation                                      */
    
    private void writeSummary ( double average, double std ) {
        
        report.writeString("stNum","Average");
        report.writeDouble("mark",average);
        report.writeString("stNum","Std. Dev.");
        report.writeDouble("mark",std);
        
    };  // writeSummary
    
    
    public static void main ( String[] args ) { ClassAve c = new ClassAve(); };
    
    
}  // ClassAve