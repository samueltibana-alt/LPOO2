package lpoo.sym.expressions;

/**
 * @author Samuel kiyoshi
 */
public class Function extends Expression {
  public static final Function sin(Expression arg) {
    return new Function(NativeFunction.SIN, arg);
  }

  public static final Function cos(Expression arg) {
    return new Function(NativeFunction.COS, arg);
  }

  public static final Function ln(Expression arg) {
    return new Function(NativeFunction.LN, arg);
  }

  @Override
  public float eval(float x, float y, float z) {
    return function.eval(arg.eval(x, y, z), 0, 0);
  }

  @Override
  public Expression diff(String var) {
    // Aplicação da Regra da Cadeia: [f(g(x))]' = f'(g(x)) * g'(x)
    Expression outerDiff;
    
    if (function instanceof NativeFunction) {
      outerDiff = ((NativeFunction) function).diffOuter(arg);
    } else {
      outerDiff = function.diff(var);
    }

    return (new Mul(outerDiff, arg.diff(var))).simplify();
  }

  @Override
  public String toString() {
    return String.format("%s(%s)", function, arg);
  }

  private final Expression function;
  private final Expression arg;

  private Function(Expression function, Expression arg) {
    this.function = function;
    this.arg = arg;
  }

} // Function


abstract class NativeFunction extends Expression {
  static final NativeFunction SIN = new Sin();
  static final NativeFunction COS = new Cos();
  static final NativeFunction LN = new Ln();

  public abstract Expression diffOuter(Expression arg);

} // NativeFunction


final class Sin extends NativeFunction {
  @Override
  public float eval(float x, float y, float z) {
    return (float) Math.sin(x);
  }

  @Override
  public Expression diff(String var) {
    return COS;
  }

  @Override
  public Expression diffOuter(Expression arg) {
    // Derivada de sin(u) é cos(u)
    return Function.cos(arg);
  }

  @Override
  public String toString() {
    return "sin";
  }

} // Sin


final class Cos extends NativeFunction {
  @Override
  public float eval(float x, float y, float z) {
    return (float) Math.cos(x);
  }

  @Override
  public Expression diff(String var) {
    return new Minus(SIN);
  }

  @Override
  public Expression diffOuter(Expression arg) {
    // Derivada de cos(u) é -sin(u)
    return new Minus(Function.sin(arg));
  }

  @Override
  public String toString() {
    return "cos";
  }

} // Cos


final class Ln extends NativeFunction {
  @Override
  public float eval(float x, float y, float z) {
    return (float) Math.log(x); 
  }

  @Override
  public Expression diff(String var) {
    return new Div(Constant.ONE, Var.X);
  }

  @Override
  public Expression diffOuter(Expression arg) {
    return new Div(Constant.ONE, arg);
  }

  @Override
  public String toString() {
    return "ln";
  }

} // Ln