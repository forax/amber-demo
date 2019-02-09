package fr.umlv.concisemethod;

import java.util.function.Function;

public class Puzzler {
  static Function<String, String> m() -> null;
  static String m(String s) -> null;
  
  static Function<String, String> fun1() = Puzzler::m;
  static Function<String, String> fun2() -> Puzzler::m;
  
  public static void main(String[] args) {
    System.out.println(fun1());
    System.out.println(fun2());
  }
}
