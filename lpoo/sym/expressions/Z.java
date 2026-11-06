package lpoo.sym.expressions;

public final class Z
  extends Expression
{
  @Override
  public float eval(float x,float y,float z)
  {
    return z;
  }

  @Override
  public Expression diff(String var)
  {
    if(var.equals("z"))
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
    return "z";
  }

} // Z