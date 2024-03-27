import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

public class House {
    private PApplet sketch;
    private int x;
    private int y;
    private String name;
    private String address;
    private int time;
    private int timeDelivery = 10;
    private Order order;
    private OrderList list;
    private DoubleLinkedNode node;
    private int lastTickTime = 0;
    private boolean isDelivery = false;
    private static int delivering;
    private boolean canCancel = true;
    PImage Receiver;
    PImage Priority;

    public House(int x, int y, String name, String address) // Constructor with specific data stored
    {
        this.x = x;
        this.y = y;
        this.name = name;
        this.address = address;
        this.order = null;
    }

    public House(String name, String address) // Constructor with randomized data stored
    {
        Random ran = new Random();
        this.x = ran.nextInt(20, 600);
        this.y = ran.nextInt(20, 700);
        this.name = name;
        this.address = address;
        this.order = null;
    }

    public void setTime(int time) // Sets up the time
    {
        this.time = time;
    }

    public void setSketch(PApplet sketch) // Sets up sketch
    {
        this.sketch = sketch;
    }

    public void setOrder(Order order) // Sets up order
    {
        this.order = order;
    }

    public void setList(OrderList list)
    {
        this.list = list;
        if(list.getScore() >= 15) // Makes sure that no matter what, you can't exceed the daily limit (Not trying to over work anyone)
        {
            this.list = null;
            this.time = 0;
            this.timeDelivery = 0;
            this.x = 0;
            this.y = 0;
            this.name = null;
            this.address = null;
            this.order= null;
            this.node = null;
        }
    }

    public void setNode(DoubleLinkedNode node) // Sets up node
    {
        this.node = node;
    }

    public void timeTick() // Time goes down by one second
    {
        if (time > 0) {
            time--;
        }
    }

    public int getX() // Returns the x coordinates
    {
        return this.x;
    }

    public int getY() // Returns the y coordinates
    {
        return this.y;
    }

    public void cancelled() // The order is not delivered but cancelled, scores doesn't get added, nodes gets removed from the list
    {
        if(canCancel)
        {
            list.removeNode(node);
            this.name = "DNE";
            this.address = "DNE";
            this.time = 0;
            this.order = null;
            this.timeDelivery = 0;
            this.delivering = 0;
            this.canCancel = false;
        }
    }

    public void finished() // The order is successfully delivered, scores gets added, nodes gets removed from the list
    {
        list.finishOrder(node);
        this.name = "Delivered";
        this.address = "Delivered";
        this.time = 0;
        this.order = null;
        this.timeDelivery = 0;
        this.delivering = 0;
    }

    public void display() { // Displaying all the graphics for the people on the map and their stats
        sketch.stroke(1);
        sketch.fill(255, 0, 0);
        if(list != null && list.getHead() != null && order == list.getHead().getValue()) // If it's head of the list and the most prioritised
        {
            sketch.fill(0,0,255);
            Priority = sketch.loadImage("images/Priority.png");
            sketch.image(Priority, x, y, 20, 40);
        }
        else // Everyone else who isn't prioritised yet
        {
            sketch.fill(255, 0, 0);
            Receiver = sketch.loadImage("images/Receiver.png");
            sketch.image(Receiver, x, y, 20, 40);
        }
        timerTick();
        if (sketch.mouseX >= x && sketch.mouseX <= x + 40 && sketch.mouseY >= y && sketch.mouseY <= y + 40) // If the mouse hovers over the person
        {
            sketch.fill(57, 255, 20);
            sketch.textSize(18);
            String infoText = "";
            if(order != null) // If the order exists, show the stats of the person
            {
                infoText = order.toString1() + "\nReceiver's Name: " + name + "\nAddress: " + address;
            }
            else
            {
                infoText = "Receiver's Name: " + name + "\nAddress: " + address;
            }
            if (y < 80) { // Making sure that it's visible anywhere
                sketch.fill(0,0,0,120);
                sketch.rect(x - 10, y + 40, 200, 50);
                sketch.rect(x + 22, y + 10, 90, 20);
                sketch.textSize(15);
                sketch.fill(57, 255, 20);
                sketch.text("Time: " + time, x + 45, y + 25);
                sketch.fill(57, 255, 20);
                sketch.text(timeDelivery, x + 25, y + 25);
                sketch.textSize(12);
                if(x > 650)
                {
                    sketch.fill(57, 255, 20);
                    sketch.text(infoText, x - 30, y + 55);
                }
                else
                {
                    sketch.fill(57, 255, 20);
                    sketch.text(infoText, x - 5, y + 55);
                }
            }
            else{
                sketch.fill(0,0,0,120);
                sketch.rect(x - 10, y - 50, 200, 50);
                sketch.rect(x + 22, y + 10, 90, 20);
                sketch.fill(57, 255, 20);
                sketch.textSize(15);
                sketch.text("Time: " + time, x + 45, y + 25);
                sketch.text(timeDelivery, x + 25, y + 25);
                sketch.textSize(12);
                if(x > 650)
                {
                    sketch.fill(57, 255, 20);
                    sketch.text(infoText, x - 30, y - 35);
                    sketch.fill(0,0,0,120);
                    sketch.rect(x - 40, y - 50, 200, 50);
                    sketch.rect(x + 22, y + 10, 90, 20);
                }
                else
                {
                    sketch.fill(57, 255, 20);
                    sketch.text(infoText, x - 5, y - 35);
                }
            }
            if(sketch.key == 'f'  && timeDelivery > 0 && delivering == 0) // If f is pressed, then it started delivering
            {
                this.isDelivery();
                sketch.key = 's';
            }
            if(sketch.key == ' '  && time - timeDelivery >= 0 && isDelivery) // Only for testing as a programmer as it doesn't give accurate stats of score
            {
                sketch.key = 's';
                finished();
                sketch.key = 's';
            }
        }
    }

    public void isDelivery() // An order is getting delivered, this is the set up so that no other deliveries can happen at the same time
    {
        this.isDelivery = true;
        delivering = 1;
    }

    public void timerTick() // Time goes down by one second
    {
        try
        {
            if (sketch.millis() - lastTickTime >= 1000) {
                lastTickTime = sketch.millis();
                if(isDelivery && timeDelivery > 0) // If there's a counting down of a delivery time, count down here
                {
                    this.timeDelivery--;
                }
                if (time <= 0) { // If the order is not delivered but the customers got tired of waiting, they cancel (Player doesn't receive points)
                    cancelled();
                }
                if(timeDelivery == 0 && order != null) // If the order is successfully delivered, player gets points
                {
                    finished();
                    this.delivering = 0;
                }
            }
        }
        catch(NullPointerException exception) // If there's an error, it won't stop the program
        {
            System.out.println("null house bro");
        }
    }
}
