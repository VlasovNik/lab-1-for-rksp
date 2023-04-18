package com.company;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Objects implements MoveAI, Serializable {
    public int nomer,x,y,id,Lifetime,pozition,isanim;
    public BaseAI intel;
    public Objects(){
        this.x = ThreadLocalRandom.current().nextInt(0, 980 + 1);
        this.y = ThreadLocalRandom.current().nextInt(0, 580 + 1);
        this.id = ThreadLocalRandom.current().nextInt(1, 9999);
        this.Lifetime = ThreadLocalRandom.current().nextInt(2, 10);
        this.pozition = 0;
    }
    public void startAN(){
        intel.Notify();
    }
    public void stopAN(){
        intel.Wait();
    }
}

class Text extends Objects implements Serializable {
    private ArrayList<Polygon> polygons; // список многоугольников
    private Random rand; // генератор случайных чисел

    public Text(int n) {
        super();
        if(n == 1){
            this.isanim = 1;
        }
        else{
            this.isanim = 0;
        }
        this.nomer = 1;
        intel = new TextAI(this);
        intel.start();
    }

    class TextAI extends BaseAI implements Serializable{

        public TextAI(MoveAI moveAI) {
            super(moveAI);
        }
        @Override
        public void Move() {
            int t = Isanim();
            if (t == 1) {
                double avgX = Getx(); // получаем начальное среднее значение по x
                double avgY = Gety(); // получаем начальное среднее значение по y
                double speedX = 0; // начальная скорость по x
                double speedY = 0; // начальная скорость по y
                double damping = 0.98; // коэффициент затухания скорости
                double deltaX = (Math.random() * 2 - 1) * 10; // случайное изменение по x
                double deltaY = (Math.random() * 2 - 1) * 10; // случайное изменение по y

                speedX += deltaX; // добавляем изменение к скорости по x
                speedY += deltaY; // добавляем изменение к скорости по y

                speedX *= damping; // затухаем скорость по x
                speedY *= damping; // затухаем скорость по y

                avgX += speedX; // обновляем среднее значение по x
                avgY += speedY; // обновляем среднее значение по y
                Move.Setx((int) avgX);
                Move.Sety((int) avgY);
                }
            }
    }
    @Override
    public int Getx() {
        return this.x;
    }

    @Override
    public int Gety() {
        return this.y;
    }

    @Override
    public void Setx(int xx) {
        this.x = xx;
    }

    @Override
    public void Sety(int yy) {
        this.y = yy;
    }

    @Override
    public int GetPoz() {
        return this.pozition;
    }

    @Override
    public void SetPoz(int poz) {
        this.pozition = poz;
    }

    @Override
    public int Isanim() {
        return this.isanim;
    }


}

class Smile extends Objects implements Serializable{

    public Smile(int i) {
        super();
        if(i == 1){
            this.isanim = 1;
        }
        else{
            this.isanim = 0;
        }
        this.nomer = 2;
        intel = new SmileAI(this);
        intel.start();
    }
    class SmileAI extends BaseAI implements Serializable{

        public SmileAI(MoveAI moveAI) {
            super(moveAI);
        }
        @Override
        public void Move() {
            int t = Isanim();
            if (t == 1) {
                int xx = 1;
                int i = Getx();
                int k = GetPoz();
                if (k == 0) {
                    if (i < 980) {
                        i += xx;
                        Move.Setx(i);
                    } else {
                        k = 1;
                        Move.SetPoz(k);
                    }
                } else if (k == 1) {
                    if (i != 0) {
                        i -= xx;
                        Move.Setx(i);
                    } else {
                        k = 0;
                        Move.SetPoz(k);
                    }

                }
            }
        }

    }
    @Override
    public int Getx() {
        return this.x;
    }

    @Override
    public int Gety() {
        return this.y;
    }

    @Override
    public void Setx(int xx) {
        this.x = xx;
    }

    @Override
    public void Sety(int yy) {
        this.y = yy;
    }

    @Override
    public int GetPoz() {
        return this.pozition;
    }

    @Override
    public void SetPoz(int poz) {
        this.pozition = poz;
    }

    @Override
    public int Isanim() {
        return this.isanim;
    }

}