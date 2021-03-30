  //weather prject telling you how the weather this year in SF looks compared to daily normal
import java.util.Scanner;
import java.io.File;
import java.nio.file.*;
import java.lang.String.*;
import java.text.DecimalFormat;

class Main {

  public static void main(String[] args) {
   Main r = new Main();
   r.setup();
  }

  public String dateIn;
  public double chi, clow;
  public String myfile;

  public void setup(){
    //ask date
    Scanner date = new Scanner(System.in);
    System.out.println("What is the date in #/# form?");
    dateIn = date.nextLine();

    //ask hi & lo
    System.out.println("What is the numeral high today?");
    chi = Integer.parseInt(date.nextLine());
    System.out.println("What is the numeral low today?");
    clow = Integer.parseInt(date.nextLine());

    //shwoing results (printing calls hi and lo which call whereDate)
    printing();

    //sourcing
    System.out.println("Source: usclimatedata.com");

    //closing Scanner
    date.close();
  }

  //returns the index of the date
  public int whereDate(){
    try{
      File ave = new File("weatherSF.txt"); //file
      Path path = Path.of("weatherSF.txt"); //path to the file
      myfile = Files.readString(path); //file as string
      if (myfile.indexOf(dateIn) != -1){ //if date inputted is in file
        return myfile.indexOf(dateIn); //return the index (beg)
      } else {
        return -1;
      }
    } catch (Exception e) { //uh oh didn't work out catch
      System.out.println ("I'm very sorry. An error has occured. Go play chrome://dino?");
      return -1;
    }
  }

  public double hi(){
    int index = whereDate() + dateIn.length() + 4; //the end of the date
    //4 per line break i think
    //printing the substring of myfile with high for checking
    //substring (inclusive, exclusive)
    return Double.parseDouble(myfile.substring(index, index + 4)); //returning same thing^^
  }

    public double lo(){
    int index = whereDate() + dateIn.length() + 11;
    return Double.parseDouble(myfile.substring(index, index + 4));
  }

  public void printing(){
    System.out.println();
    System.out.println("Comparison");
    System.out.println(dateIn + " average SF low: " + lo());
    System.out.println(dateIn + " average SF  high: " + hi());

    String ldirection;
    double valuel;
    if (clow - lo() < 0){ //if negative
      ldirection = "below";
      valuel = Math.abs(clow - lo());
    } else if (clow - lo() > 0) { // if positive
      ldirection = "above"; 
      valuel = Math.abs(lo() - clow);
    } else { //if same
      ldirection = "off of";
      valuel = 0.0;
    }
    valuel = valuel*100;
    int fvalue = (int)valuel;
    valuel = (double)fvalue;
    System.out.println("Today's SF low is " + valuel/100 + " degrees " + ldirection + " average!"); //printing comparison for lows
    
    String hdirection;
    double value;
    if (chi - hi() > 0){ //if positive
      hdirection = "below";
      value = Math.abs(chi - hi());
    } else if (chi - hi() < 0) { //if positive
      hdirection = "above";
      value = Math.abs(hi() - chi);
    } else { //if same
      hdirection = "off of";
      value = 0.0;
    }
    value = value*100;
    int ffvalue = (int)value;
    value = (double)ffvalue;
    System.out.println("Today's SF high is " + value/100 + " degrees " + hdirection + " average!"); //printing comparison for highs
  }
}