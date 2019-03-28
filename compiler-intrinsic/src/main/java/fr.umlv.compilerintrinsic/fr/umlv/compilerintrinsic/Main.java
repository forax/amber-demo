package fr.umlv.compilerintrinsic;

import java.util.Objects;

public class Main {
  public static void main(String[] args) {
    int value = 1;
    System.out.println(Objects.hash(value, "foo"));
    
    //Magic.call("", name, args)
  }
}
