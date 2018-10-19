package fr.umlv.concisemethod;

public class Outer {
  class Inner {
    String foo() -> "foo";
  }
  
  
  // method ref are now limited to more or less constant
  //String create() = new Inner()::foo;
  
  public static void main(String[] args) {
    //System.out.println(new Outer().create());
  }
}
