package part1;

import javax.swing.*;
import java.awt.event.*;

public class Panel extends JFrame {
    JList list;
    ButtonGroup bg;
    JRadioButton button1, button2;
    String[] myList = {"Некрасова Ирина", "Лепель Андрей", "Иванов Николай", "Руденко Екатерина", "Некрасов Павел", "Мирницкая Анастасия"};

    JComboBox<String> comboBox;

    Panel() {
        super("Пробное окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);

        button1 = new JRadioButton("Нечётные");
        button2 = new JRadioButton("Чётные");
        bg = new ButtonGroup();
        bg.add(button1);
        bg.add(button2);

        list = new JList(myList);

        setLayout(null);

        list.setSize(150, 110);
        list.setLocation(10, 10);

        button1.setSize(100, 50);
        button1.setLocation(200, 50);
        button2.setSize(100, 50);
        button2.setLocation(300, 50);


        add(list);

        add(button1);
        add(button2);


        comboBox = new JComboBox<>();
        comboBox.setSize(150,30);
        comboBox.setLocation(270,10);
        add(comboBox);


        //list.addMouseListener(new ButtonActionListener());x
        button1.addActionListener(new ButtonActionListener());
        button2.addActionListener(new ButtonActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            {
                if (e.getSource() == button1){
                    list.setSelectedIndices(new int[]{0,2,4});
                }
                if (e.getSource() == button2){
                    list.setSelectedIndices(new int[]{1,3,5});
                    for (int i = 1; i < myList.length; i += 2) {
                        comboBox.addItem(myList[i]);
                    }
                }
            }
        }
    }
}

