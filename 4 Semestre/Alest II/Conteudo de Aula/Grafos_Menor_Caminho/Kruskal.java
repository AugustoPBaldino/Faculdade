import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*

O algoritmo de Kruskal é usado para encontrar a Árvore de Abrangência Mínima (Minimum Spanning Tree - MST)
 de um grafo ponderado não direcionado. A MST é uma árvore que conecta todos os vértices do grafo com a 
 menor soma dos pesos das arestas.



*/
class Kruskal {
    int V; // Número de vértices
    List<Edge> edges; // Lista de arestas

    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
    }

    public void kruskalMST() {
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(V);
        int mstWeight = 0;

        for (Edge edge : edges) {
            int u = edge.from;
            int v = edge.to;
            int w = edge.weight;

            if (ds.find(u) != ds.find(v)) {
                ds.union(u, v);
                mstWeight += w;
            }
        }

        System.out.println("Peso total da MST: " + mstWeight);
    }
}

class Edge implements Comparable<Edge> {
    int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class DisjointSet {
    int[] parent;
    int[] rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}
