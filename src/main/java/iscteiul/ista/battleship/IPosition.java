package iscteiul.ista.battleship;

/**
 * Representa uma posição (coordenada) no tabuleiro do jogo Battleship.
 * <p>
 * Uma posição é identificada por linha ({@code row}) e coluna ({@code column}) e mantém
 * o seu estado lógico no jogo, nomeadamente:
 * </p>
 * <ul>
 *   <li>se está ocupada por um navio;</li>
 *   <li>se já foi atingida por um disparo.</li>
 * </ul>
 *
 * <p>
 * As implementações devem garantir que {@link #equals(Object)} é consistente com a noção
 * de “mesma coordenada” (mesma linha e mesma coluna).
 * </p>
 *
 * @author fba
 */
public interface IPosition {

    /**
     * Devolve a linha da posição.
     *
     * @return número da linha
     */
    int getRow();

    /**
     * Devolve a coluna da posição.
     *
     * @return número da coluna
     */
    int getColumn();

    /**
     * Indica se este objecto é igual a outro.
     * <p>
     * Tipicamente, duas posições são iguais se tiverem a mesma linha e a mesma coluna.
     * </p>
     *
     * @param other objecto a comparar
     * @return {@code true} se representarem a mesma coordenada; {@code false} caso contrário
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente a outra posição.
     * <p>
     * A definição exacta de adjacência depende da regra do jogo adoptada (por exemplo,
     * adjacência ortogonal e/ou diagonal).
     * </p>
     *
     * @param other outra posição a testar
     * @return {@code true} se as posições forem adjacentes; {@code false} caso contrário
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca a posição como ocupada por um navio.
     */
    void occupy();

    /**
     * Regista um disparo nesta posição, marcando-a como atingida.
     */
    void shoot();

    /**
     * Indica se a posição está ocupada por um navio.
     *
     * @return {@code true} se estiver ocupada; {@code false} caso contrário
     */
    boolean isOccupied();

    /**
     * Indica se a posição já foi atingida por um disparo.
     *
     * @return {@code true} se já tiver sido atingida; {@code false} caso contrário
     */
    boolean isHit();
}