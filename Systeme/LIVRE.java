/*
 * Created by JFormDesigner on Thu Apr 11 15:02:24 UTC 2024
 */

package Systeme;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import com.intellij.uiDesigner.core.*;

/**
 * @author user
 */
public class LIVRE extends javax.swing.JFrame {

    Connection connection;


    public LIVRE() {
        initComponents();
        getContentPane().add(gestion_livre); // Ajouter panel1 à la fenêtre
        pack(); // Ajuster la taille de la fenêtre en fonction des composants


        try {
            // Obtenir une connexion à la base de données en utilisant la classe ConnexionDB
            connection = ConnexionDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions de connexion
        }
        TableLivre();
        //TableLivreRecherche();
    }


    private void button1(ActionEvent e) {
        try {
            // Récupérer les valeurs saisies dans les champs de texte
            String id = txtid.getText();
            String titre = txttitre.getText();
            String auteur = txtauteur.getText();
            String publication = txtpublication.getText(); // Notez que j'ai modifié txtannee en txtpublication pour clarifier le champ
            String isbn = txtisbn.getText();

            // Préparer la requête d'insertion
            String query = "INSERT INTO livres (id, titre, auteur, anneePublication, ISBN) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Définir les valeurs pour les paramètres de la requête
            statement.setString(1, id);
            statement.setString(2, titre);
            statement.setString(3, auteur);
            statement.setString(4, publication);
            statement.setString(5, isbn);

            // Exécuter la requête d'insertion
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Livre ajouté avec succès !");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du livre.");
            }
            TableLivre();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage());
        }


    }

    public void TableLivre() {
        String[] book = {"ID", "TITRE", "AUTEUR", "PUBLICATION", "ISBN"};
        String[] affichage = new String[6];
        DefaultTableModel model = new DefaultTableModel(null, book);
        try {
            String query = "SELECT * FROM livres";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                affichage[0] = resultSet.getString("id");
                affichage[1] = resultSet.getString("titre");
                affichage[2] = resultSet.getString("auteur");
                affichage[3] = resultSet.getString("anneePublication");
                affichage[4] = resultSet.getString("isbn");
                model.addRow(affichage);
            }

            livre.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage());
        }
    }

    public void TableLivreRecherche(String valeur) {
        String[] book = {"ID", "TITRE", "AUTEUR", "PUBLICATION", "ISBN"};
        String[] affichage = new String[6];
        DefaultTableModel model = new DefaultTableModel(null, book);

        try {
            // Préparer la requête SQL en fonction de la valeur de recherche
            String query = "SELECT * FROM livres WHERE auteur LIKE ? OR ISBN LIKE ? OR titre LIKE ? OR anneePublication LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Remplacer les paramètres de la requête par les valeurs de recherche
            statement.setString(1, "%" + valeur + "%");
            statement.setString(2, "%" + valeur + "%");
            statement.setString(3, "%" + valeur + "%");
            statement.setString(4, "%" + valeur + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                affichage[0] = resultSet.getString("id");
                affichage[1] = resultSet.getString("titre");
                affichage[2] = resultSet.getString("auteur");
                affichage[3] = resultSet.getString("anneePublication");
                affichage[4] = resultSet.getString("isbn");
                model.addRow(affichage);
            }

            // Afficher "Not Found" si aucune correspondance n'est trouvée
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Aucun livre trouvé.");
            }

            livre.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage());
        }
    }


    private void button2(ActionEvent e) {
        try {
            // Récupérer l'ID du livre que vous souhaitez modifier
            String id = txtid.getText();

            // Récupérer les nouvelles valeurs des champs de texte
            String titre = txttitre.getText();
            String auteur = txtauteur.getText();
            String publication = txtpublication.getText();
            String isbn = txtisbn.getText();

            // Préparer la requête de mise à jour
            String query = "UPDATE livres SET titre=?, auteur=?, anneePublication=?, ISBN=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Définir les valeurs pour les paramètres de la requête
            statement.setString(1, titre);
            statement.setString(2, auteur);
            statement.setString(3, publication);
            statement.setString(4, isbn);
            statement.setString(5, id);

            // Exécuter la requête de mise à jour
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Livre modifié avec succès !");
                // Mettre à jour la table des livres après la modification
                TableLivre();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la modification du livre.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage());
        }
    }

    private void livreMouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // Vérifier si le clic est effectué avec le bouton gauche de la souris
            int row = livre.getSelectedRow(); // Récupérer l'index de la ligne sélectionnée
            if (row != -1) { // Vérifier si une ligne est sélectionnée
                // Récupérer les valeurs de la ligne sélectionnée
                String id = livre.getValueAt(row, 0).toString();
                String titre = livre.getValueAt(row, 1).toString();
                String auteur = livre.getValueAt(row, 2).toString();
                String publication = livre.getValueAt(row, 3).toString();
                String isbn = livre.getValueAt(row, 4).toString();

                // Afficher les valeurs récupérées dans les champs correspondants de votre interface utilisateur
                txtid.setText(id);
                txttitre.setText(titre);
                txtauteur.setText(auteur);
                txtpublication.setText(publication);
                txtisbn.setText(isbn);
            }
        }
    }

    private void button3(ActionEvent e) {
        int selectedRow = livre.getSelectedRow(); // Récupérer l'index de la ligne sélectionnée
        if (selectedRow != -1) { // Vérifier si une ligne est sélectionnée
            String idToDelete = livre.getValueAt(selectedRow, 0).toString(); // Récupérer l'ID du livre sélectionné
            try {
                String query = "DELETE FROM livres WHERE id = ?"; // Requête SQL pour supprimer le livre
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, idToDelete); // Définir le paramètre de la requête avec l'ID du livre à supprimer
                int rowsDeleted = statement.executeUpdate(); // Exécuter la requête de suppression
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Livre supprimé avec succès !");
                    TableLivre(); // Mettre à jour la table des livres après la suppression
                } else {
                    JOptionPane.showMessageDialog(this, "Aucun livre n'a été supprimé.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur SQL : " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un livre à supprimer.");
        }
    }

    private void button4(ActionEvent e) {
        // Réinitialiser le texte des champs à une chaîne vide
        txtid.setText("");
        txttitre.setText("");
        txtauteur.setText("");
        txtpublication.setText("");
        txtisbn.setText("");

        // Actualiser la table des livres
        TableLivre();

        JOptionPane.showMessageDialog(this, "Table des livres actualisée !");
    }

    private void textField6KeyReleased(KeyEvent e) {
        // Obtenez la valeur saisie dans le champ de recherche


        // Appelez la méthode TableLivreRecherche avec la valeur de recherche
        TableLivreRecherche(txtrecherche.getText());
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Sokhna Diarra Diop
        gestion_livre = new JPanel();
        tete = new JLabel();
        scrollPane1 = new JScrollPane();
        livre = new JTable();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        txtid = new JTextField();
        txttitre = new JTextField();
        txtauteur = new JTextField();
        txtpublication = new JTextField();
        txtisbn = new JTextField();
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label7 = new JLabel();
        txtrecherche = new JTextField();
        button4 = new JButton();

        //======== gestion_livre ========
        {
            gestion_livre.setForeground(new Color(0xccccff));
            gestion_livre.setBackground(new Color(0xccccff));
            gestion_livre.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,gestion_livre. getBorder () ) )
            ; gestion_livre. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;

            //---- tete ----
            tete.setText("GESTION LIVRES");
            tete.setFont(new Font("Century Schoolbook", Font.BOLD, 24));
            tete.setForeground(new Color(0x660066));
            tete.setHorizontalAlignment(SwingConstants.CENTER);

            //======== scrollPane1 ========
            {

                //---- livre ----
                livre.setModel(new DefaultTableModel());
                livre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                livre.setForeground(new Color(0x9900ff));
                livre.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        livreMouseReleased(e);
                    }
                });
                scrollPane1.setViewportView(livre);
            }

            //---- label2 ----
            label2.setText("ID");
            label2.setForeground(new Color(0x330033));
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            label2.setFont(new Font("Tahoma", Font.BOLD, 18));

            //---- label3 ----
            label3.setText("Titre");
            label3.setForeground(new Color(0x330033));
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            label3.setFont(new Font("Tahoma", Font.BOLD, 18));

            //---- label4 ----
            label4.setText("Auteur");
            label4.setForeground(new Color(0x330033));
            label4.setHorizontalAlignment(SwingConstants.RIGHT);
            label4.setFont(new Font("Tahoma", Font.BOLD, 18));

            //---- label5 ----
            label5.setText("Publication");
            label5.setForeground(new Color(0x330033));
            label5.setHorizontalAlignment(SwingConstants.RIGHT);
            label5.setFont(new Font("Tahoma", Font.BOLD, 18));

            //---- label6 ----
            label6.setText("ISBN");
            label6.setForeground(new Color(0x330033));
            label6.setHorizontalAlignment(SwingConstants.RIGHT);
            label6.setFont(new Font("Tahoma", Font.BOLD, 18));

            //---- txtid ----
            txtid.setForeground(new Color(0x330033));
            txtid.setFont(new Font("Tahoma", Font.PLAIN, 16));
            txtid.setDisabledTextColor(new Color(0x330033));

            //---- txttitre ----
            txttitre.setFont(new Font("Tahoma", Font.PLAIN, 16));
            txttitre.setForeground(new Color(0x330033));

            //---- txtauteur ----
            txtauteur.setFont(new Font("Tahoma", Font.PLAIN, 16));
            txtauteur.setForeground(new Color(0x330033));

            //---- txtpublication ----
            txtpublication.setFont(new Font("Tahoma", Font.PLAIN, 16));
            txtpublication.setForeground(new Color(0x330033));

            //---- txtisbn ----
            txtisbn.setFont(new Font("Tahoma", Font.PLAIN, 16));
            txtisbn.setForeground(new Color(0x330033));

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder(""));
                panel1.setBackground(new Color(0x660066));

                //---- button1 ----
                button1.setText("AJOUTER");
                button1.setForeground(Color.white);
                button1.setFont(new Font("Segoe UI", Font.BOLD, 24));
                button1.setBackground(new Color(0x330033));
                button1.addActionListener(e -> button1(e));

                //---- button2 ----
                button2.setText("MODIFIER");
                button2.setForeground(Color.white);
                button2.setFont(new Font("Segoe UI", Font.BOLD, 24));
                button2.setBackground(new Color(0x330033));
                button2.addActionListener(e -> button2(e));

                //---- button3 ----
                button3.setText("SUPPRIMER");
                button3.setForeground(Color.white);
                button3.setFont(new Font("Segoe UI", Font.BOLD, 24));
                button3.setBackground(new Color(0x330033));
                button3.addActionListener(e -> button3(e));

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(button1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(button3)))
                            .addContainerGap())
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(button1)
                            .addGap(18, 18, 18)
                            .addComponent(button2)
                            .addGap(26, 26, 26)
                            .addComponent(button3)
                            .addContainerGap(31, Short.MAX_VALUE))
                );
            }

            //---- label7 ----
            label7.setText("Rechercher");
            label7.setForeground(new Color(0x330033));
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            label7.setFont(new Font("Tahoma", Font.BOLD, 18));

            //---- txtrecherche ----
            txtrecherche.setFont(new Font("Tahoma", Font.PLAIN, 16));
            txtrecherche.setForeground(new Color(0x660066));
            txtrecherche.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    textField6KeyReleased(e);
                }
            });

            //---- button4 ----
            button4.setText("REFRESH");
            button4.setBackground(new Color(0x00cc00));
            button4.setForeground(Color.white);
            button4.setHorizontalTextPosition(SwingConstants.CENTER);
            button4.setFont(new Font("Segoe UI", Font.BOLD, 24));
            button4.addActionListener(e -> button4(e));

            GroupLayout gestion_livreLayout = new GroupLayout(gestion_livre);
            gestion_livre.setLayout(gestion_livreLayout);
            gestion_livreLayout.setHorizontalGroup(
                gestion_livreLayout.createParallelGroup()
                    .addGroup(gestion_livreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gestion_livreLayout.createParallelGroup()
                            .addComponent(tete, GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                            .addGroup(gestion_livreLayout.createSequentialGroup()
                                .addGroup(gestion_livreLayout.createParallelGroup()
                                    .addGroup(gestion_livreLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtpublication, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(gestion_livreLayout.createSequentialGroup()
                                        .addGroup(gestion_livreLayout.createParallelGroup()
                                            .addGroup(gestion_livreLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(gestion_livreLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(gestion_livreLayout.createParallelGroup()
                                            .addComponent(txtid, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtauteur, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txttitre, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(gestion_livreLayout.createSequentialGroup()
                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtisbn, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(gestion_livreLayout.createParallelGroup()
                                    .addGroup(gestion_livreLayout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtrecherche, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(gestion_livreLayout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(button4, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 10, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
            );
            gestion_livreLayout.setVerticalGroup(
                gestion_livreLayout.createParallelGroup()
                    .addGroup(gestion_livreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tete, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gestion_livreLayout.createParallelGroup()
                            .addGroup(gestion_livreLayout.createSequentialGroup()
                                .addGroup(gestion_livreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button4))
                                .addGap(18, 18, 18)
                                .addGroup(gestion_livreLayout.createParallelGroup()
                                    .addGroup(gestion_livreLayout.createSequentialGroup()
                                        .addGroup(gestion_livreLayout.createParallelGroup()
                                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txttitre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(gestion_livreLayout.createParallelGroup()
                                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtauteur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(gestion_livreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtpublication, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(gestion_livreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtisbn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 5, Short.MAX_VALUE))
                            .addGroup(gestion_livreLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(gestion_livreLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtrecherche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
            );
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Sokhna Diarra Diop
    private JPanel gestion_livre;
    private JLabel tete;
    private JScrollPane scrollPane1;
    private JTable livre;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JTextField txtid;
    private JTextField txttitre;
    private JTextField txtauteur;
    private JTextField txtpublication;
    private JTextField txtisbn;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label7;
    private JTextField txtrecherche;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static void main(String[] args) {
        // Créez et affichez votre fenêtre LIVRE ici
        java.awt.EventQueue.invokeLater(() -> {
            new LIVRE().setVisible(true);
        });
    }
}
