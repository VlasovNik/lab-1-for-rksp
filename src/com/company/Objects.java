package com.company;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;
import java.util.Random;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;
@XStreamAlias("Objects")
public abstract class Objects implements MoveAI, Serializable {
    @XStreamAsAttribute
    public int nomer;
    @XStreamAsAttribute
    public int x;
    @XStreamAsAttribute
    public int y;
    @XStreamAsAttribute
    public int id;
    @XStreamAsAttribute
    public int isanim;
    public Rectangle bounds;
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

    public abstract double Getangle();

    public abstract void Setangle(int an);

    public boolean contains(int x, int y) {

        return false;
    }
}
@XStreamAlias("Smile")
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

    @Override
    public double Getangle() {
        return 0;
    }

    @Override
    public void Setangle(int an) {

    }

    @XStreamAlias("SmileAI")
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
@XStreamAlias("Image")
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
    @XStreamAlias("ImageAI")
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
    public int Isanim() {
        return this.isanim;
    }

    @Override
    public void Setangle(double k) {
        this.angle = k;
    }

}