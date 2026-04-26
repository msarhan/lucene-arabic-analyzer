package AlKhalil2.morphology.exceptional;

import AlKhalil2.io.File;
import AlKhalil2.morphology.exceptional.xml.XMLExceptional;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.HashMap;
import java.util.Map;

public class Exceptional {
  protected Map exceptional = new HashMap<Object, Object>();

  public boolean isEmpty() {
    return this.exceptional.isEmpty();
  }

  public void addExceptionalForm() {
    if (isEmpty())
      this.exceptional = AlKhalilDataCache.compute(
          "Exceptional",
          () -> (new XMLExceptional()).parserXml((new File()).openFileXml(Database.xmlExceptional)));
  }

  public AlKhalil2.morphology.exceptional.model.Exceptional getExceptionalFormValue(String unvoweled) {
    return (AlKhalil2.morphology.exceptional.model.Exceptional)this.exceptional.get(unvoweled);
  }

  public boolean containsValue(String unvoweled) {
    return this.exceptional.containsKey(unvoweled);
  }

  public void clear() {
    this.exceptional.clear();
  }
}
