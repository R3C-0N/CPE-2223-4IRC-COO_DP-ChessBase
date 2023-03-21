package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import shared.PieceSquareColor;

public class GuiConfig {
	
	public static ObjectProperty<PaintStyle> paintStyle = new SimpleObjectProperty<PaintStyle>();
	public static ObjectProperty<PieceSquareColor> beginColor = new SimpleObjectProperty<PieceSquareColor>();
	public static ObjectProperty<Color> blackSquareColor = new SimpleObjectProperty<Color>();
	public static ObjectProperty<Color> whiteSquareColor = new SimpleObjectProperty<Color>();
	public static ObjectProperty<Color> lightColor = new SimpleObjectProperty<Color>();
	public static IntegerProperty height = new SimpleIntegerProperty();
	public static IntegerProperty width = new SimpleIntegerProperty();
	public static IntegerProperty nbLigne = new SimpleIntegerProperty();
	public static IntegerProperty nbColonne = new SimpleIntegerProperty();

	static {
		GuiConfig.setInitState();
	}

	/**
	 * Cette méthode permet d'initialiser les attributs
	 * Rq : ici les valeurs sont codées en dur mais pourraient/devraient
	 * être importées d'un fichier de config
	 */
	public static void setInitState() {
		GuiConfig.paintStyle.set(PaintStyle.GRADIENT);
		GuiConfig.beginColor.set(PieceSquareColor.WHITE);
		GuiConfig.blackSquareColor.set(Color.rgb(139,69,0,1.0));
		GuiConfig.whiteSquareColor.set(Color.rgb(255,250,240,1.0));
		GuiConfig.lightColor.set(Color.BLUE);
		GuiConfig.height.set(700);
		GuiConfig.width.set(700);
		GuiConfig.nbLigne.set(8);
		GuiConfig.nbColonne.set(8);
	}

	
}
