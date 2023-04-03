public class RoundMode {
    private int numberRound;
    private int numberAnswer;
    private int secondTimer;

    RoundMode(int numberRound, int numberAnswer, int secondTimer){
        this.numberRound = numberRound;
        this.numberAnswer = numberAnswer;
        this.secondTimer = secondTimer;
    }

    public int getNumberRound() {
        return numberRound;
    }

    public int getNumberAnswer() {
        return numberAnswer;
    }

    public int getSecondTimer() {
        return secondTimer;
    }
}
