
/*
* @CreateTime: Jun 17, 2018 1:13 PM
* @Author: Gavin Jaeger-Freeborn
* @Contact: gavinfre@uvic.ca
* @Student Number:T00611983
* @ COMP 3411 Assignment 4
* @Description: FCFS is an implementation of the cpu schedualing method using Fisrt Come First Serve schedualing. 
*               Every (emulated) 10 minutes the average wait time and turnaround is printed when the constructor is called.
*/
import java.io.*;
import java.util.*;

public class FCFS {
    private static ArrayList<Integer> waitTimeArray = new ArrayList<Integer>();
    private static ArrayList<Integer> TurnaroundArray = new ArrayList<Integer>();

    // getAverage returns the average value within an arraylist
    private long getAverage(ArrayList<Integer> values, int size) {
        long totalOfValues = 0;
        long average = 0;
        for (int i = 0; i < size; i++) {
            totalOfValues = totalOfValues + values.get(i).intValue();
        }
        try {
            average = totalOfValues / size;
        } catch (ArithmeticException e) {
            return 0;
        }
        return average;

    }

    /*
     * when the FCFS constructor is called the given files contents are stored in a
     * PriorityQueue which is then used to simulate First Come First Serve
     * schedualing. Every (emulated) 10 minutes the average wait time and turnaround
     * is printed.
     */
    public FCFS(String FileName) {
        PriorityQueue FCFSreadyQ = new PriorityQueue();
        int completedProcesses = 0;
        String sampleFile;
        boolean CPUBusy = false;
        SampleReader sr = new SampleReader(FileName);
        ProcessControlBlock SampleProcess = null;
        ProcessControlBlock CurrentProcess = null;
        int entryTime = 0;
        int process, arrival, priority, burst;
        ProcessControlBlock pcb;
        int lastCountUsedForAverage = 0;
        PriorityQueue readyQ = new PriorityQueue();
        boolean lastProcess = false;
        while (true) {

            process = -1;
            arrival = -1;
            priority = -1;
            burst = -1;

            process = sr.readProcess();
            if (process < 0)
                break;
            arrival = sr.readArrival();
            if (arrival < 0)
                break;
            priority = sr.readPriority();
            if (priority < 0)
                break;
            burst = sr.readBurst();
            if (burst < 0)
                break;

            pcb = new ProcessControlBlock(process, arrival, priority, burst);
            readyQ.putQueue(pcb, arrival);

        }
        int current = 0;
        SampleProcess = (ProcessControlBlock) readyQ.getQueue();// Read a sample process from the sample file;
        while (!readyQ.isEmpty() || CPUBusy == true || lastProcess == false) {
            //if there in no process currently in the cpu
            if (CPUBusy == false) {
                // if a new process has arived add it to cpu
                if (SampleProcess.getArrivalTime() <= current) {
                    entryTime = current;
                    CurrentProcess = SampleProcess;
                    CPUBusy = true;
                    waitTimeArray.add(new Integer(current - SampleProcess.getArrivalTime()));
                    if (!(readyQ.isEmpty()))
                        SampleProcess = (ProcessControlBlock) readyQ.getQueue();
                    else
                        lastProcess = true;
                }
            //if a process has completed set the cpu to be free
            } else if (CurrentProcess.getCpuBurstTime() + entryTime <= current) {
                completedProcesses++;
                CPUBusy = false;
                TurnaroundArray.add(new Integer(current - entryTime));
            }
            current++;

            // prints the average wait and turnaround time
            if (((current - lastCountUsedForAverage) - 600000) == 0) {
                lastCountUsedForAverage = current;
                System.out.print("average wait time for the last 10minutes is ");
                System.out.print(getAverage(waitTimeArray, waitTimeArray.size()));
                waitTimeArray.clear();
                System.out.print(" average turnaround time for the last 10minutes is ");
                System.out.println(getAverage(TurnaroundArray, TurnaroundArray.size()));
                TurnaroundArray.clear();
            }
        }
    }
}