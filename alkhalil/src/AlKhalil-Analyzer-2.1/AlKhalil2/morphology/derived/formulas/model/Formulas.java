package AlKhalil2.morphology.derived.formulas.model;

public class Formulas {
  private String idRoot;
  
  private String idDiacPattern;
  
  private String idCanonicPattern;
  
  private String idDiacLemme;
  
  private String idCanonicLemme;
  
  private String idType;
  
  private String idPOSTag;
  
  public Formulas() {}
  
  public Formulas(String idRoot, String idDiacPattern, String idCanonicPattern, String idDiacLemme, String idCanonicLemme, String idType, String idPOSTag) {
    this.idRoot = idRoot;
    this.idDiacPattern = idDiacPattern;
    this.idCanonicPattern = idCanonicPattern;
    this.idDiacLemme = idDiacLemme;
    this.idCanonicLemme = idCanonicLemme;
    this.idType = idType;
    this.idPOSTag = idPOSTag;
  }
  
  public String getIdRoot() {
    return this.idRoot;
  }
  
  public String getIdDiacPattern() {
    return this.idDiacPattern;
  }
  
  public String getIdCanonicPattern() {
    return this.idCanonicPattern;
  }
  
  public String getIdDiacLemme() {
    return this.idDiacLemme;
  }
  
  public String getIdCanonicLemme() {
    return this.idCanonicLemme;
  }
  
  public String getIdType() {
    return this.idType;
  }
  
  public String getIdPOSTag() {
    return this.idPOSTag;
  }
  
  public void setIdRoot(String idRoot) {
    this.idRoot = idRoot;
  }
  
  public void setIdDiacPattern(String idDiacPattern) {
    this.idDiacPattern = idDiacPattern;
  }
  
  public void setIdCanonicPattern(String idCanonicPattern) {
    this.idCanonicPattern = idCanonicPattern;
  }
  
  public void setIdDiacLemme(String idDiacLemme) {
    this.idDiacLemme = idDiacLemme;
  }
  
  public void setIdCanonicLemme(String idCanonicLemme) {
    this.idCanonicLemme = idCanonicLemme;
  }
  
  public void setIdType(String idType) {
    this.idType = idType;
  }
  
  public void setIdPOSTag(String idPOSTag) {
    this.idPOSTag = idPOSTag;
  }
}
