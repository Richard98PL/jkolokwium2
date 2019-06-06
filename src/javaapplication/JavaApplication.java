/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author RyszardRogalski, kolokwium nr 2 88789, czwartek 16:15, 4.1/2
 */
public class JavaApplication implements ActionListener{

    private final JFrame okno = new JFrame("KOLOKWIUM NUMER 2 ROGALSKI RYSZARD");
    private final JPanel mainPanel = new JPanel();
    
    private final JLabel kolokwium = new JLabel("Ryszard Rogalski, JKolokwium, 4.1/2, Czwartek 16:15");
    private final JLabel czasLabel = new JLabel("Gra chodzi juz sekund: 1");
    
    private final JPanel panel1 = new JPanel();
    private final JPanel panel2 = new JPanel();
    private final JPanel panel3 = new JPanel();
    private final JPanel panel11 = new JPanel();
    private final JPanel tytulGry = new JPanel();
    private final JPanel panelCzas = new JPanel();
   
    private final JLabel tytulGryLabel = new JLabel("Gra w skarby Ryszard Rogalski"); 
    
    private final ArrayList<JButton> przyciski = new ArrayList<JButton>();
    private final ArrayList<Integer> liczby = new ArrayList<Integer>();
    
    private final JButton start = new JButton("NOWA GRA");
    private final ArrayList<Character> tekstLista = new ArrayList<Character>();
    private final ArrayList<Integer> tekstListaWartosci = new ArrayList<Integer>();

    private String line;
    private String tekst="start"+"\n";
    
    private final JLabel ruch = new JLabel("Ruch: 0");
    private int ktoryRuch = 0;
    private final JLabel ktoraGraWTejSesji = new JLabel("1 gra w tej sesji");
    private int ktoraGra = 1;
    private int ileSekund = 1;
    
   
    
   JavaApplication(){
       
       
       //pozwolilem sobie urozmaicic gre o timer
       ActionListener czas = (ActionEvent e) -> {
           ileSekund++;
           czasLabel.setText("Gra nr " + ktoraGra + " chodzi juz sekund: " + Integer.toString(ileSekund));
           if(ileSekund%2==0)panelCzas.setBackground(Color.ORANGE);
           else panelCzas.setBackground(Color.GREEN);
       };
       Timer timer = new Timer(1000,czas);
       timer.start();
       
       panelCzas.add(czasLabel);
       
        try {
            BufferedReader br = new BufferedReader(new FileReader("plik.txt"));
            try {
                while((line=br.readLine())!=null)
                    tekst+=line+"\n";
            } catch (IOException ex) {
                System.out.println("Nie udalo sie odczytac!");
            }
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("Nie udalo sie zamknac tego pliku!");
            }
        } catch (FileNotFoundException ex) {
           System.out.println("Nie znalazlem tego pliku!");
        }
        
        for(int i=0;i<tekst.length();i++){
            if(tekst.charAt(i)=='0'||tekst.charAt(i)=='1'||tekst.charAt(i)=='2')
            tekstLista.add(tekst.charAt(i));
            if(tekst.charAt(i)=='0')tekstListaWartosci.add(0);
            if(tekst.charAt(i)=='1')tekstListaWartosci.add(1);
            if(tekst.charAt(i)=='2')tekstListaWartosci.add(2);
            // bo nie chcial mi rzutowac characterow na inty
        }
       
       for(int i=0;i<100;i++)liczby.add(0);
       for(int i=0;i<100;i++)przyciski.add(new JButton());
            
       mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
       mainPanel.setBorder(new EmptyBorder(25,25,25,25));
        
       panel1.add(kolokwium);
       panel11.add(ktoraGraWTejSesji);
       panel1.setBorder(new EmptyBorder(25,25,25,25));
       panel11.setBorder(new EmptyBorder(25,25,25,25));

       panel2.setBorder(new EmptyBorder(0,25,25,25));
       panel2.setLayout(new GridLayout(10,10));
       
