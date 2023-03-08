package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

/**
 * @author zym
 * @date 2022/06/05 09:33
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NOP extends Instruction {
  private String name;

  public static NOP NOP(ProgramCounter pc, MethodInfo method) {
    return new NOP("nop");
  }

  @Override
  public void run(JThread thread) {

  }

  @Override
  public String toString() {
    return name;
  }
}
