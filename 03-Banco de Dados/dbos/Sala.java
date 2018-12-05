package bd.dbos;

public class Sala
{
	protected String nome;
	protected int cod;
	protected int qtdMax;
	protected int qtdOcupado;

	public Sala(String n, int q, int c)
		{
			this.nome = n;
			this.cod = c;
			this.qtdMax = q;
		}

		public void setNome(String n)
		{
			this.nome = n;
		}

		public void setQtdMax(int q)
		{
			this.qtdMax = q;
		}

		public String getNome()
		{
			return this.nome;
		}

		public int getQtdMax()
		{
			return this.qtdMax;
		}

		public int getCod()
		{
			return this.cod;
		}

		public int getQtdOcupado()
		{
			return this.qtdOcupado;
		}

		public boolean isCheia()
		{
			if(this.qtdOcupado == this.qtdMax)
				return true;

			return false;
		}

		public String toString()
		{
			return "Nome da sala: " + this.nome + " Código: " + this.cod + " Lugares disponíveis: " + this.getQtdOcupado();
		}

		public boolean equals(Object obj)
		{
			if(obj == this)
				return true;

			if(obj == null)
				return false;

			if(obj.getClass() != this.getClass())
				return false;

			Sala sala = (Sala)obj;

			if(this.cod != sala.cod)
				return false;

			return true;
		}

		public int hashCode()
		{
			int ret = 5;

			ret = ret*2 + new Integer(this.cod).hashCode();

			ret = ret*2 + new Integer(this.qtdMax).hashCode();

			ret = ret*2 + this.nome.hashCode();

			return ret;
	}
}