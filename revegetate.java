import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class revegetate {
    static ArrayList<Integer>[] sadj;
    static ArrayList<Integer>[] dadj;
    static boolean[] visited;
    static boolean valid = true;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
        //run bipartiteness check recursive dfs. 2 adj. lists for same and different
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        sadj = new ArrayList[N+1];
        dadj = new ArrayList[N+1];
        visited = new boolean[N+1];
        int a, b;
        for(int i = 1; i < N+1; i++)
        {
            sadj[i] = new ArrayList<Integer>();
            dadj[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < M; i++)
        {
            st = new StringTokenizer(reader.readLine());
            if(st.nextToken().equals("S"))
            {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                sadj[a].add(b);
                sadj[b].add(a);
            }
            else
            {
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                dadj[a].add(b);
                dadj[b].add(a);
            }
        }
        int[] color = new int[N+1];
        //-1 seed, 1 other seed, 0 unvisited
        int components = 0;
        //color[1] = 1;

        for(int i = 1; i < N+1; i++)
        {
            if(!visited[i]) {
                visited[i] = true;
                color[i] = 1;
                dfs(i, color);
                components++;
            }
        }
        String ans = "1";
        for(int i = 0; i < components; i++)
        {
            ans += "0";
        }
        if(valid) writer.println(ans);
        else writer.println(0);
        reader.close();
        writer.close();
    }
    static void dfs(int v, int[] color)
    {
        Stack<Integer> stack = new Stack<Integer>();
        int curr = v;
        stack.add(v);
        while(!stack.isEmpty()) {
            for (int i : sadj[curr]) {
                if (color[i] == 0) {
                    color[i] = color[curr];
                    visited[i] = true;
                    stack.push(i);
                } else if (color[i] != color[curr]) {
                    valid = false;
                    break;
                }
            }
            if(!valid) break;
            for(int i : dadj[curr]){
                if(color[i] == 0){
                    color[i] = color[curr] * -1;
                    visited[i] = true;
                    stack.push(i);
                } else if (color[i] == color[curr]){
                    valid = false;
                    break;
                }
            }
            if(!valid) break;
            curr = stack.pop();
        }
    }
}
