import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class feb2021silver2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] cows = new int[N+1];
        cows[0] = 0;
        for(int i = 1; i <= N; i++)
        {
            cows[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(cows);
        int[] gaps = new int[N];
        gaps[0] = 12 * (cows[1]/12);
        for(int i = 1; i < N; i++)
        {
            gaps[i] = Math.max(0, 12 * (cows[i+1]/12 - cows[i]/12 - 1));
        }
        Arrays.sort(gaps);
        int ans = 12 * (cows[N]/12 + 1);
        for(int i = N-1; i > N-K; i--)
        {
            ans -= gaps[i];
        }
        System.out.println(ans);
        reader.close();
        //writer.close();
    }
}
