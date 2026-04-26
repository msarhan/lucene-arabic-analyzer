package AlKhalil2.io;

import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.text.tokenization.Tokenization;
import AlKhalil2.util.Settings;
import AlKhalil2.util.constants.Static;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Output {
  public static void writeSetToFile(String nameFile, String nameCharset, Set<String> l) {
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(new File(nameFile)), nameCharset);
      Iterator<String> it = l.iterator();
      while (it.hasNext()) {
        String line = it.next();
        out.write(line + "\n");
      } 
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur ...." + e);
    } catch (IOException e) {
      System.out.println("Erreur ...." + e);
    } 
  }
  
  public static void writeListToFile(String nameFile, String nameCharset, List<String> l) {
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(new File(nameFile)), nameCharset);
      Iterator<String> it = l.iterator();
      while (it.hasNext()) {
        String normalizedWord = it.next();
        out.write(normalizedWord + "\n");
      } 
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur ...." + e);
    } catch (IOException e) {
      System.out.println("Erreur ...." + e);
    } 
  }
  
  public static void writeListToFileNemlar(String nameFile, String nameCharset, List<String> l) {
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(new File(nameFile)), nameCharset);
      Iterator<String> it = l.iterator();
      while (it.hasNext()) {
        String normalizedWord = it.next();
        normalizedWord = normalizedWord.replaceAll("\\^", "").replaceAll("@", "").replaceAll("×", "").replaceAll("~", "");
        out.write(normalizedWord + "\n");
      } 
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur ...." + e);
    } catch (IOException e) {
      System.out.println("Erreur ...." + e);
    } 
  }
  
  public static void writeResultToXMLFile(File nameFile, String nameCharset) {
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(nameFile), nameCharset);
      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
      out.write("<Results>\n");
      Iterator<String> it = Static.allResults.keySet().iterator();
      while (it.hasNext()) {
        String normalizedWord = it.next();
        List<Result> res = (List)Static.allResults.get(normalizedWord);
        out.write("\t<result word='" + normalizedWord + "' nbresult='" + res.size() + "' nbOccu='" + Tokenization.TokensRepeat.get(normalizedWord) + "'>\n");
        if (res.isEmpty()) {
          out.write("\t\tلا توجد نتائج\n");
        } else {
          Iterator<Result> itt = res.iterator();
          while (itt.hasNext()) {
            Result e_r = itt.next();
            out.write("\t\t<s_result>\n");
            if (Settings.vowchoice)
              out.write("\t\t\t<voweled>" + e_r.getVoweledWord() + "</voweled>\n"); 
            if (Settings.prefchoice)
              out.write("\t\t\t<prefixe>" + e_r.getPrefix() + "</prefixe>\n"); 
            if (Settings.stemchoice)
              out.write("\t\t\t<stem>" + e_r.getStem() + "</stem>\n"); 
            if (Settings.typechoice)
              out.write("\t\t\t<type>" + e_r.getType() + "</type>\n"); 
            if (Settings.diacpatternchoice)
              out.write("\t\t\t<diac>" + e_r.getDiacPatternStem() + "</diac>\n"); 
            if (Settings.patternchoice)
              out.write("\t\t\t<canonic>" + e_r.getPatternStem() + "</canonic>\n"); 
            if (Settings.lemmechoice)
              out.write("\t\t\t<lemme>" + e_r.getLemma() + "</lemme>\n"); 
            if (Settings.patternlemmechoice)
              out.write("\t\t\t<pat_lemme>" + e_r.getLemma() + "</pat_lemme>\n"); 
            if (Settings.rootchoice)
              out.write("\t\t\t<root>" + e_r.getRoot() + "</root>\n"); 
            if (Settings.poschoice)
              out.write("\t\t\t<pos>" + e_r.getPostag() + "</pos>\n"); 
            if (Settings.suffixchoice)
              out.write("\t\t\t<suffixe>" + e_r.getSuffix() + "</suffixe>\n"); 
            out.write("\t\t</s_result>\n");
          } 
        } 
        out.write("\t</result>\n");
      } 
      out.write("</Results>\n");
      out.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
  }
  
  public static void writeResultToHTMLFile(File nameFile, String nameCharset) {
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(nameFile), nameCharset);
      out.write("<table float=\"center\" WIDTH=90% BORDER=1 dir=\"rtl\">\n");
      int cmp = 0;
      out.write("<tr>\n");
      out.write("<th>الدخل</th>\n");
      cmp++;
      if (Settings.vowchoice) {
        out.write("<th>الكلمة المشكولة</th>\n");
        cmp++;
      } 
      if (Settings.prefchoice) {
        out.write("<th>السابق</th>\n");
        cmp++;
      } 
      if (Settings.stemchoice) {
        out.write("<th>الجذع</th>\n");
        cmp++;
      } 
      if (Settings.typechoice) {
        out.write("<th>نوع الكلمة</th>\n");
        cmp++;
      } 
      if (Settings.diacpatternchoice);
      if (Settings.patternchoice) {
        out.write("<th>وزن الكلمة</th>\n");
        cmp++;
      } 
      if (Settings.lemmechoice) {
        out.write("<th>الفرع</th>\n");
        cmp++;
      } 
      if (Settings.patternlemmechoice) {
        out.write("<th>وزن الفرع</th>\n");
        cmp++;
      } 
      if (Settings.rootchoice) {
        out.write("<th>الجذر</th>\n");
        cmp++;
      } 
      if (Settings.poschoice) {
        out.write("<th>الحالة الإعرابية</th>\n");
        cmp++;
      } 
      if (Settings.suffixchoice) {
        out.write("<th>اللاحق</th>\n");
        cmp++;
      } 
      out.write("</tr>\n");
      Iterator<String> it = Static.allResults.keySet().iterator();
      while (it.hasNext()) {
        String words = it.next();
        List<Result> result = (List)Static.allResults.get(words);
        if (result.isEmpty()) {
          out.write("<tr>\n");
          out.write("<td>" + words + "</td>\n");
          out.write("<td colspan=" + cmp + ">" + "لا توجد نتائج لتحليل هذه الكلمة" + "</td>\n");
          out.write("</tr>\n");
          continue;
        } 
        int size = result.size();
        out.write("<tr>\n");
        out.write("<td rowspan=" + size + " >" + words + "</td>\n");
        Iterator<Result> itRes = result.iterator();
        if (itRes.hasNext()) {
          Result r = itRes.next();
          if (Settings.vowchoice)
            out.write("<td>" + r.getVoweledWord() + "</td>\n"); 
          if (Settings.prefchoice)
            out.write("<td>" + r.getPrefix() + "</td>\n"); 
          if (Settings.stemchoice)
            out.write("<td>" + r.getStem() + "</td>\n"); 
          if (Settings.typechoice)
            out.write("<td>" + r.getType() + "</td>\n"); 
          if (Settings.diacpatternchoice);
          if (Settings.patternchoice)
            out.write("<td>" + r.getPatternStem() + "</td>\n"); 
          if (Settings.lemmechoice)
            out.write("<td>" + r.getLemma() + "</td>\n"); 
          if (Settings.patternlemmechoice)
            out.write("<td>" + r.getPatternLemma() + "</td>\n"); 
          if (Settings.rootchoice)
            out.write("<td>" + r.getRoot() + "</td>\n"); 
          if (Settings.poschoice)
            out.write("<td>" + r.getPostag() + "</td>\n"); 
          if (Settings.suffixchoice)
            out.write("<td>" + r.getSuffix() + "</td>\n"); 
        } 
        out.write("</tr>\n");
        while (itRes.hasNext()) {
          Result r = itRes.next();
          out.write("<tr>\n");
          if (Settings.vowchoice)
            out.write("<td>" + r.getVoweledWord() + "</td>\n"); 
          if (Settings.prefchoice)
            out.write("<td>" + r.getPrefix() + "</td>\n"); 
          if (Settings.stemchoice)
            out.write("<td>" + r.getStem() + "</td>\n"); 
          if (Settings.typechoice)
            out.write("<td>" + r.getType() + "</td>\n"); 
          if (Settings.diacpatternchoice);
          if (Settings.patternchoice)
            out.write("<td>" + r.getPatternStem() + "</td>\n"); 
          if (Settings.lemmechoice)
            out.write("<td>" + r.getLemma() + "</td>\n"); 
          if (Settings.patternlemmechoice)
            out.write("<td>" + r.getPatternLemma() + "</td>\n"); 
          if (Settings.rootchoice)
            out.write("<td>" + r.getRoot() + "</td>\n"); 
          if (Settings.poschoice)
            out.write("<td>" + r.getPostag() + "</td>\n"); 
          if (Settings.suffixchoice)
            out.write("<td>" + r.getSuffix() + "</td>\n"); 
          out.write("</tr>\n");
        } 
      } 
      out.write("</table>");
      out.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
  }
  
  public static void writeResultToCSVFile(File nameFile, String nameCharset) {
    StringBuilder str = new StringBuilder();
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(nameFile), nameCharset);
      Iterator<String> it = Static.allResults.keySet().iterator();
      while (it.hasNext()) {
        String normalizedWord = it.next();
        List<Result> res = (List)Static.allResults.get(normalizedWord);
        if (res.isEmpty()) {
          out.write(normalizedWord + ";");
          out.write("لا توجد نتائج لتحليل هذه الكلمة;;;;;;;;#;#\n");
          continue;
        } 
        Iterator<Result> itt = res.iterator();
        while (itt.hasNext()) {
          Result result = itt.next();
          if (Settings.vowchoice)
            out.write(result.getVoweledWord() + ";"); 
          if (Settings.prefchoice)
            out.write(result.getPrefix() + ";"); 
          if (Settings.stemchoice)
            out.write(result.getStem() + ";"); 
          if (Settings.typechoice)
            out.write(result.getType() + ";"); 
          if (Settings.diacpatternchoice)
            out.write(result.getDiacPatternStem() + ";"); 
          if (Settings.patternchoice)
            out.write(result.getPatternStem() + ";"); 
          if (Settings.lemmechoice)
            out.write(result.getLemma() + ";"); 
          if (Settings.patternlemmechoice)
            out.write(result.getPatternLemma() + ";"); 
          if (Settings.rootchoice)
            out.write(result.getRoot() + ";"); 
          if (Settings.poschoice)
            out.write(result.getPostag() + ";"); 
          if (Settings.suffixchoice)
            out.write(result.getSuffix() + ";"); 
          out.write("\n");
        } 
      } 
      out.close();
    } catch (FileNotFoundException e) {
    
    } catch (IOException e) {}
  }
}
