package AlKhalil2.morphology.derived.voweled.interfaces;

import java.util.List;

public interface IVoweledDerived {
  void addVoweledLemmeDiacMap(int paramInt);
  
  void addVoweledLemmeCanonicMap(int paramInt);
  
  void addVoweledPatternDiacMap(int paramInt);
  
  void addVoweledPatternCanonicMap(int paramInt);
  
  boolean containsIdVoweledLemmeDiac(int paramInt);
  
  boolean containsIdVoweledLemmeCanonic(int paramInt);
  
  boolean containsIdVoweledPatternDiac(int paramInt);
  
  boolean containsIdVoweledPatternCanonic(int paramInt);
  
  List getVoweledLemmeDiacList(int paramInt);
  
  List getVoweledLemmeCanonicList(int paramInt);
  
  List getVoweledPatternDiacList(int paramInt);
  
  List getVoweledPatternCanonicList(int paramInt);
  
  String getVoweledLemmeDiac(int paramInt1, int paramInt2);
  
  String getVoweledLemmeCanonic(int paramInt1, int paramInt2);
  
  String getVoweledPatternDiac(int paramInt1, int paramInt2);
  
  String getVoweledPatternCanonic(int paramInt1, int paramInt2);
  
  void clear();
}
