package AlKhalil2.morphology.derived.formulas.interfaces;

import java.util.List;
import java.util.Set;

public interface IFormulasDerived {
  void addFormulaList(int paramInt);
  
  boolean containsValFormula(int paramInt);
  
  List getForumlaList(int paramInt);
  
  List getIdsDiacPattern(int paramInt, String paramString);
  
  List getInfoResult(Set paramSet, String paramString, int paramInt);
  
  List getInfoResult(List paramList, int paramInt);
  
  void clear();
}
