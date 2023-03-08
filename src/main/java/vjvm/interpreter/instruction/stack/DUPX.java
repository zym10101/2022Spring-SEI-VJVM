package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

/**
 * @author zym
 * @date 2022/06/05 09:31
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DUPX extends Instruction {
  private int count;
  private String name;

  public static final DUPX DUP(ProgramCounter pc, MethodInfo methodInfo) {
    return new DUPX(1, "dup");
  }

  public static final DUPX DUP2(ProgramCounter pc, MethodInfo methodInfo) {
    return new DUPX(2, "dup2");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    Slots slots = stack.popSlots(count);
    stack.pushSlots(slots);
    stack.pushSlots(slots);
  }

  @Override
  public String toString() {
    return name;
  }
}
