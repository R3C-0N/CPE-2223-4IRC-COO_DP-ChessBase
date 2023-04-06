package view;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.image.Image;
import shared.GUICoord;
import shared.PieceSquareColor;
import view.decorator.SquareGuiDecorator;

/**
 * @author francoise.perrin
 * fabriques des cases et des pièces de la view
 *
 */
public class GuiFactory {

	public static ChessSquareGui createSquare(int col, int ligne) {
		ChessSquareGui square = null;
		PieceSquareColor squareColor;
		if((col%2==0 && ligne%2==0) || (col%2!=0 && ligne%2!=0)){
			squareColor = PieceSquareColor.BLACK;
		}
		else{
			squareColor = PieceSquareColor.WHITE;
		}
		GUICoord gUICoord = new GUICoord(col, ligne);
		
		/* Version sans Decorator avec ou sans Observer  */
//		square = new SquareGui(gUICoord, squareColor);
		
		/* Version avec Decorator et Observer */
		square = new SquareGuiDecorator(new SquareGui(gUICoord, squareColor));

		return square;
	}
	
	public static ChessPieceGui createPiece(int col, int ligne) {
		return PieceGUIFactory.createChessPieceGUI(new GUICoord(col, ligne) );
	}
	
	public static ChessPieceGui createPiece(String promotionType, PieceSquareColor pieceSquareColor) {
		return PieceGUIFactory.createChessPieceGUI(promotionType, pieceSquareColor);
	}
}

/**
 * @author francoise.perrin
 * Fabrique spécifique des PieceGui
 */
class PieceGUIFactory {

	private static Map<String, List<GUICoord>> mapPieceInitCoords = new HashMap<String, List<GUICoord>>();
	private static Map<String, String> mapPieceImage = new HashMap<String, String> ();
	private static Map<PieceSquareColor, List<String>> mapPieceColor = new HashMap<PieceSquareColor, List<String>> ();

	static {	
		mapPieceInitCoords.put("TourWHITE", Arrays.asList(new GUICoord[] {new GUICoord(0,7), new GUICoord(7,7)}));
		mapPieceInitCoords.put("CavalierWHITE", Arrays.asList(new GUICoord[] {new GUICoord(1,7), new GUICoord(6,7)}));
		mapPieceInitCoords.put("FouWHITE", Arrays.asList(new GUICoord[] {new GUICoord(2,7), new GUICoord(5,7)}));
		mapPieceInitCoords.put("ReineWHITE", Arrays.asList(new GUICoord[] {new GUICoord(3,7)}));
		mapPieceInitCoords.put("RoiWHITE", Arrays.asList(new GUICoord[] {new GUICoord(4,7)}));
		mapPieceInitCoords.put("PionWHITE", Arrays.asList(new GUICoord[] {new GUICoord(0,6), new GUICoord(1,6), new GUICoord(2,6), new GUICoord(3,6),
				new GUICoord(4,6),  new GUICoord(5,6), new GUICoord(6,6), new GUICoord(7,6)}));
		mapPieceInitCoords.put("TourBLACK", Arrays.asList(new GUICoord[] {new GUICoord(0,0), new GUICoord(7,0)}));
		mapPieceInitCoords.put("CavalierBLACK", Arrays.asList(new GUICoord[] {new GUICoord(1,0), new GUICoord(6,0)}));
		mapPieceInitCoords.put("FouBLACK", Arrays.asList(new GUICoord[] {new GUICoord(2,0), new GUICoord(5,0)}));
		mapPieceInitCoords.put("ReineBLACK", Arrays.asList(new GUICoord[] {new GUICoord(3,0)}));
		mapPieceInitCoords.put("RoiBLACK", Arrays.asList(new GUICoord[] {new GUICoord(4,0)}));
		mapPieceInitCoords.put("PionBLACK", Arrays.asList(new GUICoord[] {new GUICoord(0,1), new GUICoord(1,1), new GUICoord(2,1), new GUICoord(3,1),
				new GUICoord(4,1), new GUICoord(5,1), new GUICoord(6,1), new GUICoord(7,1)}));
	}

