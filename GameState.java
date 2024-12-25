package FileIO;

public class GameState {
    private final FileInfoController controller;
    private final int playerBalanceIndex = 1;
    private final int playerWinsIndex = 2;
    private final int dealerWinsIndex = 3;
    public GameState() {
        controller = new FileInfoController("files/GameStateInfo.csv");

    }

    //get methods
    public int getPlayerBalance() {

        return Integer.parseInt(
                controller.getData(controller.getRowNum() - 1, playerBalanceIndex));
    }
    public int getPlayerWins() {

        return Integer.parseInt(
                controller.getData(controller.getRowNum() - 1, playerWinsIndex));
    }

    public int getDealerWins() {

        return Integer.parseInt(
                controller.getData(controller.getRowNum() - 1, dealerWinsIndex));
    }

    public int getNumGames() {
        return controller.getRowNum() - 1;
    }

    //set methods
    public void updateGameInfo(int playerBalance, int playerWins, int dealerWins) {
        setPlayerBalance(playerBalance);
        setPlayerWins(playerWins);
        setDealerWins(dealerWins);



        controller.writeFile();
    }

    public void setPlayerBalance(int playerBalance) {
        controller.updateData(controller.getRowNum(), playerBalanceIndex, "" + playerBalance);
    }

    public void setPlayerWins(int playerWins) {
        controller.updateData(controller.getRowNum(), playerWinsIndex, "" + playerWins);
    }

    public void setDealerWins(int dealerWins) {
        controller.updateData(controller.getRowNum(), dealerWinsIndex, "" + dealerWins);
    }

    public void reset() {
        controller.reset();
    }



}
