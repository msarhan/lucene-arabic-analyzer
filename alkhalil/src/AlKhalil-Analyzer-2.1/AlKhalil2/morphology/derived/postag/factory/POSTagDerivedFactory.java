package AlKhalil2.morphology.derived.postag.factory;

import AlKhalil2.morphology.derived.postag.model.POSTag;
import java.util.List;

public abstract class POSTagDerivedFactory {
  protected List posTag;
  
  public boolean isEmpty() {
    return this.posTag.isEmpty();
  }
  
  public POSTag getPosTag(int index) {
    return this.posTag.get(index - 1);
  }
  
  public void clear() {
    this.posTag.clear();
  }
}
