import com.github.forax.pro.api.*;
import com.github.forax.pro.helper.util.*;
import static java.nio.file.Files.*;
import static com.github.forax.pro.helper.FileHelper.*;
import static com.github.forax.pro.Pro.*;
import static com.github.forax.pro.builder.Builders.*;

resolver.
    //checkForUpdate(true).
    dependencies(
        // ASM
        "org.objectweb.asm:7.0",
    
        // JUnit 5
        "org.junit.jupiter.api:5.2.0",
        "org.junit.platform.commons:1.2.0",
        "org.apiguardian.api:1.0.0",
        "org.opentest4j:1.1.0" /*,*/
    )

compiler.
   rawArguments("--source", "13",
                "--add-exports", "jdk.compiler/com.sun.tools.javac.intrinsics=fr.umlv.compilerintrinsic.service")
   
var rewriter = command("rewriter", () -> {  // rewrite bytecode to use java.lang.compiler.CompilerIntrinsicCandidate
  runner.
    modulePath(location("target/main/exploded/fr.umlv.compilerintrinsic.api")).
    module("fr.umlv.compilerintrinsic.api/fr.umlv.compilerintrinsic.api.tool.Rewriter").
    //rawArguments("--add-exports", "jdk.compiler/com.sun.tools.javac.intrinsics=fr.umlv.compilerintrinsic.service").
    mainArguments("target/main/exploded/fr.umlv.compilerintrinsic.api/");
  run(runner);
})

var delete_test = command("delete_test", () -> deleteAllFiles(location("target/test"), true))

var compiler2 = command("compiler2", () -> {
  compiler.rawArguments(StableList.from(compiler.rawArguments().orElseThrow())
    .append("-J--module-path=target/main/exploded/fr.umlv.compilerintrinsic.service")
    .append("-J--add-modules=fr.umlv.compilerintrinsic.service")
    .append("-J--add-exports=jdk.compiler/com.sun.tools.javac.intrinsics=fr.umlv.compilerintrinsic.service")
  );
  run(compiler);
})
   
packager.
    modules(
        "fr.umlv.compilerintrinsic@1.0/fr.umlv.compilerintrinsic.Main",
        "fr.umlv.compilerintrinsic.api@1.0"
    )   
    
var delete_service = command("delete_service", () -> {
  for(var file: files(location("target/main/artifact"), p -> p.toString().contains("service"))) {
    delete(file);
  }
})    
    
runner.
  modulePath(location("target/main/artifact")).
  module("fr.umlv.compilerintrinsic")  
    
run(resolver, modulefixer,
    compiler,
    rewriter,
    packager,
    linker,
    delete_test,
    compiler2,
    tester, packager,
    //delete_service,
    runner
    )

/exit errorCode()
