package AlKhalil2.morphology.derived.type.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLTypeDerived extends DefaultHandler {
  private List iTypeXml;
  
  public List parserXml(InputStream path) {
    XMLTypeDerived parse = new XMLTypeDerived();
    parse.iTypeXml = new LinkedList();
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
    return parse.iTypeXml;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("type")) {
      String str = attributes.getValue("val") + ";" + attributes.getValue("priority");
      this.iTypeXml.add(str);
    } 
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
