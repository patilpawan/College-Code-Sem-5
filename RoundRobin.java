import java.util.Scanner;

public class RoundRobin {

    int burst[], run[], np, quantum = 0, wait[], time = 0, rp, ta[];

    public RoundRobin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        np = sc.nextInt();
        rp = np; // remaining processes
        burst = new int[np];
        run = new int[np];
        wait = new int[np];
        ta = new int[np];
        
        System.out.println("Enter the burst times for each process:");
        for (int i = 0; i < np; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            burst[i] = sc.nextInt();
            run[i] = burst[i]; // initialize run time with burst time
            wait[i] = 0;        // initialize wait times to zero
        }

        System.out.println("Enter Quantum time: ");
        quantum = sc.nextInt();
        
        logic();
        sc.close();
    }

    public void logic() {
        int i = 0;
        while (rp != 0) {
            if (run[i] > quantum) {
                run[i] -= quantum; // reduce remaining time by quantum
                time += quantum;   // increase the total time
                System.out.println("Process: " + (i + 1) + " | Time: " + time);
            } else if (run[i] > 0) { // Process can finish within the quantum
                time += run[i];      // add remaining run time to total time
                run[i] = 0;          // process finished
                ta[i] = time;        // record turnaround time
                rp--;                // reduce count of remaining processes
                System.out.println("Process: " + (i + 1) + " | Time: " + time);
            }

            i = (i + 1) % np; // cycle through processes
        }

        // Calculate waiting times
        System.out.println("\nProcess\tBurst Time\tTurnaround Time\tWaiting Time");
        for (int j = 0; j < np; j++) {
            wait[j] = ta[j] - burst[j]; // waiting time = turnaround - burst
            System.out.println((j + 1) + "\t\t" + burst[j] + "\t\t" + ta[j] + "\t\t" + wait[j]);
        }
    }

    public static void main(String[] args) {
        new RoundRobin();
    }
}
