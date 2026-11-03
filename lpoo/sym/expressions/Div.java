package lpoo.sym;

public final class Div
  extends BinaryExpression
{
  public Div(Expression e1, Expression e2)
  {
    super(e1, e2, '/');
  }

  @Override
  public float eval(float x)
  {
    return e1.eval(x) / e2.eval(x);
  }

  @Override
  public Expression diff()
  {
    Expression t1 = (new Mul(e1.diff(), e2)).simplify();
    Expression t2 = (new Mul(e1, e2.diff())).simplify();

    return (new Div(new Sub(t1, t2), new Mul(e2, e2))).simplify();
  }

  @Override
  public Expression simplify()
  {
    Expression s1 = e1.simplify();
    Expression s2 = e2.simplify();

    if (s1.isConstant())
    {
      float v1 = ((Constant)s1).value;

      if (s2.isConstant())
      {
        float v2 = ((Constant)s2).value;

        if (0 != v2)
          return new Constant(v1 / v2);
      }
    }
    else if (s2.isConstant() && 1 == ((Constant)s2).value)
      return s1;
    return new Div(s1, s2);    
  }

} // Div
