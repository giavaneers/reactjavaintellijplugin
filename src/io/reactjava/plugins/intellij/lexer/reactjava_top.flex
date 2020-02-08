/**
    ReactJava top lexer definition.
    @status Working, PI
    @todo Documentation, features as needed
 */
package com.giavaneers.reactjava.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.giavaneers.reactjava.psi.ReactJavaTypes.*;

%%

%class _ReactJavaTopLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%caseless
%eof{  return;
%eof}

WHITE_SPACE_CHAR = [\ \n\r\t\f]
RENDERCSS = "public void renderCSS"

OPENING = "/*--"
CLOSING = "--*/"

%state RENDER_CSS

%%
<YYINITIAL> {
    (\/\*--([^*]|[\r\n]|(\*+([^*\/]|[\r\n])))*--\*\/)|\/\*-- { return HTML_BLOCK; }
    //\/\*--([^*]|[\r\n]|(\*+([^*\/]|[\r\n])))*--\*\/ { return HTML_BLOCK; }
    //{CLOSING} { return CLOSING; }
    {RENDERCSS}(\(|{WHITE_SPACE_CHAR}+\()(\)|{WHITE_SPACE_CHAR}+\))(\{|{WHITE_SPACE_CHAR}+\{)({WHITE_SPACE_CHAR}+) { yybegin(RENDER_CSS); return JAVA; }

}

<RENDER_CSS> {
     (\/\*--([^*]|[\r\n]|(\*+([^*\/]|[\r\n])))*--\*\/)|\/\*-- { return CSS_BLOCK; }
     //\/\*--([^*]|[\r\n]|(\*+([^*\/]|[\r\n])))*--\*\/ { return CSS_BLOCK; }
     [\s\S] { return JAVA; }
     \} {yybegin(YYINITIAL); return JAVA; }
}

{WHITE_SPACE_CHAR}+  { return JAVA; }
.+  {return JAVA; }