package AlKhalil2.util;

public class Statistic {
  private static int nbWords;
  
  private static int nbTotalWords;
  
  private static int nbNoAnalyzedWord;
  
  private static int nbAnalyzedWord;
  
  private static long times;
  
  public static void setNbWord(int nbWord) {
    nbWords = nbWord;
  }
  
  public static void setNbTotalWords(int nbTotalWords) {
    Statistic.nbTotalWords = nbTotalWords;
  }
  
  public static void setNbAnalyzedWord(int nbAnalyzedWord) {
    Statistic.nbAnalyzedWord = nbAnalyzedWord;
  }
  
  public static void setNbNoAnalyzedWord(int nbNoAnalyzedWord) {
    Statistic.nbNoAnalyzedWord = nbNoAnalyzedWord;
  }
  
  public static void setTimes(long times) {
    Statistic.times = times;
  }
  
  public static int getNbWord() {
    return nbWords;
  }
  
  public static int getNbTotalWords() {
    return nbTotalWords;
  }
  
  public static int getNbAnalyzedWord() {
    return nbAnalyzedWord;
  }
  
  public static int getNbAnalyzedWordPourCent() {
    return (nbWords == 0) ? 0 : (nbAnalyzedWord * 100 / nbTotalWords);
  }
  
  public static int getNbNoAnalyzedWord() {
    return nbNoAnalyzedWord;
  }
  
  public static int getNbNoAnalyzedWordPourCent() {
    return (nbWords == 0) ? 0 : (nbNoAnalyzedWord * 100 / nbTotalWords);
  }
  
  public static int getWordsOfSec() {
    return (int)((times == 0L) ? 0L : ((nbWords * 1000) / times));
  }
}
