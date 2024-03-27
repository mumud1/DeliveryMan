import java.util.*;
public class OrderList {
    private DoubleLinkedNode head;
    private DoubleLinkedNode tail;
    private int score;
    private int totalScore;
    private int orders;
    private ArrayList<House> houses = new ArrayList<House>();
    private String[] names = {"Thomas", "Tanay", "Andy", "Liam", "Emma", "Noah", "Ava",
            "Elijah", "Sophia", "Oliver", "Isabella", "Mason",
            "Mia", "Logan", "Charlotte", "James", "Amelia",
            "Aiden", "Harper", "Ethan", "Evelyn", "Lucas",
            "Abigail", "Jackson", "Emily", "Alexander", "Elizabeth", "Shaun", "Jye", "Chan", "Oscar", "Sid", "Hans", "Kaito", "Ian", "Dani G."};
    private String[] lastNames = {"Lin", "Zhu", "German","Hopkins", "Page", "Janakaraj", "Li", "Low", "Donde", "Smith", "White", "Liventals", "Brown", "Lopez", "Anderson", "Jordan", "Jackson", "Tyson", "James", "Taylor", "Lee", "Lim", "Thompson", "Green", "Reeves", "Curry", "Williamson", "Middleton", "Love", "Irving", "Doncic", "Johnson"};
    private String[] address = {"123 Oak Street", "456 Maple Avenue", "789 Pine Lane", "101 Birch Road", "202 Cedar Blvd",
            "505 Elm Drive", "808 Willow Way", "909 Cherry Court", "303 Ash Place", "404 Spruce St",
            "250 Oak Terrace", "321 Poplar Street", "654 Alder Lane", "111 Acacia Avenue", "432 Fir Circle",
            "876 Sycamore Road", "765 Hawthorn Path", "159 Redwood Crescent", "349 Magnolia Blvd", "621 Juniper Way", "123 Street Street"};

    public OrderList() // The default constructor made just in case a list is made with nothing initially
    {
        this.head = null;
        this.tail = null;
        this.score = 0;
    }

    public OrderList(int num, int priority, int normal, int saver) // Constructor randomizes itself in a specific way
    {
        if(num > 15)
        {
            num = 15;
        }
        if(num < 5)
        {
            num = 6;
        }
        Random ran = new Random();
        num = ran.nextInt(5, num);
        for(int i = 0 ; i < num; i++)
        {
            int x = ran.nextInt(40, 700);
            int y = ran.nextInt(40, 500);
            House newHouse = new House(x, y, names[ran.nextInt(0,names.length)] + " " + lastNames[ran.nextInt(0,lastNames.length)], address[ran.nextInt(0, address.length)]);
            DoubleLinkedNode added = new DoubleLinkedNode(newHouse ,priority, normal, saver);
            if(added.getValue().getPriority().equals("Priority"))
            {
                totalScore += 5;
                added.getValue().getHouse().setTime(30);
                added.getValue().getHouse().setOrder(added.getValue());
                added.getValue().getHouse().setList(this);
                newHouse.setNode(added);
            }
            if(added.getValue().getPriority().equals("Normal"))
            {
                totalScore += 3;
                added.getValue().getHouse().setTime(50);
                added.getValue().getHouse().setOrder(added.getValue());
                added.getValue().getHouse().setList(this);
                newHouse.setNode(added);
            }
            if(added.getValue().getPriority().equals("Saver"))
            {
                totalScore += 1;
                added.getValue().getHouse().setTime(200);
                added.getValue().getHouse().setOrder(added.getValue());
                added.getValue().getHouse().setList(this);
                newHouse.setNode(added);
            }
            addNode(added);
            houses.add(newHouse);
        }
        this.score = 0;
    }

