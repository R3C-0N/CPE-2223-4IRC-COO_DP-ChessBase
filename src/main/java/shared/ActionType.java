package shared;


public enum  ActionType { 
	// ------------------------------------------------------- coups anormaux
	/** coup non encore évalué (valeur par défaut) */
	UNKNOWN,
	/** coup évalué comme illégal */
	ILLEGAL,
	// ------------------------------------------------------- coups normaux
	/** coup normal de déplacement (sans prise) */
	MOVE,
	/** coup normal de prise */
	TAKE,
	// ------------------------------------------------------- coup spéciaux
	/** mouvement de roque (tentative) */
	ROQUE, 
	/** promotion du pion */
	PROMOTION, 
	/** promotion du pion + prise */
	TAKEPROMOTION, 
	/** prise en passant du pion */
	PRISE_EN_PASSANT,
	// ------------------------------------------------------- coups à refuser
	/** mouvement légal à refuser car aboutirait en un échec du roi */
	ROI_EN_ECHEC,
	/** fin de partie - échec et mat ou pat */
	END
	; // ----------------------------------------------------- 
}