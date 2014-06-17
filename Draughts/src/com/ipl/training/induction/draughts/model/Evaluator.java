// Copyright (C) 2012 IPL Information Processing Ltd. All rights reserved.

package com.ipl.training.induction.draughts.model;

import com.ipl.training.induction.draughts.controller.IDraughtsModel.PlayerColor;

/**
 * An evaluator that evaluates a BoardLayout and returns a rating. The rating
 * should be higher for positions that are favourable for playerColour.
 */
public class Evaluator extends AbstractEvaluator {

    /**
     * Construct an evaluator for a particular player.
     * @param color
     *            The colour of this player
     */
    public Evaluator(final PlayerColor color) {
        super(color);
    }

    /**
     * THIS IS THE METHOD TO PRODUCE It should return a post.
     * @param layout
     *            layout to evaluate
     * @return the score for this board
     */
    int evaluateBoard(final BoardLayout layout) {
        return 0;
    }
}
