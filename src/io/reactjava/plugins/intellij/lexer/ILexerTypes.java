/*==============================================================================

name:       ILexerTypes.java

purpose:    ReactJava psi lexable token types.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.lexer;
                                       // imports --------------------------- //
import io.reactjava.plugins.intellij.psi.ReactJavaElementType;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
                                       // ILexerTypes ----------------------- //
public interface ILexerTypes
{
                                       // debug names ----------------------- //
String kDEBUG_NAME_CSS_TEXT    = "REACTJAVA_CSS_TEXT";
String kDEBUG_NAME_CSS_BLOCK   = "REACTJAVA_CSS_BLOCK";
String kDEBUG_NAME_HTML_TEXT   = "REACTJAVA_HTML_TEXT";
String kDEBUG_NAME_HTML_BLOCK  = "REACTJAVA_HTML_BLOCK";
String kDEBUG_NAME_JAVA_TEXT   = "REACTJAVA_JAVA_TEXT";

String kDEBUG_NAME_COMMENT     = "COMMENT";
String kDEBUG_NAME_FILE        = "FILE";
String kDEBUG_NAME_OPENING     = "OPENING";
String kDEBUG_NAME_CLOSING     = "CLOSING";
String kDEBUG_NAME_LEFT_BRACE  = "LEFT_BRACE";
String kDEBUG_NAME_RIGHT_BRACE = "RIGHT_BRACE";

                                       // any CSS inside the ReactJava file   //
IElementType kELEMENT_TYPE_CSS =
   new ReactJavaElementType(kDEBUG_NAME_CSS_TEXT);

                                       // block of CSS to be lexed further by //
                                       // SubLexer2                           //
IElementType kELEMENT_TYPE_CSS_BLOCK =
   new ReactJavaElementType(kDEBUG_NAME_CSS_BLOCK);

                                       // any HTML inside the ReactJava file  //
IElementType kELEMENT_TYPE_HTML =
   new ReactJavaElementType(kDEBUG_NAME_HTML_TEXT);

                                       // block of HTML to be lexed further   //
                                       // by SubLexer1                        //
IElementType kELEMENT_TYPE_HTML_BLOCK =
   new ReactJavaElementType(kDEBUG_NAME_HTML_BLOCK);

                                       // any Java inside the ReactJava file  //
IElementType kELEMENT_TYPE_JAVA =
   new ReactJavaElementType(kDEBUG_NAME_JAVA_TEXT);

                                       // comment tag                         //
IElementType kELEMENT_TYPE_COMMENT =
   new ReactJavaElementType(kDEBUG_NAME_COMMENT);

                                       // start/end tags for HTML and CSS     //
                                       // code blocks                         //
IElementType kELEMENT_TYPE_OPENING =
   new ReactJavaElementType(kDEBUG_NAME_OPENING);

IElementType kELEMENT_TYPE_CLOSING =
   new ReactJavaElementType(kDEBUG_NAME_CLOSING);

                                       // left and right curly braces inside  //
                                       // HTML and CSS representing start/end //
                                       // of a Java expression                //
IElementType kELEMENT_TYPE_LEFT_BRACE =
   new ReactJavaElementType(kDEBUG_NAME_LEFT_BRACE);

IElementType kELEMENT_TYPE_RIGHT_BRACE =
   new ReactJavaElementType(kDEBUG_NAME_RIGHT_BRACE);

IFileElementType kELEMENT_TYPE_FILE =
   new IFileElementType(kDEBUG_NAME_FILE, JavaLanguage.INSTANCE);

                                       // tokens -----------------------------//

IElementType kELEMENT_TYPE_BAD_CHARACTER = TokenType.BAD_CHARACTER;
IElementType kELEMENT_TYPE_WHITE_SPACE   = TokenType.WHITE_SPACE;

TokenSet     kCOMMENT_TOKENS    = TokenSet.create(kELEMENT_TYPE_COMMENT);
TokenSet     kCSS_TOKENS        = TokenSet.create(kELEMENT_TYPE_CSS_BLOCK);
TokenSet     kHTML_TOKENS       = TokenSet.create(kELEMENT_TYPE_HTML_BLOCK);
TokenSet     kSTRING_LITERALS   = TokenSet.create();
TokenSet     kWHITESPACE_TOKENS = TokenSet.create(kELEMENT_TYPE_WHITE_SPACE);

}//====================================// end ILexerTypes --------------------//
