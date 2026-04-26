package AlKhalil2.morphology.derived.formulas.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.derived.formulas.factory.FormulasDerivedFactory;
import AlKhalil2.morphology.derived.formulas.interfaces.IFormulasDerived;
import AlKhalil2.morphology.derived.formulas.xml.XMLFormulasDerived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormulasDerivedVerbal extends FormulasDerivedFactory implements IFormulasDerived {
  public FormulasDerivedVerbal() {
    this.formula = new HashMap<>();
  }

  public void addFormulaList(int len) {
    if (!containsValFormula(len)) {
      this.formula.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedVFormulas:" + len,
          () -> {
            Database.setDerivedVFormulas(len);
            return (new XMLFormulasDerived()).parserXml((new File()).openFileXml(Database.getDerivedVFormulas()));
          }));
    }
  }
}
