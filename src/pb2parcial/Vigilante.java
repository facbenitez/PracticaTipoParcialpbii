package pb2parcial;

public class Vigilante extends Persona {
	private Banco banco;
	
	public Vigilante(Integer dni, String nombre, String apellido,Banco banco) {
		super(dni, nombre, apellido);
		this.banco=banco;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}


}
