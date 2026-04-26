package AlKhalil2.morphology.derived.analyzer.interfaces;

import AlKhalil2.stemmer.model.Segment;
import java.util.List;

public interface IAnalyzerDerived {
  List analyzedSegment(String paramString1, String paramString2, Segment paramSegment);
  
  List getPossiblesSolutions(String paramString, Segment paramSegment);
  
  void clear();
}
