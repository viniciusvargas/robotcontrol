/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotcontrol;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import javax.swing.JFrame;
import ws3dproxy.CommandExecException;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Thing;
import ws3dproxy.util.Constants;

/**
 *
 * @author ia941
 */
public class ControllerFrame extends JFrame implements KeyListener {

    private Creature creature;

    public void keyPressed(KeyEvent e) {
        try {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                creature.start();
                creature.updateState();
                creature.move(2.0, 2.0, 5.0);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                creature.start();
                creature.updateState();
                creature.move(-2.0, -2.0, 5.0);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                creature.start();
                creature.updateState();
                creature.rotate(1.0);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                creature.start();
                creature.updateState();
                creature.rotate(-1.0);
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                System.out.println(creature.updateBag());
            } else if (e.getKeyCode() == KeyEvent.VK_X) {
                creature.start();
                if (creature.getThingsInVision() != null && !creature.getThingsInVision().isEmpty()) {
                    for (Thing thing : creature.getThingsInVision()) {
                        creature.putInSack(thing.getName());
                    }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                creature.start();
                if (creature.getThingsInVision() != null && !creature.getThingsInVision().isEmpty()) {
                    for (Thing thing : creature.getThingsInVision()) {
                        creature.eatIt(thing.getName());
                    }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_C) {
                creature.start();
                if (creature.getThingsInVision() != null && !creature.getThingsInVision().isEmpty()) {
                    for (Thing thing : creature.getThingsInVision()) {
                        creature.hideIt(thing.getName());
                    }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_V) {
                creature.start();
                if (creature.getThingsInVision() != null && !creature.getThingsInVision().isEmpty()) {
                    for (Thing thing : creature.getThingsInVision()) {
                        creature.unhideIt(thing.getName());
                    }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_B) {
                creature.start();
                creature.startCamera(creature.getName());
            }
        } catch (Exception ex) {
            System.out.println("Socket Error. " + ex.getMessage());
        }
    }

    public void keyReleased(KeyEvent e) {
        try {
            creature.stop();
        } catch (CommandExecException ex) {
            System.out.println("Socket Error. " + ex.getMessage());
        }

    }

    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }

    public static void execute(Creature creature) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ControllerFrame frame = new ControllerFrame(creature);
                frame.setTitle("Controller");
                frame.setResizable(false);
                frame.setSize(600, 600);
                frame.setMinimumSize(new Dimension(600, 600));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    ControllerFrame(Creature creature) {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        this.creature = creature;
    }
}
