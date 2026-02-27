/**
 * Define as operações que um jogo de Battleship deve disponibilizar.
 * <p>
 * Um jogo é responsável por:
 * <ul>
 *     <li>Processar disparos efetuados sobre o tabuleiro</li>
 *     <li>Registar o histórico de disparos</li>
 *     <li>Manter estatísticas do jogo (acertos, disparos inválidos, repetidos e navios afundados)</li>
 *     <li>Apresentar representações do estado do jogo</li>
 * </ul>
 * </p>
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IGame {

    /**
     * Efetua um disparo para uma determinada posição do tabuleiro.
     *
     * @param pos posição alvo do disparo
     * @return o navio afundado por este disparo, ou {@code null} caso nenhum navio seja afundado
     */
    IShip fire(IPosition pos);

    /**
     * Devolve a lista de disparos válidos registados no jogo.
     *
     * @return lista de posições correspondentes aos disparos efetuados
     */
    List<IPosition> getShots();

    /**
     * Devolve o número de disparos repetidos.
     *
     * @return número de disparos repetidos
     */
    int getRepeatedShots();

    /**
     * Devolve o número de disparos inválidos.
     *
     * @return número de disparos inválidos
     */
    int getInvalidShots();

    /**
     * Devolve o número de disparos que resultaram em acerto.
     *
     * @return número de acertos
     */
    int getHits();

    /**
     * Devolve o número de navios já afundados.
     *
     * @return número de navios afundados
     */
    int getSunkShips();

    /**
     * Devolve o número de navios ainda a flutuar.
     *
     * @return número de navios restantes
     */
    int getRemainingShips();

    /**
     * Imprime o tabuleiro com os disparos válidos registados.
     */
    void printValidShots();

    /**
     * Imprime o tabuleiro com as posições ocupadas pela frota.
     */
    void printFleet();
}