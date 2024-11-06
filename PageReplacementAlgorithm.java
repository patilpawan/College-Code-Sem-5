import java.util.*;

public class PageReplacementAlgorithm {

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
        int optimalPageFaults = optimal(referenceString, frames);
        
        // Display results
        System.out.println("\nPage Faults using LRU: " + lruPageFaults);
        System.out.println("Page Faults using Optimal: " + optimalPageFaults);
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

    // Optimal Page Replacement Algorithm
    public static int optimal(int[] referenceString, int frames) {
        Set<Integer> pageFrames = new HashSet<>(frames);
        int pageFaults = 0;

        for (int i = 0; i < referenceString.length; i++) {
            int page = referenceString[i];

            if (!pageFrames.contains(page)) {
                // Page fault occurs
                if (pageFrames.size() == frames) {
                    // Find the page that will not be used for the longest period in the future
                    int farthestPage = Integer.MIN_VALUE;
                    int farthestIndex = -1;

                    for (int p : pageFrames) {
                        int nextUseIndex = Integer.MAX_VALUE;
                        for (int j = i + 1; j < referenceString.length; j++) {
                            if (referenceString[j] == p) {
                                nextUseIndex = j;
                                break;
                            }
                        }
                        if (nextUseIndex > farthestIndex) {
                            farthestIndex = nextUseIndex;
                            farthestPage = p;
                        }
                    }
                    // Remove the page with the farthest usage
                    pageFrames.remove(farthestPage);
                }
                pageFrames.add(page);
                pageFaults++;
            }
        }
        return pageFaults;
    }
}
