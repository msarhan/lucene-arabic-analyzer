package AlKhalil2.morphology.derived.type.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.derived.type.factory.TypeDerivedFactory;
import AlKhalil2.morphology.derived.type.interfaces.ITypeDerived;
import AlKhalil2.morphology.derived.type.xml.XMLTypeDerived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.ArrayList;
import java.util.List;

public class TypeDerivedVerbal extends TypeDerivedFactory implements ITypeDerived {
  public TypeDerivedVerbal() {
    this.type = new ArrayList();
  }

  public void addType() {
    if (isEmpty())
      this.type = AlKhalilDataCache.compute(
          "DerivedTypeVerb",
          () -> (new XMLTypeDerived()).parserXml((new File()).openFileXml(Database.xmlDerivedTypeVerb)));
  }
}
