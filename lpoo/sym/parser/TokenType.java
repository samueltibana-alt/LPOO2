package lpoo.sym.parser;

/**
 *
 * @author insert your name here
 */
enum TokenType
{
  EOI,
  ADD_OP,
  SUB_OP,
  MUL_OP,
  DIV_OP,
  POW_OP,
  ASSIGN_OP,
  LPAREN,
  RPAREN,
  FLOAT,
  COMMA,
  NAME,
  EVAL,
  DIFF,
  EXIT,
  ERROR;

  @Override
  public String toString()
  {
    return strings[ordinal()];
  }

  private static String[] strings =
  {
    "<eoi>",
    "+",
    "-",
    "*",
    "/",
    "^",
    "=",
    "(",
    ")",
    "float",
    ",",
    "name",
    "eval",
    "diff",
    "exit",
    "<error>"
  };

} // TokenType
