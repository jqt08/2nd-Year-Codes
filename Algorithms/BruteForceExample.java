//bruteforce implementation
public class BruteForceExample {
public static void main(String[] args) { 
    int[] a = {
1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
//Task: Find the index of the number 30 in the array.
int target = 30;
//Brute force solution
bruteForceSearch(a, target);
//More optimal solution
int index = binarySearch(a, target);
System.out.println("More Optimal. This is the index: " + index);
}
private static void bruteForceSearch(int[] a, int target) { 
    for (int i=0; i < a.length; i++){
        if (a[i] == target) {
            System.out.println("BF. This is the index: " + i);
            break;
        }
        }
    }
private static int binarySearch (int[] a, int target) { 
    int lo = 0;
    int hi = a.length -1;
while (hi >= lo) {
    int mid = lo + (hi - lo) / 2;
    if (target < a[mid]) hi= mid -1; 
    else if (target > a[mid]) lo = mid +1; 
    else return mid;
}
return -1;
}
}
