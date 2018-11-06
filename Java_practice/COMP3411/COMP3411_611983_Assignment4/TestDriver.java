/*
* @CreateTime: Jun 17, 2018 1:13 PM
* @Author: Gavin Jaeger-Freeborn
* @Contact: gavinfre@uvic.ca
* @Student Number:T00611983
* @ COMP 3411 Assignment 4
* @Description: TestDriver is used to demonstrait each of the cpu schedualing options
*/
import java.io.*;
import java.util.*;

class TestDriver {

    public static void main(String[] args) {
        System.out.println("for FCFS");
        FCFS f = new FCFS("hello");
        System.out.println("for SJF");
        SJF s = new SJF("hello");
        System.out.println("for Priority");
        Priority p = new Priority("hello");
        System.out.println("for RoundRobin with a tq of 500");
        RoundRobin r = new RoundRobin("hello", 500);
        System.out.println("for RoundRobin with a tq of 800");
        RoundRobin rr = new RoundRobin("hello", 800);
    }
}