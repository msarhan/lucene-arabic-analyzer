package AlKhalil2.morphology.underived.analyzer.factory;

import AlKhalil2.morphology.underived.type.interfaces.ITypeUnderived;
import AlKhalil2.morphology.underived.unvoweled.interfaces.IUnvoweledUnderived;
import AlKhalil2.morphology.underived.voweled.interfaces.IVoweledUnderived;
import AlKhalil2.stemmer.model.Segment;
import java.util.List;

public abstract class AnalyzerUnderivedFactory {
  protected IUnvoweledUnderived unvoweled;
  
  protected IVoweledUnderived voweled;
  
  protected ITypeUnderived type;
  
  public abstract List analyzedSegment(String paramString1, String paramString2, Segment paramSegment);
  
  public void clear() {
    this.unvoweled.clear();
    this.voweled.clear();
    this.type.clear();
  }
}
