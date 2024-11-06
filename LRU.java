import java.util.*;

public class LRU {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input: reference string and number of frames
        System.out.print("Enter the number of frames: ");
        int frames = scanner.nextInt();
        System.out.print("Enter the number of pages in the reference string: ");
        int numPages = scanner.nextInt();
        int[] referenceString = new int[numPages];
        
        System.out.println("Enter the reference string (space-separated integers): ");
        for (int i = 0; i < numPages; i++) {
            referenceString[i] = scanner.nextInt();
        }
        
        // Running LRU and Optimal algorithms
        int lruPageFaults = lru(referenceString, frames);
        
        
        // Display results
        System.out.println("\nPage Faults using LRU: " + lruPageFaults);
       
    }
    
    // Least Recently Used (LRU) Algorithm
    public static int lru(int[] referenceString, int frames) {
        Set<Integer> pageFrames = new HashSet<>(frames);
        Map<Integer, Integer> pageIndices = new HashMap<>();
        int pageFaults = 0;

        for (int i = 0; i < referenceString.length; i++) {
            int page = referenceString[i];
            
            if (!pageFrames.contains(page)) {
                // Page fault occurs
                if (pageFrames.size() == frames) {
                    // Find the least recently used page
                    int lruPage = Integer.MAX_VALUE;
                    int lruIndex = Integer.MAX_VALUE;
                    for (int p : pageFrames) {
                        int lastUsedIndex = pageIndices.get(p);
                        if (lastUsedIndex < lruIndex) {
                            lruIndex = lastUsedIndex;
                            lruPage = p;
                        }
                    }
                    // Remove the least recently used page
                    pageFrames.remove(lruPage);
                }
                pageFrames.add(page);
                pageFaults++;
            }
            // Update last used index for the page
            pageIndices.put(page, i);
        }
        return pageFaults;
    }

   
}
