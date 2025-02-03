/*
 * Copyright (c) 2024. Robin Hillyard
 */

 package com.phasmidsoftware.dsaipg.adt.threesum;

 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 
 /**
  * Implementation of ThreeSum which follows the approach of dividing the solution-space into
  * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
  * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
  * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
  * <p>
  * NOTE: The array provided in the constructor MUST be ordered.
  */
 public class ThreeSumQuadratic implements ThreeSum {
     /**
      * Construct a ThreeSumQuadratic on a.
      *
      * @param a a sorted array.
      */
     public ThreeSumQuadratic(int[] a) {
         this.a = a;
         length = a.length;
     }
 
     /**
      * Retrieves an array of unique Triples. Each Triple represents a unique combination of three integers from
      * the source array that sum to zero.
      *
      * @return an array of distinct Triples, sorted in natural order, where each Triple satisfies the condition that
      * the sum of its three integers is zero.
      */
     public Triple[] getTriples() {
         List<Triple> triples = new ArrayList<>();
         for (int j = 0; j < length; j++) {
             triples.addAll(getTriples(j));
         }
         Collections.sort(triples);
         return triples.stream().distinct().toArray(Triple[]::new);
     }
 
     /**
      * Get a list of Triples such that the middle index is the given value j.
      *
      * @param j the index of the middle value.
      * @return a list of Triples where each Triple satisfies a[i] + a[j] + a[k] = 0.
      */
     List<Triple> getTriples(int j) {
         List<Triple> triples = new ArrayList<>();
         int target = -a[j];  // We want to find pairs that sum to -a[j]
         
         // Use two pointers to find the pairs that sum to target
         int i = 0;
         int k = length - 1;
         
         while (i < j && k > j) {
             int sum = a[i] + a[k];
             if (sum == target) {
                 triples.add(new Triple(a[i], a[j], a[k]));
                 // Move both pointers to avoid duplicates
                 while (i < j && a[i] == a[i + 1]) i++;  // Skip duplicates on the left
                 while (k > j && a[k] == a[k - 1]) k--;  // Skip duplicates on the right
                 i++;
                 k--;
             } else if (sum < target) {
                 i++;
             } else {
                 k--;
             }
         }
         
         return triples;
     }
 
     private final int[] a;
     private final int length;
 }
 