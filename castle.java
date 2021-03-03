import java.io.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class castle {
    static int[] union;
    static ArrayList<Integer>[] adj;
    static int[] roomsizes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("castle.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        adj = new ArrayList[m*n];
        union = new int[m*n];
        roomsizes = new int[m*n];

        int room;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                adj[i * m + j] = new ArrayList<Integer>();
                room = Integer.parseInt(st.nextToken());
                if (room >= 8) {
                    room -= 8;
                    adj[i * m + j].add((i + 1) * m + j);
                }
                if (room >= 4) {
                    room -= 4;
                    adj[i * m + j].add(i * m + j + 1);
                }
                if (room >= 2) {
                    room -= 2;
                    adj[i * m + j].add((i - 1) * m + j);
                }
                if (room >= 1) {
                    room--;
                    adj[i * m + j].add(i * m + j - 1);
                }
            }
        }
        int group = 1;
        for(int i = 0; i < m*n; i++) {
            if (union[i] < 1) {
                union[i] = group;
                roomsizes[group]++;
                flood(i);
                group++;
            }
        }
        int maxroom1 = 1, maxroom2 = 2;
        for(int i = 1; i <= group; i++)
        {

        }
        reader.close();
        writer.close();
    }

    public static void flood(int i)
    {
        for(int j : adj[i])
        {
            if(union[j] < 1) {
                union[j] = union[i];
                roomsizes[union[j]]++;
                flood(j);
            }
        }
    }

}
