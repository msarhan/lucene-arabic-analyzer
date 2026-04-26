package AlKhalil2.stemmer.clitic.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CliticFactory {
  protected Map<String, List> clitic;
  
  public List possibleClitics(String _affix) {
    if (this.clitic.containsKey(_affix))
      return this.clitic.get(_affix); 
    return new ArrayList();
  }
  
  public void clear() {
    this.clitic.clear();
  }
}
