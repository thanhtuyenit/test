package nals.tuyen.test.define;

public class ValidateString {
  public static String deleteSpace(String str) {
    str = str.replaceAll("\\s+", " ");
    str = str.replaceAll("(^\\s+|\\s+$)", "");
    return str;
  }
}
