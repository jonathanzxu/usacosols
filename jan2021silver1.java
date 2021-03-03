import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class jan2021silver1 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] swap1 = new int[K];
        int[] swap2 = new int[K];
        for(int i = 0; i < K; i++)
        {
            st = new StringTokenizer(reader.readLine());
            swap1[i] = Integer.parseInt(st.nextToken());
            swap2[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<Integer>[] lists = new ArrayList[N+1];
        for(int i = 1; i <= N; i++)
        {
            lists[i] = new ArrayList<Integer>();
            lists[i].add(i);
        }
        /*
        ArrayList<Integer>[] adj = new ArrayList[N+1];
        int curr;
        for(int i = 1; i <= N; i++)
        {
            curr = i;
            for(int j = 0; j < K; j++)
            {
                if(swap1[j] == curr)
                {
                    lists[i].add(swap2[j]);
                    curr = swap2[j];
                }
                else if(swap2[j] == curr)
                {
                    lists[i].add(swap1[j]);
                    curr = swap1[j];
                }
            }
        }
        for(int i = 1; i <= N; i++)
        {
            adj[i] = new ArrayList<Integer>();
        }
        for(int i = 1; i <= N; i++)
        {
            for(int j = 0; j < lists[i].size()-1; j++)
            {
                adj[lists[i].get(j)].add(lists[i].get(j+1));
            }
        }
        Stack<Integer> stack = new Stack<Integer>();
        int[] ans = new int[N+1];
        boolean[] visited;
        int currvert;
        for(int i = 1; i <= N; i++)
        {
            stack = new Stack<Integer>();
            visited = new boolean[N+1];
            ans[i]++;
            stack.push(i);
            visited[i] = true;
            while(!stack.isEmpty())
            {
                currvert = stack.pop();
                for(int j : adj[currvert])
                {
                    if(!visited[j])
                    {
                        ans[i]++;
                        visited[j] = true;
                        stack.push(j);
                    }
                }
            }
        }
        for(int i = 1; i <= N; i++)
        {
            System.out.println(ans[i]);
        }
         */
        /*
        lists[0] = new ArrayList<Integer>();
        for(ArrayList a : lists)
        {
            System.out.println(a.toString());
        }
         */
        /*
        HashSet<Integer>[] sets = new HashSet[N+1];
        for(int i = 1; i <= N; i++)
        {
            sets[i] = new HashSet<Integer>();
            sets[i].addAll(lists[i]);
        }
        LinkedHashSet<Integer>[] paths = new LinkedHashSet[N+1];
        int current;
        for(int i = 1; i <= N; i++)
        {
            paths[i] = new LinkedHashSet<Integer>();
            paths[i].add(i);
            current = lists[i].get(lists[i].size()-1);
            while(!paths[i].contains(current))
            {
                paths[i].add(current);
                current = lists[current].get(lists[current].size()-1);
            }
        }
        paths[0] = new LinkedHashSet<Integer>();
        boolean[] visited = new boolean[N+1];
        for(int i = 1; i <= N; i++)
        {
            for(int j : paths[i])
            {
                if(sets[i].size() == N)
                {
                    break;
                }
                if(visited[j])
                {
                    sets[i].addAll(sets[j]);
                    break;
                }
                sets[i].addAll(sets[j]);
            }
            visited[i] = true;
        }
        for(int i = 1; i <= N; i++)
        {
            System.out.println(sets[i].size());
        }
         */
        /*
        int temp;
        arr = new int[N+1];
        for(int i = 0; i <= N; i++)
        {
            arr[i] = i;
        }
        int[] swap1 = new int[K];
        int[] swap2 = new int[K];
        for(int i = 0; i < K; i++)
        {
            st = new StringTokenizer(reader.readLine());
            swap1[i] = Integer.parseInt(st.nextToken());
            swap2[i] = Integer.parseInt(st.nextToken());
        }
        HashSet<Integer>[] pos = new HashSet[N+1];
        for(int j = 1; j <= N; j++)
        {
            pos[j] = new HashSet<Integer>();
            pos[j].add(j);
        }
        int i = 1;
        pos[swap1[0]].add(swap2[0]);
        pos[swap2[0]].add(swap1[0]);
        temp = arr[swap1[0]];
        arr[swap1[0]] = arr[swap2[0]];
        arr[swap2[0]] = temp;
        while(!back())
        {
            i = i % K;
            pos[arr[swap1[i]]].add(swap2[i]);
            pos[arr[swap2[i]]].add(swap1[i]);
            temp = arr[swap1[i]];
            arr[swap1[i]] = arr[swap2[i]];
            arr[swap2[i]] = temp;
            i++;
        }
        for(int j = 1; j <= N; j++)
        {
            System.out.println(pos[j].size());
        }
        */
        /*
        ArrayList<Integer>[] adj = new ArrayList[N+1];
        for(int i = 1; i <= N; i++)
        {
            adj[i] = new ArrayList<Integer>();
        }
        int a, b;
        for(int i = 0; i < K; i++)
        {
            st = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        ArrayList<ArrayList<Integer>> components = new ArrayList<ArrayList<Integer>>();
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] visited = new boolean[N+1];
        int curr;
        ArrayList<Integer> current;
        for(int i = 1; i <= N; i++)
        {
            if(!visited[i])
            {
                visited[i] = true;
                current = new ArrayList<Integer>();
                current.add(i);
                stack.push(i);
                while(!stack.isEmpty())
                {
                    curr = stack.pop();
                    for(int j : adj[curr])
                    {
                        if(!visited[j])
                        {
                            stack.push(j);
                            visited[j] = true;
                            current.add(i);
                        }
                    }
                }
            }
        }
        int[] ans = new int[N+1];
        for(ArrayList<Integer> arr : components)
        {
            for(int i : arr)
            {
                ans[i] = arr.size();
            }
        }
        for(int i = 1; i <= N; i++)
        {
            System.out.println(ans[i]);
        }
         */
        reader.close();
        //writer.close();
    }
    static boolean back()
    {
        boolean back = true;
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] != i) {
                back = false;
                break;
            }
        }
        return back;
    }
}
