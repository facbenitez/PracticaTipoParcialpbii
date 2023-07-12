package pb2parcial;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSistema {


	@Test
	public void testQueRegistroUnBanco() {
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");
		
		//banco
		Integer idbanco=1;
		Banco banco = new Banco(idbanco, "la Casa de Papel");
		
		Boolean seRegistro=vigilancia.agregarBanco(banco);
		assertTrue(seRegistro);
			
	}
	
	@Test
	public void quePuedaRegistrarUnAtracador() throws PersonaDuplicadaException{
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");
		
		//banda
		Integer idbanda = 1;
		String nombre="La Casa de Papel";
		Banda banda = new Banda (idbanda,nombre);
		
		//atracador
		Integer dni= 1;
		nombre="Ursula";
		String apellido= "Corbero";
		String apodo="Tokio";
		
		//No Cambiar esta Linea
		Persona atracador = new Atracador (dni, nombre, apellido,apodo,banda );
		vigilancia.registrarPersona (atracador);
		Integer valorEsperado = 1;
		
		//Compleatar el Assert
		assertEquals(valorEsperado,vigilancia.obtenerCantidadAtracadores());
		
	}
	
	
	@Test(expected=PersonaDuplicadaException.class)
	public void queCuandoSeRegistre2PersonasConElMismoDNiLanceUnaExpcionPersonaDuplicadaException()throws PersonaDuplicadaException{
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");
		
		//Creo Vigilante
		Persona vigilante = new Vigilante (1, "Pepito", "Pistolero", new Banco(1, "LA Casa De Papel") );
		vigilancia.registrarPersona (vigilante);
		
		//Creo Banda
		Integer idBanda = 1;
		String nombre="La Casa de Papel";
		Banda banda = new Banda (idBanda,nombre);
		
		//Creo Atracador
		Integer dni= 1;
		nombre="Ursula";
		String apellido= "Corbero";
		String apodo="Tokio";
		
		//No Cambiar esta Linea
		Persona atracador = new Atracador (dni, nombre, apellido,apodo,banda );
		vigilancia.registrarPersona (atracador);
	 
	}
	

	@Test
	public void queSePuedaRegistrarUnAtraco ()throws PersonaDuplicadaException, NoSeEncuentraAtracadorException,
	BancoNoEncontradoExcecption, ClaveInexistenteException {
		
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");
		
		//banda
		Integer idBanda = 1;
		String nombre="La Casa de Papel";
		Banda banda = new Banda (idBanda,nombre);
		
		//atracador
		Integer dni= 1;
		nombre="Ursula";
		String apellido= "Corbero";
		String apodo="Tokio";
		
		//No Cambiar esta Linea
		Persona atracador = new Atracador (dni, nombre, apellido,apodo,banda );
		vigilancia.registrarPersona (atracador);
		
		//banco
		Integer idBanco=1;
		Banco banco = new Banco(idBanco, "la Casa de Papel");
		vigilancia.agregarBanco (banco);
		
		vigilancia.registrarAtraco(dni, idBanco);
		//Integer valorEsperado=1;
		
		Integer claveAtraco=1;
		Atraco atraco =vigilancia.buscarAtracoPorClave(claveAtraco);
		
		Banco bancoEsperado = new Banco(idBanco, "la Casa de Papel");
		//No cambiar
		assertTrue(bancoEsperado.equals(atraco.getBanco()));
			
	}
	
	@Test
	public void queSePuedaObtenerLosAtracadoresOrdenadosPorApodos() throws PersonaDuplicadaException {
		Vigilancia vigilancia = new Vigilancia("Nombre De la Compania de Vigilancia");
		
		// banda
		Integer idBanda = 1;
		String nombre = "La Casa de Papel";
		Banda banda = new Banda(idBanda, nombre);
		
		// atracador1
		Persona atracador1 = new Atracador(1, "Ursula", "Corbero", "Tokio", banda);
		vigilancia.registrarPersona(atracador1);
		
		// atracador2
		Persona atracador2 = new Atracador(2, "Alba", "Flores", "Nairobi", banda);
		vigilancia.registrarPersona(atracador2);
		
		// atracador3
		Persona atracador3 = new Atracador(3, "No me", "acuerdo", "Oslo", banda);
		vigilancia.registrarPersona(atracador3);
		
		// banco
		Integer idBanco = 1;
		Banco banco = new Banco(idBanco, "la Casa de Papel");
		vigilancia.agregarBanco(banco);
		
		//comparo si son iguales los atracadores con el primero de la lista ordenada y el ultimo.
		assertEquals(((Atracador)atracador2).getApodo(), vigilancia.obtenerAtracadoresOrdenados(new OrdenPorApodos()).first().getApodo());
		assertEquals(((Atracador)atracador1).getApodo(), vigilancia.obtenerAtracadoresOrdenados(new OrdenPorApodos()).last().getApodo());
		
	}

	
    //Cree test a su eleccion para que pruebe el resto de las funcionalidades
	@Test
	public void queSePuedaObtenerUnVigilanteDeUnAtraco() throws PersonaDuplicadaException, ClaveInexistenteException, NoSeEncuentraAtracadorException, BancoNoEncontradoExcecption, VigilanteNoEncontradoException {
		Vigilancia vigilancia = new Vigilancia("Nombre De la Compania de Vigilancia");
		
		// banda
		Integer idBanda = 1;
		String nombre = "La Casa de Papel";
		Banda banda = new Banda(idBanda, nombre);
		
		// atracador1
		Persona atracador1 = new Atracador(1, "Ursula", "Corbero", "Tokio", banda);
		vigilancia.registrarPersona(atracador1);
		
		// banco
		Integer idBanco = 1;
		Banco banco = new Banco(idBanco, "la Casa de Papel");
		vigilancia.agregarBanco(banco);
		
		// Creo Vigilante
		Persona vigilante = new Vigilante(2,"Pepito", "Pistolero", banco);
		vigilancia.registrarPersona(vigilante);

		vigilancia.registrarAtraco(1, idBanco);
		assertEquals(vigilante,vigilancia.obtenerElVigiladorDeUnAtraco(1));
		
	}

	@Test(expected = VigilanteNoEncontradoException.class)
		public void queCuandoNoSeEncuentreUnVigiladorDelAtracoSeLanceVigilanteNoEncontradoException() throws PersonaDuplicadaException, NoSeEncuentraAtracadorException, BancoNoEncontradoExcecption, ClaveInexistenteException, VigilanteNoEncontradoException {
		Vigilancia vigilancia = new Vigilancia("Nombre De la Compania de Vigilancia");
		
		// banda
		Integer idBanda = 1;
		String nombre = "La Casa de Papel";
		Banda banda = new Banda(idBanda, nombre);
		
		// atracador1
		Persona atracador1 = new Atracador(1, "Ursula", "Corbero", "Tokio", banda);
		vigilancia.registrarPersona(atracador1);
		
		// banco
		Integer idBanco = 1;
		Banco banco = new Banco(idBanco, "la Casa de Papel");
		vigilancia.agregarBanco(banco);
		Banco banco2= new Banco(2, "la Casa de la moneda");
		vigilancia.agregarBanco(banco2);
		
		// Creo Vigilante
		Persona vigilante = new Vigilante(2,"Pepito", "Pistolero", banco2);
		vigilancia.registrarPersona(vigilante);

		vigilancia.registrarAtraco(1, idBanco);
		vigilancia.obtenerElVigiladorDeUnAtraco(1);
		
	}

	

}
