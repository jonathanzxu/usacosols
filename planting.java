import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class planting {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("planting.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        ArrayList<Integer>[] adj = new ArrayList[N+1];
        StringTokenizer st;
        int a, b;
        for(int i = 0; i < N+1; i++)
        {
            adj[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < N-1; i++)
        {
            st = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        int max = 0;
        for(int i = 0; i < N+1; i++)
        {
            max = Math.max(max, adj[i].size());
        }
        writer.println(max+1);
        reader.close();
        writer.close();
    }
}
