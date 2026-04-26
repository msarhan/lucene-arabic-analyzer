package AlKhalil2.morphology.underived.unvoweled.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.underived.unvoweled.factory.UnvoweledUnderivedFactory;
import AlKhalil2.morphology.underived.unvoweled.interfaces.IUnvoweledUnderived;
import AlKhalil2.morphology.underived.unvoweled.xml.XMLUnvoweledUnderived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.HashMap;
import java.util.Map;

public class UnvoweledUnderivedPropernoun extends UnvoweledUnderivedFactory implements IUnvoweledUnderived {
  public UnvoweledUnderivedPropernoun() {
    this.unvoweledForm = new HashMap();
  }

  public void addUnvoweledForm() {
    if (isEmpty())
      this.unvoweledForm = AlKhalilDataCache.compute(
          "UnderivedUnvoweledPropernoun",
          () -> (new XMLUnvoweledUnderived()).parserXml((new File()).openFileXml(Database.xmlUnderivedUnvoweledPropernoun)));
  }
}
