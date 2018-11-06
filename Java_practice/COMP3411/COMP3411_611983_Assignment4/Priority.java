
/*
* @CreateTime: Jun 17, 2018 1:13 PM
* @Author: Gavin Jaeger-Freeborn
* @Contact: gavinfre@uvic.ca
* @Student Number:T00611983
* @ COMP 3411 Assignment 4
* @Description: Priority is an implementation of the cpu schedualing method using Priority schedualing. 
*               Every (emulated) 10 minutes the average wait time and turnaround is printed when the constructor is called.
*/
import java.io.*;
import java.util.*;

public class Priority {

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

    // updates the list of remaining processes with the old process on the end
    // set the new cpu burst time to the current burst time minuse the elapsed time.
    private ArrayList<ProcessControlBlockWithEntryTime> replaceWithPriority(int current, int entryTime,
            ProcessControlBlockWithEntryTime oldProcess,
            ArrayList<ProcessControlBlockWithEntryTime> remainingProcesses) {
        int elapstTime = current - entryTime;
        int remainingTime = oldProcess.getCpuBurstTime() - elapstTime;
        ProcessControlBlockWithEntryTime ramainingProcess = new ProcessControlBlockWithEntryTime(
                new ProcessControlBlock(oldProcess.getProcessNo(), oldProcess.getArrivalTime(),
                        oldProcess.getPriority(), remainingTime),
                oldProcess.getEntryTime());
        remainingProcesses.add(0, ramainingProcess);
        remainingProcesses = sortRemainingProcesess(remainingProcesses);
        return remainingProcesses;
    }

    private ArrayList<ProcessControlBlockWithEntryTime> sortRemainingProcesess(
            ArrayList<ProcessControlBlockWithEntryTime> remainingProcesses) {
        ProcessControlBlockWithEntryTime testProcess;
        for (int i = 0; i < remainingProcesses.size() - 1; i++) {
            testProcess = remainingProcesses.get(i);
            if (testProcess.getPriority() >= remainingProcesses.get(i + 1).getPriority()) {
                break;
            } else {
                remainingProcesses.set(i, remainingProcesses.get(i + 1));
                remainingProcesses.set(i + 1, testProcess);
            }
        }
        return remainingProcesses;
    }

    /*
     * when the Priority constructor is called the given files contents are stored
     * in a PriorityQueue which is then used to simulate Priority schedualing. Every
     * (emulated) 10 minutes the average wait time and turnaround is printed.
     */
    public Priority(String FileName) {
        int remainingProcessesPriorityIndex = -1;
        int completedProcesses = 0;
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

        // while there are still processes in the Q or there is still a process running
        // exicute schedualing
        while (!readyQ.isEmpty() || CPUBusy == true || !remainingProcesses.isEmpty()) {
            // if the current sample process has arrived add this process to the back of the
            // remaining processses
            // and if their is still another item in the readyq set it as the SampleProcess
            if (SampleProcess.getArrivalTime() <= current && (lastProcess == false)) {

                remainingProcesses.add(0, new ProcessControlBlockWithEntryTime(SampleProcess, -1));
                if (!readyQ.isEmpty()) {
                    SampleProcess = (ProcessControlBlock) readyQ.getQueue();
                } else
                    lastProcess = true;
                if (!remainingProcesses.isEmpty())
                    remainingProcesses = sortRemainingProcesess(remainingProcesses);
            }
            // if cpu is free get current process from the remaining processes with the
            // highest priority
            // then if the current process has not ever entered the cpu set its entry time
            // to now
            // set the cpu to busy
            if (CPUBusy == false) {
                if (!remainingProcesses.isEmpty()) {
                    entryTime = current;
                    CurrentProcess = remainingProcesses.remove(0);
                    if (CurrentProcess.getEntryTime() == -1) {
                        CurrentProcess.setEntryTime(current);
                        waitTimeArray.add(new Integer(current - CurrentProcess.getArrivalTime())); // only the first
                                                                                                   // time
                    }
                    entryTime = current;
                    CPUBusy = true;
                }
            } else {
                // if the current process is completed set the cpu to not busy, and store the
                // turnaround time
                if (CurrentProcess.getCpuBurstTime() + entryTime <= current) {
                    completedProcesses++;
                    CPUBusy = false;
                    TurnaroundArray.add(new Integer(current - CurrentProcess.getEntryTime()));
                }
                // if the process still is not done and a sample process with a higher priority
                // apears store the current process and its remaining cpu burst in the
                // remainingProcesses
                // set the sample process as the current process with the entry time as current,
                // and cpu to be busy
                // if there is still more processes in the readyq set the sample process to the
                // next one.
                else if (!remainingProcesses.isEmpty()) {
                    if ((remainingProcesses.get(0).getPriority() > CurrentProcess.getPriority())) {
                        remainingProcesses = replaceWithPriority(current, entryTime, CurrentProcess,
                                remainingProcesses);
                        CurrentProcess = remainingProcesses.remove(1);
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