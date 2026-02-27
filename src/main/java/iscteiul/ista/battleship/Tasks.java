/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Scanner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Classe utilitária que agrega um conjunto de tarefas de teste/execução
 * para o jogo Battleship.
 *
 * <p>
 * Esta classe disponibiliza vários métodos estáticos (taskA, taskB, taskC e taskD)
 * que permitem testar incrementalmente funcionalidades do projeto, tais como:
 * criação de navios, construção de frotas, verificação de estado e execução de
 * rondas de disparos no contexto de um jogo.
 * </p>
 *
 * <p>
 * As tarefas são interativas e obtêm dados através de um {@link Scanner} associado
 * ao input padrão, processando comandos textuais introduzidos pelo utilizador.
 * </p>
 *
 * <p>
 * As mensagens de saída são registadas através de um {@link Logger} (Log4j).
 * </p>
 */
public class Tasks {
    /**
     * Logger usado para apresentar mensagens ao utilizador.
     */
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * Número de disparos por ronda (rajada).
     */
    private static final int NUMBER_SHOTS = 3;
    /**
     * Mensagem apresentada ao terminar a execução das tarefas.
     */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";
    /**
     * Comando para criar uma nova frota.
     */
    private static final String NOVAFROTA = "nova";
    /**
     * Comando para terminar/abandonar a tarefa.
     */
    private static final String DESISTIR = "desisto";
    /**
     * Comando para efetuar uma rajada de disparos.
     */
    private static final String RAJADA = "rajada";
    /**
     * Comando para listar tiros válidos (no contexto do jogo).
     */
    private static final String VERTIROS = "ver";
    /**
     * Comando de batota para visualizar o mapa/frota (apenas em algumas tarefas).
     */
    private static final String BATOTA = "mapa";
    /**
     * Comando para apresentar o estado atual da frota.
     */
    private static final String STATUS = "estado";


    /////////////////////////////////////////////////////////////////////////////
    // A partir daqui encontram-se métodos que podem ser convertidos em testes
    // automáticos, desde que sejam introduzidas alterações apropriadas.
    // Estes métodos ilustram também um desenvolvimento incremental do projeto.
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Tarefa A: testa a criação de navios.
     *
     * <p>
     * Para cada navio lido, são lidas {@value #NUMBER_SHOTS} posições e é indicado
     * se o navio ocupa ou não cada uma dessas posições.
     * </p>
     *
     * <p>
     * A leitura continua enquanto existirem dados disponíveis no {@link Scanner}.
     * </p>
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Tarefa B: testa a construção de frotas.
     *
     * <p>
     * Permite ao utilizador criar uma nova frota (comando {@value #NOVAFROTA})
     * e consultar o estado da frota (comando {@value #STATUS}). Termina quando
     * o comando {@value #DESISTIR} é introduzido.
     * </p>
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tarefa C: testa a construção de frotas, incluindo a possibilidade de batota.
     *
     * <p>
     * Para além dos comandos da Tarefa B, permite o comando {@value #BATOTA},
     * que escreve no logger uma representação do estado interno da frota.
     * Termina quando o comando {@value #DESISTIR} é introduzido.
     * </p>
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tarefa D: testa a construção de frotas e o elemento de combate do jogo.
     *
     * <p>
     * Permite criar uma frota (comando {@value #NOVAFROTA}), consultar o estado
     * ({@value #STATUS}), visualizar a frota no jogo (batota: {@value #BATOTA}),
     * executar uma rajada de disparos ({@value #RAJADA}) e listar tiros válidos
     * ({@value #VERTIROS}).
     * </p>
     *
     * <p>
     * Após cada rajada, são apresentados indicadores do jogo (hits, tiros inválidos,
     * tiros repetidos e navios restantes). Termina quando o comando
     * {@value #DESISTIR} é introduzido.
     * </p>
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Constrói uma frota com base nos dados fornecidos pelo utilizador.
     *
     * <p>
     * Vai lendo navios do {@link Scanner} e tenta adicioná-los à frota até atingir
     * o tamanho definido por {@code Fleet.FLEET_SIZE}. Para cada navio criado,
     * é efetuada uma tentativa de inserção na frota.
     * </p>
     *
     * @param in scanner a partir do qual são lidos os dados dos navios
     * @return a frota construída
     * @throws AssertionError se {@code in} for {@code null} e as asserções estiverem ativas
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i represents the total of successfully created ships

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Lê os dados de um navio, constrói-o e devolve a instância criada.
     *
     * <p>
     * O formato esperado é: tipo_de_navio, linha, coluna e orientação (carácter).
     * A orientação é convertida para {@link Compass} e o navio é instanciado através
     * do metodo fábrica {@link Ship#buildShip(String, Compass, Position)}.
     * </p>
     *
     * @param in scanner a partir do qual são lidos os dados
     * @return o navio criado, ou {@code null} se o tipo não for reconhecido (dependendo da implementação do factory)
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Lê uma posição do tabuleiro a partir de um {@link Scanner}.
     *
     * <p>
     * O formato esperado é: linha coluna (dois inteiros).
     * </p>
     *
     * @param in scanner a partir do qual são lidos os valores
     * @return a posição lida
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Executa uma ronda de disparos (rajada) no contexto de um jogo.
     *
     * <p>
     * São lidas {@value #NUMBER_SHOTS} posições e, para cada uma, é efetuado um
     * disparo através de {@link IGame#fire(IPosition)}.
     * Caso o disparo atinja um navio (i.e., devolva um {@link IShip} não nulo),
     * é registada uma mensagem a indicar o tipo/categoria do navio atingido.
     * </p>
     *
     * @param in scanner a partir do qual são lidas as posições dos disparos
     * @param game instância do jogo que gere o estado da frota e dos disparos
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }

    }

}