	static {	
		mapPieceImage.put("TourWHITE", "tourBlancS.png");
		mapPieceImage.put("CavalierWHITE", "cavalierBlancS.png");
		mapPieceImage.put("FouWHITE",  "fouBlancS.png");
		mapPieceImage.put("ReineWHITE", "reineBlancS.png");
		mapPieceImage.put("RoiWHITE", "roiBlancS.png");
		mapPieceImage.put("PionWHITE", "pionBlancS.png");

		mapPieceImage.put("TourBLACK", "tourNoireS.png");
		mapPieceImage.put("CavalierBLACK", "cavalierNoirS.png");
		mapPieceImage.put("FouBLACK", "fouNoirS.png");
		mapPieceImage.put("ReineBLACK", "reineNoireS.png");
		mapPieceImage.put("RoiBLACK", "roiNoirS.png");
		mapPieceImage.put("PionBLACK", "pionNoirS.png")  ;
	}

	static {	
		mapPieceColor.put(PieceSquareColor.WHITE, Arrays.asList(new String[] {"TourWHITE","CavalierWHITE","FouWHITE","ReineWHITE","RoiWHITE","PionWHITE"}));
		mapPieceColor.put(PieceSquareColor.BLACK,Arrays.asList(new String[] {"TourBLACK","CavalierBLACK","FouBLACK","ReineBLACK","RoiBLACK","PionBLACK"}));
	}
	

	/**
	 * private pour ne pas instancier d'objets
	 */
	private PieceGUIFactory() {

	}	

	public static ChessPieceGui createChessPieceGUI(GUICoord gUICoord) {
		ChessPieceGui chessPieceGUI = null;
		Image image = null;
		PieceSquareColor pieceSquareColor = null;
		
		// Recherche type de la piece aux coordonnées coord
		Set<String> pieceTypeSet = mapPieceInitCoords.keySet();
		for (String pieceType : pieceTypeSet) {
			List<GUICoord> listCoords = mapPieceInitCoords.get(pieceType);
			if (listCoords!= null && listCoords.contains(gUICoord)) {
				
				// recheche de la couleur correspondante
				pieceSquareColor = getCouleur(pieceType);
				
				// recherche du fichier de l'image correspondante et fabrication image
				image = createImage(pieceType);		
				
				// création pièce
				chessPieceGUI = new PieceGui( pieceSquareColor,  image );
			}
		}
		
		return chessPieceGUI;
	}

	public static ChessPieceGui createChessPieceGUI(String promotionType, PieceSquareColor pieceSquareColor) {
		return new PieceGui(pieceSquareColor, createImage(promotionType + pieceSquareColor.name()) );
	}

	/**
	 * Get image for a given piece type
	 * @param pieceType
	 * @return matching image from resources, or {@literal null} if not found
	 */
	private static Image createImage(String pieceType) {
		
		Image image = null;
		String pieceImageFile = null, nomImageFile = null;
		
		nomImageFile = mapPieceImage.get(pieceType);
		
		pieceImageFile = "images/" + nomImageFile; // attention au chemin
		InputStream imageInputStream = PieceGUIFactory.class.getResourceAsStream(pieceImageFile);
		if (imageInputStream!=null) {
			image = new Image(imageInputStream);
		} else {
			System.err.printf("Warning: GuiFactory couldn't load image resource at given path \"%s\"\n", pieceImageFile);
		}
		return image;
	}
	
	private static PieceSquareColor getCouleur(String pieceType) {
		PieceSquareColor couleurPiece = null;
		
		Set<PieceSquareColor> couleurSet = mapPieceColor.keySet();
		for (PieceSquareColor pieceSquareColor : couleurSet) {	
			List<String> listPieceType = mapPieceColor.get(pieceSquareColor);
			if (listPieceType!= null && listPieceType.contains(pieceType)) {
				couleurPiece = pieceSquareColor;
			}
		}
		return couleurPiece;
	}
	

}

