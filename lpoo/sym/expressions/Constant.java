package lpoo.sym.expressions;

public final class Constant
  extends Expression
{
  public final static Constant ZERO = new Constant(0);
  public final static Constant ONE = new Constant(1);
  public final static Constant PI = new Constant((float)Math.PI);
 
  public final float value;

  public Constant(float value)
  {
    this.value = value;
  }

  @Override
  public float eval(float x,float y,float z)
  {
    return value;
  }

  @Override
  public Expression diff(String var)
  {
    return ZERO;
  }

  @Override
  public String toString()
  {
    return value == PI.value ? "pi" : Float.toString(value);
  }

} // Constant
