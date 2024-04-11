import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Création de la bibliothèque
        Bibliotheque bibliotheque = new Bibliotheque();

        // Interface utilisateur
        Scanner scanner = new Scanner(System.in);
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

            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    // Ajouter un livre
                    // Code à ajouter ici
                    break;
                case 2:
                    // Supprimer un livre
                    // Code à ajouter ici
                    break;
                case 3:
                    // Rechercher un livre
                    // Code à ajouter ici
                    break;
                case 4:
                    // Enregistrer l'emprunt d'un livre
                    // Code à ajouter ici
                    break;
                case 5:
                    // Enregistrer le retour d'un livre
                    // Code à ajouter ici
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
        scanner.close();
    }
}

