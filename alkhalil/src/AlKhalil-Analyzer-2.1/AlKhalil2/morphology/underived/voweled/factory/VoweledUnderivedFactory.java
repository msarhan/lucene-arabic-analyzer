package AlKhalil2.morphology.underived.voweled.factory;

import java.util.ArrayList;
import java.util.List;

public abstract class VoweledUnderivedFactory {
  protected List allVoweled = new ArrayList();
  
  public boolean isEmpty() {
    return this.allVoweled.isEmpty();
  }
  
  public int size() {
    return this.allVoweled.size();
  }
  
  public void clear() {
    this.allVoweled.clear();
  }
}
