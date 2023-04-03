import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;


public class GUIGussCharacter extends JFrame {

    /*-----------------------------------------------GUI Launcher-----------------------------------------------------*/
    private JPanel pnlTitleMode, pnlMode;
    private JLabel lblTitle, lblMode, lblBackgroundTitle;

    private ComboBoxMode cbMode;
    private JCheckBox chbAdMode;
    private Font fontOrbitronBlack, fontOrbitron;
    private Dimension sizePnlTitMode, sizeLblTitle;

    private void createPnlTitle(){
        pnlTitleMode = new JPanel();
        sizePnlTitMode = pnlTitleMode.getPreferredSize();

        pnlTitleMode.setLayout(null);

        lblBackgroundTitle = new JLabel(new ImageIcon(path + "/img/Background.jpg"));
        lblBackgroundTitle.setBounds(0,0,getWidth(),getHeight());

        lblTitle = new JLabel("Guess the Character");
        fontOrbitronBlack = font(50, path + "/Font/Orbitron-Black.ttf");
        lblTitle.setFont(fontOrbitronBlack);
        lblTitle.setForeground(Color.WHITE);
        sizeLblTitle = lblTitle.getPreferredSize();
        lblTitle.setBounds(((getWidth()/2) - (lblTitle.getWidth()/2)), (int) ((140 + (getHeight() * 0.1)) / 3),
                (int) (sizeLblTitle.width + (getWidth() * 0.1)), (int) (sizeLblTitle.height + (getHeight() * 0.1)));

        lblTitle.setComponentZOrder(lblBackgroundTitle,0);

        lblMode = new JLabel("Chose the mode");
        fontOrbitron = font(30, path + "/img/Orbitron-VariableFont_wght.ttf");
        lblMode.setFont(fontOrbitron);
        lblMode.setForeground(Color.WHITE);
        lblMode.setBounds((int) ((getWidth()/2) - (lblTitle.getWidth()/2)), (int) ((140 + (getHeight() * 0.1)) / 4),
                (int) (sizeLblTitle.width + (getWidth() * 0.1)), (int) (sizeLblTitle.height + (getHeight() * 0.1)));

        pnlTitleMode.add(lblTitle);
        pnlTitleMode.add(lblMode);
        pnlTitleMode.add(lblBackgroundTitle);
    }

    public void createPnlMode(){
        pnlMode = new JPanel();
        pnlMode.setLayout(null);
        pnlMode.setBounds(0, pnlTitleMode.getHeight(), getWidth(), 200);
        pnlMode.setBackground(Color.RED);

        cbMode = new ComboBoxMode();
        cbMode.addItem();
        cbMode.setBounds(0,0, 200,50);

        pnlMode.add(cbMode);
    }

    /*--------------------------------------------------GUI GAME------------------------------------------------------*/

    private JPanel pnlMatchProgress, pnlImageTimer;
    private JProgressBar pbMatchProgress;
    private JLabel lblTimer, lblCounterRound, lblImageCharacter;
    private Vector<JButton> btnAnswer;
    private ImageIcon imageIcon, scaledImageIcon;
    private Image image, scaledImage;
    private Dimension sizeBar, sizeLblCR, sizePnl, sizeLblT, sizeImage;
    private void createPnlMatchProgress(){

        pbMatchProgress = new JProgressBar(0,10);
        pnlMatchProgress = new JPanel();
        lblCounterRound = new JLabel("1 of 10");

        sizeLblCR = lblCounterRound.getPreferredSize();
        sizePnl = pnlMatchProgress.getPreferredSize();
        sizeBar = pbMatchProgress.getPreferredSize();

        pnlMatchProgress.setLayout(null);
        pnlMatchProgress.setBounds(0, 0, getWidth(), sizeBar.height);
        //pnlMatchProgress.setBackground(Color.RED);

        pbMatchProgress.setValue(1);
        pbMatchProgress.setBounds(sizeLblCR.width + 15, 0, pnlMatchProgress.getWidth() - sizeLblCR.width - 20, sizeBar.height);

        lblCounterRound.setBounds(5, 0, sizeLblCR.width + 5, sizeLblCR.height + 5);

        pnlMatchProgress.add(lblCounterRound);
        pnlMatchProgress.add(pbMatchProgress);
    }

    private void createPnlImageTimer(){
        pnlImageTimer = new JPanel();
        lblTimer = new JLabel("00:20", SwingConstants.CENTER);
        lblImageCharacter = new JLabel();
        sizeLblT = lblTimer.getPreferredSize();
        sizeImage = new Dimension(getWidth(), 300);

        pnlImageTimer.setLayout(null);
        pnlImageTimer.setBounds(0, sizeBar.height, getWidth(), getHeight() - 300);

        lblTimer.setBounds(5, pnlMatchProgress.getHeight() + 5, (int) (getWidth() * 0.2), (int) (getHeight() * 0.1));
        lblTimer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        imageIcon = new ImageIcon(path + "/img/img2.jpg");
        image = imageIcon.getImage();
        scaledImage = image.getScaledInstance(getWidth()+1, 300, Image.SCALE_SMOOTH);
        scaledImageIcon = new ImageIcon(scaledImage);

        lblImageCharacter.setIcon(scaledImageIcon);
        lblImageCharacter.setBounds(0, sizeBar.height, getWidth(), 300);

        pnlImageTimer.add(lblTimer);
        pnlImageTimer.add(lblImageCharacter);
    }

