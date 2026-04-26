package AlKhalil2.morphology.derived.postag.model;

public class POSTag {
  private String id;
  
  private String aug;
  
  private String cas;
  
  private String ncg;
  
  private String trans;
  
  public POSTag() {}
  
  public POSTag(String id, String aug, String cas, String ncg, String trans) {
    this.id = id;
    this.aug = aug;
    this.cas = cas;
    this.ncg = ncg;
    this.trans = trans;
  }
  
  public POSTag(String id, String cas, String ncg) {
    this.id = id;
    this.cas = cas;
    this.ncg = ncg;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getAug() {
    return this.aug;
  }
  
  public String getCas() {
    return this.cas;
  }
  
  public String getNcg() {
    return this.ncg;
  }
  
  public String getTrans() {
    return this.trans;
  }
}
