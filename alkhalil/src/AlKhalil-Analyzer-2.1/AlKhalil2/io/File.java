package AlKhalil2.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;

public class File {
  public InputStream openFileXml(String nameFile) {
    try {
      return getClass().getResource(nameFile).openStream();
    } catch (IOException ex) {
      Logger.getLogger(File.class.getName()).log(Level.SEVERE, (String)null, ex);
      return null;
    } 
  }
  
  public static void openFileChooser(JTextPane inputText, String nameCharset, int seuil) {
    JFileChooser jfc = new JFileChooser();
    int resultat = jfc.showOpenDialog(null);
    if (resultat == 0) {
      Input.readFile(jfc.getSelectedFile(), nameCharset, inputText, seuil);
    } else {
      System.out.println("Error : Fichier n'existe pas");
    } 
  }
  
  public static String getArabicURL(String str) {
    if (str.endsWith("%d8%a1.xml"))
      return str.substring(0, str.length() - 10) + 'ء' + ".xml"; 
    if (str.endsWith("%d8%a8.xml"))
      return str.substring(0, str.length() - 10) + 'ب' + ".xml"; 
    if (str.endsWith("%d8%aa.xml"))
      return str.substring(0, str.length() - 10) + 'ت' + ".xml"; 
    if (str.endsWith("%d8%ab.xml"))
      return str.substring(0, str.length() - 10) + 'ث' + ".xml"; 
    if (str.endsWith("%d8%ac.xml"))
      return str.substring(0, str.length() - 10) + 'ج' + ".xml"; 
    if (str.endsWith("%d8%ad.xml"))
      return str.substring(0, str.length() - 10) + 'ح' + ".xml"; 
    if (str.endsWith("%d8%ae.xml"))
      return str.substring(0, str.length() - 10) + 'خ' + ".xml"; 
    if (str.endsWith("%d8%af.xml"))
      return str.substring(0, str.length() - 10) + 'د' + ".xml"; 
    if (str.endsWith("%d8%b0.xml"))
      return str.substring(0, str.length() - 10) + 'ذ' + ".xml"; 
    if (str.endsWith("%d8%b1.xml"))
      return str.substring(0, str.length() - 10) + 'ر' + ".xml"; 
    if (str.endsWith("%d8%b2.xml"))
      return str.substring(0, str.length() - 10) + 'ز' + ".xml"; 
    if (str.endsWith("%d8%b3.xml"))
      return str.substring(0, str.length() - 10) + 'س' + ".xml"; 
    if (str.endsWith("%d8%b4.xml"))
      return str.substring(0, str.length() - 10) + 'ش' + ".xml"; 
    if (str.endsWith("%d8%b5.xml"))
      return str.substring(0, str.length() - 10) + 'ص' + ".xml"; 
    if (str.endsWith("%d8%b6.xml"))
      return str.substring(0, str.length() - 10) + 'ض' + ".xml"; 
    if (str.endsWith("%d8%b7.xml"))
      return str.substring(0, str.length() - 10) + 'ط' + ".xml"; 
    if (str.endsWith("%d8%b8.xml"))
      return str.substring(0, str.length() - 10) + 'ظ' + ".xml"; 
    if (str.endsWith("%d8%b9.xml"))
      return str.substring(0, str.length() - 10) + 'ع' + ".xml"; 
    if (str.endsWith("%d8%ba.xml"))
      return str.substring(0, str.length() - 10) + 'غ' + ".xml"; 
    if (str.endsWith("%d9%81.xml"))
      return str.substring(0, str.length() - 10) + 'ف' + ".xml"; 
    if (str.endsWith("%d9%82.xml"))
      return str.substring(0, str.length() - 10) + 'ق' + ".xml"; 
    if (str.endsWith("%d9%83.xml"))
      return str.substring(0, str.length() - 10) + 'ك' + ".xml"; 
    if (str.endsWith("%d9%84.xml"))
      return str.substring(0, str.length() - 10) + 'ل' + ".xml"; 
    if (str.endsWith("%d9%85.xml"))
      return str.substring(0, str.length() - 10) + 'م' + ".xml"; 
    if (str.endsWith("%d9%86.xml"))
      return str.substring(0, str.length() - 10) + 'ن' + ".xml"; 
    if (str.endsWith("%d9%87.xml"))
      return str.substring(0, str.length() - 10) + 'ه' + ".xml"; 
    if (str.endsWith("%d9%88.xml"))
      return str.substring(0, str.length() - 10) + 'و' + ".xml"; 
    if (str.endsWith("%d9%8a.xml"))
      return str.substring(0, str.length() - 10) + 'ي' + ".xml"; 
    return str;
  }
}
