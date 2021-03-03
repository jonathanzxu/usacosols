import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class rutold {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        ArrayList<East> easts = new ArrayList<East>();
        ArrayList<North> norths = new ArrayList<North>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            if (st.nextToken().equals("E")) {
                easts.add(new East(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
            }
            else{
                norths.add(new North(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
            }
        }
        East[] east = new East[easts.size()];
        east = easts.toArray(east);
        North[] north = new North[norths.size()];
        north = norths.toArray(north);
        Arrays.sort(east);
        Arrays.sort(north);
        int[] union = new int[N];
        for(int i = 0; i < N; i++)
        {
            union[i] = -1;
        }
        int[][] blame = new int[N][N];
        for(int i = 0; i < easts.size(); i++)
        {
            for(int j = 0; j < norths.size(); j++)
            {
                if(0 < (north[j].x - east[i].x) && (north[j].x - east[i].x) < (east[i].y - north[j].y))
                {
                    union[j] = i;
                }
                if(0 < (east[i].y - north[j].y) && (east[i].y - north[j].y) < (north[j].x - east[i].x))
                {
                    union[i] = j;
                }
            }
        }
        /*
        int[] totalblame = new int[N];
        for(int i = 0; i < N; i++)
        {
            int a = i;
            while(a >= 0)
            {
                totalblame[union[a]]++;
                a = union[a];
            }
        }
        for(int i : totalblame)
        {
            System.out.println(i);
        }
        */

        reader.close();
        //writer.close();
    }
    public static int blame(int[][] blame, int x)
    {
        int sum = 0;
        for(int i = 0; i < blame[0].length; i++)
        {
            if(blame[x][i] == 1)
            {
                sum++;
                sum += blame(blame, i);
            }
        }
        return sum;
    }
}
class East implements Comparable<East>
{
    int x, y, id;
    public East(int a, int b, int c)
    {
        x = a;
        y = b;
        id = c;
    }
    @Override
    public int compareTo(East e)
    {
        return y - e.y;
    }
}
class North implements Comparable<North>
{
    int x, y, id;
    public North(int a, int b, int c)
    {
        x = a;
        y = b;
        id = c;
    }
    @Override
    public int compareTo(North e)
    {
        return x - e.x;
    }
}

