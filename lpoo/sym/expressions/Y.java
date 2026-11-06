package lpoo.sym.expressions;

public final class Y
  extends Expression
{
  @Override
  public float eval(float x,float y,float z)
  {
    return y;
  }

  @Override
  public Expression diff(String var)
  {
    if(var.equals("y"))
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
    return "y";
  }

} // Y