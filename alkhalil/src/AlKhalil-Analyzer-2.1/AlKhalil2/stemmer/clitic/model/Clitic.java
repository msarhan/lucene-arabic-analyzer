package AlKhalil2.stemmer.clitic.model;

public class Clitic {
  private String unvoweledform;
  
  private String voweledform;
  
  private String desc;
  
  private String classe;
  
  public String getUnvoweledform() {
    return this.unvoweledform;
  }
  
  public String getVoweledform() {
    return this.voweledform;
  }
  
  public String getDesc() {
    return this.desc;
  }
  
  public String getClasse() {
    return this.classe;
  }
  
  public void setUnvoweledform(String unvoweledform) {
    this.unvoweledform = unvoweledform;
  }
  
  public void setVoweledform(String voweledform) {
    this.voweledform = voweledform;
  }
  
  public void setDesc(String desc) {
    this.desc = desc;
  }
  
  public void setClasse(String classe) {
    this.classe = classe;
  }
}
