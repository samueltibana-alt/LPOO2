package lpoo.sym;

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

  @Override
  public float eval(float x)
  {
    return function.eval(arg.eval(x));
  }

  @Override
  public Expression diff()
  {
    Expression f = new Function(function.diff(), arg); 
    return (new Mul(f, arg.diff())).simplify();
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

} // NativeFunction

final class Sin
  extends NativeFunction
{
  @Override
  public float eval(float x)
  {
    return (float)Math.sin(x);
  }

  @Override
  public Expression diff()
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
  public float eval(float x)
  {
    return (float)Math.cos(x);
  }

  @Override
  public Expression diff()
  {
    return new Minus(SIN);
  }

  @Override
  public String toString()
  {
    return "cos";
  }

} // Cos
