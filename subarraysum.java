import java.io.*;
import java.util.StringTokenizer;

public class subarraysum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("subarraysum.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("subarraysum.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(reader.readLine());
        for(int i = 0; i<n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0, right = 0;
        int sum = arr[0];
        int valid = 0;
        while(left < n && right < n-1)
        {
            if(sum + arr[right+1] < x)
            {
                right++;
                sum += arr[right];
            }
            else
            {
                if(sum + arr[right+1] == x)
                {
                    right++;
                    sum += arr[right];
                    valid++;
                }
                sum -= arr[left];
                left++;
                if(right < left)
                {
                    right = left;
                    sum = arr[right];
                }
            }
        }
        if(arr[n-1] == x) valid++;
        writer.println(valid);
        reader.close();
        writer.close();
    }
}
