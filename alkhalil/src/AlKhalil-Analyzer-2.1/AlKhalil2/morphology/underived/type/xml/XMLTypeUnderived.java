package AlKhalil2.morphology.underived.type.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLTypeUnderived extends DefaultHandler {
  private List iUnderived;
  
  public List parserXml(InputStream path) {
    XMLTypeUnderived parse = new XMLTypeUnderived();
    parse.iUnderived = new ArrayList();
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
    return parse.iUnderived;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("type"))
      this.iUnderived.add(attributes.getValue("val")); 
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
