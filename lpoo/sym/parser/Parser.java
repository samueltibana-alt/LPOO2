package lpoo.sym.parser;
import lpoo.sym.expressions.*;

import lpoo.sym.*;

public class Parser
{
  public Parser()
  {
    scanner = new Scanner();
  }

  public void setInput(Input input)
    throws ParseException
  {
    scanner.setInput(input);
    advance();
  }

  public Command parse()
    throws ParseException
  {
    Command c = null;
    Vector3 args;

    if (token.type == TokenType.EVAL)
    {
      advance(); 
      match(TokenType.LPAREN);
      Expression e = exp();
      match(TokenType.COMMA);
      args = args();
      match(TokenType.RPAREN);
      c = Command.eval;
      c.setArguments(e,args);
    }
    else if (token.type == TokenType.DEFINE)
    {
      advance(); 
      String name = name();
      Expression e = null;
      if(token.type == TokenType.ASSIGN_OP)
      {
        match(TokenType.ASSIGN_OP);
        e = exp();
      }
      c = Command.define;
      c.setArguments(name,e);
    }
    else if (token.type == TokenType.DIFF)
    {
      advance();
      match(TokenType.LPAREN);
      Expression e = exp();
      match(TokenType.COMMA);
      String var = name();
      match(TokenType.RPAREN);
      c = Command.diff;
      c.setArguments(e,var);
    }
    else if (token.type == TokenType.ASSINGMENT)
    {
      advance();
      String name = name();
      match(TokenType.ASSIGN_OP);
      Expression e = exp();
      c = Command.assingment;
      c.setArguments(name,e);
    }
    else if(token.type == TokenType.CLEAR)
    {
      advance();
      c = Command.clear;
      try{
        String name = name();
        c.setArguments(name);
      }
      catch(ParseException excep)
      {
        //do nothing 
      }
    }
    else if(token.type == TokenType.LIST)
    {
      advance();
      c = Command.list;
    }
    else if (token.type == TokenType.EXIT)
    {
      advance();
      c = Command.exit;
    }
    
    if (token.type != TokenType.EOI)
      error(SYNTAX);
      
    return c;
  }

  private Vector3 args()
    throws ParseException
  {
    if (token.type != TokenType.FLOAT)
      error(NUMBER_EXPECTED);

    float x = ((Token.Float)token).value();
    advance();
    
    match(TokenType.COMMA);
    
    if (token.type != TokenType.FLOAT)
      error(NUMBER_EXPECTED);

    float y = ((Token.Float)token).value();
    advance();
    
    match(TokenType.COMMA);
    
    if (token.type != TokenType.FLOAT)
      error(NUMBER_EXPECTED);

    float z = ((Token.Float)token).value();
    advance();
    
    return new Vector3(x,y,z);
  }

  private Expression exp()
    throws ParseException
  {
    Expression e = mult();
    while(token.type == TokenType.ADD_OP || token.type == TokenType.SUB_OP)
    {
      if(token.type == TokenType.ADD_OP)
      {
        match(TokenType.ADD_OP);
        e = new Add(e, mult());
      }
      else
      {
        match(TokenType.SUB_OP);
        e = new Sub(e,mult());
      }
    }
    return e;
  }

  private Expression mult()
    throws ParseException
  {
    Expression m = power();
    while(token.type == TokenType.MUL_OP || token.type == TokenType.DIV_OP)
    {
      if(token.type == TokenType.MUL_OP)
      {
        match(TokenType.MUL_OP);
        m = new Mul(m, power());
      }
      else
      {
        match(TokenType.DIV_OP);
        m = new Div(m, power());
      }
    }
    return m;
  }

  private Expression power()
    throws ParseException
  {
    Expression p;
    if(token.type == TokenType.SUB_OP)
    {
      match(TokenType.SUB_OP);
      p = new Minus(primary());
    }
    else
    {
      if(token.type == TokenType.ADD_OP)
      {
        match(TokenType.ADD_OP);
      }
      p = primary();
    }
    
    if(token.type == TokenType.POW_OP)
    {
      match(TokenType.POW_OP);
      p = new Pow(p, realValue());
    }
    return p;
  }

  private Expression primary()
    throws ParseException
  {
    if(token.type == TokenType.FLOAT)
    {
      Expression e = new Constant(((Token.Float)token).value());
      advance();
      return e;
    }
    else if(token.type == TokenType.NAME)
    {
      String lex = token.lexeme;
      advance();
      if(lex.equals("x"))
      {
        return new X();
      }
      else if(lex.equals("y"))
      {
        return new Y();
      }
      else if(lex.equals("z"))
      {
        return new Z();
      }
      else if (Functions.get(lex)==null)
      {
        error(UNEXPECTED,token);
      }
      return Functions.get(lex);
    }
    else if(token.type == TokenType.LPAREN)
    {
      match(TokenType.LPAREN);
      Expression e = exp();
      match(TokenType.RPAREN);
      return e;
    }
    return null;
  }

  private float realValue()
    throws ParseException
  {
    if (token.type != TokenType.FLOAT)
      error(NUMBER_EXPECTED);
      
    float number = ((Token.Float)token).value();
    advance();
    return number;
  }

  private void error(int code, Object... args)
    throws ParseException
  {
    throw new ParseException(parseErrorMessages[code], args);
  }

  private void error(String s, Object... args)
    throws ParseException
  {
    throw new ParseException(s, args);
  }

  private String name()
    throws ParseException
  {
    if(token.type != TokenType.NAME)
    {
      error(IDENTIFIER_EXPECTED);
    }
    String name = token.toString();
    advance();
    return name;
  }

  private Token advance()
    throws ParseException
  {
    try
    {
      token = scanner.nextToken();
    }
    catch (InvalidInputException e)
    {
      error(e.getMessage());
    }
    return token;
  }

  private void match(TokenType t)
    throws ParseException
  {
    if (token.type != t)
    {
      if (token.type == TokenType.EOI)
        error(EXPECTED, t.toString());
      error(UNEXPECTED, token.lexeme);
    }
    advance();
  }

  private Scanner scanner;
  private Token token;

  static final int SYNTAX = 0;
  static final int IDENTIFIER_EXPECTED = 1;
  static final int NUMBER_EXPECTED = 2;
  static final int EXPECTED = 3;
  static final int UNEXPECTED = 4;

  private static final String[] parseErrorMessages =
  {
    "Syntax error",
    "Identifier expected",
    "Number expected",
    "'%1$s' expected",
    "Unexpected '%1$s'"
  };

}