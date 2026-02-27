package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa um navio genérico no jogo Batalha Naval.
 * <p>
 * Esta é uma classe abstrata que serve de base para todos os tipos de navios no jogo 
 * (ex: Caravela, Galeão, Fragata). Define os atributos comuns como categoria, orientação, 
 * posição inicial e a lista de posições ocupadas no tabuleiro.
 * </p>
 */
public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Método fábrica (<em>Factory Method</em>) para construir instâncias de navios específicos.
     *
     * @param shipKind o tipo de navio a construir (ex: <code>"caravela"</code>, <code>"galeao"</code>)
     * @param bearing  a orientação do navio no tabuleiro
     * @param pos      a posição inicial
     * @return uma instância de uma subclasse de {@link Ship} correspondente ao tipo pedido, ou <code>null</code> se o tipo for desconhecido
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    private String category;
    private Compass bearing;
    private IPosition pos;
    protected List<IPosition> positions;

    /**
     * Construtor base para os navios.
     *
     * @param category o nome da categoria do navio (ex: "Caravela")
     * @param bearing  a orientação do navio (não pode ser <code>null</code>)
     * @param pos      a posição inicial do navio (não pode ser <code>null</code>)
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Obtém a categoria do navio.
     *
     * @return o nome da categoria em formato string
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Obtém a lista de todas as posições que o navio ocupa no tabuleiro.
     *
     * @return uma lista de objetos do tipo {@link IPosition}
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Obtém a posição inicial (referência) do navio.
     *
     * @return a coordenada inicial
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Obtém a orientação atual do navio.
     *
     * @return o ponto cardeal ({@link Compass})
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Verifica se o navio ainda está a flutuar (ou seja, se tem pelo menos uma posição que não foi atingida).
     *
     * @return <code>true</code> se ainda flutua, <code>false</code> se estiver totalmente afundado
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Obtém a linha mais acima (menor índice de linha) ocupada pelo navio.
     *
     * @return o índice da linha mais acima
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Obtém a linha mais abaixo (maior índice de linha) ocupada pelo navio.
     *
     * @return o índice da linha mais abaixo
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Obtém a coluna mais à esquerda (menor índice de coluna) ocupada pelo navio.
     *
     * @return o índice da coluna mais à esquerda
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Obtém a coluna mais à direita (maior índice de coluna) ocupada pelo navio.
     *
     * @return o índice da coluna mais à direita
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se o navio ocupa uma determinada posição no tabuleiro.
     *
     * @param pos a posição a testar
     * @return <code>true</code> se o navio ocupar a posição, <code>false</code> caso contrário
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se este navio está demasiado perto de outro navio, quebrando as regras de espaçamento.
     *
     * @param other o outro navio a testar
     * @return <code>true</code> se estiver demasiado perto, <code>false</code> caso as posições sejam válidas
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se o navio está adjacente ou sobreposto a uma dada posição.
     *
     * @param pos a posição a testar
     * @return <code>true</code> se estiver demasiado perto, <code>false</code> caso contrário
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * Regista um tiro numa posição específica pertencente ao navio.
     *
     * @param pos a posição atingida pelo tiro
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Retorna uma representação descritiva do navio.
     *
     * @return uma string no formato <code>"[categoria orientação posição_inicial]"</code>
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }
}