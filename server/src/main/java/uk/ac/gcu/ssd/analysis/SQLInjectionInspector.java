package uk.ac.gcu.ssd.analysis;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLInjectionInspector {
  private final String CONNECTION = "Connection";
  private final String DYNAMIC_QUERY = "SELECT";
  private ReportGenerator report=new ReportGenerator("SQL Injection, CWE-89");

  private void setPattern(String regex) {
    this.pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
  }

  private void setMatcher(Matcher matcher) {
    this.matcher = matcher;
  }

  private Pattern pattern;

  private Matcher getMatcher(String string) {
    return this.pattern.matcher(string);
  }

  private Matcher matcher;

  public ReportGenerator findSQLVulnerabilities(File file){
    String startingWord;
    String regex;
    String input;
    Pattern queryPattern;
    Matcher queryMatcher;

//    TODO CHECK FOR FINAL STATEMENT VULNERABILITY
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String fileRead = "";
      String line;
      Boolean sql;
      Boolean openConnection = false;
      Boolean dynamicQueries=false;
      List<Integer> dynamicQueriesLines;
      int counter = 0;
      while ((line = bufferedReader.readLine()) != null){
        counter++;
        System.out.println(line);
        fileRead += line;
        setPattern(".*(SELECT|UPDATE|DELETE|ALTER|INSERT|DROP).*");
        if (getMatcher(line).matches()){
          System.out.println("____________________________________________________________________________");
          if (line.contains("+")){
            this.report.makeReport("Dynamic Querying detected. Potential vulnerability for SQL injections. " +
                "Consider using prepared statements.",counter);
          }
        }
        setPattern("Statement");
        if(getMatcher(line).lookingAt()){
          report.makeReport("Statement object used. Consider using Prepared statements instead.",counter);
        }
      }
      if (counter!=0){
        openConnection = findOpenConnection(fileRead);
        if (openConnection) this.report.makeReport("You might have not closed your database connection. " +
            "You can close it with the method close().",-1);

      }
      bufferedReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return report;
  }

  private boolean findOpenConnection(String file){
    Pattern pattern = Pattern.compile(CONNECTION);
    Matcher matcher = pattern.matcher(file);
    if(matcher.lookingAt()){
      pattern = Pattern.compile(".close()");
      matcher = pattern.matcher(file);
      return !matcher.lookingAt();
    }else return false;
  }



  //        List<String> lineList = line.split("")
//      startingWord = ;
//  findConnection();
//
//      if()
//
//          if (line.startsWith("import")){
//    if(line.contains("sql")){
//      sql=true;
//    }
//  }else
//
//      switch (line.startsWith()){
//    case "import":
//  }

//    if(contains SQL|QUERY)
//      if(servlet)
//      if(DAO)
//        does it contain autoclosable interface??
//      if contains query||select or delete or etc
//          is it final statement?
//          does it use prepared statements
//      are there ty catch statements
//      if contains connection and
//            does it close connection
//          do you use create statement directly? => use prepared instead
}
