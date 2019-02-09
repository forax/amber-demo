package fr.umlv.concisemethod;

public class ExprSwitch {
  private static int associate(int value) {
    return switch(value) {
      case 1 -> 23;
      case 2 -> 48;
      default -> throw new AssertionError();
    };
  }
  
  public static void main(String[] args) {
    
  }
}
