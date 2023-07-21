package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Edificio;
import modelo.Estado;
import modelo.Persona;
import modelo.Reclamo;
import modelo.Unidad;
import view.views.EdificioView;
import view.views.PersonaView;
import view.views.UnidadView;

import javax.naming.ldap.Control;

public class Controlador {
	private static Controlador instancia;
	private List<Unidad> unidades;
	private List<Persona> personas;
	private List<Edificio> edificios;
	private List<Reclamo> reclamos;

	private Controlador(){
		unidades = new ArrayList<Unidad>();
		personas = new ArrayList<Persona>();
		edificios = new ArrayList<Edificio>();
		reclamos = new ArrayList<Reclamo>();
		cargarDatos();
	}
	public static Controlador getInstance(){
		if (instancia == null) {
			instancia = new Controlador();
		}
		return instancia;
	}
	private void cargarDatos(){
		personas.add(new Persona("43505235","Juan"));
		personas.add(new Persona("35000001","pedro"));
		edificios.add(new Edificio(21,"Edificio 1","calle falsa 123"));
		edificios.add(new Edificio(11,"Edificio 2","calle falsa 122"));
		unidades.add(new Unidad(001,"3ro","1",buscarEdificio(21)));
		unidades.add(new Unidad(002,"2ro","3",buscarEdificio(11)));
	}
	public void transferirUnidad(int codigo, String piso, String numero, String documento) {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.transferir(persona);
	}

	public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarDuenio(persona);
	}

	public void alquilarUnidad(int codigo, String piso, String numero, String documento) {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.alquilar(persona);
		habitarUnidad(codigo, piso, numero);
	}

	public void agregarInquilinoUnidad(int codigo, String piso, String numero, String documento) {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		unidad.agregarInquilino(persona);
	}

	public void liberarUnidad(int codigo, String piso, String numero) {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.liberar();
	}
	
	public void habitarUnidad(int codigo, String piso, String numero) {
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		unidad.habitar();;
	}
	
	public void agregarPersona(String documento, String nombre) {
		Persona persona = new Persona(documento, nombre);
		personas.add(persona);
	}
	
	public void eliminarPersona(String documento) {
		Persona persona = buscarPersona(documento);
		personas.remove(persona);
	}
	
	public int agregarReclamo(int codigo, String piso, String numero, String documento, String ubicacion, String descripcion) {
		Edificio edificio = buscarEdificio(codigo);
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		Persona persona = buscarPersona(documento);
		Reclamo reclamo = new Reclamo(persona, edificio, ubicacion, descripcion, unidad);
		reclamos.add(reclamo);
		return reclamo.getNumero();
	}
	
	public void agregarImagenAReclamo(int numero, String direccion, String tipo) {
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.agregarImagen(direccion, tipo);
	}
	
	public void cambiarEstado(int numero, Estado estado) {
		Reclamo reclamo = buscarReclamo(numero);
		reclamo.cambiarEstado(estado);
	}
	
	private Edificio buscarEdificio(int codigo){
		for(Edificio edificio : edificios)
			if(edificio.soyElEdificio(codigo))
				return edificio;
		return null;
	}

	private Unidad buscarUnidad(int codigo, String piso, String numero) {
		for(Unidad unidad : unidades)
			if(unidad.soyLaUnidad(codigo, piso, numero))
				return unidad;
		return null;
	}	
	
	private Persona buscarPersona(String documento)  {
		for(Persona persona : personas)
			if(persona.soyLaPersona(documento))
				return persona;
		return null;
	}
	
	private Reclamo buscarReclamo(int numero) {
		for(Reclamo reclamo : reclamos)
			if(reclamo.soyElReclamo(numero))
				return reclamo;
		return null;
	}

	public List<EdificioView> getEdificios(){
		List<EdificioView> listaEd = new ArrayList<>();
		for (Edificio e: edificios) {
			listaEd.add(e.toView());
		}
		return listaEd;
	}
	public List<UnidadView> getUnidades(){
		List<UnidadView> listaUn = new ArrayList<>();
		for (Unidad u: unidades) {
			listaUn.add(u.toView());
		}
		return listaUn;
	}
	public List<PersonaView> getPersonas(){
		List<PersonaView> listaPer = new ArrayList<>();
		for (Persona p: personas) {
			listaPer.add(p.toView());
		}
		return listaPer;
	}
}
