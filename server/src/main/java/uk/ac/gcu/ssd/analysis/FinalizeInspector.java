package uk.ac.gcu.ssd.analysis;

import java.applet.Applet;
import java.io.*;
import java.util.regex.Pattern;

public class FinalizeInspector {
//  extends Applet
  private ReportGenerator report = new ReportGenerator("finalize() Method Declared Public, CWE-78");

  private Pattern setPattern(String regex) {
    return Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
  }

  public ReportGenerator findOSCommandInjection(File file){
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String fileRead = "";
      String line;
      int counter = 0;
      while ((line = bufferedReader.readLine()) != null){
        counter++;
        System.out.println(line);
        fileRead += line;
        if (setPattern(".*public void finalize().*").matcher(line).matches()){
          this.report.makeReport("You are using finalize with public access. make it protected." ,counter);
        }
      }
      bufferedReader.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return this.report;
  }
}
