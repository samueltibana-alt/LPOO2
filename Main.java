import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import lpoo.sym.*;
import lpoo.util.*;

public class Main {
  public static void main(String[] args) {
    if (args.length > 0) {
      execute(args[0]);
      return;
    }

    console();
  }

  private static void execute(String path) {
    try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
      String s;
      int line = 0;

      while ((s = bf.readLine()) != null) {
        line++;
        System.out.printf("%05d: %s%n", line, s);

        if (!s.trim().isEmpty() && !Calc.execute(s)) {
          break;
        }
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo '" + path + "': " + e.getMessage());
    }
  }

  private static void console() {
     for (;;)
{
    String line = Console.readString("> ");
    if (!line.equals("") && !Calc.execute(line))
    {
    return;
    }
} 
  }
}