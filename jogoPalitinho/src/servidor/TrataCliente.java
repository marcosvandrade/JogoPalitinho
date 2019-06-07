package servidor;

import java.io.InputStream;
import java.util.Scanner;

public class TrataCliente implements Runnable {

    private InputStream cliente;
    private Servidor servidor;

    public TrataCliente(InputStream cliente, Servidor servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    public void run() {
        // quando chegar uma msg, distribui pra todos
        Scanner s = new Scanner(this.cliente);
        while (s.hasNextLine()) {
        	String msg = s.nextLine();
        	if(msg.startsWith("-")) {
        		String[] tokens = msg.split("#");
        		String nome = tokens[0].substring(1);
        		int palitinhos = Integer.parseInt(tokens[1]);
        		int aposta = Integer.parseInt(tokens[2]);
        		servidor.adicionarJogador(nome, palitinhos, aposta);
        	}else if(msg.startsWith("VAI")){
        		servidor.verQuemGanhou();
        	}
            //servidor.distribuiMensagem(s.nextLine());
        }
        s.close();
    }
}