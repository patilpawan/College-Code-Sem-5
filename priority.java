import java.util.Scanner;

public class Priority {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();

        int[] p = new int[n]; // process IDs
        int[] bt = new int[n]; // burst times
        int[] pt = new int[n]; // priorities
        int[] wt = new int[n]; // waiting times
        int[] tat = new int[n]; // turnaround times

        System.out.println("Enter the burst times for each process:");
        for (int i = 0; i < n; i++) {
            p[i] = i + 1; // process ID
            System.out.print("Process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
        }

        System.out.println("Enter the priority for each process (lower value = higher priority):");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            pt[i] = sc.nextInt();
        }

        // Sorting based on priority (Selection Sort)
        for (int i = 0; i < n; i++) {
            int pos = i;
            for (int j = i + 1; j < n; j++) {
                if (pt[j] < pt[pos]) {
                    pos = j;
                }
            }

            // Swapping priority, burst time, and process ID based on the priority
            int temp = pt[pos];
            pt[pos] = pt[i];
            pt[i] = temp;

            temp = bt[pos];
            bt[pos] = bt[i];
            bt[i] = temp;

            temp = p[pos];
            p[pos] = p[i];
            p[i] = temp;
        }

        // Calculate waiting times
        wt[0] = 0; // waiting time for the first process is 0
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + bt[i - 1];
        }

        // Display process info and calculate turnaround times
        System.out.println("\nProcess\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");
        float totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
            totalWT += wt[i];
            totalTAT += tat[i];
            System.out.println(p[i] + "\t\t" + bt[i] + "\t\t" + pt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
        }

        // Display average waiting and turnaround times
        System.out.printf("\nAverage Waiting Time: %.2f", totalWT / n);
        System.out.printf("\nAverage Turnaround Time: %.2f", totalTAT / n);

        sc.close();
    }
}
