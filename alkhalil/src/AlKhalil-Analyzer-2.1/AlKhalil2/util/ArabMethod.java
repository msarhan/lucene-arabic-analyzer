package AlKhalil2.util;

import AlKhalil2.stemmer.model.Segment;
import java.util.HashSet;
import java.util.Set;

public class ArabMethod {
  public static boolean notCompatible(String normalizedWord, String voweledWord, Segment segment, boolean isTool) {
    String norWord = normalizedWord;
    String vowWord = voweledWord;
    String unorWord = getUnvoweledWord(norWord);
    String uvowWord = getUnvoweledWord(vowWord);
    if ((!segment.getEnclitic().getUnvoweledform().equals("") || isTool || norWord.charAt(norWord.length() - 1) == 'ِ') && vowWord.charAt(vowWord.length() - 1) == 'ْ' && ArabCharUtil.isDiacritic(norWord.charAt(norWord.length() - 1))) {
      norWord = norWord.substring(0, norWord.length() - 1);
      vowWord = vowWord.substring(0, vowWord.length() - 1);
    } 
    if (vowWord.endsWith("َا") && norWord.endsWith("اً"))
      return true; 
    if (unorWord.length() != uvowWord.length())
      return true; 
    int in = 0;
    int iv = 0;
    while (in < norWord.length() && iv < vowWord.length()) {
      if (norWord.charAt(in) == vowWord.charAt(iv) || (norWord.charAt(in) == 'ا' && vowWord.charAt(iv) == 'ى')) {
        if (ArabCharUtil.isDiacritic(norWord.charAt(in))) {
          in++;
          iv++;
          continue;
        } 
        in++;
        iv++;
        continue;
      } 
      if (!ArabCharUtil.isDiacritic(norWord.charAt(in)) && !ArabCharUtil.isDiacritic(vowWord.charAt(iv)))
        return true; 
      if (!ArabCharUtil.isDiacritic(norWord.charAt(in)) && ArabCharUtil.isDiacritic(vowWord.charAt(iv))) {
        iv++;
        continue;
      } 
      if (ArabCharUtil.isDiacritic(norWord.charAt(in)))
        return true; 
    } 
    return false;
  }
  
  public static String getUnvoweledWord(String voweledWord) {
    String unvoweledWord = "";
    for (int i = 0; i < voweledWord.length(); i++) {
      if (!ArabCharUtil.isDiacritic(voweledWord.charAt(i)))
        unvoweledWord = unvoweledWord + voweledWord.charAt(i); 
    } 
    return unvoweledWord;
  }
  
  public static boolean notCompatible(String normalizedWord, String voweledWord) {
    String norWord = normalizedWord;
    String vowWord = voweledWord;
    String unorWord = getUnvoweledWord(norWord);
    String uvowWord = getUnvoweledWord(vowWord);
    if (vowWord.endsWith("َا") && norWord.endsWith("اً"))
      return true; 
    int in = 0;
    int iv = 0;
    if (unorWord.length() != uvowWord.length())
      return true; 
    while (in < norWord.length() && iv < vowWord.length()) {
      if (norWord.charAt(in) == vowWord.charAt(iv) || (norWord.charAt(in) == 'ا' && vowWord.charAt(iv) == 'ى')) {
        if (ArabCharUtil.isDiacritic(norWord.charAt(in))) {
          in++;
          iv++;
          continue;
        } 
        in++;
        iv++;
        continue;
      } 
      if (!ArabCharUtil.isDiacritic(norWord.charAt(in)) && !ArabCharUtil.isDiacritic(vowWord.charAt(iv)))
        return true; 
      if (!ArabCharUtil.isDiacritic(norWord.charAt(in)) && ArabCharUtil.isDiacritic(vowWord.charAt(iv))) {
        iv++;
        continue;
      } 
      if (ArabCharUtil.isDiacritic(norWord.charAt(in)))
        return true; 
    } 
    return false;
  }
  
