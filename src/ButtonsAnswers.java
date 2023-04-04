import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class ButtonsAnswers extends JButton{
    private String path;
    private static GUIGussCharacter get;

    ButtonsAnswers(GUIGussCharacter get){
        this.get = get;
    }
    ButtonsAnswers(String name){
        super(name);
    }

    ButtonsAnswers(){

    }

    public static ButtonsAnswers[] addAnswers(int numberAnswer){
        Scanner scanner;
        ButtonsAnswers[] btnTmp = new ButtonsAnswers[numberAnswer];
        Vector<String> tmp = new Vector<>();
        int randomIndex, randomNumberFalse;
        //creo una strategia di per inserire i nomi all'interno dei bottoni, sicuramente in un modo random
        //la risposta giusta deve essere sempre in un bottone differente
        //faccio un math.random e un numero da 0 a numberAnswer il numero che esce sara l'indice in cui metterò la risposta giusta
        //per capire qual'è la risposta giusta uso l'indice dell'immagine - 1, i nomi sono ordinati in base all'immagine
        try {
            scanner = new Scanner(new File("File/FileNameCharacter/nameCharacter.txt"));
            File directory = new File("img/");
            File[] files = directory.listFiles();
            int numberImage = (files.length - 1)/2;

            while (scanner.hasNextLine()){
                tmp.add(scanner.nextLine());
            }

            randomIndex = (int)Math.floor(Math.random() * numberAnswer);
            System.out.println("True" + randomIndex);


            for (int i = 0; i < btnTmp.length; i++) {
                btnTmp[i] = new ButtonsAnswers();
            }

            System.out.println("tmp size" + tmp.size() + " random number" + get.getRandomNImage());

            btnTmp[randomIndex].setText(tmp.get(get.getRandomNImage()));

            System.out.println(btnTmp[randomIndex].getText());
            for (int i = 0; i < btnTmp.length; i++) {
                System.out.println("fuori if" + i);
                randomNumberFalse = (int)Math.floor(Math.random() * tmp.size());//tmp.size da sostituire con numberImage
                if(i != randomIndex){
                    while (randomNumberFalse == get.getRandomNImage()){
                        randomNumberFalse = (int)Math.floor(Math.random() * tmp.size());//tmp.size da sostituire con numberImage
                    }
                    System.out.println();
                    System.out.println(tmp.get(randomNumberFalse));

                    btnTmp[i].setText(tmp.get(randomNumberFalse));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return btnTmp;
    }
}
