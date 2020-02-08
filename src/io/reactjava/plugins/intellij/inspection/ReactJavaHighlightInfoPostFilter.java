/*==============================================================================

name:       ReactJavaHighlightInfoPostFilter.java

purpose:    Highlight post filter for ReactJava.

            Extension for "com.intellij.highlightInfoPostFilter" extension point.

            This is used rather than ReactJavaImplicitUsageProvider since
            ImplicitUsageProvider is not invoked for import statements.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.inspection;
                                       // imports --------------------------- //
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.HighlightInfoPostFilter;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import io.reactjava.plugins.intellij.psi.PsiUtils;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Field;
                                       // ReactJavaHighlightErrorFilter ======//
public class ReactJavaHighlightInfoPostFilter implements HighlightInfoPostFilter
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

@name       accept - check whether highlighting should be added to markup
                                                                              */
                                                                             /**
            Check whether highlighting should be added to markup.

@return     true if highlighting should be added to markup

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public boolean accept(
   @NotNull HighlightInfo highlightInfo)
{
   boolean bAccept = true;
   TextAttributesKey attributesKey = highlightInfo.type.getAttributesKey();
   if (attributesKey.equals(CodeInsightColors.NOT_USED_ELEMENT_ATTRIBUTES))
   {
      try
      {
                                       // psiElement is package protected     //
         Field field = HighlightInfo.class.getDeclaredField("psiElement");
         field.setAccessible(true);

         bAccept =
            !PsiUtils.isReferencedInMarkupOrCSS(
               (PsiElement)field.get(highlightInfo));
      }
      catch(Exception e)
      {
                                       // ignore                              //
      }
   }
   return bAccept;
}
}//------------------------------------// end ReactJavaHighlightInfoPostFilter//
