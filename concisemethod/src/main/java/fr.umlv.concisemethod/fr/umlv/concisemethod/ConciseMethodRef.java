package fr.umlv.concisemethod;

public class ConciseMethodRef implements Runnable {
  @Override
  public void run() {
    System.out.println("ConciseMethodRef");
  }
  
  Runnable f(Runnable r) -> r;
  
  // method ref are now limited to bound "constant" 
  //void m() = f(this)::run;
  //void m2() = ((Runnable)this)::run;
  
  String asString() = super::toString;
  
  public static void main(String[] args) {
    var ref = new ConciseMethodRef();
    //ref.m();
    //ref.m2();
    System.out.println(ref.asString());
  }
}

