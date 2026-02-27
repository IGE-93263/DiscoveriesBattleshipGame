/**
 * Define a especificaçãode uma Frota no jogo Battleship.
 * <p>
 * Uma frota agrega um conjunto de navios ({@link IShip}) e disponibiliza operações
 * para:
 * <ul>
 *     <li>Consultar os navios existentes</li>
 *     <li>Adicionar navios à frota</li>
 *     <li>Filtrar navios por categoria</li>
 *     <li>Obter os navios ainda a flutuar</li>
 *     <li>Determinar se existe um navio numa posição do tabuleiro</li>
 *     <li>Imprimir o estado da frota</li>
 * </ul>
 * </p>
 */
package iscteiul.ista.battleship;

import java.util.List;

public interface IFleet {
    /**
     * Dimensão (lado) do tabuleiro quadrado do jogo.
     */
    Integer BOARD_SIZE = 10;
    /**
     * Número máximo de navios permitido numa frota.
     */
    /**
     * Número máximo de navios permitido numa frota.
     */
    Integer FLEET_SIZE = 10;

    /**
     * Devolve a lista de navios atualmente pertencentes à frota.
     *
     * @return lista de navios da frota
     */
    List<IShip> getShips();

    /**
     * Adiciona um navio à frota.
     *
     * @param s navio a adicionar
     * @return {@code true} se o navio foi adicionado; {@code false} caso contrário
     */
    boolean addShip(IShip s);

    /**
     * Devolve os navios da frota cuja categoria coincide com a categoria indicada.
     *
     * @param category categoria de navio (ex.: "Galeao", "Fragata", ...)
     * @return lista de navios que pertencem à categoria indicada
     */
    List<IShip> getShipsLike(String category);

    /**
     * Devolve os navios que ainda se encontram a flutuar (não afundados).
     *
     * @return lista de navios ainda a flutuar
     */
    List<IShip> getFloatingShips();

    /**
     * Devolve o navio que ocupa uma determinada posição do tabuleiro.
     *
     * @param pos posição a verificar
     * @return o navio que ocupa {@code pos}, ou {@code null} se não existir nenhum
     */
    IShip shipAt(IPosition pos);

    /**
     * Imprime o estado atual da frota (por exemplo, lista de navios e/ou agrupamentos).
     */
    void printStatus();
}