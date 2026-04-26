package AlKhalil2.util.constants;

import AlKhalil2.morphology.analyzer.AnalyzerTokens;
import java.util.HashMap;
import java.util.Map;

public class Static {
  public static Map allResults = new HashMap<Object, Object>();
  
  public static Map allResultsBis = new HashMap<Object, Object>();
  
  public static AnalyzerTokens analyzer;
  
  public static boolean isNominalAnalyzed = true;
  
  public static boolean isVerbalAnalyzed = true;
  
  public static boolean isToolwordsAnalyzed = true;
  
  public static boolean isPropernounAnalyzed = true;
  
  public static boolean isCliticsAnalyzed = false;
  
  public static final int Max_Enclitic = 5;
  
  public static final int Max_Proclitic = 5;
  
  public static final int Min_VerbalPattern = 1;
  
  public static final int Max_VerbalPattern = 9;
  
  public static final int Min_NominalPattern = 2;
  
  public static final int Max_NominalPattern = 11;
  
  public static boolean StaticUnvoweled = false;
  
  public static boolean isArabicLangue = true;
}
