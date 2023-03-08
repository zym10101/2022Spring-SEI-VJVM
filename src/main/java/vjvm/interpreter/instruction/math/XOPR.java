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
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author zym
 * @date 2022/06/05 09:30
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XOPR<T> extends Instruction {
  private final BinaryOperator<T> opFunc;
  private final Function<OperandStack, T> popFunc;
  private final BiConsumer<OperandStack, T> pushFunc;
  private String name;

  public static final XOPR<Integer> IADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(Integer::sum, OperandStack::popInt, OperandStack::pushInt, "iadd");
  }

  public static final XOPR<Float> FADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(Float::sum, OperandStack::popFloat, OperandStack::pushFloat, "fadd");
  }

  public static final XOPR<Double> DADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(Double::sum, OperandStack::popDouble, OperandStack::pushDouble, "dadd");
  }

  public static final XOPR<Long> LADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>((Long::sum), OperandStack::popLong, OperandStack::pushLong, "ladd");
  }

  public static final XOPR<Integer> ISUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a - b), OperandStack::popInt, OperandStack::pushInt, "isub");
  }

  public static final XOPR<Float> FSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a - b), OperandStack::popFloat, OperandStack::pushFloat, "fsub");
  }

  public static final XOPR<Double> DSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a - b), OperandStack::popDouble, OperandStack::pushDouble, "dsub");
  }

  public static final XOPR<Long> LSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a - b), OperandStack::popLong, OperandStack::pushLong, "lsub");
  }

  public static final XOPR<Integer> IMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a * b), OperandStack::popInt, OperandStack::pushInt, "imul");
  }

  public static final XOPR<Float> FMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a * b), OperandStack::popFloat, OperandStack::pushFloat, "fmul");
  }

  public static final XOPR<Double> DMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a * b), OperandStack::popDouble, OperandStack::pushDouble, "dmul");
  }

  public static final XOPR<Long> LMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a * b), OperandStack::popLong, OperandStack::pushLong, "lmul");
  }

  public static final XOPR<Integer> IDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a / b), OperandStack::popInt, OperandStack::pushInt, "idiv");
  }

  public static final XOPR<Float> FDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a / b), OperandStack::popFloat, OperandStack::pushFloat, "fdiv");
  }

  public static final XOPR<Double> DDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a / b), OperandStack::popDouble, OperandStack::pushDouble, "ddiv");
  }

  public static final XOPR<Long> LDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a / b), OperandStack::popLong, OperandStack::pushLong, "ldiv");
  }

  public static final XOPR<Integer> IREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a % b), OperandStack::popInt, OperandStack::pushInt, "irem");
  }

  public static final XOPR<Float> FREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a % b), OperandStack::popFloat, OperandStack::pushFloat, "frem");
  }

  public static final XOPR<Double> DREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a % b), OperandStack::popDouble, OperandStack::pushDouble, "drem");
  }

  public static final XOPR<Long> LREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR<>(((a, b) -> a % b), OperandStack::popLong, OperandStack::pushLong, "lrem");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    T num2 = popFunc.apply(stack);
    T num1 = popFunc.apply(stack);
    T apply = opFunc.apply(num1, num2);
    pushFunc.accept(stack, apply);
  }

  @Override
  public String toString() {
    return name;
  }
}
