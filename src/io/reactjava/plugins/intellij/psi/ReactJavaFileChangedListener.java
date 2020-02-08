/*==============================================================================

name:       ReactJavaStartup.java

purpose:    Component for plugin lifecycle

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.psi;
                                       // imports --------------------------- //
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import org.jetbrains.annotations.NotNull;
import java.util.List;
                                       // ReactJavaFileChangedListener =======//
public class ReactJavaFileChangedListener implements BulkFileListener
{
                                       // class constants --------------------//
                                       // (none)                              //
                                       // class variables ------------------- //
                                       // (none)                              //
                                       // public instance variables --------- //
                                       // (none)                              //
                                       // protected instance variables -------//
                                       // private instance variables -------- //
                                       // (none)                              //
/*------------------------------------------------------------------------------

@name       after - after file changed handler
                                                                              */
                                                                             /**
            After file changed handler

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
@Override
public void after(
   @NotNull List<? extends VFileEvent> events)
{
   for (VFileEvent event : events)
   {
                                       // clear all caches of changed file    //
      PsiUtils.deregisterReactJavaPsiFile(event.getFile());
   }
}
}//====================================// end ReactJavaStartup ---------------//
