import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ComboBoxMode extends JComboBox<String> {
    //read file with all modality and custom the cb, I put in the constructor a string for the search into file
    //struct of file example: Standard: 10(round) 4(answer) 20(second)

    private String path;
    private RoundMode roundMode;

    ComboBoxMode(){
        new JComboBox<>();
    }

    public void addItem(){
        path = System.getProperty("user.dir");
        Scanner scanner;
        String tmp, tmpSplit[];

        try {
            scanner = new Scanner(new File(path + "/File/Mode.txt"));
            tmpSplit = new String[4];

            while (scanner.hasNextLine()){
                tmp = scanner.nextLine();
                tmpSplit = tmp.split(" ");

                addItem(tmpSplit[0]);
                roundMode = new RoundMode(Integer.valueOf(tmpSplit[1]), Integer.valueOf(tmpSplit[2]), Integer.valueOf(tmpSplit[3]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public RoundMode getRoundMode() {
        return roundMode;
    }

    public void setRoundMode(RoundMode roundMode) {
        this.roundMode = roundMode;
    }
}
