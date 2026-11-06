package lpoo.sym.expressions;

public final class X
  extends Expression
{
  @Override
  public float eval(float x,float y,float z)
  {
    return x;
  }

  @Override
  public Expression diff(String var)
  {
    if(var.equals("x"))
    {
    return Constant.ONE;
    }
    else 
    {
    return Constant.ZERO;
    }
  }

  @Override
  public String toString()
  {
    return "x";
  }

} // X
