package io.reactjava.plugins.intellij.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

/**
    Simple implementation of MergingLexerAdapter.
    @status Working, PI
    @todo Documentation, additional tokens as needed
 */
public class ReactJavaTopLexer extends MergingLexerAdapter {

    protected static final TokenSet TOKENS_TO_MERGE =
       TokenSet.create(
            ILexerTypes.kELEMENT_TYPE_COMMENT,
            ILexerTypes.kELEMENT_TYPE_WHITE_SPACE,
            ILexerTypes.kELEMENT_TYPE_HTML,
            ILexerTypes.kELEMENT_TYPE_JAVA,
            ILexerTypes.kELEMENT_TYPE_CSS);

    public ReactJavaTopLexer() {
        super(new LookAheadLexer(new FlexAdapter(new _ReactJavaTopLexer(null))) {
            @Override
            protected void lookAhead(@NotNull Lexer baseLexer)
            {
                super.lookAhead(baseLexer);
            }
        }, TOKENS_TO_MERGE);
    }
}
