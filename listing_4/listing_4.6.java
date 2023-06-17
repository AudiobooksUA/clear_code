public class wc{
  public static void main(String[] args){
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in))
    String line;
    int lineCount = 0;
    int charCount = 0;
    int wordCount = 0;
    try {
      while ((line = in.readLine()) != null){
        lineCount++;
        charCount += line.length();
        String words[] = line.split("\\W");
        wordCount += words.length;
      } //while
      System.out.println("wordCount = " + wordCount)
      System.out.println("lineCount = " + lineCount)
      System.out.println("charCount = " + charCount)
    } //try
    catch (IOException e){
      System.err.println("Error:" + e.getMessage());
    } //catch
  } //main
}
