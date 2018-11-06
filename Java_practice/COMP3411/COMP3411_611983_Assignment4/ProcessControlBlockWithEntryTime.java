/*
 * @CreateTime: Jun 17, 2018 1:13 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Student Number:T00611983
 * @ COMP 3411 Assignment 4
 * @Description: ProcessControlBlockWithEntryTime is just to retain the entry time of a process when in is swapped with another process.
*/
class ProcessControlBlockWithEntryTime {
    ProcessControlBlock pcb;
    int entryTime;

    public ProcessControlBlockWithEntryTime(ProcessControlBlock Newpcb, int NewentryTime) {
        /*
         * System.out.print("new ProcessControlBlockWithEntryTime");
         * System.out.println(Newpcb.getProcessNo());
         */
        pcb = Newpcb;
        entryTime = NewentryTime;
    }

    ProcessControlBlock getPCB() {
        return pcb;
    }

    int getEntryTime() {
        return entryTime;
    }

    int getProcessNo() {
        return pcb.getProcessNo();
    }

    int getArrivalTime() {
        return pcb.getArrivalTime();
    }

    int getPriority() {
        return pcb.getPriority();
    }

    int getCpuBurstTime() {
        return pcb.getCpuBurstTime();
    }

    void setEntryTime(int NewentryTime) {
        entryTime = NewentryTime;
    }
}