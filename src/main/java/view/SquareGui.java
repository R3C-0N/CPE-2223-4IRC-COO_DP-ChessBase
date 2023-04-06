package view;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import shared.GUICoord;
import shared.PieceSquareColor;

/**
 * @author francoiseperrin
 * 
 * Classe d'affichage des carrés du damier
 * leur couleur est initialisé par les couleurs par défaut du jeu
 *
 */
class SquareGui extends BorderPane implements ChessSquareGui {

	private PieceSquareColor squareColor;    	// le carré est Noir ou Blanc
	private GUICoord gUICoord;					// les coordonnées du carré sur le damier
	boolean isLight;							// true si on "allume" la case de destination
	private ObjectProperty<Color> backgroundColor = new SimpleObjectProperty<Color>();

	public SquareGui (GUICoord gUICoord, PieceSquareColor squareColor) {
		super();
		this.squareColor = squareColor;
		this.gUICoord = gUICoord;
		this.isLight = false;
		
		// la couleur est définie par les valeurs par défaut de configuration
		if(PieceSquareColor.BLACK.equals(this.squareColor)) {
			this.backgroundColor.bind(GuiConfig.blackSquareColor);
		}
		else {
			this.backgroundColor.bind(GuiConfig.whiteSquareColor);
		}

		// On dessine un carré
		this.paint();
	}

	/**
	 * @return the coord
	 */
	@Override
	public GUICoord getCoord() {
		
		return gUICoord;
	}

	/**
	 * @param isLight
	 * positionne la couleur en fonction du booléen
	 */
	@Override
	public void resetColor(boolean isLight) {
	
		this.isLight = isLight;
		this.paint();
	}

	/**
	 * Permet de redessiner le carré en cas de changement de couleur
	 * dans la factory
	 */
	@Override
	public void paint () {
		
		Color color =  this.isLight ? GuiConfig.lightColor.get() : this.backgroundColor.get();
		
		if(PaintStyle.GRADIENT.equals(GuiConfig.paintStyle.get())) {
			Stop[] stops = new Stop[] { new Stop(0, color), new Stop(1, Color.WHITE)};
			LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
			this.setBackground(new Background(new BackgroundFill(lg1, CornerRadii.EMPTY, Insets.EMPTY)));	
		}
		else {
			this.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		
		this.setBorder(new Border(new BorderStroke(GuiConfig.blackSquareColor.get(), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
	

}