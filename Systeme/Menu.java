/*
 * Created by JFormDesigner on Thu Apr 11 12:18:37 UTC 2024
 */

package Systeme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout;
//import com.intellij.ide.ui.laf.*;
//import com.intellij.ide.ui.laf.*;
import com.intellij.uiDesigner.core.*;

/**
 * @author user
 */
public class Menu extends javax.swing.JFrame {
    public Menu() {
        initComponents();
        getContentPane().add(panel1); // Ajouter panel1 à la fenêtre
        pack(); // Ajuster la taille de la fenêtre en fonction des composants
    }
    //@SuppressWarnings("unchecked")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Sokhna Diarra Diop
        panel1 = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0xff66cc));
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );

            //---- label1 ----
            label1.setText("  BIBILOTHEQUE           ");
            label1.setBackground(new Color(0xffccff));
            label1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            label1.setFont(new Font("Britannic Bold", Font.BOLD | Font.ITALIC, 30));
            label1.setForeground(new Color(0x330033));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setHorizontalTextPosition(SwingConstants.CENTER);

            //---- button1 ----
            button1.setText("LIVRE");
            button1.setFont(new Font("Berlin Sans FB Demi", button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 4));
            button1.setForeground(new Color(0x990099));

            //---- button2 ----
            button2.setText("EMPRUNT");
            button2.setFont(new Font("Berlin Sans FB Demi", button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 4));
            button2.setForeground(new Color(0x990099));

            //---- button3 ----
            button3.setText("UTILISATEUR");
            button3.setFont(new Font("Berlin Sans FB Demi", button3.getFont().getStyle() | Font.BOLD, button3.getFont().getSize() + 4));
            button3.setForeground(new Color(0x990099));

            //---- button4 ----
            button4.setText("STATISTIQUES");
            button4.setFont(new Font("Berlin Sans FB Demi", button4.getFont().getStyle() | Font.BOLD, button4.getFont().getSize() + 4));
            button4.setForeground(new Color(0x990099));

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(button4, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(button1, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                .addComponent(button3, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                .addComponent(button2, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
                        .addGap(0, 29, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(button3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(161, Short.MAX_VALUE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Sokhna Diarra Diop
    private JPanel panel1;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static void main(String args[]){

        // Créer une instance de la classe Menu
        Menu menu = new Menu();

        // Rendre la fenêtre de Menu visible
        menu.setVisible(true);

        // Créer une instance de la classe LIVRE
       // LIVRE livre = new LIVRE();

        // Rendre la fenêtre de LIVRE visible
       // livre.setVisible(true);

    }
}


