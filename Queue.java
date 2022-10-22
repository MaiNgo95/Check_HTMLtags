package com.company;
import java.util.*;
//create Queue implemented by Linked List
public class Queue{
    QNode front, rear;
    int size;

    class QNode {


        String data;

        QNode next;


    }


  public Queue() {
      front = null;
      rear = null;
  }
    //check if the Queue is empty


    public void enqueue(String data){
        QNode temp = rear;
        rear = new QNode();
        rear.data = data;
        rear.next =null;
        if(isEmpty()){
            front = rear;
        }
        else{
            temp.next = rear;
        }
        size++;
    }
    //remove an element in the queue by the first element
    public String dequeue(){
     String data = front.data;
     front = front.next;
     if(isEmpty()){
         rear = null;

     }
     size--;
     return data;
    }
    //check for empty linked list
    public boolean isEmpty(){
        return size == 0;
        }

    public String peekQ(){
        if(front ==null){
            return null;
        }
        String data = front.data;

        return data;
    }
    public String getItem(){
        String data = front.data;
        String item = front.next.data;
        return item;
    }
    public void printQ(){
        QNode head = front;
        while(head != null){
            System.out.println(head.data);
            head = head.next;

        }
    }
    }


