package com.company;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.List;

public class Habitat extends JComponent implements ActionListener, KeyListener,Serializable {
    JFrame f = new JFrame("Lab1-3");
    private ActionListener Actions;
    private static Habitat Instance;
    String text = "Hello, World!";
    private int N1 = 2;
    private int N2 = 3;
    private int P1 = 0;
    private int P2 = 0;
    private boolean isAnim = true;

    private Random random = new Random();

    public int time = 0;
    public int Ttime = 0;

    private Timer t = new Timer(100, this::actionPerformed);
    private Timer t1 = new Timer(N1 * 1000, this::actionPerformed);
    private Timer t2 = new Timer(N2 * 1000, this::actionPerformed);

    public int FishCountGold = 0;
    public int FishCountGuppi = 0;
    public int liveFishGold = 0;
    public int liveFishGuppi = 0;
    public int GoldLifeTime = 0;
    public int GuppiLifeTime = 0;

    private Font font = new Font("Verdana", Font.PLAIN, 11);
    private static JLabel timer = new JLabel("Time");
    private static JPanel panel = new JPanel();
    private static JButton start = new JButton("Старт");
    private static JButton stop = new JButton("Стоп");
    private static JCheckBox show = new JCheckBox("Показывать окно в конце");
    private static JRadioButton showtimeYES = new JRadioButton("Показывать время", true);
    private static JRadioButton showtimeNO = new JRadioButton("Скрывать время", false);
    private static JButton infor = new JButton("Информация");
    private static JButton startAnim = new JButton("Запустить анимацию");
    private static JButton stopAnim = new JButton("Остановить Анимацию");
    private static JButton Console = new JButton("Консоль");
    private static JButton editConsole = new JButton("Ввести вероятность");
    private static JButton viewConsole = new JButton("Получить вероятность");
    private static JButton readset = new JButton("Загрузить");
    private static JButton saveset = new JButton("Сохранить");
    private static ButtonGroup group = new ButtonGroup();

    //menubar
    private JMenuBar menuBar = new JMenuBar();
    private JMenu about = new JMenu("Общее");
    private JMenuItem Menustart = new JMenuItem("Старт");
    private JMenuItem Menustop = new JMenuItem("Стоп");
    private JMenuItem exit = new JMenuItem(new ExitAction());
    private JMenu settings = new JMenu("Настройки");
    private JCheckBoxMenuItem Menushow = new JCheckBoxMenuItem("Показывать окно");
    private JRadioButtonMenuItem MenushowtimeYES = new JRadioButtonMenuItem("Показывать время", true);
    private JRadioButtonMenuItem MenushowtimeNO = new JRadioButtonMenuItem("Скрывать время", false);
    private ButtonGroup Menugroup = new ButtonGroup();

    //settings labels
    private static final JLabel N1Labelinfo = new JLabel("Период рождения Gold");
    private static final JTextField N1Label = new JTextField(3);
    private static final JLabel N2Labelinfo = new JLabel("Период рождения Guppi");
    private static final JTextField N2Label = new JTextField(3);
    private static final JLabel P1Labelinfo = new JLabel("Вероятность Gold");
    private static final JLabel P2Labelinfo = new JLabel("Вероятность Guppi");
    private static final JLabel LifetimeLable1 = new JLabel("Время жизни Gold");
    private static final JTextField LifetimeField1 = new JTextField(4);
    private static final JLabel LifetimeLable2 = new JLabel("Время жизни Guppi");
    private static final JTextField LifetimeField2 = new JTextField(4);
    private static final JLabel Line1 = new JLabel("----------------------------------------- ");
    private static final JLabel Line2 = new JLabel(" -----------------------------------------");
    private static final JLabel Line3 = new JLabel("-----------------------------------------");
    private static final JLabel Line4 = new JLabel("-----------------------------------------");
    private static final JLabel Line5 = new JLabel("----------------------------------------- ");
    private static final JTextArea InfoAr = new JTextArea(5, 15);
    private static final JTextArea ConsD = new JTextArea(5, 20);

