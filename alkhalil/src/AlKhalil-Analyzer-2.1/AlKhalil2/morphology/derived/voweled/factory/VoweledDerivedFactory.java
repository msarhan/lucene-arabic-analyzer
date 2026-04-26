package AlKhalil2.morphology.derived.voweled.factory;

import java.util.List;
import java.util.Map;

public abstract class VoweledDerivedFactory {
  protected Map voweledLemmeDiacMap;
  
  protected Map voweledLemmeCanonicMap;
  
  protected Map voweledPatternDiacMap;
  
  protected Map voweledPatternCanonicMap;
  
  public boolean containsIdVoweledLemmeDiac(int len) {
    if (this.voweledLemmeDiacMap != null)
      return this.voweledLemmeDiacMap.containsKey(Integer.valueOf(len)); 
    return false;
  }
  
  public boolean containsIdVoweledLemmeCanonic(int len) {
    if (this.voweledLemmeCanonicMap != null)
      return this.voweledLemmeCanonicMap.containsKey(Integer.valueOf(len)); 
    return false;
  }
  
  public boolean containsIdVoweledPatternDiac(int len) {
    if (this.voweledPatternDiacMap != null)
      return this.voweledPatternDiacMap.containsKey(Integer.valueOf(len)); 
    return false;
  }
  
  public boolean containsIdVoweledPatternCanonic(int len) {
    if (this.voweledPatternCanonicMap != null)
      return this.voweledPatternCanonicMap.containsKey(Integer.valueOf(len)); 
    return false;
  }
  
  public List getVoweledLemmeDiacList(int len) {
    return (List)this.voweledLemmeDiacMap.get(Integer.valueOf(len));
  }
  
  public List getVoweledLemmeCanonicList(int len) {
    return (List)this.voweledLemmeCanonicMap.get(Integer.valueOf(len));
  }
  
  public List getVoweledPatternDiacList(int len) {
    return (List)this.voweledPatternDiacMap.get(Integer.valueOf(len));
  }
  
  public List getVoweledPatternCanonicList(int len) {
    return (List)this.voweledPatternCanonicMap.get(Integer.valueOf(len));
  }
  
  public String getVoweledLemmeDiac(int len, int index) {
    return getVoweledLemmeDiacList(len).get(index - 1);
  }
  
  public String getVoweledLemmeCanonic(int len, int index) {
    return getVoweledLemmeCanonicList(len).get(index - 1);
  }
  
  public String getVoweledPatternDiac(int len, int index) {
    return getVoweledPatternDiacList(len).get(index - 1);
  }
  
  public String getVoweledPatternCanonic(int len, int index) {
    return getVoweledPatternCanonicList(len).get(index - 1);
  }
  
  public void clear() {
    this.voweledLemmeDiacMap.clear();
    this.voweledLemmeCanonicMap.clear();
    this.voweledPatternDiacMap.clear();
    this.voweledPatternCanonicMap.clear();
  }
}
