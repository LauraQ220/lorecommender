package model.recommender.ranking;

import java.util.List;

import model.datatransferobject.LO;

/**
 * Main Rating class
 * Etapa de Valoraci�n, etapa responsable de asignar valores de calidad a cada uno de los elementos candidatos que 
 * quedan tras la etapa de filtrado. 
 * El comportamiento de esta clase es com�n a todos los recomendadores: 
 * iterar sobre el conjunto de elementos que han llegado a esta etapa, proporcionando un valor de calidad para cada uno de ellos. 
 * Del mismo modo, se ha seguido el patr�n Strategy para hacer que esta clase delegue la implementaci�n concreta de la 
 * valoraci�n de un elemento en un objeto de la clase QualityMetric.
 * 
 * @author almudena ruiz-iniesta (almudenaris(at)gmail.com)
 *
 */
public class RatingStage {
	
	/** quality metric employed to rank the set*/
	protected QualityMetric theQuality;
	
	/** constructor por defecto. actualmente no hace nada */
	public RatingStage(){}
	
	/** establece la m�trica de calidad encargada de valorar cada elemento */
	public void configureQuality(QualityMetric q){
		theQuality = q;
	}	
	

	/**
	 * m�todo encargado de iterar sobre el conjunto de elementos que han llegado a esta etapa, 
	 * proporcionando un valor de calidad para cada uno de ellos
	 * 
	 * @param listaLO lista de elementos provenientes de la etapa de filtrado
	 * @return lista de elementos con un valor de calidad asignado
	 */
	public List<LO> rate(List<LO> listaLO){				
		double calidadLO;
		for (int i=0; i<listaLO.size(); i++){			
			calidadLO = theQuality.computeQuality(listaLO.get(i));
			listaLO.get(i).setCalidad(calidadLO);			
		}			
		return listaLO;	
	}

	/**
	 * inicializaci�n de la etapa.
	 * debe comprobar que tiene configurada una m�trica de calidad
	 * 
	 * @return true si todo ha ido bien
	 */
	public boolean init(){
		// comprobar que tenemos una quality metric
		return (theQuality != null);
	}
	
	/**
	 * m�todo encargado de liberar la etapa y dejarla lista para la siguiente ejecuci�n.
	 */
	public void end() {
		// �hay que hacer algo?		
	}

}
