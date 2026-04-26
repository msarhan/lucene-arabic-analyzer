package AlKhalil2.morphology.derived.type.factory;

import java.util.List;

public abstract class TypeDerivedFactory {
  protected List type;
  
  public boolean isEmpty() {
    return this.type.isEmpty();
  }
  
  public String getType(int index) {
    if (!isEmpty())
      return this.type.get(index - 1); 
    return "";
  }
  
  public void clear() {
    this.type.clear();
  }
}