    public void randomize(int num, int priority, int normal, int saver) // Randomizes and already intialised orderlist based on performance
    {
        if(num > 15) // Making sure its between the lower bound 5 and uppder bound 15 as it's not realistic
        {
            num = 15;
        }
        if(num < 5)
        {
            num = 6;
        }
        Random ran = new Random();
        num = ran.nextInt(5, num);
        for(int i = 0 ; i < num; i++) // Iterates the random amount of times
        {
            // All this is generating new things to put into the OrderList
            int x = ran.nextInt(40, 700);
            int y = ran.nextInt(40, 500);
            House newHouse = new House(x, y, names[ran.nextInt(0,names.length)] + " " + lastNames[ran.nextInt(0,lastNames.length)], address[ran.nextInt(0, address.length)]);
            DoubleLinkedNode added = new DoubleLinkedNode(newHouse ,priority, normal, saver);
            if(added.getValue().getPriority().equals("Priority"))
            {
                totalScore += 5;
                added.getValue().getHouse().setTime(30);
                added.getValue().getHouse().setOrder(added.getValue());
                added.getValue().getHouse().setList(this);
                newHouse.setNode(added);
            }
            if(added.getValue().getPriority().equals("Normal"))
            {
                totalScore += 3;
                added.getValue().getHouse().setTime(50);
                added.getValue().getHouse().setOrder(added.getValue());
                added.getValue().getHouse().setList(this);
                newHouse.setNode(added);
            }
            if(added.getValue().getPriority().equals("Saver"))
            {
                totalScore += 1;
                added.getValue().getHouse().setTime(200);
                added.getValue().getHouse().setOrder(added.getValue());
                added.getValue().getHouse().setList(this);
                newHouse.setNode(added);
            }
            addNode(added);
            houses.add(newHouse);
        }
        this.score = 0;
    }
    public OrderList(DoubleLinkedNode head, DoubleLinkedNode tail) // Constructor with just the head and tail
    {
        this.head = head;
        this.tail = tail;
        this.score = 0;
    }

    public DoubleLinkedNode getHead() // Return head node
    {
        return head;
    }

    public DoubleLinkedNode getTail() // Returns tail node
    {
        return tail;
    }

public void removeNode(DoubleLinkedNode node) // Manipulates the list to remove a node O(1)
    {
    if (node == null) { // If node is null then nothing can be done so it returns
        System.out.println("Node to remove is null");
        return;
    }
    if (head == node && tail == node)
    { // There's only node in the orderList so the list is empty
        head = null;
        tail = null;
    }
    else if (head == node) // The node is the head
    {
        head = head.getNext();
        if (head != null)
        {
            head.setPrev(null);
        }
    }
    else if (tail == node) // The node is the tail
    {
        tail = tail.getPrev();
        if (tail != null)
        {
            tail.setNext(null);
        }
    }
    else
    { // Node is in the middle of the list
        if (node.getPrev() != null) // Checking so there's no nullpointerexceptions
        {
            node.getPrev().setNext(node.getNext());
        }
        if (node.getNext() != null)
        {
            node.getNext().setPrev(node.getPrev());
        }
    }
}

    public void addNode(DoubleLinkedNode node) // Manipulates the list to add in another node O(n) worst case and O(1) best case
    {
        try {
            if(node.getValue().getPriority() == null)
            {
                return;
            }
            if (orders >= 15) {
                System.out.println("Too many orders");
                return;
            }
            if (head == null) // List is empty so it'll be set as the very first node in the list with it pointing back and forth null.
            {
                head = tail = node;
                node.setNext(null);
                node.setPrev(null);
            }
            else
            {
                String priority = node.getValue().getPriority();
                if (priority.equals("Saver")) // Saver orders will always be at the end of the list, even if its added later on.
                {
                    tail.setNext(node);
                    node.setPrev(tail);
                    tail = node;
                    node.setNext(null);
                }
                else
                {
                    DoubleLinkedNode current = head; // Initialising my two pointers
                    DoubleLinkedNode previous = null;
                    while (current != null && !current.getValue().getPriority().equals("Saver")) // Both priority and normal orders are ahead of savers so they have to at least stop at where there is a saver order already in the list
                    {
                        if ((priority.equals("Priority") && !current.getValue().getPriority().equalsIgnoreCase("priority")) || (priority.equalsIgnoreCase("normal") && current.getValue().getPriority().equalsIgnoreCase("saver"))) // Making sure that the loop iterated when it needs to for when it's either priority order or normal
                        {
                            break;
                        }
                        previous = current;
                        current = current.getNext();
                    }
                    if (previous == null) // Means that it is at the head position since head's previous is null and so it gets treated like a head
                    {
                        node.setNext(head);
                        head.setPrev(node);
                        head = node;
                    }
                    else if (current == null) // Means that it is at the tail position since it's current pointer is null so it gets treated like a tail
                    {
                        tail.setNext(node);
                        node.setPrev(tail);
                        tail = node;
                        node.setNext(null);
                    }
                    else // Means that we are in the middle of the list
                    {
                        previous.setNext(node);
                        node.setPrev(previous);
                        node.setNext(current);
                        current.setPrev(node);
                    }
                }
            }
            orders++; // Adding this class's total orders so it can be used to calculate the score ratio
            houses.add(node.getValue().getHouse()); // Adding to this class's collection of houses
        }
        catch (NullPointerException exception) // If there's a null pointing error, this program can still run
        {
            System.out.println("null input");
        }
    }

