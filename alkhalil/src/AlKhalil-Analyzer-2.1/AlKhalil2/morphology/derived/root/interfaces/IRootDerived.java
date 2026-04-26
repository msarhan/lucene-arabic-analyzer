package AlKhalil2.morphology.derived.root.interfaces;

import AlKhalil2.morphology.derived.root.model.Root;
import java.util.List;

public interface IRootDerived {
  boolean isEmptyQuadriliteralRootMap();
  
  boolean isEmptyQuadriliteralRootList();
  
  boolean isEmptyTrilateralRootList();
  
  boolean isEmptyTrilateralRootMap();
  
  void addQuadriliteralRootMap(char paramChar);
  
  void addQuadriliteralRootList();
  
  void addTrilateralRootList();
  
  void addTrilateralRootMap(char paramChar);
  
  boolean containsQuadriliteralRoot(String paramString);
  
  boolean containsQuadriliteralCharRoot(char paramChar);
  
  boolean containsTrilateralRoot(String paramString);
  
  boolean containsTrilateralCharRoot(char paramChar);
  
  int getQuadriliteralRootList(String paramString);
  
  int getTrilateralRootList(String paramString);
  
  List<Root> getQuadriliteralRootMap(char paramChar);
  
  List<Root> getTrilateralRootMap(char paramChar);
  
  Root getQuadriliteralRoot(String paramString);
  
  Root getTrilateralRoot(String paramString);
  
  String getLenTrilateralRoot(String paramString, int paramInt);
  
  String getLenQuadriliteralRoot(String paramString, int paramInt);
  
  void clear();
}
