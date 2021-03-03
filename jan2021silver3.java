import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jan2021silver3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        int[][] beauty = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            for(int j = 0; j < N; j++)
            {
                beauty[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int odd, even;
        int total = 0;
        for(int i = 0; i < N; i++)
        {
            odd = 0;
            even = 0;
            //odd
            for(int j = 0; j < N; j+=2)
            {
                odd += beauty[i][j];
            }
            //even
            for(int j = 1; j < N; j+=2)
            {
                even += beauty[i][j];
            }
            total += Math.max(odd, even);
        }
        int total2 = 0;
        for(int j = 0; j < N; j++)
        {
            odd = 0;
            even = 0;
            //odd
            for(int i= 0; i < N; i+=2)
            {
                odd += beauty[i][j];
            }
            //even
            for(int i = 1; i < N; i+=2)
            {
                even += beauty[i][j];
            }
            total2 += Math.max(odd, even);
        }
        System.out.println(Math.max(total, total2));
        reader.close();
        //writer.close();
    }
}
