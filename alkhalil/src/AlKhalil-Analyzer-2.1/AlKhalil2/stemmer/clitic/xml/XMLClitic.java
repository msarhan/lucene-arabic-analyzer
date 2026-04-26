package AlKhalil2.stemmer.clitic.xml;

import AlKhalil2.stemmer.clitic.model.Clitic;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLClitic extends DefaultHandler {
  private Map<String, List> lAffixe;
  
  private String _qName;
  
  public Map<String, List> parserXml(InputStream path, String qName) {
    XMLClitic parse = new XMLClitic();
    parse.lAffixe = new HashMap<String, List>();
    parse._qName = qName;
    try {
      SAXParserFactory spfac = SAXParserFactory.newInstance();
      SAXParser sp = spfac.newSAXParser();
      sp.parse(path, parse);
    } catch (ParserConfigurationException ex) {
      System.out.println(" : " + ex);
    } catch (SAXException ex) {
      System.out.println(" : " + ex);
    } catch (IOException ex) {
      System.out.println(" : " + ex);
    } 
    return parse.lAffixe;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase(this._qName)) {
      Clitic affix = new Clitic();
      String unvoweled = attributes.getValue("unvoweledform");
      affix.setUnvoweledform(unvoweled);
      affix.setVoweledform(attributes.getValue("voweledform"));
      affix.setDesc(attributes.getValue("desc"));
      affix.setClasse(attributes.getValue("classe"));
      if (this.lAffixe.containsKey(unvoweled)) {
        ((List<Clitic>)this.lAffixe.get(unvoweled)).add(affix);
      } else {
        List<Clitic> list = new ArrayList();
        list.add(affix);
        this.lAffixe.put(unvoweled, list);
      } 
    } 
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