       tytulGry.add(tytulGryLabel);
       tytulGry.setAlignmentX(tytulGryLabel.RIGHT_ALIGNMENT);

       for(JButton x:przyciski){
           panel2.add(x);
           x.setOpaque(true);
           x.setBackground(Color.gray);
           x.setPreferredSize(new Dimension(20,20));
           x.addActionListener(this);
       }
       
       panel3.setBorder(new EmptyBorder(25,25,25,25)); 
       panel3.add(start);
       panel3.add(ruch);
       
       start.addActionListener(e->{ //uzylem Lambdy za zapytaniem czy wolno zamiast klasy anonimowej
           czyszczenieGry();
       });

       panel3.setAlignmentX(start.RIGHT_ALIGNMENT); //nie wiem czmeu ale jak daje RIGHT to daje w lewo..
       
       mainPanel.add(panel1);panel1.setBackground(Color.MAGENTA);
       mainPanel.add(panelCzas);
       mainPanel.add(panel11);
       mainPanel.add(tytulGry);
       mainPanel.add(panel2);
       mainPanel.add(panel3);
       okno.add(mainPanel);
       
       okno.setDefaultCloseOperation(3);
       okno.setLocationRelativeTo(null);
       okno.setSize(480,600);
       okno.setResizable(false);
       okno.setVisible(true);
       
   }
    public static void main(String[] args) {
        
        JavaApplication app = new JavaApplication();
        
    }
    public void czyszczenieGry(){
      
        for(JButton x : przyciski){
            x.setBackground(Color.gray);
            x.setEnabled(true);//powrot to klikalnosci
        }
        
        ruch.setText("Ruch: 0");
        JOptionPane.showMessageDialog(new JFrame(),"Uwaga, zaczynamy gre!");
        ktoraGra++;
        ktoraGraWTejSesji.setText(ktoraGra + " gra w tej sesji");
        ileSekund=0;
 
    };
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
            System.out.println("Odebralem akcje.");
                   
            JButton zrodlo = (JButton)e.getSource();
            for(JButton x : przyciski){
                
                if(zrodlo.equals(x)){
                    
                    ktoryRuch++;
                    ruch.setText("Ruch: "+ktoryRuch);
                    int indeks = przyciski.indexOf(x);
                    przyciski.get(indeks).setEnabled(false); //zapobiega klikaniu pare razy w ten sam przycisk
                    System.out.println(indeks);
                   
                    if(tekstListaWartosci.get(indeks)==2){
                        przyciski.get(indeks).setBackground(Color.yellow);
                        if(ktoryRuch==0)
                               JOptionPane.showMessageDialog(new JFrame(),"Gratulacje, wygrales! I to w jeden ruch!"
                                       +"Twoj czas to : " + ileSekund + " sekund.");
                        else
                                JOptionPane.showMessageDialog(new JFrame(),"Gratulacje, wygrales!"+"\n"+
                                                                "Udalo ci sie to w ilosci ruchow : " 
                                                                    + Integer.toString(ktoryRuch)+
                                        "\n" + "Twoj czas to : " + ileSekund + " sekund.");

                        for(JButton z : przyciski)z.setEnabled(false);
                        ktoryRuch = 0; // wazne!
                    }
                    if(tekstListaWartosci.get(indeks)==0)
                         przyciski.get(indeks).setBackground(Color.blue);
                    if(tekstListaWartosci.get(indeks)==1){
                         przyciski.get(indeks).setBackground(Color.red);
                                 JOptionPane.showMessageDialog(new JFrame(),"Przegrales..."+"\n"+
                                                                "Wykonales ilosc ruchow rowna : " 
                                                                    + Integer.toString(ktoryRuch)+
                                          "\n" + "Twoj czas to : " + ileSekund + " sekund.");
                         for(JButton z : przyciski)z.setEnabled(false);
                         ktoryRuch = 0; // wazne!
                    }
            }
                   
            }
    }   
}