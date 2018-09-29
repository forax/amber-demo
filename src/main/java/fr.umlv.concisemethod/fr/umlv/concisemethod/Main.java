package fr.umlv.concisemethod;

public class Main {
  private static void all(String[] args) {
    Logger.main(args);
    //StudentList.main(args); wait until the bug is fixed
    Puzzler.main(args);
    BoundArguments.main(args);
  }
  
  public static void main(String[] args) = Main::all;
}
