/*==============================================================================

name:       ReactJavaFileViewProviderFactory.java

purpose:    ReactJava file view provider factory.

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
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.FileViewProviderFactory;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
                                       // ReactJavaFileViewProviderFactory ===//
public class ReactJavaFileViewProviderFactory implements FileViewProviderFactory
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

@name       createFileViewProvider - create file view provider
                                                                              */
                                                                             /**
            Create file view provider.

@param      file                    target file
@param      language                language
@param      manager                 psi manager
@param      eventSystemEnabled      true iff event system is enabled

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
@NotNull
@Override
public FileViewProvider createFileViewProvider(
   @NotNull VirtualFile file,
   Language             language,
   @NotNull PsiManager  manager,
   boolean              eventSystemEnabled)
{
   FileViewProvider provider =
      new ReactJavaFileViewProvider(manager, file, eventSystemEnabled);

   return provider;
}
}//====================================// end ReactJavaFileViewProviderFactory//
