/*
ID: j.jonny1
LANG: JAVA
TASK: wormhole
 */
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class wormhole {
    public static coord[] holes;
    public static int[] partner;
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
        //Scanner reader = new Scanner(System.in);
        n = Integer.parseInt(reader.readLine());
        holes = new coord[n+1];
        partner = new int[n+1];
        StringTokenizer st;
        for(int i = 1; i < n+1; i++)
        {
            st = new StringTokenizer(reader.readLine());
            holes[i] = new coord(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), i);
        }
        holes[0] = new coord(-1, -1, 0);
        Arrays.sort(holes);
        writer.println(solve());
        reader.close();
        writer.close();
    }
    public static int solve(){
        int i;
        int total = 0;
        for(i = 1; i <= n; i++)
        {
            if(partner[i] == 0) break;
        }
        if(i > n){
            /*
            for(int m = 1; m <= n; m++)
            {
                System.out.print(m + ":" + partner[m] + "/");
            }
            System.out.println();
            */
            if(iscycle()) return 1;
            return 0;
        }
        for(int j = i+1; j <= n; j++)
        {
            if(partner[j] == 0){
                partner[i] = j;
                partner[j] = i;
                total += solve();
                partner[i] = 0;
                partner[j] = 0;
            }
        }
        return total;

    }
    public static boolean iscycle(){
        //HashSet<Integer> path;
        int node;
        for(int i = 1; i <= n; i++)
        {
            //path = new HashSet<Integer>();
            node = i;
            /*while(!path.contains(node))
            {
                path.add(node);
                path.add(partner[node]);
                node = next(partner[node]);
                if(node == 0 || partner[node] == 0) break;
            }

             */
            for(int j = 0; j < n; j++)
            {
                node = next(partner[node]);
            }
            if(node != 0){
                //System.out.print("cycle");
                return true;
            }
        }
        return false;
    }
    public static int next(int node)
    {
        if(node == 0) return 0;
        int i = 1;
        while(holes[i].id != node) i++;
        for(int j = i+1; j <= n; j++)
        {
            if(holes[j].y == holes[i].y) return holes[j].id;
        }
        return 0;
    }
}

class coord implements Comparable<coord> {
    long x, y;
    int id;
    public coord(long a, long b, int c)
    {
        x = a;
        y = b;
        id = c;
    }
    @Override
    public int compareTo(coord c)
    {
        if(x == c.x)
        {
            if(y - c.y > 0) return 1;
            else if (y - c.y < 0) return -1;
            return 0;
        }
        if(x - c.x > 0) return 1;
        else if (x - c.x < 0) return -1;
        return 0;
    }
}

