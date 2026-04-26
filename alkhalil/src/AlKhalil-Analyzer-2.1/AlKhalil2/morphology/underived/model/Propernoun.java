package AlKhalil2.morphology.underived.model;

public class Propernoun {
  private String id;
  
  private String voweledform;
  
  private String type;
  
  public Propernoun() {}
  
  public Propernoun(String id, String voweledform, String type) {
    this.id = id;
    this.voweledform = voweledform;
    this.type = type;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getVoweledform() {
    return this.voweledform;
  }
  
  public String getType() {
    return this.type;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setVoweledform(String voweledform) {
    this.voweledform = voweledform;
  }
  
  public void setType(String type) {
    this.type = type;
  }
}