  public String[] Vowelize(Segment segment, String voweledpattern, String ncg) {
    String[] resultat = { "", "" };
    String stem = segment.getStem();
    String result = "";
    String soukoun = "";
    int j = 0;
    if (segment.getEnclitic().getClasse().equals("V4"))
      voweledpattern = voweledpattern.replaceAll("ًٌٍَُِْ$", "ُ"); 
    if (segment.getProclitic().getClasse().equals("N1") || segment.getProclitic().getClasse().equals("N2") || segment.getProclitic().getClasse().equals("N3") || segment.getProclitic().getClasse().equals("N5")) {
      if (ArabCharUtil.isSolar(stem.charAt(0))) {
        for (int i = 0; i < voweledpattern.length(); i++) {
          if (ArabCharUtil.isDiacritic(voweledpattern.charAt(i))) {
            result = result + voweledpattern.charAt(i);
          } else {
            result = result + stem.charAt(j);
            j++;
          } 
        } 
        result = result.substring(0, 1) + "ّ" + result.substring(1);
      } else {
        for (int i = 0; i < voweledpattern.length(); i++) {
          if (ArabCharUtil.isDiacritic(voweledpattern.charAt(i))) {
            result = result + voweledpattern.charAt(i);
          } else {
            result = result + stem.charAt(j);
            j++;
          } 
        } 
        soukoun = "ْ";
      } 
    } else {
      for (int i = 0; i < voweledpattern.length(); i++) {
        if (ArabCharUtil.isDiacritic(voweledpattern.charAt(i))) {
          result = result + voweledpattern.charAt(i);
        } else {
          result = result + stem.charAt(j);
          j++;
        } 
      } 
    } 
    if (result.indexOf("ة") != -1 && !segment.getEnclitic().getVoweledform().equals(""))
      result = result.replace('ة', 'ت'); 
    if ((result.endsWith("ُوا") || result.endsWith("وْا")) && !segment.getEnclitic().getUnvoweledform().equals(""))
      result = result.substring(0, result.length() - 1); 
    if (segment.getEnclitic().getUnvoweledform().equals("ه"))
      if (result.endsWith("ي") || result.endsWith("يْ") || result.endsWith("ِ")) {
        segment.getEnclitic().setVoweledform("هِ");
      } else {
        segment.getEnclitic().setVoweledform("هُ");
      }  
    if (segment.getEnclitic().getUnvoweledform().equals("هما"))
      if (result.endsWith("ي") || result.endsWith("يْ") || result.endsWith("ِ")) {
        segment.getEnclitic().setVoweledform("هِمَا");
      } else {
        segment.getEnclitic().setVoweledform("هُمَا");
      }  
    if (segment.getEnclitic().getUnvoweledform().equals("هم"))
      if (result.endsWith("ي") || result.endsWith("يْ") || result.endsWith("ِ")) {
        segment.getEnclitic().setVoweledform("هِمْ");
      } else {
        segment.getEnclitic().setVoweledform("هُمْ");
      }  
    if (segment.getEnclitic().getUnvoweledform().equals("هن"))
      if (result.endsWith("ي") || result.endsWith("يْ") || result.endsWith("ِ")) {
        segment.getEnclitic().setVoweledform("هِنَّ");
      } else {
        segment.getEnclitic().setVoweledform("هُنَّ");
      }  
    if ((result.endsWith("ُ") || result.endsWith("َ")) && segment.getEnclitic().getUnvoweledform().equals("ي"))
      result = result.substring(0, result.length() - 1) + "ِ"; 
    String res = "";
    String sf = segment.getEnclitic().getVoweledform();
    if (sf.length() > 0) {
      result = result.replaceAll("ى", "ا");
      if (result.endsWith("ْ")) {
        if (sf.length() == 1) {
          if (sf.equals("و")) {
            res = result.substring(0, result.length() - 1) + 'ُ' + sf;
          } else if (sf.equals("ي")) {
            res = result.substring(0, result.length() - 1) + "ِ" + sf;
          } else if (sf.equals("ا")) {
            res = result.substring(0, result.length() - 1) + "َ" + sf;
          } 
        } else if (!ArabCharUtil.isDiacritic(sf.charAt(1))) {
          if (sf.charAt(0) == 'و') {
            res = result.substring(0, result.length() - 1) + 'ُ' + sf;
          } else if (sf.charAt(0) == 'ي') {
            res = result.substring(0, result.length() - 1) + "ِ" + sf;
          } else if (sf.charAt(0) == 'ا') {
            res = result.substring(0, result.length() - 1) + "َ" + sf;
          } else {
            res = result + sf;
          } 
        } else {
          res = result + sf;
        } 
      } else {
        res = result + sf;
      } 
    } else {
      res = result;
    } 
    if (!res.equals("")) {
      res = CreatHamza.correctHamza(res);
      if (!ncg.equals("3") && !ncg.equals("4") && res.endsWith("ءَانِ"))
        res = res.replaceAll("ءَانِ$", "آنِ"); 
    } 
    if (segment.getProclitic().getUnvoweledform().equals("أ") && res.length() > 3 && res.charAt(0) == 'ا' && res.charAt(2) == 'ْ') {
      resultat[0] = segment.getProclitic().getVoweledform() + soukoun + res.substring(1);
    } else {
      resultat[0] = segment.getProclitic().getVoweledform() + soukoun + res;
    } 
    resultat[1] = result;
    return resultat;
  }
  
