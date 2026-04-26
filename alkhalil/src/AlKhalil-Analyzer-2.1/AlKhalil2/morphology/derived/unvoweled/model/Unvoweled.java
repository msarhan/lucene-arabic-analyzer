package AlKhalil2.morphology.derived.unvoweled.model;

public class Unvoweled {
  private String val;
  
  private String rules;
  
  private String ids;
  
  public Unvoweled() {}
  
  public Unvoweled(String val, String rules, String ids) {
    this.val = val;
    this.rules = rules;
    this.ids = ids;
  }
  
  public String getVal() {
    return this.val;
  }
  
  public String getRules() {
    return this.rules;
  }
  
  public String getIds() {
    return this.ids;
  }
  
  public void setVal(String val) {
    this.val = val;
  }
  
  public void setRules(String rules) {
    this.rules = rules;
  }
  
  public void setIds(String ids) {
    this.ids = ids;
  }
  
  public String toString() {
    return this.val + ";" + this.rules + ";" + this.ids;
  }
}
