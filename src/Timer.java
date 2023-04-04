import javax.swing.*;

public class Timer extends JLabel implements Runnable{
    private int seconds;
    private GUIGussCharacter get;
    private Boolean resetTimer;

    Timer(GUIGussCharacter get){
        this.get = get;
    }
    @Override
    public void run() {
        while(seconds > 0){
            Delay.delay(1000);
            seconds--;
            setName("00:" + seconds);
        }
    }

    public synchronized void resetTimer(){
        JComboBox[] tmpCbMode;
        RoundMode tmpRm;
        if(get.getChbAdMode().isSelected()){
             tmpCbMode = get.getCbModeAd();
             seconds = Integer.valueOf(String.valueOf(tmpCbMode[2]));
        }else{
            tmpRm = get.getCbMode().getRoundMode();
            seconds = tmpRm.getSecondTimer();
        }
    }

}
