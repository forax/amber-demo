package fr.umlv.concisemethod;

import static java.lang.invoke.MethodHandles.lookup;
import static java.lang.invoke.MethodType.methodType;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.UndeclaredThrowableException;

public final class DynFunction {
  private final MethodHandle mh;

  DynFunction(MethodHandle mh) {
    this.mh = mh;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof DynFunction)) {
      return false;
    }
    return mh == ((DynFunction) obj).mh;
  }

  @Override
  public int hashCode() {
    return mh.hashCode();
  }

  static <R> R call(DynFunction self, Object arg1) throws Throwable = self.mh::invoke;
  static <R> R call(DynFunction self, Object arg1, Object arg2) throws Throwable = self.mh::invoke;

  public static void main(String[] args) {
    try {
      var mh = lookup().findVirtual(DynFunction.class, "equals", methodType(boolean.class, Object.class));
      var function = new DynFunction(mh);
      boolean result = call(function, function);
      System.out.println("equals " + result);
    } catch(Error | RuntimeException e) {
      throw e;
    } catch(Throwable t) {
      throw new UndeclaredThrowableException(t);
    }
  }
}
