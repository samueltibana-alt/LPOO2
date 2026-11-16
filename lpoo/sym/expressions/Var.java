package lpoo.sym.expressions;
/**
 *
 * @author Samuel kiyoshi
 */
public final class Var extends Expression {

  // As únicas instâncias permitidas no programa inteiro (Singletons)
  public static final Var X = new Var("x");
  public static final Var Y = new Var("y");
  public static final Var Z = new Var("z");

  private final String name;

  private Var(String name) {
    this.name = name;
  }

  @Override
  public float eval(float x, float y, float z) {
    return switch (name) {
      case "x" -> x;
      case "y" -> y;
      case "z" -> z;
      default -> 0;
    };
  }

  @Override
  public Expression diff(String var) {
    if(this.name.equals(var))
    {
      return Constant.ONE;
    }
    else
    {
      return Constant.ZERO;
    }
  }

  @Override
  public String toString() {
    return name;
  }
}