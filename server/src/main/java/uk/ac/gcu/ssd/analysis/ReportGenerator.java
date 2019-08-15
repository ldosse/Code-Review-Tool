package uk.ac.gcu.ssd.analysis;

public class ReportGenerator {
  public String getVulnerability() {
    return vulnerability;
  }

  private String vulnerability;

  public String getReport() {
    return report;
  }

  private String report;
  public ReportGenerator(String vulnerability) {
    this.vulnerability = vulnerability;
    this.report="";
  }

  public void makeReport(String statement, int line){
    if (line==-1)
      this.report += statement+"\n";
    else
    this.report += "Line "+line+" : "+statement+"\n";
  }

}
