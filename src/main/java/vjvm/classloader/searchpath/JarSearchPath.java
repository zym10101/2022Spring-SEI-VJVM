package vjvm.classloader.searchpath;

import lombok.SneakyThrows;
import lombok.var;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;

/**
 * @author zym
 * @date 2022/05/03 22:06
 **/
public class JarSearchPath extends ClassSearchPath {

  //might be null if the file does not exist
  private JarFile jarFile;

  @SneakyThrows
  public JarSearchPath(String name) {
    try {
      jarFile = new JarFile(name);
    } catch (FileNotFoundException e) {
      jarFile = null;
    }
  }

  @Override
  @SneakyThrows
  public InputStream findClass(String name) {
    if (jarFile == null) {
      return null;
    }
    var entry = jarFile.getEntry(name + ".class");
    return entry == null ? null : jarFile.getInputStream(entry);
  }

  @Override
  public void close() throws IOException {

  }
}