    public String displayOrder(int n) // This just displays the info of the nth order in the orderlist
    {
        if(head == null)
        {
            return "";
        }
        DoubleLinkedNode pointer = head;
        for(int i = 0 ; i < n ; i++)
        {
            if(pointer.getNext() == null)
            {
                return "Cancelled or Delivered";
            }
            pointer = pointer.getNext();
        }
        return pointer.toString() + "\n" + estimateTime(pointer);
    }


    public String estimateTime(DoubleLinkedNode check) // Estimates how long it'll take to deliver the "check" node
    {
        if(head == null)
        {
            return "There is no order";
        }
        else
        {
            return estimatedTimeHelper(check, head, 0);
        }
    }

    public String estimatedTimeHelper(DoubleLinkedNode check, DoubleLinkedNode node, int time) // Recursively counts through the list until it gets to "check" node and returns the time
    {
        if(node == check)
        {
            return "Initial estimated time of arrival: " + (time + node.getValue().getTime());
        }
        else
        {
            return estimatedTimeHelper(check, node.getNext(), time + node.getValue().getTime());
        }
    }

    public double calculateScore() // Effectively calculates the score as the info is stored in the class and returns it
    {
        return (double) score/totalScore;
    }

    public void clearList() // Completely erases the list if needed
    {
        head = null;
        tail = null;
    }

    public void timeTick() // Time ticks down
    {
        if(head == null)
        {
            return;
        }
        if(head.getNext() == null && head.getValue().getTime() > 0)
        {
            head.getValue().timeTick();
        }
        else
        {
            DoubleLinkedNode p1 = head;
            while(p1 != null)
            {
                p1.getValue().timeTick();
                p1 = p1.getNext();
            }
        }
    }

    public void timeTick1() // The other way of time ticking that would be more effective but not as robust
    {
        if(head == null)
        {
            return;
        }
        else if(head.getNext() == null && head.getValue().getTime() > 0) // If there's just one order to tick time
        {
            head.getValue().timeTick();
        }
        else
        {
            DoubleLinkedNode pointer1 = head;
            DoubleLinkedNode pointer2 = tail;
            while((pointer1 != pointer2) || (pointer1.getNext() != pointer2 && pointer2.getPrev() != pointer1)) // Manages for both odd number and even number of orders
            {
                    pointer1.getValue().timeTick();
                    pointer2.getValue().timeTick();
                    pointer1 = pointer1.getNext();
                    pointer2 = pointer2.getPrev();
            }
            if(pointer1 == pointer2)
            {
                pointer1.getValue().timeTick();
            }
        }
    }

    public int getOrders() // Returns the number of orders in list
    {
        return this.orders;
    }

    public int getScore() // Returns the total score that the player scored
    {
        return this.score;
    }

    public ArrayList<House> getHouses() // Returns the information about the houses of each order
    {
        return houses;
    }

    public void finishOrder(DoubleLinkedNode node) // Finishes the order and counts up the scores depending on its priority level
    {
        if(node.getValue().getPriority().equals("Priority"))
        {
            this.score += 5;
        }
        else if(node.getValue().getPriority().equals("Normal"))
        {
            this.score += 3;
        }
        else
        {
            this.score++;
        }
        removeNode(node);
        System.out.println("removed");
    }
}

