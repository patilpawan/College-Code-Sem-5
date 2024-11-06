import java.util.*;

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];
        int ar[] = new int[n];
        int bt[] = new int[n];
        int ct[] = new int[n];
        int ta[] = new int[n];
        int wt[] = new int[n];
        int temp;
        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process " + (i + 1) + " Arrival Time: ");
            ar[i] = sc.nextInt();
            System.out.print("Enter Process " + (i + 1) + " Burst Time: ");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }

        // Sorting by arrival time
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (ar[j] > ar[j + 1]) {
                    // Swapping elements for sorting
                    temp = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = temp;

                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;
                }
            }
        }

        // Calculating completion, turnaround, and waiting times
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ct[i] = ar[i] + bt[i];
            } else {
                if (ar[i] > ct[i - 1]) {
                    ct[i] = ar[i] + bt[i];
                } else {
                    ct[i] = ct[i - 1] + bt[i];
                }
            }

            ta[i] = ct[i] - ar[i];  // Turnaround time
            wt[i] = ta[i] - bt[i];  // Waiting time
            avgwt += wt[i];
            avgta += ta[i];
        }

        // Printing process information
        System.out.println("\nPID\tArrival\tBurst\tComplete\tTurnaround\tWaiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t\t" + ta[i] + "\t\t" + wt[i]);
        }

        // Printing average times
        System.out.printf("\nAverage Waiting Time: %.2f\n", (avgwt / n));
        System.out.printf("Average Turnaround Time: %.2f\n", (avgta / n));

        sc.close();
    }
}
