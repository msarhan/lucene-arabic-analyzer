package AlKhalil2.text.tokenization;

import AlKhalil2.util.ArabCharUtil;
import AlKhalil2.util.ArabMethod;
import AlKhalil2.util.Collections;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenization {
  private List<String> AllTokens = new LinkedList<String>();
  
  private List<String> AllText = new LinkedList<String>();
  
  private List<String> AllTokensUnvoweled = new LinkedList<String>();
  
  private Set<String> Tokens = new HashSet<String>();
  
  public static Map<String, Integer> TokensRepeat = new HashMap<String, Integer>();
  
  private Map<String, Integer> TokensUnvoweledRepeat = new HashMap<String, Integer>();
  
  public List<String> getAllTokens() {
    return this.AllTokens;
  }
  
  public Set<String> getTokens() {
    this.Tokens = new HashSet<String>();
    this.Tokens.addAll(this.AllTokens);
    return this.Tokens;
  }
  
  public Map<String, Integer> getTokensRepeat() {
    return TokensRepeat;
  }
  
  public Map<String, Integer> getTokensUnvoweledRepeat() {
    return this.TokensUnvoweledRepeat;
  }
  
  public List<String> getAllTokensUnvoweled() {
    return this.AllTokensUnvoweled;
  }
  
  public List<String> getAllText() {
    return this.AllText;
  }
  
  public int getNbAllTokens() {
    return getAllTokens().size();
  }
  
  public int getNbAllTokensUnvoweled() {
    return getAllTokensUnvoweled().size();
  }
  
  public int getNbTokens() {
    return getTokens().size();
  }
  
  public int getNbAllText() {
    return getAllText().size();
  }
  
  public void setTokenizationFile(String nameFile, String nameCharset) {
    this.AllTokens = new LinkedList<String>();
    this.AllTokensUnvoweled = new LinkedList<String>();
    this.AllText = new LinkedList<String>();
    TokensRepeat = new HashMap<String, Integer>();
    this.TokensUnvoweledRepeat = new HashMap<String, Integer>();
    String ptr = ArabMethod.getPatternCompile();
    Pattern pattern = Pattern.compile(ptr);
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nameFile)), nameCharset));
      String line;
      while ((line = in.readLine()) != null) {
        int start = 0;
        int end = 0;
        line = line.replaceAll("ـ", "");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          String word = matcher.group();
          char c1 = word.charAt(0);
          if (!ArabCharUtil.isSeparator(c1)) {
            word = ArabCharUtil.correctErreur(word);
            this.AllTokens.add(word);
            Collections.addStringToMap(TokensRepeat, word);
            Collections.addStringToMap(this.TokensUnvoweledRepeat, ArabMethod.getUnvoweledWord(word));
            this.AllTokensUnvoweled.add(ArabMethod.getUnvoweledWord(word));
          } 
          this.AllText.add(word);
          if (matcher.start() > 0) {
            String wrd = line.substring(start, matcher.start());
            this.AllText.add(wrd);
          } 
          start = matcher.end();
          end = matcher.end();
        } 
        if (line.length() > end) {
          String wrd = line.substring(start, line.length());
          this.AllText.add(wrd);
        } 
      } 
      in.close();
    } catch (IOException e) {
      System.out.println("Erreur : " + e);
    } 
  }
  
  public void setTokenizationFileLine(String nameFile, String nameCharset, int startline, int nbline) {
    this.AllTokens = new LinkedList<String>();
    this.AllTokensUnvoweled = new LinkedList<String>();
    this.AllText = new LinkedList<String>();
    this.TokensUnvoweledRepeat = new HashMap<String, Integer>();
    TokensRepeat = new HashMap<String, Integer>();
    String ptr = ArabMethod.getPatternCompile();
    Pattern pattern = Pattern.compile(ptr);
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nameFile)), nameCharset));
      int i = 0;
      String line;
      while ((line = in.readLine()) != null && i < startline)
        i++; 
      i = 0;
      while ((line = in.readLine()) != null && i < nbline) {
        int start = 0;
        int end = 0;
        line = line.replaceAll("ـ", "");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          String word = matcher.group();
          char c1 = word.charAt(0);
          if (!ArabCharUtil.isSeparator(c1)) {
            word = ArabCharUtil.correctErreur(word);
            this.AllTokens.add(word);
            Collections.addStringToMap(TokensRepeat, word);
            Collections.addStringToMap(this.TokensUnvoweledRepeat, ArabMethod.getUnvoweledWord(word));
            this.AllTokensUnvoweled.add(ArabMethod.getUnvoweledWord(word));
          } 
          this.AllText.add(word);
          if (matcher.start() > 0) {
            String wrd = line.substring(start, matcher.start());
            this.AllText.add(wrd);
          } 
          start = matcher.end();
          end = matcher.end();
        } 
        if (line.length() > end) {
          String wrd = line.substring(start, line.length());
          this.AllText.add(wrd);
        } 
        i++;
      } 
      in.close();
    } catch (IOException e) {
      System.out.println("Erreur : " + e);
    } 
  }
  
  public void setTokenizationString(String Text) {
    this.AllTokens = new LinkedList<String>();
    this.AllTokensUnvoweled = new LinkedList<String>();
    this.AllText = new LinkedList<String>();
    this.TokensUnvoweledRepeat = new HashMap<String, Integer>();
    TokensRepeat = new HashMap<String, Integer>();
    String ptr = ArabMethod.getPatternCompile();
    Pattern pattern = Pattern.compile(ptr);
    List<String> Lines = new LinkedList<String>();
    Lines.addAll(Arrays.asList(Text.split("\n")));
    Iterator<String> it_line = Lines.iterator();
    while (it_line.hasNext()) {
      String line = it_line.next();
      int start = 0;
      int end = 0;
      line = line.replaceAll("ـ", "");
      Matcher matcher = pattern.matcher(line);
      while (matcher.find()) {
        String word = matcher.group();
        char c1 = word.charAt(0);
        if (!ArabCharUtil.isSeparator(c1)) {
          word = ArabCharUtil.correctErreur(word);
          this.AllTokens.add(word);
          Collections.addStringToMap(TokensRepeat, word);
          Collections.addStringToMap(this.TokensUnvoweledRepeat, ArabMethod.getUnvoweledWord(word));
          this.AllTokensUnvoweled.add(ArabMethod.getUnvoweledWord(word));
        } 
        if (matcher.start() > 0) {
          String wrd = line.substring(start, matcher.start());
          this.AllText.add(wrd);
        } 
        this.AllText.add(word);
        start = matcher.end();
        end = matcher.end();
      } 
      if (line.length() > end) {
        String wrd = line.substring(start, line.length());
        this.AllText.add(wrd);
      } 
      this.AllText.add("<br>");
    } 
  }
}
