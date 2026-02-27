/**
 * Representa uma Barca no jogo Battleship.
 * A Barca é uma subclasse de {@link Ship}, sendo um navio com tamanho fixo de 1 posição.
 * A sua posição no tabuleiro é definida por uma posição inicial e por uma orientação (bearing).
 *    No entanto, devido ao seu tamanho unitário, a orientação não altera
 *    a forma como ocupa o tabuleiro, correspondendo sempre a uma única célula.
 */

package iscteiul.ista.battleship;

public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";
 /**
     * Constrói uma nova instância de Barca com a orientação e posição inicial especificadas.
     * Este construtor inicializa o navio e adiciona automaticamente a única coordenada 
     * que a Barca ocupará no tabuleiro.
     *
     * @param bearing a orientação (ponto cardeal) associada à Barca
     * @param pos     a coordenada inicial para o posicionamento da Barca
     * @throws NullPointerException se a orientação ({@code bearing}) fornecida for {@code null}
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }
  /*
     * Devolve o tamanho ocupado pela Barca no tabuleiro.
     *
     * @return o tamanho do navio (sempre 1 para a Barca)
     * @see Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
