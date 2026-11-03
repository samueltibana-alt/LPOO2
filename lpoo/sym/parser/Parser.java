package lpoo.sym.parser;

import lpoo.sym.*;

/**
 *
 * @author Samuel Kiyoshi
 */
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

    if (token.type == TokenType.EVAL)// EVAL
    {
      advance(); 
      match(TokenType.LPAREN);
      expression();
      match(TokenType.COMMA);
      args();
      match(TokenType.RPAREN);
      c = Command.eval;
    }
     else if (token.type == TokenType.DEFINE)// DEFINE
    {
      advance(); 
      name();
      if(scanner.nextToken != '\0')
      {
      match(TokenType.ASSIGN_OP);
      expression();
      }
      c = Command.define;
    }
    else if (token.type == TokenType.Diff)// DIFF
    {
      advance();
      match(TokenType.LPAREN);
      expression();
      match(TokenType.COMMA);
      name();
      match(TokenType.RPAREN);
      c = Command.diff;
    }
    else if (token.type == TokenType.ASSINGMENT)// ASSINGMENT
    {
      advance();
      match(TokenType.LPAREN);
      name();
      match(TokenType.COMMA);
      expression();
      match(TokenType.RPAREN);
      c = Command.assingment;
    }
    else if( token.type == TokenType.CLEAR)
    {
      advance();
      c = Command.clear;
    }
    else if( token.type == TokenType.LIST)
    {
      advance();
      c = Command.list;
    }
    else if (token.type == TokenType.EXIT)
    {
      advance(); // EXIT
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

  private void expression()
  {
    mul();
    while(token.type == TokenType.ADD_OP || token.type == TokenType.SUB_OP)
    {
      advance();
      mul();
    }
  }

  private void mul()
  {
    pow();
    while(token.type == TokenType.MUL_OP || token.type == TokenType.DIV_OP)
    {
      advance();
      pow();
    }
  }
  private void pow()
  {
    if(token.type == TokenType.ADD_OP||token.type == TokenType.SUB_OP)
    {
      advance();
    }
    primary();
    while(token.type == TokenType.POW_OP)
    {
      pow();
    }
  }
  private void primary()
  {
    advance()
    if(token.type == TokenType.NAME)
    {
        
    }
    else if(token.type == TokenType.FLOAT)
    {

    }
    else
    {
      expression();
    }
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

} // Parser
