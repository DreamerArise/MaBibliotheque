import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Création de la bibliothèque
        Bibliotheque bibliotheque = new Bibliotheque();

        // Interface utilisateur
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.println("Menu principal :");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Supprimer un livre");
            System.out.println("3. Rechercher un livre");
            System.out.println("4. Enregistrer l'emprunt d'un livre");
            System.out.println("5. Enregistrer le retour d'un livre");
            System.out.println("6. Afficher les statistiques de la bibliothèque");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix : ");

            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Entrez les informations du livre :");
                    System.out.println("ID du livre :");
                    int idLivre = sc.nextInt();
                    sc.nextLine(); // pour consommer le retour à la ligne restant dans le buffer

                    System.out.println("Titre du livre :");
                    String titreLivre = sc.nextLine();

                    System.out.println("Auteur du livre :");
                    String auteurLivre = sc.nextLine();

                    int anneePublication;
                    while (true) {
                        System.out.println("Année de publication du livre :");
                        if (sc.hasNextInt()) {
                            anneePublication = sc.nextInt();
                            break;
                        } else {
                            System.out.println("Veuillez entrer une année valide.");
                            sc.next(); // consommer l'entrée invalide pour éviter une boucle infinie
                        }
                    }

                    System.out.println("ISBN du livre :");
                    String isbn = sc.next();

                    try {
                        bibliotheque.ajouterLivre(idLivre, titreLivre, auteurLivre, anneePublication, isbn);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erreur lors de l'ajout du livre.");
                    }
                    break;


                case 2:
                    System.out.println("Entrez l'ID du livre à supprimer :");
                    int idLivreASupprimer = sc.nextInt();
                    try {
                        bibliotheque.supprimerLivre(idLivreASupprimer);
                        System.out.println("Livre supprimé avec succès.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erreur lors de la suppression du livre.");
                    }
                    break;
                case 3:
                    // Rechercher un livre
                    System.out.println("Entrez le critère de recherche (titre, auteur ou ISBN) :");
                    String critereRecherche = sc.next();
                    System.out.println("Donner le "+ critereRecherche+ "du livre recherche");
                    String recherche = sc.next();

                    // Afficher les résultats de la recherche
                    try {
                        bibliotheque.rechercherLivre(recherche);
                        System.out.println("Livre trouvé");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erreur lors de la recherche du livre.");
                    }
                    break;



                case 4:
                    // Enregistrer l'emprunt d'un livre
                    System.out.println("Entrez les informations de l'emprunt:");
                    System.out.println("ID du livre :");
                    int idLivreEmprunt = sc.nextInt();
                    sc.nextLine(); // pour consommer le retour à la ligne restant dans le buffer

                    System.out.println("ID de l'emprunt :");
                    int idEmprunt = sc.nextInt();
                    sc.nextLine(); // pour consommer le retour à la ligne restant dans le buffer

                    System.out.println("Date de l'emprunt (format : yyyy-mm-dd) :");
                    String dateEmpruntStr = sc.nextLine();
                    Date dateEmprunt = Date.valueOf(dateEmpruntStr);

                    System.out.println("Date de retour prévue (format : yyyy-mm-dd) :");
                    String dateRetourPrevuStr = sc.nextLine();
                    Date dateRetourPrevu = Date.valueOf(dateRetourPrevuStr);

                    try {
                        bibliotheque.enregistrerEmprunt(idEmprunt, idLivreEmprunt, dateEmprunt, dateRetourPrevu);
                        System.out.println("Emprunt enregistré avec succès.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erreur lors de l'enregistrement de l'emprunt.");
                    }
                    break;
                case 5:
                    // Enregistrer le retour d'un livre
                    System.out.println("Entrez les informations de retour d'emprunt:");
                    System.out.println("ID de l'emprunt :");
                    int idEmpruntRetour = sc.nextInt();
                    sc.nextLine(); // pour consommer le retour à la ligne restant dans le buffer

                    System.out.println("Date de retour effective (format : yyyy-mm-dd) :");
                    String dateRetourEffectifStr = sc.nextLine();
                    Date dateRetourEffectif = Date.valueOf(dateRetourEffectifStr);

                    try {
                        bibliotheque.enregistrerRetourEmprunt(idEmpruntRetour, dateRetourEffectif);
                        System.out.println("Retour d'emprunt enregistré avec succès.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erreur lors de l'enregistrement du retour d'emprunt.");
                    }
                    break;
                case 6:
                    // Afficher les statistiques de la bibliothèque
                    bibliotheque.afficherStatistiques();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);

        // Fermeture du scanner
        sc.close();
    }
}

