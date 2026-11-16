package lpoo.sym.expressions;
/**
 *
 * @author Samuel kiyoshi
 */
public abstract class UnaryExpression
  extends Expression
{
  @Override
  public String toString()
  {
    return op + e.toString();
  }

  protected Expression e;
  protected char op;

  protected UnaryExpression(Expression e, char op)
  {
    this.e = e;
    this.op = op;
  }

} // UnaryExpression
