package lpoo.sym.expression;

public final class Z
  extends Expression
{
  @Override
  public float eval(float z)
  {
    return z;
  }

  @Override
  public Expression diff()
  {
    return Constant.ONE;
  }

  @Override
  public String toString()
  {
    return "z";
  }

} // Z