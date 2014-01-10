package model.recommender.selection;

import java.util.List;

import model.datatransferobject.LO;

/**
 * Main Selection interface
 * �ltima etapa que interviene en el proceso de recomendaci�n.
 * Se encarga de elaborar el conjunto final de elementos recomendados.
 * 
 * Cada recomendador debe implementar (o hacer uso de las existentes) una etapa de selecci�n
 * 
 * @author Almudena Ruiz-Iniesta
 *
 */
public abstract class SelectionStage {
	/** 
	 * m�todo encargado de la inicializaci�n de la etapa
	 * tomar�n valor aquellos par�metros necesarios
	 * 
	 * @return true si todo ha salido bien
	 */
	public abstract boolean init ();
	/**
	 * m�todo encargado de realizar la selecci�n de los candidatos que finalmente van a ser recomendados
	 * 
	 * @param setCandidates conjunto de elementos candidatos a ser recomendados. Vienen de la etapa anterior 
	 * con un valor de calidad asignado.
	 * @return lista de elementos recomendados.
	 */
	public abstract List<LO> select(List<LO> setCandidates);
	/**
	 * m�todo final que se encarga de liberar la etapa y dejarla lista para la siguiente ejecuci�n
	 */
	public abstract void end();
}
