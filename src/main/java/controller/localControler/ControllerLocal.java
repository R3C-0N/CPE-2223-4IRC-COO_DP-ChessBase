package controller.localControler;

import controller.AbstractController;
import controller.ChessControllerModel;
import controller.ChessControllerView;
import model.ChessModel;
import model.strategy.factory.concrete.ModelFactory;
import shared.*;
import view.ChessView;

import java.util.LinkedList;
import java.util.List;


/**
 * @author francoise.perrin
 *
 * Ce controleur propage les informations de la view au modèle (demande déplacement pièce...),
 * et du model à la view (qui les propage à son damier et à son afficheur de coups joués)
 * Il dispose ainsi d'une référence vers le model et vers la view
 *
 * Il sait convertir les coordonnées de la view vers celles du model et réciproquement
 *
 * = Mise en oeuvre du DP Mediator
 *
 */
public class ControllerLocal extends AbstractController implements ChessControllerModel, ChessControllerView  {
	private ChessModel chessModel;
	private ChessView chessGUI;

	private GUICoord pieceToMoveCoord;
	private List<GUICoord> listPotentielTargetCoords ;

	public ControllerLocal() {
		super();
		this.listPotentielTargetCoords = null;
	}


	@Override
	public boolean actionsWhenPieceIsSelectedOnGui(PieceSquareColor pieceSquareColor, GUICoord pieceToMoveCoord) {
		boolean ret = false;

		if (this.chessGUI != null && this.chessModel !=null && this.chessModel.isCurrentPlayer(pieceSquareColor)) {

			ret = true;

			// Mise en évidence des cases vers lesquelles
			// la pièce peut être déplacée
			List<ModelCoord> modelCoords = this.chessModel.getPieceListMoveOK(
					CoordToModelCoord(pieceToMoveCoord));
			if (modelCoords != null) {
				this.listPotentielTargetCoords = new LinkedList<GUICoord>();
				for (ModelCoord modelCoord : modelCoords) {
					this.listPotentielTargetCoords.add(ModelCoordToCoord(modelCoord));
				}
				this.chessGUI.resetLight(this.listPotentielTargetCoords, true);
			}
		}
		return ret;
	}

	@Override
	public boolean actionsWhenPieceIsDraggedOnGui(PieceSquareColor pieceSquareColor, GUICoord pieceToMoveCoord) {
		boolean ret = false;

		if (this.chessGUI != null && this.chessModel !=null && this.chessModel.isCurrentPlayer(pieceSquareColor)) {

			ret = true;

			// efface la pièce à déplacer de la view
			this.chessGUI.setPieceToMoveVisible(pieceToMoveCoord, false);

			// fixe la pièce à déplacer  dans le controller
			this.pieceToMoveCoord = pieceToMoveCoord;
		}
		return ret;
	}


	@Override
	public void actionsWhenPieceIsMovedOnGui(GUICoord targetCoord) {

		if (this.chessGUI != null && this.chessModel !=null && this.pieceToMoveCoord != null) {

			ActionType actionType = ActionType.UNKNOWN;

//			// réinitialisation des couleurs d'origines des cases allumées 
//			this.chessGUI.resetLight(this.listPotentielTargetCoords, false);

			// Invoque la methode de deplacement de l'echiquier
			// qui retourne une info s'il est besoin de gérer la promotion du pion
			actionType = this.chessModel.move(
					CoordToModelCoord(this.pieceToMoveCoord),
					CoordToModelCoord(targetCoord)
            );

			// si déplacement OK avec ou sans capture, on déplace et on prend
			// effectivement la pièce sur le damier

			if (ActionType.ILLEGAL.equals(actionType)){
				this.chessGUI.undoMovePiece(this.pieceToMoveCoord);
			}
			else {
				// Déplacement effectif sur view
				this.chessGUI.movePiece(this.pieceToMoveCoord, targetCoord);

				// en cas promotion du pion
				if (ActionType.PROMOTION.equals(actionType) || ActionType.TAKEPROMOTION.equals(actionType)){

					String promotionType = this.chessGUI.getPromotionType();
					this.chessGUI.promotePiece(targetCoord, promotionType);
					this.chessModel.pawnPromotion(CoordToModelCoord(targetCoord), promotionType);
				}

				// l'action est VALIDE et le tour est finis
				this.chessModel.switchJoueur();
			}
			this.pieceToMoveCoord = null;
		}
	}

	@Override
	public void actionsWhenPieceIsReleasedOnGui(GUICoord targetCoord) {

		// réinitialisation des couleurs d'origines des cases allumées
		if (this.chessGUI != null &&  this.listPotentielTargetCoords != null) {
			this.chessGUI.resetLight(this.listPotentielTargetCoords, false);
			this.listPotentielTargetCoords = null;
		}
	}

	@Override
	public void actionWhenGameModeIsChanged(GameMode gameMode) {
		ModelFactory.gameMode.set(gameMode);
	}

	@Override
	public void setView(ChessView chessGUI) {
		this.chessGUI = chessGUI;
	}

	@Override
	public void setModel(ChessModel chessModel) {
		this.chessModel = chessModel;
	}



}
