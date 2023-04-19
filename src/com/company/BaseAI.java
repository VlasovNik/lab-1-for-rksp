package com.company;

import java.io.Serializable;

public abstract class BaseAI extends Thread implements Serializable
{
    protected MoveAI Move;

    public BaseAI(MoveAI moveAI)
    {
        Move = moveAI;
    }

    private boolean IsRunning=true;
    private boolean IsAlive=true;

    public void Wait()
    {
        IsRunning = false;
    }
    public synchronized void Notify() {
        IsRunning = true;
        notify();
    }

    public void Stop()
    {
        IsAlive=false;
    }

    @Override public void run()
    {
        super.run();
        while (IsAlive)
        {
            if (!IsRunning)
                synchronized (this)
                {
                    try
                    {
                        this.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            Move();
            try
            {
                sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public abstract void Move();
}
