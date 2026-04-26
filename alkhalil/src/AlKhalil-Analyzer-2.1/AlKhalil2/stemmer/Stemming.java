package AlKhalil2.stemmer;

import AlKhalil2.stemmer.clitic.impl.Enclitic;
import AlKhalil2.stemmer.clitic.impl.Proclitic;
import AlKhalil2.stemmer.clitic.interfaces.IClitic;
import AlKhalil2.stemmer.clitic.model.Clitic;
import AlKhalil2.stemmer.model.Segment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Stemming {
  private IClitic suffix;
  
  private IClitic prefix;
  
  public Stemming() {
    this.suffix = new Enclitic();
    this.suffix.addClitics();
    this.prefix = new Proclitic();
    this.prefix.addClitics();
  }
  
  public List getListsSegment(String unvoweledWord) {
    List<Segment> result = new ArrayList();
    Set<Segment> setResult = new HashSet();
    List<Clitic> listsPrefixe = this.prefix.getListsClitics(unvoweledWord);
    List<Clitic> listsSuffixe = this.suffix.getListsClitics(unvoweledWord);
    boolean valid = true;
    Iterator<Clitic> it_p = listsPrefixe.iterator();
    while (it_p.hasNext()) {
      Clitic p = it_p.next();
      Iterator<Clitic> it_s = listsSuffixe.iterator();
      while (valid && it_s.hasNext()) {
        Clitic s = it_s.next();
        if (unvoweledWord.length() - s.getUnvoweledform().length() - p.getUnvoweledform().length() >= 0) {
          Set<String> Alternatives = new HashSet();
          String stem = unvoweledWord.substring(p.getUnvoweledform().length(), unvoweledWord.length() - s.getUnvoweledform().length());
          if (p.getClasse().equals("N4") && p.getUnvoweledform().endsWith("ل") && stem.startsWith("ل"))
            stem = 'ا' + stem; 
          Alternatives.add(stem);
          if (p.getUnvoweledform().equals("أ") || p.getUnvoweledform().equals("ب"))
            Alternatives.add('ا' + stem); 
          String stem1 = "";
          if (stem.length() > 1)
            stem1 = getAlternatives(stem, stem.charAt(stem.length() - 1), (s.getUnvoweledform().length() != 0), (s.getUnvoweledform().length() > 0)); 
          if (!"".equals(stem1))
            Alternatives.add(stem1); 
          String stem3 = stem1.replaceAll("آ", "ءا");
          if (!stem3.equals(stem1))
            Alternatives.add(stem3); 
          String stem2 = stem.replaceAll("آ", "ءا");
          if (!stem2.equals(stem))
            Alternatives.add(stem2); 
          Iterator<String> it = Alternatives.iterator();
          while (it.hasNext()) {
            String sch = it.next();
            Segment segment = new Segment(p, sch, s);
            if (isValidSegment(segment))
              setResult.add(segment); 
          } 
          continue;
        } 
        valid = false;
      } 
    } 
    result.addAll(setResult);
    return result;
  }
  
  private boolean isValidSegment(Segment segment) {
    Clitic p = segment.getProclitic();
    Clitic s = segment.getEnclitic();
    String prefix_classe = p.getClasse();
    String suffixe_classe = s.getClasse();
    return ((prefix_classe.indexOf("N") == -1 || suffixe_classe.indexOf("V") == -1) && (prefix_classe.indexOf("V") == -1 || suffixe_classe.indexOf("N") == -1) && ((!prefix_classe.equals("N1") && !prefix_classe.equals("N2") && !prefix_classe.equals("N3") && !prefix_classe.equals("N5")) || segment.getEnclitic().getUnvoweledform().equals("")));
  }
  
  public String getAlternatives(String stem, char st, boolean valid, boolean validSuf) {
    switch (st) {
      case 'ت':
        if (valid)
          return stem.substring(0, stem.length() - 1) + 'ة'; 
        return "";
      case 'آ':
        if (valid)
          return stem.substring(0, stem.length() - 1) + "أى"; 
        return "";
      case 'ا':
        if (valid)
          return stem.substring(0, stem.length() - 1) + 'ى'; 
        return "";
      case 'و':
        if (valid)
          return stem + 'ا'; 
        return "";
      case 'ئ':
        if (validSuf)
          return stem.substring(0, stem.length() - 1) + 'ء'; 
        return "";
      case 'ؤ':
        if (validSuf)
          return stem.substring(0, stem.length() - 1) + 'ء'; 
        return "";
    } 
    return "";
  }
  
  public static void print(Segment segment) {
    System.out.println(segment.getProclitic().getUnvoweledform() + "|" + segment.getProclitic().getVoweledform() + "|" + segment.getStem() + "|" + segment.getEnclitic().getVoweledform() + "|" + segment.getEnclitic().getUnvoweledform());
  }
  
  public void clear() {
    this.suffix.clear();
    this.prefix.clear();
  }
}
