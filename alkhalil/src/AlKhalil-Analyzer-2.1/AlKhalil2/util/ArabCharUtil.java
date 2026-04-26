package AlKhalil2.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ArabCharUtil {
  public static String correctErreur(String normalizedWord) {
    if (normalizedWord.length() >= 3 && normalizedWord.charAt(1) == 'ّ')
      normalizedWord = normalizedWord.charAt(0) + normalizedWord.substring(2); 
    normalizedWord = normalizedWord.replaceAll("اُ", "ا");
    normalizedWord = normalizedWord.replaceAll("اَّ", "َّا");
    normalizedWord = normalizedWord.replaceAll("ىً$", "ًى");
    normalizedWord = normalizedWord.replaceAll("اً$", "ًا");
    normalizedWord = normalizedWord.replaceAll("اَ", "ا");
    normalizedWord = normalizedWord.replaceAll("اِ", "ا");
    normalizedWord = normalizedWord.replaceAll("ِا", "ا");
    normalizedWord = normalizedWord.replaceAll("ِّ", "ِّ");
    normalizedWord = normalizedWord.replaceAll("َّ", "َّ");
    normalizedWord = normalizedWord.replaceAll("ُّ", "ُّ");
    normalizedWord = normalizedWord.replaceAll("آَ", "آ");
    return normalizedWord;
  }
  
  public static boolean isSeparator(char car) {
    switch (car) {
      case '.':
        return true;
      case '!':
        return true;
      case ':':
        return true;
      case '؟':
        return true;
      case '؛':
        return true;
    } 
    return false;
  }
  
  public static String[] getSepator() {
    String[] Str = { "\\.", "!", ":", "،", "؟", "؛" };
    return Str;
  }
  
  public static boolean isDiacritic(char c) {
    switch (c) {
      case 'ً':
      case 'ٌ':
      case 'ٍ':
      case 'َ':
      case 'ُ':
      case 'ِ':
      case 'ّ':
      case 'ْ':
        return true;
    } 
    return false;
  }
  
  public static boolean isSolar(char c) {
    switch (c) {
      case 'ت':
        return true;
      case 'ث':
        return true;
      case 'د':
        return true;
      case 'ذ':
        return true;
      case 'ر':
        return true;
      case 'ز':
        return true;
      case 'س':
        return true;
      case 'ش':
        return true;
      case 'ص':
        return true;
      case 'ض':
        return true;
      case 'ط':
        return true;
      case 'ظ':
        return true;
      case 'ل':
        return true;
      case 'ن':
        return true;
    } 
    return false;
  }
  
  public static boolean isNumeric(char c) {
    switch (c) {
      case '0':
        return true;
      case '1':
        return true;
      case '2':
        return true;
      case '3':
        return true;
      case '4':
        return true;
      case '5':
        return true;
      case '6':
        return true;
      case '7':
        return true;
      case '8':
        return true;
      case '9':
        return true;
    } 
    return false;
  }
  
  public static boolean isHamza(char c) {
    switch (c) {
      case 'ء':
        return true;
      case 'أ':
        return true;
      case 'إ':
        return true;
      case 'ؤ':
        return true;
      case 'ئ':
        return true;
    } 
    return false;
  }
  
  public static boolean isCompletVoyeled(String word) {
    String ch_1 = "ضصثقفغعهخحجدذطكمنتليسشبءؤرةزظإأآ";
    String ch_2 = "وي";
    String ch_3 = "اى";
    String ch_4 = "ّ";
    String ch_5 = "ًٌٍَُِْ";
    String exp = "(([" + ch_1 + ch_2 + "][" + ch_4 + "]*[" + ch_5 + "])|([" + ch_3 + ch_2 + "]))+";
    try {
      Pattern p = Pattern.compile(exp);
      Matcher m = p.matcher(word);
      String str = "";
      while (m.find())
        str = m.group(0); 
      return word.equals(str);
    } catch (PatternSyntaxException pse) {
      return false;
    } 
  }
  
  public static char getCharArabicToLatinCode(char c) {
    switch (c) {
      case 'ء':
        return 'Á';
      case 'آ':
        return 'Â';
      case 'أ':
        return 'Ã';
      case 'ؤ':
        return 'Ä';
      case 'إ':
        return 'Å';
      case 'ئ':
        return 'Æ';
      case 'ا':
        return 'Ç';
      case 'ب':
        return 'È';
      case 'ة':
        return 'É';
      case 'ت':
        return 'Ê';
      case 'ث':
        return 'Ë';
      case 'ج':
        return 'Ì';
      case 'ح':
        return 'Í';
      case 'خ':
        return 'Î';
      case 'د':
        return 'Ï';
      case 'ذ':
        return 'Ð';
      case 'ر':
        return 'Ñ';
      case 'ز':
        return 'Ò';
      case 'س':
        return 'Ó';
      case 'ش':
        return 'Ô';
      case 'ص':
        return 'Õ';
      case 'ض':
        return 'Ö';
      case 'ط':
        return 'Ø';
      case 'ظ':
        return 'Ù';
      case 'ع':
        return 'Ú';
      case 'غ':
        return 'Û';
      case 'ف':
        return 'Ý';
      case 'ق':
        return 'Þ';
      case 'ك':
        return 'ß';
      case 'ل':
        return 'á';
      case 'م':
        return 'ã';
      case 'ن':
        return 'ä';
      case 'ه':
        return 'å';
      case 'و':
        return 'æ';
      case 'ى':
        return 'ì';
      case 'ي':
        return 'í';
      case 'ـ':
        return 'Ü';
      case 'ً':
        return 'ð';
      case 'ٌ':
        return 'ñ';
      case 'ٍ':
        return 'ò';
      case 'َ':
        return 'ó';
      case 'ُ':
        return 'õ';
      case 'ِ':
        return 'ö';
      case 'ّ':
        return 'ø';
      case 'ْ':
        return 'ú';
    } 
    return c;
  }
  
  public static char getCharCodeLatinToArabic(char c) {
    switch (c) {
      case 'Á':
        return 'ء';
      case 'Â':
        return 'آ';
      case 'Ã':
        return 'أ';
      case 'Ä':
        return 'ؤ';
      case 'Å':
        return 'إ';
      case 'Æ':
        return 'ئ';
      case 'Ç':
        return 'ا';
      case 'È':
        return 'ب';
      case 'É':
        return 'ة';
      case 'Ê':
        return 'ت';
      case 'Ë':
        return 'ث';
      case 'Ì':
        return 'ج';
      case 'Í':
        return 'ح';
      case 'Î':
        return 'خ';
      case 'Ï':
        return 'د';
      case 'Ð':
        return 'ذ';
      case 'Ñ':
        return 'ر';
      case 'Ò':
        return 'ز';
      case 'Ó':
        return 'س';
      case 'Ô':
        return 'ش';
      case 'Õ':
        return 'ص';
      case 'Ö':
        return 'ض';
      case 'Ø':
        return 'ط';
      case 'Ù':
        return 'ظ';
      case 'Ú':
        return 'ع';
      case 'Û':
        return 'غ';
      case 'Ý':
        return 'ف';
      case 'Þ':
        return 'ق';
      case 'ß':
        return 'ك';
      case 'á':
        return 'ل';
      case 'ã':
        return 'م';
      case 'ä':
        return 'ن';
      case 'å':
        return 'ه';
      case 'æ':
        return 'و';
      case 'ì':
        return 'ى';
      case 'í':
        return 'ي';
      case 'Ü':
        return 'ـ';
      case 'ð':
        return 'ً';
      case 'ñ':
        return 'ٌ';
      case 'ò':
        return 'ٍ';
      case 'ó':
        return 'َ';
      case 'õ':
        return 'ُ';
      case 'ö':
        return 'ِ';
      case 'ø':
        return 'ّ';
      case 'ú':
        return 'ْ';
    } 
    return '-';
  }
  
  public static String getStringCodeLatinToArabic(String str) {
    if (str == null)
      return null; 
    char[] tab = str.toCharArray();
    String st = "";
    for (int i = 0; i < tab.length; i++)
      st = st + "" + getCharCodeLatinToArabic(tab[i]); 
    return st;
  }
  
  public static char getRomanizeChar(char c) {
    switch (c) {
      case 'ء':
        return '\'';
      case 'آ':
        return '|';
      case 'أ':
        return '>';
      case 'ؤ':
        return '&';
      case 'إ':
        return '<';
      case 'ئ':
        return '}';
      case 'ا':
        return 'A';
      case 'ب':
        return 'b';
      case 'ة':
        return 'p';
      case 'ت':
        return 't';
      case 'ث':
        return 'v';
      case 'ج':
        return 'j';
      case 'ح':
        return 'H';
      case 'خ':
        return 'x';
      case 'د':
        return 'd';
      case 'ذ':
        return '*';
      case 'ر':
        return 'r';
      case 'ز':
        return 'z';
      case 'س':
        return 's';
      case 'ش':
        return '$';
      case 'ص':
        return 'S';
      case 'ض':
        return 'D';
      case 'ط':
        return 'T';
      case 'ظ':
        return 'Z';
      case 'ع':
        return 'E';
      case 'غ':
        return 'g';
      case 'ـ':
        return '_';
      case 'ف':
        return 'f';
      case 'ق':
        return 'q';
      case 'ك':
        return 'k';
      case 'ل':
        return 'l';
      case 'م':
        return 'm';
      case 'ن':
        return 'n';
      case 'ه':
        return 'h';
      case 'و':
        return 'w';
      case 'ى':
        return 'Y';
      case 'ي':
        return 'y';
      case 'ً':
        return 'F';
      case 'ٌ':
        return 'N';
      case 'ٍ':
        return 'K';
      case 'َ':
        return 'a';
      case 'ُ':
        return 'u';
      case 'ِ':
        return 'i';
      case 'ّ':
        return '~';
      case 'ْ':
        return 'o';
      case 'ٰ':
        return '`';
      case 'ٱ':
        return '{';
      case 'پ':
        return 'P';
      case 'چ':
        return 'J';
      case 'ڤ':
        return 'V';
      case 'گ':
        return 'G';
      case 'ژ':
        return 'R';
      case '،':
        return ',';
      case '؛':
        return ';';
      case '؟':
        return '?';
    } 
    return c;
  }
  
  public static String getArabicTransliteration(String word) {
    String st = "";
    for (int i = 0; i < word.length(); i++)
      st = st + "" + getRomanizeChar(word.charAt(i)); 
    return st;
  }
  
  public static char getArabicCharacter(char c) {
    switch (c) {
      case '\'':
        return 'ء';
      case '|':
        return 'آ';
      case '>':
        return 'أ';
      case '&':
        return 'ؤ';
      case '<':
        return 'إ';
      case '}':
        return 'ئ';
      case 'A':
        return 'ا';
      case 'b':
        return 'ب';
      case 'p':
        return 'ة';
      case 't':
        return 'ت';
      case 'v':
        return 'ث';
      case 'j':
        return 'ج';
      case 'H':
        return 'ح';
      case 'x':
        return 'خ';
      case 'd':
        return 'د';
      case '*':
        return 'ذ';
      case 'r':
        return 'ر';
      case 'z':
        return 'ز';
      case 's':
        return 'س';
      case '$':
        return 'ش';
      case 'S':
        return 'ص';
      case 'D':
        return 'ض';
      case 'T':
        return 'ط';
      case 'Z':
        return 'ظ';
      case 'E':
        return 'ع';
      case 'g':
        return 'غ';
      case '_':
        return 'ـ';
      case 'f':
        return 'ف';
      case 'q':
        return 'ق';
      case 'k':
        return 'ك';
      case 'l':
        return 'ل';
      case 'm':
        return 'م';
      case 'n':
        return 'ن';
      case 'h':
        return 'ه';
      case 'w':
        return 'و';
      case 'Y':
        return 'ى';
      case 'y':
        return 'ي';
      case 'F':
        return 'ً';
      case 'N':
        return 'ٌ';
      case 'K':
        return 'ٍ';
      case 'a':
        return 'َ';
      case 'u':
        return 'ُ';
      case 'i':
        return 'ِ';
      case '~':
        return 'ّ';
      case 'o':
        return 'ْ';
      case '`':
        return 'ٰ';
      case '{':
        return 'ٱ';
      case 'P':
        return 'پ';
      case 'J':
        return 'چ';
      case 'V':
        return 'ڤ';
      case 'G':
        return 'گ';
      case 'R':
        return 'ژ';
      case ',':
        return '،';
      case ';':
        return '؛';
      case '?':
        return '؟';
    } 
    return c;
  }
  
  public static String getRomanizeTransliteration(String word) {
    String st = "";
    for (int i = 0; i < word.length(); i++)
      st = st + "" + getArabicCharacter(word.charAt(i)); 
    return st;
  }
}
