import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;


public class GUIGussCharacter extends JFrame {

    /*-----------------------------------------------GUI Launcher-----------------------------------------------------*/
    private JPanel pnlTitleMode, pnlMode, pnlStart;
    private JLabel lblTitle, lblMode, lblBackgroundTitle, lblBackgroundMode, lblBackgroundStart;
    private ComboBoxMode cbMode;
    private JComboBox[] cbModeAd;
    private Control chbAdMode;
    private JButton btnStart;
    private Font fontOrbitronBlack, fontOrbitron;
    private Dimension sizePnlTitMode, sizeLblTitle;

    private void createPnlTitle(){
        pnlTitleMode = new JPanel();
        sizePnlTitMode = pnlTitleMode.getPreferredSize();

        pnlTitleMode.setLayout(null);

        lblBackgroundTitle = new JLabel(new ImageIcon(path + "/Background/Background.jpg"));
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

    private void initializeCbModeAd(){
        //round; answer; time
        String[] tmp = {"Round", "Answer", "Time"};

        cbModeAd = new JComboBox[3];

        for (int i = 0; i < 3; i++) {
            cbModeAd[i] = new JComboBox<>();
            cbModeAd[i].addItem(tmp[i]);
        }
        for (int i = 0; i < 20; i++) {
            cbModeAd[0].addItem(i + 1);
        }
        for (int i = 0; i < 4; i++) {
            cbModeAd[1].addItem(i + 1);
        }
        for (int i = 0; i < 59; i++) {
            cbModeAd[2].addItem(i + 1);
        }
    }

    private void createPnlMode(){
        Font tmp = font(20, path + "/Font/Orbitron-Black.ttf");

        pnlMode = new JPanel();
        pnlMode.setLayout(null);
        pnlMode.setBounds(0, pnlTitleMode.getHeight(), getWidth(), 200);

        lblBackgroundMode = new JLabel(new ImageIcon(path + "/Background/Background.jpg"));
        lblBackgroundMode.setBounds(0, -1 * pnlTitleMode.getHeight(), getWidth(), getHeight());

        cbMode = new ComboBoxMode();
        cbMode.addItem();
        cbMode.setFont(tmp);
        cbMode.setBounds((getWidth()/2) - ((int)(200 + (getWidth() * 0.2))/2),pnlMode.getHeight()/10, (int)(200 + (getWidth() * 0.2)),50);

        chbAdMode = new Control("Advanced mode", this);
        chbAdMode.setFont(tmp);
        chbAdMode.setForeground(Color.BLACK);
        chbAdMode.setBounds(0,pnlMode.getHeight()/4,400,50);

        initializeCbModeAd();

        cbModeAd[0].setBounds((getWidth()/2) - 300,200,200,50);
        cbModeAd[0].setFont(tmp);
        cbModeAd[0].setEnabled(false);

        cbModeAd[1].setBounds((getWidth()/2) - 100,200,200,50);
        cbModeAd[1].setFont(tmp);
        cbModeAd[1].setEnabled(false);

        cbModeAd[2].setBounds((getWidth()/2) + 100,200,200,50);
        cbModeAd[2].setFont(tmp);
        cbModeAd[2].setEnabled(false);

        pnlMode.add(cbMode);
        pnlMode.add(chbAdMode);

        pnlMode.add(cbModeAd[0]);
        pnlMode.add(cbModeAd[1]);
        pnlMode.add(cbModeAd[2]);

        pnlMode.add(lblBackgroundMode);
    }

    private void createPnlStart(){
        pnlStart = new JPanel();
        pnlStart.setLayout(null);
        pnlStart.setBounds(0, pnlTitleMode.getHeight() + pnlMode.getHeight(), getWidth(), getWidth() - (pnlTitleMode.getHeight() + pnlMode.getHeight()));

        lblBackgroundStart = new JLabel(new ImageIcon(path + "/Background/Background.jpg"));
        lblBackgroundStart.setBounds(0, -1 * (pnlTitleMode.getHeight() + pnlMode.getHeight()), getWidth(), getHeight());

        btnStart = new JButton("Start");
        Font tmp = font(30, path + "/Font/Orbitron-Black.ttf");
        btnStart.setFont(tmp);
        btnStart.setBounds((getWidth()/2) - ((int)(300 + (getWidth() * 0.1))/2),((pnlStart.getHeight()/2) - (btnStart.getHeight()/2)) - 20, (int) (300 + (getWidth() * 0.1)), 70);
        btnStart.addActionListener(new ActionBtnStart(this));

        pnlStart.add(btnStart);
        pnlStart.add(lblBackgroundStart);
    }

    /*--------------------------------------------------GUI GAME------------------------------------------------------*/

    private JPanel pnlMatchProgress, pnlImageTimer, pnlAnswer, pnlHomeNext;
    private JProgressBar pbMatchProgress;
    private JLabel lblCounterRound, lblImageCharacter;
    private Timer lblTimer;
    private ButtonsAnswers[] btnAnswer;
    private JButton btnHome, btnNext;
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
        lblTimer = new Timer(this);
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
        int numberAnswer;

        if(chbAdMode.isSelected()){
            numberAnswer = Integer.parseInt(String.valueOf(cbModeAd[1].getSelectedItem()));
        }else{
            numberAnswer = cbMode.getRoundMode().getNumberAnswer();
        }

        pnlAnswer = new JPanel();
        if(numberAnswer == 2){
            pnlAnswer.setLayout(new GridLayout(1,2));
        } else if (numberAnswer > 2) {
            pnlAnswer.setLayout(new GridLayout(2, 2));
        }

        pnlAnswer.setBounds(0, pnlMatchProgress.getHeight() + pnlImageTimer.getHeight(), getWidth(), (int)(100 + (getHeight() * 0.2)));
        pnlAnswer.setBackground(Color.RED);

        initializeBtnAnswer(numberAnswer);

    }

