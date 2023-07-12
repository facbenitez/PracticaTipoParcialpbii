package pb2parcial;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Vigilancia {

	private String nombre;

	// Colocar el tipo de coleccion que corresponda
	private Set<Persona> personas;
	// Colocar el tipo de coleccion que corresponda
	private Set<Banco> bancos;

	public Vigilancia(String nombre) {
		this.nombre = nombre;
		this.personas=new HashSet<>();
		this.bancos=new HashSet<>();
		this.atracos=new HashMap<>();
	
	}
	
	//No se puede cambiar 
	private Map <Integer, Atraco> atracos;
	

	/*
	 * Registra tanto como Vigiladores como atracadores no permite registrar 2
	 * personas con el mismo DNI. Si esto sucede lanza una exception
	 * PersonaDuplicadaException
	 * 
	 */

	public String getNombre() {
		return nombre;
	}

	
	public Set<Persona> getPersonas() {
		return personas;
	}


	public Set<Banco> getBancos() {
		return bancos;
	}


	public Map<Integer, Atraco> getAtracos() {
		return atracos;
	}

	public void registrarPersona(Persona persona)throws PersonaDuplicadaException{
		if(this.personas.contains(persona)) {
		   throw new PersonaDuplicadaException();
		}
	  this.personas.add(persona);
	  
	}

	public Integer cantidadDePersonasTotalesRegistradas() {
		return this.personas.size();
	}
	
	public Boolean agregarBanco(Banco banco){
	    return this.bancos.add(banco);
	}
	

	/*
	 * Este Metodo lanza las siguientes Excepciones NoSeEncuentraAtracadorException
	 * BancoNoEncontradoExcecption
	 * 
	 */
	
	public Atracador buscarAtracador(Integer dniAtracador)throws NoSeEncuentraAtracadorException {
		for (Persona personaActual : personas) {
			 if((personaActual.getDni().equals(dniAtracador))&&(personaActual instanceof Atracador)){
				return (Atracador)personaActual;	
			}
		 }
	   throw new NoSeEncuentraAtracadorException();
	}
	
	public Banco buscarBanco(Integer idBanco)throws BancoNoEncontradoExcecption  {
		for (Banco bancoActual : bancos) {
			 if((bancoActual.getIdBanco().equals(idBanco))){
				return bancoActual;	
			}
		 }
	   throw new BancoNoEncontradoExcecption ();
	}
	
	public void registrarAtraco(Integer dniAtracador, Integer idBanco) throws NoSeEncuentraAtracadorException,BancoNoEncontradoExcecption{
	   Atracador atracador = buscarAtracador(dniAtracador);
	   Banco banco = buscarBanco(idBanco);
	
	   //Se debe agregar un atraco al Mapa
	   Integer idAtraco = this.atracos.size()+1;
	   this.atracos.put(idAtraco,new Atraco(atracador,banco));
	}
	

	//Si la clave no existe lanza ClaveInexistenteException
	public Atraco buscarAtracoPorClave(Integer claveAtraco) throws ClaveInexistenteException{
		if (this.atracos.get(claveAtraco) == null) {
			throw new ClaveInexistenteException();
		}
		return this.atracos.get(claveAtraco);
	}
	
	
	public Vigilante obtenerElVigiladorDeUnAtraco(Integer claveAtraco) throws ClaveInexistenteException, VigilanteNoEncontradoException {
		//B_Instancio un atraco buscando un atraco por clave 
		Atraco atraco = buscarAtracoPorClave(claveAtraco);
		//C_Instancio un banco usando el atraco buscado e invoco un banco usando el getBanco
		Banco banco = atraco.getBanco();
		//D_Instancio un vigilante buscando un vigilante por Banco y retorno el Vigilante buscado
		Vigilante vigilante = buscarVigilantePorBanco(banco);

		return vigilante;

	}

	//A_creo un metodo para buscar un vigilante de un banco
	public Vigilante buscarVigilantePorBanco(Banco banco) throws VigilanteNoEncontradoException {
		for (Persona vigilante : personas) {
			if (vigilante instanceof Vigilante && ((Vigilante) vigilante).getBanco().equals(banco)) {
				return (Vigilante) vigilante;
			}
		}
		throw new VigilanteNoEncontradoException();
	}

	public TreeSet<Atracador> obtenerAtracadoresOrdenados(OrdenPorApodos ordenPorApodo) {
		//2_creo un Hashset e invoco la funcion de buscar Atracadores para tener los atracadores.
		Set<Atracador> atracadores = buscarAtracadores();
		//3_creo un TreeSet y al final invoco el comparator de orden por apodo de los atracadores
		TreeSet<Atracador> atracadoresOrdenadados = new TreeSet<>(ordenPorApodo);
		//4_agrago el set de atracadores dentro del treeSet
		atracadoresOrdenadados.addAll(atracadores);
		return atracadoresOrdenadados;
	}

	//1_ creo un metodo de tipo Set para buscar atracadores y si los encuentro los ingreso en el HashSet de atracadores, luego uso la funcion en obtener atracadores ordenados
	private Set<Atracador>buscarAtracadores() {
		Set<Atracador> atracadores = new HashSet<>();
		for (Persona atracador : personas) {
			if (atracador instanceof Atracador) {
				atracadores.add((Atracador) atracador);
			}
		}
		return atracadores;
	}
	
	public Integer obtenerCantidadAtracadores() {
		Integer cantidadDeAtracadores = 0;
		for (Persona personaActual : personas) {
			if (personaActual instanceof Atracador) {
				cantidadDeAtracadores++;
			}
		}
		return cantidadDeAtracadores;
	}

}
