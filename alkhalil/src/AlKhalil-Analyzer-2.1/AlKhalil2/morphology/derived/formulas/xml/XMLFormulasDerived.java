package AlKhalil2.morphology.derived.formulas.xml;

import AlKhalil2.morphology.derived.formulas.model.Formulas;
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

public class XMLFormulasDerived extends DefaultHandler {
  private List iFormulasXml;
  
  public List parserXml(InputStream path) {
    XMLFormulasDerived parse = new XMLFormulasDerived();
    parse.iFormulasXml = new ArrayList();
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
    return parse.iFormulasXml;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("formula")) {
      String idR = attributes.getValue("idR");
      String idDP = attributes.getValue("idDP");
      String idCP = attributes.getValue("idCP");
      String idDL = attributes.getValue("idDL");
      String idCL = attributes.getValue("idCL");
      String idT = attributes.getValue("idT");
      String idPT = attributes.getValue("idPT");
      this.iFormulasXml.add(new Formulas(idR, idDP, idCP, idDL, idCL, idT, idPT));
    } 
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
