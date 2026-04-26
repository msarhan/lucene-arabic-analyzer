package AlKhalil2.morphology.derived.voweled.impl;

import AlKhalil2.io.File;
import AlKhalil2.morphology.derived.voweled.factory.VoweledDerivedFactory;
import AlKhalil2.morphology.derived.voweled.interfaces.IVoweledDerived;
import AlKhalil2.morphology.derived.voweled.xml.XMLVoweledDerived;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoweledDerivedNominal extends VoweledDerivedFactory implements IVoweledDerived {
  public VoweledDerivedNominal() {
    this.voweledLemmeDiacMap = new HashMap<Integer, List>();
    this.voweledLemmeCanonicMap = new HashMap<Integer, List>();
    this.voweledPatternDiacMap = new HashMap<Integer, List>();
    this.voweledPatternCanonicMap = new HashMap<Integer, List>();
  }

  public void addVoweledLemmeDiacMap(int len) {
    if (!containsIdVoweledLemmeDiac(len)) {
      this.voweledLemmeDiacMap.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedVoweledPLDN:" + len,
          () -> {
            Database.setDerivedVoweledPLDN(len);
            return (new XMLVoweledDerived()).parserXml((new File()).openFileXml(Database.getDerivedVoweledPLDN()));
          }));
    }
  }

  public void addVoweledLemmeCanonicMap(int len) {
    if (!containsIdVoweledLemmeCanonic(len)) {
      this.voweledLemmeCanonicMap.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedVoweledPLCN:" + len,
          () -> {
            Database.setDerivedVoweledPLCN(len);
            return (new XMLVoweledDerived()).parserXml((new File()).openFileXml(Database.getDerivedVoweledPLCN()));
          }));
    }
  }

  public void addVoweledPatternDiacMap(int len) {
    if (!containsIdVoweledPatternDiac(len)) {
      this.voweledPatternDiacMap.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedVoweledPSDN:" + len,
          () -> {
            Database.setDerivedVoweledPSDN(len);
            return (new XMLVoweledDerived()).parserXml((new File()).openFileXml(Database.getDerivedVoweledPSDN()));
          }));
    }
  }

  public void addVoweledPatternCanonicMap(int len) {
    if (!containsIdVoweledPatternCanonic(len)) {
      this.voweledPatternCanonicMap.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedVoweledPSCN:" + len,
          () -> {
            Database.setDerivedVoweledPSCN(len);
            return (new XMLVoweledDerived()).parserXml((new File()).openFileXml(Database.getDerivedVoweledPSCN()));
          }));
    }
  }
}
