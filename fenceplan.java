import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class fenceplan {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] x = new int[N+1];
        int[] y = new int[N+1];
        for(int i = 1; i < N+1; i++)
        {
            st = new StringTokenizer(reader.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<Integer>[] adj = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++)
        {
            adj[i] = new ArrayList<Integer>();
        }
        int a, b;
        for(int i = 0; i < M; i++)
        {
            st = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        int minperi = Integer.MAX_VALUE;
        boolean[] visited = new boolean[N+1];
        Stack<Integer> stack = new Stack<Integer>();
        int minl, maxr, minb, maxt, curr;
        for(int i = 1; i < N+1; i++)
        {
            if(!visited[i])
            {
                minl = x[i];
                maxr = x[i];
                minb = y[i];
                maxt = y[i];
                visited[i] = true;
                stack.push(i);
                while(!stack.isEmpty())
                {
                    curr = stack.pop();
                    for(int j : adj[curr]) {
                        if (!visited[j])
                        {
                            visited[j] = true;
                            minl = Math.min(minl, x[j]);
                            maxr = Math.max(maxr, x[j]);
                            minb = Math.min(minb, y[j]);
                            maxt = Math.max(maxt, y[j]);
                            stack.push(j);
                        }
                    }
                }
                minperi = Math.min(minperi, 2*((maxr - minl) + (maxt - minb)));
            }
        }
        writer.println(minperi);
        reader.close();
        writer.close();
    }
}
