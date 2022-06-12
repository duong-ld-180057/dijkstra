package dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadMap {
    private static String getVertName(String vertex) {
        // vertex string in format "<vertex name>_xx_yy"
        // vertex name is in the first part of the string
        String[] vertexSplit = vertex.split("_");
        return vertexSplit[0];
    }

    private static Vert getVert(List<Vert> vertList, String vertName) {
        for (Vert vert : vertList) {
            if (vert.getName().equals(vertName)) {
                return vert;
            }
        }

        Vert newVert = new Vert(vertName);
        vertList.add(newVert);
        return newVert;
    }

    public static dijkstra.Map readMap(String fileName) {
        List<Vert> vertList = new ArrayList<>();
        String line;
        String[] lineSplit;

        try (Scanner sc = new Scanner(new java.io.File(fileName))) {
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                lineSplit = line.split(" ");

                String vertStartName = getVertName(lineSplit[0]);
                Vert vertStart = getVert(vertList, vertStartName);

                double weight = Double.parseDouble(lineSplit[1]);

                String tmpName = "\0";
                Vert tmp;
                for (int i = 2; i < lineSplit.length; i++) {
                    tmpName = getVertName(lineSplit[i]);
                    tmp = getVert(vertList, tmpName);

                    vertStart.addNeighbour(new Edge(weight, vertStart, tmp));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return new dijkstra.Map(vertList);
    }

    public static boolean hasPath(String mapFile, String start, String end) {
        try {
            dijkstra.Map mapObj = readMap(mapFile);
            Vert startVert = mapObj.getVert(start);
            Vert endVert = mapObj.getVert(end);

            PathFinder pathFinder = new PathFinder();
            pathFinder.ShortestP(startVert);
            System.out.println(pathFinder.getShortestP(endVert));

            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            dijkstra.Map map = readMap("input.txt");

            System.out.println("Enter start vertex: ");
            String startVert = sc.nextLine();
            System.out.println("Enter end vertex: ");
            String endVert = sc.nextLine();

            PathFinder pathFinder = new PathFinder();
            Vert start = map.getVert(startVert);
            pathFinder.ShortestP(start);

            Vert end = map.getVert(endVert);
            System.out.println("Shortest path: " + pathFinder.getShortestP(end));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // System.out.println(hasPath("input.txt", "E2", "-E4"));
    }
}
