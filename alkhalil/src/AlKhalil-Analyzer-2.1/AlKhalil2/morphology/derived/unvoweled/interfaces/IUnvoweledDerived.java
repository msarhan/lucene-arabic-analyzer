package AlKhalil2.morphology.derived.unvoweled.interfaces;

import AlKhalil2.morphology.derived.unvoweled.model.Unvoweled;
import java.util.List;

public interface IUnvoweledDerived {
  void addUnvoweledPatternDiacMap(int paramInt);
  
  boolean containsIdUnvoweled(int paramInt);
  
  List getUnvoweledList(int paramInt);
  
  Unvoweled getUnvoweledForm(int paramInt1, int paramInt2);
  
  void clear();
}
