public class DoubleLinkedNode {
    private Order value;
    private DoubleLinkedNode prev;
    private DoubleLinkedNode next;

    public DoubleLinkedNode(DoubleLinkedNode prev, DoubleLinkedNode next) // Randomized Food
    {
        this.value = new Order();
        this.prev = prev;
        this.next = next;
    }

    public DoubleLinkedNode(House house, int priority, int normal, int saver) // More customized version of the constructor
    {
        this.value = new Order(house, priority, normal, saver);
        this.next = null;
        this.prev = null;
    }

    public DoubleLinkedNode(Order value) // Very simplistic version of the constructor
    {
        this.value = value;
    }

    public DoubleLinkedNode getPrev() // Returns previous node
    {
        return this.prev;
    }

    public DoubleLinkedNode getNext() // Returns next node
    {
        return this.next;
    }

    public Order getValue() // Returns the order the node stores
    {
        return this.value;
    }

    public void setNext(DoubleLinkedNode next) // Sets the next node to be another node
    {
        this.next = next;
    }

    public void setPrev(DoubleLinkedNode prev) // Sets the previous node to be another node
    {
        this.prev = prev;
    }

    public void setValue(Order value) // Sets the node's order value to be another order value
    {
        this.value = value;
    }

    public String toString() //
    {
        return this.value.toString();
    }
}
