//interfaces n�o podem ter m�todos abstratos nem vari�veis, apenas constantes

public interface Enviavel extends Serializable //implements � quando mistura classes e interfaces, extends � para do mesmo tipo
{
	Usuario enviador;
	Usuario receptor;
}