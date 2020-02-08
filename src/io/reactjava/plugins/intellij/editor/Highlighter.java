/*==============================================================================

name:       Highlighter.java

purpose:    Responsible for syntax highlighting and language specific editor
            features such as brace matching in Java.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.editor;
                                       // imports --------------------------- //
import com.intellij.ide.highlighter.HtmlFileHighlighter;
import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.lang.html.HtmlSyntaxHighlighterFactory;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.fileTypes.LanguageFileTypeHighlighterProvider;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.css.CssFileType;
import java.util.Objects;
import io.reactjava.plugins.intellij.lexer.ILexerTypes;
import io.reactjava.plugins.intellij.psi.PsiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

                                       // Highlighter ========================//
public class Highlighter extends LayeredLexerEditorHighlighter
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
                                       // (none)
/*------------------------------------------------------------------------------

@name       Highlighter - constructor
                                                                              */
                                                                             /**
            Constructor. Creates ReactJava SyntaxHighlighter for ReactJava
            files and the normal Java SyntaxHighlighter for normal Java files.

@param      project          project
@param      virtualFile     virtual file
@param      colors          colors

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public Highlighter(
   @Nullable Project            project,
   @Nullable VirtualFile        virtualFile,
   @NotNull  EditorColorsScheme colors)
{
   super(
      (PsiUtils.isReactJavaFile(virtualFile)
                                       // register ReactJava base highlighter //
         ? new SyntaxHighlighter()
                                       // syntax highlighter for normal Java  //
         : new LanguageFileTypeHighlighterProvider()
            .create(JavaFileType.INSTANCE, project, virtualFile)),

      colors);

   if (PsiUtils.isReactJavaFile(virtualFile))
   {
                                       // register highlighter layer for JAVA //
      registerLayer(
         ILexerTypes.kELEMENT_TYPE_JAVA,
         new LayerDescriptor(
            SyntaxHighlighterFactory.getSyntaxHighlighter(
               JavaLanguage.INSTANCE, project, virtualFile),
               ""));
                                       // register highlighter layer for HTML //
      //registerLayer(
      //    ILexerTypes.kELEMENT_TYPE_HTML,
      //    new LayerDescriptor(new HtmlFileHighlighter(),""));

      registerLayer(
          ILexerTypes.kELEMENT_TYPE_HTML,
          new LayerDescriptor(new JSXFileHighlighter(),""));

                                       // register highlighter layer for CSS  //
      registerLayer(
         ILexerTypes.kELEMENT_TYPE_CSS,
         new LayerDescriptor(
            Objects.requireNonNull(
               HtmlSyntaxHighlighterFactory.getSyntaxHighlighter(
                  CssFileType.INSTANCE, project, virtualFile)),
                  ""));
   }
}
}//====================================// end Highlighter --------------------//
