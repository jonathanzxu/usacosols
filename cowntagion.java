import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class cowntagion {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st;
        int N = Integer.parseInt(reader.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++)
        {
            graph[i] = new ArrayList<Integer>();
        }
        int a, b;
        for(int i = 0; i < N-1; i++)
        {
            st = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        System.out.println(dfs(graph)-1);
        reader.close();
        //writer.close();
    }
    public static int dfs(ArrayList<Integer>[] graph)
    {
        int days = 0;
        boolean[] visited = new boolean[graph.length];
        visited[1] = true;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        int curr, children, x;
        while(!stack.isEmpty())
        {
            children = 0;
            days++;
            curr = stack.pop();
            for(int i : graph[curr])
            {
                if(!visited[i])
                {
                    visited[i] = true;
                    children++;
                    stack.push(i);
                }
            }
            x = 1;
            while(x < children+1)
            {
                x *= 2;
                days++;
            }
        }
        return days;
    }
}
