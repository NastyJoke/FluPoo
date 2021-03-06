package gui;

import enu.Direction;
import enu.State;
import game.*;
import game.Map;
import game.Timer;
import living.*;

import java.util.*;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field containing
 * rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Simulator implements Runnable{

    private final static Direction[] directions = {Direction.NORTH,Direction.WEST,Direction.SOUTH,Direction.EAST,Direction.NORTHWEST,Direction.NORTHEAST,Direction.SOUTHWEST,Direction.SOUTHEAST};
    private final static double DEFAULT_HUMANRATE = 5;
    private final static double DEFAULT_DUCKRATE = 1;
    private final static double DEFAULT_CHICKENRATE = 2;
    private final static double DEFAULT_PIGRATE = 3;

    private final static int DEFAULT_WIDTH = 200;
    private final static int DEFAULT_HEIGHT = 100;

    private final static int DEFAULT_CONTAGION = 4;

    private final static double DEFAULT_TIME = 1;

    private Map map;
    private int step;
    private List<SimulatorView> views;

    private long time;
    private int width;
    private  int height;
    private int humanRate;
    private int pigRate;
    private int chickenRate;
    private int ducksRate;
    private int contagion;

    private Case[][] cases;

    public Simulator() {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    public Simulator(int width, int height)
    {
        //Construire la map : Rates par defaut
        this(width, height, DEFAULT_HUMANRATE, DEFAULT_DUCKRATE, DEFAULT_CHICKENRATE, DEFAULT_PIGRATE,DEFAULT_CONTAGION,DEFAULT_TIME);
    }

    public Simulator(int width, int height, double hr, double dr, double cr, double pr, int contagion,double time)
    {
        start(width, height, hr, dr, cr, pr, contagion, time);
        Thread thread = new Thread(this);
        thread.start();
    }

    public void start(int width, int height, double hr, double dr, double cr, double pr, int contagion,double time){
        this.width = width;
        this.height = height;
        this.humanRate = (int)hr;
        this.chickenRate = (int)cr;
        this.ducksRate = (int)dr;
        this.pigRate = (int)pr;
        this.contagion = contagion;
        this.time = (long)time;
    }

    public void simulate() {

        try {
            Timer timer = new Timer(this.time);
            while (!map.gameOver()) {
                synchronized (timer) {
                    simulateOneStep();
                    timer.sleep();
                }
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void simulateOneStep() {
        step++;
        map.simulateOneStep();
        updateViews();

    }

    public void reset() {
        step = 0;
        for (SimulatorView view : views) {
            view.reset();
        }

        updateViews();
    }

    private void updateViews() {
        for (SimulatorView view : views) {
            view.showStatus(step, map);
        }
    }

    @Override
    public void run() {
        map = new Map(width, height, humanRate, ducksRate, chickenRate, pigRate, contagion);
        views = new ArrayList<>();
        SimulatorView view = new GridView(height, width);
        view.setColor(Human.class, Color.RED);
        view.setColor(Pig.class, Color.PINK);
        view.setColor(Chicken.class, Color.YELLOW);
        view.setColor(Duck.class, Color.GREEN);
        views.add(view);

        view = new GraphView(500, 150, 500);
        view.setColor(Human.class, Color.RED);
        view.setColor(Pig.class, Color.PINK);
        view.setColor(Chicken.class, Color.YELLOW);
        view.setColor(Duck.class, Color.GREEN);
        views.add(view);
        simulate();
    }
}







