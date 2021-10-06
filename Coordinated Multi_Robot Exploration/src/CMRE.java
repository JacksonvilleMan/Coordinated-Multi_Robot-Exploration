import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSinkImages;
import scala.Int;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class CMRE {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int option = 0;

        int gridSize = 0;
        int robotCount = 0;
        int wallPercent = 0;

        System.out.print("1. 3_Robots\n2. 4_Robots\n3. 5_Robots\n4. Custom\n\tOption -> ");
        option = scanner.nextInt();

        switch (option) {
            case 1:
                gridSize = 50;
                robotCount = 3;
                break;
            case 2:
                gridSize = 50;
                robotCount = 4;
                break;
            case 3:
                gridSize = 50;
                robotCount = 5;
                break;
            case 4:
                System.out.print("Grid Size: ");
                gridSize = scanner.nextInt();
                System.out.print("Robot Count: ");
                robotCount = scanner.nextInt();
                System.out.print("Wall Percent: ");
                wallPercent = scanner.nextInt();
                break;
        }

        Graph graph = new DefaultGraph("graph");
        Generator generator = new GridGenerator();

        graph.setAttribute("ui.stylesheet", "url('file:src/stylesheet.txt')");

        generator.addSink(graph);
        generator.begin();
        for(int i = 0; i < gridSize; i++)
            generator.nextEvents();
        generator.end();

        Grid grid = new Grid(gridSize);
        Robot[] robots = new Robot[robotCount];

        int nodeCount = graph.getNodeCount();

        int wallCount = (nodeCount * wallPercent) / 100;
        int[] randWalls = new int[wallCount];
        int[] randBots = new int[robotCount];

        Random random = new Random();

        if(option == 4) {
            for (int i = 0; i < wallCount; i++) {
                int randNum = random.nextInt(nodeCount);
                for (int j = 0; j < i; j++) {
                    if (randNum == randWalls[j]) {
                        randNum = random.nextInt(nodeCount);
                        j = -1;
                    }
                }
                randWalls[i] = randNum;

                Node n = graph.getNode(randNum);
                String[] xy = n.toString().split("_");

                grid.setWall(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
                n.setAttribute("ui.class", "wall");
            }

            for(int i = 0; i < robotCount; i++) {
                int randNum = random.nextInt(nodeCount);
                for(int j = 0; j < wallCount; j++) {
                    if(randNum == randWalls[j]) {
                        randNum = random.nextInt(nodeCount);
                        j = -1;
                    }
                }
                for(int j = 0; j < i; j++) {
                    if(randNum == randBots[j]) {
                        randNum = random.nextInt(nodeCount);
                        j = -1;
                    }
                }
                randBots[i] = randNum;

                Node n = graph.getNode(randNum);
                String[] xy = n.toString().split("_");

                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);

                robots[i] = new Robot("robot" + i, x, y);
                grid.setOccupied(x, y);
                grid.setVisited(x, y);

                if(grid.getFrontier(x, y))
                    grid.unsetFrontier(x, y);

                int x1 = x - 1;
                int x2 = x + 1;
                int y1 = y - 1;
                int y2 = y + 1;

                if(x1 >= 0) {
                    if(!grid.getOccupied(x1, y))
                        grid.setFrontier(x1, y);
                }
                if(x2 <= gridSize) {
                    if(!grid.getOccupied(x2, y))
                        grid.setFrontier(x2, y);
                }
                if(y1 >= 0) {
                    if(!grid.getOccupied(x, y1))
                        grid.setFrontier(x, y1);
                }
                if(y2 <= gridSize) {
                    if(!grid.getOccupied(x, y2))
                        grid.setFrontier(x, y2);
                }

                n.setAttribute("ui.class", robots[i].getName());
            }
        }
        else {
            for(int i = 0; i < 51; i++) {
                String coords = 0 + "_" + i;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(0, i);
            }
            for(int i = 0; i < 51; i++) {
                String coords = 50 + "_" + i;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(50, i);
            }
            for(int i = 0; i < 51; i++) {
                String coords = i + "_" + 0;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(i, 0);
            }
            for(int i = 0; i < 51; i++) {
                String coords = i + "_" + 50;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(i, 50);
            }

            for(int i = 0; i < 16; i++) {
                String coords = i + "_" + 15;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(i, 15);
            }

            for(int i = 0; i < 32 ; i++) {
                String coords = i + "_" + 34;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(i, 34);
            }
            for(int i = 34; i < 45; i++) {
                String coords = 18 + "_" + i;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
            }

            for(int i = 0; i < 21; i++) {
                String coords = 26 + "_" + i;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(26, i);
            }
            for(int i = 26; i > 16; i--) {
                String coords = i + "_" + 21;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(i, 21);
            }

            for(int i = 49; i > 34; i--) {
                String coords = i + "_" + 17;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(i, 17);
            }
            for(int i = 17; i < 35; i++) {
                String coords = 39 + "_" + i;
                Node n = graph.getNode(coords);
                n.setAttribute("ui.class", "wall");
                grid.setWall(39, i);
            }

            //robot_0
            Node n = graph.getNode("1_1");
            n.setAttribute("ui.class", "robot0");
            robots[0] = new Robot("robot0", 1, 1);
            grid.setOccupied(1, 1);
            grid.setVisited(1, 1);
            grid.setFrontier(2, 1);
            grid.setFrontier(1, 2);

            //robot_1
            n = graph.getNode("1_16");
            n.setAttribute("ui.class", "robot1");
            robots[1] = new Robot("robot1", 1, 16);
            grid.setOccupied(1, 16);
            grid.setVisited(1, 16);
            grid.setFrontier(1, 17);
            grid.setFrontier(2, 16);

            //robot_2
            n = graph.getNode("1_35");
            n.setAttribute("ui.class", "robot2");
            robots[2] = new Robot("robot2", 1, 35);
            grid.setOccupied(1, 35);
            grid.setVisited(1, 35);
            grid.setFrontier(1, 36);
            grid.setFrontier(2, 35);

            if(option == 2 || option == 3) {
                //robot_3
                n = graph.getNode("49_1");
                n.setAttribute("ui.class", "robot3");
                robots[3] = new Robot("robot3", 49, 1);
                grid.setOccupied(49, 1);
                grid.setVisited(49, 1);
                grid.setFrontier(49, 2);
                grid.setFrontier(48, 1);

                if(option == 3) {
                    //robot_4
                    n = graph.getNode("49_18");
                    n.setAttribute("ui.class", "robot");
                    robots[4] = new Robot("robot4", 49, 18);
                    grid.setOccupied(49, 18);
                    grid.setVisited(49, 18);
                    grid.setFrontier(49, 19);
                    grid.setFrontier(48, 18);
                }
            }
        }



        int jpgCount = 0;
        FileSinkImages pic = new FileSinkImages(FileSinkImages.OutputType.JPG, FileSinkImages.Resolutions.HD1080);

        double probabilityCoefficient = (double)(robotCount - 1) / robotCount;
        int visitedCells = grid.getVisitedCells();

        while(visitedCells != nodeCount) {
            String fileName = "pics/screenshot" + String.valueOf(jpgCount) + ".jpg";
            pic.writeAll(graph, fileName);
            jpgCount++;

            for(int i = 0; i < robotCount; i++) {
                String[] frontierCells = new String[nodeCount];
                int frontierCellCount = 0;

                for (int j = 0; j < gridSize + 1; j++) {
                    for (int k = 0; k < gridSize + 1; k++) {
                        if (grid.getFrontier(j, k))
                            frontierCells[frontierCellCount++] = j + "_" + k;
                    }
                }

                //System.out.println(frontierCellCount);

                if(frontierCellCount == 0) {
                    //System.out.println(visitedCells + " : " + nodeCount);

                    visitedCells = nodeCount;
                    break;
                }

                double[] frontierCosts = new double[frontierCellCount];
                String bestCostCoords = "";
                double bestCostValue = -999999;

                int x = robots[i].getCurrentX();
                int y = robots[i].getCurrentY();

                for (int j = 0; j < frontierCellCount; j++) {
                    String[] xy = frontierCells[j].split("_");
                    int tempX = Integer.parseInt(xy[0]);
                    int tempY = Integer.parseInt(xy[1]);
                    int changeX = x - tempX;
                    int changeY = y - tempY;

                    frontierCosts[j] = 1 - (Math.sqrt(Math.pow(changeX, 2) + Math.pow(changeY, 2)) * probabilityCoefficient);

                    if (j == 0) {
                        bestCostCoords = frontierCells[j];
                        bestCostValue = frontierCosts[j];
                    }

                    if (!grid.getOccupied(tempX, tempY)) {
                        if (frontierCosts[j] > bestCostValue) {
                            bestCostCoords = frontierCells[j];
                            bestCostValue = frontierCosts[j];
                        }
                    }
                }

                //System.out.println(bestCostCoords);
                String[] xy = bestCostCoords.split("_");

                grid.unsetOccupied(x, y);

                x = Integer.parseInt(xy[0]);
                y = Integer.parseInt(xy[1]);

                robots[i].setCurrentX(x);
                robots[i].setCurrentY(y);
                grid.unsetFrontier(x, y);
                grid.setOccupied(x, y);
                grid.setVisited(x, y);
                //System.out.println(robots[i].getName() + " : " + bestCostCoords);
                Node n = graph.getNode(bestCostCoords);
                n.setAttribute("ui.class", robots[i].getName());

                int x1 = x - 1;
                int x2 = x + 1;
                int y1 = y - 1;
                int y2 = y + 1;

                if (x1 >= 0) {
                    if (!grid.getOccupied(x1, y) && !grid.getVisited(x1, y))
                        grid.setFrontier(x1, y);
                }
                if (x2 <= gridSize) {
                    if (!grid.getOccupied(x2, y) && !grid.getVisited(x2, y))
                        grid.setFrontier(x2, y);
                }
                if (y1 >= 0) {
                    if (!grid.getOccupied(x, y1) && !grid.getVisited(x, y1))
                        grid.setFrontier(x, y1);
                }
                if (y2 <= gridSize) {
                    if (!grid.getOccupied(x, y2) && !grid.getVisited(x, y2))
                        grid.setFrontier(x, y2);
                }
                visitedCells = grid.getVisitedCells();
            }
        }
        String fileName = "pics/screenshot" + String.valueOf(jpgCount) + ".jpg";
        pic.writeAll(graph, fileName);

        graph.display(false);
    }
}