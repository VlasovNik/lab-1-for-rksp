package com.company;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Objects implements MoveAI, Serializable {
    public int nomer,x,y,id,Lifetime,pozition,isanim;
    public Rectangle bounds;
    public double angle = 0;
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

    public abstract double Getangle();

    public abstract void Setangle(int an);
}

class Smile extends Objects implements Serializable {
    private ArrayList<Polygon> polygons; // список многоугольников
    private Random rand; // генератор случайных чисел

    public Smile(int n) {
        super();
        if(n == 1){
            this.isanim = 1;

        }
        else{
            this.isanim = 0;
        }
        this.nomer = 1;
        intel = new SmileAI(this);
        intel.start();
    }

    @Override
    public double Getangle() {
        return 0;
    }

    @Override
    public void Setangle(int an) {

    }


    class SmileAI extends BaseAI implements Serializable{

        public SmileAI(MoveAI moveAI) {
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

    @Override
    public void Setangle(double k) {

    }


}

class Image extends Objects implements Serializable{

    public Image(int i) {
        super();
        this.angle = 0;
        if(i == 1){
            this.isanim = 1;
        }
        else{
            this.isanim = 0;
        }
        this.nomer = 2;
        intel = new ImageAI(this);
        intel.start();
    }
    class ImageAI extends BaseAI implements Serializable{

        public ImageAI(MoveAI moveAI) {
            super(moveAI);
        }
        @Override
        public void Move() {
            int t = Isanim();
            double k = Getangle();
            k++;
            Move.Setangle(k);
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
    public double Getangle() {
        return this.angle;
    }

    @Override
    public void Setangle(int an) {

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

    @Override
    public void Setangle(double k) {
        this.angle = k;
    }

}