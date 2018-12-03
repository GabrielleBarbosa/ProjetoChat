import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

//TUDO QUE ESTÁ COMENTADO É PORQUE PODE MUDAR OU QUE NÃO HÁ CERTEZA DA UTILIZAÇÃO
public class Janela
{
	// O QUE DECLARAR?
	private JFrame janela = new JFrame("Sala");
	private JTextField txtEnvia = new JTextField();
	private JTextArea AreaDeConversa = new JTextArea();	//Área na qual são escritas as mensagens
	private JLabel lblNomeUsuario = new JLabel();  //label com o nome do fudido
	private JButton btnEnviar = new JButton();	//envia mensagem
	private JButton btnSair = new JButton();	//sair da sala atual
	private JButton btnUsuariosDisp = new JButton();
	private JButton btnTeste = new JButton();  //ete botao deve ser apagado antes de enviar o projeto
	private Socket conexao;
	private ObjectOutputStream transmissor;
	private ObjectInputStream receptor;
	private ArrayList usuarios;



	private class TratadorDeRedimensionamento implements ComponentListener
		{
			public void componentResized(ComponentEvent e)
			{
				Font fonte = new Font("Times New Roman", Font.PLAIN, Math.min(janela.getHeight(),janela.getWidth())*22/600);
				btnEnviar.setFont(fonte);
				btnSair.setFont(fonte);
				btnUsuariosDisp.setFont(fonte);
				btnTeste.setFont(fonte);
				lblNomeUsuario.setFont(fonte);
				btnOK.setFont(fonte);
				lblSalas.setFont(fonte);
				escolhaSala.setFont(fonte);
				lblSeuNome.setFont(fonte);
				txtEscrevaNome.setFont(fonte);
			}

			public void componentMoved(java.awt.event.ComponentEvent e)
			{}

			public void componentShown(java.awt.event.ComponentEvent e)
			{}

			public void componentHidden(java.awt.event.ComponentEvent e)
			{}
	}
	/*
	private class TratadorDeEvento implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String textoE = txtEnvia.getText();
			transmissor.println(textoE);
			transmissor.flush();
			AreaDeConversa.append("Você: "+textoE+"\n");
			txtEnvia.setText("");
		}
	}
	*/


	public Janela(/*Socket s*/) throws Exception
	{
			//conexao = s;
			//transmissor = new ObjectOutputStream(conexao.getOutputStream());
			//receptor = new ObjectInputStream(conexao.getInputStream());
			design();
	}

	//objetos do primeiro panel que abre
	private JLabel lblSalas = new JLabel("Escolha uma Sala:");
	private JLabel lblAvisoErros = new JLabel();
	private JLabel lblSeuNome = new JLabel("Escolha um Nome:");
	private JComboBox escolhaSala = new JComboBox();
	private JButton btnOK = new JButton();
	private JPanel painelEscolhaDeSala = new JPanel();
	private JPanel painelEscolhaDeNome = new JPanel();
	private JPanel painelErros = new JPanel();
	private JTextField txtEscrevaNome = new JTextField();
	/////////////////////////////////////////////////////

	private void design()
	{
		btnOK.setText("OK");
		TratadorDeEvento tratador = new TratadorDeEvento();
        btnOK.addActionListener(tratador);

		painelEscolhaDeSala.add(lblSalas);
		painelEscolhaDeSala.add(escolhaSala);
		painelEscolhaDeSala.add(btnOK);
		painelErros.add(lblAvisoErros);

		this.janela.setSize(600,500);
		this.janela.getContentPane().setLayout(new BorderLayout());

		this.janela.add(painelEscolhaDeSala,BorderLayout.NORTH);
		this.janela.add(painelErros,BorderLayout.SOUTH);
		this.janela.add(painelEscolhaDeNome,BorderLayout.CENTER);

		this.janela.addComponentListener (new TratadorDeRedimensionamento());
		this.janela.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		this.janela.setVisible(true);
	}

	public void mostrarSalas(ArrayList<String> salas)throws Exception
	{
		if(salas == null)
			throw new Exception("ArrayList de salas passada é null");

		for(int i=0; i<salas.size();i++)
			escolhaSala.addItem(salas.get(i));
	}

	public void mostrarAvisoDeErro(String erro)
	{
		lblAvisoErros.setText(erro);
	}

	public void mostrarEscolhaDeNome()
	{
		lblAvisoErros.setText("");
		escolhaSala.setEnabled(false);
		btnOK.setText("Confirmar");
		painelEscolhaDeSala.remove(btnOK);
		painelEscolhaDeNome.add(lblSeuNome);
		painelEscolhaDeNome.add(txtEscrevaNome);
		painelEscolhaDeNome.add(btnOK);
		txtEscrevaNome.setColumns(10);
	}

	public void mostrarDesingDeChat(ArrayList usuarios)
	{
		this.usuarios = usuarios;

		janela.remove(painelErros);
		janela.remove(painelEscolhaDeNome);
		janela.remove(painelEscolhaDeSala);

		JPanel painelSul = new JPanel();
		painelSul.setLayout (new GridLayout(1,2));

		JPanel painelNorte = new JPanel();
		painelNorte.setLayout(new GridLayout(1,2));

		JPanel painelUsuarios = new JPanel();
		painelUsuarios.setLayout(new GridLayout(50,1));

		JPanel painelConversas = new JPanel();
		painelConversas.setLayout(new GridLayout(50,1));



		this.janela.add(painelSul,BorderLayout.SOUTH);
		this.janela.add(painelNorte,BorderLayout.NORTH);
		this.janela.add(painelUsuarios,BorderLayout.EAST);
		this.janela.add(painelConversas,BorderLayout.WEST);
		this.janela.add(AreaDeConversa, BorderLayout.CENTER);

		//TratadorDeEvento tratador = new TratadorDeEvento();

		this.btnEnviar.setText("Enviar");
		//this.btnEnviar.addActionListener(tratador);


		this.btnSair.setText("Sair");
		//this.btnSair.addActionListener(tratador);

		this.btnUsuariosDisp.setText("usuários");
		//this.btnSair.addActionListener(tratador);


		painelSul.add(txtEnvia);
		painelSul.add(btnEnviar);

		painelNorte.add(btnSair);
		painelNorte.add(lblNomeUsuario);
		painelNorte.add(btnUsuariosDisp);
	}

	/*
	public void mostra(String textoR, String remetente)
	{
		AreaDeConversa.append(remetente + ": " + textoR + "\n");
	}
	*/

	private class TratadorDeEvento implements ActionListener
	{
		public void trateClickNoBotaoOK()
		{
			mostrarEscolhaDeNome();
			String s = escolhaSala.getSelectedItem()+"";
			//transmissor.writeObject(new EscolhaDeSala(s));
			//transmissor.flush;
		}

		public void trateClickNoBotaoConfirmar()
		{
			String s = txtEscrevaNome.getText();

			usuarios.add("eu");
			usuarios.add("ele");
			mostrarDesingDeChat(usuarios);

			if(s == null || s.trim().equals(""))
			{
				mostrarAvisoDeErro("Digite seu nome!");
			}
			else
			{
				//transmissor.writeObject(new EscolhaDeSala(s));
				//transmissor.flush;
			}
		}


		public void actionPerformed (ActionEvent e)
		{
			String comando = e.getActionCommand();
			if(comando == "OK")
			{
				trateClickNoBotaoOK();
			}
			if(comando == "Confirmar")
			{
				trateClickNoBotaoConfirmar();
			}

		}
	}
}


//this.janela.remove       --> elimina o objeto em questão