package fr.umlv.concisemethod;

public class Main {
  private static void all(String[] args) {
    Logger.main(args);
    StudentList.main(args);
    Puzzler.main(args);
    BoundArguments.main(args);
    ConciseMethodRef.main(args);
    Outer.main(args);
  }
  
  public static void main(String[] args) = Main::all;
}
