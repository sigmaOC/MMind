/**
 * 
 */
package mecaniqueJeu;

import java.util.ArrayList;

/**
 * @author steph
 *
 */
public class MecaJeu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// variables
		int size = 4;
		int color = 6;

		int[] codeSecret = new int[size];

		codeSecret = tirageCode(size, color, true);
		afficherCode(codeSecret, "CodeSecret");

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
