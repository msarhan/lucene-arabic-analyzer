package AlKhalil2.morphology.underived.type.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.underived.type.factory.TypeFactory;
import AlKhalil2.morphology.underived.type.interfaces.ITypeUnderived;
import AlKhalil2.morphology.underived.type.xml.XMLTypeUnderived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.ArrayList;
import java.util.List;

public class TypeUnderivedPropernoun extends TypeFactory implements ITypeUnderived {
  public TypeUnderivedPropernoun() {
    this.allType = new ArrayList();
  }

  public void addAllType() {
    if (isEmpty())
      this.allType = AlKhalilDataCache.compute(
          "UnderivedTypePropernoun",
          () -> (new XMLTypeUnderived()).parserXml((new File()).openFileXml(Database.xmlUnderivedTypePropernoun)));
  }
}
