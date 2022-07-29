import java.util.*;

public class Main {
//POJO for vertex data
    public static class Vertex{
        int x;
        int y;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    //POJO for Component data
    public static class Component{
        List<Vertex> vertices;
        boolean isIsland;

        public Component() {
            this.vertices = new ArrayList<Vertex>();
            this.isIsland = true;
        }

        @Override
        public String toString() {
            return "Component{" +
                    "vertices=" + vertices +
                    ", isIsland=" + isIsland +
                    '}';
        }
    }

    //preforms a DFS on vertex with values of 1
    public static void dfs(int x, int y, boolean[][] visited,List<Component> components, int count){

        //set
        visited[y][x] = true;
        components.get(count).vertices.add(new Vertex(x,y));

        //checks if vertex is connected ot boarder
        if(x == 0 || x == array[y].length-1 || y == 0 || y == array.length-1){
            //sets component island status to false
            components.get(count).isIsland = false;
        }
        //look up
        if(y != 0 && !visited[y-1][x] && array[y-1][x] == 1){
            dfs(x,y-1, visited,components,count);
        }
        //look right
        if (x != array[y].length-1 && !visited[y][x+1] && array[y][x+1] == 1) {
            dfs(x+1,y, visited,components,count);
        }
        //look down
        if(y != array.length-1 && !visited[y+1][x] && array[y+1][x] == 1){
            dfs(x,y+1, visited,components,count);
        }
        //look left
        if (x != 0 && !visited[y][x-1] && array[y][x-1] == 1) {
            dfs(x-1,y, visited,components,count);
        }
        //return once all routes exhausted
        return;
    }

    //finds and removes all island values in 2D boolean matrix
    public static void findIslands(){

        //initialize boolean array for tracking visited Nodes
        boolean[][] visited = new boolean[array.length][array[0].length];
        List<Component> components = new ArrayList();
        int count = 0;
        for(int y = 1; y < array.length-1;y++){
            for(int x = 1; x < array[x].length-1; x++){
                if(array[y][x] == 1 && !visited[y][x]){
                    //create new component
                    Component component = new Component();
                    //add to components
                    components.add(component);
                    // call recursive DFS function
                    dfs(x,y,visited,components,count);
                    //increment index of component list
                    count++;
                }
            }
        }

        //set all vertex in island components to 0
        components.stream().forEach(e -> {
            if(e.isIsland){
                e.vertices.forEach(v -> {
                    array[v.y][v.x] = 0;
                });
            }
        });
    }

    public static void main(String [] args){
        findIslands();
        print2D(array);
    }

    //input
    public static Integer[][] array = {
            {1, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1},
            {0, 0, 1, 0, 1, 0},
            {1, 1, 0, 0, 1, 0},
            {1, 0, 1, 1, 0, 0},
            {1, 0, 0, 0, 0, 1},
    };

    //simple 2D print method
    public static void print2D(Integer mat[][])
    {
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[i].length; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
