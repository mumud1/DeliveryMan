import java.util.ArrayList;
import java.util.*;

public class Queue {
    private ArrayList<OrderList> day = new ArrayList<OrderList>();
    private int days;

    public Queue() // Creates a week's worth of orders for each day of the week (randomizes the orders fairly)
    {
        for(int i = 0 ; i < 7 ; i++)
        {
            day.add(new OrderList(10, 5, 5, 5));
            days++;
        }
    }

    public void addDay() // Manipulating the ArrayList to include one additional day of orders that's randomized fairly
    {
        day.add(new OrderList(10, 5, 5, 5));
        days++;
    }

    public void addDay(OrderList dayList) // Manipulating the ArrayList to include one additional day of orders that's already initialized
    {
        day.add(dayList);
        days++;
    }

    public double calculateTdy() // Calculates today's work scores
    {
        OrderList tdy = day.get(0);
        System.out.println(tdy.calculateScore());
        return tdy.calculateScore();
    }

    public OrderList getTdy() // Returns the list of orders happening TODAY
    {
        return day.get(0);
    }

    public void removeTdy() // The end of the current day
    {
        day.remove(0);
        days--;
    }

    public void printTdy() // Printing information of all orders of today onto the system
    {
        OrderList tdy = getTdy();
        int total = tdy.getOrders();
        if(total > 0)
        {
            for(int i = 0 ; i < total ; i++)
            {
                System.out.println(tdy.displayOrder(i));
            }
        }
        else
        {
            System.out.println("theres no order bro"); // If there's nothing at all initially
        }
    }

    public void updateList() // Uses the score from the current day to update the likelihood of certain priority orders to show up in the later weeks
    // O(n)
    {
        if(days == 1 || days == 0)
        {
            return;
        }
        double score = calculateTdy(); // Gets access to today's scores (ratio between deliveries and total deliveries)
        OrderList curr = day.get(1); // Gets access to tomorrow's list of orders
        Random ran = new Random();
        if(score >= 0.8) // For these logic statements, it's all just randomizing priority in certain ways based on today's score
        {
            int priority = ran.nextInt(5, 9);
            int normal = ran.nextInt(2,4);
            int saver = ran.nextInt(0, 2);
            day.set(1, new OrderList((priority + normal + saver), priority, normal, saver));
        }
        else if(score >= 0.7)
        {
            int priority = ran.nextInt(3, 5);
            int normal = ran.nextInt(3,5);
            int saver = ran.nextInt(2, 5);
            day.set(1, new OrderList((priority + normal + saver), priority, normal, saver));
        }
        else if(score >= 0.6)
        {
            int priority = ran.nextInt(1, 3);
            int normal = ran.nextInt(4,6);
            int saver = ran.nextInt(4, 6);
            day.set(1, new OrderList((priority + normal + saver), priority, normal, saver));
        }
        else if(score >= 0.5) // Less likely for priority
        {
            int priority = ran.nextInt(0, 2);
            int normal = ran.nextInt(4,5);
            int saver = ran.nextInt(7, 8);
            day.set(1, new OrderList((priority + normal + saver), priority, normal, saver));
        }
        else // Completely not possible for priority orders
        {
            int normal = ran.nextInt(3,5);
            int saver = ran.nextInt(4, 7);
            day.set(1, new OrderList((normal + saver), 0, normal, saver));
        }
    }

    public void deleteDays() // Deletes the amount of days;
    {
        days--;
    }
     public int getDays() // Returns how many days are left
     {
         return this.days;
     }
    public void addRandomOrder() // Adds a random order to today's orders
    {
        Order added = new Order();
        OrderList tdy = day.get(0);
        tdy.addNode(new DoubleLinkedNode(added));
        System.out.println("randomAdded");
    }

    public void addOrder(String name, String address, ArrayList<String> food, int typeOfDelivery) // Adds a specific order to today's orders
    {
        Random ran = new Random();
        House home = new House(ran.nextInt(20, 600), ran.nextInt(20, 800), name, address);
        Order added = new Order(food, typeOfDelivery, home);
        OrderList tdy = day.get(0);
        tdy.addNode(new DoubleLinkedNode(added));
    }
}
