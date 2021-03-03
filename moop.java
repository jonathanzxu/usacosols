import java.io.*;
import java.util.StringTokenizer;

public class moop {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("moop.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        int[] x = new int[N];
        int[] y = new int[N];
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        int components = 0;
        boolean visited[] = new boolean[N];
        for(int i = 0; i < N; i++)
        {
            if(!visited[i])
            {
                components++;
                for(int j = 0; j < N; j++)
                {
                    if((x[i] >= x[j] && y[i] >= y[j]) || (x[i] <= x[j] && y[i] <= y[j])) {
                        visited[j] = true;
                    }
                }
            }
        }
        writer.println(components);
        reader.close();
        writer.close();
    }

}
class Particle
{
    int x, y;
    public Particle(int a, int b)
    {
        x = a;
        y = b;
    }
}