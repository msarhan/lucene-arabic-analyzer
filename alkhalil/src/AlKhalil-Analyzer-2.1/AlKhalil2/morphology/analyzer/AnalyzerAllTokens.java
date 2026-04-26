package AlKhalil2.morphology.analyzer;

import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.text.tokenization.Tokenization;
import AlKhalil2.util.Settings;
import AlKhalil2.util.Statistic;
import AlKhalil2.util.Times;
import AlKhalil2.util.constants.Static;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalyzerAllTokens {
  public static Map<String, Integer> iMapRepeat = new HashMap<String, Integer>();
  
  public static List<String> getAnalyzedText(String textToAnalyzed) {
    Tokenization tokens = new Tokenization();
    tokens.setTokenizationString(textToAnalyzed);
    iMapRepeat = tokens.getTokensRepeat();
    Static.allResults = new HashMap<Object, Object>();
    Static.analyzer = new AnalyzerTokens();
    int nbNoAnalyzedWord = 0;
    int nbAnalyzedWord = 0;
    int i = 0;
    List<String> l = new ArrayList();
    l.addAll(tokens.getTokens());
    Collections.sort(l);
    Iterator<String> it_normalized = l.iterator();
    Times.start();
    while (it_normalized.hasNext()) {
      String normalizedWord = it_normalized.next();
      List result = Static.analyzer.analyzerToken(normalizedWord);
      if (result.isEmpty()) {
        nbNoAnalyzedWord++;
      } else {
        nbAnalyzedWord++;
      } 
      Static.allResults.put(normalizedWord, result);
      i++;
    } 
    Times.end();
    Statistic.setNbTotalWords(tokens.getNbAllTokens());
    Statistic.setNbWord(tokens.getNbTokens());
    Statistic.setNbAnalyzedWord(nbAnalyzedWord);
    Statistic.setNbNoAnalyzedWord(nbNoAnalyzedWord);
    Statistic.setTimes(Times.getTimes());
    return tokens.getAllText();
  }
  
  public static List<String> getAnalyzedFile(String nameFile, String nameCharset) {
    Tokenization tokens = new Tokenization();
    tokens.setTokenizationFile(nameFile, nameCharset);
    iMapRepeat = tokens.getTokensRepeat();
    Static.allResults = new HashMap<Object, Object>();
    Static.analyzer = new AnalyzerTokens();
    int nbNoAnalyzedWord = 0;
    int nbAnalyzedWord = 0;
    int i = 0;
    List<String> l = new ArrayList();
    l.addAll(tokens.getTokens());
    Collections.sort(l);
    Iterator<String> it_normalized = l.iterator();
    Times.start();
    while (it_normalized.hasNext()) {
      String normalizedWord = it_normalized.next();
      List result = Static.analyzer.analyzerToken(normalizedWord);
      if (result.isEmpty()) {
        nbNoAnalyzedWord++;
      } else {
        nbAnalyzedWord++;
      } 
      Static.allResults.put(normalizedWord, result);
      i++;
    } 
    Times.end();
    Statistic.setNbTotalWords(tokens.getNbAllTokens());
    Statistic.setNbWord(tokens.getNbTokens());
    Statistic.setNbAnalyzedWord(nbAnalyzedWord);
    Statistic.setNbNoAnalyzedWord(nbNoAnalyzedWord);
    Statistic.setTimes(Times.getTimes());
    return tokens.getAllText();
  }
  
  public List<String> getResultLemma(String word) {
    Settings.changeSettings(false, false, false, false, false, false, true, false, false, false, false);
    Static.analyzer = new AnalyzerTokens();
    List<String> res = new ArrayList<String>();
    List<Result> result = Static.analyzer.analyzerToken(word);
    if (result.isEmpty()) {
      System.out.println("");
    } else {
      Set<String> resL = new HashSet<String>();
      for (int i = 0; i < result.size(); i++) {
        String str = ((Result)result.get(i)).getLemma();
        if (!str.equals("#"))
          resL.add(str); 
      } 
      res.addAll(resL);
      Collections.sort(res);
    } 
    return res;
  }
  
  public List<String> getResultStem(String word) {
    Settings.changeSettings(false, false, true, false, false, false, false, false, false, false, false);
    Static.analyzer = new AnalyzerTokens();
    List<String> res = new ArrayList<String>();
    List<Result> result = Static.analyzer.analyzerToken(word);
    if (result.isEmpty()) {
      System.out.println("");
    } else {
      Set<String> resL = new HashSet<String>();
      for (int i = 0; i < result.size(); i++) {
        String str = ((Result)result.get(i)).getStem();
        if (!str.equals("#"))
          resL.add(str); 
      } 
      res.addAll(resL);
      Collections.sort(res);
    } 
    return res;
  }
}