    private void createPnlAnswers(){
        btnAnswer = new Vector<>();
        //for (int i = 0; i < ; i++) {

        //}
    }

    /*-------------------------------------------------Config app-----------------------------------------------------*/

    private String path;
    private int[] changeTextureFrame;

    private GraphicsEnvironment ge;
    private Container c;
    private JLabel lblBackground;
    public void updateApp(){
        c = getContentPane();

        if(changeTextureFrame[0] == 0){
            if(changeTextureFrame[1] == 0){
                createPnlTitle();
                createPnlMode();

                c.add(pnlTitleMode);
                c.add(pnlMode);
                c.add(lblBackground);


                c.addComponentListener(new java.awt.event.ComponentAdapter() {
                    public void componentResized(java.awt.event.ComponentEvent evt) {

                        lblBackground.setBounds(0,0,getWidth(),getHeight());
                        lblBackgroundTitle.setBounds(0,0,getWidth(),getHeight());
                        pnlTitleMode.setBounds(0,0, getWidth(), (int) (140 + (getHeight() * 0.2)));

                        fontOrbitronBlack = font((float) (36 + (getWidth() * 0.01)), path + "/Font/Orbitron-Black.ttf");
                        lblTitle.setFont(fontOrbitronBlack);


                        fontOrbitron = font((float) (36 + (getWidth() * 0.01)), path + "/Font/Orbitron-VariableFont_wght.ttf");
                        lblMode.setFont(fontOrbitron);

                        lblTitle.setBounds((int) ((getWidth()/2) - (lblTitle.getWidth()/2.25)) + 30, (int) ((140 + (pnlTitleMode.getHeight() * 0.1)) / 2.5),
                                (int) (sizeLblTitle.width + (getWidth() * 0.1)), (int) (sizeLblTitle.height + (getHeight() * 0.1)));

                        lblMode.setBounds((int) ((getWidth()/2) - (lblMode.getWidth()/2.5)) + 70, (int) ((140 + (pnlTitleMode.getHeight() * 0.2))/1.2),
                                (int) (sizeLblTitle.width + (getWidth() * 0.1)), (int) (sizeLblTitle.height + (getHeight() * 0.1)));

                        pnlMode.setBounds(0, pnlTitleMode.getHeight(), getWidth(), (int) (80 + (getHeight() * 0.3)));
                    }
                });
                setMinimumSize(new Dimension(1000,700));
            } else if (changeTextureFrame[1] == 1) {

            }
        } else {
            if(changeTextureFrame[1] == 1){
                
            }

            createPnlMatchProgress();
            createPnlImageTimer();
            createPnlAnswers();

            c.add(pnlMatchProgress);
            c.add(pnlImageTimer);

            c.addComponentListener(new java.awt.event.ComponentAdapter() {
                public void componentResized(java.awt.event.ComponentEvent evt) {
                    lblTimer.setBounds(10, 15, (int) (getWidth() * 0.2), (int) (getHeight() * 0.1));

                    sizeImage.setSize(getWidth(), 300);
                    Image scaledImage = image.getScaledInstance(getWidth()+1, getHeight()-300, Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                    lblImageCharacter.setIcon(scaledImageIcon);
                    lblImageCharacter.setBounds(0, 0, getWidth(), getHeight() - 300);

                    lblCounterRound.setBounds(5, 0, sizeLblCR.width + 5, sizeLblCR.height + 5);

                    pnlMatchProgress.setBounds(0, 0, getWidth(), sizeBar.height);
                    pbMatchProgress.setBounds( sizeLblCR.width + 15, 0, pnlMatchProgress.getWidth() - sizeLblCR.width - 20, sizeBar.height);
                    pnlImageTimer.setBounds(0, sizeBar.height, getWidth(), getHeight() - 300);

                }
            });
            setMinimumSize(new Dimension(650,650));
        }
    }

    public Font font(float size, String nameFile){
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(nameFile));
            return font.deriveFont(size);
        } catch (IOException|FontFormatException e) {
            // Handle exception
        }
        return null;
    }

    GUIGussCharacter(){
        super("Absolute Layout");
        setLayout(null);

        path = System.getProperty("user.dir");

        lblBackground = new JLabel(new ImageIcon(path + "/img/Background.jpg"));
        lblBackground.setBounds(0,0,getWidth(),getHeight());

        changeTextureFrame = new int[2];

        changeTextureFrame[0] = 0;
        changeTextureFrame[1] = 0;

        updateApp();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

