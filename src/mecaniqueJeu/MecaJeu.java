/**
 * 
 */
package mecaniqueJeu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author steph
 *
 */
public class MecaJeu {

	public static Scanner scanner = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// variables
		int size = 4;
		int color = 6;

		int[] codeSecret = new int[size];
		int[] essai = new int[size];
		int[] resultat = new int[2];

		codeSecret = tirageCode(size, color, true);
		afficherCode(codeSecret, "CodeSecret");

		essai = demanderEssai(size);
		afficherCode(essai, "Essai");

		resultat = analyserEssai(size, codeSecret, essai);
	}

	/**
	 * @param taille
	 * @param codeSecret
	 * @param codeEssai
	 * @return int[] ... [0] = bien place / [1] = mal place
	 */
	private static int[] analyserEssai(int size, int[] codeSecret, int[] codeEssai) {

		return null;
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
