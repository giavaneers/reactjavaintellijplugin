package io.reactjava.plugins.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

/**
    Simple implementation of custom Psi element for ReactJava.
    @status Working, PI
    @todo Custom functionality
 */
public class ReactJavaPsiElement extends ASTWrapperPsiElement {
    public ReactJavaPsiElement(@NotNull ASTNode node) {
        super(node);
    }
}
