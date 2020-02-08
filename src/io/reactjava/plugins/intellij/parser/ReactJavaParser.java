package io.reactjava.plugins.intellij.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
    Simple ReactJava parser implementation.
    @status Working, PI
 */
public class ReactJavaParser implements PsiParser {
    @NotNull
    @Override
    public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        Marker marker = builder.mark();

        while (!builder.eof())
        {
            IElementType type = builder.getTokenType();

            builder.advanceLexer();
        }

        marker.done(root);
        return builder.getTreeBuilt();
    }
}
