package lpoo.sym.expression;

public final class Y
  extends Expression
{
  @Override
  public float eval(float y)
  {
    return y;
  }

  @Override
  public Expression diff()
  {
    return Constant.ONE;
  }

  @Override
  public String toString()
  {
    return "y";
  }

} // Y