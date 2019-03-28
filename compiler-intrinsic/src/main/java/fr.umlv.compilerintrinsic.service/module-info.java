module fr.umlv.compilerintrinsic.service {
  requires jdk.compiler;
  
  provides com.sun.tools.javac.intrinsics.IntrinsicProcessor with
      fr.umlv.compilerintrinsic.service.MagicProcessor;
}