import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;



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
	private JButton btnSalasDisp = new JButton();
	private JButton btnTeste = new JButton();  //ete botao deve ser apagado antes de enviar o projeto
	private ServerSocket pedido;
	private Socket conexao;
	private ObjectOutputStream transmissor;
	private ObjectInputStream receptor;


	//objetos do primeiro panel que abre
	private JLabel Salas = new JLabel("Escolha uma Sala:");
	private JComboBox escolhaSala = new JComboBox();




	private class TratadorDeRedimensionamento implements ComponentListener
		{
			public void componentResized(ComponentEvent e)
			{

				Font fonte = new Font("Times New Roman", Font.PLAIN, Math.min(janela.getHeight(),janela.getWidth())*22/950);

				btnEnviar.setFont (fonte);
				btnSair.setFont(fonte);
				btnSalasDisp.setFont(fonte);
				btnTeste.setFont(fonte);
				lblNomeUsuario.setFont(fonte);
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


	public void escolhaDeSala()
	{

	}


	private void Criacao()
	{
		JPanel painelSul = new JPanel();
		painelSul.setLayout (new GridLayout(1,2));

		JPanel painelNorte = new JPanel();
		painelNorte.setLayout(new GridLayout(1,2));

		JPanel painelSalas = new JPanel();
		painelSalas.setLayout(new GridLayout(50,1));

		JPanel painelConversas = new JPanel();
		painelConversas.setLayout(new GridLayout(50,1));

		//TratadorDeEvento tratador = new TratadorDeEvento();

		this.btnEnviar.setText("Enviar");
		//this.btnEnviar.addActionListener(tratador);


		this.btnSair.setText("Sair");
		//this.btnSair.addActionListener(tratador);

		this.btnSalasDisp.setText("Salas");
		//this.btnSair.addActionListener(tratador);


		painelSul.add(txtEnvia);
		painelSul.add(btnEnviar);

		painelNorte.add(btnSair);
		painelNorte.add(lblNomeUsuario);
		painelNorte.add(btnSalasDisp);

		this.janela.setSize (900,800);
		this.janela.getContentPane().setLayout(new BorderLayout());

		this.janela.add(painelSul,BorderLayout.SOUTH);
		this.janela.add(painelNorte,BorderLayout.NORTH);
		this.janela.add(painelSalas,BorderLayout.EAST);
		this.janela.add(painelConversas,BorderLayout.WEST);
		this.janela.add(AreaDeConversa, BorderLayout.CENTER);
	}

	private void design()
	{
		JPanel painelEscolhaDeSala = new JPanel();
		painelEscolhaDeSala.setLayout(new FlowLayout());



			this.janela.addComponentListener (new TratadorDeRedimensionamento());
			this.janela.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			this.janela.setVisible(true);
	}
	/*
	public void mostra(String textoR)
	{
		AreaDeConversa.append(textoR + "\n");
	}
	*/
}