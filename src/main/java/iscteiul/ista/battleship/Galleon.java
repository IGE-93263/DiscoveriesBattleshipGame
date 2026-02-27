/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Classe que representa um navio do tipo Galeão no jogo Battleship.
 *
 * <p>O Galeão possui um tamanho fixo de 5 posições no tabuleiro.
 * A sua forma varia consoante a orientação (bearing) definida
 * no momento da sua criação.</p>
 *
 * <p>As orientações possíveis são: NORTH, SOUTH, EAST e WEST.</p>
 */
public class Galleon extends Ship {
    /**
     * Tamanho fixo do Galeão.
     */
    private static final Integer SIZE = 5;
    /**
     * Nome identificador do navio.
     */
    private static final String NAME = "Galeao";

    /**
     * Construtor do Galeão.
     *
     * @param bearing orientação do navio (NORTH, SOUTH, EAST ou WEST)
     * @param pos posição base a partir da qual o navio será colocado
     * @throws NullPointerException se a orientação for nula
     * @throws IllegalArgumentException se a orientação for inválida
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Devolve o tamanho do Galeão.
     *
     * @return número de posições ocupadas pelo Galeão (5)
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Define as posições ocupadas pelo Galeão quando orientado a NORTH.
     *
     * @param pos posição base do navio
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Define as posições ocupadas pelo Galeão quando orientado a SOUTH.
     *
     * @param pos posição base do navio
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Define as posições ocupadas pelo Galeão quando orientado a EAST.
     *
     * @param pos posição base do navio
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Define as posições ocupadas pelo Galeão quando orientado a WEST.
     *
     * @param pos posição base do navio
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
