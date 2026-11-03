package lpoo.sym;

public final class Sub
  extends BinaryExpression
{
  public Sub(Expression e1, Expression e2)
  {
    super(e1, e2, '-');
  }

  @Override
  public float eval(float x)
  {
    return e1.eval(x) - e2.eval(x);
  }

  @Override
  public Expression diff()
  {
    return (new Sub(e1.diff(), e2.diff())).simplify();
  }

  @Override
  public Expression simplify()
  {
    Expression s1 = e1.simplify();
    Expression s2 = e2.simplify();

    if (s1.isConstant())
    {
      float v1 = ((Constant)e1).value;

      if (s2.isConstant())
        return new Constant(v1 - ((Constant)s2).value);
      if (0 == v1)
        return new Minus(s2);
    }
    else if (s2.isConstant() && 0 == ((Constant)e2).value)
      return e1;
    return new Sub(s1, s2);
  }

} // Sub
