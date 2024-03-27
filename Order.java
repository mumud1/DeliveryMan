import java.util.ArrayList;
import java.util.*;

public class Order {
    public String[] foodList = {"CHICKEN BIRYANI", "KF AND C", "MAC DONALDS", "PASTA", "SANDWICH", "GYOZA", "RAMEN", "SUSHI", "SOBA", "DUMPLINGS", "COKE", "PEPSI", "SPRITE", "ROOT BEER", "MILK", "TEA", "MILK TEA", "BOBA TEA", "CURRY", "FRIED RICE", "FRIED NOODLES", "CARROT CAKE", "PIZZA", "FRUIT CUP", "SALAD"};
    private ArrayList<String> food = new ArrayList<String>();
    private boolean isPriority;
    private boolean isNormal;
    private boolean isSaver;
    private int time;
    private House house;

    public Order() // Order is randomized
    {
        int listNum = foodList.length;
        int randomAmt = (int)(Math.random() * 3) + 1; // Sets a random amount of food that will be inserted in Order
        for(int i = 0 ; i < randomAmt ; i++) // Iterates to add the food into the food variable
        {
            this.food.add((foodList[(int)(Math.random() * listNum)]));
        }
        int random = (int)(Math.random() * 3); // Fairly randomizing priority chances
        if(random == 0) // Be prioritised
        {
            this.isPriority = true;
            this.isNormal = false;
            this.isSaver = false;
            this.time = 40;
        }
        if(random == 1) // Be normal
        {
            this.isPriority = false;
            this.isNormal = true;
            this.isSaver = false;
            this.time = 90;
        }
        if(random == 2) // Be saver
        {
            this.isPriority = false;
            this.isNormal = false;
            this.isSaver = true;
            this.time = 200;
        }
    }

    public Order(House house, int priority, int normal, int saver) // Lowers or increases chances of priority orders
    {
        this.house = house;
        int listNum = foodList.length;
        int randomAmt = (int)(Math.random() * 2) + 1; // Randomises amount of food in order
        for(int i = 0 ; i < randomAmt ; i++) // Iterates and adds food into food variable
        {
            this.food.add(foodList[(int)(Math.random() * listNum)]);
        }
        int total = priority + normal + saver;
        Random ran = new Random();
        int random = ran.nextInt(0, total);
        if(random < priority) // If the random quantity is at priority level, its prioritised order
        {
            this.isPriority = true;
            this.isNormal = false;
            this.isSaver =  false;
            this.time = 30;
        }
        else if(random < priority + normal) // If the random quantity is at normal level, its normal order
        {
            this.isNormal = true;
            this.isPriority = false;
            this.isSaver = false;
            this.time = 50;
        }
        else // If the random quantity is at saver level, its saver order
        {
            this.isSaver = true;
            this.isPriority = false;
            this.isNormal = false;
            this.time = 200;
        }
    }

    public Order(ArrayList<String> food, int typeOfDelivery, House house) // Order is customized!!
    {
        this.food = food;
        if(typeOfDelivery == 1) // Set to prioritised
        {
            this.isPriority = true;
            this.time = 30;
            this.house = house;
        }
        else if(typeOfDelivery == 2) // Set to normal
        {
            this.isNormal = true;
            this.time = 50;
            this.house = house;
        }
        else if(typeOfDelivery == 3) // set to saver
        {
            this.isSaver = true;
            this.time = 200;
            this.house = house;
        }
    }

    public Order(String food, String typeOfDelivery, House house) // Order is customized!!
    {
        this.food.add(food);
        if(typeOfDelivery.equalsIgnoreCase("priority")) // Set to priority
        {
            this.isPriority = true;
            this.isNormal = false;
            this.isSaver= false;
            this.time = 30;
            this.house = house;
        }
        else if(typeOfDelivery.equalsIgnoreCase("normal")) // Set to normal
        {
            this.isNormal = true;
            this.isSaver = false;
            this.isPriority = false;
            this.time = 50;
            this.house = house;
        }
        else if(typeOfDelivery.equalsIgnoreCase("saver")) // Set to saver
        {
            this.isSaver = true;
            this.isNormal = false;
            this.isPriority = false;
            this.time = 200;
            this.house = house;
        }
        else // If it's invalid input
        {
            System.out.println("You can't input that");
        }
    }

    public void setFood(ArrayList<String> newFood) // Sets the food to the new food arraylist
    {
        this.food = newFood;
    }

    public void setTime(int newTime) // Sets up time to the new time
    {
        this.time = newTime;
    }

    public void timeTick() // Time goes down by one second and also makes this true for the house variable
    {
        if(house!=null)
        {
            this.time--;
            house.timeTick();
        }
    }

    public void setPriority() // Set to priority
    {
        this.isPriority = true;
        this.isNormal = false;
        this.isSaver = false;
    }

    public void setNormal() // Set to normal
    {
        this.isPriority = false;
        this.isNormal = true;
        this.isSaver = false;
    }

    public void setSaver() // Set to saver
    {
        this.isPriority = false;
        this.isNormal = false;
        this.isSaver = true;
    }

    public String getPriority() // Returns the priority of order
    {
        if(isPriority) // If its priority, return priority
        {
            return "Priority";
        }
        else if(isNormal) // If its normal, return normal
        {
            return "Normal";
        }
        else if(isSaver) // If its saver, return saver
        {
            return "Saver";
        }
        else // If it's somehow nothing else
        {
            return "DNE";
        }
    }

    public int getTime() // Returns time
    {
        return time;
    }

    public String getFood() // Returns the food like how it's listed
    {
        String message = "";
        if(food.size() == 1)
        {
            message += food.get(0);
            return message;
        }
        else if(food.size() > 1)
        {
            for(int i = 0 ; i < food.size() - 1; i++) // Iterates the food variable and adds it to message in the right order
            {
                message += food.get(i) + ", ";
            }
            message += food.get(food.size() - 1); // Making sure message doesn't add with a comma
            return message;
        }
        else
        {
            return message;
        }
    }

    public House getHouse() // Return the house information
    {
        return this.house;
    }

    @Override
    public String toString() // To be displayed in other methods and classes
    {
        if(isPriority) // If its priority
        {
            return "Order: " + getFood() + "\nPriority: Prioritised Delivery Time Left: " + time;
        }
        if(isNormal) // If its normal
        {
            return "Order: " + getFood() + "\nPriority: Normal Delivery Time Left: " + time;
        }
        if(isSaver) // If its saver
        {
            return "Order: " + getFood() + "\nPriority: Saver Delivery Time Left: " + time;
        }
        else // If its none of those above
        {
            return "DNE";
        }
    }
    public String toString1() // To be displayed in other methods and classes
    {
        if(isPriority) // If its priority
        {
            return "Order: " + getFood();
        }
        if(isNormal) // If its normal
        {
            return "Order: " + getFood();
        }
        if(isSaver) // If its saver
        {
            return "Order: " + getFood();
        }
        else // If its none of those above
        {
            return "DNE";
        }
    }
}
