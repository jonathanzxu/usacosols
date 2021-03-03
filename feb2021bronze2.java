import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class feb2021bronze2 {
    static HashSet<hashPair2> cows;
    static boolean[][] grid;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        cows = new HashSet<hashPair2>();
        grid = new boolean[1001][1001];
        StringTokenizer st;
        int comforts = 0;
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cows.add(new hashPair2(a, b));
            grid[a][b] = true;
            /*
            for(hashPair2 h : cows)
            {
                if(neighbors(h.a, h.b) == 3) comforts++;
            }
            System.out.println(comforts);
            /*
            if(neighbors(new hashPair2(a-1, b)) == 3) comforts++;
            if(neighbors(new hashPair2(a-1, b)) == 4) comforts--;
            if(neighbors(new hashPair2(a+1, b)) == 3) comforts++;
            if(neighbors(new hashPair2(a+1, b)) == 4) comforts--;
            if(neighbors(new hashPair2(a, b-1)) == 3) comforts++;
            if(neighbors(new hashPair2(a, b-1)) == 4) comforts--;
            if(neighbors(new hashPair2(a, b+1)) == 3) comforts++;
            if(neighbors(new hashPair2(a, b+1)) == 4) comforts--;
            if(neighbors(new hashPair2(a, b)) == 3) comforts++;

            */
            if(neighbors(a-1, b) == 3) comforts++;
            if(neighbors(a-1, b) == 4) comforts--;
            if(neighbors(a+1, b) == 3) comforts++;
            if(neighbors(a+1, b) == 4) comforts--;
            if(neighbors(a, b-1) == 3) comforts++;
            if(neighbors(a, b-1) == 4) comforts--;
            if(neighbors(a, b+1) == 3) comforts++;
            if(neighbors(a, b+1) == 4) comforts--;
            if(neighbors(a, b) == 3) comforts++;
            System.out.println(comforts);

        }
        reader.close();
        //writer.close();
    }
    /*
    static int neighbors(hashPair2 pair)
    {
        int count = 0;
        if(cows.contains(new hashPair2(pair.a-1, pair.b))) count++;
        if(cows.contains(new hashPair2(pair.a+1, pair.b))) count++;
        if(cows.contains(new hashPair2(pair.a, pair.b-1))) count++;
        if(cows.contains(new hashPair2(pair.a, pair.b+1))) count++;
        return count;
    }

     */
    static int neighbors(int a, int b)
    {
        int count = 0;
        if(a >= 0 && a <= 1000 && b >= 0 && b <= 1000 && !grid[a][b]) return 0;
        if(a > 0 && b >= 0 && grid[a-1][b]) count++;
        if(a < 1000 && b >= 0 && grid[a+1][b]) count++;
        if(b > 0 && a >= 0 && grid[a][b-1]) count++;
        if(b < 1000 && a >= 0 && grid[a][b+1]) count++;
        return count;
    }
}
class hashPair2 implements Comparable<hashPair2>
{
    int a, b;
    public hashPair2(int c, int d)
    {
        a = c;
        b = d;
    }
    @Override
    public int compareTo(hashPair2 m)
    {
        return a == m.a ? b - m.b : a - m.a;
    }

    @Override
    public boolean equals(Object m)
    {
        return m instanceof hashPair2 && compareTo((hashPair2) m) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 31 * result + a;
        result = 31 * result + b;
        return result;
    }
}