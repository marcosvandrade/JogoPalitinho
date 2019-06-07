package servidor;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
	public static void main(String[] args) throws IOException {
		// inicia o servidor
		new Servidor(12345).executa();
	}

	private int porta;
	private List<PrintStream> clientes;
	private List<Jogador> jogadores;

	public Servidor (int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<PrintStream>();
		this.jogadores = new ArrayList<>();
	}

	public void executa() throws IOException {
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Porta 12345 aberta!");

        while (true) {
            // aceita um cliente
            Socket cliente = servidor.accept();
            System.out.println("Nova conexão com o cliente " +     
                cliente.getInetAddress().getHostAddress()
            );

            // adiciona saida do cliente à lista
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            this.clientes.add(ps);

            // cria tratador de cliente numa nova thread
            TrataCliente tc = 
                    new TrataCliente(cliente.getInputStream(), this);
            new Thread(tc).start();
        }
	}

	public void distribuiMensagem(String msg) {
		// envia msg para todo mundo
		for (PrintStream cliente : this.clientes) {
			cliente.println(msg);
		}
	}
	
	public void adicionarJogador(String nome, int palitinhos, int aposta) {
		if(palitinhos > 3) {
			palitinhos = 3;
		}
		if(palitinhos < 0) {
			palitinhos = 0;
		}
		this.jogadores.add(new Jogador (nome, palitinhos, aposta));
	}
	
	public void verQuemGanhou() {
		int soma = 0;
		for (Jogador j : jogadores) {
			soma = soma + j.getPalitinhos();
		}
		
		int diferenca, menor = 1000;
		Jogador vencedor = null;
		
		for (Jogador jogador : jogadores) {
			diferenca = Math.abs(soma - jogador.getAposta());
			if (diferenca < menor) {
				vencedor = jogador;
				menor = diferenca;
			}
		}
		
		this.distribuiMensagem("Soma: "+soma+"Jogador Vencedor:\n\n");
		this.distribuiMensagem(""+vencedor+"\n\n\n");
		for (Jogador j : jogadores) {
			this.distribuiMensagem(j.toString());
		}
		
		this.jogadores.clear();
	}
	
	
	
	
	
	

}
