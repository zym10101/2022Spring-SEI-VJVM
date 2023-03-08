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
public class SWAP extends Instruction {
  private String name;

  public static final SWAP SWAP(ProgramCounter pc, MethodInfo method) {
    return new SWAP("swap");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    Slots upper = stack.popSlots(1);
    Slots lower = stack.popSlots(1);
    stack.pushSlots(upper);
    stack.pushSlots(lower);
  }

  @Override
  public String toString() {
    return name;
  }
}
