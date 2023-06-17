private class  GuessStatsticsMessage{
  private String number;
  private String verb;
  private String pluralModifier;

  public String make(char candidate, int count){
    CreatePluralDependentMessageParts(count);
    return String.format(
      "There %s %s %s%s",
      verb, number, candidate, pluralModifier);
  }

  private void CreatePluralDependentMessageParts(int ciunt){
    if (count == 0){
      thereAreNoLetters();
    }else if (count == 1){
      thereIsOneLetter();
    }else{
      thereAreManyLetters(count);
    }
  }

  private void thereAreManyLetters(int count){
    number = Integer.toString(count);
    verb = "are";
    pluralModifier = "s";
  }

  private void thereIsOneLetter(int count){
    number = "1";
    verb = "is";
    pluralModifier = "";
  }

  private void thereAreNoLetters(int count){
    number = "no";
    verb = "are";
    pluralModifier = "s";
  }
}
