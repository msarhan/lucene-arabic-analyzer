package AlKhalil2.morphology.underived.unvoweled.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class UnvoweledUnderivedFactory {
  protected Map unvoweledForm = new HashMap<Object, Object>();
  
  public boolean isEmpty() {
    return this.unvoweledForm.isEmpty();
  }
  
  public boolean containsVal(String unvoweled) {
    return this.unvoweledForm.containsKey(unvoweled);
  }
  
  public String getUnvoweledFormValue(String unvoweled) {
    return (String)this.unvoweledForm.get(unvoweled);
  }
  
  public Set getUnvoweledForm() {
    return this.unvoweledForm.keySet();
  }
  
  public void clear() {
    this.unvoweledForm.clear();
  }
}
