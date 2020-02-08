/*==============================================================================

name:       PluginStartup.java

purpose:    Startup for ReactJavaPluigin

            Implements beforeProjectLoaded() handler of ProjectLifecycleListener
            extension registered in plugin.xml

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij;
                                       // imports --------------------------- //
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.openapi.fileTypes.EditorHighlighterProvider;
import com.intellij.openapi.fileTypes.FileTypeEditorHighlighterProviders;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.openapi.project.impl.ProjectLifecycleListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import io.reactjava.plugins.intellij.editor.Highlighter;
import io.reactjava.plugins.intellij.psi.PsiUtils;
import io.reactjava.plugins.intellij.psi.ReactJavaFileChangedListener;

                                       // ReactJavaStartup ===================//
public class PluginStartup
   implements ProjectLifecycleListener, ProjectManagerListener
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

@name       beforeProjectLoaded - project opened
                                                                              */
                                                                             /**
            Project opened

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public void beforeProjectLoaded(
   Project project)
{
                                       // register a project manager listener //
   project.getMessageBus().connect().subscribe(ProjectManager.TOPIC, this);
}
/*------------------------------------------------------------------------------

@name       projectClosing - project closed
                                                                              */
                                                                             /**
            Invoked on project close before any closing activities

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public void projectClosing(
   Project closedProject)
{
   if (PsiUtils.isReactJavaProject(closedProject))
   {
      PsiUtils.clearReactJavaPsiFileCache();
   }
}
/*------------------------------------------------------------------------------

@name       projectOpened - project opened
                                                                              */
                                                                             /**
            Invoked on project open

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public void projectOpened(
   Project openedProject)
{
   if (PsiUtils.isReactJavaProject(openedProject))
   {
                                       // add the ReactJava highlighter       //
      EditorHighlighterProvider reactJavaEditorHighlighterProvider =
         (project, fileType, virtualFile, colors) ->
            (new Highlighter(project, virtualFile, colors));

      FileTypeEditorHighlighterProviders.INSTANCE.addExplicitExtension(
         JavaFileType.INSTANCE, reactJavaEditorHighlighterProvider);

                                       // register a file change listener     //
      openedProject.getMessageBus().connect().subscribe(
         VirtualFileManager.VFS_CHANGES, new ReactJavaFileChangedListener());
   }
}
}//====================================// end ReactJavaStartup ---------------//
