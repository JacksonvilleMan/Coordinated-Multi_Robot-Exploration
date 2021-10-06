public class Grid {
    private class Cell {
        private boolean wall;
        private boolean occupied;
        private boolean frontier;
        private boolean visited;

        public Cell() {
            this.wall = false;
            this.occupied = false;
            this.frontier = false;
        }

        private void setWall() {
            this.wall = true;
            this.occupied = true;
            this.visited = true;
        }
        private void setOccupied() {
            this.occupied = true;
        }
        private void setFrontier() {
            this.frontier = true;
        }
        private void setVisited() {
            this.visited = true;
        }

        private void unsetOccupied() {
            this.occupied = false;
        }
        private void unsetFrontier() {
            this.frontier = false;
        }

        private boolean getWall() {
            return this.wall;
        }
        private  boolean getOccupied() {
            return this.occupied;
        }
        private boolean getFrontier() {
            return this.frontier;
        }
        private boolean getVisited() {
            return this.visited;
        }
    }

    private int gridSize;
    private int visitedCells;
    private Cell[][] grid;

    public Grid(int gridSize) {
        this.gridSize = gridSize + 1;
        this.visitedCells = 0;
        this.grid = new Cell[this.gridSize][this.gridSize];

        for(int i = 0; i < this.gridSize; i++) {
            for(int j = 0; j < this.gridSize; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void setWall(int x, int y) {
        this.grid[x][y].setWall();
        this.visitedCells++;
    }
    public void setOccupied(int x, int y) {
        this.grid[x][y].setOccupied();
    }
    public void setFrontier(int x, int y) {
        this.grid[x][y].setFrontier();
    }
    public void setVisited(int x, int y) {
        this.grid[x][y].setVisited();
        this.visitedCells++;
    }

    public void unsetOccupied(int x, int y) {
        this.grid[x][y].unsetOccupied();
    }
    public void unsetFrontier(int x, int y) {
        this.grid[x][y].unsetFrontier();
    }

    public boolean getWall(int x, int y) {
        return this.grid[x][y].getWall();
    }
    public boolean getOccupied(int x, int y) {
        return this.grid[x][y].getOccupied();
    }
    public boolean getFrontier(int x, int y) {
        return this.grid[x][y].getFrontier();
    }
    public boolean getVisited(int x, int y) { return this.grid[x][y].getVisited(); }

    public int getVisitedCells() {
        return this.visitedCells;
    }
}