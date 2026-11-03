package lpoo.sym;

public final class Pow
  extends BinaryExpression
{
  public Pow(Expression base, Constant exp)
  {
    super(base, exp, '^');
  }

  public Pow(Expression base, float exp)
  {
    this(base, new Constant(exp));
  }

  @Override
  public float eval(float x)
  {
    return (float)Math.pow(e1.eval(x), ((Constant)e2).value);
  }

  @Override
  public Expression diff()
  {
    float exp = ((Constant)e2).value;

    if (0 == exp)
      return Constant.ZERO;

    Expression bd = e1.diff();

    if (1 == exp)
      return bd;
    return (new Mul(new Mul(e2, new Pow(e1, exp - 1)), bd)).simplify();
  }

  @Override
  public Expression simplify()
  {
    float exp = ((Constant)e2).value;

    if (0 == exp)
      return Constant.ONE;

    Expression bs = e1.simplify();

    if (1 == exp)
      return bs;
    if (bs.isConstant())
      return new Constant((float)Math.pow(((Constant)bs).value, exp));
    return new Pow(bs, (Constant)e2);
  }

} // Pow
