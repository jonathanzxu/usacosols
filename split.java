import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class split {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("split.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        Integer[] idx = new Integer[N];
        Integer[] idy = new Integer[N];
        int[] xsort = new int[N];
        int[] y = new int[N];
        int[] yxsort = new int[N];
        int[] ysort = new int[N];
        int[] x = new int[N];
        int[] xysort = new int[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            xsort[i] = a;
            y[i] = b;
            ysort[i] = b;
            x[i] = a;
            idx[i] = i;
            idy[i] = i;
        }
        Arrays.sort(idx, Comparator.comparingInt(j -> xsort[j]));
        Arrays.sort(xsort);
        Arrays.sort(idy, Comparator.comparingInt(j -> ysort[j]));
        Arrays.sort(ysort);
        for(int i = 0; i < N; i++)
        {
            yxsort[i] = y[idx[i]];
            xysort[i] = x[idy[i]];
        }
        int minarea = Integer.MAX_VALUE;
        int maxx = 0, maxy = 0, minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;
        int totalmaxx = 0, totalmaxy = 0, totalminx = Integer.MAX_VALUE, totalminy = Integer.MAX_VALUE;
        int[] toiareax = new int[N];
        int[] fromiareax = new int[N];
        //x axis line sweep calculate area of rectangle enclosing cows 0 -> i
        for(int i = 0; i < N; i++)
        {
            minx = Math.min(minx, xsort[i]);
            miny = Math.min(miny, yxsort[i]);
            maxx = Math.max(maxx, xsort[i]);
            maxy= Math.max(maxy, yxsort[i]);
            toiareax[i] = (maxx - minx) * (maxy - miny);
        }
        totalminx = Math.min(totalminx, minx);
        totalminy = Math.min(totalminy, miny);
        totalmaxx = Math.max(totalmaxx, maxx);
        totalmaxy = Math.max(totalmaxy, maxy);
        maxx = 0;
        maxy = 0;
        minx = Integer.MAX_VALUE;
        miny = Integer.MAX_VALUE;
        //x axis line sweep from cows N -> i
        for(int i = N-1; i >= 0; i--)
        {
            minx = Math.min(minx, xsort[i]);
            miny = Math.min(miny, yxsort[i]);
            maxx = Math.max(maxx, xsort[i]);
            maxy= Math.max(maxy, yxsort[i]);
            fromiareax[i] = (maxx - minx) * (maxy - miny);
        }
        //calculate min area enclosed
        for(int i = 0; i < N-1; i++)
        {
            minarea = Math.min(minarea, toiareax[i] + fromiareax[i+1]);
        }
        //whole thing but on y axis
        int[] toiareay = new int[N];
        int[] fromiareay = new int[N];
        maxx = 0;
        maxy = 0;
        minx = Integer.MAX_VALUE;
        miny = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            minx = Math.min(minx, xysort[i]);
            miny = Math.min(miny, ysort[i]);
            maxx = Math.max(maxx, xysort[i]);
            maxy = Math.max(maxy, ysort[i]);
            toiareay[i] = (maxx - minx) * (maxy - miny);
        }
        maxx = 0;
        maxy = 0;
        minx = Integer.MAX_VALUE;
        miny = Integer.MAX_VALUE;
        for(int i = N-1; i >= 0; i--)
        {
            minx = Math.min(minx, xysort[i]);
            miny = Math.min(miny, ysort[i]);
            maxx = Math.max(maxx, xysort[i]);
            maxy= Math.max(maxy, ysort[i]);
            fromiareay[i] = (maxx - minx) * (maxy - miny);
        }
        //calculate min area enclosed
        for(int i = 0; i < N-1; i++)
        {
            minarea = Math.min(minarea, toiareay[i] + fromiareay[i+1]);
        }
        writer.println((totalmaxx - totalminx) * (totalmaxy - totalminy) - minarea);
        reader.close();
        writer.close();
    }
}

