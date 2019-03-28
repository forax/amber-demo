package fr.umlv.compilerintrinsic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.umlv.compilerintrinsic.api.Magic;

public class MagicTests {
  @Test
  public void call() {
    var hello = "hello";
    Assertions.assertEquals(5, Magic.call(hello, "length"));   
  }
}
