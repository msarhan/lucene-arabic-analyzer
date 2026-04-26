package AlKhalil2.stemmer.model;

import AlKhalil2.stemmer.clitic.model.Clitic;

public class Segment {
  private Clitic proclitic;
  
  private String stem;
  
  private Clitic enclitic;
  
  public Segment() {}
  
  public Segment(Clitic _proclitic, String stem, Clitic _enclitic) {
    this.proclitic = _proclitic;
    this.stem = stem;
    this.enclitic = _enclitic;
  }
  
  public Clitic getProclitic() {
    return this.proclitic;
  }
  
  public String getStem() {
    return this.stem;
  }
  
  public Clitic getEnclitic() {
    return this.enclitic;
  }
  
  public void setProclitic(Clitic _proclitic) {
    this.proclitic = _proclitic;
  }
  
  public void setStem(String stem) {
    this.stem = stem;
  }
  
  public void setEnclitic(Clitic _enclitic) {
    this.enclitic = _enclitic;
  }
}
