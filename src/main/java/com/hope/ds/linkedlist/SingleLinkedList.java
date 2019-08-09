package com.hope.ds.linkedlist;

/**
 * 单链表
 */
public class SingleLinkedList<T> {

    Node first;

    int size;

    class Node{
        T data;
        Node next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void removeFirst(){
        first = first.next;
        size--;
    }

    public void linkFirst(T t){
        first = new Node(t, first);
        size++;
    }

    public int getSize(){
        return size;
    }

    public void removeByValue(T value){
        if(value == null || first == null){
            return;
        }

        Node f = first;
        Node p = null;
        while (f != null && f.data != value){
            p = f;
            f = f.next;
        }

        if(f == null){
            return;
        }

        if(p == null){
            first = first.next;
        }else{
            p.next = f.next;
        }
        size --;
    }

    public void removeAllByValue(T value) {
        if (value == null || first == null) {
            return;
        }
        Node f = first;
        Node p = null;
        while(f != null){
            if(f.data == value){
                if(p == null){
                    first = first.next;
                    f = f.next;
                    continue;
                }else{
                    p.next = f.next;
                }
            }

            p = f;
            f = f.next;
        }
    }

    public boolean contains(T value){
        Node f = first;
        while(f != null){
            if(f.data == value){
                return true;
            }
            f = f.next;
        }
        return false;
    }

    public void printAll() {
        Node p = first;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        SingleLinkedList<String> singleLinkedList = new SingleLinkedList<String>();
        singleLinkedList.linkFirst("3");
        singleLinkedList.linkFirst("4");
        singleLinkedList.linkFirst("1");
        singleLinkedList.printAll();

        singleLinkedList.removeFirst();
        singleLinkedList.printAll();
        singleLinkedList.removeByValue("4");
        singleLinkedList.printAll();
        singleLinkedList.removeByValue("3");
        singleLinkedList.printAll();

        System.out.println(singleLinkedList.getSize()+"");
        singleLinkedList.linkFirst("1");
        singleLinkedList.linkFirst("4");
        singleLinkedList.linkFirst("1");
        singleLinkedList.linkFirst("4");
        singleLinkedList.linkFirst("1");
        singleLinkedList.linkFirst("1");
        singleLinkedList.linkFirst("1");
        singleLinkedList.printAll();
        singleLinkedList.removeAllByValue("1");
        singleLinkedList.printAll();

        System.out.println(singleLinkedList.contains("4"));
    }

}
