/*==============================================================================

name:       JSXHighlightingLexer.java

purpose:    Syntax highlighting lexer for ReactJava JSX. Enhances to
            HtmlHighlightingLexer by adding '@' character as permissable tag
            character (not allowed in XML).

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.editor;
                                       // imports --------------------------- //
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.HtmlHighlightingLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.xml.XmlTokenType;
import io.reactjava.plugins.intellij.lexer._ReactJavaJSXLexer;

public class JSXHighlightingLexer extends HtmlHighlightingLexer
{
                                       // class constants --------------------//
protected static final TokenSet TOKENS_TO_MERGE =
   TokenSet.create(
      XmlTokenType.XML_COMMENT_CHARACTERS,
      XmlTokenType.XML_WHITE_SPACE,
      XmlTokenType.XML_REAL_WHITE_SPACE,
      XmlTokenType.XML_ATTRIBUTE_VALUE_TOKEN,
      XmlTokenType.XML_DATA_CHARACTERS,
      XmlTokenType.XML_TAG_CHARACTERS);
                                       // class variables ------------------- //
                                       // (none)                              //
                                       // public instance variables --------- //
                                       // (none)                              //
                                       // protected instance variables -------//
                                       // (none)                              //
                                       // private instance variables -------- //
                                       // (none)                              //
/*------------------------------------------------------------------------------

@name       JSXHighlightingLexer - constructor for specified file type
                                                                              */
                                                                             /**
            Constructor for specified file type

 @history   Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

 @notes                                                                       */

//------------------------------------------------------------------------------
public JSXHighlightingLexer(
   FileType styleFileType)
{
   super(
      new MergingLexerAdapter(
         new FlexAdapter(new _ReactJavaJSXLexer()), TOKENS_TO_MERGE),
      true,
      styleFileType);
}
}//====================================// JSXFileHighlighter -----------------//
