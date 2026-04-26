package AlKhalil2.morphology.underived.model;

public class Toolwords {
  private String id;
  
  private String voweledform;
  
  private String type;
  
  private String prefixeclass;
  
  private String suffixeclass;
  
  private String priority;
  
  public Toolwords() {}
  
  public Toolwords(String id, String voweledform, String type, String prefixeclass, String suffixeclass, String priority) {
    this.id = id;
    this.voweledform = voweledform;
    this.type = type;
    this.prefixeclass = prefixeclass;
    this.suffixeclass = suffixeclass;
    this.priority = priority;
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
  
  public String getPrefixeclass() {
    return this.prefixeclass;
  }
  
  public String getSuffixeclass() {
    return this.suffixeclass;
  }
  
  public String getPriority() {
    return this.priority;
  }
}
