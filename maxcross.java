import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class maxcross {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] broken = new int[N+1];
        for(int i = 0; i < B; i++)
        {
            broken[Integer.parseInt(reader.readLine())] = 1;
        }
        int[] prefix = new int[N+1];
        prefix[0] = 0;
        for(int i = 1; i <= N; i++)
        {
            prefix[i] = prefix[i-1] + broken[i];
        }
        int min = B;
        for(int i = 1; i <= N-K+1; i++)
        {
            min = Math.min(min, prefix[i+K-1] - prefix[i]);
        }
        writer.println(min);
        reader.close();
        writer.close();
    }
}
