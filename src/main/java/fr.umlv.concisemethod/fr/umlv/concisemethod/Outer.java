package fr.umlv.concisemethod;

public class Outer {
  class Inner {
    String foo() -> "foo";
  }
  
  
  String create() = new Inner()::foo;
  
  public static void main(String[] args) {
    System.out.println(new Outer().create());
  }
}
