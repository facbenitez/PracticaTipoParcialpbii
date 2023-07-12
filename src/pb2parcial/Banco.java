package pb2parcial;

public class Banco {

	private String nombre;
	private Integer idBanco;

	public Banco(Integer idBanco, String nombre) {

		this.idBanco=idBanco;
		this.nombre=nombre;
	}

	public Banco() {

		this.idBanco=0;
		this.nombre="";
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBanco == null) ? 0 : idBanco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Banco other = (Banco) obj;
		if (idBanco == null) {
			if (other.idBanco != null)
				return false;
		} else if (!idBanco.equals(other.idBanco))
			return false;
		return true;
	}
	
	

}
