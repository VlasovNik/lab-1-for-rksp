package com.company;
import java.io.Serializable;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main implements Serializable {
    public static void main(String[] args) {
        // Создаем и отображаем первое окно
        JFrame mainWindow = new JFrame("Main Window");
        mainWindow.setSize(300, 200);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Создаем и отображаем второе окно
        JFrame secondWindow = new JFrame("Second Window");
        secondWindow.setSize(300, 200);
        secondWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Создаем отдельные экземпляры класса Habitat для каждого окна
        Habitat habitat1 = new Habitat(mainWindow);
       Habitat habitat2 = new Habitat(secondWindow);
        mainWindow.setVisible(true);
        secondWindow.setVisible(true);
    }
}