package fr.umlv.concisemethod;

public class BoundArguments {
  final int value;
  
  BoundArguments(int value) { this.value = value; }
  
  int f() -> value;
  int m(BoundArguments arg) = BoundArguments::f;
  int m() = this::f;
  
  
  //void f2() -> null;
  //void m2() = BoundArguments::f2;
  
  public static void main(String[] args) {
    var arg1 = new BoundArguments(1);
    var arg2 = new BoundArguments(2);
    System.out.println(arg1.m());
    System.out.println(arg1.m(arg2));
  }
}
