/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Frame.Principal;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author MSBENAVIDES
 */
public class Utilities {

    private List<Match> match = new ArrayList<>();
    private List<Match> ok = new ArrayList<>();

    //METODO DONDE SE CARGAR NUMEROS RAMDON EN FILA
    public static List<Integer> rowsTable() {
        List<Integer> list = new ArrayList<>();
        Random r = new Random();
        int aument = 1;
        for (int i = 0; i < aument; i++) {
            if (list.size() != 4) {
                aument++;
            }
            int value = r.nextInt(5);
            if (!list.contains(value) && list.size() != 5 && value != 0) {
                list.add(value);
            }
        }
        return list;
    }

    //Metodo para ingresar las 2 filas
    public static List<Integer> rowsTableAll() {
        List<Integer> all = new ArrayList<>();
        all.addAll(rowsTable());
        all.addAll(rowsTable());
        return all;
    }

    //Metodo para pintar las cartas
    public void paintImage(JButton btn, int value) {
        try {
            btn.setIcon(new ImageIcon(getClass().getResource("/Images/0" + value + ".png")));
            btn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            btn.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //METODO QUE INICIALIZA LAS IMAGENES EN ?(carta misteriosa)
    public void imgStart() {
        paintImage(Principal.btn01, 9);
        paintImage(Principal.btn02, 9);
        paintImage(Principal.btn03, 9);
        paintImage(Principal.btn04, 9);
        paintImage(Principal.btn05, 9);
        paintImage(Principal.btn06, 9);
        paintImage(Principal.btn07, 9);
        paintImage(Principal.btn08, 9);
        paintImage(Principal.btn09, 9);
        paintImage(Principal.btn10, 9);
        paintImage(Principal.btn11, 9);
        paintImage(Principal.btn12, 9);
        paintImage(Principal.btn13, 9);
        paintImage(Principal.btn14, 9);
    }
        
    //Condicion para cuando las 2 cartas son iguales
    public void paintMatch() {
        System.out.println(getMatch().size());
        if (getMatch().size() == 1) {
            paintImage(getMatch().get(0).getBtn(), getMatch().get(0).getValueMatch());
        } else if(getMatch().size() == 2) {
            paintImage(getMatch().get(1).getBtn(), getMatch().get(1).getValueMatch());
        }
    }
    
    //Metodo donde las cartas ya hicieron par o match
    public void imgStartMatch() {
        if (ok.size() == 14) {
            JOptionPane.showMessageDialog(null, "Win Player");

            for (int i = 0; i < ok.size(); i++) {
                paintImage(ok.get(i).getBtn(), ok.get(i).getValueMatch());
                ok.get(i).getBtn().setEnabled(true);
            }
            ok.clear();
            imgStart();
        }
        for (int i = 0; i < ok.size(); i++) {
            System.out.println(ok.get(i).getNumberBtn() + "_" + ok.get(i).getValueMatch());
            paintImage(ok.get(i).getBtn(), ok.get(i).getValueMatch());
            ok.get(i).getBtn().setEnabled(false);
        }
    }

    //Metodo controlador de la ejecucion
    public void reset() {
        imgStart();
        imgStartMatch();
    }

    //Metodo de par o match
    public void match() {
        if (getMatch().size() == 2) {
            if (getMatch().get(0).getNumberBtn() != 0 && getMatch().get(1).getNumberBtn() != 0) {

                if (getMatch().get(0).getValueMatch() == getMatch().get(1).getValueMatch()) {
                    ok.add(getMatch().get(0));
                    ok.add(getMatch().get(1));
                    getMatch().clear();
                    reset();
                } else {                    
                    Runnable mx = new Runnable() {
                        @Override
                        public void run() {
                            getMatch().clear();
                            reset();
                        }
                    };
                    setTimeout(mx, 2000);
                }
            }
        }
    }

    public void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    public List<Match> getMatch() {
        return match;
    }

    public void setMatch(List<Match> match) {
        this.match = match;
    }

}
