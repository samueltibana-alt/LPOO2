package lpoo.sym;
import lpoo.sym.expressions.*;
import lpoo.sym.parser.Vector3;

/**
 *
 * @author Samuel kiyoshi
 */
public abstract class Command
{
  public void setArguments(Object... args)
  {
    arguments = args;
  }
  
  public final boolean execute()
  {
    boolean returnValue = run();
    
    arguments = null;
    return returnValue;
  }
  
  protected abstract boolean run();
  
  protected Object[] arguments;

  public static final Command eval = new Command()
  {
    @Override
    protected boolean run()
    {
      Expression exp = (Expression) arguments[0];
      Vector3 vec = (Vector3) arguments[1];
      float x = vec.x;
      float y = vec.y;
      float z = vec.z;
      System.out.println(exp.eval(x,y,z));
      return true;
    }
  };

  public static final Command diff = new Command()
  {
    @Override
    protected boolean run()
    {
      Expression exp = (Expression) arguments[0];
      String var = (String) arguments[1];
      System.out.println(exp.diff(var).simplify().toString());
      return true;
    }
  };

  public static final Command exit = new Command()
  {
    @Override
    protected boolean run()
    {
      return false;
    }
  };
    public static final Command assingment = new Command()
  {
    @Override
    protected boolean run()
    {
      String name = (String) arguments[0];
      Expression exp = (Expression) arguments[1];
      Functions.add(name,exp);
      return true;
    }
  };
    public static final Command list = new Command()
  {
    @Override
    protected boolean run()
    {
      Functions.list();
      return true;
    }
  };
    public static final Command clear = new Command()
  {
    @Override
    protected boolean run()
    {
      if(arguments != null)
      {
      String name = (String) arguments[0];
      Functions.clear(name);
      }
      else
      {
      Functions.clear();
      }
      return true;
    }
  };
    public static final Command define = new Command()
  {
    @Override
    protected boolean run()
    {
      String name = (String) arguments[0];
      if(arguments[1] != null)
      {
      Expression exp = (Expression) arguments[1];
      Functions.add(name,exp);
      }
      else
      {
      Functions.add(name);
      }
      return true;
    }
  };

} // Command
