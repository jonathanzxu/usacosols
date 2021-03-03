import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jan2021silver2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader reader = new BufferedReader(new FileReader("template.in"));
        //PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("template.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        //preprocess
        int[] fence = new int[N];
        int[] reversefence = new int[N];
        String s = reader.readLine();
        for(int i = 0; i < N; i++)
        {
            fence[i] = s.charAt(i) - 64;
            reversefence[N-1-i] = fence[i];
        }
        int[] forward = compute(fence);
        int[] reverse = compute(reversefence);
        int a, b;
        for(int i = 0; i < Q; i++)
        {
            st = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(a == 1 && b == N) System.out.println(0);
            else if(a == 1)
            {
                System.out.println(reverse[N - b - 1]);
            }
            else if(b == N)
            {
                System.out.println(forward[a - 2]);
            }
            else {
                System.out.println(forward[a - 2] + reverse[N - b - 1]);
            }
        }
        reader.close();
        //writer.close();
    }
    static int[] compute(int[] arr)
    {
        int[] minsincelast = new int[27];
        int[] strokes = new int[arr.length];
        //strokes[0] = 0;
        int num = 0;
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 1; j < 27; j++)
            {
                minsincelast[j] = Math.min(minsincelast[j], arr[i]);
            }
            if(minsincelast[arr[i]] != arr[i]) num++;
            minsincelast[arr[i]] = arr[i];
            strokes[i] = num;
        }
        return strokes;
    }
}
