import Systeme.ConnexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Bibliotheque {
    // Attributs
    private ArrayList<Livre> listeLivres;
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;



    // Constructeur
    public Bibliotheque() {
        listeLivres = new ArrayList<>();
        empruntsUtilisateurs = new HashMap<>();

    }

    // Méthode pour ajouter un livre à la bibliothèque
    public void ajouterLivre(int idLivre, String titreLivre, String auteurLivre, int anneePublication, String isbn) throws SQLException {
        // Connexion à la base de données
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnexionDB.getConnection(); // Utilisez votre méthode de connexion à la base de données
            String sqlLivre = "INSERT INTO livres (id, titre, auteur, anneePublication, ISBN) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sqlLivre);
            statement.setInt(1, idLivre);
            statement.setString(2, titreLivre);
            statement.setString(3, auteurLivre);
            statement.setInt(4, anneePublication);
            statement.setString(5, isbn);
            statement.executeUpdate();

            System.out.println("Livre ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions
        } finally {
            // Fermer la connexion et le statement
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Méthode pour supprimer un livre de la bibliothèque
    public void supprimerLivre(int idLivre) throws SQLException {
        // Connexion à la base de données
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnexionDB.getConnection(); // Utilisez votre méthode de connexion à la base de données
            String sql = "DELETE FROM livres WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idLivre);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Livre supprimé avec succès.");
            } else {
                System.out.println("Aucun livre trouvé avec l'ID spécifié.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions
        } finally {
            // Fermer la connexion et le statement
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Méthode pour rechercher un livre par titre, auteur ou ISBN
// Méthode pour rechercher un livre par titre, auteur ou ISBN dans la base de données
    public void rechercherLivre(String critere) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnexionDB.getConnection(); // Utilisez votre méthode de connexion à la base de données
            String sql = "SELECT * FROM livres WHERE titre LIKE ? OR auteur LIKE ? OR ISBN LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + critere + "%");
            statement.setString(2, "%" + critere + "%");
            statement.setString(3, "%" + critere + "%");
            resultSet = statement.executeQuery();

            // Afficher les livres trouvés
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                int anneePublication = resultSet.getInt("anneePublication");
                String isbn = resultSet.getString("ISBN");
                System.out.println("ID: " + id + ", Titre: " + titre + ", Auteur: " + auteur + ", Année de publication: " + anneePublication + ", ISBN: " + isbn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions
        } finally {
            // Fermer la connexion, le statement et le resultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Une HashMap est utilisée dans la classe Bibliotheque pour suivre les emprunts des utilisateurs.
    // Méthode pour enregistrer l'emprunt d'un livre par un utilisateur
    public void enregistrerEmprunt(int idEmprunt, int idLivre, Date dateEmprunt, Date dateRetourPrevue) throws SQLException {
        // Connexion à la base de données
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnexionDB.getConnection(); // Utilisez votre méthode de connexion à la base de données
            String sql = "INSERT INTO emprunts (id_emprunt, id_livre, date_emprunt, date_retour_prevue) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idEmprunt);
            statement.setInt(2, idLivre);
            statement.setDate(3, new java.sql.Date(dateEmprunt.getTime()));
            statement.setDate(4, new java.sql.Date(dateRetourPrevue.getTime()));
            statement.executeUpdate();

            System.out.println("Emprunt enregistré avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions
        } finally {
            // Fermer la connexion et le statement
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Méthode pour enregistrer le retour d'un livre par un utilisateur
    public void enregistrerRetourEmprunt(int idEmprunt, Date dateRetourEffectif) throws SQLException {
        // Connexion à la base de données
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnexionDB.getConnection(); // Utilisez votre méthode de connexion à la base de données
            String sql = "UPDATE emprunts SET dateRetourEffectif = ? WHERE idEmprunt = ?";
            statement = connection.prepareStatement(sql);
            statement.setDate(1, dateRetourEffectif);
            statement.setInt(2, idEmprunt);
            statement.executeUpdate();

            System.out.println("Retour de livre enregistré avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions
        } finally {
            // Fermer la connexion et le statement
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void afficherStatistiques() {
        // Connexion à la base de données
        Connection connection = null;
        PreparedStatement livreStatement = null;
        PreparedStatement empruntStatement = null;
        ResultSet livreResultSet = null;
        ResultSet empruntResultSet = null;

        try {
            connection = ConnexionDB.getConnection(); // Utilisez votre méthode de connexion à la base de données

            // Nombre total de livres
            String livreSql = "SELECT COUNT(*) AS totalLivres FROM livres";
            livreStatement = connection.prepareStatement(livreSql);
            livreResultSet = livreStatement.executeQuery();
            livreResultSet.next();
            int totalLivres = livreResultSet.getInt("totalLivres");

            // Nombre total de livres empruntés
            String empruntSql = "SELECT COUNT(*) AS totalEmprunts FROM emprunts";
            empruntStatement = connection.prepareStatement(empruntSql);
            empruntResultSet = empruntStatement.executeQuery();
            empruntResultSet.next();
            int totalEmprunts = empruntResultSet.getInt("totalEmprunts");

            // Affichage des statistiques
            System.out.println("Statistiques de la bibliothèque :");
            System.out.println("Nombre total de livres : " + totalLivres);
            System.out.println("Nombre total de livres empruntés : " + totalEmprunts);

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les exceptions
        } finally {
            // Fermer la connexion, le statement et le resultSet
            try {
                if (livreResultSet != null) {
                    livreResultSet.close();
                }
                if (empruntResultSet != null) {
                    empruntResultSet.close();
                }
                if (livreStatement != null) {
                    livreStatement.close();
                }
                if (empruntStatement != null) {
                    empruntStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour vérifier l'éligibilité d'un utilisateur à emprunter un livre
    public boolean verifierEligibilite(Utilisateur utilisateur) {
        // Vérifie si l'utilisateur a déjà emprunté le maximum de livres permis (par exemple, 3 livres)
        return empruntsUtilisateurs.getOrDefault(utilisateur, new ArrayList<>()).size() < 3;
    }


    // Méthode pour afficher les statistiques de la bibliothèque


    public void ajouterLivre(Bibliotheque bibliotheque) {
    }
}

