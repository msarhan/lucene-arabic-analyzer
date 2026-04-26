package AlKhalil2.morphology.derived.root.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.derived.root.factory.RootFactory;
import AlKhalil2.morphology.derived.root.interfaces.IRootDerived;
import AlKhalil2.morphology.derived.root.xml.XMLIDRootDerived;
import AlKhalil2.morphology.derived.root.xml.XMLRootDerived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RootDerivedVerbal extends RootFactory implements IRootDerived {
  public RootDerivedVerbal() {
    this.quadriliteralRootMap = new HashMap<>();
    this.trilateralRootMap = new HashMap<>();
    this.quadriliteralRootList = new HashMap();
    this.trilateralRootList = new HashMap();
  }

  public void addQuadriliteralRootList() {
    this.quadriliteralRootList = AlKhalilDataCache.compute(
        "DerivedVQRoot",
        () -> (new XMLIDRootDerived()).parserXml((new File()).openFileXml(Database.xmlDerivedVQRoot)));
  }

  public void addTrilateralRootList() {
    this.trilateralRootList = AlKhalilDataCache.compute(
        "DerivedVTRoot",
        () -> (new XMLIDRootDerived()).parserXml((new File()).openFileXml(Database.xmlDerivedVTRoot)));
  }

  public void addTrilateralRootMap(char rootC1) {
    this.trilateralRootMap.put(Character.valueOf(rootC1), AlKhalilDataCache.compute(
        "DerivedVTRootC:" + rootC1,
        () -> {
          Database.setDerivedVTRootC(rootC1);
          return (new XMLRootDerived()).parserXml((new File()).openFileXml(File.getArabicURL(Database.getDerivedVTRootC())));
        }));
  }

  public void addQuadriliteralRootMap(char rootC1) {
    this.quadriliteralRootMap.put(Character.valueOf(rootC1), AlKhalilDataCache.compute(
        "DerivedVQRootC:" + rootC1,
        () -> {
          Database.setDerivedVQRootC(rootC1);
          return (new XMLRootDerived()).parserXml((new File()).openFileXml(File.getArabicURL(Database.getDerivedVQRootC())));
        }));
  }
}
