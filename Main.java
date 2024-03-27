import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;
import javax.swing.*;
import java.util.*;
import java.util.ArrayList;

public class Main extends PApplet {
    private int gameState;
    private Queue Q;
    private int numDays = 0;
    private static int lastTickTime = 0;
    private static int timer = 80;
    private int score = 0;
    private Random ran = new Random();
    PImage grab;
    PImage deliveryMan;
    PImage receiver;
    PImage city;
    PImage priority;
    SoundFile backgroundMusic;


    public void settings()
    {
        size(1000, 800);
        pixelDensity(displayDensity());
    }

    public void setup()
    {
        backgroundMusic = new SoundFile(this, "assests/OMGTHECHILLESTMUSICYOUCANLISTENTO.mp3");
        backgroundMusic.amp(1);
        backgroundMusic.play();
        gameState = 0;
        Q = new Queue();
        Q.printTdy();
        System.out.print(score);
    }

    public void infoScreen() // Gives user brief descriptions of the simulation
    {
        background(173,216,230);
        fill(54, 69, 79);
        textSize(35);
        text("You are tasked to run a simulation of a food delivery driver, \nYou will be receiving different orders of different priority \nevery single day and you must try your best to deliver \nthem on time to gain points there's a list of orders to follow. \nChoose to either follow them or not, your choice. Let's see \nhow efficient using the list is.", 60, 100);
        fill(255, 179, 128);
        rect(250, 500, 500, 100);
        fill(54, 69, 79);
        textSize(50);
        text("More", 450, 565);
        grab = loadImage("images/grab.png");
        image(grab, 650, 330, 250, 100);
    }

    public void startingScreen() // The title screen
    {
        background(173, 216, 230);
        fill(54, 69, 79);
        textSize(100);
        text("Delivery Man", 230, 280);
        fill(0, 128, 0);
        rect(250, 500, 500, 100);
        fill(211, 211, 211);
        textSize(50);
        text("Info", 458, 565);
        fill(0, 128, 0);
        deliveryMan = loadImage("images/deliveryMan.png");
        image(deliveryMan, 400, 300, 200, 200);
    }

    public void tutorialScreenList() // The tutorial screen that shows the user where to expect the list and what it does
    {
        background(173, 216, 230);
        fill(0,255,0);
        rect(800, 0, 300, 800);
        textSize(80);
        fill(54, 69, 79);
        text("List", 440, 70);
        fill(0,255,0);
        stroke(2);
        line(800, 53, 1000, 53);
        line(800, 106, 1000, 106);
        line(800, 159, 1000, 159);
        line(800, 212, 1000, 212);
        line(800, 265, 1000, 265);
        line(800, 265, 1000, 265);
        line(800, 318, 1000, 318);
        line(800, 371, 1000, 371);
        line(800, 424, 1000, 424);
        line(800, 477, 1000, 477);
        line(800, 530, 1000, 530);
        line(800, 583, 1000, 583);
        line(800, 636, 1000, 636);
        line(800, 689, 1000, 689);
        line(800, 742, 1000, 742);
        textSize(60);
        fill(0);
        text("This is the list, \nit's your decision \nto use it or not", 300, 280);
        textSize(30);
        text("(It will update itself as time pass)", 300, 460);
        fill(255, 179, 128);
        rect(250, 500, 500, 100);
        fill(54, 69, 79);
        textSize(50);
        text("More", 450, 565);
    }

    public void tutorialScreenDeliver() // Shows the user how to deliver in the simulation and who to prioritise but also lets them know that they don't have to always deliver to prioritised people
    {
        background(173, 216, 230);
        receiver = loadImage("images/Receiver.png");
        priority = loadImage("images/Priority.png");
        image(priority, 250, 230, 100, 200);
        image(receiver, 640, 230, 100, 200);
        textSize(80);
        text("Controls", 350, 70);
        textSize(20);
        text("This person is prioritised, \nfocus on delivering it to them", 200, 150);
        text("This person isn't prioritised, \nfocus on delivering to them \nIF you want", 650, 150);
        text("Press f to \nstart delivering", 420, 330);
        fill(255, 179, 128);
        rect(250, 500, 500, 100);
        fill(54, 69, 79);
        textSize(50);
        text("More", 450, 565);
    }

    public void tutorialScreenStats()  // Shows users where to expect to see each person's statistics
    {
        background(173, 216, 230);
        priority = loadImage("images/Priority.png");
        image(priority, 450, 230, 100, 200);
        textSize(80);
        text("Displays", 350, 70);
        textSize(25);
        text("Time it \ntakes to \ndeliver", 570, 300);
        text("Time before \ncancellation", 670, 300);
        text("Order statistics \nwill be shown \nhere", 440, 150);

        fill(255, 179, 128);
        rect(250, 500, 500, 100);
        fill(54, 69, 79);
        textSize(50);
        text("Start", 450, 565);
    }

    public void simulationScreen() // Runs the display for the simulation
    {
        city = loadImage("images/city2.jpg");
        image(city,0,0,800,800);
        OrderList tdy = Q.getTdy();
        ArrayList<House> houses = tdy.getHouses();
        fill(0,0,0,140);
        rect(0,0,70,60);
        fill(0,255,0);
        textSize(50);
        timerTick();
        text(timer, 10, 45);
        textSize(20);
        for (House house : houses) // Iterating all houses in the today's list to display
        {
            if(house != null)
            {
                house.setSketch(this);
                house.display();
            }
        }
        fill(0,255,0);
        rect(800, 0, 300, 800);
        stroke(2);
        line(800, 53, 1000, 53);
        line(800, 106, 1000, 106);
        line(800, 159, 1000, 159);
        line(800, 212, 1000, 212);
        line(800, 265, 1000, 265);
        line(800, 265, 1000, 265);
        line(800, 318, 1000, 318);
        line(800, 371, 1000, 371);
        line(800, 424, 1000, 424);
        line(800, 477, 1000, 477);
        line(800, 530, 1000, 530);
        line(800, 583, 1000, 583);
        line(800, 636, 1000, 636);
        line(800, 689, 1000, 689);
        line(800, 742, 1000, 742);
        textSize(10);
        fill(0);
        text(tdy.displayOrder(0), 810, 25);
        for(int i = 1 ; i < tdy.getOrders() ; i++)
        {
            text(tdy.displayOrder(i), 810, 25 + (53 * i));
        }
        if(tdy.getHead() == null)
        {
            timer = 0;
        }
        fill(255, 0, 0);
        rect(0, 750, 150, 50);
        fill(255);
        textSize(20);
        text("Add Order", 40, 780);
    }

