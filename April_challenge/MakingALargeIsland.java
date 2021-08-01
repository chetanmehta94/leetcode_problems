class Solution {
    public int largestIsland(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        boolean[][] visited = new boolean[r][c];
        int[][] islandGroup = new int[r][c];
        Map<Integer, Integer> groupSizeMap = new HashMap<>();
        int group = 1;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(grid[i][j]==1 && !visited[i][j]){
                    int islandArea = 0;
                    Queue<int[]> bfsQ = new LinkedList<>();
                    bfsQ.add(new int[]{i,j});
                    while(!bfsQ.isEmpty()) {
                        int[] pos = bfsQ.remove();
                        int posi = pos[0];
                        int posj = pos[1];
                        if(visited[posi][posj]) {
                            continue;
                        }
                        islandArea++;
                        visited[posi][posj] = true;
                        islandGroup[posi][posj] = group;
                        for(int[] nd: NEIGHBOUR_DELTAS) {
                            int ni = posi+nd[0];
                            int nj = posj+nd[1];
                            if(isValidPosition(grid, ni, nj) && grid[ni][nj]==1) {
                                bfsQ.add(new int[] {ni, nj});
                            }
                        }
                    }
                    groupSizeMap.put(group, islandArea);
                    group++;
                }
            }
        }
        
        int greatestIslandSize = 0;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(grid[i][j]==0){
                    int islandSize = 1;
                    Set<Integer> neighbouringUniqueIslandGroups = new HashSet<>();
                    for(int[] nd: NEIGHBOUR_DELTAS) {
                        int ni = i+nd[0];
                        int nj = j+nd[1];
                        if(isValidPosition(grid, ni, nj) && grid[ni][nj]==1) {
                            int nig = islandGroup[ni][nj];
                            if(!neighbouringUniqueIslandGroups.contains(nig)){
                                int nigSize = groupSizeMap.get(nig);
                                islandSize += nigSize;
                                neighbouringUniqueIslandGroups.add(nig);
                            }
                        }
                    }
                    greatestIslandSize = Math.max(greatestIslandSize, islandSize);
                }
            }
        }
        return greatestIslandSize==0 ? r*c : greatestIslandSize;
    }
    
    public boolean isValidPosition(int[][] grid, int x, int y) {
        return x>=0 && x<grid.length && y>=0 && y<grid[0].length;
    }
    
    public static int[][] NEIGHBOUR_DELTAS = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    }; 
}
