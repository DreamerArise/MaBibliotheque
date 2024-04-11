import java.util.ArrayList;

public class Utilisateur {
    // Attributs
    private String nom;
    private int idNumber;
    private ArrayList<Livre> livresEmpruntes;

    // Constructeur
    public Utilisateur(String nom, int idNumber) {
        this.nom = nom;
        this.idNumber = idNumber;
        this.livresEmpruntes = new ArrayList<>();
    }

    // Méthode pour emprunter un livre
    public void emprunterLivre(Livre livre) {
        livresEmpruntes.add(livre);
    }

    // Méthode pour retourner un livre
    public void retournerLivre(Livre livre) {
        livresEmpruntes.remove(livre);
    }

    // Méthode pour afficher les livres empruntés par l'utilisateur
    public void afficherLivresEmpruntes() {
        if (livresEmpruntes.isEmpty()) {
            System.out.println("L'utilisateur " + nom + " n'a emprunté aucun livre.");
        } else {
            System.out.println("Livres empruntés par l'utilisateur " + nom + ":");
            for (Livre livre : livresEmpruntes) {
                System.out.println("- " + livre.getTitre() + " par " + livre.getAuteur());
            }
        }
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setNumeroIdentification(int idNumber) {
        this.idNumber = idNumber;
    }

    public ArrayList<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public void setLivresEmpruntes(ArrayList<Livre> livresEmpruntes) {
        this.livresEmpruntes = livresEmpruntes;
    }
}

