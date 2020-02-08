/*==============================================================================

name:       ReactJavaLexer.java

purpose:    Layered lexer implementation for ReactJava.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.lexer;
                                       // imports --------------------------- //
                                       // (none)                              //
                                       // ReactJavaLexer =====================//
public class ReactJavaLexer extends LayeredLexer {
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

@name       ReactJavaLexer - default constructor
                                                                              */
                                                                             /**
            Default constructor.

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public ReactJavaLexer()
{
   super(
      new ReactJavaTopLexer(),
      new ReactJavaSubLexer(),
      new ReactJavaSubLexer2(),
      ILexerTypes.kHTML_TOKENS,
      ILexerTypes.kCSS_TOKENS);
}
}//====================================// end ReactJavaLexer -----------------//
