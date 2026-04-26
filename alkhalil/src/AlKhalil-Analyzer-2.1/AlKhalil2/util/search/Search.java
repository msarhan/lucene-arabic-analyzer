package AlKhalil2.util.search;

import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.text.indexation.Indexation;
import AlKhalil2.util.ArabMethod;
import AlKhalil2.util.constants.Static;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Search {
  public Set<String> byWordSearch(String word) {
    Set<String> lWords = new HashSet<String>();
    Iterator<String> it = Indexation.getiWordsID().keySet().iterator();
    while (it.hasNext()) {
      String st = it.next();
      if (!ArabMethod.notCompatible(word, st))
        lWords.add(st); 
    } 
    return lWords;
  }
  
  public Set<String> byRootSearch(String root) {
    Set<String> lWords = new HashSet<String>();
    Iterator<String> it = Indexation.getiWordsID().keySet().iterator();
    while (it.hasNext()) {
      String st = it.next();
      Iterator<Result> it_r = ((List<Result>)Static.allResults.get(st)).iterator();
      while (it_r.hasNext()) {
        Result res = it_r.next();
        if (root.equals(res.getRoot()))
          lWords.add(st); 
      } 
    } 
    return lWords;
  }
  
  public Set<String> byLemmaSearch(String lemme) {
    Set<String> lWords = new HashSet<String>();
    Iterator<String> it = Indexation.getiWordsID().keySet().iterator();
    while (it.hasNext()) {
      String st = it.next();
      Iterator<Result> it_r = ((List<Result>)Static.allResults.get(st)).iterator();
      while (it_r.hasNext()) {
        Result res = it_r.next();
        if (!ArabMethod.notCompatible(lemme, res.getLemma()))
          lWords.add(st); 
      } 
    } 
    return lWords;
  }
}
