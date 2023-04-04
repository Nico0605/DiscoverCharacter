import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ActionBtnStart implements ActionListener {
    GUIGussCharacter get;

    ActionBtnStart(GUIGussCharacter get){
        this.get = get;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Boolean[] tmpBool;

        tmpBool = get.getChangeTextureFrame();
        tmpBool[0] = false;
        tmpBool[1] = true;
        get.setChangeTextureFrame(tmpBool);

        File directory = new File("img/");
        File[] files = directory.listFiles();
        int numberImage = files.length - 1;

        get.setRandomNImage((int) (Math.floor(Math.random() * numberImage) + 1)/2);

        get.updateApp();
        get.getChbAdMode().stopControl();

        new Thread(get.getLblTimer()).start();
    }
}
