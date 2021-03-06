package io.reactjava.plugins.intellij.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

/**
    Simple implementation of MergingLexerAdapter.
    @status Working, PI
    @todo Documentation, additional tokens as needed
 */
public class ReactJavaSubLexer extends MergingLexerAdapter   {
    protected static final TokenSet TOKENS_TO_MERGE = TokenSet.create(
            ILexerTypes.kELEMENT_TYPE_WHITE_SPACE
    );

    public ReactJavaSubLexer() {
        super(new FlexAdapter(new _ReactJavaSubLexer(null)), TOKENS_TO_MERGE);
    }
}
