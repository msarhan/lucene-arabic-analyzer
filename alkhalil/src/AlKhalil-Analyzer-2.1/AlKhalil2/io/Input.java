package AlKhalil2.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTextPane;

public class Input {
  public static Set readFileToSet(String nameFileIn, String nameCharset) {
    Set<String> lset = new HashSet();
    BufferedReader in = null;
    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nameFileIn)), nameCharset));
      String line = "";
      while ((line = in.readLine()) != null)
        lset.add(line); 
      in.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
    return lset;
  }
  
  public static List readFileToList(String nameFileIn, String nameCharset) {
    List<String> list = new ArrayList();
    BufferedReader in = null;
    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nameFileIn)), nameCharset));
      String line = "";
      while ((line = in.readLine()) != null)
        list.add(line); 
      in.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
    return list;
  }
  
  public static List readFileToListNemlar_AlKhalil2(String nameFileIn, String nameCharset) {
    List<String> list = new ArrayList();
    BufferedReader in = null;
    try {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nameFileIn)), nameCharset));
      String line = "";
      while ((line = in.readLine()) != null) {
        String str = "";
        if (line.startsWith("{")) {
          str = "false;#;" + line.charAt(1);
        } else {
          String[] arrayOfString = line.split(":");
          if (arrayOfString.length == 2)
            str = "false;" + (((arrayOfString[1].split(";")).length > 1) ? arrayOfString[1].split(";")[0] : arrayOfString[1]) + ";" + "" + arrayOfString[0]; 
          if (arrayOfString.length == 1)
            str = "false;;" + arrayOfString[0]; 
        } 
        list.add(str);
      } 
      String st = "false;------;------";
      list.add(st);
      in.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
    return list;
  }
  
  public static void readFile(File fichier, String nameCharset, JTextPane inputText, int seuil) {
    StringBuilder buf = new StringBuilder();
    try {
      inputText.setText("");
      BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fichier), nameCharset));
      int i = 0;
      String ligne;
      while (i < seuil && (ligne = in.readLine()) != null) {
        buf.append(ligne).append('\n');
        i++;
      } 
      inputText.setText(buf.toString());
    } catch (FileNotFoundException e) {
      System.out.println("Error : " + e);
    } catch (IOException e) {
      System.out.println("Error : " + e);
    } 
  }
}
