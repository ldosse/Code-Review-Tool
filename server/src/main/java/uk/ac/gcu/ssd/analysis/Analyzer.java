package uk.ac.gcu.ssd.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import uk.ac.gcu.ssd.storage.FileStorageProperties;
import uk.ac.gcu.ssd.storage.FileStorageService;

import java.io.File;
import java.io.IOException;

public class Analyzer {
  private SQLInjectionInspector sqlInjectionInspector;
  private OSCommandInjectionInspector osCommandInjectionInspector;
  private FinalizeInspector finalizeInspector;

  @Autowired
  private FileStorageService fileStorageService;

  public Analyzer() {
    sqlInjectionInspector=new SQLInjectionInspector();
    osCommandInjectionInspector = new OSCommandInjectionInspector();
    finalizeInspector = new FinalizeInspector();
//    fileStorageService = new FileStorageService(new FileStorageProperties());
  }

  public String analyze(File file){
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    System.out.println(filename);
//    Resource resource = fileStorageService.loadFileAsResource(filename);
//    File file = new File("Users/LENOVO/Documents/Y4/Term2/Security/dw/uploads/"+filename);
//    System.out.println("Users/LENOVO/Documents/Y4/Term2/Security/dw/uploads/"+filename);
//    try {
//      System.out.println(file.getFile().getAbsolutePath());
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
    if(file!=null && file.exists()){
//      System.out.println("--------------------------------------------------------------------------------");
      ReportGenerator sqlReport = sqlInjectionInspector.findSQLVulnerabilities(file);
//      System.out.println(sqlReport.getReport());
      ReportGenerator osReport = osCommandInjectionInspector.findOSCommandInjection(file);
      ReportGenerator finalizeReport = finalizeInspector.findOSCommandInjection(file);
      String compileReport = compileReports(new ReportGenerator[]{sqlReport, osReport, finalizeReport});
      System.out.println(compileReport);
      System.out.println("--------------------------------------------------------------------------------");
      System.out.println(file.getAbsolutePath());
      System.out.println(file.delete());
      return compileReport;
    }
    return null;
  }

  private String compileReports(ReportGenerator[] reportGenerators){
    String compiledReport = "";
    for (ReportGenerator reportGenerator:reportGenerators) {
      compiledReport+=reportGenerator.getVulnerability();
      compiledReport+="<br/>____________________________________________________________________________<br/>";
      compiledReport += (reportGenerator.getReport()!="") ? reportGenerator.getReport() : "None detected";
      compiledReport+="<br/>";
//      compiledReport+="\n____________________________________________________________________________\n";
    }
    return compiledReport;
  }

}
