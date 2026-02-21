/**
 * Pacote que contém as classes relacionadas com a lógica do jogo Batalha Naval (Battleship).
 */
package iscteiul.ista.battleship;

/**
 * Representa um navio do tipo Nau (Carrack) no jogo Batalha Naval.
 * * A Nau é um tipo específico de navio que ocupa 3 posições (células) consecutivas 
 * no tabuleiro. As posições são calculadas no momento da sua criação, com base 
 * na posição inicial e na sua orientação (norte, sul, este ou oeste).
 */
public class Carrack extends Ship {
    
    /** O tamanho físico da Nau no tabuleiro (número de células que ocupa). */
    private static final Integer SIZE = 3;
    
    /** O nome identificativo deste tipo de navio. */
    private static final String NAME = "Nau";

    /**
     * Construtor da classe Carrack.
     * * Inicializa uma nova Nau definindo o seu nome, orientação e posição inicial. 
     * Calcula também as restantes posições que o navio irá ocupar no tabuleiro 
     * com base na sua orientação. Se a orientação for Norte ou Sul, o navio é 
     * disposto verticalmente. Se for Este ou Oeste, é disposto horizontalmente.
     * * @param bearing A orientação do navio (ex: NORTH, SOUTH, EAST, WEST).
     * @param pos     A posição inicial (coordenada base) onde o navio começa a ser colocado no tabuleiro.
     * @throws IllegalArgumentException Lançada caso a orientação (bearing) fornecida não seja válida ou reconhecida.
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERRO! Orientação (bearing) inválida para a Nau (Carrack).");
        }
    }

    /**
     * Obtém o tamanho da Nau.
     * * @return O número de células que este navio ocupa no tabuleiro (3).
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
