package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma frota de navios no jogo Battleship.
 * <p>
 * A frota mantém uma lista de {@link IShip} e disponibiliza operações para:
 * </p>
 * <ul>
 *   <li>adicionar navios respeitando regras (limite, tabuleiro e colisões);</li>
 *   <li>consultar navios por categoria;</li>
 *   <li>obter navios ainda a flutuar;</li>
 *   <li>descobrir que navio ocupa uma determinada posição.</li>
 * </ul>
 *
 * <p><strong>Nota:</strong> A validação inclui verificar se o navio cabe no tabuleiro
 * e se não fica demasiado perto de outros navios.</p>
 */
public class Fleet implements IFleet {

    /**
     * Imprime para a consola a representação textual de todos os navios recebidos.
     *
     * @param ships lista de navios a imprimir
     * @throws NullPointerException se {@code ships} for {@code null}
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /**
     * Lista interna de navios que compõem a frota.
     */
    private List<IShip> ships;

    /**
     * Constrói uma frota vazia.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Devolve a lista de navios da frota.
     * <p>
     * Atenção: devolve a lista interna (não é uma cópia). Alterações externas
     * à lista podem afetar o estado da frota.
     * </p>
     *
     * @return lista de navios da frota
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um navio à frota, se cumprir as regras:
     * <ul>
     *   <li>não ultrapassar o tamanho máximo da frota ({@link #FLEET_SIZE});</li>
     *   <li>o navio tem de estar totalmente dentro do tabuleiro;</li>
     *   <li>não pode haver risco de colisão/proximidade com navios já existentes.</li>
     * </ul>
     *
     * @param s navio a adicionar
     * @return {@code true} se o navio foi adicionado com sucesso; {@code false} caso contrário
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Obtém todos os navios da frota cuja categoria coincide com a indicada.
     *
     * @param category categoria a filtrar (por exemplo: "Galeao", "Fragata", ...)
     * @return lista de navios dessa categoria (pode ser vazia)
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Obtém todos os navios que ainda estão a flutuar (não afundados).
     *
     * @return lista de navios ainda a flutuar (pode ser vazia)
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Devolve o navio que ocupa a posição indicada, se existir.
     *
     * @param pos posição a testar no tabuleiro
     * @return o {@link IShip} que ocupa a posição, ou {@code null} se não existir nenhum
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Verifica se um navio fica totalmente dentro dos limites do tabuleiro.
     *
     * @param s navio a validar
     * @return {@code true} se o navio couber no tabuleiro; {@code false} caso contrário
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se existe risco de colisão/proximidade entre o navio dado e algum navio já na frota.
     *
     * @param s navio candidato
     * @return {@code true} se for demasiado próximo de algum navio existente; {@code false} caso contrário
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Imprime um resumo do estado da frota:
     * <ul>
     *   <li>todos os navios;</li>
     *   <li>navios ainda a flutuar;</li>
     *   <li>navios agrupados por categoria (por ordem predefinida).</li>
     * </ul>
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Imprime todos os navios da frota que pertencem à categoria indicada.
     *
     * @param category categoria de navios a imprimir
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Imprime todos os navios da frota que ainda estão a flutuar.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Imprime todos os navios existentes na frota.
     */
    void printAllShips() {
        printShips(ships);
    }

}
