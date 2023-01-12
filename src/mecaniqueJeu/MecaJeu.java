/**
 * 
 */
package mecaniqueJeu;

import java.util.ArrayList;
import java.util.Scanner;

public class MecaJeu {

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// variables
		int size = 4; // taille du code
		int color = 6; // nbre couleurs possibles
		int count = 0; // compteur essai
		int countMax = 10; // nbre essais max
		boolean win = false; // gagne?

		int[] codeSecret = new int[size];
		int[] essai = new int[size];
		int[] resultat = new int[2];

		codeSecret = tirageCode(size, color, true);
		afficherCode(codeSecret, "CodeSecret");

		while (!win && count < countMax) {
			count++;
			System.out.println("Essai no " + count);
			essai = demanderEssai(size);
			afficherCode(essai, "Essai");
			resultat = analyserEssai(size, codeSecret, essai);
			afficherResultat(resultat);

			if (resultat[0] == size) {
				win = true;
			}
		}
		System.out.println("--------------------------");
		if (win) {
			System.out.println(" GAGNE en " + count + " essais !!!");
		} else {
			System.out.println("PERDU !!!");
			afficherCode(codeSecret, "La solution était : ");
		}

	}

	/**
	 * @param resultat
	 */
	private static void afficherResultat(int[] result) {
		System.out.println("Bien placé = " + result[0] + " / Mal placé = " + result[1]);

	}

	/**
	 * @param taille
	 * @param codeSecret
	 * @param codeEssai
	 * @return int[] ... [0] = bien place / [1] = mal place
	 */
	private static int[] analyserEssai(int taille, int[] codeSecret, int[] codeEssai) {
		int[] result = { 0, 0 };

		int bienPlace = 0;
		int malPlace = 0;

		int[] secretDejaVerifie = new int[taille]; // = 1 si pris en compte, = 0 sinon
		int[] essaiDejaVerifie = new int[taille]; // = 1 si pris en compte, = 0 sinon

		// elements BIEN PLACES
		for (int i = 0; i < taille; i++) {
			if (codeSecret[i] == codeEssai[i]) {
				bienPlace++;
				secretDejaVerifie[i] = 1;
				essaiDejaVerifie[i] = 1;
			} else {
				secretDejaVerifie[i] = 0;
				essaiDejaVerifie[i] = 0;
			}
		}

		// elements MAL PLACES
		// uniquement pour les non pris en compte (dejaVerifie = 0)
		for (int i = 0; i < taille; i++) {
			if (secretDejaVerifie[i] == 0) {
				for (int j = 0; j < taille; j++) {
					if (i != j) { // si i=j  et meme couleur => BIEN PLACE
						if (essaiDejaVerifie[j] == 0 && codeSecret[i] == codeEssai[j]) {
							malPlace++;
							secretDejaVerifie[i] = 1;
							essaiDejaVerifie[j] = 1;
							break;
						}
					}
				}
			}
		}

		result[0] = bienPlace;
		result[1] = malPlace;
		return result;
	}

	/**
	 * Saisie au clavier des entiers/couleurs du code
	 * 
	 * @param taille
	 * @return
	 */
	private static int[] demanderEssai(int taille) {
		int[] array = new int[taille];
		System.out.println("Entrer successivement les 4 chiffres (de 1 à 6) de la combinaison");
		for (int i = 0; i < taille; i++) {
			array[i] = scanner.nextInt();
		}
		return array;
	}

	/**
	 * @param array
	 * @param string
	 */
	private static void afficherCode(int[] array, String string) {
		String str = "";
		for (int nb : array) {
			str += nb + " / ";
		}
		System.out.println(string + " = " + str);
	}

	/**
	 * Tirage code / combinaison secret(e)
	 * 
	 * @param taille
	 * @param nombreCouleur
	 * @param couleurUnique
	 * @return
	 */
	private static int[] tirageCode(int taille, int nombreCouleur, boolean couleurUnique) {
		int[] array = new int[taille];

		ArrayList<Integer> listeCouleur = new ArrayList<>();
		for (int i = 0; i < nombreCouleur; i++) {
			listeCouleur.add(i + 1);
		}
		for (int i = 0; i < taille; i++) {
			int index = nombreAleatoire(listeCouleur.size());
			array[i] = listeCouleur.get(index);
			if (couleurUnique) {
				listeCouleur.remove(index);
			}
		}

		return array;
	}

	/**
	 * Nombre aleatoire entre 0 et (max - 1)
	 * 
	 * @param max
	 * @return
	 */
	private static int nombreAleatoire(int max) {
		return (int) (Math.random() * max);
	}

}
