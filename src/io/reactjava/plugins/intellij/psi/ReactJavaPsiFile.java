/*==============================================================================

name:       PsiReactJavaFile.java

purpose:    Simple implementation of PsiFile for ReactJava.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.psi;
                                       // imports --------------------------- //
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import org.jetbrains.annotations.NotNull;
                                       // PsiReactJavaFile ===================//
public class ReactJavaPsiFile extends PsiJavaFileImpl
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

@name       PsiReactJavaFile - constructor for specified view provider
                                                                              */
                                                                             /**
            Constructor for specified view provider

@return     viewProvider        view provider

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public ReactJavaPsiFile(
   @NotNull FileViewProvider viewProvider)
{
   super(viewProvider);
}
/*------------------------------------------------------------------------------

@name       toString - standard toString method
                                                                              */
                                                                             /**
            Standard toString method

@return     string representation

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
@Override
public String toString()
{
   return "PsiReactJavaFile:" + getName();
}
}//====================================// end PsiReactJavaFile ---------------//
