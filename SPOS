import java.util.Scanner;

public class MemoryAllocation {

    // Function to simulate First Fit
    public static void firstFit(int blockSize[], int processSize[]) {
        int allocation[] = new int[processSize.length];
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1; // Initialize all allocations as unallocated
        }
        
        for (int i = 0; i < processSize.length; i++) {
            for (int j = 0; j < blockSize.length; j++) {
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    break;
                }
            }
        }
        
        System.out.println("\nFirst Fit Allocation:");
        printAllocation(processSize, allocation);
    }

    // Function to simulate Best Fit
    public static void bestFit(int blockSize[], int processSize[]) {
        int allocation[] = new int[processSize.length];
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        for (int i = 0; i < processSize.length; i++) {
            int bestIdx = -1;
            for (int j = 0; j < blockSize.length; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[bestIdx] > blockSize[j]) {
                        bestIdx = j;
                    }
                }
            }

            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blockSize[bestIdx] -= processSize[i];
            }
        }
        
        System.out.println("\nBest Fit Allocation:");
        printAllocation(processSize, allocation);
    }

    // Function to simulate Worst Fit
    public static void worstFit(int blockSize[], int processSize[]) {
        int allocation[] = new int[processSize.length];
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        for (int i = 0; i < processSize.length; i++) {
            int worstIdx = -1;
            for (int j = 0; j < blockSize.length; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (worstIdx == -1 || blockSize[worstIdx] < blockSize[j]) {
                        worstIdx = j;
                    }
                }
            }

            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blockSize[worstIdx] -= processSize[i];
            }
        }

        System.out.println("\nWorst Fit Allocation:");
        printAllocation(processSize, allocation);
    }

    // Function to simulate Next Fit
    public static void nextFit(int blockSize[], int processSize[]) {
        int allocation[] = new int[processSize.length];
        int j = 0; // Start from the last allocated block
        
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        for (int i = 0; i < processSize.length; i++) {
            while (j < blockSize.length) {
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    break;
                }
                j = (j + 1) % blockSize.length; // Wrap around if end of array reached
            }
        }

        System.out.println("\nNext Fit Allocation:");
        printAllocation(processSize, allocation);
    }

    // Utility function to print allocation results
    private static void printAllocation(int processSize[], int allocation[]) {
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < processSize.length; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.print(allocation[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of memory blocks:");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter size of each block:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = sc.nextInt();
        }
        
        System.out.println("Enter number of processes:");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter size of each process:");
        for (int i = 0; i < n; i++) {
            processSize[i] = sc.nextInt();
        }

        // Copy original block sizes for each strategy as they modify the array
        int[] blockSizeCopy = blockSize.clone();
        firstFit(blockSizeCopy, processSize);

        blockSizeCopy = blockSize.clone();
        bestFit(blockSizeCopy, processSize);

        blockSizeCopy = blockSize.clone();
        worstFit(blockSizeCopy, processSize);

        blockSizeCopy = blockSize.clone();
        nextFit(blockSizeCopy, processSize);

        sc.close();
    }
}
  
