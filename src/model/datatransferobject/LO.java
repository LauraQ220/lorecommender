package model.datatransferobject;


import java.util.ArrayList;
/** 
 * 
 * @author Almudena Ruiz-Iniesta (almudenaris(at)gmail.com)
 *
 */

public class LO implements Comparable<Object>{
	
	/** identificador �nico de cada LO */
	private String id;
	/** title of the resource  */
	private String title;
	/** the description of the resource */
	private String description;
	/** lista de conceptos que est�n contenidos en el LO */
	private ArrayList<String> concepts;
	/** valores de similitud con la consulta, media aritm�tica y UP del LO */
	private TCalculos valores;
	/** calidad para cada recomedaci�n del LO */
	private double calidad;
	
	private double diversidadRel;
	
	//valoraci�n para el LO
	private double rating;
	
	
	// variables para calcular la relevancia para los test offline	
	private boolean queryRelevance;
	private boolean profileRelevance;
	private String level;
	
	/**
	 * Constructor
	 * @param id nombre del LO
	 * @param concepts lista de conceptos asociados
	 */
	public LO(String id, ArrayList<String> concepts) {
		super();
		this.id = id;
		title = ""; description = "";
		this.concepts = concepts;
		valores = new TCalculos();
	}
	/**
	 * Constructor por defecto
	 */
	public LO() {
		super();
		concepts = new ArrayList<String>();
		valores = new TCalculos();
		title="";description = "";
		calidad=1.0;
	}
	/**
	 * Constructor por copia
	 * @param tLO LO a copiar
	 */
	public LO(LO tLO) {
		super();
		this.id = tLO.id;
		this.concepts = tLO.concepts;
		//valores = new TCalculos();
		//title="";description = "";
		valores = tLO.valores;
		this.title = tLO.title;
		this.description = tLO.description;
		this.queryRelevance = tLO.queryRelevance;
		this.calidad = tLO.calidad;
		this.diversidadRel = tLO.diversidadRel;
		this.profileRelevance = tLO.profileRelevance;
		this.rating = tLO.rating;
		this.level = tLO.level;
	}
	/**
	 * Constructor vac�o
	 * @param id nombre del LO
	 */
	public LO(String id) {
		this.id = id;
		concepts = new ArrayList<String>();
		valores = new TCalculos();
		title="";description = "";
	}
	/**
	 * M�todo encargado de a�adir un nuevo concepto a los asociados al LO
	 * @param concepto
	 */
	public void addConcepto(String concepto){
		concepts.add(concepto);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<String> getConcepts() {
		return concepts;
	}
	public void setConcepts(ArrayList<String> concepts) {
		this.concepts = concepts;
	}
	/*
	public String toString(){
		String s;
		s = this.id +"\t";
		s += this.concepts.toString()+"\t";
		s += valores.toString()+"\t";
		//s += concepts.toString()+"\n";
		s += "Calidad: "+this.calidad+"\n";
		//s+= this.calidad+"\t";
		s+= "Diversidad relativa: "+this.diversidadRel+"\n";
		//s+= this.diversidadRel+"\n";
		return s;
	}
	*/
	public String toString(){
		return this.id +" "+this.concepts.toString()+" "+calidad;
	}
	// m�todo auxiliar que escribe de una determinada manera la informaci�n del LO
	public String escribeLoUp(){
		String s;
		s = "["+this.id+","+this.valores.getUP()+"]";
		return s;
	}
	/**
	 * @return the valores
	 */
	public TCalculos getValores() {
		return valores;
	}
	/**
	 * @param valores the valores to set
	 */
	public void setValores(TCalculos valores) {
		this.valores = valores;
	}
	public void setSimilitud(double similitud) {
		this.valores.setSimilitud(similitud);
	}
	public void setCalidad(double calidad) {
		this.calidad = calidad;
	}
	public double getCalidad() {
		return calidad;
	}
	/**
	 * m�todo que calcula la calidad de un LO a partir de la similitud y la UP
	 * @deprecated en la nueva versi�n del framework este m�todo ya no es necesario
	 * la m�trica que se emplea en la etapa de rating es la encargada de hacer estos
	 * c�lculos
	 * @param alfa
	 */
	public void calculaCalidad(double alfa) {		
		this.calidad = alfa * this.valores.getSimilitud() + (1-alfa)*this.valores.getUP();
		/* alternativas para calcular la calidad */
		//this.calidad = 1 / ((alfa/this.valores.getSimilitud())+((1-alfa)/this.valores.getUP()));
		//this.calidad = this.valores.getSimilitud()*this.valores.getUP();
		//this.calidad = this.getValores().getSimilitud();
		//this.calidad = this.getValores().getUP();
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * ordenamos por calidad
	 */
	public int compareTo(Object o){
		// nos pasan un TransfeLO para comparar con this
		if (((LO)o).getCalidad() == this.getCalidad())
			return 0;
		else if (((LO)o).getCalidad() < this.getCalidad())
				return -1;
		else if (((LO)o).getCalidad() > this.getCalidad())
			return 1;
		return 0;
	}
	/**
	 * @return the diversidadRel
	 */
	public double getDiversidadRel() {
		return diversidadRel;
	}
	/**
	 * @param diversidadRel the diversidadRel to set
	 */
	public void setDiversidadRel(double diversidadRel) {
		this.diversidadRel = diversidadRel;
	}
	public boolean isQueryRelevance() {
		return queryRelevance;
	}
	public void setQueryRelevance(boolean queryRelevance) {
		this.queryRelevance = queryRelevance;
	}
	public boolean isProfileRelevance() {
		return profileRelevance;
	}
	public void setProfileRelevance(boolean profileRelevance) {
		this.profileRelevance = profileRelevance;
	}
	
	/**
	 * @return true si el objeto pasado es igual a nivel de identificador
	 */
	public boolean equals(Object o){
		if(!(o instanceof LO))
			return false;		
		LO other = (LO)o;
		String idOther = other.id;
		
		boolean b= id.toLowerCase().equals(idOther.toLowerCase());// (other.getId().equals(id));
		return b;
	}
	
	public void setUP(double UP){
		valores.setUP(UP);
	}
	
	public double getUP(){
		return valores.getUP();
	}
	public double getSim() {
		return valores.getSimilitud();
	}	
	public void setSim(double d){
		valores.setSimilitud(d);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}
	/** 
	 * M�todo que actualiza la valoraci�n del LO 
	 * @param rating
	 */
	public void sumRating(double rating) {
		if (this.rating > 0)
			this.rating += rating;
		else
			this.rating = rating;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevel() {
		return level;
	}
	

}
