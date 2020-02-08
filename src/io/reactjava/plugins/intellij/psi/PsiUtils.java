/*==============================================================================

name:       PsiUtils.java

purpose:    ReactJava PSI Utils.

history:    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

notes:

                        COPYRIGHT (c) BY GIAVANEERS, INC.
         This source code is licensed under the MIT license found in the
             LICENSE file in the root directory of this source tree.

==============================================================================*/
                                       // package --------------------------- //
package io.reactjava.plugins.intellij.psi;
                                       // imports --------------------------- //
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.impl.StubVirtualFile;
import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;
import com.intellij.psi.impl.source.JavaStubPsiElement;
import com.intellij.psi.impl.source.tree.TreeElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.assertj.core.annotations.NonNull;
import org.jetbrains.annotations.Nullable;
                                       // ReactJavaPsiUtil ===================//
public class PsiUtils
{
                                       // class constants --------------------//
public static final String kREACT_JAVA_COMMENT_OPEN  = "/*--";
public static final String kREACT_JAVA_COMMENT_CLOSE = "--*/";

                                       // regex matching render or renderCSS  //
                                       // method declarations (simplistic)    //
public static final Pattern kREGEX_RENDER_OR_RENDER_CSS =
   Pattern.compile(
      "((void)[\\s]+(renderCSS)[\\s]*[(][^)]*[)][\\s]*[{])"
    + "|((void)[\\s]+(render)[\\s]*[(][^)]*[)][\\s]*[{])");

                                       // map of reactjava virtual files      //
                                       // private so accessor will be used    //
private static final Map<VirtualFile,ReactJavaPsiFile>
   kPSI_FILE_BY_VIRTUAL_FILE = new HashMap<>();

                                       // map of reactjava blocks by reactjava//
                                       // psi file                            //
private static final Map<ReactJavaPsiFile,List<String>>
   kREACT_JAVA_BLOCKS_BY_REACT_JAVA_PSI_FILE = new HashMap<>();

