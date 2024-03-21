# Audie's Party - Manuel Morales


## Introduction

Audie recently moved to a new town and decided to throw a party to bond with her neighbors.
She wants to invite as many guests as possible while ensuring they get along well.
Additionally, Audie wants to divide the guests into groups where members have strong friendships.
This README presents a programming solution to help Audie with her party planning.

## Problem Description

Audie has a list of villagers and their relationships stored in a text file.
Each line contains two villagers and a numerical value representing their compatibility.
The task is to generate a guest list based on compatibility and divide them into groups with strong
friendships.

## Problems

1. Filter villagers who have a compatibility value greater than `x`.
2. Form groups of `k` guests ensuring they have strong friendships and has the major amount of
   theses.

## Solution

### Read File

- ***Algorithm:***
    - The algorithm used in this method is a linear scan of the file, splitting each line into separate components and adding them to the appropriate lists.
- ***Time Complexity***
    - O (n), where `n` is the number of lines in the file.

### Parse to Graph

- ***Algorithm:***
  - Parses the graph from the file and returns it. This method uses an undirected graph implementation to represent the graph.
- ***Time Complexity***
  - The time complexity of this method is O(n + m), where n is the number of nodes and m is the number of edges.

### Get Maximum Spanning Tree

- ***Algorithm:***
    - Applies the Kruskal's algorithm to find the maximum spanning tree (MST) of the given graph. This algorithm is a greedy algorithm that finds a maximum spanning tree for a connected weighted graph.
- ***Time Complexity***
    - The time complexity of this algorithm is O(E log V), where E is the number of edges and V is the number of vertices in the graph.

### Filter Villagers

- ***Algorithm:***
    - This method returns a set of nodes from the graph where all edges have a weight greater than a given value. It first removes all edges with a weight less than or equal to the given value, then removes all nodes without edges. Finally, it transforms the graph into a maximum graph using a greedy algorithm and returns all nodes from the maximum graph.

- ***Time Complexity***
    - The time complexity of this algorithm is O(E log V) where E is the number of edges and V is the number of vertices in the graph.

### Form Groups

- ***Algorithm:***
    - This method groups the nodes of the graph into k groups. It uses a greedy algorithm that iterates over the edges of the graph. For each edge, if the source and destination nodes are not in the same group, it merges the groups of the source and destination nodes. The algorithm stops when the desired number of groups is reached or when all edges have been processed.

- ***Time Complexity***
    - The time complexity of this algorithm is O(E + V) where E is the number of edges and V is the number of vertices in the graph.

## Conclusion

- ***Time Complexity***
    - We can conclude that the time complexity of the solution is O(E log V) where E is the number of edges and V is the number of vertices in the graph.
