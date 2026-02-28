package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Representa uma coordenada específica no tabuleiro do jogo.
 * <p>
 * Esta classe implementa {@link IPosition} e mantém o estado de uma célula, 
 * nomeadamente a sua linha, coluna, e se está ocupada por um navio ou se já foi atingida por um tiro.
 * </p>
 */
public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Constrói uma nova posição com a linha e coluna especificadas.
     * <p>
     * Por omissão, a posição é inicializada como <strong>não ocupada</strong> (<code>isOccupied = false</code>) 
     * e <strong>não atingida</strong> (<code>isHit = false</code>).
     * </p>
     *
     * @param row    a linha da grelha
     * @param column a coluna da grelha
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Obtém o índice da linha desta posição.
     *
     * @return o valor da linha
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Obtém o índice da coluna desta posição.
     *
     * @return o valor da coluna
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Gera um código de hash baseado na linha, coluna e estados da posição.
     *
     * @return o código hash gerado
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compara esta posição com outro objeto para verificar se são iguais.
     * <p>
     * Duas posições são consideradas iguais se tiverem exatamente a mesma linha e a mesma coluna.
     * </p>
     *
     * @param otherPosition o objeto a ser comparado
     * @return <code>true</code> se as coordenadas forem iguais, <code>false</code> caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Verifica se esta posição é adjacente a outra posição fornecida.
     * <p>
     * A adjacência é definida por uma diferença máxima de 1 unidade, tanto na linha como na coluna (incluindo diagonais).
     * </p>
     *
     * @param other a posição com a qual se pretende verificar a adjacência
     * @return <code>true</code> se for adjacente, <code>false</code> caso contrário
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marca esta posição como ocupada por um navio.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Regista um tiro nesta posição, marcando-a como atingida.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Verifica se a posição está ocupada por um navio.
     *
     * @return <code>true</code> se estiver ocupada, <code>false</code> caso contrário
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Verifica se a posição já foi atingida por um tiro.
     *
     * @return <code>true</code> se já foi atingida, <code>false</code> caso contrário
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Retorna uma representação em formato de texto das coordenadas da posição.
     *
     * @return uma string no formato <code>"Linha = X Coluna = Y"</code>
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}