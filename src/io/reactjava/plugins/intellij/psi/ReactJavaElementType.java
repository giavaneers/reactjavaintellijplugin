/*==============================================================================

name:       ReactJavaElementType.java

purpose:    Simple implementation of custom ElementType for ReactJava.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.psi;
                                       // imports --------------------------- //
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
                                       // ReactJavaElementType ===============//
public class ReactJavaElementType extends IElementType
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
                                       // (none)                              //
/*------------------------------------------------------------------------------

@name       ReactJavaElementType - constructor for specified debug name
                                                                              */
                                                                             /**
            Constructor for specified debug name

@return     debugName       debug name

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public ReactJavaElementType(
   @NotNull String debugName)
{
   super(debugName, ReactJavaLanguage.INSTANCE);
}
/*------------------------------------------------------------------------------

@name       toString - standard toString method
                                                                              */
                                                                             /**
            Standard toString method

@return     string representation of instance

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public String toString()
{
   return "[ReactJava] " + super.toString();
}
}//====================================// end ReactJavaElementType -----------//
