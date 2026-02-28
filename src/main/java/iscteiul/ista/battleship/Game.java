package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a lógica principal do jogo Battleship.
 * <p>
 * A classe {@code Game} implementa {@link IGame} e gere:
 * <ul>
 *     <li>A frota ({@link IFleet}) que está em jogo</li>
 *     <li>O histórico de tiros efetuados ({@link IPosition})</li>
 *     <li>Estatísticas do jogo (tiros inválidos, repetidos, acertos e navios afundados)</li>
 * </ul>
 * </p>
 *
 * <p>
 * Um tiro é processado da seguinte forma:
 * <ul>
 *     <li>Se for inválido, incrementa o contador de tiros inválidos</li>
 *     <li>Se já tiver sido efetuado, incrementa o contador de tiros repetidos</li>
 *     <li>Caso contrário, regista o tiro e, se existir navio na posição, aplica o disparo</li>
 *     <li>Se o disparo afundar o navio, incrementa o contador de afundamentos e devolve esse navio</li>
 * </ul>
 * </p>
 */
public class Game implements IGame {

    /**
     * Frota associada ao jogo.
     */
    private IFleet fleet;

    /**
     * Lista de posições correspondentes a tiros válidos não repetidos já efetuados.
     */
    private List<IPosition> shots;

    /**
     * Número de tiros inválidos efetuados.
     */
    private Integer countInvalidShots;

    /**
     * Número de tiros repetidos efetuados.
     */
    private Integer countRepeatedShots;

    /**
     * Número total de acertos (tiros que atingiram um navio).
     */
    private Integer countHits;

    /**
     * Número de navios afundados.
     */
    private Integer countSinks;

    /**
     * Constrói uma nova instância de jogo para a frota indicada, inicializando o
     * histórico de tiros e os contadores estatísticos.
     *
     * @param fleet frota a utilizar no jogo
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Efetua um disparo para a posição indicada.
     * <p>
     * Se a posição for inválida, incrementa o contador de tiros inválidos.
     * Se o tiro já tiver sido efetuado, incrementa o contador de tiros repetidos.
     * Caso contrário, regista o tiro; se existir um navio nessa posição, aplica o disparo,
     * contabiliza um acerto e, caso o navio fique afundado, contabiliza um afundamento e
     * devolve o navio afundado.
     * </p>
     *
     * @param pos posição alvo do disparo
     * @return o navio afundado como resultado deste tiro, ou {@code null} caso não tenha sido afundado nenhum navio
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
                //tiro válido
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Devolve a lista de tiros válidos não repetidos efetuados até ao momento.
     *
     * @return lista de posições correspondentes aos tiros registados
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Devolve o número de tiros repetidos efetuados.
     *
     * @return número de tiros repetidos
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Devolve o número de tiros inválidos efetuados.
     *
     * @return número de tiros inválidos
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Devolve o número total de acertos.
     *
     * @return número de acertos
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Devolve o número de navios afundados.
     *
     * @return número de navios afundados
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Devolve o número de navios ainda a flutuar.
     *
     * @return número de navios restantes (a flutuar)
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se um tiro para a posição indicada é válido, i.e., se está dentro dos limites do tabuleiro.
     *
     * @param pos posição a validar
     * @return {@code true} se estiver dentro dos limites do tabuleiro; {@code false} caso contrário
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se um tiro para a posição indicada já foi efetuado anteriormente.
     *
     * @param pos posição a verificar
     * @return {@code true} se o tiro já existir no histórico; {@code false} caso contrário
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Imprime uma representação do tabuleiro no output standard, marcando um conjunto de posições.
     *
     * @param positions posições a marcar no tabuleiro
     * @param marker carácter usado para marcar as posições fornecidas
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }
    }

    /**
     * Imprime o tabuleiro, mostrando os tiros válidos já efetuados.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Imprime o tabuleiro, mostrando todas as posições ocupadas pela frota.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }
}