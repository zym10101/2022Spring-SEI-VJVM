package vjvm.interpreter.instruction.math;

import com.sun.org.apache.bcel.internal.generic.FNEG;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author zym
 * @date 2022/06/05 09:30
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XNEG<T> extends Instruction {
  private final Function<T, T> neg;
  private final Function<OperandStack, T> popFunc;
  private final BiConsumer<OperandStack, T> pushFunc;
  private String name;

  public static final XNEG<Integer> INEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG<>((a -> -a), OperandStack::popInt, OperandStack::pushInt, "ineg");
  }

  public static final XNEG<Float> FNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG<>((a -> -a), OperandStack::popFloat, OperandStack::pushFloat, "fneg");
  }

  public static final XNEG<Double> DNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG<>((a -> -a), OperandStack::popDouble, OperandStack::pushDouble, "dneg");
  }

  public static final XNEG<Long> LNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG<>((a -> -a), OperandStack::popLong, OperandStack::pushLong, "lneg");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    T num = popFunc.apply(stack);
    T apply = neg.apply(num);
    pushFunc.accept(stack, apply);
  }

  @Override
  public String toString() {
    return name;
  }
}
