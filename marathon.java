import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class marathon {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("marathon.in"));
        PrintWriter writer = new PrintWriter("marathon.out");
        int N, K;
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        checkpoint[] checks = new checkpoint[N];
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            checks[i] = new checkpoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int[][] combinations = new int[K][N];

    }
}

class checkpoint {
    int x;
    int y;
    public checkpoint(int cx, int cy) {
        x = cx;
        y = cy;
    }
}
