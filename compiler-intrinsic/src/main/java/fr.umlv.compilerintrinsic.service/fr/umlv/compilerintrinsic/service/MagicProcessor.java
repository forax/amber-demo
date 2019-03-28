package fr.umlv.compilerintrinsic.service;

import java.lang.constant.ClassDesc;
import java.lang.constant.ConstantDesc;
import java.lang.constant.ConstantDescs;
import java.lang.constant.DynamicCallSiteDesc;
import java.lang.constant.MethodTypeDesc;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

import com.sun.tools.javac.intrinsics.Intrinsics;
import com.sun.tools.javac.intrinsics.IntrinsicProcessor;
import com.sun.tools.javac.intrinsics.IntrinsicProcessor.Result;

/**
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class MagicProcessor implements IntrinsicProcessor {
  private static final Method REGISTER;
  static {
    try {
      REGISTER = Intrinsics.class.getDeclaredMethod("register", IntrinsicProcessor.class, Class.class, String.class, Class.class, Class[].class);
      REGISTER.setAccessible(true);
    } catch(ReflectiveOperationException e) {
      throw new AssertionError(e);
    }
  }
  
    @Override
    public void register(Intrinsics intrinsics) {
        System.out.println("Magic processsor registering !");
        
        this.intrinsics = intrinsics;
        //intrinsics.register(this,
        //        Objects.class, "call", Object.class, Object[].class);
        try {
          REGISTER.invoke(intrinsics, this, Objects.class, "call", Object.class, Object[].class);
        } catch(ReflectiveOperationException e) {
          throw new AssertionError(e);
        }
    }

    Intrinsics intrinsics;

  @Override
  public Result tryIntrinsify(ClassDesc ownerDesc, String methodName, MethodTypeDesc methodType, boolean isStatic,
      ClassDesc[] argClassDescs, ConstantDesc[] constantArgs) {
    // TODO
    // if (intrinsics.isAllConstants(constantArgs, false)) {
    // or
    // if (intrinsics.isArrayVarArg(argClassDescs, 0)) {

    return new Result.Indy(
        DynamicCallSiteDesc.of(
            ConstantDescs.ofCallsiteBootstrap(
              ClassDesc.of("fr.umlv.compilerintrinsic.MagicFactory"),
              "bsm",
              ClassDesc.of("java.lang.invoke.CallSite")),
            methodName, methodType, new ConstantDesc[0]),
        argClassDescs.length);

  }
}