/*==============================================================================

name:       LayeredLexer.java

purpose:    Simple layered implementation of a LookAheadLexer.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.lexer;
                                       // imports --------------------------- //
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
                                       // LayeredLexer =======================//
public class LayeredLexer extends LookAheadLexer
{
                                       // class constants --------------------//
                                       // (none)                              //
                                       // class variables ------------------- //
                                       // (none)                              //
                                       // public instance variables --------- //
                                       // (none)                              //
                                       // protected instance variables -------//
protected Lexer subLexer;
protected Lexer subLexer2;
protected TokenSet typesToLex;
protected TokenSet typesToLex2;
                                       // private instance variables -------- //
                                       // (none)
/*------------------------------------------------------------------------------

@name       ReactJavaLexer - default constructor
                                                                              */
                                                                             /**
            Default constructor.

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public LayeredLexer(
   Lexer    baseLexer,
   Lexer    subLexer,
   Lexer    subLexer2,
   TokenSet typesToLex,
   TokenSet typesToLex2)
{
    super(baseLexer);
    this.subLexer    = subLexer;
    this.subLexer2   = subLexer2;
    this.typesToLex  = typesToLex;
    this.typesToLex2 = typesToLex2;
}
/*------------------------------------------------------------------------------

@name       lookAhead - look ahead
                                                                              */
                                                                             /**
            Look ahead.

@param      baseLexer       base lexer

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
protected void lookAhead(
   Lexer baseLexer)
{
   if (typesToLex.contains(baseLexer.getTokenType()))
   {
      IElementType subToken;
      subLexer.start(
         baseLexer.getBufferSequence(),
         baseLexer.getTokenStart(),
         baseLexer.getTokenEnd());

      while((subToken = subLexer.getTokenType()) != null)
      {
         addToken(subLexer.getTokenEnd(), subToken);
         subLexer.advance();
      }

      baseLexer.advance();

   }
   else if (typesToLex2.contains(baseLexer.getTokenType()))
   {
      IElementType subToken;
      subLexer2.start(
         baseLexer.getBufferSequence(),
         baseLexer.getTokenStart(),
         baseLexer.getTokenEnd());

      while((subToken = subLexer2.getTokenType()) != null)
      {
         addToken(subLexer2.getTokenEnd(), subToken);
         subLexer2.advance();
      }

      baseLexer.advance();
   }
   else
   {
      addToken(baseLexer.getTokenType());
      baseLexer.advance();
   }
}
}//====================================// end LayeredLexer -------------------//
