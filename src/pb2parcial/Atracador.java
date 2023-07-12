package pb2parcial;

public class Atracador extends Persona {

	private Banda banda;
	private String apodo;

	public Atracador(Integer dni, String nombre, String apellido,String apodo,Banda banda) {
		super(dni, nombre, apellido);
		this.apodo=apodo;
		this.banda=banda;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	@Override
	public String toString() {
		return "Atracador [banda=" + banda + ", apodo=" + apodo + ", getDni()=" + getDni() + ", getNombre()="
				+ getNombre() + ", getApellido()=" + getApellido() +"]";
	}


	

}
