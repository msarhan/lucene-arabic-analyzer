package AlKhalil2.morphology.underived.unvoweled.interfaces;

import java.util.Set;

public interface IUnvoweledUnderived {
  boolean isEmpty();
  
  boolean containsVal(String paramString);
  
  void addUnvoweledForm();
  
  String getUnvoweledFormValue(String paramString);
  
  Set getUnvoweledForm();
  
  void clear();
}