    private void initializeBtnAnswer(int numberAnswer){

        btnAnswer = new ButtonsAnswers[numberAnswer];
        System.out.println(numberAnswer);
        for (int i = 0; i < btnAnswer.length; i++) {
            btnAnswer[i] = new ButtonsAnswers(this);
        }
        btnAnswer = ButtonsAnswers.addAnswers(numberAnswer);
        for (int i = 0; i < btnAnswer.length; i++) {
            pnlAnswer.add(btnAnswer[i]);
        }
    }

    private void createPnlHomeNext(){
        pnlHomeNext = new JPanel();
        pnlHomeNext.setLayout(null);
        pnlHomeNext.setBounds(0, pnlMatchProgress.getHeight() + pnlImageTimer.getHeight() + pnlAnswer.getHeight(), getWidth(),
                getHeight() - (pnlMatchProgress.getHeight() + pnlImageTimer.getHeight() + pnlAnswer.getHeight()));

        btnHome = new JButton("Home");
        btnHome.setBounds((getWidth()/2) - 150,0,150, 70);
        btnHome.addActionListener(new ActionBtnHomeNext(this));

        btnNext = new JButton("Next");
        btnNext.setBounds((getWidth()/2) + 150,0,150, 70);
        btnNext.addActionListener(new ActionBtnHomeNext(this));

        pnlHomeNext.add(btnHome);
        pnlHomeNext.add(btnNext);
    }

    /*-------------------------------------------------Config app-----------------------------------------------------*/

