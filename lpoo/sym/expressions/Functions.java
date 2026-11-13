package lpoo.sym.expressions;

import java.util.TreeMap;

public class Functions {

  public interface ExpressionSupplier {
    Expression get(Expression arg);
  }

  private static TreeMap<String, ExpressionSupplier> map = new TreeMap<>();

  static {
    registerDefaults();
  }

  private static void registerDefaults() {
    map.put("sin", arg -> Function.sin(arg));
    map.put("cos", arg -> Function.cos(arg));
    map.put("ln", arg -> Function.ln(arg));
    Functions.add("e", Constant.E);
    Functions.add("pi", Constant.PI);

  }

  public static void add(String name, Expression exp) {
    if (exp == null) {
      map.put(name, null);
    } else {
      map.put(name, arg -> exp);
    }
  }

  public static void add(String name) {
    map.put(name, null);
  }

  public static void clear() {
    map.clear();
    registerDefaults();
  }

  public static void clear(String name) {
    map.remove(name);
  }

  // Para funções cridadas pelo usuario
  public static Expression get(String name) {
    ExpressionSupplier supplier = map.get(name);
    Expression result;
    if (supplier != null) {
      result = supplier.get(null);
    } else {
      result = null;
    }

    return result;
  }

  // Para funções fundamentais criadas estaticamente
  public static Expression get(String name, Expression arg) {
    ExpressionSupplier supplier = map.get(name);
    Expression result;
    if (supplier != null) {
      result = supplier.get(arg);
    } else {
      result = null;
    }

    return result;
  }

  public static void list() {
    System.out.println("--- Variáveis e Funções Salvas ---");
    map.forEach((name, supplier) -> {
      String value = (supplier != null) ? supplier.get(Var.X).toString() : "<indefinida>";
      System.out.println(name + " : " + value);
    });
    System.out.println("----------------------------------");
  }
}