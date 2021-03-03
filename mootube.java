import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class mootube {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        ArrayList<Video>[] graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++)
        {
            graph[i] = new ArrayList<Video>();
        }
        int p, q, r;
        for(int i = 0; i < N-1; i++)
        {
            st = new StringTokenizer(reader.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            graph[p].add(new Video(q, r));
            graph[q].add(new Video(p, r));
        }
        for(int i = 0; i < Q; i++)
        {
            st = new StringTokenizer(reader.readLine());
            writer.println(dfs(graph, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        reader.close();
        writer.close();
    }
    public static int dfs(ArrayList<Video>[] graph, int K, int V)
    {
        int vids = 0;
        Stack<Integer> stack = new Stack<Integer>();
        int curr;
        boolean[] visited = new boolean[graph.length];
        int[] minrelevance = new int[graph.length];
        minrelevance[V] = Integer.MAX_VALUE;
        stack.push(V);
        visited[V] = true;
        while(!stack.isEmpty()) {
            curr = stack.pop();
            for (Video v : graph[curr]) {
                if(!visited[v.vid]) {
                    stack.push(v.vid);
                    visited[v.vid] = true;
                    minrelevance[v.vid] = Math.min(minrelevance[curr], v.rel);
                    if (minrelevance[v.vid] >= K) {
                        vids++;
                    }
                }
            }
        }
        return vids;
    }
}
class Video
{
    int vid, rel;
    public Video(int v, int r)
    {
        vid = v;
        rel = r;
    }
}
