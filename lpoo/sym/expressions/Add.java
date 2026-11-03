package lpoo.sym;

public final class Add
  extends BinaryExpression
{
  public Add(Expression e1, Expression e2)
  {
    super(e1, e2, '+');
  }

  @Override
  public float eval(float x)
  {
    return e1.eval(x) + e2.eval(x);
  }

  @Override
  public Expression diff()
  {
    return (new Add(e1.diff(), e2.diff())).simplify();
  }

  @Override
  public Expression simplify()
  {
    Expression s1 = e1.simplify();
    Expression s2 = e2.simplify();

    if (s1.isConstant())
    {
      float v1 = ((Constant)s1).value;

      if (0 == v1)
        return s2;
      if (s2.isConstant())
        return new Constant(v1 + ((Constant)s2).value);
    }
    else if (s2.isConstant() && 0 == ((Constant)s2).value)
      return s1;
    return new Add(s1, s2);
  }

} // Add
