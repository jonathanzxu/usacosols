import java.io.*;
import java.util.*;

public class multimoo {
    static ArrayList<Integer> regsizes;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("multimoo.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        StringTokenizer st;
        int[][] matrix = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        int[][] regid  = new int[N][N];
        regsizes = new ArrayList<Integer>();
        //TreeSet<Integer> cows = new TreeSet<Integer>();
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(reader.readLine());
            for(int j = 0; j < N; j++)
            {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                //cows.add(matrix[i][j]);
            }
        }
        //first part, dfs on each connected component. generate second part graph, have list of adjacent
        //identify conncomps with id in order
        Stack<Integer> stack;
        int curri, currj;
        int currregid = 0, regsize;
        int maxsize = 0;
        for(int i = 0; i < N*N; i++)
        {
            if(!visited[i/N][i%N])
            {
                regsize = 1;
                stack = new Stack<Integer>();
                visited[i/N][i%N] = true;
                stack.push(i);
                regid[i/N][i%N] = currregid;
                while(!stack.isEmpty())
                {
                    curri = stack.peek()/N;
                    currj = stack.pop()%N;
                    if(curri > 0)
                    {
                        if(!visited[curri-1][currj] && matrix[curri-1][currj] == matrix[curri][currj])
                        {
                            visited[curri-1][currj] = true;
                            stack.push((curri-1)*N + currj);
                            regsize++;
                            regid[curri-1][currj] = currregid;
                        }
                    }
                    if(curri < N-1)
                    {
                        if(!visited[curri+1][currj] && matrix[curri+1][currj] == matrix[curri][currj])
                        {
                            visited[curri+1][currj] = true;
                            stack.push((curri+1)*N + currj);
                            regsize++;
                            regid[curri+1][currj] = currregid;
                        }
                    }
                    if(currj > 0)
                    {
                        if(!visited[curri][currj-1] && matrix[curri][currj-1] == matrix[curri][currj])
                        {
                            visited[curri][currj-1] = true;
                            stack.push(curri*N + currj-1);
                            regsize++;
                            regid[curri][currj-1] = currregid;
                        }
                    }
                    if(currj < N-1)
                    {
                        if(!visited[curri][currj+1] && matrix[curri][currj+1] == matrix[curri][currj])
                        {
                            visited[curri][currj+1] = true;
                            stack.push(curri*N + currj+1);
                            regsize++;
                            regid[curri][currj+1] = currregid;
                        }
                    }
                }
                regsizes.add(regsize);
                maxsize = Math.max(maxsize, regsize);
                currregid++;
            }
        }
        HashMap<multimooPair, HashMap<Integer, HashSet<Integer>>> graphs = new HashMap<multimooPair, HashMap<Integer, HashSet<Integer>>>();
        /*
        int[] cownums = new int[cows.size()];
        for(int i = 0; i < cownums.length; i++)
        {
            cownums[i] = cows.pollFirst();
        }
        for(int i = 0; i < cownums.length; i++)
        {
            for(int j = i+1; j < cownums.length; j++)
            {
                graphs.put(new multimooPair(cownums[i], cownums[j]), new HashMap<Integer, HashSet<Integer>>());
            }
        }

         */
        for(int i = 0; i < N*N; i++)
        {
            curri = i/N;
            currj = i%N;
            if(curri > 0)
            {
                if(regid[curri-1][currj] != regid[curri][currj])
                {
                    if(!graphs.containsKey(new multimooPair(Math.min(matrix[curri-1][currj], matrix[curri][currj]), Math.max(matrix[curri-1][currj], matrix[curri][currj])))) graphs.put(new multimooPair(Math.min(matrix[curri-1][currj], matrix[curri][currj]), Math.max(matrix[curri-1][currj], matrix[curri][currj])), new HashMap<Integer, HashSet<Integer>>());
                    if(!(graphs.get(new multimooPair(Math.min(matrix[curri-1][currj], matrix[curri][currj]), Math.max(matrix[curri-1][currj], matrix[curri][currj]))).containsKey(regid[curri][currj])))
                    {
                        graphs.get(new multimooPair(Math.min(matrix[curri-1][currj], matrix[curri][currj]), Math.max(matrix[curri-1][currj], matrix[curri][currj]))).put(regid[curri][currj], new HashSet<Integer>());
                    }
                    if(!(graphs.get(new multimooPair(Math.min(matrix[curri-1][currj], matrix[curri][currj]), Math.max(matrix[curri-1][currj], matrix[curri][currj]))).containsKey(regid[curri-1][currj])))
                    {
                        graphs.get(new multimooPair(Math.min(matrix[curri-1][currj], matrix[curri][currj]), Math.max(matrix[curri-1][currj], matrix[curri][currj]))).put(regid[curri-1][currj], new HashSet<Integer>());
                    }
                    graphs.get(new multimooPair(Math.min(matrix[curri-1][currj], matrix[curri][currj]), Math.max(matrix[curri-1][currj], matrix[curri][currj]))).get(regid[curri][currj]).add(regid[curri-1][currj]);
                    graphs.get(new multimooPair(Math.min(matrix[curri-1][currj], matrix[curri][currj]), Math.max(matrix[curri-1][currj], matrix[curri][currj]))).get(regid[curri-1][currj]).add(regid[curri][currj]);
                }
            }
            if(curri < N-1)
            {
                if(regid[curri+1][currj] != regid[curri][currj]) {
                    if(!graphs.containsKey(new multimooPair(Math.min(matrix[curri+1][currj], matrix[curri][currj]), Math.max(matrix[curri+1][currj], matrix[curri][currj])))) graphs.put(new multimooPair(Math.min(matrix[curri+1][currj], matrix[curri][currj]), Math.max(matrix[curri+1][currj], matrix[curri][currj])), new HashMap<Integer, HashSet<Integer>>());
                    if (!(graphs.get(new multimooPair(Math.min(matrix[curri + 1][currj], matrix[curri][currj]), Math.max(matrix[curri + 1][currj], matrix[curri][currj]))).containsKey(regid[curri][currj]))) {
                        graphs.get(new multimooPair(Math.min(matrix[curri + 1][currj], matrix[curri][currj]), Math.max(matrix[curri + 1][currj], matrix[curri][currj]))).put(regid[curri][currj], new HashSet<Integer>());
                    }
                    if (!(graphs.get(new multimooPair(Math.min(matrix[curri + 1][currj], matrix[curri][currj]), Math.max(matrix[curri + 1][currj], matrix[curri][currj]))).containsKey(regid[curri + 1][currj]))) {
                        graphs.get(new multimooPair(Math.min(matrix[curri + 1][currj], matrix[curri][currj]), Math.max(matrix[curri + 1][currj], matrix[curri][currj]))).put(regid[curri + 1][currj], new HashSet<Integer>());
                    }
                    if (regid[curri + 1][currj] != regid[curri][currj]) {
                        graphs.get(new multimooPair(Math.min(matrix[curri + 1][currj], matrix[curri][currj]), Math.max(matrix[curri + 1][currj], matrix[curri][currj]))).get(regid[curri][currj]).add(regid[curri + 1][currj]);
                        graphs.get(new multimooPair(Math.min(matrix[curri + 1][currj], matrix[curri][currj]), Math.max(matrix[curri + 1][currj], matrix[curri][currj]))).get(regid[curri + 1][currj]).add(regid[curri][currj]);
                    }
                }
            }
            if(currj > 0)
            {
                if(regid[curri][currj-1] != regid[curri][currj]) {
                    if(!graphs.containsKey(new multimooPair(Math.min(matrix[curri][currj-1], matrix[curri][currj]), Math.max(matrix[curri][currj-1], matrix[curri][currj])))) graphs.put(new multimooPair(Math.min(matrix[curri][currj-1], matrix[curri][currj]), Math.max(matrix[curri][currj-1], matrix[curri][currj])), new HashMap<Integer, HashSet<Integer>>());
                    if (!(graphs.get(new multimooPair(Math.min(matrix[curri][currj - 1], matrix[curri][currj]), Math.max(matrix[curri][currj - 1], matrix[curri][currj]))).containsKey(regid[curri][currj]))) {
                        graphs.get(new multimooPair(Math.min(matrix[curri][currj - 1], matrix[curri][currj]), Math.max(matrix[curri][currj - 1], matrix[curri][currj]))).put(regid[curri][currj], new HashSet<Integer>());
                    }
                    if (!(graphs.get(new multimooPair(Math.min(matrix[curri][currj - 1], matrix[curri][currj]), Math.max(matrix[curri][currj - 1], matrix[curri][currj]))).containsKey(regid[curri][currj - 1]))) {
                        graphs.get(new multimooPair(Math.min(matrix[curri][currj - 1], matrix[curri][currj]), Math.max(matrix[curri][currj - 1], matrix[curri][currj]))).put(regid[curri][currj - 1], new HashSet<Integer>());
                    }
                    if (regid[curri][currj - 1] != regid[curri][currj]) {
                        graphs.get(new multimooPair(Math.min(matrix[curri][currj - 1], matrix[curri][currj]), Math.max(matrix[curri][currj - 1], matrix[curri][currj]))).get(regid[curri][currj]).add(regid[curri][currj - 1]);
                        graphs.get(new multimooPair(Math.min(matrix[curri][currj - 1], matrix[curri][currj]), Math.max(matrix[curri][currj - 1], matrix[curri][currj]))).get(regid[curri][currj - 1]).add(regid[curri][currj]);
                    }
                }
            }
            if(currj < N-1)
            {
                if(regid[curri][currj+1] != regid[curri][currj]) {
                    if(!graphs.containsKey(new multimooPair(Math.min(matrix[curri][currj+1], matrix[curri][currj]), Math.max(matrix[curri][currj+1], matrix[curri][currj])))) graphs.put(new multimooPair(Math.min(matrix[curri][currj+1], matrix[curri][currj]), Math.max(matrix[curri][currj+1], matrix[curri][currj])), new HashMap<Integer, HashSet<Integer>>());
                    if (!(graphs.get(new multimooPair(Math.min(matrix[curri][currj + 1], matrix[curri][currj]), Math.max(matrix[curri][currj + 1], matrix[curri][currj]))).containsKey(regid[curri][currj]))) {
                        graphs.get(new multimooPair(Math.min(matrix[curri][currj + 1], matrix[curri][currj]), Math.max(matrix[curri][currj + 1], matrix[curri][currj]))).put(regid[curri][currj], new HashSet<Integer>());
                    }
                    if (!(graphs.get(new multimooPair(Math.min(matrix[curri][currj + 1], matrix[curri][currj]), Math.max(matrix[curri][currj + 1], matrix[curri][currj]))).containsKey(regid[curri][currj + 1]))) {
                        graphs.get(new multimooPair(Math.min(matrix[curri][currj + 1], matrix[curri][currj]), Math.max(matrix[curri][currj + 1], matrix[curri][currj]))).put(regid[curri][currj + 1], new HashSet<Integer>());
                    }
                    if (regid[curri][currj + 1] != regid[curri][currj]) {
                        graphs.get(new multimooPair(Math.min(matrix[curri][currj + 1], matrix[curri][currj]), Math.max(matrix[curri][currj + 1], matrix[curri][currj]))).get(regid[curri][currj]).add(regid[curri][currj + 1]);
                        graphs.get(new multimooPair(Math.min(matrix[curri][currj + 1], matrix[curri][currj]), Math.max(matrix[curri][currj + 1], matrix[curri][currj]))).get(regid[curri][currj + 1]).add(regid[curri][currj]);
                    }
                }
            }
        }
        int maxsize2 = 0;
        for(HashMap<Integer, HashSet<Integer>> h : graphs.values())
        {
            maxsize2 = Math.max(maxsize2, dfs(h));
        }
        writer.println(maxsize);
        writer.println(maxsize2);
        reader.close();
        writer.close();
    }
    static int dfs(HashMap<Integer, HashSet<Integer>> adj)
    {
        HashSet<Integer> visited = new HashSet<Integer>();
        Stack<Integer> stack;
        int curr;
        int maxsize = 0;
        int size;
        for(int i : adj.keySet())
        {
            if(!visited.contains(i))
            {
                size = regsizes.get(i);
                visited.add(i);
                stack = new Stack<Integer>();
                stack.push(i);
                while(!stack.isEmpty())
                {
                    curr = stack.pop();
                    for(int j : adj.get(curr))
                    {
                        if(!visited.contains(j))
                        {
                            visited.add(j);
                            stack.push(j);
                            size += regsizes.get(j);
                        }
                    }
                }
                maxsize = Math.max(maxsize, size);
            }
        }
        return maxsize;
    }
}
class multimooPair implements Comparable<multimooPair>
{
    int a, b;
    public multimooPair(int c, int d)
    {
        a = c;
        b = d;
    }
    @Override
    public int compareTo(multimooPair m)
    {
        return a == m.a ? b - m.b : a - m.a;
    }

    @Override
    public boolean equals(Object m)
    {
        return m instanceof multimooPair && compareTo((multimooPair) m) == 0;
    }
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 31 * result + a;
        result = 31 * result + b;
        return result;
    }
}