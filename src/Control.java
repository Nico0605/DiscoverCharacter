import javax.swing.*;

public class Control extends JCheckBox implements Runnable{
    private GUIGussCharacter get;
    private Boolean stopControl;
    Control(String name, GUIGussCharacter get){
        super(name);
        this.get = get;
    }
    Control(){}

    @Override
    public void run() {

        JComboBox[] tmpCombo;
        ComboBoxMode tmpComboMode;
        stopControl = false;

        while(!stopControl){
            Delay.delay(300);
            if(isSelected()){
                tmpComboMode = get.getCbMode();
                tmpCombo = get.getCbModeAd();

                tmpCombo[0].setEnabled(true);
                tmpCombo[1].setEnabled(true);
                tmpCombo[2].setEnabled(true);

                tmpComboMode.setEnabled(false);

                get.setCbModeAd(tmpCombo);
                get.setCbMode(tmpComboMode);
            } else if (!isSelected()) {
                tmpComboMode = get.getCbMode();
                tmpCombo = get.getCbModeAd();

                tmpCombo[0].setEnabled(false);
                tmpCombo[1].setEnabled(false);
                tmpCombo[2].setEnabled(false);

                tmpComboMode.setEnabled(true);

                get.setCbModeAd(tmpCombo);
                get.setCbMode(tmpComboMode);
            }
        }
    }

    public synchronized void stopControl(){
        stopControl = false;
    }
}
