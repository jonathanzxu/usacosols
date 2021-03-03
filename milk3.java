/*
ID: j.jonny1
LANG: JAVA
TASK: milk3
 */
import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class milk3 {
    static int[] capacity = new int[3];
    static int[] buckets = new int[3];
    static boolean[][][] cases;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        capacity[0] = Integer.parseInt(st.nextToken());
        capacity[1] = Integer.parseInt(st.nextToken());
        capacity[2] = Integer.parseInt(st.nextToken());
        buckets[0] = 0;
        buckets[1] = 0;
        buckets[2] = capacity[2];
        cases = new boolean[capacity[0]+1][capacity[1]+1][capacity[2]+1];
        cases[0][0][capacity[2]] = true;
        dfs(0, 0, capacity[2]);
        TreeSet<Integer> out = new TreeSet<Integer>();
        for(int i = 0; i <= capacity[1]; i++)
        {
            for(int j = 0; j <= capacity[2]; j++)
            {
                if(cases[0][i][j]) out.add(j);
            }
        }
        int space = 1;
        for(int i : out)
        {
            writer.print(i);
            if(space < out.size()) writer.print(" ");
            space++;
        }
        writer.println();
        reader.close();
        writer.close();
    }
    public static void dfs(int a, int b, int c)
    {
        int t0, t1, t2;
        cases[a][b][c] = true;
        int[] next;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(i != j)
                {
                    t0 = buckets[0];
                    t1 =  buckets[1];
                    t2 = buckets[2];
                    next = pour(i, j);
                    if(!cases[next[0]][next[1]][next[2]]) {
                        dfs(next[0], next[1], next[2]);
                    }
                    buckets[0] = t0;
                    buckets[1] = t1;
                    buckets[2] = t2;
                }
            }
        }

    }
    // 0-a, 1-b, 2-c
    public static int[] pour(int source, int dest)
    {
        int[] out;
        int temp0 = buckets[0], temp1 = buckets[1], temp2 = buckets[2];
        if(buckets[dest] + buckets[source] <= capacity[dest]){
            buckets[dest] += buckets[source];
            buckets[source] = 0;
        }
        else {
            buckets[source] -= capacity[dest] - buckets[dest];
            buckets[dest] = capacity[dest];
        }
        out = new int[] {buckets[0], buckets[1], buckets[2]};
        //buckets[0] = temp0;
        //buckets[1] = temp1;
        //buckets[2] = temp2;
        return out;
    }
}
