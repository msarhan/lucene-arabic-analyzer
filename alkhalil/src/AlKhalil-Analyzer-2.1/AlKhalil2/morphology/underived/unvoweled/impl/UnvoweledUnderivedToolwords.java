package AlKhalil2.morphology.underived.unvoweled.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.underived.unvoweled.factory.UnvoweledUnderivedFactory;
import AlKhalil2.morphology.underived.unvoweled.interfaces.IUnvoweledUnderived;
import AlKhalil2.morphology.underived.unvoweled.xml.XMLUnvoweledUnderived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.HashMap;
import java.util.Map;

public class UnvoweledUnderivedToolwords extends UnvoweledUnderivedFactory implements IUnvoweledUnderived {
  public UnvoweledUnderivedToolwords() {
    this.unvoweledForm = new HashMap();
  }

  public void addUnvoweledForm() {
    if (isEmpty())
      this.unvoweledForm = AlKhalilDataCache.compute(
          "UnderivedUnvoweledToolwords",
          () -> (new XMLUnvoweledUnderived()).parserXml((new File()).openFileXml(Database.xmlUnderivedUnvoweledToolwords)));
  }
}
