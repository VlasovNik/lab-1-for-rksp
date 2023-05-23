package com.company;

import java.awt.*;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Objects implements MoveAI, Serializable {
    public int nomer, x, y, id, isanim, poz,xy;
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, 100, 100);
    }

}

class Smile extends Objects implements Serializable {
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
                double avgX = Getx();
                double avgY = Gety();
                double deltaX = (Math.random() * 2 - 1) * 5;
                double deltaY = (Math.random() * 2 - 1) * 5;
                avgX += deltaX*0.9;
                avgY += deltaY*0.9;
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
    public int Getpoz() {
        return this.poz;
    }
    @Override
    public void Setpoz(int k) {
        this.poz = k;
    }
    @Override
    public int Getxy() {
        return this.xy;
    }
    @Override
    public void Setxy(int k) {
        this.xy = k;
    }

}

class Image extends Objects implements Serializable{

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
            if (t ==1) {
                int xy = Getxy();
                int x = Getx();
                int y = Gety();
                int k = Getpoz();
                int n = 50;
                if (k == 0) {
                    if (xy != n) {
                        x += 1;
                        Move.Setxy(xy+1);
                        Move.Setx(x);
                    } else {
                        Move.Setpoz(1);
                        Move.Setxy(0);
                    }
                } else if (k == 1) {
                    if (xy != n) {
                        y += 1;
                        Move.Setxy(xy+1);
                        Move.Sety(y);
                    } else {
                        Move.Setpoz(2);
                        Move.Setxy(0);
                    }
                } else if (k == 2) {
                    if (xy != n) {
                        x -= 1;
                        Move.Setxy(xy+1);
                        Move.Setx(x);
                    } else {
                        Move.Setpoz(3);
                        Move.Setxy(0);
                    }
                } else if (k == 3) {
                    if (xy != n) {
                        y -= 1;
                        Move.Setxy(xy+1);
                        Move.Sety(y);
                    } else {
                        Move.Setpoz(0);
                        Move.Setxy(0);
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
    public int Isanim() {
        return this.isanim;
    }
    @Override
    public int Getpoz() {
        return this.poz;
    }

    @Override
    public int Getxy() {
        return this.xy;
    }

    @Override
    public void Setpoz(int k) {
        this.poz = k;
    }

    @Override
    public void Setxy(int k) {
        this.xy = k;
    }
}