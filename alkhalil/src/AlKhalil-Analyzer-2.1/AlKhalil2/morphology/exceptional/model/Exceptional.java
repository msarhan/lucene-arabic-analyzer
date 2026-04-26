package AlKhalil2.morphology.exceptional.model;

public class Exceptional {
  private String unvoweledform;
  
  private String voweledform;
  
  private String prefix;
  
  private String stem;
  
  private String type;
  
  private String suffix;
  
  public Exceptional() {}
  
  public Exceptional(String unvoweledform, String voweledform, String prefix, String stem, String type, String suffix) {
    this.unvoweledform = unvoweledform;
    this.voweledform = voweledform;
    this.prefix = prefix;
    this.stem = stem;
    this.type = type;
    this.suffix = suffix;
  }
  
  public String getUnvoweledform() {
    return this.unvoweledform;
  }
  
  public String getVoweledform() {
    return this.voweledform;
  }
  
  public String getPrefix() {
    return this.prefix;
  }
  
  public String getStem() {
    return this.stem;
  }
  
  public String getType() {
    return this.type;
  }
  
  public String getSuffix() {
    return this.suffix;
  }
}
