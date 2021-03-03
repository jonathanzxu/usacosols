import java.io.*;
import java.util.StringTokenizer;

public class paintbarn {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] prefix = new int[1001][1001];
        int a, b, c, d;
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            prefix[a][b]++;
            prefix[a][d]--;
            prefix[c][b]--;
            prefix[c][d]++;
        }
        int ans = 0;
        for(int i = 0; i < 1001; i++)
        {
            for(int j = 0; j < 1001; j++)
            {
                if(i > 0) prefix[i][j] += prefix[i-1][j];
                if(j > 0) prefix[i][j] += prefix[i][j-1];
                if(i > 0 && j > 0) prefix[i][j] -= prefix[i-1][j-1];
                if(prefix[i][j] == K) ans++;
            }
        }
        writer.println(ans);
        reader.close();
        writer.close();
    }
}
