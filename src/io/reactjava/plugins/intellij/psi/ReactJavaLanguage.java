/*==============================================================================

name:       ReactJavaLanguage.java

purpose:    Custom language implementation for ReactJava.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.psi;
                                       // imports --------------------------- //
import com.intellij.lang.Language;
                                       // ReactJavaLanguage ==================//
public class ReactJavaLanguage extends Language
{
                                       // class constants --------------------//
public static final ReactJavaLanguage INSTANCE = new ReactJavaLanguage();

                                       // class variables ------------------- //
                                       // (none)                              //
                                       // public instance variables --------- //
                                       // (none)                              //
                                       // protected instance variables -------//
                                       // (none)                              //
                                       // private instance variables -------- //
                                       // (none)                              //
/*------------------------------------------------------------------------------

@name       ReactJavaLanguage - default constructor
                                                                              */
                                                                             /**
            Default constructor

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
protected ReactJavaLanguage()
{
   super("ReactJava");
}
}//====================================// end ReactJavaLanguage --------------//
