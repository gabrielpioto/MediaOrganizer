package testejava;

public class Aluno extends Pessoa {
	private String escola;
	
	public Aluno(Nome nome, int idade, Genero genero) {
		super(nome, idade, genero);
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}
	
	
}
