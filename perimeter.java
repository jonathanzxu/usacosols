import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;

public class perimeter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        boolean[][] ic = new boolean[N][N];
        String line;
        for(int i = 0; i < N; i++)
        {
            line = reader.readLine();
            for(int j = 0; j < N; j++)
            {
                ic[i][j] = line.charAt(j) == '#';
            }
        }
        boolean[][] visited = new boolean[N][N];
        TreeSet<blob> blobs = new TreeSet<blob>();
        int area, peri;
        Stack<pair> stack;
        pair p;
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(ic[i][j] && !visited[i][j])
                {
                    stack = new Stack<pair>();
                    area = 1;
                    peri = 0;
                    stack.push(new pair(i, j));
                    visited[i][j] = true;
                    while(!stack.isEmpty())
                    {
                        p = stack.pop();
                        peri += 4;
                        if(p.x > 0 && ic[p.x-1][p.y])
                        {
                            if(!visited[p.x-1][p.y]) {
                                stack.push(new pair(p.x - 1, p.y));
                                visited[p.x - 1][p.y] = true;
                                area++;
                            }
                            peri--;
                        }
                        if(p.x < N-1 && ic[p.x+1][p.y])
                        {
                            if(!visited[p.x+1][p.y]) {
                                stack.push(new pair(p.x + 1, p.y));
                                visited[p.x + 1][p.y] = true;
                                area++;
                            }
                            peri--;
                        }
                        if(p.y > 0 && ic[p.x][p.y-1])
                        {
                            if(!visited[p.x][p.y-1]) {
                                stack.push(new pair(p.x, p.y - 1));
                                visited[p.x][p.y - 1] = true;
                                area++;
                            }
                            peri--;
                        }
                        if(p.y < N-1 && ic[p.x][p.y+1])
                        {
                            if(!visited[p.x][p.y+1]) {
                                stack.push(new pair(p.x, p.y + 1));
                                visited[p.x][p.y + 1] = true;
                                area++;
                            }
                            peri--;
                        }
                    }
                    blobs.add(new blob(area, peri));
                }
            }
        }
        writer.println(blobs.last().area + " " + blobs.last().peri);
        reader.close();
        writer.close();
    }
}
class blob implements Comparable<blob>
{
    int area, peri;
    public blob(int a, int p)
    {
        area = a;
        peri = p;
    }
    @Override
    public int compareTo(blob b)
    {
        if(area == b.area)
        {
            return b.peri - peri;
        }
        return area - b.area;
    }
}
class pair
{
    int x, y;
    public pair(int a, int b)
    {
        x=a;
        y=b;
    }
}