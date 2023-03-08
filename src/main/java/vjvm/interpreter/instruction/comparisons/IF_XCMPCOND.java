package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author zym
 * @date 2022/06/05 09:32
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IF_XCMPCOND<T> extends Instruction {
  private final BinaryOperator<Integer> opFunc;
  private final Function<OperandStack, Integer> popFunc;

  private short offset;
  private String name;

  public static final IF_XCMPCOND<Integer> IF_ICMPEQ(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND<>((a, b) -> (Objects.equals(a, b) ? 1 : 0), OperandStack::popInt, pc.short_(), "if_icmpeq");
  }

  public static final IF_XCMPCOND<Integer> IF_ICMPNE(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND<>((a, b) -> (!Objects.equals(a, b) ? 1 : 0), OperandStack::popInt, pc.short_(), "if_icmpne");
  }

  public static final IF_XCMPCOND<Integer> IF_ICMPLT(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND<>((a, b) -> (a < b ? 1 : 0), OperandStack::popInt, pc.short_(), "if_icmplt");
  }

  public static final IF_XCMPCOND<Integer> IF_ICMPGE(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND<>((a, b) -> (a >= b ? 1 : 0), OperandStack::popInt, pc.short_(), "if_icmpge");
  }

  public static final IF_XCMPCOND<Integer> IF_ICMPGT(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND<>((a, b) -> (a > b ? 1 : 0), OperandStack::popInt, pc.short_(), "if_icmpgt");
  }

  public static final IF_XCMPCOND<Integer> IF_ICMPLE(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND<>((a, b) -> (a <= b ? 1 : 0), OperandStack::popInt, pc.short_(), "if_icmple");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    Integer num2 = popFunc.apply(stack);
    Integer num1 = popFunc.apply(stack);
    Integer res = opFunc.apply(num1, num2);
    ProgramCounter pc = thread.pc();
    if (res == 1) {
      pc.move(offset - 3);
    }
  }

  @Override
  public String toString() {
    return String.format("%s %d", name, offset);
  }
}
