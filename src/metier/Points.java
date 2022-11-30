package metier;

import java.io.File;
import java.util.Scanner;

public class Points {
	
	public static int[][] tabPoints = new int[26][26];
	public static int[] tabPremierPoint = new int[26];
	public static int[] compteurLettres = new int[26];
	public static int compteurPremier = 0;
	
	
	public static void afficherTabs() {
		System.out.println(" Le tableau des suivants : \n");
		for(int i=0;i<tabPoints.length;i++) {
			for(int j=0;j<tabPoints.length;j++) {
				System.out.print(tabPoints[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n Le tableau de la premiere lettre : \n");
		for(int k=0;k<tabPremierPoint.length;k++) {
			System.out.print(tabPremierPoint[k] + " ");
		}
		System.out.println();
	}
	
	public static int premiereLettre() {
		int plusGrand = 0;
		int elem = -1;
		
		for(int i = 0; i < tabPremierPoint.length; i++) {
			if (tabPremierPoint[i] >= plusGrand) {
				plusGrand = tabPremierPoint[i];
				elem = i;
			}
		}
		return elem;
	}
	
	public static void chargerPrem(int indice) {
		if ( 25 >= indice || indice >= 0) {
			tabPremierPoint[indice] += 1;
			compteurPremier += 1;
		} else System.err.println("probleme de depassement de tableau");
	}
	
	public static void calculPoints(int lettreAv, int lettreAc) {
		if ( 25 >= lettreAv || lettreAv >= 0 || 25 >= lettreAc || lettreAc >= 0) {
			tabPoints[lettreAv][lettreAc] += 1;
			compteurLettres[lettreAv] += 1;
		} else System.err.println("probleme de depassement de tableau");
	}
	
	public static int donnerLettre(int e) {
		int plusGrand = 0;
		int elem = -1;
		
		for(int i = 0; i < tabPoints.length; i++) {
			if (tabPoints[e][i] >= plusGrand) {
				plusGrand = tabPoints[e][i];
				elem = i;
			}
		}
		return elem;
	}
	
	public static int choisirLettre(int e) {
		int pc = (int) (Math.random() * (compteurLettres[e] -1));
		int incrementable = 0;
		if (pc <= 0) pc = 1;
		
		int i = 0;
		while(pc > incrementable && incrementable != compteurLettres[e]) {
			incrementable += tabPoints[e][i];
			i++;
		}
		return i -1;
	}
	
	public static int choisirPrem() {
		int pc = (int) (Math.random() * (compteurPremier - 1));
		int incrementable = 0;
		if (pc <= 0) pc = 1;
		
		for(int i=0;i<tabPremierPoint.length;i++) {
			incrementable += tabPremierPoint[i];
			if (pc >= incrementable - tabPremierPoint[i] && pc <= incrementable) {
				return i;
			}
		}
		return -1;
	}
	
	public static boolean estCompatible(File file, Scanner sc) {
		
		boolean resultat = true;
		
		while(sc.hasNextLine()) {
			String buffer = sc.nextLine();
			for (int i=0;i<buffer.length();i++) {
				int charactere = (int) buffer.charAt(i);
				if( charactere < 65 || charactere > 90 && charactere < 97 || charactere > 122 ) {
					System.out.println(buffer.charAt(i) + " = " + charactere);
					resultat = false;
				}
			}
		}
		return resultat;
	}
}




