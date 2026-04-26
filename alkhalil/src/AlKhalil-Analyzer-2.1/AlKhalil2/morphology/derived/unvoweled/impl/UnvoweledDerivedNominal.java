package AlKhalil2.morphology.derived.unvoweled.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.derived.unvoweled.factory.UnvoweledDerivedFactory;
import AlKhalil2.morphology.derived.unvoweled.interfaces.IUnvoweledDerived;
import AlKhalil2.morphology.derived.unvoweled.xml.XMLUnvoweledDerived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnvoweledDerivedNominal extends UnvoweledDerivedFactory implements IUnvoweledDerived {
  public UnvoweledDerivedNominal() {
    this.unvoweledPatternStem = new HashMap<Integer, List>();
  }

  public void addUnvoweledPatternDiacMap(int len) {
    if (!containsIdUnvoweled(len)) {
      this.unvoweledPatternStem.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedNUnvoweled:" + len,
          () -> {
            Database.setDerivedNUnvoweled(len);
            return (new XMLUnvoweledDerived()).parserXml((new File()).openFileXml(Database.getDerivedNUnvoweled()));
          }));
    }
  }
}