    private String[] items = {"10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%"};
    private JComboBox editComboBox1 = new JComboBox(items);
    private JComboBox editComboBox2 = new JComboBox(items);

    //список объектов
    public static Vector<Objects> Object = new Vector<Objects>();
    private static HashSet<Integer> ID = new HashSet<Integer>();
    private static TreeMap<Integer, Integer> Borntime = new TreeMap<Integer, Integer>();

    //диалогогвое окно
    private JDialog dialog = new JDialog();
    private JDialog Infor = new JDialog();
    private JDialog Cons = new JDialog();
    private static JButton Ok = new JButton("Ок");
    private static JButton Ok2 = new JButton("Ок");
    private static JButton Ok3 = new JButton("Выход");
    private static JButton Cancel = new JButton("Отмена");
    private static JLabel info = new JLabel("Nothing");


    public static Habitat GetHabitatInstance() {
        if (Instance == null) {
            Instance = new Habitat();
        }
        return Instance;
    }

    public Habitat() {
        f.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                try (FileReader fr = new FileReader("src/text.txt")) {
                    Scanner scan = new Scanner(fr);
                    N1 = Integer.parseInt(scan.nextLine());
                    P1 = Integer.parseInt(scan.nextLine());
                    GoldLifeTime = Integer.parseInt(scan.nextLine()) * 10;
                    N2 = Integer.parseInt(scan.nextLine());
                    P2 = Integer.parseInt(scan.nextLine());
                    GuppiLifeTime = Integer.parseInt(scan.nextLine()) * 10;
                    t1.setDelay(N1*1000);
                    N1Label.setText(String.valueOf(N1));
                    LifetimeField1.setText(String.valueOf(GoldLifeTime/10));
                    t2.setDelay(N2*1000);
                    N2Label.setText(String.valueOf(N2));
                    LifetimeField2.setText(String.valueOf(GuppiLifeTime/10));
                    editComboBox1.setSelectedIndex(P1/10-1);
                    editComboBox2.setSelectedIndex(P2/10-1);
                    fr.close();
                } catch (IOException ex) {
                }
            }

