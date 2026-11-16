package lpoo.sym.parser;

/**
 *
 * @author Samuel kiyoshi
 */
public final class Vector3
{
  public static final Vector3 NULL = new Vector3(0);


  public final float x;
  public final float y;
  public final float z;

  public Vector3(float x, float y, float z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector3(float x)
  {
    this(x, x, x);
  }

  @Override
  public String toString()
  {
    return String.format("(%g,%g,%g)", x, y, z);
  }

} // Vector3
