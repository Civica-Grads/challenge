package com.ipl.training.induction.draughts.model;

import com.ipl.training.induction.draughts.controller.IDraughtsModel.PlayerColor;
import com.ipl.training.induction.draughts.controller.Square;

/**
 * Utility to print the {@link BoardLayout} to console.
 * Relies on unicode characters so to enable these edit the run/debug configuration by
 * following this guide:
 * {@link http://eclipsesource.com/blogs/2013/02/21/pro-tip-unicode-characters-in-the-eclipse-console/}
 * 
 * @author Matthew.Strickland
 * 
 */
public final class AsciiBoardLayout {

	/** Display the score string. */
	private static final String SCORE_STRING = "Board above has score:";
	/** Space as a string. */
	private static final String SPACE = " ";
	/** The divider between boards. */
	private static final String DIVIDER = "____________________________________________________________________________";
	/** The constant for a left empty edge. */
	private static final String HORIZONTAL_EDGE = "_";
	/** The constant for an edge. */
	private static final String VERITCAL_EDGE = "|";
	/** The length of the rows and columns. */
	private static final int ROW_COLUMN_LENGTH = 8;
	/** The number of columns we care about. */
	private static final int NUMBER_COLUMNS = ROW_COLUMN_LENGTH /2;
	/** The number of squares on the board we care about. */
	private static final int NUMBER_SQUARES = ROW_COLUMN_LENGTH * ROW_COLUMN_LENGTH /2;

	// Unicode characters
	// Note: See class JavaDoc to active unicode characters in the console
	/** Combining underline unicode string. */
	private static final String COMBINING_UNDERLINE = "\u0332";
	/** Black circle unicode string. */
	private static final String BLACK_CIRCLE = "\u25CF";
	/** Black square unicode string. */
	private static final String BLACK_SQUARE = "\u25A0";
	/** White circle unicode string. */
	private static final String WHITE_CIRCLE = "\u25CB";
	/** White square unicode string. */
	private static final String WHITE_SQUARE = "\u25A1";

	/** Private constructor. */
	private AsciiBoardLayout() {
		// Do nothing
	}

	/**
	 * Print the board to console.
	 * @param layout the layout
	 */
	public static void printBoard(final BoardLayout layout) {
		final StringBuilder builder = new StringBuilder();
		createInitialLines(layout, builder);
		for (int i = 1 ; i <= NUMBER_SQUARES ; i++) {
			if (i % NUMBER_COLUMNS == 1) {
				builder.append(VERITCAL_EDGE);
			}
			final SquareContent square = SquareContent.fromSquare(layout.getSquare(i));
			final String squareContent = square.getVisualString();
			if(BoardLayout.getRank(i, PlayerColor.BLACK) % 2 == 1) {
				builder.append(HORIZONTAL_EDGE);
				builder.append(VERITCAL_EDGE);
				builder.append(squareContent);
			} else {
				builder.append(squareContent);
				builder.append(VERITCAL_EDGE);
				builder.append(HORIZONTAL_EDGE);
			}

			builder.append(VERITCAL_EDGE);
			if (i % NUMBER_COLUMNS == 0) {
				builder.append(System.lineSeparator());
			}
		}

		System.out.println(builder);
	}

	/**
	 * Print the board with the score.
	 * @param layout the layout
	 * @param score the score for this layout
	 */
	public static void printBoard(final BoardLayout layout, final int score) {
		printBoard(layout);
		final StringBuilder builder = new StringBuilder();
		builder.append(SCORE_STRING);
		builder.append(SPACE);
		builder.append(score);
		System.out.println(builder);
	}

	/**
	 * Creates the initial lines for the builder.
	 * @param layout the layout this is for
	 * @param builder the builder
	 */
	private static void createInitialLines(final BoardLayout layout,
			final StringBuilder builder) {
		builder.append(System.lineSeparator());
		builder.append(DIVIDER);
		builder.append(System.lineSeparator());
		builder.append(System.lineSeparator());
		builder.append(layout);
		builder.append(System.lineSeparator());

		for (int i = 0 ; i < ROW_COLUMN_LENGTH ; i++) {
			builder.append(SPACE);
			builder.append(HORIZONTAL_EDGE);
		}

		builder.append(System.lineSeparator());
	}

	/**
	 * Decorator for Square.
	 * 
	 * @author Matthew.Strickland
	 * 
	 */
	private static enum SquareContent {

		/** The black piece. */
		BP(Square.BLACK_PIECE, BLACK_CIRCLE),
		/** The black king. */
		BK(Square.BLACK_KING, BLACK_SQUARE),
		/** The white piece. */
		WP(Square.WHITE_PIECE, WHITE_CIRCLE),
		/** The white king. */
		WK(Square.WHITE_KING, WHITE_SQUARE),
		/** Empty, no piece. */
		NP(Square.EMPTY, HORIZONTAL_EDGE);

		/** The square to decorate. */
		private final Square square;
		/** The string the will be printed for this square. */
		private final String visualString;

		/**
		 * Enum constructor.
		 * @param square the square we are decorating
		 * @param visualString the visual string for this square
		 */
		SquareContent(final Square square, final String visualString) {
			this.square = square;
			this.visualString = visualString;
		}

		/**
		 * Utility to find this enum value for a given {@link Square}.
		 * @param square
		 * @return
		 */
		public static SquareContent fromSquare(final Square square) {
			if (square != null) {
				for (final SquareContent content : SquareContent.values()) {
					if (content.getSquare().equals(square)) {
						return content;
					}
				}
			}
			return null;
		}

		/**
		 * Get square.
		 * @return the square
		 */
		public Square getSquare() {
			return square;
		}

		/**
		 * Get the visual string.
		 * @return the visual string
		 */
		public String getVisualString() {
			final StringBuilder builder = new StringBuilder(COMBINING_UNDERLINE);
			builder.append(visualString);
			return builder.toString();
		}

	}

}