            public void windowClosing(WindowEvent event) {
                try (FileWriter fw = new FileWriter("src/settings.txt")) {
                    String lineSeparator = System.getProperty("line.separator");
                    fw.write(Integer.toString(N1) + lineSeparator);
                    fw.write(Integer.toString(P1) + lineSeparator);
                    fw.write(Integer.toString(GoldLifeTime/10) + lineSeparator);
                    fw.write(Integer.toString(N2) + lineSeparator);
                    fw.write(Integer.toString(P2) + lineSeparator);
                    fw.write(Integer.toString(GuppiLifeTime/10) + lineSeparator);
                    fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
                System.exit(0);

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        t.setActionCommand("Timer");
        t1.setActionCommand("SpGold");
        t2.setActionCommand("SpGuppi");

        dialog.setModal(true);
        Infor.setModal(true);
        Cons.setModal(false);

        //dialog,infor,Cons
        dialog.setTitle("Итоговая информация");
        Infor.setTitle("Информэйшн");
        Cons.setTitle("консоль");
        dialog.add(info);
        dialog.add(Cancel);
        dialog.add(Ok);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(400, 100);
        Infor.setLayout(new FlowLayout());
        Infor.setSize(400, 300);
        Infor.add(new JScrollPane(InfoAr));
        Infor.add(Ok2);

        Cons.setLayout(new FlowLayout());
        Cons.setSize(400, 400);
        Cons.add(new JScrollPane(ConsD));
        Cons.add(editConsole);
        Cons.add(viewConsole);
        Cons.add(Ok3);


        //menubar
        Menugroup.add(MenushowtimeYES);
        Menugroup.add(MenushowtimeNO);
        Menushow.addActionListener(this::actionPerformed);
        Menustart.addActionListener(this::actionPerformed);
        Menustop.addActionListener(this::actionPerformed);
        MenushowtimeYES.addActionListener(this::actionPerformed);
        MenushowtimeNO.addActionListener(this::actionPerformed);
        LifetimeField1.addActionListener(this::actionPerformed);
        LifetimeField2.addActionListener(this::actionPerformed);

        Menushow.setActionCommand("DopMenu");
        Menustart.setActionCommand("Start");
        Menustop.setActionCommand("Stop");
        MenushowtimeYES.setActionCommand("timeYES");
        MenushowtimeNO.setActionCommand("timeNO");
        LifetimeField1.setActionCommand("Lifetime1");
        LifetimeField2.setActionCommand("Lifetime2");

        about.add(Menustart);
        about.add(Menustop);
        about.addSeparator();
        about.add(exit);
        about.setVisible(true);

        settings.add(Menushow);
        settings.add(MenushowtimeYES);
        settings.add(MenushowtimeNO);

        menuBar.add(about);
        menuBar.add(settings);
        menuBar.setVisible(true);

        //buttons
        start.addActionListener(this::actionPerformed);
        start.setActionCommand("Start");
        start.setBackground(Color.white);
        start.setFocusable(false);
        start.setVisible(true);

        stop.addActionListener(this::actionPerformed);
        stop.setActionCommand("Stop");
        stop.setBackground(Color.white);
        stop.setFocusable(false);
        stop.setVisible(true);

        infor.addActionListener(this::actionPerformed);
        infor.setActionCommand("Info");
        infor.setBackground(Color.white);
        infor.setFocusable(false);
        infor.setVisible(true);

        Ok.addActionListener(this::actionPerformed);
        Ok.setActionCommand("Ok");
        Ok.setBackground(Color.white);

        Ok2.addActionListener(this::actionPerformed);
        Ok2.setActionCommand("Ok2");
        Ok2.setBackground(Color.white);

        Ok3.addActionListener(this::actionPerformed);
        Ok3.setActionCommand("Ok3");
        Ok3.setBackground(Color.white);

        Cancel.addActionListener(this::actionPerformed);
        Cancel.setActionCommand("Cancel");
        Cancel.setBackground(Color.white);

        show.setBackground(Color.yellow);
        showtimeYES.setBackground(Color.yellow);
        showtimeNO.setBackground(Color.yellow);

        startAnim.addActionListener(this::actionPerformed);
        startAnim.setActionCommand("Start");
        startAnim.setBackground(Color.white);
        startAnim.setFocusable(false);
        startAnim.setVisible(true);

        stopAnim.addActionListener(this::actionPerformed);
        stopAnim.setActionCommand("StopAnim");
        stopAnim.setBackground(Color.white);
        stopAnim.setFocusable(false);
        stopAnim.setVisible(true);

        Console.addActionListener(this::actionPerformed);
        Console.setActionCommand("console");
        Console.setBackground(Color.white);
        Console.setFocusable(false);
        Console.setVisible(true);

        editConsole.addActionListener(this::actionPerformed);
        editConsole.setActionCommand("editcons");
        editConsole.setBackground(Color.white);
        editConsole.setFocusable(false);
        editConsole.setVisible(true);

        viewConsole.addActionListener(this::actionPerformed);
        viewConsole.setActionCommand("viewcons");
        viewConsole.setBackground(Color.white);
        viewConsole.setFocusable(false);
        viewConsole.setVisible(true);

        readset.addActionListener(this::actionPerformed);
        readset.setActionCommand("Readset");
        readset.setBackground(Color.white);
        readset.setFocusable(false);
        readset.setVisible(true);

        saveset.addActionListener(this::actionPerformed);
        saveset.setActionCommand("Saveset");
        saveset.setBackground(Color.white);
        saveset.setFocusable(false);
        saveset.setVisible(true);


        //settings Main menu
        show.setFocusable(false);
        show.setActionCommand("DopSettings");
        show.addActionListener(this::actionPerformed);

        showtimeYES.setFocusable(false);
        showtimeYES.setActionCommand("timeYES");
        showtimeYES.addActionListener(this::actionPerformed);

        showtimeNO.setFocusable(false);
        showtimeNO.setActionCommand("timeNO");
        showtimeNO.addActionListener(this::actionPerformed);

        group.add(showtimeYES);
        group.add(showtimeNO);

        //labels
        N1Labelinfo.setFocusable(false);
        N2Labelinfo.setFocusable(false);
        P1Labelinfo.setFocusable(false);
        P2Labelinfo.setFocusable(false);

        N1Label.addActionListener(this::actionPerformed);
        N1Label.setActionCommand("Period1");
        N1Label.setFocusable(true);

        N2Label.addActionListener(this::actionPerformed);
        N2Label.setActionCommand("Period2");
        N2Label.setFocusable(true);

        LifetimeField1.setFocusable(true);
        LifetimeField2.setFocusable(true);
        InfoAr.setEditable(false);

        ConsD.setEditable(true);
        ConsD.setLineWrap(true);
        ConsD.setWrapStyleWord(true);
        //timer
        timer.setFocusable(false);
        timer.setLocation(100, 0);
        timer.setSize(300, 15);
        timer.setForeground(Color.BLACK);
        timer.setFont(font);

        //combobox
        editComboBox1.addActionListener(this::actionPerformed);
        editComboBox1.setActionCommand("Chance1");
        editComboBox1.setFocusable(false);

        editComboBox2.addActionListener(this::actionPerformed);
        editComboBox2.setActionCommand("Chance2");
        editComboBox2.setFocusable(false);

        //panel
        panel.setLayout(new FlowLayout());
        panel.setBounds(1035, 0, 200, 630);
        panel.setBackground(Color.yellow);
        panel.setFocusable(false);
        panel.add(start);
        panel.add(stop);
        panel.add(infor);
        panel.add(Line1);
        panel.add(startAnim);
        panel.add(stopAnim);
        panel.add(Line5);
        panel.add(timer);
        panel.add(Line2);
        panel.add(show);
        panel.add(showtimeYES);
        panel.add(showtimeNO);
        panel.add(Line3);
        panel.add(P1Labelinfo);
        panel.add(editComboBox1);
        panel.add(N1Labelinfo);
        panel.add(N1Label);
        panel.add(LifetimeLable1);
        panel.add(LifetimeField1);
        panel.add(Line4);
        panel.add(P2Labelinfo);
        panel.add(editComboBox2);
        panel.add(N2Labelinfo);
        panel.add(N2Label);
        panel.add(LifetimeLable2);
        panel.add(LifetimeField2);
        panel.add(readset);
        panel.add(saveset);

        panel.add(Console);

        panel.addKeyListener(this);

        //--------------------------------------
        //frame
        f.setLayout(new BorderLayout());
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1250, 700);
        f.setLocation(10, 10);
        f.getContentPane().setBackground(Color.white);
        f.setJMenuBar(menuBar);
        f.addKeyListener(this);
        f.getContentPane().add(panel);
        f.add(this);
        f.setVisible(true);
        f.toFront();
        f.requestFocus();
    }

