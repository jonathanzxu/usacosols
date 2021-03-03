import java.io.*;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class mooyomooyo {
    static int[][] grid;
    static int N;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        grid = new int[N][10];
        String line;
        for(int i = 0; i < N; i++)
        {
            line = reader.readLine();
            for(int j = 0; j < 10; j++)
            {
                grid[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }
        while(reduce())
        {}
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                writer.print(grid[i][j]);
            }
            writer.println();
        }
        reader.close();
        writer.close();
    }
    static boolean reduce()
    {
        boolean changed = false;
        boolean[][] visited = new boolean[N][10];
        Stack<mooyopair> stack = new Stack<mooyopair>();
        HashSet<mooyopair> component;
        mooyopair curr;
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                if(!visited[i][j] && grid[i][j] != 0)
                {
                    component = new HashSet<mooyopair>();
                    component.add(new mooyopair(i, j));
                    stack.push(new mooyopair(i, j));
                    visited[i][j] = true;
                    while(!stack.isEmpty())
                    {
                        curr = stack.pop();
                        if(curr.y > 0 && !visited[curr.y-1][curr.x] && grid[curr.y-1][curr.x] == grid[curr.y][curr.x])
                        {
                            stack.push(new mooyopair(curr.y-1, curr.x));
                            visited[curr.y-1][curr.x] = true;
                            component.add(new mooyopair(curr.y-1, curr.x));
                        }
                        if(curr.y < N-1 && !visited[curr.y+1][curr.x] && grid[curr.y+1][curr.x] == grid[curr.y][curr.x])
                        {
                            stack.push(new mooyopair(curr.y+1, curr.x));
                            visited[curr.y+1][curr.x] = true;
                            component.add(new mooyopair(curr.y+1, curr.x));
                        }
                        if(curr.x > 0 && !visited[curr.y][curr.x-1] && grid[curr.y][curr.x-1] == grid[curr.y][curr.x])
                        {
                            stack.push(new mooyopair(curr.y, curr.x-1));
                            visited[curr.y][curr.x-1] = true;
                            component.add(new mooyopair(curr.y, curr.x-1));
                        }
                        if(curr.x < 9 && !visited[curr.y][curr.x+1] && grid[curr.y][curr.x+1] == grid[curr.y][curr.x])
                        {
                            stack.push(new mooyopair(curr.y, curr.x+1));
                            visited[curr.y][curr.x+1] = true;
                            component.add(new mooyopair(curr.y, curr.x+1));
                        }
                    }
                    if(component.size() >= K)
                    {
                        changed = true;
                        for(mooyopair m : component)
                        {
                            grid[m.y][m.x] = 0;
                        }
                    }
                }
            }
        }
        //start gravity calc
        int pos;
        for(int i = 0; i < 10; i++)
        {
            pos = N-2;
            for(int y = N-1; y > 0; y--)
            {
                if(grid[y][i] == 0)
                {
                    pos = Math.min(pos, y-1);
                    while(pos >= 0 && grid[pos][i] == 0) pos--;
                    if(pos < 0) break;
                    grid[y][i] = grid[pos][i];
                    grid[pos][i] = 0;
                }
            }
        }
        return changed;
    }
}
class mooyopair implements Comparable<mooyopair>
{
    int x, y;
    public mooyopair(int a, int b)
    {
        y = a;
        x = b;
    }
    @Override
    public int compareTo(mooyopair m)
    {
        return x == m.x ? y - m.y : x - m.x;
    }
}
