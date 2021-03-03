import java.io.*;
import java.util.*;
import java.lang.*;
public class socdistold {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter writer = new PrintWriter("socdist.out");
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        //StringTokenizer st = new StringTokenizer(reader.nextLine());
        int cows = Integer.parseInt(st.nextToken());
        int segs = Integer.parseInt(st.nextToken());
        ArrayList<Segment> map = new ArrayList<Segment>();
        for(int i = 0; i < segs; i++)
        {
            //st = new StringTokenizer(reader.nextLine());
            st = new StringTokenizer(reader.readLine());
            map.add(new Segment(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        long lower = 1;
        long higher = (int) (1e18 / (cows - 1));
        long mid;
        while(lower < higher)
        {
            mid = (lower + higher + 1) / 2;
            if(valid(mid, cows, map))
            {
                lower = mid;
            }
            else
            {
                higher = mid - 1;
            }
        }
        writer.println(lower);
        //System.out.println(lower);
        reader.close();
        writer.close();
    }
    public static boolean valid(long d, int c, ArrayList<Segment> map)
    {
        TreeMap<Segment, Integer> comb = new TreeMap<>();
        for(Segment s : map)
        {
            comb.put(s, (int) ((s.end - s.start)/d + 1));
        }
        while(comb.size() > 1)
        {
            int fs = comb.firstEntry().getKey().start;
            //int fe = comb.firstEntry().getKey().end;
            int fv = comb.firstEntry().getValue();
            comb.remove(comb.firstKey());
            //int ss = comb.firstEntry().getKey().start;
            int se = comb.firstEntry().getKey().end;
            int sv = comb.firstEntry().getValue();
            comb.remove(comb.firstKey());
            comb.put(new Segment(fs, se), Math.min(fv + sv, (int) ((se - fs)/d + 1)));

        }
        return comb.firstEntry().getValue() >= c;

    }
}

class Segment implements Comparable<Segment>
{
    int start, end;
    @Override
    public int compareTo(Segment s)
    {
        return end - s.end;
    }
    public Segment(int s, int e)
    {
        start = s;
        end = e;
    }
}