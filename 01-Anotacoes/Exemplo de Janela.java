import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;



public class Janela
{
	private JFrame janela = new JFrame("Conversa");
	private JLabel lblMsg = new JLabel("Mensagens:", JLabel.LEFT);
	private JTextField txtEnvia = new JTextField();
	private JTextArea txtRecebe = new JTextArea();
	private JButton btnEnviar = new JButton();
	private ServerSocket pedido;
	private Socket conexao;
	private PrintWriter transmissor;

	private class TratadorDeRedimensionamento implements ComponentListener
	{
		public void componentResized(ComponentEvent e)
		{
			lblMsg.setFont (new Font("Arial", Font.PLAIN, Math.min(janela.getHeight(),janela.getWidth())*26/220));

			Font fonte = new Font("Arial", Font.PLAIN, Math.min(janela.getHeight(),janela.getWidth())*22/220);

			btnEnviar.setFont (fonte);
		}

		public void componentMoved(java.awt.event.ComponentEvent e)
		{}

		public void componentShown(java.awt.event.ComponentEvent e)
		{}

		public void componentHidden(java.awt.event.ComponentEvent e)
		{}
	}

	private class TratadorDeEvento implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String textoE = txtEnvia.getText();
			transmissor.println(textoE);
			transmissor.flush();
			txtRecebe.append("Você: "+textoE+"\n");
			txtEnvia.setText("");
		}
	}

	public Janela(Socket s) throws Exception
	{
		conexao = s;
		transmissor = new PrintWriter(conexao.getOutputStream());
		design();
	}

	private void design()
	{
		this.lblMsg.setFont(new Font("Arial", Font.PLAIN, 26));

		JPanel painel = new JPanel();
		painel.setLayout (new GridLayout(1,2));

		Font fonte = new Font ("Arial", Font.PLAIN, 20);
		TratadorDeEvento tratador = new TratadorDeEvento();

		this.btnEnviar.setFont(fonte);
		this.btnEnviar.setText("Enviar");
		this.btnEnviar.addActionListener(tratador);

		painel.add(txtEnvia);
		painel.add(btnEnviar);

		this.janela.setSize (220,300);
		this.janela.getContentPane().setLayout(new BorderLayout());

		this.janela.add(this.lblMsg,BorderLayout.NORTH);
		this.janela.add(painel,BorderLayout.SOUTH);
		this.janela.add(txtRecebe, BorderLayout.CENTER);

		this.janela.addComponentListener (new TratadorDeRedimensionamento());
		this.janela.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		this.janela.setVisible(true);
	}

	public void mostra(String textoR)
	{
		txtRecebe.append(textoR + "\n");
	}
}