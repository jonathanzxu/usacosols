import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class piggyback {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("piggyback.in"));
        PrintWriter writer = new PrintWriter("piggyback.out");
        int B, E, P, N, M;
        StringTokenizer st = new StringTokenizer(reader.readLine());
        B = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
        {
            adj[i] = new ArrayList<Integer>();
        }
        int u, v;
        for(int i = 0; i < M; i++)
        {
            st = new StringTokenizer(reader.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        //boolean worth = P < B + E;
        int[] bdist = bfs(1, adj);
        int[] edist = bfs(2, adj);
        int[] fdist = bfs(N, adj);
        int mindist = Math.max(B + E, P) * M;
        for(int i = 1; i < N; i++)
        {
            mindist = Math.min(mindist, (B * bdist[i] + E * edist[i] + P * fdist[i]));
        }
        mindist = Math.min(mindist, (B * fdist[1] + E * fdist[2]));
        writer.println(mindist);
        reader.close();
        writer.close();
    }
    public static int[] bfs(int source, ArrayList<Integer>[] adj){
        int[] dist = new int[adj.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visited = new boolean[adj.length];
        dist[source] = 0;
        queue.add(source);
        visited[source] = true;
        while(!queue.isEmpty())
        {
            int node = queue.poll();
            for(int n : adj[node]){
                if(!visited[n]){
                    queue.add(n);
                    dist[n] = dist[node] + 1;
                    visited[n] = true;
                }
            }

        }
        return dist;
    }

}