    private int randomNImage;
    private String path;
    private Boolean[] changeTextureFrame;
    private Container c;
    private JLabel lblBackground;
    public void updateApp(){
        c = getContentPane();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        if(changeTextureFrame[0]){
            if(changeTextureFrame[1]){
                setLauncher();
            } else if (!changeTextureFrame[1]) {
                c.remove(pnlMatchProgress);
                c.remove(pnlImageTimer);
                setLauncher();
            }
            setMinimumSize(new Dimension(1000,700));
            setBounds((screenSize.width/2) - (1000/2), (screenSize.height/2) - (700/2), 1000, 700);
        } else {
            c.remove(pnlTitleMode);
            c.remove(pnlMode);
            c.remove(pnlStart);

            setGame();

            setMinimumSize(new Dimension(650,650));
            setBounds((screenSize.width/2) - (650/2), (screenSize.height/2) - (650/2), 650, 650);
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
    private void setLauncher(){
        createPnlTitle();
        createPnlMode();
        createPnlStart();

        c.add(pnlTitleMode);
        c.add(pnlMode);
        c.add(pnlStart);

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
                lblBackgroundMode.setBounds(0, -1 * pnlTitleMode.getHeight(), getWidth(), getHeight());
                cbMode.setBounds((getWidth()/2) - ((int)(200 + (getWidth() * 0.2))/2),pnlMode.getHeight()/10, (int)(200 + (getWidth() * 0.2)),50);
                //chbAdMode.setBounds((getWidth()/2) - (chbAdMode.getWidth()/2) - 100,pnlMode.getHeight()/4,200,50);
                chbAdMode.setBounds((getWidth()/2) - (chbAdMode.getWidth()/2) , (int) (100 + (getHeight() * 0.008)),400,50);

                cbModeAd[0].setBounds((getWidth()/2) - 300,200,200,50);
                cbModeAd[1].setBounds((getWidth()/2) - 100,200,200,50);
                cbModeAd[2].setBounds((getWidth()/2) + 100,200,200,50);

                pnlStart.setBounds(0, pnlTitleMode.getHeight() + pnlMode.getHeight(), getWidth(), getHeight() - (pnlTitleMode.getHeight() + pnlMode.getHeight()));
                lblBackgroundStart.setBounds(0, -1 * (pnlTitleMode.getHeight() + pnlMode.getHeight()), getWidth(), getHeight());
                btnStart.setBounds((getWidth()/2) - ((int)(300 + (getWidth() * 0.1))/2),((pnlStart.getHeight()/2) - (btnStart.getHeight()/2)) - 20, (int) (300 + (getWidth() * 0.1)), 70);

            }
        });
    }
    private void setGame(){
        createPnlMatchProgress();
        createPnlImageTimer();
        createPnlAnswers();
        createPnlHomeNext();

        c.add(pnlMatchProgress);
        c.add(pnlImageTimer);
        c.add(pnlAnswer);
        c.add(pnlHomeNext);

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

                pnlAnswer.setBounds(0, pnlMatchProgress.getHeight() + pnlImageTimer.getHeight(), getWidth(), (int)(100 + (getHeight() * 0.1)));

                pnlHomeNext.setBounds(0, pnlMatchProgress.getHeight() + pnlImageTimer.getHeight() + pnlAnswer.getHeight(), getWidth(),
                        getHeight() - (pnlMatchProgress.getHeight() + pnlImageTimer.getHeight() + pnlAnswer.getHeight()));


                btnNext.setBounds((getWidth()/2),(pnlHomeNext.getHeight()/2) - (btnNext.getHeight()/2) - 15,150, 70);
                btnHome.setBounds((getWidth()/2) - 150,(pnlHomeNext.getHeight()/2) - (btnHome.getHeight()/2) - 15,150, 70);

                System.out.println(" btn home" + ((pnlHomeNext.getHeight()/2) - (btnNext.getHeight()/2)) + " btn next" + ((pnlHomeNext.getHeight()/2) - (btnHome.getHeight()/2)));
            }
        });
    }

    GUIGussCharacter(){
        super("Absolute Layout");
        setLayout(null);

        path = System.getProperty("user.dir");

        lblBackground = new JLabel(new ImageIcon(path + "/img/Background.jpg"));
        lblBackground.setBounds(0,0,getWidth(),getHeight());

        changeTextureFrame = new Boolean[2];

        changeTextureFrame[0] = true;
        changeTextureFrame[1] = true;

        updateApp();
        new Thread(chbAdMode).start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JComboBox[] getCbModeAd() {
        return cbModeAd;
    }

    public void setCbModeAd(JComboBox[] cbModeAd) {
        this.cbModeAd = cbModeAd;
    }

    public ComboBoxMode getCbMode() {
        return cbMode;
    }

    public void setCbMode(ComboBoxMode cbMode) {
        this.cbMode = cbMode;
    }

    public Control getChbAdMode() {
        return chbAdMode;
    }

    public void setChbAdMode(Control chbAdMode) {
        this.chbAdMode = chbAdMode;
    }

    public Timer getLblTimer() {
        return lblTimer;
    }

    public void setLblTimer(Timer lblTimer) {
        this.lblTimer = lblTimer;
    }

    public Boolean[] getChangeTextureFrame() {
        return changeTextureFrame;
    }

    public void setChangeTextureFrame(Boolean[] changeTextureFrame) {
        this.changeTextureFrame = changeTextureFrame;
    }

    public int getRandomNImage() {
        return randomNImage;
    }

    public void setRandomNImage(int randomNImage) {
        this.randomNImage = randomNImage;
    }
}

