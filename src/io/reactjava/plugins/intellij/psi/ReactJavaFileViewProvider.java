/*==============================================================================

name:       ReactJavaFileViewProvider.java

purpose:    ReactJava file view provider

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
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.java.JavaParserDefinition;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.SingleRootFileViewProvider;
import org.jetbrains.annotations.NotNull;
                                       // ReactJavaFileViewProvider ==========//
public class ReactJavaFileViewProvider extends SingleRootFileViewProvider
{
                                       // class constants --------------------//
                                       // (none)                              //
                                       // class variables --------------------//
                                       // (none)                              //
                                       // public instance variables --------- //
                                       // (none)                              //
                                       // protected instance variables -------//
                                       // (none)                              //
                                       // private instance variables -------- //
                                       // (none)                              //

/*------------------------------------------------------------------------------

@name       ReactJavaFileViewProvider - constructor
                                                                              */
                                                                             /**
            Constructor for specified language, template element type, and outer
            element type.

@param      file                      target file
@param      language                  language (ignored)
@param      psiManager                psi manager
@param      eventSystemEnabled        true iff event system is enabled

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public ReactJavaFileViewProvider(
   @NotNull PsiManager  psiManager,
   @NotNull VirtualFile file,
   boolean              eventSystemEnabled)
{
   super(psiManager, file, eventSystemEnabled);
}
/*------------------------------------------------------------------------------

@name       createFile - create psi file for specified language
                                                                              */
                                                                             /**
            Creates and returns a PsiFile.

@return     cloned provider for specified file copy

@param      language        language

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
@Override
protected PsiFile createFile(
   Language language)
{
   VirtualFile virtualFile = getVirtualFile();

   PsiFile psiFile = PsiUtils.registeredReactJavaPsiFile(virtualFile);
   if (psiFile == null)
   {
      if (!PsiUtils.isReactJavaFile(virtualFile))
      {
         psiFile = super.createFile(language);
      }
      else
      {
                                       // from SingleRootFileViewProvider     //
                                       // .createFile()                       //
         if (language == getBaseLanguage())
         {
            final ParserDefinition parserDef =
               LanguageParserDefinitions.INSTANCE.forLanguage(language);

            if (parserDef != null && parserDef instanceof JavaParserDefinition)
            {
                                       // from JavaParserDefinition           //
                                       // .createFile()                       //
               psiFile = new ReactJavaPsiFile(this);

               PsiUtils.registerReactJavaPsiFile(
                  virtualFile, (ReactJavaPsiFile)psiFile);
            }
         }
      }
   }

   return(psiFile);
}
}//====================================// end ReactJavaFileViewProvider ------//
