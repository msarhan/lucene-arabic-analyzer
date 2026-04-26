package AlKhalil2.stemmer.analyzer;

import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.stemmer.model.Segment;
import AlKhalil2.util.ArabMethod;

public class AnalyzerClitic {
  public Result analyzedSegment(String normalizedWord, String unvoweledWord, Segment segment) {
    Result r = new Result();
    String vpref = segment.getProclitic().getVoweledform();
    String vsuff = segment.getEnclitic().getVoweledform();
    String vowledWord = vpref + vsuff;
    if (!ArabMethod.notCompatible(normalizedWord, vowledWord, segment, false)) {
      String decPref = vpref.equals("") ? "#" : (vpref + ":" + segment.getProclitic().getDesc());
      String decSuff = vsuff.equals("") ? "#" : (vsuff + ":" + segment.getEnclitic().getDesc());
      r = new Result(vowledWord, decPref, "#", "#", "#", "#", "#", "#", "#", "#", decSuff, "0");
    } 
    return r;
  }
}
