package AlKhalil2.morphology.derived.unvoweled.factory;

import AlKhalil2.morphology.derived.unvoweled.model.Unvoweled;
import java.util.List;
import java.util.Map;

public abstract class UnvoweledDerivedFactory {
  protected Map unvoweledPatternStem;
  
  public boolean containsIdUnvoweled(int len) {
    if (this.unvoweledPatternStem != null)
      return this.unvoweledPatternStem.containsKey(Integer.valueOf(len)); 
    return false;
  }
  
  public List getUnvoweledList(int len) {
    return (List)this.unvoweledPatternStem.get(Integer.valueOf(len));
  }
  
  public Unvoweled getUnvoweledForm(int len, int index) {
    return getUnvoweledList(len).get(index - 1);
  }
  
  public void clear() {
    this.unvoweledPatternStem.clear();
  }
}
