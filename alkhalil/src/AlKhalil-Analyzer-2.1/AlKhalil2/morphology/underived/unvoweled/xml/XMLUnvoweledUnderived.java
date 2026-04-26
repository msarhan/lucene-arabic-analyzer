package AlKhalil2.morphology.underived.unvoweled.xml;

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

public class XMLUnvoweledUnderived extends DefaultHandler {
  private Map iUnvoweledUnderived;
  
  public Map parserXml(InputStream path) {
    XMLUnvoweledUnderived parse = new XMLUnvoweledUnderived();
    parse.iUnvoweledUnderived = new HashMap<Object, Object>();
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
    return parse.iUnvoweledUnderived;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("propernoun")) {
      String unvoweledform = attributes.getValue("unvoweledform");
      String ids = attributes.getValue("ids");
      this.iUnvoweledUnderived.put(unvoweledform, ids);
    } 
    if (qName.equalsIgnoreCase("toolword")) {
      String unvoweledform = attributes.getValue("unvoweledform");
      String ids = attributes.getValue("ids");
      this.iUnvoweledUnderived.put(unvoweledform, ids);
    } 
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
