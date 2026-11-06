package lpoo.sym.expressions;

public abstract class Expression
{
  public abstract float eval(float x,float y,float z);
  public abstract Expression diff(String var);

  public Expression simplify()
  {
    return this;
  }

  public final boolean isConstant()
  {
    return this instanceof Constant;
  }

  public final boolean isBinary()
  {
    return this instanceof BinaryExpression;
  }

} // Expression
