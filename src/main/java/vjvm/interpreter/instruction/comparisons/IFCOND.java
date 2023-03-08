package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.Function;

/**
 * @author zym
 * @date 2022/06/05 09:32
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IFCOND<T> extends Instruction {
  private final Function<Integer, Integer> opFunc;
  private final Function<OperandStack, Integer> popFunc;
  private short offset;
  private String name;

  public static final IFCOND<Integer> IFEQ(ProgramCounter pc, MethodInfo method) {
    return new IFCOND<>((a) -> (a == 0 ? 1 : 0), OperandStack::popInt, pc.short_(), "ifeq");
  }

  public static final IFCOND<Integer> IFNE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND<>((a) -> (a != 0 ? 1 : 0), OperandStack::popInt, pc.short_(), "ifne");
  }

  public static final IFCOND<Integer> IFLT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND<>((a) -> (a < 0 ? 1 : 0), OperandStack::popInt, pc.short_(), "iflt");
  }

  public static final IFCOND<Integer> IFGE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND<>((a) -> (a >= 0 ? 1 : 0), OperandStack::popInt, pc.short_(), "ifge");
  }

  public static final IFCOND<Integer> IFGT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND<>((a) -> (a > 0 ? 1 : 0), OperandStack::popInt, pc.short_(), "ifgt");
  }

  public static final IFCOND<Integer> IFLE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND<>((a) -> (a <= 0 ? 1 : 0), OperandStack::popInt, pc.short_(), "ifle");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    Integer num = popFunc.apply(stack);
    ProgramCounter pc = thread.pc();
    Integer res = opFunc.apply(num);
    if (res == 1) {
      pc.move(offset - 3);
    }
  }

  @Override
  public String toString() {
    return String.format("%s %d", name, offset);
  }
}
