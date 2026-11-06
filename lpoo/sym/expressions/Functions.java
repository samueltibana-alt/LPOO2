package lpoo.sym.expressions;

import java.util.TreeMap;

public class Functions {
  public static void add(String name, Expression  exp)
  {
    map.put(name, exp);
  }
  public static void add(String name)
  {
    map.put(name, null);
  }
  public static void clear()
  {
    map.clear();
  }
  public static void clear(String name)
  {
    map.remove(name);
  }
  public static Expression get(String name)
  {
    return map.get(name);
  }
  public static void list()
  {
  if (map.isEmpty()) {
        System.out.println("Nenhuma função ou variável definida.");
    } else {
        System.out.println("--- Variáveis e Funções Salvas ---");
        map.forEach((name, exp) -> {
            String value = (exp != null) ? exp.toString() : "<indefinida>";
            System.out.println(name + " : " + value);
        });
        System.out.println("----------------------------------");
    }
  }
  static
  {
    map = new TreeMap<>();
    
  }

  static TreeMap<String, Expression> map;
}
