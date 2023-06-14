// Generated from java-escape by ANTLR 4.11.1
package com.ibm.wala.cast.racket.Antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class BSLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, SYMBOL=21, NAME=22, NUMBER=23, INT=24, BOOLEAN=25, 
		STRING=26, CHARACTER=27, LANG=28, COMMENT=29, WS=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "SYMBOL", "NAME", "NUMBER", "INT", "BOOLEAN", 
			"STRING", "CHARACTER", "LANG", "COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "'define'", "')'", "'lambda'", "'define-struct'", "'cond'", 
			"'['", "']'", "'else '", "'if'", "'and'", "'or'", "'\\u2019()'", "'check-expect'", 
			"'check-random'", "'check-within'", "'check-member-of'", "'check-satisfied'", 
			"'check-error'", "'require'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "SYMBOL", "NAME", 
			"NUMBER", "INT", "BOOLEAN", "STRING", "CHARACTER", "LANG", "COMMENT", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public BSLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BSL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001e\u0147\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0004\u0015\u00da\b\u0015\u000b\u0015\f\u0015"+
		"\u00db\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u00e2"+
		"\b\u0016\n\u0016\f\u0016\u00e5\t\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00ed\b\u0016\u0001\u0017"+
		"\u0001\u0017\u0005\u0017\u00f1\b\u0017\n\u0017\f\u0017\u00f4\t\u0017\u0001"+
		"\u0017\u0003\u0017\u00f7\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u010c\b\u0018\u0001"+
		"\u0019\u0001\u0019\u0005\u0019\u0110\b\u0019\n\u0019\f\u0019\u0113\t\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0003\u001a\u0121\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u012a\b\u001b\n\u001b"+
		"\f\u001b\u012d\t\u001b\u0001\u001b\u0003\u001b\u0130\b\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0005\u001c"+
		"\u0138\b\u001c\n\u001c\f\u001c\u013b\t\u001c\u0001\u001c\u0003\u001c\u013e"+
		"\b\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0000\u0000\u001e\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e\u0001\u0000\u0007\t\u0000!!$&*+-:<Z\\\\^_az~~\u0001"+
		"\u000009\u0001\u000019\u0001\u0000 ~\u0003\u000009AZaz\u0002\u0000\n\n"+
		"\r\r\u0003\u0000\t\n\f\r  \u0157\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-"+
		"\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000"+
		"\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000"+
		"\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;"+
		"\u0001\u0000\u0000\u0000\u0001=\u0001\u0000\u0000\u0000\u0003?\u0001\u0000"+
		"\u0000\u0000\u0005F\u0001\u0000\u0000\u0000\u0007H\u0001\u0000\u0000\u0000"+
		"\tO\u0001\u0000\u0000\u0000\u000b]\u0001\u0000\u0000\u0000\rb\u0001\u0000"+
		"\u0000\u0000\u000fd\u0001\u0000\u0000\u0000\u0011f\u0001\u0000\u0000\u0000"+
		"\u0013l\u0001\u0000\u0000\u0000\u0015o\u0001\u0000\u0000\u0000\u0017s"+
		"\u0001\u0000\u0000\u0000\u0019v\u0001\u0000\u0000\u0000\u001bz\u0001\u0000"+
		"\u0000\u0000\u001d\u0087\u0001\u0000\u0000\u0000\u001f\u0094\u0001\u0000"+
		"\u0000\u0000!\u00a1\u0001\u0000\u0000\u0000#\u00b1\u0001\u0000\u0000\u0000"+
		"%\u00c1\u0001\u0000\u0000\u0000\'\u00cd\u0001\u0000\u0000\u0000)\u00d5"+
		"\u0001\u0000\u0000\u0000+\u00d9\u0001\u0000\u0000\u0000-\u00ec\u0001\u0000"+
		"\u0000\u0000/\u00f6\u0001\u0000\u0000\u00001\u010b\u0001\u0000\u0000\u0000"+
		"3\u010d\u0001\u0000\u0000\u00005\u0120\u0001\u0000\u0000\u00007\u0122"+
		"\u0001\u0000\u0000\u00009\u0135\u0001\u0000\u0000\u0000;\u0143\u0001\u0000"+
		"\u0000\u0000=>\u0005(\u0000\u0000>\u0002\u0001\u0000\u0000\u0000?@\u0005"+
		"d\u0000\u0000@A\u0005e\u0000\u0000AB\u0005f\u0000\u0000BC\u0005i\u0000"+
		"\u0000CD\u0005n\u0000\u0000DE\u0005e\u0000\u0000E\u0004\u0001\u0000\u0000"+
		"\u0000FG\u0005)\u0000\u0000G\u0006\u0001\u0000\u0000\u0000HI\u0005l\u0000"+
		"\u0000IJ\u0005a\u0000\u0000JK\u0005m\u0000\u0000KL\u0005b\u0000\u0000"+
		"LM\u0005d\u0000\u0000MN\u0005a\u0000\u0000N\b\u0001\u0000\u0000\u0000"+
		"OP\u0005d\u0000\u0000PQ\u0005e\u0000\u0000QR\u0005f\u0000\u0000RS\u0005"+
		"i\u0000\u0000ST\u0005n\u0000\u0000TU\u0005e\u0000\u0000UV\u0005-\u0000"+
		"\u0000VW\u0005s\u0000\u0000WX\u0005t\u0000\u0000XY\u0005r\u0000\u0000"+
		"YZ\u0005u\u0000\u0000Z[\u0005c\u0000\u0000[\\\u0005t\u0000\u0000\\\n\u0001"+
		"\u0000\u0000\u0000]^\u0005c\u0000\u0000^_\u0005o\u0000\u0000_`\u0005n"+
		"\u0000\u0000`a\u0005d\u0000\u0000a\f\u0001\u0000\u0000\u0000bc\u0005["+
		"\u0000\u0000c\u000e\u0001\u0000\u0000\u0000de\u0005]\u0000\u0000e\u0010"+
		"\u0001\u0000\u0000\u0000fg\u0005e\u0000\u0000gh\u0005l\u0000\u0000hi\u0005"+
		"s\u0000\u0000ij\u0005e\u0000\u0000jk\u0005 \u0000\u0000k\u0012\u0001\u0000"+
		"\u0000\u0000lm\u0005i\u0000\u0000mn\u0005f\u0000\u0000n\u0014\u0001\u0000"+
		"\u0000\u0000op\u0005a\u0000\u0000pq\u0005n\u0000\u0000qr\u0005d\u0000"+
		"\u0000r\u0016\u0001\u0000\u0000\u0000st\u0005o\u0000\u0000tu\u0005r\u0000"+
		"\u0000u\u0018\u0001\u0000\u0000\u0000vw\u0005\u2019\u0000\u0000wx\u0005"+
		"(\u0000\u0000xy\u0005)\u0000\u0000y\u001a\u0001\u0000\u0000\u0000z{\u0005"+
		"c\u0000\u0000{|\u0005h\u0000\u0000|}\u0005e\u0000\u0000}~\u0005c\u0000"+
		"\u0000~\u007f\u0005k\u0000\u0000\u007f\u0080\u0005-\u0000\u0000\u0080"+
		"\u0081\u0005e\u0000\u0000\u0081\u0082\u0005x\u0000\u0000\u0082\u0083\u0005"+
		"p\u0000\u0000\u0083\u0084\u0005e\u0000\u0000\u0084\u0085\u0005c\u0000"+
		"\u0000\u0085\u0086\u0005t\u0000\u0000\u0086\u001c\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0005c\u0000\u0000\u0088\u0089\u0005h\u0000\u0000\u0089\u008a"+
		"\u0005e\u0000\u0000\u008a\u008b\u0005c\u0000\u0000\u008b\u008c\u0005k"+
		"\u0000\u0000\u008c\u008d\u0005-\u0000\u0000\u008d\u008e\u0005r\u0000\u0000"+
		"\u008e\u008f\u0005a\u0000\u0000\u008f\u0090\u0005n\u0000\u0000\u0090\u0091"+
		"\u0005d\u0000\u0000\u0091\u0092\u0005o\u0000\u0000\u0092\u0093\u0005m"+
		"\u0000\u0000\u0093\u001e\u0001\u0000\u0000\u0000\u0094\u0095\u0005c\u0000"+
		"\u0000\u0095\u0096\u0005h\u0000\u0000\u0096\u0097\u0005e\u0000\u0000\u0097"+
		"\u0098\u0005c\u0000\u0000\u0098\u0099\u0005k\u0000\u0000\u0099\u009a\u0005"+
		"-\u0000\u0000\u009a\u009b\u0005w\u0000\u0000\u009b\u009c\u0005i\u0000"+
		"\u0000\u009c\u009d\u0005t\u0000\u0000\u009d\u009e\u0005h\u0000\u0000\u009e"+
		"\u009f\u0005i\u0000\u0000\u009f\u00a0\u0005n\u0000\u0000\u00a0 \u0001"+
		"\u0000\u0000\u0000\u00a1\u00a2\u0005c\u0000\u0000\u00a2\u00a3\u0005h\u0000"+
		"\u0000\u00a3\u00a4\u0005e\u0000\u0000\u00a4\u00a5\u0005c\u0000\u0000\u00a5"+
		"\u00a6\u0005k\u0000\u0000\u00a6\u00a7\u0005-\u0000\u0000\u00a7\u00a8\u0005"+
		"m\u0000\u0000\u00a8\u00a9\u0005e\u0000\u0000\u00a9\u00aa\u0005m\u0000"+
		"\u0000\u00aa\u00ab\u0005b\u0000\u0000\u00ab\u00ac\u0005e\u0000\u0000\u00ac"+
		"\u00ad\u0005r\u0000\u0000\u00ad\u00ae\u0005-\u0000\u0000\u00ae\u00af\u0005"+
		"o\u0000\u0000\u00af\u00b0\u0005f\u0000\u0000\u00b0\"\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b2\u0005c\u0000\u0000\u00b2\u00b3\u0005h\u0000\u0000\u00b3"+
		"\u00b4\u0005e\u0000\u0000\u00b4\u00b5\u0005c\u0000\u0000\u00b5\u00b6\u0005"+
		"k\u0000\u0000\u00b6\u00b7\u0005-\u0000\u0000\u00b7\u00b8\u0005s\u0000"+
		"\u0000\u00b8\u00b9\u0005a\u0000\u0000\u00b9\u00ba\u0005t\u0000\u0000\u00ba"+
		"\u00bb\u0005i\u0000\u0000\u00bb\u00bc\u0005s\u0000\u0000\u00bc\u00bd\u0005"+
		"f\u0000\u0000\u00bd\u00be\u0005i\u0000\u0000\u00be\u00bf\u0005e\u0000"+
		"\u0000\u00bf\u00c0\u0005d\u0000\u0000\u00c0$\u0001\u0000\u0000\u0000\u00c1"+
		"\u00c2\u0005c\u0000\u0000\u00c2\u00c3\u0005h\u0000\u0000\u00c3\u00c4\u0005"+
		"e\u0000\u0000\u00c4\u00c5\u0005c\u0000\u0000\u00c5\u00c6\u0005k\u0000"+
		"\u0000\u00c6\u00c7\u0005-\u0000\u0000\u00c7\u00c8\u0005e\u0000\u0000\u00c8"+
		"\u00c9\u0005r\u0000\u0000\u00c9\u00ca\u0005r\u0000\u0000\u00ca\u00cb\u0005"+
		"o\u0000\u0000\u00cb\u00cc\u0005r\u0000\u0000\u00cc&\u0001\u0000\u0000"+
		"\u0000\u00cd\u00ce\u0005r\u0000\u0000\u00ce\u00cf\u0005e\u0000\u0000\u00cf"+
		"\u00d0\u0005q\u0000\u0000\u00d0\u00d1\u0005u\u0000\u0000\u00d1\u00d2\u0005"+
		"i\u0000\u0000\u00d2\u00d3\u0005r\u0000\u0000\u00d3\u00d4\u0005e\u0000"+
		"\u0000\u00d4(\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005\u2019\u0000\u0000"+
		"\u00d6\u00d7\u0003+\u0015\u0000\u00d7*\u0001\u0000\u0000\u0000\u00d8\u00da"+
		"\u0007\u0000\u0000\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00da\u00db"+
		"\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00db\u00dc"+
		"\u0001\u0000\u0000\u0000\u00dc,\u0001\u0000\u0000\u0000\u00dd\u00ed\u0003"+
		"/\u0017\u0000\u00de\u00df\u0003/\u0017\u0000\u00df\u00e3\u0005.\u0000"+
		"\u0000\u00e0\u00e2\u0007\u0001\u0000\u0000\u00e1\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e5\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e6\u00e7\u0007\u0002\u0000"+
		"\u0000\u00e7\u00ed\u0001\u0000\u0000\u0000\u00e8\u00e9\u0003/\u0017\u0000"+
		"\u00e9\u00ea\u0005/\u0000\u0000\u00ea\u00eb\u0003/\u0017\u0000\u00eb\u00ed"+
		"\u0001\u0000\u0000\u0000\u00ec\u00dd\u0001\u0000\u0000\u0000\u00ec\u00de"+
		"\u0001\u0000\u0000\u0000\u00ec\u00e8\u0001\u0000\u0000\u0000\u00ed.\u0001"+
		"\u0000\u0000\u0000\u00ee\u00f2\u0007\u0002\u0000\u0000\u00ef\u00f1\u0007"+
		"\u0001\u0000\u0000\u00f0\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f4\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f7\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f5\u00f7\u00050\u0000\u0000\u00f6\u00ee\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f5\u0001\u0000\u0000\u0000\u00f70\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f9\u0005#\u0000\u0000\u00f9\u00fa\u0005t\u0000\u0000\u00fa"+
		"\u00fb\u0005r\u0000\u0000\u00fb\u00fc\u0005u\u0000\u0000\u00fc\u010c\u0005"+
		"e\u0000\u0000\u00fd\u00fe\u0005#\u0000\u0000\u00fe\u010c\u0005T\u0000"+
		"\u0000\u00ff\u0100\u0005#\u0000\u0000\u0100\u010c\u0005t\u0000\u0000\u0101"+
		"\u0102\u0005#\u0000\u0000\u0102\u0103\u0005f\u0000\u0000\u0103\u0104\u0005"+
		"a\u0000\u0000\u0104\u0105\u0005l\u0000\u0000\u0105\u0106\u0005s\u0000"+
		"\u0000\u0106\u010c\u0005e\u0000\u0000\u0107\u0108\u0005#\u0000\u0000\u0108"+
		"\u010c\u0005F\u0000\u0000\u0109\u010a\u0005#\u0000\u0000\u010a\u010c\u0005"+
		"f\u0000\u0000\u010b\u00f8\u0001\u0000\u0000\u0000\u010b\u00fd\u0001\u0000"+
		"\u0000\u0000\u010b\u00ff\u0001\u0000\u0000\u0000\u010b\u0101\u0001\u0000"+
		"\u0000\u0000\u010b\u0107\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000"+
		"\u0000\u0000\u010c2\u0001\u0000\u0000\u0000\u010d\u0111\u0005\"\u0000"+
		"\u0000\u010e\u0110\u0007\u0003\u0000\u0000\u010f\u010e\u0001\u0000\u0000"+
		"\u0000\u0110\u0113\u0001\u0000\u0000\u0000\u0111\u010f\u0001\u0000\u0000"+
		"\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112\u0114\u0001\u0000\u0000"+
		"\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0114\u0115\u0005\"\u0000\u0000"+
		"\u01154\u0001\u0000\u0000\u0000\u0116\u0117\u0005#\u0000\u0000\u0117\u0118"+
		"\u0005\\\u0000\u0000\u0118\u0121\u0007\u0004\u0000\u0000\u0119\u011a\u0005"+
		"#\u0000\u0000\u011a\u011b\u0005\\\u0000\u0000\u011b\u011c\u0005s\u0000"+
		"\u0000\u011c\u011d\u0005p\u0000\u0000\u011d\u011e\u0005a\u0000\u0000\u011e"+
		"\u011f\u0005c\u0000\u0000\u011f\u0121\u0005e\u0000\u0000\u0120\u0116\u0001"+
		"\u0000\u0000\u0000\u0120\u0119\u0001\u0000\u0000\u0000\u01216\u0001\u0000"+
		"\u0000\u0000\u0122\u0123\u0005#\u0000\u0000\u0123\u0124\u0005l\u0000\u0000"+
		"\u0124\u0125\u0005a\u0000\u0000\u0125\u0126\u0005n\u0000\u0000\u0126\u0127"+
		"\u0005g\u0000\u0000\u0127\u012b\u0001\u0000\u0000\u0000\u0128\u012a\b"+
		"\u0005\u0000\u0000\u0129\u0128\u0001\u0000\u0000\u0000\u012a\u012d\u0001"+
		"\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000\u0000\u012b\u012c\u0001"+
		"\u0000\u0000\u0000\u012c\u012f\u0001\u0000\u0000\u0000\u012d\u012b\u0001"+
		"\u0000\u0000\u0000\u012e\u0130\u0005\r\u0000\u0000\u012f\u012e\u0001\u0000"+
		"\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000"+
		"\u0000\u0000\u0131\u0132\u0005\n\u0000\u0000\u0132\u0133\u0001\u0000\u0000"+
		"\u0000\u0133\u0134\u0006\u001b\u0000\u0000\u01348\u0001\u0000\u0000\u0000"+
		"\u0135\u0139\u0005;\u0000\u0000\u0136\u0138\b\u0005\u0000\u0000\u0137"+
		"\u0136\u0001\u0000\u0000\u0000\u0138\u013b\u0001\u0000\u0000\u0000\u0139"+
		"\u0137\u0001\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013a"+
		"\u013d\u0001\u0000\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013c"+
		"\u013e\u0005\r\u0000\u0000\u013d\u013c\u0001\u0000\u0000\u0000\u013d\u013e"+
		"\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u0140"+
		"\u0005\n\u0000\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0142\u0006"+
		"\u001c\u0000\u0000\u0142:\u0001\u0000\u0000\u0000\u0143\u0144\u0007\u0006"+
		"\u0000\u0000\u0144\u0145\u0001\u0000\u0000\u0000\u0145\u0146\u0006\u001d"+
		"\u0000\u0000\u0146<\u0001\u0000\u0000\u0000\u000e\u0000\u00d9\u00db\u00e3"+
		"\u00ec\u00f2\u00f6\u010b\u0111\u0120\u012b\u012f\u0139\u013d\u0001\u0000"+
		"\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}