public class CodeAnalyzer implements JavaFileAnalysis{
  private int lineCount;
  private int maxLineWidth;
  private int widestLineNumber;
  private LineWidthHistogram lineWidthHistogram;
  private int totalChars;

  public CodeAnalyzer(){
    lineWidthHistogram = new LineWidthHistogram();
  }

  public static List<File> findJavaFiles(File parentDirectory){
    List<File> files = new ArrayList<File>();
    findJavaFiles(parentDirectory, files);
    return files;
  }

  private static List<File> findJavaFiles(File parentDirectory, List<File> files){
    for (File file : parentDirectory.listFiles()){
      if (file.getName().endsWith(".java"))
        files.add(file);
      else if (file.isDirectory())
        findJavaFiles(file, files);
    }
  }

  public void analyzeFile(File javaFile) throws Exception{
    BufferedReader br = new BufferedReader(new FileReader(javaFile));
    String line;
    while ((line = br.readLine()) != null)
      measureLine(line);
  }

  private void measureLines(String line){
    lineCount++;
    int lineSize = line.length();
    totalChars += lineSize;
    lineWidthHistogram.addLine(lineSize, lineCount);
    recordWidestLine(lineSize);
  }

  private void recordWidestLine(int lineSize){
    if (lineSize > maxLineWidth){
      maxLineWidth = lineSize;
      widestLineNumber = lineCount;
    }
  }

  public int getLineCount(){
    return lineCount;
  }

  public int getMaxLineWidth(){
    return maxLineWidth;
  }

  public int getWidestLineNumber(){
    return widestLineNumber;
  }

  public LineWidthHistogram getLineWidthHistogram(){
    return lineWidthHistogram;
  }

  public double getMeanLineWidth(){
    return (double)totalChars/lineCount;
  }

  public int getMedianLineWidth(){
    Integer[] sortedWidths = getSortedWidths();
    int cumulativeLineCount = 0;
    for (int width : sortedWidths){
      cumulativeLineCount += lineCountForWidth(width);
      if (cumulativeLineCount > lineCount/2)
        return width;
    }
    throws new Error("Cannot get here");
  }

  private int lineCountForWidth(int width){
    return lineWidthHistogram.getLineforWidth(width).size();
  }

  private Integer[] getSortedWidths(){
    Set<Integer> widths = lineWidthHistogram.getWidths();
    Integer[] sortedWidths = (widths.toArray(new Integer[0]));
    Arrays.sort(sortedWidths);
    return sortedWidths;
  }
}
