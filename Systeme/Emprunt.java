/*
 * Created by JFormDesigner on Fri Apr 12 16:57:41 UTC 2024
 */

package Systeme;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author user
 */
public class Emprunt extends JFrame {
    public Emprunt() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Sokhna Diarra Diop
        tete = new JLabel();

        //======== this ========
        setBackground(new Color(0xccccff));
        setForeground(SystemColor.text);
        var contentPane = getContentPane();

        //---- tete ----
        tete.setText("GESTION EMPRUNT");
        tete.setFont(new Font("Century Schoolbook", Font.BOLD, 24));
        tete.setForeground(new Color(0x660066));
        tete.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(88, Short.MAX_VALUE)
                    .addComponent(tete, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
                    .addGap(74, 74, 74))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(tete, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 410, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Sokhna Diarra Diop
    private JLabel tete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
