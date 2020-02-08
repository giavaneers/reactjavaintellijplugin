/*==============================================================================

name:       JSXFileHighlighter.java

purpose:    Syntax highlighter for ReactJava JSX. whose lexer builds upon the
            standard HtmlLexer by adding the '@' characater as allowable in
            tag names.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.editor;
                                       // imports --------------------------- //
import com.intellij.ide.highlighter.HtmlFileHighlighter;
import com.intellij.lexer.HtmlHighlightingLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.fileTypes.FileTypeRegistry;
import org.jetbrains.annotations.NotNull;
                                       // JSXFileHighlighter =================//
public class JSXFileHighlighter extends HtmlFileHighlighter
{
                                       // class constants --------------------//
                                       // (none)                              //
                                       // class variables ------------------- //
                                       // (none)                              //
                                       // public instance variables --------- //
                                       // (none)                              //
                                       // protected instance variables -------//
                                       // (none)                              //
                                       // private instance variables -------- //
                                       // (none)
/*------------------------------------------------------------------------------

@name       getHighlightingLexer - get highlighting lexer for reactjava jsx
                                                                              */
                                                                             /**
            Get reactjava jsx highlighting lexer for associated java source file


@return     reactjava jsx highlighting lexer for associated java source file

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
@Override
@NotNull
public Lexer getHighlightingLexer()
{
   return new JSXHighlightingLexer(
      FileTypeRegistry.getInstance().findFileTypeByName("CSS"));
}
}//====================================// JSXFileHighlighter -----------------//
