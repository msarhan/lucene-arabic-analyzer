package AlKhalil2.morphology.analyzer;

import AlKhalil2.morphology.derived.analyzer.impl.AnalyzerDerivedNominal;
import AlKhalil2.morphology.derived.analyzer.impl.AnalyzerDerivedVerbal;
import AlKhalil2.morphology.derived.analyzer.interfaces.IAnalyzerDerived;
import AlKhalil2.morphology.exceptional.analyzer.AnalyzerExceptionalWords;
import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.morphology.underived.analyzer.AnalyzerUnderivedPropernoun;
import AlKhalil2.morphology.underived.analyzer.AnalyzerUnderivedToolwords;
import AlKhalil2.morphology.underived.analyzer.factory.AnalyzerUnderivedFactory;
import AlKhalil2.stemmer.Stemming;
import AlKhalil2.stemmer.analyzer.AnalyzerClitic;
import AlKhalil2.stemmer.model.Segment;
import AlKhalil2.util.ArabCharUtil;
import AlKhalil2.util.ArabMethod;
import AlKhalil2.util.constants.Static;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnalyzerTokens {
  private final AnalyzerExceptionalWords AnalyzerEW = new AnalyzerExceptionalWords();
  
  private final AnalyzerUnderivedFactory AnalyzerPN = new AnalyzerUnderivedPropernoun();
  
  private final AnalyzerUnderivedFactory AnalyzerTW = new AnalyzerUnderivedToolwords();
  
  private final IAnalyzerDerived AnalyzerN = new AnalyzerDerivedNominal();
  
  private final IAnalyzerDerived AnalyzerV = new AnalyzerDerivedVerbal();
  
  private final AnalyzerClitic AnalyzerAff = new AnalyzerClitic();
  
  private final Stemming Gsegment = new Stemming();
  
  public List analyzerToken(String normalizedWord) {
    String unvoweledWord = ArabMethod.getUnvoweledWord(normalizedWord);
    normalizedWord = ArabCharUtil.correctErreur(normalizedWord);
    List<Result> result = new ArrayList();
    result = this.AnalyzerEW.analyzedSegment(normalizedWord, unvoweledWord);
    if (!result.isEmpty())
      return result; 
    List<Segment> Segments = this.Gsegment.getListsSegment(unvoweledWord);
    Iterator<Segment> it_Seg = Segments.iterator();
    while (it_Seg.hasNext()) {
      Segment segment = it_Seg.next();
      if (Static.isPropernounAnalyzed)
        result.addAll(this.AnalyzerPN.analyzedSegment(normalizedWord, unvoweledWord, segment)); 
      if (Static.isToolwordsAnalyzed)
        result.addAll(this.AnalyzerTW.analyzedSegment(normalizedWord, unvoweledWord, segment)); 
      if (Static.isNominalAnalyzed)
        result.addAll(this.AnalyzerN.analyzedSegment(normalizedWord, unvoweledWord, segment)); 
      if (Static.isVerbalAnalyzed)
        result.addAll(this.AnalyzerV.analyzedSegment(normalizedWord, unvoweledWord, segment)); 
      if (Static.isCliticsAnalyzed)
        result.add(this.AnalyzerAff.analyzedSegment(normalizedWord, unvoweledWord, segment)); 
    } 
    return Result.sort(result);
  }
  
  public void clear() {
    this.AnalyzerEW.clear();
    this.AnalyzerPN.clear();
    this.AnalyzerTW.clear();
    this.AnalyzerN.clear();
    this.AnalyzerV.clear();
    this.Gsegment.clear();
  }
}
