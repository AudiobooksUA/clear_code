public class UserValidator{
  private Cryptographer cryptographer;

  public boolean checkPassword(String iserName, String password){
    User user = UserGateway.findByName(userName);
    if (user != User.NULL){
      String codePhrase = user.getPhraseEncodedByPassword();
      String phrase = cryptographer.decrypt(codePhrase, password);
      if ("Valid Password".equals(phrase)){
        Session.initialize();
        return true;
      }
    }
    return false;
  }
}
