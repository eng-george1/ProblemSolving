package LeetCode.Pinterest;

import java.util.*;
import java.util.regex.MatchResult;

public class RoutePlanner {

    public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn, boolean[][] mapMatrix) {
        //use a new 2-d array to record if the node has been visted or not
        boolean[][] mapVisited = new boolean[mapMatrix.length][mapMatrix[0].length];   
        return routeSearch(fromRow, fromColumn, toRow, toColumn,mapMatrix, mapVisited);  
    }
    // a helper method with memo
    public static boolean routeSearch(int fromRow, int fromColumn, int toRow, int toColumn, boolean[][] mapMatrix, boolean[][] mapVisited) {
        // out of boundrary
        if(fromRow < 0 || fromColumn < 0 || fromRow >= mapMatrix.length || fromColumn >= mapMatrix[0].length)
            return false;
        // if return back to the start point or start and exit is false
        if(mapVisited[fromRow][fromColumn] || !mapMatrix[fromRow][fromColumn]|| !mapMatrix[toRow][toColumn])
            return false;
        //if start equals false
        if(fromRow == toRow && fromColumn == toColumn)
            return true;
        //initialize the start point as visited
        mapVisited[fromRow][fromColumn] = true;
        // 4 directions: left right up down any way is true return true;
        return routeSearch(fromRow-1, fromColumn, toRow, toColumn, mapMatrix, mapVisited)
            || routeSearch(fromRow, fromColumn-1, toRow, toColumn, mapMatrix, mapVisited)
            || routeSearch(fromRow+1, fromColumn, toRow, toColumn, mapMatrix, mapVisited)            
            ||routeSearch(fromRow, fromColumn+1, toRow, toColumn, mapMatrix, mapVisited);
    }

    public static void main(String[] args) {
        boolean[][] mapMatrix = {
                { true, false, false },
                { true, true, false },
                { false, true, true }
        };

        System.out.println(routeExists(2, 2, 2, 2, mapMatrix));
    }
}
