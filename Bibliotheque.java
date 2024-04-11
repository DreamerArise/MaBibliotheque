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
    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
    }

    // Méthode pour supprimer un livre de la bibliothèque
    public void supprimerLivre(Livre livre) {
        listeLivres.remove(livre);
    }

    // Méthode pour rechercher un livre par titre, auteur ou ISBN
    public Livre rechercherLivre(String critere) {
        for (Livre livre : listeLivres) {
            if (livre.getTitre().equals(critere) || livre.getAuteur().equals(critere) || livre.getISBN().equals(critere)) {
                return livre;
            }
        }
        return null; // Retourne null si aucun livre correspondant n'est trouvé
    }

    // Une HashMap est utilisée dans la classe Bibliotheque pour suivre les emprunts des utilisateurs.
    // Méthode pour enregistrer l'emprunt d'un livre par un utilisateur
    public void enregistrerEmprunt(Utilisateur utilisateur, Livre livre) {
        ArrayList<Livre> emprunts = empruntsUtilisateurs.getOrDefault(utilisateur, new ArrayList<>());
        emprunts.add(livre);
        empruntsUtilisateurs.put(utilisateur, emprunts);
    }

    // Méthode pour enregistrer le retour d'un livre par un utilisateur
    public void enregistrerRetour(Utilisateur utilisateur, Livre livre) {
        ArrayList<Livre> emprunts = empruntsUtilisateurs.getOrDefault(utilisateur, new ArrayList<>());
        emprunts.remove(livre);
        empruntsUtilisateurs.put(utilisateur, emprunts);
    }

    // Méthode pour vérifier l'éligibilité d'un utilisateur à emprunter un livre
    public boolean verifierEligibilite(Utilisateur utilisateur) {
        // Vérifie si l'utilisateur a déjà emprunté le maximum de livres permis (par exemple, 3 livres)
        return empruntsUtilisateurs.getOrDefault(utilisateur, new ArrayList<>()).size() < 3;
    }


    // Méthode pour afficher les statistiques de la bibliothèque
    public void afficherStatistiques() {
        int nombreTotalLivres = listeLivres.size();
        int nombreExemplairesEmpruntes = 0;

        // Calcul du nombre total d'exemplaires empruntés
        for (ArrayList<Livre> emprunts : empruntsUtilisateurs.values()) {
            nombreExemplairesEmpruntes += emprunts.size();
        }

        // Affichage des statistiques
        System.out.println("Statistiques de la bibliothèque :");
        System.out.println("Nombre total de livres : " + nombreTotalLivres);
        System.out.println("Nombre d'exemplaires empruntés : " + nombreExemplairesEmpruntes);
        // Vous pouvez ajouter d'autres statistiques au besoin
    }
}

