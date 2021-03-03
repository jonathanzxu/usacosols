/*
ID: j.jonny1
LANG: JAVA
TASK: ariprog
 */
import java.io.*;
import java.util.HashSet;
import java.util.TreeSet;

public class ariprog {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        //Scanner reader = new Scanner(System.in);
        HashSet<Integer> bisquares = new HashSet<Integer>();
        TreeSet<Pair> solutions = new TreeSet<Pair>();
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        for(int i = 0; i <= m; i++)
        {
            for(int j = 0; j <= m; j++)
            {
                bisquares.add(i*i + j*j);
            }
        }
        boolean good;
        for(int a = 0; a <= m*m*2 - n + 1; a++)
        {
            for(int b = 1; b <= (m*m*2 - a)/(n-1); b++)
            {
                good = true;
                for(int i = 0; i < n; i++)
                {
                    if(!bisquares.contains(a + b*i)) good = false;
                }
                if(good) solutions.add(new Pair(a, b));
            }

        }
        for(Pair p : solutions)
        {
            writer.println(p.a + " " + p.b);
        }
        if(solutions.isEmpty()) writer.println("NONE");
        reader.close();
        writer.close();
    }

}

class Pair implements Comparable<Pair>
{
    int a;
    int b;
    public Pair(int x, int y)
    {
        a = x;
        b = y;
    }
    @Override
    public int compareTo(Pair p)
    {
        if(b == p.b)
        {
            return a - p.a;
        }
        return b - p.b;
    }
}
