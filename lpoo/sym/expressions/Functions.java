package lpoo.sym.expressions;

import java.util.TreeMap;
/**
 *
 * @author Samuel kiyoshi
 */
public class Functions {

  public interface ExpressionSupplier {
    Expression get(Expression arg);
  }

  // Dois mapas separados
  private static final TreeMap<String, ExpressionSupplier> defaultMap = new TreeMap<>();
  private static final TreeMap<String, ExpressionSupplier> userMap = new TreeMap<>();

  static {
    defaultMap.put("sin", arg -> Function.sin(arg));
    defaultMap.put("cos", arg -> Function.cos(arg));
    defaultMap.put("ln", arg -> Function.ln(arg));
    defaultMap.put("e", arg -> Constant.E);
    defaultMap.put("pi", arg -> Constant.PI);
  }

  // Adiciona apenas no mapa do usuário
  public static void add(String name, Expression exp) {
    if (exp == null) {
      userMap.put(name, null);
    } else {
      userMap.put(name, arg -> exp);
    }
  }

  public static void add(String name) {
    userMap.put(name, null);
  }

  public static void clear() {
    userMap.clear();
  }

  public static void clear(String name) {
    userMap.remove(name);
  }

  private static ExpressionSupplier findSupplier(String name) {
    ExpressionSupplier supplier = userMap.get(name);
    if (supplier == null) {
      supplier = defaultMap.get(name);
    }
    return supplier;
  }

  public static Expression get(String name) {
      ExpressionSupplier supplier = findSupplier(name);
      
      if (supplier != null) {
        Expression result= supplier.get(null);
        return result;
      } else {
        return null;
      }
    }
  public static Expression get(String name, Expression arg) {
    ExpressionSupplier supplier = findSupplier(name);
    
    if (supplier != null) {
      Expression result = supplier.get(arg);
      return result;
    } else {
      return null;
    }
  }

  public static void list() {
    System.out.println("--- Funções Nativas ---");
    defaultMap.forEach((name, supplier) -> {
      String value = (supplier != null) ? supplier.get(Var.X).toString() : "<indefinida>";
      System.out.println(name + " : " + value);
    });

    System.out.println("\n--- Variáveis e Funções do Usuário ---");
    if (userMap.isEmpty()) {
      System.out.println("<nenhuma>");
    } else {
      userMap.forEach((name, supplier) -> {
        String value = (supplier != null) ? supplier.get(Var.X).toString() : "<indefinida>";
        System.out.println(name + " : " + value);
      });
    }
    System.out.println("----------------------------------");
  }
}