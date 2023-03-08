package vjvm.classloader.searchpath;

import vjvm.utils.UnimplementedError;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents a path to search class files in.
 * A search path may hold resources, such as jar files, so it must implement the Closeable interface.
 * If a subclass doesn't hold any resources, then just do nothing.
 */
public abstract class ClassSearchPath implements Closeable {
  /**
   * Construct search path objects with a given path.
   */
  public static ClassSearchPath[] constructSearchPath(String path) {
    String sep = System.getProperty("path.separator");
    ArrayList<ClassSearchPath> classSearchPaths = new ArrayList<>();
    for (String seg : path.split(sep)) {
      Path segPath = Paths.get(seg);
      if (Files.exists(segPath)) {
        if (Files.isDirectory(segPath)) {
          classSearchPaths.add(new DirSearchPath(seg));
        } else if (Files.isRegularFile(segPath) && seg.endsWith(".jar")) {
          classSearchPaths.add(new JarSearchPath(seg));
        }
      }
    }

    return classSearchPaths.toArray(new ClassSearchPath[0]);
  }

  /**
   * Find a class with specified name.
   *
   * @param name name of the class to find.
   * @return Returns a stream containing the binary data if such class is found, or null if not.
   */
  public abstract InputStream findClass(String name);
}
