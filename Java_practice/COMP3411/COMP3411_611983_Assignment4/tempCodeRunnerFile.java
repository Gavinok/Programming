                lastCountUsedForAverage = current;
                System.out.print("average wait time for the last 10minutes is ");
                System.out.println(getAverage(waitTimeArray, waitTimeArray.size()));
                waitTimeArray.clear();
                System.out.print("average turnaround time for the last 10minutes is ");