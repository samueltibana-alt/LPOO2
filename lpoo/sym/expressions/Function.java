package lpoo.sym.expressions;

public class Function
  extends Expression
{
  public static final Function sin(Expression arg)
  {
    return new Function(NativeFunction.SIN, arg);
  }

  public static final Function cos(Expression arg)
  {
    return new Function(NativeFunction.COS, arg);
  }

  public static final Function ln(Expression arg)
  {
    return new Function(NativeFunction.LN, arg);
  }

  @Override
  public float eval(float x, float y, float z)
  {
    return function.eval(arg.eval(x, y, z), 0, 0);
  }

  @Override
  public Expression diff(String var)
  {
    Expression df = function.diff(var);
    Expression f = (df instanceof NativeFunction) ? new Function(df, arg) : df;
    return (new Mul(f, arg.diff(var))).simplify();
  }

  @Override
  public String toString()
  {
    return String.format("%s(%s)", function, arg);
  }

  private final Expression function;
  private final Expression arg;

  private Function(Expression function, Expression arg)
  {
    this.function = function;
    this.arg = arg;
  }

} // Function

abstract class NativeFunction
  extends Expression 
{
  static final NativeFunction SIN = new Sin();
  static final NativeFunction COS = new Cos();
  static final NativeFunction LN = new Ln();

} // NativeFunction

final class Sin
  extends NativeFunction
{
  @Override
  public float eval(float x, float y, float z)
  {
    return (float)Math.sin(x);
  }

  @Override
  public Expression diff(String var)
  {
    return COS;
  }

  @Override
  public String toString()
  {
    return "sin";
  }

} // Sin

final class Cos
  extends NativeFunction
{
  @Override
  public float eval(float x, float y, float z)
  {
    return (float)Math.cos(x);
  }

  @Override
  public Expression diff(String var)
  {
    return new Minus(SIN);
  }

  @Override
  public String toString()
  {
    return "cos";
  }

} // Cos
// E

final class Ln
  extends NativeFunction
{
  @Override
  public float eval(float x, float y, float z)
  {
    return (float)Math.log(x);
  }

  @Override
  public Expression diff(String var)
  {
    return new Div(Constant.ONE, Var.X);
  }

  @Override
  public String toString()
  {
    return "ln";
  }

} // Ln