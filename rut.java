import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class rut {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        int[] x = new int[N];
        int[] y = new int[N];
        ArrayList<Integer> east = new ArrayList<Integer>();
        ArrayList<Integer> north = new ArrayList<Integer>();
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            if(st.nextToken().equals("E"))
            {
                east.add(i);
            }
            else
            {
                north.add(i);
            }
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        east.sort(Comparator.comparingInt(i -> y[i]));
        north.sort(Comparator.comparingInt(i -> x[i]));
        int[] union = new int[N];
        for(int i = 0; i < N; i++)
        {
            union[i] = -1;
        }
        boolean[] stopped = new boolean[N];
        for(int i : east)
        {
            for(int j : north)
            {
                if(!stopped[j] && stops(x[i], y[i], x[j], y[j]) > 0)
                {
                    union[j] = i;
                    stopped[j] = true;
                }
                else if(!stopped[j] && stops(x[i], y[i], x[j], y[j]) < 0)
                {
                    union[i] = j;
                    break;
                }
            }
        }
        int[] stops = new int[N];
        for(int i = 0; i < N; i++)
        {
            int j = union[i];
            while(j >= 0)
            {
                stops[j]++;
                j = union[j];
            }
        }
        for(int i : stops)
        {
            System.out.println(i);
        }
        reader.close();
        //writer.close();
    }
    static int stops(int x1, int y1, int x2, int y2)
    {
        if(x2 - x1 < 0 || y1 - y2 < 0) return 0;
        if(x2 - x1 == y1 - y2) return 0;
        if(x2 - x1 > y1 - y2) return -1;
        return 1;
    }
}
