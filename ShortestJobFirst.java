import java.util.Scanner;

public class ShortestJobFirst {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();

        int[] pid = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        // Input process details
        for (int i = 0; i < n; i++) {
            System.out.println("Enter arrival time and burst time for process " + (i + 1) + ":");
            arrivalTime[i] = sc.nextInt();
            burstTime[i] = sc.nextInt();
            pid[i] = i + 1;
        }

        // Sort processes by arrival time and burst time
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arrivalTime[j] > arrivalTime[j + 1] || 
                   (arrivalTime[j] == arrivalTime[j + 1] && burstTime[j] > burstTime[j + 1])) {
                    swap(arrivalTime, j, j + 1);
                    swap(burstTime, j, j + 1);
                    swap(pid, j, j + 1);
                }
            }
        }

        int currentTime = 0;
        boolean[] completed = new boolean[n];

        // Calculate completion, turnaround, and waiting times
        for (int count = 0; count < n; count++) {
            int idx = -1;
            int minBurst = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= currentTime && !completed[i] && burstTime[i] < minBurst) {
                    minBurst = burstTime[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                currentTime++;
                count--;  // Skip to next iteration
            } else {
                completionTime[idx] = currentTime + burstTime[idx];
                turnaroundTime[idx] = completionTime[idx] - arrivalTime[idx];
                waitingTime[idx] = turnaroundTime[idx] - burstTime[idx];
                currentTime += burstTime[idx];
                completed[idx] = true;
            }
        }

        // Display results
        System.out.println("PID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" +
                               completionTime[i] + "\t\t" + turnaroundTime[i] + "\t\t" + waitingTime[i]);
        }

        sc.close();
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
