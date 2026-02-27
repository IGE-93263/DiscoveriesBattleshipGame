package iscteiul.ista.battleship;

/**
 * Enumeração que representa os pontos cardeais e orientações no tabuleiro de jogo.
 * <p>
 * Utilizada para definir a direção de colocação dos navios. Inclui as seguintes direções:
 * </p>
 * <ul>
 * <li><strong>NORTH</strong>: Norte ('n')</li>
 * <li><strong>SOUTH</strong>: Sul ('s')</li>
 * <li><strong>EAST</strong>: Este/Leste ('e')</li>
 * <li><strong>WEST</strong>: Oeste ('o')</li>
 * <li><strong>UNKNOWN</strong>: Desconhecido ('u')</li>
 * </ul>
 *
 * @author fba
 */
public enum Compass {
    NORTH('n'),
    SOUTH('s'),
    EAST('e'),
    WEST('o'),
    UNKNOWN('u');

    private final char c;

    /**
     * Construtor da orientação.
     *
     * @param c o caráter associado à direção
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Obtém o caráter identificativo desta direção.
     *
     * @return um <code>char</code> que representa a direção
     */
    public char getDirection() {
        return c;
    }

    /**
     * Retorna a representação em texto do caráter da direção.
     *
     * @return uma string contendo a letra da direção
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um caráter recebido na orientação correspondente do tipo {@link Compass}.
     *
     * @param ch o caráter a ser traduzido (ex: <code>'n'</code>, <code>'s'</code>)
     * @return a instância de <code>Compass</code> correspondente, ou <code>UNKNOWN</code> se o caráter for inválido
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}