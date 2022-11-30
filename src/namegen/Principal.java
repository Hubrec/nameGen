package nameGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import metier.Points;

public class Principal {
	
	private static Scanner sc;
	private static int taillePrenom = 5;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		boolean fin = false;
		
		try
		{
			File file = new File("ressources/nameLists/default_15000.txt"); // ici on met le fichier en entrée que l'on veut utiliser comme données pour nourrir notre algo
			sc = new Scanner(file);
			
			if ( Points.estCompatible(file, sc)) {
				sc = new Scanner(file);
				while(sc.hasNextLine()) {
					String buffer = sc.nextLine();
					char premLettre = buffer.charAt(0);
					int entier = premLettre;
					entier -= 65;
					Points.chargerPrem(entier);
					char y = buffer.charAt(1);
					int intY = y;
					intY -= 97;
					Points.calculPoints(entier, intY);
					
					for (int i=2;i<buffer.length();i++) {
						char lettreAv = buffer.charAt(i-1);
						char lettreAc = buffer.charAt(i);
						int entLettreAv = lettreAv;
						int entLettreAc = lettreAc;
						entLettreAv -= 97;
						entLettreAc -= 97;
						
						Points.calculPoints(entLettreAv, entLettreAc);
					}
				}	
			}
			else System.err.println(" Probleme de fichier corrompu");
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
                
                System.out.println("\nCeci est un programme qui va generer des prenoms");
                System.out.println("\nA savoir que l'interprétation de ces noms est parfaitement arbitraire et ne parlera pas a tout le monde");
                System.out.println("Il existe quelques commandes : ");
                System.out.println("    gen => generer un prenom");
                System.out.println("    gen+ => generer plein de prenoms");
                System.out.println("    ultime => genere le nom le plus probable");
                System.out.println("    taille => changer la taille");
                System.out.println("    tab => afficher les tableaux de valeurs (debug)");
                System.out.println("    alt => afficher de nouveau les commandes");
		
		while(!fin) {

			System.out.print(" => ");
			sc = new Scanner(System.in);
			String choix = sc.nextLine();

			int genRes = choix.compareTo("gen");
			int genPlusRes = choix.compareTo("gen+");
			int ultimeRes = choix.compareTo("ultime");
			int tabRes = choix.compareTo("tab");
			int changeTailleRes = choix.compareTo("taille");
                        int afficherConsignes = choix.compareTo("alt");
			
			if (genRes == 0) {
				String prenom = nouveauPrenom(taillePrenom);
				
				System.out.println("/-------------------------------------\\");
				System.out.println("| Le prenom genere est : " + prenom);
				System.out.println("\\-------------------------------------/");
			}
			else if (changeTailleRes == 0) {
				
				System.out.print("Entrez la nouvelle taille du prenom => ");
				int taille = sc.nextInt();
				
				taillePrenom = taille;
				
			}
			else if (genPlusRes == 0) {
				
				System.out.print("Min de la taille du prenom => ");
				int min = sc.nextInt();
				
				System.out.print("Max de la taille du prenom => ");
				int max = sc.nextInt();
				
				System.out.print("Combien de prenoms generer => ");
				int nbr = sc.nextInt();
				String nth = sc.nextLine();
				
				System.out.print("Creer un fichier texte pour les ranger (X for no file) => ");
				String fichierRangement = sc.nextLine();
				
				if (fichierRangement.compareTo("X") == 0) {
					for (int i=0;i<nbr;i++) {
						int taille = (int) (min + (Math.random() * (max-min)));
						String prenom = nouveauPrenom(taille);
						
						System.out.println("/-------------------------------------\\");
						System.out.println("| Le prenom genere est : " + prenom);
						System.out.println("\\-------------------------------------/\n");
					}
				}
				else {
					File f = new File("./nameLists/" + fichierRangement + ".txt");
					
					PrintWriter writer = new PrintWriter(fichierRangement + ".txt");
					
					for (int i=0;i<nbr;i++) {
						int taille = (int) (min + (Math.random() * (max-min)));
						
						String prenom = nouveauPrenom(taille);
						writer.println(prenom);
						
						//System.out.println("/-------------------------------------\\");
						//System.out.println("| Le prenom genere est : " + prenom);
						//System.out.println("\\-------------------------------------/");
					}
					writer.close();		
                                        System.out.println("//------------------------------------------\\");
                                        System.out.println("| La liste de prénoms a été téléchargée !!   |");
                                        System.out.println("\\------------------------------------------//");
				}
			}
			else if (ultimeRes == 0) {
				
				
				String leMot = "";
				int res = Points.premiereLettre();		
				char resChar = (char) (res + 65);
				leMot += resChar;
				
				for (int i = 0; i < taillePrenom -1; i++) {
					res = Points.donnerLettre(res);
					resChar = (char) (res + 97);
					leMot += resChar;
				}
				
				System.out.println(leMot);
			}
			else if (tabRes == 0) {
				Points.afficherTabs();
			} else if (afficherConsignes == 0) {
                            System.out.println("    gen => generer un prenom");
                            System.out.println("    gen+ => generer plein de prenoms");
                            System.out.println("    ultime => genere le nom le plus probable");
                            System.out.println("    taille => changer la taille");
                            System.out.println("    tab => afficher les tableaux de valeurs (debug)");
                            System.out.println("    alt => afficher de nouveau les commandes");
                        }
			else System.err.println("Erreur de syntaxe");
		}
	}
	
	public static void importer(String nomFic) {
		
	}
	
	public static String nouveauPrenom(int taille) {
		
		String prenom = "";
		int res = Points.choisirPrem();
		char resChar = (char) (res + 65);
		prenom += resChar;
		
		for (int i = 0; i < taille -1; i++) {
			res = Points.choisirLettre(res);
			resChar = (char) (res + 97);
			prenom += resChar;
		}
		
		return prenom;
	}
}
