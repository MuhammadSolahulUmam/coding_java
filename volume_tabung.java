/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author BISMILLAH
 */
import java.util.Scanner;

public class volume_tabung {
    double jariJari;
    double tinggi;

    volume_tabung(double r, double t) 
    {
        jariJari = r;
        tinggi = t;
    }

    double volume() {
        return Math.PI * jariJari * jariJari * tinggi;
        
    }
}


class PersegiPanjang {
    double panjang, lebar;

    PersegiPanjang(double p, double l) {
        panjang = p;
        lebar = l;
    }

    double luas() {
        return panjang * lebar;
    }
}

class Trapesium {
    double sisiAtas, sisiBawah, tinggi;

    Trapesium(double a, double b, double t) {
        sisiAtas = a;
        sisiBawah = b;
        tinggi = t;
    }

    double luas() {
        return 0.5 * (sisiAtas + sisiBawah) * tinggi;
    }
}

public class Main {
    public static void main(String[] args) {
        Tabung tabung = new Tabung(7, 10);
        PersegiPanjang pp = new PersegiPanjang(5, 8);
        Trapesium tr = new Trapesium(6, 10, 7);

        System.out.println("Volume Tabung = " + tabung.volume());
        System.out.println("Luas Persegi Panjang = " + pp.luas());
        System.out.println("Luas Trapesium = " + tr.luas());
    }
}

