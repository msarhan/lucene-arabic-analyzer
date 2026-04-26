package AlKhalil2.morphology.derived.postag.interfaces;

import AlKhalil2.morphology.derived.postag.model.POSTag;

public interface IPOSTagDerived {
  void addPosTag();
  
  boolean isEmpty();
  
  POSTag getPosTag(int paramInt);
  
  void clear();
}
