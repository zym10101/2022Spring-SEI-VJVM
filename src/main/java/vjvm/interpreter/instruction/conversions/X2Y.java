package vjvm.interpreter.instruction.conversions;

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
 * @date 2022/06/05 09:31
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class X2Y<T1, T2> extends Instruction {
  private final Function<T1, T2> opFunc;
  private final Function<OperandStack, T1> popFunc;
  private final BiConsumer<OperandStack, T2> pushFunc;
  private String name;

  public static final X2Y<Integer, Long> I2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Integer::longValue, OperandStack::popInt, OperandStack::pushLong, "i2l");
  }

  public static final X2Y<Integer, Float> I2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Integer::floatValue, OperandStack::popInt, OperandStack::pushFloat, "i2f");
  }

  public static final X2Y<Integer, Double> I2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Integer::doubleValue, OperandStack::popInt, OperandStack::pushDouble, "i2d");
  }

  public static final X2Y<Long, Integer> L2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Long::intValue, OperandStack::popLong, OperandStack::pushInt, "l2i");
  }

  public static final X2Y<Long, Float> L2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Long::floatValue, OperandStack::popLong, OperandStack::pushFloat, "l2f");
  }

  public static final X2Y<Long, Double> L2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Long::doubleValue, OperandStack::popLong, OperandStack::pushDouble, "l2d");
  }

  public static final X2Y<Float, Integer> F2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Float::intValue, OperandStack::popFloat, OperandStack::pushInt, "f2i");
  }

  public static final X2Y<Float, Long> F2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Float::longValue, OperandStack::popFloat, OperandStack::pushLong, "f2l");
  }

  public static final X2Y<Float, Double> F2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Float::doubleValue, OperandStack::popFloat, OperandStack::pushDouble, "f2d");
  }

  public static final X2Y<Double, Integer> D2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Double::intValue, OperandStack::popDouble, OperandStack::pushInt, "d2i");
  }

  public static final X2Y<Double, Long> D2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Double::longValue, OperandStack::popDouble, OperandStack::pushLong, "d2l");
  }

  public static final X2Y<Double, Float> D2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Double::floatValue, OperandStack::popDouble, OperandStack::pushFloat, "d2f");
  }

  public static final X2Y<Integer, Byte> I2B(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Integer::byteValue, OperandStack::popInt, OperandStack::pushByte, "i2b");
  }

  public static final X2Y<Integer, Character> I2C(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(a -> (char) (int) a, OperandStack::popInt, OperandStack::pushChar, "i2c");
  }

  public static final X2Y<Integer, Short> I2S(ProgramCounter pc, MethodInfo method) {
    return new X2Y<>(Integer::shortValue, OperandStack::popInt, OperandStack::pushShort, "i2s");
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    T1 num = popFunc.apply(stack);
    T2 res = opFunc.apply(num);
    pushFunc.accept(stack, res);
  }

  @Override
  public String toString() {
    return name;
  }
}
