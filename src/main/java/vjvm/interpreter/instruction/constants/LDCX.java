package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JFrame;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.*;

/**
 * @author zym
 * @date 2022/06/05 09:33
 **/

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LDCX extends Instruction {

  private int index;
  private String name;

  public static final LDCX LDC(ProgramCounter pc, MethodInfo method) {
    return new LDCX(pc.ubyte(), "ldc");
  }

  public static final LDCX LDC_W(ProgramCounter pc, MethodInfo method) {
    return new LDCX(pc.ushort(), "ldc_w");
  }

  public static final LDCX LDC2_W(ProgramCounter pc, MethodInfo method) {
    return new LDCX(pc.ushort(), "ldc2_w");
  }


  @Override
  public void run(JThread thread) {
    JFrame top = thread.top();
    var stack = top.stack();
    Constant constant = top.jClass().constantPool().constant(index);
    if (constant instanceof IntegerConstant) {
      stack.pushInt(((IntegerConstant) constant).value());
    } else if (constant instanceof LongConstant) {
      stack.pushLong(((LongConstant) constant).value());
    } else if (constant instanceof FloatConstant) {
      stack.pushFloat(((FloatConstant) constant).value());
    } else if (constant instanceof DoubleConstant) {
      stack.pushDouble(((DoubleConstant) constant).value());
    }
  }

  @Override
  public String toString() {
    return String.format("%s %d", name, index);
  }
}
