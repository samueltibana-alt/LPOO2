package lpoo.sym.parser;

/**
 *
 * @author Samuel Kiyoshi
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
  DEFINE,
  DIFF,
  LIST,
  ASSINGMENT,
  CLEAR,
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
    "define",
    "diff",
    "list",
    "assingment",
    "clear",
    "exit",
    "<error>"
  };

} // TokenType
