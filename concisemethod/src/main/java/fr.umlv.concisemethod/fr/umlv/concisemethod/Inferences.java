package fr.umlv.concisemethod;

import java.util.List;

public class Inferences {
  List<String> list() -> List.of();
  
  Runnable foo() -> () -> { /*empty*/ };
  
  static class Internal {
    void $() -> 3;
  }
  
  void m() {
    //int $() -> 3;  // doesn't compile
  }
}
