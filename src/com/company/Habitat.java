package com.company;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Habitat extends JComponent implements ActionListener, Serializable, MouseListener {
    JFrame f = new JFrame("Lab1");
    private ActionListener Actions;
    private static Habitat Instance;
    private boolean isAnimSmile = true;
    private boolean isAnimImage = true;

    private Random random = new Random();

    public int time = 0;
    public boolean flag = false;

    private Timer t = new Timer(100, this::actionPerformed);
    private Timer t1  = new Timer(100, this::actionPerformed);
    private Timer t2  = new Timer(100, this::actionPerformed);


    private Font font = new Font("Verdana", Font.PLAIN, 11);
    private static JLabel timer = new JLabel("Time");
    private static JPanel panel = new JPanel();
    private static JButton start = new JButton("Старт");
    private static JButton stop = new JButton("Стоп");
    private static JButton startAnimSmile = new JButton("Запустить анимацию смайлов");
    private static JButton stopAnimSmile = new JButton("Остановить Анимацию смайлов");
    private static JButton startAnimImage = new JButton("Запустить анимацию картинок");
    private static JButton stopAnimImage = new JButton("Остановить Анимацию картинок");
    private static JButton readset = new JButton("Загрузить");
    private static JButton saveset = new JButton("Сохранить");
    private static ButtonGroup group = new ButtonGroup();

    //settings labels

    private static final JLabel Line1 = new JLabel("----------------------------------------- ");
    private static final JLabel Line2 = new JLabel(" -----------------------------------------");
    private static final JLabel Line3 = new JLabel("-----------------------------------------");
    private static final JLabel Line4 = new JLabel("-----------------------------------------");
    private static final JLabel Line5 = new JLabel("----------------------------------------- ");

    //список объектов
    @XStreamImplicit
    public static ArrayList<Objects> Object = new ArrayList<Objects>();
    //private static HashSet<Integer> ID = new HashSet<Integer>();
    //private static TreeMap<Integer, Integer> Borntime = new TreeMap<Integer, Integer>();
    java.awt.Image img = new ImageIcon("src/1.png").getImage();


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
            }

            public void windowClosing(WindowEvent event) {
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
        t2.setActionCommand("Timer");

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

        startAnimSmile.addActionListener(this::actionPerformed);
        startAnimSmile.setActionCommand("startAnimSmile");
        startAnimSmile.setBackground(Color.white);
        startAnimSmile.setFocusable(false);
        stopAnimSmile.setVisible(true);
        stopAnimSmile.addActionListener(this::actionPerformed);
        stopAnimSmile.setActionCommand("stopAnimSmile");
        stopAnimSmile.setBackground(Color.white);
        stopAnimSmile.setFocusable(false);
        stopAnimSmile.setVisible(true);

        startAnimImage.addActionListener(this::actionPerformed);
        startAnimImage.setActionCommand("startAnimImage");
        startAnimImage.setBackground(Color.white);
        startAnimImage.setFocusable(false);
        startAnimImage.setVisible(true);
        stopAnimImage.addActionListener(this::actionPerformed);
        stopAnimImage.setActionCommand("stopAnimImage");
        stopAnimImage.setBackground(Color.white);
        stopAnimImage.setFocusable(false);
        stopAnimImage.setVisible(true);



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



        //timer
        timer.setFocusable(false);
        timer.setLocation(100, 0);
        timer.setSize(300, 15);
        timer.setForeground(Color.BLACK);
        timer.setFont(font);


        //panel
        panel.setLayout(new FlowLayout());
        panel.setBounds(1035, 0, 200, 630);
        panel.setBackground(Color.yellow);
        panel.setFocusable(false);
        panel.add(start);
        panel.add(stop);
        panel.add(Line1);
        panel.add(startAnimSmile);
        panel.add(stopAnimSmile);
        panel.add(Line2);
        panel.add(startAnimImage);
        panel.add(stopAnimImage);
        panel.add(readset);
        panel.add(saveset);;

        //panel.addMouseListener(this);

        //--------------------------------------
        //frame
        f.setLayout(new BorderLayout());
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1250, 700);
        f.setLocation(10, 10);
        f.getContentPane().setBackground(Color.white);
        f.addMouseListener(this);
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
                if (a.nomer == 1) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(a.x, a.y, 100, 100);
                    g.setColor(Color.BLACK);
                    g.fillOval(a.x+25, a.y+25, 10, 10);
                    g.fillOval(a.x+65, a.y+25, 10, 10);
                    g.drawArc(a.x+20, a.y+50, 60, 30, 180, 180);
                } else {
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    // Сохраняем текущую матрицу преобразования
                    AffineTransform at = new AffineTransform();
                    at.translate(a.x, a.y); // перемещаем AffineTransform в центр изображения
                    at.rotate(a.angle, width/4 , height/4 ); // поворачиваем AffineTransform на угол angle вокруг центра изображения
                    at.translate(-width/4, -height/4); // перемещаем AffineTransform обратно в левый верхний угол изображения

                    // рисуем изображение, используя AffineTransform
                    g1.drawImage(img,at,null);
                    // Восстанавливаем исходную матрицу преобразования
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Timer": {
                time += 1;
                timer.setText(Integer.toString(time / 10) + " сек");
                repaint();
                break;
            }
            case "SpImage": {
                int x = random.nextInt(100);
                int width = img.getWidth(null);
                int height = img.getHeight(null);
                if (isAnimImage == true) {
                    Object.add(new Image(1));
                } else {
                    Object.add(new Image(0));
                }
                break;
            }

            case "Start": {
                Object.add(new Smile(1));
                Object.add(new Image(1));
                for (Objects a : Object) {
                    a.isanim = 1;
                    a.startANSmile();
                    a.startANImage();
                }
                if (t.isRunning() == false && t2.isRunning() == false) {
                    time = 0;
                    Object.clear();
                    repaint();
                    t.start();
                    t2.start();
                    repaint();
                }
                repaint();
                break;
            }
            case "startAnimSmile":{
                if (t.isRunning() == false) {
                    for (Objects a : Object) {
                        if(a.nomer == 1){
                            a.isanim = 1;
                            a.startANSmile();
                        }
                    }
                    t.start();
                    repaint();
                }
                break;
            }
            case "startAnimImage":{
                if (t2.isRunning() == false) {
                    for (Objects a : Object) {
                        if(a.nomer == 2){
                            a.isanim = 1;
                            a.startANImage();
                        }
                    }
                    t2.start();
                    repaint();
                }
                break;
            }
            case "stopAnimSmile":{
                if (t.isRunning() == true) {
                    for (Objects a : Object) {
                        if(a.nomer == 1){
                            a.isanim = 0;
                            a.stopANSmile();
                        }
                    }
                    t.stop();
                    repaint();
                }
                break;
            }
            case "stopAnimImage":{
                if (t2.isRunning() == true) {
                    for (Objects a : Object) {
                        if(a.nomer == 2){
                            a.isanim = 0;
                            a.stopANImage();
                        }
                    }
                    t2.stop();
                    repaint();
                }
                break;
            }
            case "Stop": {
                if (t.isRunning() == true) {
                    for (Objects a : Object) {
                        a.isanim = 0;
                        a.stopANSmile();
                        a.stopANImage();
                    }
                    t.stop();
                    t2.stop();
                    repaint();
                }
                break;
            }
            case "Saveset": {
                try {
                    FileOutputStream fos = new FileOutputStream("src/obj.dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(Object);
                    XStream xstream = new XStream();
                    String xml = xstream.toXML(Object);
                    System.out.println(xml);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            }
            case "Readset": {
                t.stop();
                t2.stop();
                try {
                    FileInputStream fis = new FileInputStream("src/obj.dat");
                    ObjectInputStream iis = new ObjectInputStream(fis);
                    Object = (ArrayList<Objects>) iis.readObject();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                isAnimSmile = true;
                isAnimImage = true;
                for (Objects a : Object) {
                    a.intel.start();
                    a.isanim = 1;
                    a.startANSmile();
                    a.startANImage();
                }
                t.start();
                t2.start();
                repaint();
                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(t.isRunning() == true){
            Point clickPoint = e.getPoint();
            int x = clickPoint.x - f.getInsets().left;
            int y = clickPoint.y - f.getInsets().top;
            Objects objToDelete = null;
            int width = 100;
            int height = 100;
            for (Objects obj :Object) {
                if ((x >= obj.x && x <= obj.x+100) || (y >= obj.y && y <= obj.y-100)) {
                    int k = obj.x;
                    int n = obj.y;
                    objToDelete = obj;
                    break;
                }
            }
            if (objToDelete != null) {
                Object.remove(objToDelete);
            } else {
                if(isAnimSmile==true){
                    Object.add(new Image(1));
                    Object.add(new Smile(1));
                }
                else{
                    Object.add(new Image(0));
                    Object.add(new Smile(0));
                }
            }
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



}
