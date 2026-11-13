package lpoo.sym.expressions;

public final class Pow
  extends BinaryExpression
{
  public Pow(Expression base, Expression exp)
  {
    super(base, exp, '^');
  }

  @Override
  public float eval(float x, float y, float z)
  {
    return (float)Math.pow(e1.eval(x, y, z), e2.eval(x, y, z));
  }

  @Override
  public Expression diff(String var)
  {
    // Caso otimizado: expoente é constante (u^c -> c * u^(c-1) * u')
    if (e2.isConstant()) {
      float exp = ((Constant)e2).value;

      if (exp == 0)
        return Constant.ZERO;

      Expression bd = e1.diff(var);

      if (exp == 1)
        return bd;

      return (new Mul(new Mul(e2, new Pow(e1, new Constant(exp - 1))), bd)).simplify();
    }

    // Caso geral: u^v -> v * u^(v-1) * u' + u^v * ln(u) * v'
    Expression term1 = new Mul(
      new Mul(e2, new Pow(e1, new Sub(e2, Constant.ONE))),
      e1.diff(var)
    );

    Expression term2 = new Mul(
      new Mul(this, Function.ln(e1)),
      e2.diff(var)
    );

    return (new Add(term1, term2)).simplify();
  }

  @Override
  public Expression simplify()
  {
    Expression bs = e1.simplify();
    Expression es = e2.simplify();

    if (es.isConstant()) {
      float exp = ((Constant)es).value;

      if (exp == 0)
        return Constant.ONE;

      if (exp == 1)
        return bs;

      if (bs.isConstant())
        return new Constant((float)Math.pow(((Constant)bs).value, exp));
    }

    return new Pow(bs, es);
  }

} // Pow