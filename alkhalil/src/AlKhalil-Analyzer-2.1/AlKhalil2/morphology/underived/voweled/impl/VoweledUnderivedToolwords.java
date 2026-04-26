package AlKhalil2.morphology.underived.voweled.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.underived.model.Toolwords;
import AlKhalil2.morphology.underived.voweled.factory.VoweledUnderivedFactory;
import AlKhalil2.morphology.underived.voweled.interfaces.IVoweledUnderived;
import AlKhalil2.morphology.underived.voweled.xml.XMLVoweledUnderived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;

public class VoweledUnderivedToolwords extends VoweledUnderivedFactory implements IVoweledUnderived {
  public void addVoweledForm() {
    if (isEmpty())
      this.allVoweled = AlKhalilDataCache.compute(
          "UnderivedVoweledToolwords",
          () -> (new XMLVoweledUnderived()).parserXml((new File()).openFileXml(Database.xmlUnderivedVoweledToolwords)));
  }

  public Toolwords getVoweledFormValue(int idVoweled) {
    return (Toolwords) this.allVoweled.get(idVoweled - 1);
  }
}
