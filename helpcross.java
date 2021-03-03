import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class helpcross {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] chickens = new int[c];
        for(int i = 0; i < c; i++)
        {
            chickens[i] = Integer.parseInt(reader.readLine());
        }
        ArrayList<CowCross> cows = new ArrayList<CowCross>(n);
        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(reader.readLine());
            cows.add(new CowCross(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Arrays.sort(chickens);
        Collections.sort(cows);
        //int cow = 0;
        int num = 0;
        for(int i = 0; i < c; i++)
        {
            for(int j = 0; j < cows.size(); j++)
            {
                if(cows.get(j).start <= chickens[i] && cows.get(j).end >= chickens[i])
                {
                    num++;
                    cows.remove(j);
                    break;
                }
            }
        }
        writer.println(num);
        reader.close();
        writer.close();
    }
}
class CowCross implements Comparable<CowCross>
{
    int start, end;
    public CowCross(int s, int e)
    {
        start = s;
        end = e;
    }
    @Override
    public int compareTo(CowCross c)
    {
        return end - c.end;
    }
}