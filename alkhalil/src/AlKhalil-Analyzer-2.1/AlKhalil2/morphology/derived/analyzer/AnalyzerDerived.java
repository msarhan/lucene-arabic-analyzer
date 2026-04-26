package AlKhalil2.morphology.derived.analyzer;

import AlKhalil2.morphology.derived.analyzer.impl.AnalyzerDerivedNominal;
import AlKhalil2.morphology.derived.analyzer.impl.AnalyzerDerivedVerbal;
import AlKhalil2.morphology.derived.analyzer.interfaces.IAnalyzerDerived;
import AlKhalil2.morphology.exceptional.analyzer.AnalyzerExceptionalWords;
import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.stemmer.Stemming;
import AlKhalil2.stemmer.model.Segment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AnalyzerDerived {
  private AnalyzerExceptionalWords AnalyzerEW = new AnalyzerExceptionalWords();
  
  private IAnalyzerDerived AnalyzerN = new AnalyzerDerivedNominal();
  
  private IAnalyzerDerived AnalyzerV = new AnalyzerDerivedVerbal();
  
  private Stemming Gsegment = new Stemming();
  
  public AnalyzerDerived() {
    this.Gsegment = new Stemming();
    this.AnalyzerN = new AnalyzerDerivedNominal();
    this.AnalyzerV = new AnalyzerDerivedVerbal();
    this.AnalyzerEW = new AnalyzerExceptionalWords();
  }
  
  public List analyzerSegment(String normalizedWord, String unvoweledWord) {
    List<Result> result = new ArrayList<Result>();
    result = this.AnalyzerEW.analyzedSegment(normalizedWord, unvoweledWord);
    if (!result.isEmpty())
      return new ArrayList(); 
    List<Segment> Segments = this.Gsegment.getListsSegment(unvoweledWord);
    Iterator<Segment> it_Seg = Segments.iterator();
    while (it_Seg.hasNext()) {
      Segment segment = it_Seg.next();
      result.addAll(this.AnalyzerN.analyzedSegment(normalizedWord, unvoweledWord, segment));
      result.addAll(this.AnalyzerV.analyzedSegment(normalizedWord, unvoweledWord, segment));
    } 
    return Result.sort(result);
  }
}
