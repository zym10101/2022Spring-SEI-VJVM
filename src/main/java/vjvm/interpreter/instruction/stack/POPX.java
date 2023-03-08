package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JFrame;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

/**
 * @author zym
 * @date 2022/06/05 09:31
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class POPX extends Instruction {
  private int count;
  private String name;

  public static final POPX POP(ProgramCounter pc, MethodInfo methodInfo) {
    return new POPX(1, "pop");
  }

  public static final POPX POP2(ProgramCounter pc, MethodInfo methodInfo) {
    return new POPX(2, "pop2");
  }

  @Override
  public void run(JThread thread) {
    thread.top().stack().popSlots(count);
  }

  @Override
  public String toString() {
    return name;
  }
}
