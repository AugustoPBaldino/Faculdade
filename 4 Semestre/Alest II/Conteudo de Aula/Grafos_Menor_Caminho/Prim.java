import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*

O algoritmo de Prim é usado para encontrar a Árvore de Abrangência Mínima (Minimum Spanning Tree - MST) 
de um grafo ponderado não direcionado. A MST é uma árvore que conecta todos os vértices do grafo com a 
menor soma dos pesos das arestas.



*/


class Prim {
    int V; // Número de vértices
    List<List<Edge>> adj; // Lista de adjacência

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int w) {
        Edge edge = new Edge(u, v, w);
        adj.get(u).add(edge);
        adj.get(v).add(edge);
    }

    public void primMST() {
        boolean[] inMST = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>(V, (a, b) -> a.weight - b.weight);

        // Começa com o vértice 0
        pq.add(new Edge(0, -1, 0));

        int mstWeight = 0;

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int u = e.to;

            if (inMST[u]) continue;

            inMST[u] = true;
            mstWeight += e.weight;

            for (Edge edge : adj.get(u)) {
                if (!inMST[edge.other(u)]) {
                    pq.add(edge);
                }
            }
        }

        System.out.println("Peso total da MST: " + mstWeight);
    }
}

class Edge {
    int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int other(int vertex) {
        return vertex == from ? to : from;
    }
}
