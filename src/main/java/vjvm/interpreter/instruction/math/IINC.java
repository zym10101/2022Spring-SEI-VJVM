package vjvm.interpreter.instruction.math;

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
 * @date 2022/06/05 09:30
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IINC extends Instruction {
  private final int index;
  private final int my_const;
  private String name;

  public static final IINC IINC(ProgramCounter pc, MethodInfo method) {
    int index = pc.ubyte();
    int my_const = pc.byte_();
    return new IINC(index, my_const, "iinc");
  }

  @Override
  public void run(JThread thread) {
    Slots slots = thread.top().vars();
    var res = slots.int_(index) + my_const;
    slots.int_(index, res);
  }

  @Override
  public String toString() {
    return String.format("%s %d %d", name, index, my_const);
  }
}
