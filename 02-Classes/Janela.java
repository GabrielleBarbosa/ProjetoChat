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
	private JTextField txtEnviar = new JTextField();
	private JTextPane txtAreaDeConversa = new JTextPane();	//Área na qual são escritas as mensagens
	private JScrollPane jsp = new JScrollPane(txtAreaDeConversa);
	private StyledDocument doc = txtAreaDeConversa.getStyledDocument();
	private JTextPane txtAreaDeConversaPriv = new JTextPane();	//Área na qual são escritas as mensagens
        private JScrollPane jspPriv = new JScrollPane(txtAreaDeConversaPriv);
	private StyledDocument docPriv = txtAreaDeConversaPriv.getStyledDocument();
	private JLabel lblNomeSala = new JLabel("Sala: ");  //label com o nome do fudido
	private JButton btnEnviar = new JButton();	//envia mensagem
	private JButton btnEnviarPriv = new JButton();	//envia mensagem
	private JTextField txtEnviarPriv = new JTextField();
	private JButton btnSair = new JButton();	//sair da sala atual
	private JLabel lblNomeUsuario = new JLabel("Bem Vindo, ");
	private JLabel lblUsuariosDisp = new JLabel("Usuários: ");
	private JComboBox cbxUsuariosDisp = new JComboBox();
	private JButton btnTeste = new JButton();  //ete botao deve ser apagado antes de enviar o projeto
	private Socket conexao;
	private ObjectOutputStream transmissor;
	private ObjectInputStream receptor;
	private ArrayList<String> usuarios;
	private String nomeUsuario;



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
				lblNomeUsuario.setFont(fonte2);
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

	public Janela(Socket s) throws Exception
	{
		if(s == null)
			throw new Exception("Socket null");
		this.conexao = s;
		this.transmissor = new ObjectOutputStream(conexao.getOutputStream());
		design();
	}

	//objetos do primeiro panel que abre
	private JLabel lblSalas = new JLabel("Escolha uma Sala:");
	private JLabel lblAvisoErros = new JLabel();
	private JLabel lblSeuNome = new JLabel("Escolha um Nome:");
	private JLabel lblTitulo = new JLabel("Escolha sua sala!!");
	private JComboBox escolhaSala = new JComboBox();
	private JButton btnOK = new JButton();
	private JPanel painelEscolhaDeSala = new JPanel();
	private JPanel painelEscolhaDeNome = new JPanel();
	private JPanel painelErros = new JPanel();
	private JPanel painelEscolhas = new JPanel();
	private JTextField txtEscrevaNome = new JTextField();
	/////////////////////////////////////////////////////

	private void design()
	{
		btnOK.setText("OK");
		TratadorDeEvento tratador = new TratadorDeEvento();
        	btnOK.addActionListener(tratador);

		painelEscolhas.setLayout(new GridLayout(10,1));

		painelEscolhaDeSala.add(lblSalas);
		painelEscolhaDeSala.add(escolhaSala);
		painelEscolhaDeSala.add(btnOK);
		painelErros.add(lblAvisoErros);

		this.janela.setSize(600,500);
		this.janela.getContentPane().setLayout(new BorderLayout());

		lblTitulo.setFont(new Font("Candara", Font.PLAIN, 30));

		painelEscolhas.add(painelEscolhaDeSala);
		painelEscolhas.add(painelEscolhaDeNome);
		this.janela.add(painelEscolhas, BorderLayout.CENTER);
		//this.janela.add(painelEscolhaDeSala,BorderLayout.NORTH);
		this.janela.add(painelErros,BorderLayout.SOUTH);
		//this.janela.add(painelEscolhaDeNome,BorderLayout.CENTER);
		this.janela.add(lblTitulo, BorderLayout.NORTH);

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

	public void mostrarAvisoDeErro(String erro)throws Exception
	{
		if(erro == null)
			throw new Exception("String passada é null");

		lblAvisoErros.setText(erro);
	}

	public void mostrarEscolhaDeNome()
	{

		lblAvisoErros.setText("");
		escolhaSala.setEnabled(false);
		painelEscolhaDeNome.add(lblSeuNome);
		painelEscolhaDeNome.add(txtEscrevaNome);
		painelEscolhaDeNome.add(btnOK);
		painelEscolhaDeSala.remove(btnOK);
		btnOK.setText("Confirmar");
		txtEscrevaNome.setColumns(10);
	}

	public void mostrarDesignDeChat(ArrayList<String> usuarios)
	{
		this.janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		nomeUsuario = txtEscrevaNome.getText().trim();
		lblNomeUsuario.setText("Bem Vindo, " + txtEscrevaNome.getText());
		lblNomeSala.setText("Sala: " + escolhaSala.getSelectedItem());
		janela.remove(painelErros);
		janela.remove(painelEscolhas);
		janela.remove(lblTitulo);

		this.usuarios = usuarios;

		for(int i=0; i<this.usuarios.size(); i++)
			cbxUsuariosDisp.addItem(this.usuarios.get(i));

		JPanel painelLeste = new JPanel();
		painelLeste.setBorder(javax.swing.BorderFactory.createTitledBorder("Conversas privadas"));
		painelLeste.setLayout(new BorderLayout());

		JPanel painelOeste = new JPanel();
		painelOeste.setBorder(javax.swing.BorderFactory.createTitledBorder("Conversas públicas"));
		painelOeste.setLayout(new BorderLayout());


		JPanel painelSulOeste = new JPanel();
		painelSulOeste.setLayout (new GridLayout(1,2));

		JPanel painelNorte = new JPanel();
		painelNorte.setLayout(new GridLayout(1,3));

		JPanel painelUsuarios = new JPanel();
		painelUsuarios.setLayout(new GridLayout(1,2));

		JPanel painelSulLeste = new JPanel();
		painelSulLeste.setLayout (new GridLayout(1,2));

		painelLeste.add(painelSulLeste,BorderLayout.SOUTH);
		painelLeste.add(painelUsuarios,BorderLayout.NORTH);
		painelLeste.add(jspPriv, BorderLayout.CENTER);

		painelOeste.add(painelSulOeste,BorderLayout.SOUTH);
		painelOeste.add(jsp, BorderLayout.CENTER);

		this.janela.add(painelNorte,BorderLayout.NORTH);
		this.janela.add(painelLeste,BorderLayout.EAST);
		this.janela.add(painelOeste,BorderLayout.CENTER);

		TratadorDeEvento tratador = new TratadorDeEvento();
		this.btnEnviar.setText("Enviar");
		this.btnEnviar.addActionListener(tratador);

		this.btnEnviarPriv.setText("Envia");
		this.btnEnviarPriv.addActionListener(tratador);

		this.btnSair.setText("Sair");
		this.btnSair.addActionListener(tratador);

		painelSulLeste.add(txtEnviarPriv);
		painelSulLeste.add(btnEnviarPriv);


		painelSulOeste.add(txtEnviar);
		painelSulOeste.add(btnEnviar);

		painelNorte.add(lblNomeSala);
		painelNorte.add(lblNomeUsuario);
		painelNorte.add(btnSair);

		painelUsuarios.add(lblUsuariosDisp);
		painelUsuarios.add(cbxUsuariosDisp);

		txtAreaDeConversa.setEditable(false);
		txtAreaDeConversaPriv.setEditable(false);

		if(this.usuarios.size() == 0)
		{
			txtEnviarPriv.setEnabled(false);
			btnEnviarPriv.setEnabled(false);
		}
	}

	public void mostra(String textoR, String remetente)throws Exception
	{
		if(remetente == null || remetente.trim().equals(""))
			throw new Exception("remetente null");

		if(textoR == null || textoR.trim().equals(""))
			throw new Exception("texto null");

		doc.insertString(doc.getLength(), remetente + ": " + textoR + "\n",
                         doc.getStyle("pink"));
	}

	public void mostraPriv(String textoR, String remetente)throws Exception
	{
		if(remetente == null || remetente.trim().equals(""))
			throw new Exception("remetente null");

		if(textoR == null || textoR.trim().equals(""))
			throw new Exception("texto null");

		System.out.println("TextoR: " + textoR);
		System.out.println("Remetente: "+ remetente);

		docPriv.insertString(docPriv.getLength(), remetente + ": " + textoR + "\n",
						 docPriv.getStyle("pink"));
	}

	public void mostraEntrada(String remetente)throws Exception
	{
		if(remetente == null || remetente.trim().equals(""))
			throw new Exception("remetente null");

		doc.insertString(doc.getLength(), remetente + " entrou na sala!\n",
				 doc.getStyle("bold"));

		if(this.usuarios.size() > 0)
		{
			for(int i=0; i<this.usuarios.size();i++)
				if(!(this.usuarios.get(i).equals(remetente.trim())))
				{
					this.cbxUsuariosDisp.addItem(remetente);
					this.usuarios.add(remetente);
				}
		}
		else
		{
			this.cbxUsuariosDisp.addItem(remetente);
			this.usuarios.add(remetente);
		}

		txtEnviarPriv.setEnabled(true);
		btnEnviarPriv.setEnabled(true);
	}

	public void mostraSaida(String remetente)throws Exception
	{
		if(remetente == null || remetente.trim().equals(""))
			throw new Exception("remetente null");

		doc.insertString(doc.getLength(), remetente + " saiu da sala!\n",
				 doc.getStyle("color: red;"));

	    this.cbxUsuariosDisp.removeItem(remetente);

	    for(int i=0; i<usuarios.size(); i++)
	    	if(usuarios.get(i).equals(remetente.trim()))
	    		usuarios.remove(i);

	    if(this.usuarios.size() == 0)
		{
			txtEnviarPriv.setEnabled(false);
			btnEnviarPriv.setEnabled(false);
		}
	}

	private class TratadorDeEvento implements ActionListener
	{

		public void trateClickNoBotaoOK()
		{
			try
			{
				String s = escolhaSala.getSelectedItem()+"";
				transmissor.writeObject(new EscolhaDeSala(s));
				transmissor.flush();
			}
			catch(Exception err)
			{}
		}

		public void trateClickNoBotaoConfirmar()
		{
			String s = txtEscrevaNome.getText();

			if(s == null || s.trim().equals(""))
			{
				try
				{
					mostrarAvisoDeErro("Digite seu nome!");
				}
				catch(Exception err)
				{}
			}
			else
			{
				try
				{
					//mostrarDesignDeChat(usuarios);
					transmissor.writeObject(new EscolhaDeNome(s));
					transmissor.flush();
				}
				catch(Exception err)
				{}
			}
		}

		public void trateClickNoBotaoEnviar()
		{
			String s = txtEnviar.getText();

			try
			{
				if(s != null || !(s.trim().equals("")))
				{
					mostra(s, "Você");

					transmissor.writeObject(new Mensagem(s, nomeUsuario));
					transmissor.flush();
					txtEnviar.setText("");
				}
			}
			catch(Exception err)
			{}
		}

		public void trateClickNoBotaoEnviarPriv()
		{
			String s = txtEnviarPriv.getText();

			try
			{
				if(s != null || !(s.trim().equals("")))
				{
					mostraPriv(s, "Você(para: " + cbxUsuariosDisp.getSelectedItem() + ")");
					transmissor.writeObject(new Mensagem(cbxUsuariosDisp.getSelectedItem() + "", nomeUsuario,s));
					transmissor.flush();
					txtEnviarPriv.setText("");
				}
			}
			catch(Exception err)
			{}
		}

		public void trateClickNoBotaoSair()
		{
			try
			{
				transmissor.writeObject(new PedidoParaSairDaSala());
				transmissor.flush();
				conexao.close();
				transmissor.close();
				janela.dispose();
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
			if(comando == "Envia")
			{
				trateClickNoBotaoEnviarPriv();
			}
			if(comando == "Sair")
			{
				trateClickNoBotaoSair();
			}

		}
	}
}


//this.janela.remove       --> elimina o objeto em questão