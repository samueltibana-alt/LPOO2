package lpoo.sym;

public final class X
  extends Expression
{
  @Override
  public float eval(float x)
  {
    return x;
  }

  @Override
  public Expression diff()
  {
    return Constant.ONE;
  }

  @Override
  public String toString()
  {
    return "x";
  }

} // X
