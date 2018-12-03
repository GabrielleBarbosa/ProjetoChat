import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.text.StyledDocument;

//TUDO QUE ESTÁ COMENTADO É PORQUE PODE MUDAR OU QUE NÃO HÁ CERTEZA DA UTILIZAÇÃO
public class Janela
{
	// O QUE DECLARAR?
	private JFrame janela = new JFrame("Sala");
	private JTextField txtEnvia = new JTextField();
	private JTextPane txtAreaDeConversa = new JTextPane();	//Área na qual são escritas as mensagens
	private StyledDocument doc = txtAreaDeConversa.getStyledDocument();
	private JLabel lblNomeSala = new JLabel("Sala: ");  //label com o nome do fudido
	private JButton btnEnviar = new JButton();	//envia mensagem
	private JButton btnSair = new JButton();	//sair da sala atual
	private JLabel lblUsuariosDisp = new JLabel("Usuários: ");
	private JComboBox cbxUsuariosDisp = new JComboBox();
	private JButton btnTeste = new JButton();  //ete botao deve ser apagado antes de enviar o projeto
	private Socket conexao;
	private ObjectOutputStream transmissor;
	private ObjectInputStream receptor;
	private ArrayList<String> usuarios;



	private class TratadorDeRedimensionamento implements ComponentListener
		{
			public void componentResized(ComponentEvent e)
			{
				Font fonte = new Font("Times New Roman", Font.PLAIN, Math.min(janela.getHeight(),janela.getWidth())*22/600);
				Font fonte2 = new Font("Candara", Font.PLAIN, Math.min(janela.getHeight(),janela.getWidth())*30/600);
				btnEnviar.setFont(fonte);
				btnSair.setFont(fonte);
				lblUsuariosDisp.setFont(fonte);
				btnTeste.setFont(fonte);
				lblNomeSala.setFont(fonte2);
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
			txtAreaDeConversa.append("Você: "+textoE+"\n");
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

	public void mostrarDesingDeChat(ArrayList<String> usuarios)
	{
		janela.remove(painelErros);
		janela.remove(painelEscolhaDeNome);
		janela.remove(painelEscolhaDeSala);

		this.usuarios = usuarios;

		for(int i=0; i<this.usuarios.size(); i++)
			cbxUsuariosDisp.addItem(this.usuarios.get(i));

		JPanel painelLeste = new JPanel();
		painelLeste.setBorder(javax.swing.BorderFactory.createTitledBorder("Conversas privadas"));
		painelLeste.setLayout(new BorderLayout());

		JPanel painelOeste = new JPanel();
		painelOeste.setBorder(javax.swing.BorderFactory.createTitledBorder("Conversas públicas"));
		painelOeste.setLayout(new BorderLayout());


		JPanel painelSul = new JPanel();
		painelSul.setLayout (new GridLayout(1,2));

		JPanel painelNorte = new JPanel();
		painelNorte.setLayout(new GridLayout(1,2));

		JPanel painelUsuarios = new JPanel();
		painelUsuarios.setLayout(new GridLayout(1,2));

		JPanel painelConversas = new JPanel();
		painelConversas.setLayout(new GridLayout(20,1));

		painelLeste.add(painelUsuarios,BorderLayout.NORTH);

		painelOeste.add(painelSul,BorderLayout.SOUTH);
		painelOeste.add(painelNorte,BorderLayout.NORTH);
		painelOeste.add(txtAreaDeConversa, BorderLayout.CENTER);

		this.janela.add(painelLeste,BorderLayout.EAST);
		this.janela.add(painelOeste,BorderLayout.CENTER);


		//TratadorDeEvento tratador = new TratadorDeEvento();

		this.btnEnviar.setText("Enviar");
		//this.btnEnviar.addActionListener(tratador);


		this.btnSair.setText("Sair");
		//this.btnSair.addActionListener(tratador);


		painelSul.add(txtEnvia);
		painelSul.add(btnEnviar);

		//painelNorte.add(btnSair);
		painelNorte.add(lblNomeSala);

		painelUsuarios.add(lblUsuariosDisp);
		painelUsuarios.add(cbxUsuariosDisp);
	}

	public void mostra(String textoR, String remetente)throws Exception
	{
		System.out.println("aaaaaaaaaa");

		if(remetente == null || remetente.trim().equals(""))
			throw new Exception("remetente null");

		if(textoR == null || textoR.trim().equals(""))
			throw new Exception("texto null");

		//txtAreaDeConversa.append(remetente + ": " + textoR + "\n");
		doc.insertString(doc.getLength(), remetente + ": " + textoR + "\n",
                         doc.getStyle("color: pink;"));
	}

	public void mostraEntrada(String remetente)throws Exception
	{
		if(remetente == null || remetente.trim().equals(""))
			throw new Exception("remetente null");

		//txtAreaDeConversa.append(remetente + " entrou na sala!");
		doc.insertString(doc.getLength(), remetente + " entrou na sala!",
				 doc.getStyle("color: green;"));
	}

	public void mostraSaida(String remetente)throws Exception
	{
		if(remetente == null || remetente.trim().equals(""))
			throw new Exception("remetente null");

		//txtAreaDeConversa.append(remetente + " saiu na sala!");
		doc.insertString(doc.getLength(), remetente + " saiu na sala!",
				 doc.getStyle("color: red;"));
	}

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

			usuarios = new ArrayList<String>(6);
			usuarios.add("eu");
			usuarios.add("ele");

			if(s == null || s.trim().equals(""))
			{
				System.out.println("ccccccc");
				mostrarAvisoDeErro("Digite seu nome!");
			}
			else
			{
				mostrarDesingDeChat(usuarios);
				//transmissor.writeObject(new EscolhaDeSala(s));
				//transmissor.flush;
			}
		}

		public void trateClickNoBotaoEnviar()
		{
			System.out.println("aaa");
			String s = txtEnvia.getText();

			try
			{
				if(s != null || !(s.trim().equals("")))
				{
					System.out.println("aaaaa");
					mostra(s, "Você");
					//transmissor.writeObject(new Mensagem());
					//transmissor.flush;
				}
			}
			catch(Exception err)
			{}
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
			if(comando == "Enviar")
			{
				trateClickNoBotaoEnviar();
			}

		}
	}
}


//this.janela.remove       --> elimina o objeto em questão