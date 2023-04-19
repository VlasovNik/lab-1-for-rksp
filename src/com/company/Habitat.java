package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.Random;
import java.util.Vector;

public class Habitat extends JComponent implements ActionListener, Serializable, MouseListener {
    JFrame f = new JFrame("Lab1");
    private static Habitat Instance;
    private boolean isAnimSmile = true;
    private boolean isAnimImage = true;

    public int time = 0;

    private Timer t = new Timer(100, this::actionPerformed);
    private Timer t1  = new Timer(100, this::actionPerformed);
    private Timer t2  = new Timer(100, this::actionPerformed);


    private Font font = new Font("Verdana", Font.PLAIN, 11);
    private static JLabel info = new JLabel("Состояние программы");
    private static JLabel infosave = new JLabel("");
    private static JPanel panel = new JPanel();
    private static JButton start = new JButton("Старт");
    private static JButton stop = new JButton("Стоп");
    private static JButton startAnimSmile = new JButton("Запустить анимацию смайлов");
    private static JButton stopAnimSmile = new JButton("Остановить Анимацию смайлов");
    private static JButton startAnimImage = new JButton("Запустить анимацию картинок");
    private static JButton stopAnimImage = new JButton("Остановить Анимацию картинок");
    private static JButton readset = new JButton("Загрузить");
    private static JButton saveset = new JButton("Сохранить");
    private static JButton DelAll = new JButton("Удалить все объекты");

    //settings labels

    private static final JLabel Line1 = new JLabel("----------------------------------------- ");
    private static final JLabel Line2 = new JLabel(" -----------------------------------------");
    private static final JLabel Line3 = new JLabel("-----------------------------------------");
    private static final JLabel Line4 = new JLabel("-----------------------------------------");
    private static final JLabel Line5 = new JLabel("-----------------------------------------");

    //список объектов
    public static Vector<Objects> Object = new Vector<Objects>();
    java.awt.Image img = new ImageIcon("src/1.png").getImage();


    public static Habitat GetHabitatInstance() {
        if (Instance == null) {
            Instance = new Habitat();
        }
        return Instance;
    }

    public Habitat() {
        t.setActionCommand("Timer");
        t1.setActionCommand("Timer");
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

        DelAll.addActionListener(this::actionPerformed);
        DelAll.setActionCommand("DelAll");
        DelAll.setBackground(Color.white);
        DelAll.setFocusable(false);
        DelAll.setVisible(true);


        info.setFocusable(false);
        info.setLocation(100, 0);
        info.setSize(300, 15);
        info.setForeground(Color.BLACK);
        info.setFont(font);

        infosave.setFocusable(false);
        infosave.setLocation(100, 0);
        infosave.setSize(300, 15);
        infosave.setForeground(Color.BLACK);
        infosave.setFont(font);


        //panel
        panel.setLayout(new FlowLayout());
        panel.setBounds(1035, 0, 200, 630);
        panel.setBackground(Color.gray);
        panel.setFocusable(false);
        panel.add(start);
        panel.add(stop);
        panel.add(Line1);
        panel.add(info);
        panel.add(Line2);
        panel.add(startAnimSmile);
        panel.add(stopAnimSmile);
        panel.add(Line3);
        panel.add(startAnimImage);
        panel.add(stopAnimImage);
        panel.add(Line4);
        panel.add(DelAll);
        panel.add(Line5);
        panel.add(readset);
        panel.add(saveset);
        panel.add(infosave);

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
                Rectangle bounds = a.getBounds();
                g1.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
                if (a.nomer == 1) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(a.x, a.y, 100, 100);
                    g.setColor(Color.BLACK);
                    g.fillOval(a.x+25, a.y+25, 10, 10);
                    g.fillOval(a.x+65, a.y+25, 10, 10);
                    g.drawArc(a.x+20, a.y+50, 60, 30, 180, 180);
                } else {
                    g1.drawImage(img, a.x, a.y,null);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Timer": {
                time += 1;
                repaint();
                break;
            }
            case "Start": {
                info.setText("Запущено");
                if (t.isRunning() == false &&t1.isRunning() == false && t2.isRunning() == false) {
                    time = 0;
                    Object.clear();
                    repaint();
                    t.start();
                    t1.start();
                    t2.start();
                    repaint();
                }
                repaint();
                break;
            }
            case "startAnimSmile":{
                if (t1.isRunning() == false) {
                    for (Objects a : Object) {
                        if(a.nomer == 1){
                            a.isanim = 1;
                            a.startANSmile();
                        }
                    }
                    isAnimSmile= true;
                    t1.start();
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
                    isAnimImage= true;
                    t2.start();
                    repaint();
                }
                break;
            }
            case "stopAnimSmile":{
                if (t1.isRunning() == true) {
                    for (Objects a : Object) {
                        if(a.nomer == 1){
                            a.isanim = 0;
                            a.stopANSmile();
                        }
                    }
                    isAnimSmile= false;
                    t1.stop();
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
                    isAnimImage= false;
                    t2.stop();
                    repaint();
                }
                break;
            }
            case "Stop": {
                info.setText("Остановлено");
                if (t.isRunning() == true) {
                    for (Objects a : Object) {
                        a.isanim = 0;
                        a.stopANSmile();
                        a.stopANImage();
                    }
                    t.stop();
                    t1.stop();
                    t2.stop();
                    repaint();
                }
                break;
            }
            case "DelALl":{
                Object.clear();
                repaint();
                break;
            }
            case "Saveset": {
                try {
                    FileOutputStream fos = new FileOutputStream("src/obj.dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(Object);
                    infosave.setText("Успешно сохранено");

                } catch (Exception ex) {
                    infosave.setText("Ошибка");
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
                    infosave.setText("Успешно загружено");
                } catch (Exception ex) {
                    infosave.setText("Ошибка");
                    ex.printStackTrace();
                }
                info.setText("Запущено");
                isAnimSmile = true;
                isAnimImage = true;
                for (Objects a : Object) {
                    a.intel.start();
                    a.isanim = 1;
                    a.startANSmile();
                    a.startANImage();
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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(t.isRunning() == true){
            Point clickPoint = e.getPoint();
            int x = clickPoint.x - f.getInsets().left;
            int y = clickPoint.y - f.getInsets().top;
            Objects objToDelete = null;
            for (Objects a :Object) {
                Rectangle bounds = a.getBounds();
                if (bounds.contains(x,y)) {
                    objToDelete = a;
                    break;
                }
            }
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (objToDelete != null) {
                    Object.remove(objToDelete);
                } else {
                    if(isAnimSmile == true){
                        Object.add(new Smile(1,x,y));
                    }
                    else{
                        Object.add(new Smile(0,x,y));
                    }
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (objToDelete != null) {
                    Object.remove(objToDelete);
                } else {
                    if (isAnimImage == true) {
                        Object.add(new Image(1, x, y));
                    } else {
                        Object.add(new Image(0, x, y));
                    }
                }
            }
            repaint();
        }

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
