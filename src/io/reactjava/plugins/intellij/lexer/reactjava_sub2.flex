/**
    ReactJava sub lexer 2 definition.
    @status Working, PI
    @todo Documentation, features as needed
 */
package com.giavaneers.reactjava.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import static com.giavaneers.reactjava.psi.ReactJavaTypes.*;

%%

%class _ReactJavaSubLexer2
%implements FlexLexer
%unicode
%function advance
%type IElementType
%caseless
%eof{  return;
%eof}

OPENING = "/*--"
CLOSING = "--*/"

%state CSS_TEXT

%%

<YYINITIAL> {
    {OPENING} { yybegin(CSS_TEXT); return OPENING; }
}

<CSS_TEXT> {
    {CLOSING} {yybegin(YYINITIAL); return CLOSING; }
    [\s\S] { return CSS; }
}