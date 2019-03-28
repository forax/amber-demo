package fr.umlv.compilerintrinsic.api.tool;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Files.walk;
import static java.nio.file.Files.write;
import static org.objectweb.asm.Opcodes.ASM7;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.constant.ClassDesc;
import java.nio.file.Path;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import fr.umlv.compilerintrinsic.api.annotation.CompilerIntrinsic;

public class Rewriter {
  private static final String COMPILER_INTRINSIC_DESC = ClassDesc.of(CompilerIntrinsic.class.getName()).descriptorString();
  private static final String COMPILER_INTRINSIC_CANDIDATE_DESC = ClassDesc.of("java.lang.compiler.IntrinsicCandidate").descriptorString();
  
  public static byte[] rewrite(byte[] code) {
    code[7] = 56; // only V12 is supported
    
    var reader = new ClassReader(code);
    var writer = new ClassWriter(reader, 0);
    
    reader.accept(new ClassVisitor(ASM7, writer) {
      @Override
      public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        var mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new MethodVisitor(ASM7, mv) {
          @Override
          public AnnotationVisitor visitAnnotation(String annotationDescriptor, boolean visible) {
            System.out.println("in " + reader.getClassName() + " " + name + descriptor + " try to rewrite " + annotationDescriptor);
            var desc = annotationDescriptor.equals(COMPILER_INTRINSIC_DESC)? COMPILER_INTRINSIC_CANDIDATE_DESC: annotationDescriptor;
            return super.visitAnnotation(desc, visible);
          }
        };
      }
    }, 0);
    var newCode = writer.toByteArray();
    newCode[7] = 57;
    return newCode;
  }
  
  public static void main(String[] args) throws IOException {
    var directory = Path.of(args[0]);
    System.out.println("rewrite " + directory + " to use replace compiler intrinsic annotation");
    
    try(var stream = walk(directory)) {
      stream
        .filter(path -> path.getFileName().toString().endsWith(".class"))
        .forEach(path -> {
          try {
            //System.out.println("rewrite file " + path);
            write(path, rewrite(readAllBytes(path)));
          } catch (IOException e) {
            throw new UncheckedIOException(e);
          }
        });
    } catch(UncheckedIOException e) {
      throw e.getCause();
    }
  }
}