    public void passingScreen() // When a day passes, show this
    {
        background(255, 250, 160);
        textSize(50);
        fill(0);
        text("Days Passed: " + numDays + "\nScore: " + score , 350, 200);
        text("Press anywhere to continue", 250, 500);
    }

    public void endingScreen() // When all 7 (intended) days are done, this is the ending
    {
        background(173, 216, 230);
        textSize(100);
        text("Thank you for \nyour hard work! \nYou are done with \nthe simulation!", 50, 250);
    }

    public String getUserInput(String message) // Gets user's input and stops the time of the simulation
    {
        return JOptionPane.showInputDialog(null, message, "Input your order!", JOptionPane.PLAIN_MESSAGE);
    }


    public void draw()
    {
        switch(gameState)
        {
            case 0:
                startingScreen();
                break;

            case 1:
                infoScreen();
                break;

            case 2:
                simulationScreen();
                break;

            case 3:
                passingScreen();
                break;

            case 4:
                endingScreen();
                break;

            case 5:
                tutorialScreenList();
                break;

            case 6:
                tutorialScreenDeliver();
                break;

            case 7:
                tutorialScreenStats();
                break;
        }
    }

    public void mousePressed() // Tracks what the user inputs in terms of mouse press
    {
        if(gameState == 0)
        {
            if(mouseX >= 250 && mouseY >= 500 && mouseX <= 750 && mouseY <= 600)
            {
                gameState = 1;
            }
        }
        else if(gameState == 1)
        {
            if(mouseX >= 250 && mouseY >= 500 && mouseX <= 750 && mouseY <= 600)
            {
                gameState = 5;
            }
        }
        else if(gameState == 6)
        {
            if(mouseX >= 250 && mouseY >= 500 && mouseX <= 750 && mouseY <= 600)
            {
                gameState = 7;
            }
        }
        else if(gameState == 7)
        {
            if(mouseX >= 250 && mouseY >= 500 && mouseX <= 750 && mouseY <= 600)
            {
                gameState = 2;
            }
        }
        else if(gameState == 5)
        {
            if(mouseX >= 250 && mouseY >= 500 && mouseX <= 750 && mouseY <= 600)
            {
                gameState = 6;
            }
        }
        else if (gameState == 2 && mouseX >= 0 && mouseX <= 150 && mouseY >= 750 && mouseY <= 800) // Dealing with user inputs for specified orders
        {
            String newName = getUserInput("Enter your name:");
            String newAddress = getUserInput("Enter your address:");
            String newFood = getUserInput("Enter food:");
            String newPriority = getUserInput("Enter priority (Priority, Normal, Saver): ");
            if (newFood != null && !newFood.isEmpty() && newPriority != null && !newFood.isEmpty() && newName != null && !newName.isEmpty() && newAddress != null && !newAddress.isEmpty() && newName.length() <= 20 && newAddress.length() <= 30 && newFood.length() <= 25 && (newPriority.equalsIgnoreCase("priority") || newPriority.equalsIgnoreCase("normal") || newPriority.equalsIgnoreCase("saver")))
            {
                House newHouse = new House(newName, newAddress);
                Order newOrder = new Order(newFood, newPriority, newHouse);
                newHouse.setOrder(newOrder);
                newHouse.setList(Q.getTdy());
                if(newPriority.equalsIgnoreCase("priority"))
                {
                    newHouse.setTime(30);
                    DoubleLinkedNode newNode = new DoubleLinkedNode(newOrder);
                    newHouse.setNode(newNode);
                    Q.getTdy().addNode(newNode);
                }
                if(newPriority.equalsIgnoreCase("normal"))
                {
                    newHouse.setTime(50);
                    DoubleLinkedNode newNode = new DoubleLinkedNode(newOrder);
                    newHouse.setNode(newNode);
                    Q.getTdy().addNode(newNode);
                }
                if(newPriority.equalsIgnoreCase("saver"))
                {
                    newHouse.setTime(200);
                    DoubleLinkedNode newNode = new DoubleLinkedNode(newOrder);
                    newHouse.setNode(newNode);
                    Q.getTdy().addNode(newNode);
                }
            }
            else
            {
                System.out.println("Invalid Input");
            }
        }
        else if(gameState == 3)
        {
            if(mouseX >= 0 && mouseY >= 0)
            {
                timer = 80;
                gameState = 2;
            }
        }
    }

    public void timerTick() // Time ticks without pausing the entire program
    {
        if (millis() - lastTickTime >= 1000)
        {
            timer--;
            lastTickTime = millis();
            Q.getTdy().timeTick();
            if (timer <= 0)
            {
                numDays++;
                score += Q.getTdy().getScore();
                Q.updateList();
                gameState = 3;
                Q.removeTdy();
                if (Q.getDays() == 0)
                {
                    gameState = 4;
                }
            }
        }
    }


    public static void main(String[] args)
    {
        PApplet.main("Main");
    }
}
