package com.company;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Objects implements MoveAI, Serializable {
    public int nomer, x, y, id, isanim;
    public double angle = 0;
    public BaseAI intel;
    public Objects(){
        this.x = ThreadLocalRandom.current().nextInt(0, 980 + 1);
        this.y = ThreadLocalRandom.current().nextInt(0, 580 + 1);
        this.id = ThreadLocalRandom.current().nextInt(1, 9999);
    }
    public void startANSmile(){
        intel.Notify();
    }
    public void stopANSmile(){
        intel.Wait();
    }
    public void startANImage(){
        intel.Notify();
    }
    public void stopANImage(){
        intel.Wait();
    }

}

class Smile extends Objects implements Serializable {
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
    public Smile(int n,int x, int y) {
        super();
        if(n == 1){
            this.isanim = 1;
        }
        else{
            this.isanim = 0;
        }
        this.x = x;
        this.y = y;
        this.nomer = 1;
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
                double avgX = Getx(); // получаем начальное среднее значение по x
                double avgY = Gety(); // получаем начальное среднее значение по y
                double speedX = 0; // начальная скорость по x
                double speedY = 0; // начальная скорость по y
                double damping = 0.98; // коэффициент затухания скорости
                double deltaX = (Math.random() * 2 - 1) * 10; // случайное изменение по x
                double deltaY = (Math.random() * 2 - 1) * 10; // случайное изменение по y

                speedX += deltaX; // добавляем изменение к скорости по x
                speedY += deltaY; // добавляем изменение к скорости по y

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
    public Image(int i,int x, int y) {
        super();
        this.angle = 0;
        if(i == 1){
            this.isanim = 1;
        }
        else{
            this.isanim = 0;
        }
        this.x = x;
        this.y = y;
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
            if (t ==1){
                double k = 0;
                k++;
                Move.Setangle(k);
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
    public int Isanim() {
        return this.isanim;
    }

    @Override
    public void Setangle(double k) {
        this.angle = k;
    }

}