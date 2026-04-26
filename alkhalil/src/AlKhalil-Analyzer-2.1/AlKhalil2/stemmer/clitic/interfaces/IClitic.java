package AlKhalil2.stemmer.clitic.interfaces;

import java.util.List;

public interface IClitic {
  void addClitics();
  
  List getListsClitics(String paramString);
  
  void clear();
}
