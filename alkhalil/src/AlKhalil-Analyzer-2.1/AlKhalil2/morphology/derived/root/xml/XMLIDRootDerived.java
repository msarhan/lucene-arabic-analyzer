package AlKhalil2.morphology.derived.root.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLIDRootDerived extends DefaultHandler {
  private Map<String, Integer> idRootXml;
  
  public Map parserXml(InputStream path) {
    XMLIDRootDerived parse = new XMLIDRootDerived();
    parse.idRootXml = new HashMap<String, Integer>();
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
    return parse.idRootXml;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("root")) {
      int id = Integer.parseInt(attributes.getValue("id"));
      String val = attributes.getValue("val");
      this.idRootXml.put(val, Integer.valueOf(id));
    } 
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
