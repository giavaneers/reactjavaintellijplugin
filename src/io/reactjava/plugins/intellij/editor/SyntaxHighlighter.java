/*==============================================================================

name:       SyntaxHighlighter.java

purpose:    Provides multi-layered syntax highlighting for ReactJava sources.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.editor;
                                       // imports --------------------------- //
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import io.reactjava.plugins.intellij.lexer.ReactJavaLexer;
import org.jetbrains.annotations.NotNull;
                                       // SyntaxHighlighter ==================//
public class SyntaxHighlighter extends SyntaxHighlighterBase
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

@name       getHighlightingLexer - get highlighting lexer for java source files
                                                                              */
                                                                             /**
            Get highlighting lexer for associated java source file


@return     highlighting lexer for associated java source file

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
@NotNull
@Override
public Lexer getHighlightingLexer()
{
    return new ReactJavaLexer();
}
/*------------------------------------------------------------------------------

@name       getTokenHighlights - get highlights for specified token type
                                                                              */
                                                                             /**
            Get highlights for specified token type


@return     highlights for specified token type

@param      tokenType      highlights for specified token

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
@NotNull
@Override
public TextAttributesKey[] getTokenHighlights(
   IElementType tokenType)
{
   return new TextAttributesKey[0];
}
}//====================================// SyntaxHighlighter ------------------//
