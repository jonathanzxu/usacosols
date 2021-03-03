import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class moocast {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st;
        int N = Integer.parseInt(reader.readLine());
        ArrayList<Cow>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++)
        {
            graph[i] = new ArrayList<Cow>();
        }
        int[] x = new int[N];
        int[] y = new int[N];
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                graph[i].add(new Cow(j, (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])));
            }
        }
        int lo = 2, hi = 1250000000;
        while(lo < hi)
        {
            int mid = (lo + hi)/2;
            if(dfs(graph, mid))
            {
                hi = mid;
            }
            else lo = mid+1;
        }
        writer.println(hi);
        reader.close();
        writer.close();
    }
    public static boolean dfs(ArrayList<Cow>[] graph, int x)
    {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        boolean[] visited = new boolean[graph.length];
        int visits = 1;
        visited[0] = true;
        int curr;
        while(!stack.isEmpty())
        {
            curr = stack.pop();
            for (Cow c : graph[curr])
            {
                if(!visited[c.index] && c.dist <= x)
                {
                    stack.push(c.index);
                    visited[c.index] = true;
                    visits++;
                }
            }
        }
        return visits == graph.length;
    }
}
class Cow
{
    int index, dist;
    public Cow(int i, int d)
    {
        index = i;
        dist = d;
    }
}
