package dijkstra;

import java.util.List;

public class Map {
    private List<Vert> vertList;

    public Map(List<Vert> vertList) {
        this.vertList = vertList;
    }

    public List<Vert> getVertList() {
        return vertList;
    }

    public void setVertList(List<Vert> vertList) {
        this.vertList = vertList;
    }

    public void addVert(Vert vert) {
        this.vertList.add(vert);
    }

    public void removeVert(Vert vert) {
        this.vertList.remove(vert);
    }

    public Vert getVert(String name) throws Exception {
        for (Vert vert : vertList) {
            if (vert.getName().equals(name)) {
                return vert;
            }
        }

        throw new Exception("Vert not found");
    }
}
