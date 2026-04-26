package AlKhalil2.stemmer.clitic.impl;

import AlKhalil2.io.File;
import AlKhalil2.stemmer.clitic.factory.CliticFactory;
import AlKhalil2.stemmer.clitic.interfaces.IClitic;
import AlKhalil2.stemmer.clitic.xml.XMLClitic;
import AlKhalil2.util.cache.AlKhalilDataCache;
import AlKhalil2.util.constants.Database;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Proclitic extends CliticFactory implements IClitic {
  public Proclitic() {
    this.clitic = new HashMap<String, List>();
  }

  public void addClitics() {
    if (this.clitic.isEmpty())
      this.clitic = AlKhalilDataCache.compute(
          "Proclitic",
          () -> (new XMLClitic()).parserXml((new File()).openFileXml(Database.xmlProclitic), Database.proclitic));
  }

  public List getListsClitics(String token) {
    List listClitics = new LinkedList();
    int size = token.length();
    int ip = 0;
    while (ip < size && ip <= 5) {
      listClitics.addAll(possibleClitics(token.substring(0, ip)));
      ip++;
    }
    return listClitics;
  }
}
