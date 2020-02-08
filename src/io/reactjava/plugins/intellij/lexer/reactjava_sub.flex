/**
    ReactJava sub lexer definition.
    @status Working, PI
    @todo Documentation, features as needed
 */
package com.giavaneers.reactjava.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import static com.giavaneers.reactjava.psi.ReactJavaTypes.*;

%%

%class _ReactJavaSubLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%caseless
%eof{  return;
%eof}

%{
    private Stack<Integer> stack = new Stack<Integer>();

    public void yypushState(int newState) {
      stack.push(yystate());
      yybegin(newState);
    }

    public void yypopState() {
      yybegin(stack.pop());
    }
%}

OPENING_HTML = "/*--"
CLOSING_HTML = "--*/"
LEFT_BRACE = "{"
RIGHT_BRACE = "}"

%state HTML_TEXT

%state JAVA_TEXT
%state INNER_JAVA

%%

<YYINITIAL> {
    {OPENING_HTML} { yybegin(HTML_TEXT); return OPENING; }
}

<HTML_TEXT> {
    {CLOSING_HTML} { yybegin(YYINITIAL); return CLOSING; }
    //{LEFT_BRACE} { yybegin(JAVA_TEXT); return LEFT_BRACE; }
    {LEFT_BRACE} { yypushState(JAVA_TEXT); return LEFT_BRACE; }
    //[^{}]+ / {CLOSING} { return HTML; }
    //[^{]+ / {LEFT_BRACE} { return HTML; }
    [^{}] { return HTML; }
    {RIGHT_BRACE} { return RIGHT_BRACE; }
}

<JAVA_TEXT> {
    {CLOSING_HTML} { yybegin(YYINITIAL); return CLOSING; }
    {LEFT_BRACE} { yypushState(INNER_JAVA); return JAVA; }
    {RIGHT_BRACE} { yypopState(); return RIGHT_BRACE; }
    [^{}] { return JAVA; }
}

<INNER_JAVA> {
    {LEFT_BRACE} { yypushState(INNER_JAVA); return JAVA; }
    {RIGHT_BRACE} { yypopState(); return JAVA; }
    [^{}]  { return JAVA; }
}