  public static String diaclemme(String root, String dLemme) {
    String str = "";
    boolean passe = false;
    boolean passe2 = false;
    for (int i = 0; i < dLemme.length(); i++) {
      if (dLemme.charAt(i) == 'ف') {
        str = str + root.charAt(0);
      } else if (dLemme.charAt(i) == 'ع') {
        str = str + root.charAt(1);
      } else if (dLemme.charAt(i) == 'ل') {
        if (!passe) {
          str = str + root.charAt(2);
        } else if (!passe2) {
          str = str + root.charAt(3);
          if (root.length() > 4)
            passe2 = true; 
        } else {
          str = str + root.charAt(4);
        } 
        if (root.length() != 3)
          passe = true; 
      } else {
        str = str + dLemme.charAt(i);
      } 
    } 
    return CreatHamza.correctHamza(str);
  }
  
  public static boolean isDiacPattern(String stem, String diac) {
    int i = 0, j = 0;
    while (i < diac.length() && j < stem.length()) {
      if (diac.charAt(i) != 'ف' && diac.charAt(i) != 'ع' && diac.charAt(i) != 'ل' && 
        diac.charAt(i) != stem.charAt(j))
        return false; 
      i++;
      j++;
    } 
    return true;
  }
  
  public static int Max(int a, int b) {
    return (a > b) ? a : b;
  }
  
  public static int Min(int a, int b) {
    return (a < b) ? a : b;
  }
  
  public static String getPatternCompile() {
    String str = "";
    str = str + "[ابتثجحخدذرزسشصضطظعغفقكلمنهويءآأإةؤئى][ابتثجحخدذرزسشصضطظعغفقكلمنهويءآأإةؤئىًٌٍَُِّْ]*";
    String chainePattern = "((" + str + ")";
    chainePattern = chainePattern + "|(\\.)";
    chainePattern = chainePattern + "|(:)";
    chainePattern = chainePattern + "|(؟)";
    chainePattern = chainePattern + "|(؛)";
    chainePattern = chainePattern + "|(!)";
    chainePattern = chainePattern + ")";
    return chainePattern;
  }
  
  public static boolean isSeparator(String pat) {
    Set<String> sep = new HashSet<String>();
    sep.add(".");
    sep.add(":");
    sep.add("،");
    sep.add("؟");
    sep.add("؛");
    sep.add("!");
    return sep.contains(pat);
  }
}
