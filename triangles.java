import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class triangles {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        ArrayList<Integer>[] xadj = new ArrayList[20001];
        ArrayList<Integer>[] yadj = new ArrayList[20001];
        for(int i = 0; i < 20001; i++)
        {
            xadj[i] = new ArrayList<Integer>(0);
            yadj[i] = new ArrayList<Integer>(0);
        }
        StringTokenizer st;
        HashSet<Integer> xs = new HashSet<Integer>();
        HashSet<Integer> ys = new HashSet<Integer>();
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken()) + 10000;
            int y = Integer.parseInt(st.nextToken()) + 10000;
            xadj[x].add(y);
            yadj[y].add(x);
            xs.add(x);
            ys.add(y);
        }
        //prefixify
        for(int i : xs)
        {
            Collections.sort(xadj[i]);
            for(int j = 1; j < xadj[i].size(); j++)
            {
                xadj[i].set(j, xadj[j].get(j-1) + xadj[j].get(j));
            }
        }
        for(int i : ys)
        {
            Collections.sort(yadj[i]);
            for(int j = 1; j < yadj[i].size(); j++)
            {
                yadj[i].set(j, yadj[j].get(j-1) + yadj[j].get(j));
            }
        }
        for(int i : xs)
        {

        }
        reader.close();
        writer.close();
    }
}
class trianglespair implements Comparable<trianglespair>
{
    int x, y;
    public trianglespair(int a, int b)
    {
        x=a;
        y=b;
    }
    @Override
    public int compareTo(trianglespair t)
    {
        return x == t.x ? y - t.y : x - t.x;
    }
}