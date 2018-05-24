/* Leetcode.com problem #741 (https://leetcode.com/problems/cherry-pickup/description/)

Description:
In a N x N grid representing a field of cherries, each cell is one of three possible integers.

	0 means the cell is empty, so you can pass through;
	1 means the cell contains a cherry, that you can pick up and pass through;
	-1 means the cell contains a thorn that blocks your way.
	
Your task is to collect maximum number of cherries possible by following the rules below:

	Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
	After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
	When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
	If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.*/

// Strategy:  Use dynamic programming.  Created three-dimensional array to store "scores" of paths as we traverse down.  Takes O(n^3) time

class CherrySolution {
    public int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length;
        
        
        int[][][] memo=new int[n+1][n+1][n+1];
        for (int i = 0; i <= n;i++)
            for (int j = 0; j <= n; j++)
                for (int k = 0; k <= n; k++)
                    memo[i][j][k] = -1;
        
        memo[1][1][1] = grid[0][0];
        
        for (int x1 = 1; x1 <= n; x1++){
            for (int y1 = 1; y1 <= n; y1++){
                for (int x2 = 1; x2 <= n; x2++){
                    int y2 = x1 + y1 - x2;
                    if ( y2 <= 0 || y2 > n || memo[x1][y1][x2] != -1 || grid[x1-1][y1-1] < 0 || grid[x2-1][y2-1] < 0) 
                        continue;
                    int path1 = Math.max(memo[x1-1][y1][x2], memo[x1][y1-1][x2]);
                    int path2 = Math.max(memo[x1-1][y1][x2-1], memo[x1][y1-1][x2-1]);
                    int max = Math.max(path1, path2);
                    if (max == -1) continue;
                    
                    memo[x1][y1][x2] = max+grid[x1-1][y1-1];
                    if (x2 != x1) memo[x1][y1][x2] += grid[x2-1][y2-1];
                }
            }
        }
        
        if (memo[n][n][n] > 0) return memo[n][n][n];
        else return 0;

    }
}