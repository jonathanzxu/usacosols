import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class jan2021silver1sol {
    public static void main(String[] args) throws IOException {
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adj = new ArrayList[N+1];
        HashSet<Integer>[] possible = new HashSet[N+1];
        for(int i = 0; i < N+1; i++)
        {
            adj[i] = new ArrayList<Integer>();
            possible[i] = new HashSet<Integer>();
            possible[i].add(i);
        }
        int[] cowid = new int[N+1];
        for(int i = 0; i < N+1; i++)
        {
            cowid[i] = i;
        }
        int a, b, temp;
        for(int i = 0; i < K; i++)
        {
            st = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            possible[cowid[a]].add(b);
            possible[cowid[b]].add(a);
            temp = cowid[a];
            cowid[a] = cowid[b];
            cowid[b] = temp;
        }
        for(int i = 1; i < N+1; i++)
        {
            adj[cowid[i]].add(i);
        }
        ArrayList<HashSet<Integer>> disjoints = new ArrayList<HashSet<Integer>>();
        int[] ans = new int[N+1];
        boolean[] visited = new boolean[N+1];
        Stack<Integer> stack = new Stack<Integer>();
        HashSet<Integer> curr;
        int currvert;
        for(int i = 1; i <= N; i++)
        {
            if(!visited[i])
            {
                curr = new HashSet<Integer>();
                curr.add(i);
                visited[i] = true;
                stack.push(i);
                while(!stack.isEmpty())
                {
                    currvert = stack.pop();
                    if(!visited[adj[currvert].get(0)])
                    {
                        visited[adj[currvert].get(0)] = true;
                        stack.push(adj[currvert].get(0));
                        curr.add(adj[currvert].get(0));
                    }
                }
                disjoints.add(curr);
            }
        }
        HashSet<Integer> union;
        for(HashSet<Integer> h : disjoints)
        {
            union = new HashSet<Integer>();
            for(int i : h)
            {
                union.addAll(possible[i]);
            }
            for(int i : h)
            {
                ans[i] = union.size();
            }
        }
        for(int i = 1; i <= N; i++)
        {
            System.out.println(ans[i]);
        }
        reader.close();
        //writer.close();

         */
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        int[] cows = new int[n + 1];
        List<Integer>[] viewed = new List[n + 1];
        for (int j = 1; j <= n; j++) {
            cows[j] = j;
            viewed[j] = new ArrayList<>();
            viewed[j].add(j);
        }
        for (long t = 1; t <= k; t++) {
            tokenizer = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = cows[a];
            int d = cows[b];
            cows[a] = d;
            cows[b] = c;
            viewed[cows[a]].add(a);
            viewed[cows[b]].add(b);
        }
        int[] answer = new int[n + 1];
        for (int r = 1; r <= n; r++) {
            if (cows[r] != 0) {
                List<Integer> cycle = new ArrayList<>();
                int j = r;
                while (cows[j] != 0) {
                    cycle.add(j);
                    j = cows[j];
                    cows[cycle.get(cycle.size() - 1)] = 0;
                }
                Set<Integer> viewedHere = new HashSet<>();
                for (int cow : cycle) {
                    viewedHere.addAll(viewed[cow]);
                }
                for (int cow : cycle) {
                    answer[cow] = viewedHere.size();
                }
            }
        }
        StringBuilder out = new StringBuilder();
        for (int j = 1; j <= n; j++) {
            out.append(answer[j]).append('\n');
        }
        System.out.print(out);
    }

}
