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

public class VoweledDerivedVerbal extends VoweledDerivedFactory implements IVoweledDerived {
  public VoweledDerivedVerbal() {
    this.voweledLemmeDiacMap = new HashMap<Integer, List>();
    this.voweledLemmeCanonicMap = new HashMap<Integer, List>();
    this.voweledPatternDiacMap = new HashMap<Integer, List>();
    this.voweledPatternCanonicMap = new HashMap<Integer, List>();
  }

  public void addVoweledLemmeDiacMap(int len) {
    if (!containsIdVoweledLemmeDiac(len)) {
      this.voweledLemmeDiacMap.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedVoweledPLDV:" + len,
          () -> {
            Database.setDerivedVoweledPLDV(len);
            return (new XMLVoweledDerived()).parserXml((new File()).openFileXml(Database.getDerivedVoweledPLDV()));
          }));
    }
  }

  public void addVoweledLemmeCanonicMap(int len) {
    if (!containsIdVoweledLemmeCanonic(len)) {
      this.voweledLemmeCanonicMap.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedVoweledPLCV:" + len,
          () -> {
            Database.setDerivedVoweledPLCV(len);
            return (new XMLVoweledDerived()).parserXml((new File()).openFileXml(Database.getDerivedVoweledPLCV()));
          }));
    }
  }

  public void addVoweledPatternDiacMap(int len) {
    if (!containsIdVoweledPatternDiac(len)) {
      this.voweledPatternDiacMap.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedVoweledPSDV:" + len,
          () -> {
            Database.setDerivedVoweledPSDV(len);
            return (new XMLVoweledDerived()).parserXml((new File()).openFileXml(Database.getDerivedVoweledPSDV()));
          }));
    }
  }

  public void addVoweledPatternCanonicMap(int len) {
    if (!containsIdVoweledPatternCanonic(len)) {
      this.voweledPatternCanonicMap.put(Integer.valueOf(len), AlKhalilDataCache.compute(
          "DerivedVoweledPSCV:" + len,
          () -> {
            Database.setDerivedVoweledPSCV(len);
            return (new XMLVoweledDerived()).parserXml((new File()).openFileXml(Database.getDerivedVoweledPSCV()));
          }));
    }
  }
}
