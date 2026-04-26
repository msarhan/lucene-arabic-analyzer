package AlKhalil2.morphology.result.model;

import AlKhalil2.util.ArabMethod;
import AlKhalil2.util.Settings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Result implements Comparable<Result> {
  private String voweledWord;
  
  private String prefix;
  
  private String prefNoDec;
  
  private String stem;
  
  private String type;
  
  private String diacPatternStem;
  
  private String patternStem;
  
  private String lemma;
  
  private String patternLemma;
  
  private String root;
  
  private String pos;
  
  private String suffix;
  
  private String sufNoDec;
  
  private String priority;
  
  public Result() {}
  
  public Result(List<String> result, String priority) {
    Iterator<String> ires = result.iterator();
    if (Settings.vowchoice)
      this.voweledWord = ires.next(); 
    if (Settings.prefchoice)
      this.prefix = ires.next(); 
    if (Settings.stemchoice)
      this.stem = ires.next(); 
    if (Settings.typechoice)
      this.type = ires.next(); 
    if (Settings.diacpatternchoice)
      this.diacPatternStem = ires.next(); 
    if (Settings.patternchoice)
      this.patternStem = ires.next(); 
    if (Settings.lemmechoice)
      this.lemma = ires.next(); 
    if (Settings.patternlemmechoice)
      this.patternLemma = ires.next(); 
    if (Settings.rootchoice)
      this.root = ires.next(); 
    if (Settings.poschoice)
      this.pos = ires.next(); 
    if (Settings.suffixchoice)
      this.suffix = ires.next(); 
    this.priority = priority;
  }
  
  public Result(String voweledWord, String prefix, String stem, String wordType, String wordDiacPattern, String wordCanPattern, String lemme, String lemmePattern, String wordRoot, String pos, String suffix) {
    this.voweledWord = voweledWord;
    this.prefix = prefix;
    this.stem = stem;
    this.type = wordType;
    this.diacPatternStem = wordDiacPattern;
    this.patternStem = wordCanPattern;
    this.lemma = lemme;
    this.patternLemma = lemmePattern;
    this.root = wordRoot;
    this.pos = pos;
    this.suffix = suffix;
  }
  
  public Result(String voweledWord, String prefix, String stem, String wordType, String wordDiacPattern, String wordCanPattern, String lemme, String lemmePattern, String wordRoot, String pos, String suffix, String priority) {
    if (Settings.vowchoice)
      this.voweledWord = voweledWord; 
    if (Settings.prefchoice)
      this.prefix = prefix; 
    if (Settings.stemchoice)
      this.stem = stem; 
    if (Settings.typechoice)
      this.type = wordType; 
    if (Settings.diacpatternchoice)
      this.diacPatternStem = wordDiacPattern; 
    if (Settings.patternchoice)
      this.patternStem = wordCanPattern; 
    if (Settings.lemmechoice)
      this.lemma = lemme; 
    if (Settings.patternlemmechoice)
      this.patternLemma = lemmePattern; 
    if (Settings.rootchoice)
      this.root = wordRoot; 
    if (Settings.poschoice)
      this.pos = pos; 
    if (Settings.suffixchoice)
      this.suffix = suffix; 
    this.priority = priority;
  }
  
  public Result(String _voweledWord, String _prefix, String _prefNoDec, String _stem, String _wordType, String _wordDiacPattern, String _wordCanPattern, String _lemme, String _lemmePattern, String _wordRoot, String _pos, String _suffix, String _sufNoDec, String _priority) {
    if (Settings.vowchoice)
      this.voweledWord = _voweledWord; 
    if (Settings.prefchoice) {
      this.prefix = _prefix;
      this.prefNoDec = _prefNoDec;
    } 
    if (Settings.stemchoice) {
      String pattern = _wordDiacPattern;
      pattern = pattern.replaceAll("[ًٌٍَُِْ]$", "");
      if (!_wordRoot.equals("#")) {
        String Stem = ArabMethod.diaclemme(_wordRoot, pattern);
        this.stem = Stem;
      } else {
        this.stem = _stem;
      } 
    } 
    if (Settings.typechoice)
      this.type = _wordType; 
    if (Settings.diacpatternchoice)
      this.diacPatternStem = _wordDiacPattern; 
    if (Settings.patternchoice)
      this.patternStem = _wordCanPattern; 
    if (Settings.lemmechoice)
      this.lemma = _lemme; 
    if (Settings.patternlemmechoice)
      this.patternLemma = _lemmePattern; 
    if (Settings.rootchoice)
      this.root = _wordRoot; 
    if (Settings.poschoice)
      this.pos = _pos; 
    if (Settings.suffixchoice) {
      this.suffix = _suffix;
      this.sufNoDec = _sufNoDec;
    } 
    this.priority = _priority;
  }
  
  public String getVoweledWord() {
    return this.voweledWord;
  }
  
  public String getPrefix() {
    return this.prefix;
  }
  
  public String getStem() {
    return this.stem;
  }
  
  public String getType() {
    return this.type;
  }
  
  public String getDiacPatternStem() {
    return this.diacPatternStem;
  }
  
  public String getPatternStem() {
    return this.patternStem;
  }
  
  public String getLemma() {
    return this.lemma;
  }
  
  public String getPatternLemma() {
    return this.patternLemma;
  }
  
  public String getRoot() {
    return this.root;
  }
  
  public String getPostag() {
    return this.pos;
  }
  
  public String getSuffix() {
    return this.suffix;
  }
  
  public String getPriority() {
    return this.priority;
  }
  
  public String getPrefNoDec() {
    return this.prefNoDec;
  }
  
  public String getSufNoDec() {
    return this.sufNoDec;
  }
  
  public void setVoweledWord(String voweledWord) {
    this.voweledWord = voweledWord;
  }
  
  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }
  
  public void setStem(String stem) {
    this.stem = stem;
  }
  
  public void setType(String wordType) {
    this.type = wordType;
  }
  
  public void setDiacPatternStem(String wordDiacPattern) {
    this.diacPatternStem = wordDiacPattern;
  }
  
  public void setPatternStem(String patternStem) {
    this.patternStem = patternStem;
  }
  
  public void setLemma(String lemme) {
    this.lemma = lemme;
  }
  
  public void setPatternLemma(String lemmePattern) {
    this.patternLemma = lemmePattern;
  }
  
  public void setRoot(String root) {
    this.root = root;
  }
  
  public void setPostag(String pos) {
    this.pos = pos;
  }
  
  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }
  
  public void setPriority(String priority) {
    this.priority = priority;
  }
  
  public void setPrefNoDec(String prefNoDec) {
    this.prefNoDec = prefNoDec;
  }
  
  public void setSufNoDec(String sufNoDec) {
    this.sufNoDec = sufNoDec;
  }
  
  public String toString() {
    return "Result{" + (Settings.vowchoice ? ("voweledWord=" + this.voweledWord + ",") : "") + (Settings.prefchoice ? (" prefix=" + this.prefix + ",") : "") + (Settings.prefchoice ? (" prefNo=" + this.prefNoDec + ",") : "") + (Settings.stemchoice ? (" stem=" + this.stem + ",") : "") + (Settings.typechoice ? (" wordType=" + this.type + ",") : "") + (Settings.patternchoice ? (" wordCanPattern=" + this.patternStem + ",") : "") + (Settings.lemmechoice ? (" lemme=" + this.lemma + ",") : "") + (Settings.patternlemmechoice ? (" lemmePattern=" + this.patternLemma + ",") : "") + (Settings.rootchoice ? (" wordRoot=" + this.root + ",") : "") + (Settings.poschoice ? (" pos=" + this.pos + ",") : "") + (Settings.suffixchoice ? (" suffix=" + this.suffix + ",") : "") + "}";
  }
  
  public boolean equals(Object obj) {
    Result other = (Result)obj;
    if (Settings.vowchoice && 
      !this.voweledWord.equals(other.voweledWord))
      return false; 
    if (Settings.prefchoice && 
      !this.prefix.equals(other.prefix))
      return false; 
    if (Settings.stemchoice && 
      !this.stem.equals(other.stem))
      return false; 
    if (Settings.typechoice && 
      !this.type.equals(other.type))
      return false; 
    if (Settings.diacpatternchoice && 
      !this.diacPatternStem.equals(other.diacPatternStem))
      return false; 
    if (Settings.patternchoice && 
      !this.patternStem.equals(other.patternStem))
      return false; 
    if (Settings.lemmechoice && 
      !this.lemma.equals(other.lemma))
      return false; 
    if (Settings.patternlemmechoice && 
      !this.patternLemma.equals(other.patternLemma))
      return false; 
    if (Settings.rootchoice && 
      !this.root.equals(other.root))
      return false; 
    if (Settings.poschoice && 
      !this.pos.equals(other.pos))
      return false; 
    if (Settings.suffixchoice && 
      !this.suffix.equals(other.suffix))
      return false; 
    return true;
  }
  
  public int hashCode() {
    int hash = 5;
    hash = 3 * hash + ((this.voweledWord != null) ? this.voweledWord.hashCode() : 0);
    hash = 3 * hash + ((this.prefix != null) ? this.prefix.hashCode() : 0);
    hash = 3 * hash + ((this.stem != null) ? this.stem.hashCode() : 0);
    hash = 3 * hash + ((this.type != null) ? this.type.hashCode() : 0);
    hash = 3 * hash + ((this.diacPatternStem != null) ? this.diacPatternStem.hashCode() : 0);
    hash = 3 * hash + ((this.patternStem != null) ? this.patternStem.hashCode() : 0);
    hash = 3 * hash + ((this.lemma != null) ? this.lemma.hashCode() : 0);
    hash = 3 * hash + ((this.patternLemma != null) ? this.patternLemma.hashCode() : 0);
    hash = 3 * hash + ((this.root != null) ? this.root.hashCode() : 0);
    hash = 3 * hash + ((this.pos != null) ? this.pos.hashCode() : 0);
    hash = 3 * hash + ((this.suffix != null) ? this.suffix.hashCode() : 0);
    return hash;
  }
  
  public int compareTo(Result o) {
    return getPriority().compareTo(o.getPriority());
  }
  
  public static List<Result> sort(List<Result> result) {
    Map<String, Result> iMap = new HashMap<String, Result>();
    Iterator<Result> it = result.iterator();
    while (it.hasNext()) {
      Result rs = it.next();
      if (!iMap.containsKey(rs.toString()))
        iMap.put(rs.toString(), rs); 
    } 
    result = new LinkedList<Result>();
    Iterator<String> itt = iMap.keySet().iterator();
    while (itt.hasNext()) {
      String Key = itt.next();
      result.add(iMap.get(Key));
    } 
    Collections.sort(result);
    return result;
  }
}
