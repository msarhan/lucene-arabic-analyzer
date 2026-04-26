package AlKhalil2.morphology.derived.postag.xml;

import AlKhalil2.morphology.derived.postag.model.POSTag;
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

public class XMLPOSTagDerived extends DefaultHandler {
  private List iPosTagXml;
  
  private boolean isNoun;
  
  public XMLPOSTagDerived() {}
  
  public XMLPOSTagDerived(boolean isNoun) {
    this.isNoun = isNoun;
  }
  
  public List parserXml(InputStream path, boolean isNoun) {
    XMLPOSTagDerived parse = new XMLPOSTagDerived(isNoun);
    parse.iPosTagXml = new ArrayList();
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
    return parse.iPosTagXml;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("postag"))
      if (this.isNoun) {
        POSTag postag = new POSTag(attributes.getValue("id"), attributes.getValue("cas"), attributes.getValue("ncg"));
        this.iPosTagXml.add(postag);
      } else {
        POSTag postag = new POSTag(attributes.getValue("id"), attributes.getValue("aug"), attributes.getValue("cas"), attributes.getValue("ncg"), attributes.getValue("trans"));
        this.iPosTagXml.add(postag);
      }  
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
