// Copyright (C) 2011 IPL Information Processing Ltd. All rights reserved
package com.ipl.training.induction.draughts;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import javax.swing.UIManager;

import com.ipl.training.induction.draughts.controller.DraughtsController;
import com.ipl.training.induction.draughts.controller.IDraughtsController;
import com.ipl.training.induction.draughts.model.DraughtsModel;
import com.ipl.training.induction.draughts.view.remote.RemoteView;
import com.ipl.training.induction.draughts.view.ui.DraughtsView;

/**
 * Main class of the Draughts Game.
 */
public final class DraughtsGame {

    /**
     * Opens the board and sets it up to its default arrangement.
     */
    private DraughtsGame() {
        readDefaultProperties();

        IDraughtsController controller = DraughtsController.getInstance();
        controller.setModel(DraughtsModel.createModel());
        controller.addView(DraughtsView.createView());
        try {
            controller.addView(RemoteView.createView());
        } catch (UnknownHostException e) {
            // Do nothing, remote games will not be supported
        }
        controller.newGame();
    }

    /**
     * Look for the default properties file, if it exists read the contents into
     * the system properties. This method exits silently if the
     * draughts.properties file does not exist, or there is an exception when
     * reading it.
     */
    private void readDefaultProperties() {
        // If there is a properties file read it
        final InputStream in = getClass().getClassLoader().getResourceAsStream("draughts.properties");
        if (in != null) {
            try {
                System.getProperties().load(in);
            } catch (IOException e) {
                // Do nothing
            }
        }
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(final String[] args) {
        try {
            // Set the look and feel to one appropriate for the platform
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            // Fail silently if we cannot set look and feel
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DraughtsGame();
            }
        });
    }
}
