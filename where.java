import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class where {
    static char[][] pic;
    static int N;
    static HashSet<wherecoords> pcls;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("where.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));
        //Scanner reader = new Scanner(System.in);
        N = Integer.parseInt(reader.readLine());
        pcls = new HashSet<wherecoords>();
        pic = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = reader.readLine();
            for (int j = 0; j < N; j++) {
                pic[i][j] = str.charAt(j);
            }
        }
        //int total = 0;
        for (int a = 0; a < N; a++) {
            for (int b = a; b < N; b++) {
                for (int c = 0; c < N; c++) {
                    for (int d = c; d < N; d++) {
                        flood(a, c, b, d);
                    }
                }
            }
        }
        writer.println(pcls.size());
        reader.close();
        writer.close();
    }
    static boolean flood(int a, int b, int c, int d){
        boolean[][] visited = new boolean[N][N];
        int color1 = 0;
        char c1 = '0';
        int color2 = 0;
        char c2 = '0';
        Stack<Integer> stack = new Stack<Integer>();
        int currx, curry;
        for(int i = a; i <= c; i++)
        {
            for(int j = b; j <= d; j++)
            {
                if(!visited[i][j])
                {
                    if(c1 == '0') {
                        c1 = pic[i][j];
                        color1++;
                    }
                    else if(c2 == '0') {
                        c2 = pic[i][j];
                        color2++;
                    }
                    else if(c1 == pic[i][j]) color1++;
                    else if(c2 == pic[i][j]) color2++;
                    else return false;
                    if(color1 > 1 && color2 > 1) return false;
                    visited[i][j] = true;
                    stack.push(i*100+j);
                    while(!stack.isEmpty())
                    {
                        curry = stack.peek()/100;
                        currx = stack.pop()%100;
                        if(curry > a && pic[curry-1][currx] == pic[curry][currx] && !visited[curry-1][currx])
                        {
                            stack.push((curry-1)*100 + currx);
                            visited[curry-1][currx] = true;
                        }
                        if(curry < b && pic[curry+1][currx] == pic[curry][currx] && !visited[curry+1][currx])
                        {
                            stack.push((curry+1)*100 + currx);
                            visited[curry+1][currx] = true;
                        }
                        if(currx > c && pic[curry][currx-1] == pic[curry][currx] && !visited[curry][currx-1])
                        {
                            stack.push(curry*100 + currx-1);
                            visited[curry][currx-1] = true;
                        }
                        if(currx < d && pic[curry][currx+1] == pic[curry][currx] && !visited[curry][currx+1])
                        {
                            stack.push(curry*100 + currx+1);
                            visited[curry][currx+1] = true;
                        }
                    }
                }
            }
        }
        if(c2 == '0') return false;
        boolean add = true;
        if((color1 == 1 && color2 >= 2) || (color1 >= 2 && color2 == 1)) {
            HashSet<wherecoords> remove = new HashSet<wherecoords>();
            Iterator iter = pcls.iterator();
            while(iter.hasNext())
            {
                wherecoords w = (wherecoords) iter.next();
                if(contains(a,b,c,d,w.x1,w.y1,w.x2,w.y2))
                {
                    remove.add(w);
                    //pcls.add(new wherecoords(a,b,c,d));
                }
                if(contains(w.x1,w.y1,w.x2,w.y2,a,b,c,d))
                {
                    return true;
                }
            }
            pcls.add(new wherecoords(a,b,c,d));
            pcls.removeAll(remove);
        }
        return true;
    }
    static boolean contains(int a1, int b1, int c1, int d1, int a2, int b2, int c2, int d2)
    {
        return  a2 >= a1 && a2 <= c1 && b2 >= b1 && b2 <= d1 && c2 >= a1 && c2 <= c1 && d2 >= b1 && d2 <= d1;
    }
}
class wherecoords implements Comparable<wherecoords> {
    int x1, y1, x2, y2;
    public wherecoords(int a, int b, int c, int d)
    {
        x1=a;
        y1=b;
        x2=c;
        y2=d;
    }
    @Override
    public int compareTo(wherecoords w)
    {
        return x1 == w.x1 ? (x2 == w.x2 ? (y1 == w.y1 ? y2 - w.y2 : y1 - w.y1) : x2 - w.x2) : x1 - w.x1;
    }
}