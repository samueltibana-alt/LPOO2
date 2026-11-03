package lpoo.sym;

public final class Minus
  extends UnaryExpression
{
  public Minus(Expression e)
  {
    super(e, '-');
  }

  @Override
  public float eval(float x)
  {
    return -e.eval(x);
  }

  @Override
  public Expression diff()
  {
    return (new Minus(e.diff())).simplify();
  }

  @Override
  public Expression simplify()
  {
    Expression s = e.simplify();

    if (s.isConstant())
    {
      float v = ((Constant)s).value;
      return 0 == v ? Constant.ZERO : new Constant(-v);
    }
    return s instanceof Minus ? s : new Minus(s);
  }

} // Minus
