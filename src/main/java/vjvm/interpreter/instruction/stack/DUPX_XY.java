package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

/**
 * @author zym
 * @date 2022/06/05 09:31
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DUPX_XY extends Instruction {
  private int count;
  private int tempCount;
  private String name;

  public static final DUPX_XY DUP_X1(ProgramCounter pc, MethodInfo methodInfo) {
    return new DUPX_XY(1, 1, "dup_x1");
  }

  public static final DUPX_XY DUP_X2(ProgramCounter pc, MethodInfo methodInfo) {
    return new DUPX_XY(1, 2, "dup_x2");
  }

  public static final DUPX_XY DUP2_X1(ProgramCounter pc, MethodInfo methodInfo) {
    return new DUPX_XY(2, 1, "dup2_x1");
  }

  public static final DUPX_XY DUP2_X2(ProgramCounter pc, MethodInfo methodInfo) {
    return new DUPX_XY(2, 2, "dup2_x2");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    Slots slots = stack.popSlots(count);
    Slots temp = stack.popSlots(tempCount);
    stack.pushSlots(slots);
    stack.pushSlots(temp);
    stack.pushSlots(slots);
  }

  @Override
  public String toString() {
    return name;
  }
}
