package AlKhalil2.morphology.underived.voweled.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.underived.model.Propernoun;
import AlKhalil2.morphology.underived.voweled.factory.VoweledUnderivedFactory;
import AlKhalil2.morphology.underived.voweled.interfaces.IVoweledUnderived;
import AlKhalil2.morphology.underived.voweled.xml.XMLVoweledUnderived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;

public class VoweledUnderivedPropernoun extends VoweledUnderivedFactory implements IVoweledUnderived {
  public void addVoweledForm() {
    if (isEmpty())
      this.allVoweled = AlKhalilDataCache.compute(
          "UnderivedVoweledPropernoun",
          () -> (new XMLVoweledUnderived()).parserXml((new File()).openFileXml(Database.xmlUnderivedVoweledPropernoun)));
  }

  public Propernoun getVoweledFormValue(int idVoweled) {
    return (Propernoun) this.allVoweled.get(idVoweled - 1);
  }
}
