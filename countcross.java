import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class countcross {
    static ArrayList<Integer>[] adj;
    static TreeSet<Integer> cows;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("countcross.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N*N];
        for(int i = 0; i < N*N; i++) {
            adj[i] = new ArrayList<Integer>();
            if (i % N > 0) {
                adj[i].add(i - 1);
            }
            if (i >= N) {
                adj[i].add(i - N);
            }
            if (i < N * (N - 1)) {
                adj[i].add(i + N);
            }
            if (i % N < N - 1)
            {
                adj[i].add(i+1);
            }
        }
        int a, b, c, d;
        for(int i = 0; i < R; i++)
        {
            st = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            d = Integer.parseInt(st.nextToken())-1;
            adj[a*N+b].remove(Integer.valueOf(c*N+d));
            adj[c*N+d].remove(Integer.valueOf(a*N+b));
        }
        cows = new TreeSet<Integer>();
        for(int i = 0; i < K; i++)
        {
            st = new StringTokenizer(reader.readLine());
            cows.add((Integer.parseInt(st.nextToken())-1)*N+Integer.parseInt(st.nextToken())-1);
        }
        int pairs = 0;
        for(int i : cows)
        {
            pairs += dfs(i);
        }
        writer.println((K*(K-1)-pairs)/2);
        reader.close();
        writer.close();
    }
    static int dfs(int v)
    {
        int num = 0;
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] visited = new boolean[adj.length];
        visited[v] = true;
        stack.push(v);
        int curr;
        while(!stack.isEmpty())
        {
            curr = stack.pop();
            for(int i : adj[curr])
            {
                if(!visited[i])
                {
                    visited[i] = true;
                    stack.push(i);
                    if(cows.contains(i)) num++;
                }
            }
        }
        return num;
    }
}
