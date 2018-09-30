package fr.umlv.concisemethod;

import java.util.function.IntConsumer;

public class FieldVersusMethod {
  static void foo(int i) -> null; 
  static IntConsumer foo() -> null; 
  
  static IntConsumer bar() = FieldVersusMethod::foo;
  static IntConsumer bar = FieldVersusMethod::foo;
}
