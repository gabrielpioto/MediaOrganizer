package testejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pessoa {
	
	
	private Nome nome;
	private int idade;
	private Genero genero;
	
	public Pessoa(Nome nome, int idade, Genero genero) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
	}

	public Nome getNome() {
		return nome;
	}

	public void setNome(Nome nome) {
		this.nome = nome;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Nome getNomes(){
		return nome;
	}
	
	public int getIdade(){
		return idade;
	}
	
	@XmlElement
	public void setIdade(int idade){
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", idade=" + idade + "]";
	}
	
	public static enum Genero{
		HOMEM, MULHER;
	}
	

}
