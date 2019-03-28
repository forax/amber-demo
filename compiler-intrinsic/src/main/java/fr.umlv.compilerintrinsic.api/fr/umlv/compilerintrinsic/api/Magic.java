package fr.umlv.compilerintrinsic.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;

import fr.umlv.compilerintrinsic.api.annotation.CompilerIntrinsic;

public class Magic {
  //@java.lang.compiler.IntrinsicCandidate
  @CompilerIntrinsic
  public static Object call(Object receiver, String name, Object... args) {
    try {
      return receiver.getClass().getMethod(name, Arrays.stream(args).map(Object::getClass).toArray(Class[]::new)).invoke(receiver, args);
    } catch (IllegalAccessException e) {
      throw (IllegalAccessError)new IllegalAccessError().initCause(e);
    } catch (NoSuchMethodException e) {
      throw (NoSuchMethodError)new NoSuchMethodError().initCause(e);
    } catch (InvocationTargetException e) {
      var cause = e.getCause();
      if (cause instanceof RuntimeException) {
        throw (RuntimeException)cause;
      }
      if (cause instanceof Error) {
        throw (Error)cause;
      }
      throw new UndeclaredThrowableException(cause);
    } 
  }
}
