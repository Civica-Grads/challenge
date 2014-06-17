// Copyright (C) 2012 IPL Information Processing Ltd. All rights reserved.

package com.ipl.training.induction.draughts.model;

import com.ipl.training.induction.draughts.controller.IDraughtsModel.PlayerColor;

/**
 * An abstract class representing a board evaluator that evaluates a BoardLayout
 * and returns a rating. The rating should be higher for positions that are
 * favourable for playerColour.
 */
public abstract class AbstractEvaluator {

    /** The Player that is represented by this instance. */
    private final PlayerColor playerColor;

    /**
     * Construct an evaluator for a particular player.
     * @param color
     *            The colour of this player
     */
    public AbstractEvaluator(final PlayerColor color) {
        playerColor = color;
    }

    /**
     * THIS IS THE METHOD TO PRODUCE It should return a post.
     * @param layout
     *            layout to evaluate
     * @return the score for this board
     */
    abstract int evaluateBoard(final BoardLayout layout);

    /**
     * The Player that is represented by this instance.
     * @return PlayerColor
     */
    public PlayerColor getPlayerColor() {
        return playerColor;
    }
}
