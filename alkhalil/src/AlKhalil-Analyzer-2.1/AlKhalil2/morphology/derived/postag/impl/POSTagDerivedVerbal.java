package AlKhalil2.morphology.derived.postag.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.derived.postag.factory.POSTagDerivedFactory;
import AlKhalil2.morphology.derived.postag.interfaces.IPOSTagDerived;
import AlKhalil2.morphology.derived.postag.xml.XMLPOSTagDerived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.ArrayList;
import java.util.List;

public class POSTagDerivedVerbal extends POSTagDerivedFactory implements IPOSTagDerived {
  public POSTagDerivedVerbal() {
    this.posTag = new ArrayList();
  }

  public void addPosTag() {
    if (isEmpty())
      this.posTag = AlKhalilDataCache.compute(
          "DerivedPOSTagVerb",
          () -> (new XMLPOSTagDerived()).parserXml((new File()).openFileXml(Database.xmlDerivedPOSTagVerb), false));
  }
}
