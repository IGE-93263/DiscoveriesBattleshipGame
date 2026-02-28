package iscteiul.ista.battleship;

import java.util.List;

/**
 * Interface que define o comportamento de um navio no jogo Battleship.
 * <p>
 * Um navio:
 * </p>
 * <ul>
 *   <li>tem uma categoria (ex.: "Barca", "Nau", ...);</li>
 *   <li>ocupa um conjunto de posições ({@link IPosition}) no tabuleiro;</li>
 *   <li>tem uma orientação ({@link Compass}) e uma posição de referência (normalmente a posição inicial);</li>
 *   <li>pode ser atingido por disparos e eventualmente afundar.</li>
 * </ul>
 *
 * <p>
 * As implementações devem garantir consistência entre:
 * {@link #getPositions()}, {@link #occupies(IPosition)} e os limites devolvidos por
 * {@link #getTopMostPos()}, {@link #getBottomMostPos()}, {@link #getLeftMostPos()} e
 * {@link #getRightMostPos()}.
 * </p>
 */
public interface IShip {

    /**
     * Devolve a categoria do navio (nome do tipo de navio).
     *
     * @return categoria do navio
     */
    String getCategory();

    /**
     * Devolve o tamanho do navio (número de células/posições ocupadas).
     *
     * @return tamanho do navio
     */
    Integer getSize();

    /**
     * Devolve a lista de posições ocupadas pelo navio.
     * <p>
     * A lista representa as células do tabuleiro onde o navio está colocado.
     * </p>
     *
     * @return lista de posições ocupadas
     */
    List<IPosition> getPositions();

    /**
     * Devolve a posição de referência do navio.
     * <p>
     * Tipicamente corresponde à posição usada aquando da criação/colocação do navio.
     * </p>
     *
     * @return posição de referência do navio
     */
    IPosition getPosition();

    /**
     * Devolve a orientação (bearing) do navio.
     *
     * @return orientação do navio
     */
    Compass getBearing();

    /**
     * Indica se o navio ainda está a flutuar.
     *
     * @return {@code true} se o navio ainda não estiver afundado; {@code false} caso contrário
     */
    boolean stillFloating();

    /**
     * Devolve o limite superior (linha mínima) ocupado pelo navio.
     *
     * @return linha mínima ocupada
     */
    int getTopMostPos();

    /**
     * Devolve o limite inferior (linha máxima) ocupado pelo navio.
     *
     * @return linha máxima ocupada
     */
    int getBottomMostPos();

    /**
     * Devolve o limite esquerdo (coluna mínima) ocupado pelo navio.
     *
     * @return coluna mínima ocupada
     */
    int getLeftMostPos();

    /**
     * Devolve o limite direito (coluna máxima) ocupado pelo navio.
     *
     * @return coluna máxima ocupada
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa a posição indicada.
     *
     * @param pos posição a testar
     * @return {@code true} se a posição fizer parte do navio; {@code false} caso contrário
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se existe risco de proximidade/colisão entre este navio e outro navio.
     * <p>
     * A regra concreta (por exemplo, proibir navios adjacentes) depende da implementação.
     * </p>
     *
     * @param other outro navio a comparar
     * @return {@code true} se estiver demasiado perto; {@code false} caso contrário
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se existe risco de proximidade/colisão entre este navio e uma posição.
     *
     * @param pos posição a comparar
     * @return {@code true} se estiver demasiado perto; {@code false} caso contrário
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um disparo na posição indicada.
     * <p>
     * Se a posição pertencer ao navio, deverá ficar marcada como atingida, podendo
     * afectar o resultado de {@link #stillFloating()}.
     * </p>
     *
     * @param pos posição alvejada
     */
    void shoot(IPosition pos);
}