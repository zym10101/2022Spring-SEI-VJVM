package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.classfiledefs.Descriptors;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JClass;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.MethodrefConstant;

public class INVOKESTATIC extends Instruction {
  private final MethodInfo method;

  public INVOKESTATIC(ProgramCounter pc, MethodInfo method) {
    // decode invokestatic
    JClass thisClass = method.jClass();
    ConstantPool constantPool = thisClass.constantPool();
    MethodrefConstant methodRef = (MethodrefConstant) constantPool.constant(pc.short_());
    JClass jClass = thisClass.classLoader().loadClass(Descriptors.of(methodRef.className()));
    this.method = jClass.findMethod(methodRef.getName(), methodRef.getType());
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var args = stack.popSlots(method.argc());
    thread.context().interpreter().invoke(method, thread, args);
  }

  @Override
  public String toString() {
    return String.format("invokestatic %s:%s:%s", method.jClass().name(), method.name(), method.descriptor());
  }
}