                                       // class variables ------------------- //
                                       // (none)                              //
                                       // public instance variables --------- //
                                       // (none)                              //
                                       // protected instance variables -------//
                                       // (none)                              //
                                       // private instance variables -------- //
                                       // (none)                              //

/*------------------------------------------------------------------------------

@name       clearReactJavaPsiFileCache - remove  all ReactJava psi files.
                                                                              */
                                                                             /**
            Deregister all ReactJava psi files.

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static void clearReactJavaPsiFileCache()
{

   kPSI_FILE_BY_VIRTUAL_FILE.clear();
   kREACT_JAVA_BLOCKS_BY_REACT_JAVA_PSI_FILE.clear();
}
/*------------------------------------------------------------------------------

@name       deregisterReactJavaPsiFile - remove ReactJava psi file.
                                                                              */
                                                                             /**
            Deregister ReactJava psi file for specified virtual file.

@return     deregistered ReactJava psi file, or null if none found

@param      file        target virtual file

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static ReactJavaPsiFile deregisterReactJavaPsiFile(
   @NonNull VirtualFile file)
{
   ReactJavaPsiFile psiFile = kPSI_FILE_BY_VIRTUAL_FILE.remove(file);
   if (psiFile != null)
   {
      kREACT_JAVA_BLOCKS_BY_REACT_JAVA_PSI_FILE.remove(psiFile);

      System.out.println(
        "PsiUtils.deregisterReactJavaPsiFile(): removed " + file);
   }
   return psiFile;
}
/*------------------------------------------------------------------------------

@name       getJavaSections - get java sections for specified reactjava block
                                                                              */
                                                                             /**
            Get java sections for specified reactjava block.

@return     java sections for specified reactjava block

@param      reactJavaBlock    target reactjava block

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static List<String> getJavaSections(
   String reactJavaBlock)
{
   List<String> javaSections = new ArrayList<>();

   for (int idxBeg = 0, idxEnd = 0, idxMax = reactJavaBlock.length();
         idxBeg < idxMax;
         idxBeg = idxEnd + 1)
   {
      idxBeg = reactJavaBlock.indexOf('{', idxBeg);
      if (idxBeg < 0)
      {
         break;
      }
      idxEnd = reactJavaBlock.indexOf('}', idxBeg);
      if (idxEnd < 0)
      {
         break;
      }

      javaSections.add(reactJavaBlock.substring(idxBeg + 1, idxEnd));
   }

   return(javaSections);
}
/*------------------------------------------------------------------------------

@name       getPsiFileString - get psi file string representation
                                                                              */
                                                                             /**
            Get psi file string representation.

@return     psi file string representation

@param      psiFile     psi file

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static String getPsiFileString(
   PsiFile psiFile)
{
   final StringBuffer buf = new StringBuffer("");

   PsiRecursiveElementWalkingVisitor visitor =
      new PsiRecursiveElementWalkingVisitor(false)
      {
         List<PsiElement> ancestors = new ArrayList<>();
         int              indent;

         public void visitElement(PsiElement element)
         {
            int        size = ancestors.size();
            PsiElement last = size > 0 ? ancestors.get(size - 1) : null;
            PsiElement ancestor;

            if (isSibling(element, last))
            {
                                       // remove the last                     //
               ancestors.remove(last);
            }
            else if ((ancestor = getAncestor(element)) != null)
            {
               if (ancestor != last)
               {
                                       // remove all back to the parent       //
                  ancestors.subList(
                     ancestors.indexOf(ancestor) + 1, ancestors.size()).clear();
               }
            }
            ancestors.add(element);

            for (int i = size * 2; i > 0; i--)
            {
               buf.append(" ");
            }

            buf.append(element.toString() + " " + element.getTextRange() + "\n");

            super.visitElement(element);
         }
         protected void elementFinished(PsiElement element)
         {
            element = element;
         }
         protected PsiElement getAncestor(PsiElement element)
         {
            PsiElement ancestor = null;
            if (element != null)
            {
               PsiElement parent = element.getParent();
               if (parent != null && ancestors.contains(parent))
               {
                  ancestor = parent;
               }
            }

            return(ancestor);
         }
         protected boolean isSibling(PsiElement element, PsiElement candidate)
         {
            Set<PsiElement> siblings = null;
            if (candidate != null)
            {
               siblings =
                  new HashSet<>(Arrays.asList(
                     element.getParent().getChildren()));
            }

            return(candidate != null ? siblings.contains(candidate) : false);
         }
      };
   psiFile.accept(visitor);

   return(buf.toString());
}
/*------------------------------------------------------------------------------

@name       getReactJavaBlocks - get ReactJava blocks for ReactJava psi file
                                                                              */
                                                                             /**
            Get ReactJava blocks for specified ReactJava psi file.

@return     ReactJava blocks for specified ReactJava psi file

@param      psiFile     ReactJava psi file

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - AR) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static List<String> getReactJavaBlocks(
   ReactJavaPsiFile psiFile)
{
   List<String> blocks = kREACT_JAVA_BLOCKS_BY_REACT_JAVA_PSI_FILE.get(psiFile);
   if (blocks == null)
   {
      final List<String> newBlocks = new ArrayList<>();
      psiFile.accept(new JavaRecursiveElementVisitor()
      {
         @Override
         public void visitComment(PsiComment comment)
         {
            String block = comment.getText();
            if (block.startsWith(kREACT_JAVA_COMMENT_OPEN)
                  && block.endsWith(kREACT_JAVA_COMMENT_CLOSE))
            {
               newBlocks.add(block);
            }
         }
      });

      kREACT_JAVA_BLOCKS_BY_REACT_JAVA_PSI_FILE.put(psiFile, newBlocks);
      blocks = newBlocks;
   }
   return(blocks);
}
/*------------------------------------------------------------------------------

@name       isReactJavaFile - test whether is ReactJava file.
                                                                              */
                                                                             /**
            Test whether is ReactJava file.

@return     true iff is ReactJava file

@param      file        target file

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static boolean isReactJavaFile(
   @Nullable VirtualFile file)
{
   boolean bReactJavaFile =
      registeredReactJavaPsiFile(file) != null || isReactJavaFileByContent(file);

   return bReactJavaFile;
}
/*------------------------------------------------------------------------------

@name       isReactJavaFileByContent - test whether is ReactJava file by content
                                                                              */
                                                                             /**
            Test whether is ReactJava file by examining content.

@return     true iff is ReactJava file by examining content.

@param      file        target file

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static boolean isReactJavaFileByContent(
   @Nullable VirtualFile file)
{
   boolean bReactJavaFile = false;

   if (file != null
         && !(file instanceof StubVirtualFile)
         && "java".equals(file.getExtension().toLowerCase()))
   {
      Document doc = FileDocumentManager.getInstance().getCachedDocument(file);
      try
      {
         String src =
            doc != null
               ? doc.getText()
               : new String(file.contentsToByteArray(true), "UTF-8");

         if (kREGEX_RENDER_OR_RENDER_CSS.matcher(src).find())
         {
            bReactJavaFile = true;
         }
      }
      catch(IOException e)
      {
         throw new IllegalStateException(e);
      }
   }

   return bReactJavaFile;
}
/*------------------------------------------------------------------------------

@name       isReactJavaFileElement - test whether is ReactJava file element.
                                                                              */
                                                                             /**
            Test whether is ReactJava file element.

@return     true iff is ReactJava file element

@param      element     target element

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static boolean isReactJavaFileElement(
   @NonNull PsiElement element)
{
   return(isReactJavaFile(element.getContainingFile().getVirtualFile()));
}
/*------------------------------------------------------------------------------

@name       isReactJavaProject - test whether is ReactJava project.
                                                                              */
                                                                             /**
            Test whether is ReactJava project. All ReactJava projects include
            a file with name "reactjava.xml" in the project ".idea" directory.

@return     true iff is ReactJava project

@param      project     target project

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static boolean isReactJavaProject(
   Project project)
{
   boolean bReactJavaProject =
      new File(
         new File(
            project.getProjectFilePath()).getParentFile(),
            "reactjava.xml").exists();

   return bReactJavaProject;
}
/*------------------------------------------------------------------------------

@name       isReferencedInMarkupOrCSS - element is referenced in markup or css.
                                                                              */
                                                                             /**
            Test whether element is referenced in markup or css.

@return     true iff element is referenced in markup or css

@param      element     target element

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static boolean isReferencedInMarkupOrCSS(
   PsiElement element)
{
   boolean bReferenced = false;
   PsiFile psiFile     = element.getContainingFile();
   if (psiFile != null && psiFile instanceof ReactJavaPsiFile)
   {
      bReferenced =
         isReferencedInMarkupOrCSS((ReactJavaPsiFile)psiFile, element);
   }

   return(bReferenced);
}
/*------------------------------------------------------------------------------

@name       isReferencedInMarkupOrCSS - element is referenced in markup or css.
                                                                              */
                                                                             /**
            Test whether element is referenced in markup or css.

@return     true iff element is referenced in markup or css

@param      psiFile     react java psi file
@param      element     target element

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static boolean isReferencedInMarkupOrCSS(
   @NonNull ReactJavaPsiFile psiFile,
   @NonNull PsiElement       element)
{
   final boolean[] bReferenced = new boolean[1];
   final String    elementText = element.getText();
   final String    elementType =
      element instanceof JavaStubPsiElement
         ? ((JavaStubPsiElement)element).getElementType().toString()
         : element instanceof TreeElement
            ? ((TreeElement)element).getElementType().toString()
            : null;

   if (elementType != null)
   {
      String[] targets = {elementText};
      boolean bImport = "IMPORT_STATEMENT".equals(elementType);
      if (bImport)
      {
         String trimmed = elementText.replace(";","").trim();
         targets =
            new String[]
            {
               trimmed,
               trimmed.substring(elementText.lastIndexOf('.') + 1)
            };
      }

      loopBlocks:
      for (String reactJavaBlock : getReactJavaBlocks(psiFile))
      {
         for (String target : targets)
         {
            if (bImport)
            {
               if (reactJavaBlock.contains(target))
               {
                  bReferenced[0] = true;
                  break loopBlocks;
               }
            }
            else
            {
               for (String javaSection : getJavaSections(reactJavaBlock))
               {
                  if (javaSection.contains(target))
                  {
                     bReferenced[0] = true;
                     break loopBlocks;
                  }
               }
            }
         }
      }
   }

   return(bReferenced[0]);
}
/*------------------------------------------------------------------------------

@name       registerReactJavaPsiFile - put ReactJava psi file for virtual file.
                                                                              */
                                                                             /**
            Register ReactJava psi file for specified virtual file.

@return     registered ReactJava psi file

@param      file        target virtual file

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static ReactJavaPsiFile registerReactJavaPsiFile(
   @NonNull VirtualFile      file,
   @NonNull ReactJavaPsiFile psiFile)
{
   kPSI_FILE_BY_VIRTUAL_FILE.put(file, psiFile);

   System.out.println("PsiUtils.registerReactJavaPsiFile(): added " + file);

   return psiFile;
}
/*------------------------------------------------------------------------------

@name       registeredReactJavaPsiFile - get ReactJava psi file for virtual file.
                                                                              */
                                                                             /**
            Get ReactJava psi file for specified virtual file.

@return     ReactJavaPsiFile iff is ReactJava file; otherwise null

@param      file        target virtual file

@history    Sun Jan 19, 2020 10:30:00 (Giavaneers - LBM) created

@notes
                                                                              */
//------------------------------------------------------------------------------
public static ReactJavaPsiFile registeredReactJavaPsiFile(
   @Nullable VirtualFile file)
{
   ReactJavaPsiFile reactJavaPsiFile =
      file != null ? kPSI_FILE_BY_VIRTUAL_FILE.get(file) : null;

   return reactJavaPsiFile;
}
}//====================================// end ReactJavaPsiUtil ---------------//
