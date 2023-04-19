package view;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 * Cette classe permet de donner une couleur et une image aux pièces
 *
 */
public class PieceGui extends ImageView implements ChessPieceGui{
	
	private PieceSquareColor pieceSquareColor;
	
	public PieceGui(PieceSquareColor pieceSquareColor, Image image ) {
		System.out.println("PieceGui.PieceGui()" + pieceSquareColor + " " + image.getHeight() + " " + image.getWidth());
		this.pieceSquareColor = pieceSquareColor;
		this.setImage(image);
		this.setX(GuiConfig.height.get()/GuiConfig.nbColonne.get()/6);
	}

	@Override
	public PieceSquareColor getCouleur() {
		return pieceSquareColor;
	}

	@Override
	public String toString() {
		return "ChessPieceGUI [couleur=" + pieceSquareColor + ", image=" + getImage() + "]";
	}
	
}
