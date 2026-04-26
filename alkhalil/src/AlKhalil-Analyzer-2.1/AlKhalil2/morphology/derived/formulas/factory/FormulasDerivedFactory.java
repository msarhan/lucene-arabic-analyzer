package AlKhalil2.morphology.derived.formulas.factory;

import AlKhalil2.morphology.derived.formulas.model.Formulas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class FormulasDerivedFactory {
  protected Map<Integer, List<Formulas>> formula;
  
  public boolean containsValFormula(int len) {
    if (this.formula != null)
      return this.formula.containsKey(Integer.valueOf(len)); 
    return false;
  }
  
  public List getForumlaList(int len) {
    return this.formula.get(Integer.valueOf(len));
  }
  
  public List getIdsDiacPattern(int len, String rootLen) {
    Iterator<String> it = Arrays.<String>asList(rootLen.split(" ")).iterator();
    Set<String> set = new HashSet();
    while (it.hasNext()) {
      int idFormula = Integer.parseInt(it.next());
      String idDiacPattern = ((Formulas)getForumlaList(len).get(idFormula - 1)).getIdDiacPattern();
      set.add(idDiacPattern);
    } 
    List<String> list = new ArrayList();
    list.addAll(set);
    return list;
  }
  
  public List getInfoResult(Set L1, String rootLen, int len) {
    Set<Formulas> set = new HashSet();
    Iterator<String> it = Arrays.<String>asList(rootLen.split(" ")).iterator();
    while (it.hasNext()) {
      int idFormula = Integer.parseInt(it.next());
      Formulas ids = getForumlaList(len).get(idFormula - 1);
      if (L1.contains(ids.getIdDiacPattern()))
        set.add(ids); 
    } 
    List<Formulas> list = new ArrayList();
    list.addAll(set);
    return list;
  }
  
  public List getInfoResult(List L1, int len) {
    Set set = new HashSet();
    set.addAll(L1);
    List<Formulas> list = new ArrayList();
    Iterator<Formulas> it = getForumlaList(len).iterator();
    while (it.hasNext()) {
      Formulas ids = it.next();
      if (set.contains(ids.getIdDiacPattern()))
        list.add(ids); 
    } 
    return list;
  }
  
  public void clear() {
    this.formula.clear();
  }
}
