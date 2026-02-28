/**
 * Pacote principal do jogo Batalha Naval.
 */
package iscteiul.ista.battleship;

/**
 * Representa uma <strong>Caravela</strong> no jogo Battleship.
 * <p>
 * A Caravela é uma subclasse de {@link Ship}, caracterizada por ser um navio com tamanho fixo de 2 posições.
 * A sua posição no tabuleiro é definida por uma posição inicial e por uma orientação (<em>bearing</em>).
 * </p>
 * <ul>
 * <li>Se a orientação for <code>NORTH</code> ou <code>SOUTH</code>, o navio ocupa posições na <strong>vertical</strong>.</li>
 * <li>Se a orientação for <code>EAST</code> ou <code>WEST</code>, o navio ocupa posições na <strong>horizontal</strong>.</li>
 * </ul>
 */
public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Constrói uma nova instância de Caravela com a orientação e posição inicial especificadas.
     * <p>
     * Este construtor inicializa o navio e calcula automaticamente todas as coordenadas
     * que a Caravela ocupará no tabuleiro com base no seu tamanho (2) e na sua orientação.
     * </p>
     *
     * @param bearing a orientação (ponto cardeal) para onde a Caravela aponta
     * @param pos     a coordenada inicial para o posicionamento da Caravela
     * @throws NullPointerException     se a orientação (<code>bearing</code>) fornecida for nula
     * @throws IllegalArgumentException se a orientação (<code>bearing</code>) for inválida
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }
    }

    /**
     * Devolve o tamanho ocupado pela Caravela no tabuleiro.
     *
     * @return o tamanho do navio (sempre <code>2</code> para a Caravela)
     * @see Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}