/*
 * Copyright (C) 2014 Tim Vaughan <tgvaughan@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mandelscape;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class MandelscapeApp extends JFrame {

    public MandelscapeApp() throws HeadlessException {
        setTitle("MandelView - Mandelbrot Set Viewer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cp = getContentPane();

        final MandelModel model = new MandelModel(500, 800, 800);
        MandelColourModel colourModel = new BasicMandelColourModel();

        final MandelPanel mandelPanel = new MandelPanel(model, colourModel);
        cp.add(mandelPanel, BorderLayout.CENTER);
        mandelPanel.repaint();

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Maximum number of iterations: "));
        JSpinner iterSpinner = new JSpinner(new SpinnerNumberModel(500, 100, 10000, 100));
        iterSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner spinnerObj = (JSpinner)e.getSource();
                model.setMaxIter((Integer)spinnerObj.getValue());
                mandelPanel.repaint();
            }
        });
        bottomPanel.add(iterSpinner);
        cp.add(bottomPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(800, 800));
        pack();
    }

    public static void main(String[] args) {

        new MandelscapeApp().setVisible(true);

    }
    
}
