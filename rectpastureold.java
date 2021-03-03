import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class rectpastureold {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        pasturecow[] pasture = new pasturecow[N];
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            pasture[i] = new pasturecow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(pasture);
        int sets = 1;
        int a, b;
        //TreeSet<pasturecow> set = new TreeSet<pasturecow>();
        int intsize;
        for(int i = 0; i < N; i++)
        {
            sets += i+1;
            //set.add(pasture[i]);
            for(int j = 1; j < i; j++)
            {
                //set.remove(pasture[j]);
                intsize = i - j + 1;
                b = part(pasture, pasture[j-1], j, i);
                a = intsize - b;

                if(pasture[i].y > pasture[j-1].y)
                {
                    sets += af(intsize) - af(a) - af(b) - (af(intsize-1) - af(a-1) - af(b));
                }
                else sets += af(intsize) - af(a) - af(b) - (af(intsize-1) - af(a) - af(b-1));

                //sets += b;
            }

        }
        System.out.println(sets);
        reader.close();
        //writer.close();
    }
    public static int af(int num)
    {
        return num * (num + 1) / 2;
    }
    public static int part(pasturecow[] arr, pasturecow p, int start, int end)
    {
        int left = 0;
        for(int i = start; i <= end; i++)
        {
            if(arr[i].y < p.y) left++;
        }
        return left;
    }

}
class pasturecow implements Comparable<pasturecow>{
    int x, y;
    public pasturecow(int a, int b)
    {
        x = a;
        y = b;
    }
    @Override
    public int compareTo(pasturecow p)
    {
        return x - p.x;
    }
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof pasturecow)) return false;
        return compareTo((pasturecow) o) == 0;
    }
}
