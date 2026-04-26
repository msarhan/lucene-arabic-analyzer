package AlKhalil2.morphology.derived.root.factory;

import AlKhalil2.morphology.derived.root.model.Root;
import java.util.List;
import java.util.Map;

public abstract class RootFactory {
  protected Map<Character, List<Root>> trilateralRootMap;
  
  protected Map<Character, List<Root>> quadriliteralRootMap;
  
  protected Map<String, Integer> trilateralRootList;
  
  protected Map<String, Integer> quadriliteralRootList;
  
  public boolean isEmptyQuadriliteralRootMap() {
    return this.quadriliteralRootMap.isEmpty();
  }
  
  public boolean isEmptyQuadriliteralRootList() {
    return this.quadriliteralRootList.isEmpty();
  }
  
  public boolean isEmptyTrilateralRootList() {
    return this.trilateralRootList.isEmpty();
  }
  
  public boolean isEmptyTrilateralRootMap() {
    return this.trilateralRootMap.isEmpty();
  }
  
  public boolean containsQuadriliteralRoot(String root) {
    if (this.quadriliteralRootList != null)
      return this.quadriliteralRootList.containsKey(root); 
    return false;
  }
  
  public boolean containsQuadriliteralCharRoot(char rootC1) {
    if (this.quadriliteralRootMap != null)
      return this.quadriliteralRootMap.containsKey(Character.valueOf(rootC1)); 
    return false;
  }
  
  public boolean containsTrilateralRoot(String root) {
    if (this.trilateralRootList != null)
      return this.trilateralRootList.containsKey(root); 
    return false;
  }
  
  public boolean containsTrilateralCharRoot(char rootC1) {
    if (this.trilateralRootMap != null)
      return this.trilateralRootMap.containsKey(Character.valueOf(rootC1)); 
    return false;
  }
  
  public int getQuadriliteralRootList(String root) {
    return ((Integer)this.quadriliteralRootList.get(root)).intValue();
  }
  
  public int getTrilateralRootList(String root) {
    return ((Integer)this.trilateralRootList.get(root)).intValue();
  }
  
  public List<Root> getQuadriliteralRootMap(char rootC1) {
    return this.quadriliteralRootMap.get(Character.valueOf(rootC1));
  }
  
  public List<Root> getTrilateralRootMap(char rootC1) {
    return this.trilateralRootMap.get(Character.valueOf(rootC1));
  }
  
  public Root getQuadriliteralRoot(String root) {
    int index = getQuadriliteralRootList(root);
    return getQuadriliteralRootMap(root.charAt(0)).get(index);
  }
  
  public Root getTrilateralRoot(String root) {
    int index = getTrilateralRootList(root);
    return getTrilateralRootMap(root.charAt(0)).get(index);
  }
  
  public String getLenTrilateralRoot(String root, int len) {
    switch (len) {
      case 1:
        return getTrilateralRoot(root).getLen1();
      case 2:
        return getTrilateralRoot(root).getLen2();
      case 3:
        return getTrilateralRoot(root).getLen3();
      case 4:
        return getTrilateralRoot(root).getLen4();
      case 5:
        return getTrilateralRoot(root).getLen5();
      case 6:
        return getTrilateralRoot(root).getLen6();
      case 7:
        return getTrilateralRoot(root).getLen7();
      case 8:
        return getTrilateralRoot(root).getLen8();
      case 9:
        return getTrilateralRoot(root).getLen9();
      case 10:
        return getTrilateralRoot(root).getLen10();
    } 
    return "";
  }
  
  public String getLenQuadriliteralRoot(String root, int len) {
    switch (len) {
      case 1:
        return getQuadriliteralRoot(root).getLen1();
      case 2:
        return getQuadriliteralRoot(root).getLen2();
      case 3:
        return getQuadriliteralRoot(root).getLen3();
      case 4:
        return getQuadriliteralRoot(root).getLen4();
      case 5:
        return getQuadriliteralRoot(root).getLen5();
      case 6:
        return getQuadriliteralRoot(root).getLen6();
      case 7:
        return getQuadriliteralRoot(root).getLen7();
      case 8:
        return getQuadriliteralRoot(root).getLen8();
      case 9:
        return getQuadriliteralRoot(root).getLen9();
      case 10:
        return getQuadriliteralRoot(root).getLen10();
    } 
    return "";
  }
  
  public void clear() {
    this.quadriliteralRootMap.clear();
    this.trilateralRootMap.clear();
    this.quadriliteralRootList.clear();
    this.trilateralRootList.clear();
  }
}
