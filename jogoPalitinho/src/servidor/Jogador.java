package servidor;

public class Jogador {
	
	private int palitinhos;
	private int aposta;
	private String nome;
	public Jogador(String nome, int palitinhos, int aposta) {
		super();
		this.palitinhos = palitinhos;
		this.aposta = aposta;
		this.nome = nome;
	}
	public int getPalitinhos() {
		return palitinhos;
	}
	public void setPalitinhos(int palitinhos) {
		this.palitinhos = palitinhos;
	}
	public int getAposta() {
		return aposta;
	}
	public void setAposta(int aposta) {
		this.aposta = aposta;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return nome+" [palitinhos=" + palitinhos + " - aposta=" + aposta + "]";
	}
	
	

}
