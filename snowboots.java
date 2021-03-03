import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class snowboots {
    public static int boots;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        boots = B;
        st = new StringTokenizer(reader.readLine());
        int[] tiles = new int[N];
        for(int i = 0; i < N; i++)
        {
               tiles[i] = Integer.parseInt(st.nextToken());
        }
        Queue<Boot> queue = new LinkedList<Boot>();
        for(int i = 0; i < B; i++)
        {
            st = new StringTokenizer(reader.readLine());
            queue.add(new Boot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int[] mindiscard = new int[N];
        for(int i = 0; i < N; i++)
        {
            mindiscard[i] = Integer.MAX_VALUE;
        }
        dfs(tiles, 0, queue, new Boot(0, 0), mindiscard);
        writer.println(mindiscard[N-1] - 1);
        reader.close();
        writer.close();
    }
    public static void dfs(int[] tiles, int pos, Queue<Boot> queue, Boot curr, int[] mindiscard)
    {
        while(!queue.isEmpty()) {
            if(curr.depth >= tiles[pos]) {
                for (int i = pos + 1; i <= Math.min(pos + curr.step, tiles.length - 1); i++) {
                    if (curr.depth >= tiles[i]) {
                        if (boots - queue.size() < mindiscard[i]) {
                            mindiscard[i] = boots - queue.size();
                            dfs(tiles, i, queue, curr, mindiscard);
                        }
                    }
                }
            }
            curr = queue.poll();
        }
    }
}
class Boot
{
    int depth, step;
    public Boot(int d, int s)
    {
        depth = d;
        step = s;
    }
}
