import static com.github.forax.pro.Pro.*;
import static com.github.forax.pro.builder.Builders.*;

resolver.
    //checkForUpdate(true).
    dependencies(
        // JUnit 5
        "org.junit.jupiter.api:5.2.0",
        "org.junit.platform.commons:1.2.0",
        "org.apiguardian.api:1.0.0",
        "org.opentest4j:1.1.0" /*,*/
    )

 compiler.
   sourceRelease(13).
   enablePreview(true)
//     rawArguments(
//         "--processor-module-path", "deps"   // enable JMH annotation processor
//     )
   
packager.
    modules(
        "fr.umlv.concisemethod@1.0/fr.umlv.concisemethod.Main"
    )   
    
runner.
  enablePreview(true)   
    
run(resolver, modulefixer, compiler, tester, packager, runner /*, perfer */)

/exit errorCode()
