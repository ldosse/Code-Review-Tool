package uk.ac.gcu.ssd.analysis;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OSCommandInjectionInspector {
  private ReportGenerator report = new ReportGenerator("OS Command Injection, CWE-78");

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
        if (setPattern(".*\\.exec.*").matcher(line).matches()){
          this.report.makeReport("You are using an OS command. You should avoid creating a new OS process and use built-in Java " +
              "functionality instead.",counter);
          if (!setPattern(".*\\.exec\\(\".*").matcher(line).matches()){
            this.report.makeReport("You are also using a potentially tamperable parameter. Make sure you have sanitized your " +
                "parameter. You can use `{param}.replaceAll(\"[^a-z0-9\\-\\.]\",\"\")` to sanitize it.",counter);
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return this.report;
  }
}
