package modelo;

import view.views.PersonaView;

public class Persona {

	private String documento;
	private String nombre;
	
	public Persona(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean soyLaPersona(String documento) {
		return this.documento.equalsIgnoreCase(documento);
	}

	public PersonaView toView(){
		return new PersonaView(this.documento,this.nombre);
	}
}
