package AlKhalil2.morphology.exceptional.analyzer;

import AlKhalil2.morphology.exceptional.Exceptional;
import AlKhalil2.morphology.exceptional.model.Exceptional;
import AlKhalil2.morphology.result.model.Result;
import java.util.ArrayList;
import java.util.List;

public class AnalyzerExceptionalWords {
  Exceptional exceptional = new Exceptional();
  
  public AnalyzerExceptionalWords() {
    this.exceptional = new Exceptional();
  }
  
  public List analyzedSegment(String normalizedWord, String unvoweledWord) {
    List<Result> result = new ArrayList();
    if (this.exceptional.isEmpty())
      this.exceptional.addExceptionalForm(); 
    if (this.exceptional.containsValue(unvoweledWord)) {
      Exceptional ew = this.exceptional.getExceptionalFormValue(unvoweledWord);
      String lemme = "الله";
      result.add(new Result(ew.getVoweledform(), ew.getPrefix(), ew.getPrefix(), ew.getStem(), ew.getType(), "#", "#", lemme, "#", "#", "#", ew.getSuffix(), ew.getSuffix(), "1"));
    } 
    return result;
  }
  
  public void clear() {
    this.exceptional.clear();
  }
}
