package lpoo.sym;

public abstract class Expression
{
  public abstract float eval(float x);
  public abstract Expression diff();

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
