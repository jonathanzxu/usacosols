import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class socdist {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Interval[] intervals = new Interval[M];
        for(int i = 0; i < M; i++)
        {
            st = new StringTokenizer(reader.readLine());
            intervals[i] = new Interval(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(intervals);
        long lo = 1, hi = (long) 1e18;
        while(lo < hi)
        {
            long mid = (lo+hi+1)/2;
            if(check(intervals, N, mid))
            {
                lo = mid;
            }
            else hi = mid-1;
        }
        writer.println(lo);
        reader.close();
        writer.close();
    }
    public static boolean check(Interval[] intervals, long N, long D)
    {
        long next = intervals[0].start;
        int intnum = 0;
        for(int i = 1; i < N; i++)
        {
            if(next + D > intervals[intnum].end)
            {
                intnum = nextInterval(intervals, next + D, intnum);
                if(intnum < 0) return false;
                next = Math.max(intervals[intnum].start, next + D);
            }
            else next += D;
        }
        return true;
    }
    public static int nextInterval(Interval[] intervals, long location, int intnum)
    {
        for(int i = intnum; i < intervals.length; i++)
        {
            if(intervals[i].end >= location) return i;
        }
        return -1;
    }
}

class Interval implements Comparable<Interval>
{
    long start, end;
    public Interval(long s, long e)
    {
        start = s;
        end = e;
    }
    @Override
    public int compareTo(Interval i)
    {
        if(start - i.start > 0) return 1;
        else if(start - i.start < 0) return -1;
        return 0;
    }
}
