
//package Tests;
//import Lab5.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;

import java.lang.Math;

/**
 *
 * @author Ashpan Raskar 400185326
 */
public class TestMaxHeap {   
    public static void main(String[] args) {
        MaxHeap heap1 = new MaxHeap(3);
        System.out.println("Heap1: " + heap1.toString());
        System.out.println("Adding 2 elements");
        heap1.insert(4);
        heap1.insert(44);
        System.out.println("Heap size: " + heap1.getSize());
        System.out.println("Heap capacity: " + heap1.getCapacity());
        System.out.println("Adding 2 elements");
        heap1.insert(64);
        heap1.insert(18);
        System.out.println("Heap size: " + heap1.getSize());
        System.out.println("Heap capacity: " + heap1.getCapacity());
        System.out.println("Heap1: " + heap1.toString());
        System.out.println("Creating heap from array = 1, 5, 1546, 4896, 4");
        MaxHeap heap2 = new MaxHeap(new Integer[] {1, 5, 1546, 4896, 4});
        System.out.println("Heap2: " + heap2.toString());
        System.out.println("Adding 69");
        heap2.insert(69);
        System.out.println("Heap2: " + heap2.toString());
        Integer[] arr = new Integer[] {1, 5, 1546, 4896, 4};
        System.out.println("Before Sort\t" + Arrays.toString(arr));
        MaxHeap.heapsort(arr);
        System.out.println("After Sort\t" + Arrays.toString(arr));
    }
}
