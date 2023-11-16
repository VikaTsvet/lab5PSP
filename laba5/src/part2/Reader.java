package part2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Reader extends JFrame {
    int k;
    DefaultListModel listModel;
    JList list;
    Object boxA, boxB, boxC;
    File file = new File("D:/универ/arch/ПрогСП/5лаба/GUI.txt");
    static JLabel l1, l2, l3, l4;
    JComboBox box_1, box_2, box_3;
    JRadioButton flag1, flag2;
    ButtonGroup bg;
    static JButton submit, reset;
    static JTextField nameField;
    static JTextArea descriptionArea;
    static String[] box1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    static String[] box2 = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
    static String[] box3 = {"2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};

    public Reader(String str) {
        super(str);
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listModel = new DefaultListModel();
        list = new JList(listModel);
        submit = new JButton("Внесение в базу");
        reset = new JButton("Очистить");
        nameField = new JTextField(9);
        descriptionArea = new JTextArea(9, 9);
        l1 = new JLabel("Название книги");
        l2 = new JLabel("Описание книги");
        l3 = new JLabel("Дата поступления");
        l4 = new JLabel("В электронном варианте");
        box_1 = new JComboBox(box1);
        box_2 = new JComboBox(box2);
        box_3 = new JComboBox<Object>(box3);
        flag1 = new JRadioButton("да");
        flag2 = new JRadioButton("нет");
        bg = new ButtonGroup();
        bg.add(flag1);
        bg.add(flag2);

        setLayout(null);
        submit.setSize(200, 30);
        submit.setLocation(150, 400);
        reset.setSize(100, 30);
        reset.setLocation(10, 400);
        nameField.setSize(220, 25);
        nameField.setLocation(250, 20);
        descriptionArea.setSize(220, 150);
        descriptionArea.setLocation(250, 60);
        l1.setSize(200, 25);
        l1.setLocation(30, 20);
        l2.setSize(220, 25);
        l2.setLocation(30, 60);
        l3.setSize(200, 25);
        l3.setLocation(30, 250);
        l4.setSize(200, 25);
        l4.setLocation(30, 310);
        box_1.setSize(40, 25);
        box_1.setLocation(250, 250);
        box_2.setSize(100, 25);
        box_2.setLocation(300, 250);
        box_3.setSize(70, 25);
        box_3.setLocation(410, 250);
        flag1.setSize(40, 25);
        flag1.setLocation(250, 310);
        flag2.setSize(50, 25);
        flag2.setLocation(300, 310);
        list.setSize(600,400);
        list.setLocation(500,20);

        add(submit);
        add(reset);
        add(nameField);
        add(descriptionArea);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(box_1);
        add(box_2);
        add(box_3);
        add(flag1);
        add(flag2);
        add(list);

        submit.addActionListener(new SubmitActionListener());
        flag1.addActionListener(new FlagActionListener());
        flag2.addActionListener(new FlagActionListener());
        reset.addActionListener(new ResetActionListener());
        box_1.addActionListener(new BoxActionListener());
        box_2.addActionListener(new BoxActionListener());
        box_3.addActionListener(new BoxActionListener());

        GetDataFromFile();
    }

    public class SubmitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter out = new FileWriter(file, true);
                try {
                    String text1 = nameField.getText();
                    String area2 = descriptionArea.getText();
                    out.write(text1 + "|");
                    out.write(area2 + "|");
                    if (k == 1)
                        out.write("Электронный вариант|");
                    else if (k == -1)
                        out.write("Бумажный вариант|");
                    out.write(boxA + " " + boxB + " " + boxC + "\n");
                } finally {
                    nameField.setText(null);
                    descriptionArea.setText(null);
                    out.close();
                    GetDataFromFile();
                }
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    public class FlagActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            k = 0;
            if (e.getSource() == flag1) {
                k++;
            } else if (e.getSource() == flag2) {
                k--;
            }
        }
    }

    public class ResetActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == reset) {
                nameField.setText(null);
                descriptionArea.setText(null);
            }
        }
    }

    public class BoxActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == box_1) {
                boxA = box_1.getSelectedItem();
            }
            if (e.getSource() == box_2) {
                boxB = box_2.getSelectedItem();
            }
            if (e.getSource() == box_3) {
                boxC = box_3.getSelectedItem();
            }
        }
    }

    public void GetDataFromFile(){
        try {
            listModel.clear();
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                listModel.addElement(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
