import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Represent a Readable throws an IOException.
 */
public class MockReadable implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}