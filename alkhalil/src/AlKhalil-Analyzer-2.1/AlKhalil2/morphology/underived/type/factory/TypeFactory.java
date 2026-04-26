package AlKhalil2.morphology.underived.type.factory;

import java.util.ArrayList;
import java.util.List;

public abstract class TypeFactory {
  protected List allType = new ArrayList();
  
  public String getTypeValue(int idType) {
    return this.allType.get(idType - 1);
  }
  
  public boolean isEmpty() {
    return this.allType.isEmpty();
  }
  
  public void clear() {
    this.allType.clear();
  }
}
