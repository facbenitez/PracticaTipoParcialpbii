package pb2parcial;

public class Banda {
    private Integer idbanda; 
    private String nombre;
	
    public Banda(Integer idbanda, String nombre) {
		
		this.idbanda = idbanda;
		this.nombre = nombre;
	}

	public Integer getIdbanda() {
		return idbanda;
	}

	public void setIdbanda(Integer idbanda) {
		this.idbanda = idbanda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	    

}
