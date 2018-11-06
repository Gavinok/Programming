
/*
* @CreateTime: Jun 17, 2018 1:13 PM
* @Author: Gavin Jaeger-Freeborn
* @Contact: gavinfre@uvic.ca
* @Student Number:T00611983
* @ COMP 3411 Assignment 4
* @Description: RoundRobin is an implementation of the cpu schedualing method using round robin. 
*               Every (emulated) 10 minutes the average wait time and turnaround is printed when the constructor is called.
*/
import java.io.*;
import java.util.*;
import java.rmi.AccessException;
import java.io.IOException;

public class RoundRobin {
    private static ArrayList<Integer> waitTimeArray = new ArrayList<Integer>();
    private static ArrayList<Integer> TurnaroundArray = new ArrayList<Integer>();

    // gets the average value of all the elements in the arraylist
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

    // returns the process with the highest priority from the arraylist
    private ArrayList<ProcessControlBlockWithEntryTime> UpdatedRemainingProcesses(int current, int entryTime,
            ProcessControlBlockWithEntryTime oldProcess,
            ArrayList<ProcessControlBlockWithEntryTime> remainingProcesses) {
        int elapstTime = current - entryTime;
        int remainingTime = oldProcess.getCpuBurstTime() - elapstTime;
        ProcessControlBlockWithEntryTime ramainingProcess = new ProcessControlBlockWithEntryTime(
                new ProcessControlBlock(oldProcess.getProcessNo(), oldProcess.getArrivalTime(),
                        oldProcess.getPriority(), remainingTime),
                oldProcess.getEntryTime());
        remainingProcesses.add(ramainingProcess);
        return remainingProcesses;
    }

    /*
     * when the RoundRobin constructor is called the given files contents are stored
     * in a PriorityQueue which is then used to simulate Round Robin schedualing.
     * Every (emulated) 10 minutes the average wait time and turnaround is printed.
     */
    public RoundRobin(String FileName, int inputTimeQuantom) {
        int completedProcesses = 0;
        int timeQuantom = inputTimeQuantom;
        String sampleFile;
        boolean CPUBusy = false;
        SampleReader sr = new SampleReader(FileName);
        ProcessControlBlock SampleProcess = null;
        ProcessControlBlockWithEntryTime CurrentProcess = null;
        int entryTime = 0;
        int process, arrival, priority, burst;
        ProcessControlBlock pcb;
        int lastCountUsedForAverage = 0;
        ArrayList<ProcessControlBlockWithEntryTime> remainingProcesses = new ArrayList<ProcessControlBlockWithEntryTime>();
        PriorityQueue readyQ = new PriorityQueue();
        int current = 0;
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

        SampleProcess = (ProcessControlBlock) readyQ.getQueue();// Read a sample process from the sample file;
        while (!readyQ.isEmpty() || CPUBusy == true || !remainingProcesses.isEmpty()) {

            // if a new process arives add it to the remaining processes
            if ((SampleProcess.getArrivalTime() <= current) && (lastProcess == false)) {
                // add to the end of the list of remaining processe
                remainingProcesses.add(new ProcessControlBlockWithEntryTime(SampleProcess, -1));
                if (!readyQ.isEmpty()) {
                    SampleProcess = (ProcessControlBlock) readyQ.getQueue();
                } else {
                    lastProcess = true;
                }
            }
            // if the cpu is not busy and there are still remaining processes set the
            // current proces to the next process in the list
            if (CPUBusy == false) {
                if (!remainingProcesses.isEmpty()) {
                    entryTime = current;
                    CurrentProcess = remainingProcesses.remove(0);

                    // if this is the first time the process has entered the cpu then set the
                    // initial entrytime to the current time
                    if (CurrentProcess.getEntryTime() == -1) {
                        CurrentProcess.setEntryTime(current);
                        waitTimeArray.add(new Integer(current - CurrentProcess.getArrivalTime()));
                    }
                    // set the entry time for this part of the round robin to the current time
                    entryTime = current;
                    CPUBusy = true;
                }
            } else {

                // if the cpu is busy and the process has been completed set the cpu as not busy
                // since CurrentProcess.getCpuBurstTime() is the remainder of the processes
                // burst time and the
                if (CurrentProcess.getCpuBurstTime() + entryTime <= current) {
                    completedProcesses++;
                    CPUBusy = false;
                    TurnaroundArray.add(new Integer(current - CurrentProcess.getEntryTime()));
                }
                // if the current process has still not completed but its time quantom has
                // expired replace this process with the next process in the list
                else if ((!remainingProcesses.isEmpty())) {

                    if (((current - entryTime) >= timeQuantom)) {
                        remainingProcesses = UpdatedRemainingProcesses(current, entryTime, CurrentProcess,
                                remainingProcesses);
                        CurrentProcess = remainingProcesses.remove(0);
                        // if this is the first time the process has entered the cpu then set the
                        // initial entrytime to the current time
                        if (CurrentProcess.getEntryTime() == -1) {
                            CurrentProcess.setEntryTime(current);
                            waitTimeArray.add(new Integer(current - CurrentProcess.getArrivalTime()));
                        }
                        // set the entry time for this part of the round robin to the current time
                        CPUBusy = true;
                        entryTime = current;
                    }
                }
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