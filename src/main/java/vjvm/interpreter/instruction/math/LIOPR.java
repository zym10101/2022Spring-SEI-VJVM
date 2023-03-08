package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.var;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author zym
 * @date 2022/06/05 09:30
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LIOPR<T> extends Instruction {
  private final BiFunction<T, T, T> opFunc;
  private final BiFunction<T, Integer, T> opFuncSH;
  private final Function<OperandStack, T> popFunc;
  private final BiConsumer<OperandStack, T> pushFunc;
  private String name;

  public static final LIOPR<Integer> ISHL(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>(null, (a, b) -> a << (b & 0x1f), OperandStack::popInt, OperandStack::pushInt, "ishl");
  }

  public static final LIOPR<Long> LSHL(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>(null, (a, b) -> a << (b & 0x3f), OperandStack::popLong, OperandStack::pushLong, "lshl");
  }

  public static final LIOPR<Integer> ISHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>(null, (a, b) -> a >> (b & 0x1f), OperandStack::popInt, OperandStack::pushInt, "ishr");
  }

  public static final LIOPR<Long> LSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>(null, (a, b) -> a >> (b & 0x3f), OperandStack::popLong, OperandStack::pushLong, "lshr");
  }

  public static final LIOPR<Integer> IUSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>(null, (a, b) -> a >>> (b & 0x1f), OperandStack::popInt, OperandStack::pushInt, "iushr");
  }

  public static final LIOPR<Long> LUSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>(null, (a, b) -> a >>> (b & 0x3f), OperandStack::popLong, OperandStack::pushLong, "lushr");
  }

  public static final LIOPR<Integer> IAND(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>((a, b) -> a & b, null, OperandStack::popInt, OperandStack::pushInt, "iand");
  }

  public static final LIOPR<Long> LAND(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>((a, b) -> a & b, null, OperandStack::popLong, OperandStack::pushLong, "land");
  }

  public static final LIOPR<Integer> IOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>((a, b) -> a | b, null, OperandStack::popInt, OperandStack::pushInt, "ior");
  }

  public static final LIOPR<Long> LOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>((a, b) -> a | b, null, OperandStack::popLong, OperandStack::pushLong, "lor");
  }

  public static final LIOPR<Integer> IXOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>((a, b) -> a ^ b, null, OperandStack::popInt, OperandStack::pushInt, "ixor");
  }

  public static final LIOPR<Long> LXOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR<>((a, b) -> a ^ b, null, OperandStack::popLong, OperandStack::pushLong, "lxor");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();

    if (name.contains("sh")) {
      int num2 = stack.popInt();
      T num1 = popFunc.apply(stack);
      T apply = opFuncSH.apply(num1, num2);
      pushFunc.accept(stack, apply);
    } else {
      T num2 = popFunc.apply(stack);
      T num1 = popFunc.apply(stack);
      T apply = opFunc.apply(num1, num2);
      pushFunc.accept(stack, apply);
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
