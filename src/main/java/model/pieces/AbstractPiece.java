package model.pieces;

import model.strategy.adapter.ChessPieceAdapter;
import model.strategy.adapter.IChessPieceAdaptor;
import model.strategy.factory.astract.MovementStrategyFactory;
import model.strategy.factory.concrete.ClassicMovementStategyFactory;
import model.strategy.factory.concrete.ModelFactory;
import model.strategy.factory.concrete.TempestMovementStategyFactory;
import model.strategy.movementStrategy.abstractMovementStrategy.MovementStrategy;
import shared.ActionType;
import shared.GameMode;
import shared.ModelCoord;
import shared.PieceSquareColor;

import java.util.List;



/**
 * @author francoise.perrin - Alain BECKER
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD
 * 
 * Gère le comportement commun à toutes les pièces
 * Chaque classe dérivée (Pion, etc.) sera capable de dire 
 * si le déplacement est OK.
 * 
 * 
 * 
 * ATTENTION, DANS LA VERSION PRECEDENTE,
 * TOUS LES ALGOS SUR LES PIECES CONSIDERAIENT DES COORDONNEES x et y DE TYPE int
 * L'INTRODUCTION DES Coord SERT POUR L'INTERFACE AVEC LE ChessImplementor
 * MAIS EN INTERNE, LES COORDONÉES RESTENT IMPLEMENTÉES AVEC x et y 
 * 
 * 
 * 
 */
public abstract class  AbstractPiece implements ChessPieceModel {

	private int x, y;
	private PieceSquareColor pieceColor;
	private boolean hasMoved;
	private boolean hasMovedBeforeMove;

	/** Constante représentant l'absence d'"état antérieur" pour une pièce **/
	private static final int THERE_IS_NO_BEFORE = -1;
	/** Constante représentant l'état "capturé" d'une pièce **/
	private static final int CAPTURED = -1;

	private int xBeforeCapture = THERE_IS_NO_BEFORE;
	private int yBeforeCapture = THERE_IS_NO_BEFORE;
	private int xBeforeMove = THERE_IS_NO_BEFORE;
	private int yBeforeMove = THERE_IS_NO_BEFORE;


	/**
	 * @param pieceSquareColor
	 * @param coord
	 */
	public AbstractPiece(PieceSquareColor pieceSquareColor, ModelCoord coord){
		super();
		this.x = coord.getCol() - 'a';
		this.y = 8 - coord.getLigne();
		this.pieceColor=pieceSquareColor;
		this.hasMoved = false;
		this.hasMovedBeforeMove = false;
	}

	@Override
	public boolean hasThisCoord(ModelCoord modelCoord) {
		int x = modelCoord.getCol() - 'a';
		int y = 8 - modelCoord.getLigne();
		return this.x == x && this.y == y;
	}

	@Override
	public boolean hasThisColor(PieceSquareColor pieceColor) {
		return this.pieceColor == pieceColor;
	}

	@Override
	public char getCol() {
		return (char)(this.x + 'a');
	}

	@Override
	public int getLigne() {
		return this.y - 8;
	}


	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public boolean hasMoved() {
		return this.hasMoved;
	}

	@Override
	public ActionType doMove (ModelCoord coord){
		ActionType isMoveOk = ActionType.UNKNOWN;

		if(ModelFactory.coordonnees_valides(coord)){    // vérifie aussi que coord != null
			this.xBeforeMove = this.x;
			this.yBeforeMove = this.y; // Mémorisation pour undoLastMove()
			this.x = coord.getCol() - 'a';
			this.y = 8 - coord.getLigne();
			isMoveOk = ActionType.MOVE;

			this.hasMovedBeforeMove = this.hasMoved;
			this.hasMoved = true;
		}
		return isMoveOk;
	}

	@Override
	public boolean undoLastMove(){
		boolean ret = false;
		if ( (this.x == THERE_IS_NO_BEFORE) || (this.y == THERE_IS_NO_BEFORE)) {
			throw new IllegalStateException("Tentative d'annulation de mouvement incohérente!");
		}
		this.x=this.xBeforeMove;
		this.y=this.yBeforeMove;
		this.xBeforeMove = this.yBeforeMove = THERE_IS_NO_BEFORE;

		this.hasMoved = this.hasMovedBeforeMove;
		return ret;
	}

	@Override
	public boolean catchPiece(){

		if ( (this.x == CAPTURED) || (this.y == CAPTURED)) {
			throw new IllegalStateException("Tentative de capture d'une pièce déjà capturée ?!");
		}
		this.xBeforeCapture = this.x;
		this.yBeforeCapture = this.y; // Mémorisation pour undoCaptured()
		this.x = CAPTURED;
		this.y = CAPTURED;
		return true;
	}

	@Override
	public final boolean undoLastCatch(){
		if ( (this.x != CAPTURED) || (this.y != CAPTURED)) {
			throw new IllegalStateException("Tentative de restauration d'une pièce non capturée ?!");
		}
		this.x = this.xBeforeCapture;
		this.y = this.yBeforeCapture;
		this.xBeforeCapture = this.yBeforeCapture = THERE_IS_NO_BEFORE;
		return true;
	}

	@Override
	public String toString(){
		String S = (this.getClass().getSimpleName()).substring(0, 2)
				+ " " + (char)(this.x+'a') + " " + (8-this.y);
		return S;
	}

	@Override
	public boolean isAlgoMoveOk(ModelCoord finalCoord, ActionType type){
		boolean ret = false;

		IChessPieceAdaptor chessPieceAdapter = new ChessPieceAdapter(this);
		MovementStrategyFactory movementStrategyFactory;
		if(ModelFactory.gameMode.get() == GameMode.NORMAL){
			movementStrategyFactory = ClassicMovementStategyFactory.getInstance();
		} else {
			movementStrategyFactory = TempestMovementStategyFactory.getInstance();
		}
		MovementStrategy movementStrategy = movementStrategyFactory.createMovementStrategy(chessPieceAdapter);

		return movementStrategy.isMoveOk(
				this.getX(),
				this.getY(),
				finalCoord.getCol() - 'a',
				8 - finalCoord.getLigne(),
				this.hasMoved, type
		);
	}

	@Override
	public abstract List<ModelCoord> getMoveItinerary(ModelCoord finalCoord);


	protected int getX(){
		return this.x;
	}

	protected int getY(){
		return this.y;
	}
}

