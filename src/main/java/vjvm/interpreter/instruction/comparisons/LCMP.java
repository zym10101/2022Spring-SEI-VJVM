package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author zym
 * @date 2022/06/05 09:32
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LCMP<T> extends Instruction {
  private final BinaryOperator<T> opFunc;
  private final Function<OperandStack, T> popFunc;
  private final BiConsumer<OperandStack, Integer> pushFunc;
  private String name;

  public static final LCMP<Long> LCMP(ProgramCounter pc, MethodInfo method) {
    return new LCMP<>((a, b) -> (long) (a.compareTo(b)), OperandStack::popLong, OperandStack::pushInt, "lcmp");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    T num2 = popFunc.apply(stack);
    T num1 = popFunc.apply(stack);
    T res = opFunc.apply(num1, num2);
    int value = ((Long) res).intValue();
    pushFunc.accept(stack, value);
  }

  @Override
  public String toString() {
    return name;
  }
}
