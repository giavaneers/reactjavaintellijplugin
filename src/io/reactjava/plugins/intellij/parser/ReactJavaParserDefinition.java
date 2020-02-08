package io.reactjava.plugins.intellij.parser;

import io.reactjava.plugins.intellij.lexer.ILexerTypes;
import io.reactjava.plugins.intellij.lexer.ReactJavaLexer;
import io.reactjava.plugins.intellij.psi.ReactJavaPsiFile;
import io.reactjava.plugins.intellij.psi.impl.ReactJavaPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

/**
    ReactJava parser/lexer definition/attachment class.
    @status Working, PI
    @todo Documentation
 */
public class ReactJavaParserDefinition implements ParserDefinition {
    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new ReactJavaLexer();
    }

    @NotNull
    @Override
    public PsiParser createParser(Project project) {
        return new ReactJavaParser();
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return ILexerTypes.kWHITESPACE_TOKENS;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return ILexerTypes.kCOMMENT_TOKENS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return ILexerTypes.kSTRING_LITERALS;
    }

    @Override
    public IFileElementType getFileNodeType() {
        return ILexerTypes.kELEMENT_TYPE_FILE;
    }

    @Override
    public com.intellij.psi.PsiFile createFile(FileViewProvider viewProvider) {
        return new ReactJavaPsiFile(viewProvider);
    }

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        IElementType type = node.getElementType();
        return new ReactJavaPsiElement(node);
    }
}
