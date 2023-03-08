package vjvm.classfiledefs;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.var;

import static vjvm.classfiledefs.Descriptors.DESC_array;
import static vjvm.classfiledefs.Descriptors.DESC_reference;

public class MethodDescriptors {
  public static int argc(String descriptor) {
    assert descriptor.startsWith("(");

    // calculate arguments size in slots
    int current = 1;
    int end = descriptor.indexOf(')');
    int argc = 0;
    while (current < end) {
      char c = descriptor.charAt(current);
      int size = Descriptors.size(c);
      argc += size;
      while (c == DESC_array) {
        c = descriptor.charAt(++current);
      }
      if (c == DESC_reference) {
        current = descriptor.indexOf(';', current) + 1;
      } else {
        current++;
      }
    }
    return argc;
  }

  public static char returnType(String descriptor) {
    assert descriptor.startsWith("(");

    var i = descriptor.indexOf(')') + 1;
    assert i < descriptor.length();
    return descriptor.charAt(i);
  }
}