    class ExitAction extends AbstractAction {
        ExitAction() {
            putValue(NAME, "Выход");
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        g1.drawLine(1, 1, 1034, 1);
        g1.drawLine(1034, 1, 1034, 631);
        g1.drawLine(1034, 631, 1, 631);
        g1.drawLine(1, 631, 1, 1);
        g1.drawLine(1034, 631, 1250, 631);
        for (Objects i : Object) {
            for(Objects a : Object) {
                java.awt.Image img = null;
                if (a.nomer == 1) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(a.x, a.y, 100, 100);
                    g.setColor(Color.BLACK);
                    g.fillOval(a.x+25, a.y+25, 10, 10);
                    g.fillOval(a.x+65, a.y+25, 10, 10);
                    g.drawArc(a.x+20, a.y+50, 60, 30, 180, 180);
                } else {
                    img = new ImageIcon("src/1.png").getImage();
                    g1.drawImage(img, (int) a.x, (int) a.y, 70, 54, this);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SpGold": {
                int x = random.nextInt(100);
                if (x <= P1) {
                    if (isAnim == true) {
                        Object.add(new Text(1));
                    } else {
                        Object.add(new Text(0));
                    }
                    ID.add(Object.get(Object.size() - 1).id);
                    if (GoldLifeTime != 0) {
                        Object.get(Object.size() - 1).Lifetime = GoldLifeTime / 10;
                    }
                    Borntime.put(Object.get(Object.size() - 1).id, time);
                    FishCountGold += 1;
                    liveFishGold += 1;
                }
                break;
            }
            case "SpGuppi": {
                int x = random.nextInt(100);
                if (x <= P2) {
                    if (isAnim == true) {
                        Object.add(new Image(1));
                    } else {
                        Object.add(new Image(0));
                    }
                    ID.add(Object.get(Object.size() - 1).id);
                    if (GuppiLifeTime != 0) {
                        Object.get(Object.size() - 1).Lifetime = GuppiLifeTime / 10;
                    }
                    Borntime.put(Object.get(Object.size() - 1).id, time);
                    FishCountGuppi += 1;
                    liveFishGuppi += 1;
                }
                break;
            }
            case "Timer": {
                time += 1;
                Ttime += 1;
                timer.setText(Integer.toString(time / 10) + " сек");
                if (Ttime == 10) {
                    for (int i = 0; i < Object.size(); i++) {
                        Object.get(i);
                        Object.get(i).Lifetime -= 1;
                        if (Object.get(i).Lifetime == 0) {
                            int id = Object.get(i).id;
                            if(Object.get(i).nomer == 1){
                                liveFishGold -=1;
                            }
                            else if(Object.get(i).nomer == 2){
                                liveFishGuppi -=1;
                            }
                            ID.remove(i);
                            Borntime.remove(id);
                            Object.remove(i);
                        }
                    }
                    Ttime = 0;
                }
                repaint();
                break;
            }
            case "Start": {
                isAnim = true;
                for (Objects a : Object) {
                    a.isanim = 1;
                    a.startAN();
                }
                if (t.isRunning() == false) {
                    time = 0;
                    FishCountGold = 0;
                    FishCountGuppi = 0;
                    Object.clear();
                    repaint();
                    t.start();
                    t1.start();
                    t2.start();
                    repaint();
                }
                break;
            }
            case "Stop": {
                if (t.isRunning() == true) {
                    for (Objects a : Object) {
                        a.isanim = 0;
                        a.stopAN();
                    }
                    t.stop();
                    t1.stop();
                    t2.stop();
                    repaint();
                    if (show.isSelected() == true) {
                        info.setText(timer.getText() + " Золотых: " + Integer.toString(FishCountGold)
                                + " Гуппи: " + Integer.toString(FishCountGuppi));
                        dialog.setVisible(true);
                    }
                    timer.setText(timer.getText() + " Золотых: " + Integer.toString(FishCountGold)
                            + " Гуппи: " + Integer.toString(FishCountGuppi));
                }
                break;
            }
            case "timeYES": {
                showtimeYES.setSelected(true);
                MenushowtimeYES.setSelected(true);
                timer.setVisible(true);
                Line1.setVisible(true);
                break;
            }
            case "timeNO": {
                showtimeNO.setSelected(true);
                MenushowtimeNO.setSelected(true);
                timer.setVisible(false);
                Line1.setVisible(false);
                break;
            }
            case "DopMenu": {
                if (Menushow.isSelected() == true) {
                    show.setSelected(true);
                } else {
                    show.setSelected(false);
                }
                break;
            }
            case "DopSettings": {
                if (show.isSelected() == true) {
                    Menushow.setSelected(true);
                } else {
                    Menushow.setSelected(false);
                }
                break;
            }
            case "Chance1": {
                P1 = (editComboBox1.getSelectedIndex() + 1) * 10;
                requestFocusInWindow();
                f.requestFocus();
                break;
            }
            case "Chance2": {
                P2 = (editComboBox2.getSelectedIndex() + 1) * 10;
                requestFocusInWindow();
                f.requestFocus();
                break;
            }
            case "Period1": {
                N1 = Integer.parseInt(N1Label.getText());
                t1.setDelay(N1 * 1000 + 1);
                requestFocusInWindow();
                f.requestFocus();
                break;
            }
            case "Period2": {
                N2 = Integer.parseInt(N2Label.getText());
                t2.setDelay(N2 * 1000);
                requestFocusInWindow();
                f.requestFocus();
                break;
            }
            case "Ok": {
                dialog.setVisible(false);
                break;
            }
            case "Ok2": {
                t.start();
                t1.start();
                t2.start();
                for (Objects a : Object) {
                    a.startAN();
                }
                Infor.setVisible(false);
                break;
            }
            case "Cancel": {
                t.start();
                t1.start();
                t2.start();
                dialog.setVisible(false);
                break;
            }
            case "Lifetime1": {
                GoldLifeTime = Integer.parseInt(LifetimeField1.getText()) * 10;
                break;
            }
            case "Lifetime2": {
                GuppiLifeTime = Integer.parseInt(LifetimeField2.getText()) * 10;
                break;
            }
            case "Info": {
                t.stop();
                t1.stop();
                t2.stop();
                for (Objects a : Object) {
                    a.stopAN();
                }
                repaint();
                String temps = "";
                for (Map.Entry<Integer, Integer> entry : Borntime.entrySet()) {
                    temps += ("ID:" + entry.getKey() + "   " + "Born time:" + (entry.getValue()) / 10 + "\n");
                }
                InfoAr.setText(temps);
                Infor.setVisible(true);
                break;
            }
            case "StopAnim": {
                isAnim = false;
                for (Objects a : Object) {
                    a.isanim = 0;
                    a.stopAN();
                }
                break;
            }
            case "console": {
                t.stop();
                t1.stop();
                t2.stop();
                for (Objects a : Object) {
                    a.stopAN();
                }
                repaint();
                Cons.setVisible(true);
                ConsD.setText("");
                break;
            }
            case "editcons": {
                String text = "";
                while (text == "") {
                    text += ConsD.getText();
                }
                text = ConsD.getText();
                int fileName = Integer.parseInt(text);
                P1 = fileName;
                ConsD.setText("");
                ConsD.append("Вероятность золотых рыбок: " + P1);
                break;
            }
            case "viewcons": {
                ConsD.setText("");
                ConsD.append("Вероятность золотых рыбок: " + P1);
                break;
            }
            case "Ok3": {
                t.start();
                t1.start();
                t2.start();
                editComboBox1.setSelectedIndex(P1/10-1);
                for (Objects a : Object) {
                    a.startAN();
                }
                Cons.setVisible(false);
                break;
            }
            case "Saveset": {
                try {
                    FileOutputStream fos = new FileOutputStream("src/obj.dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(Object);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            }
            case "Readset": {
                t.stop();
                t1.stop();
                t2.stop();
                try {
                    FileInputStream fis = new FileInputStream("src/obj.dat");
                    ObjectInputStream iis = new ObjectInputStream(fis);
                    Object = (Vector<Objects>) iis.readObject();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                isAnim = true;
                for (Objects a : Object) {
                    a.intel.start();
                    a.isanim = 1;
                    a.startAN();
                }
                t.start();
                t1.start();
                t2.start();
                repaint();
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_B){
            if (t.isRunning()==false){
                time =0;
                FishCountGold = 0;
                FishCountGuppi = 0;
                Object.clear();
                repaint();
                t.start();
                t1.start();
                t2.start();
                repaint();
            }

        }
        if(e.getKeyCode()==KeyEvent.VK_E){
            repaint();
            t.stop();
            t1.stop();
            t2.stop();
            repaint();
            if (show.isSelected()==true){
                info.setText(timer.getText()+" Золотых: "+Integer.toString(FishCountGold)
                        + " Гуппи: "+Integer.toString(FishCountGuppi));
                dialog.setVisible(true);
            }
            timer.setText(timer.getText()+" Золотых: "+Integer.toString(FishCountGold)
                    + " Гуппи: "+Integer.toString(FishCountGuppi));
        }
        if(e.getKeyCode()==KeyEvent.VK_T){
            if (timer.isVisible()==true){
                timer.setVisible(false);
                showtimeNO.setSelected(true);
                MenushowtimeNO.setSelected(true);
            }
            else{
                timer.setVisible(true);
                showtimeYES.setSelected(true);
                MenushowtimeYES.setSelected(true);

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }



}
