import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class twosum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("twosum.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("twosum.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(reader.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] orig = new int[n];
        for(int i = 0; i < n; i++)
        {
            orig[i] = arr[i];
        }
        Arrays.sort(arr);
        int left = 0, right = n-1;
        while(arr[left] + arr[right] != x)
        {
            if(left >= right)
            {
                writer.println("IMPOSSIBLE");
                break;
            }
            if(arr[left] + arr[right] > x) right--;
            else left++;
        }
        int lindex = -1, rindex = -1;
        for(int i = 0; i < n; i++)
        {
            if(orig[i] == arr[left]) lindex = i;
            else if(orig[i] == arr[right]) rindex = i;
            if(lindex >= 0 && rindex >= 0) break;
        }
        lindex++;
        rindex++;
        if(left < right) writer.println(lindex + " " + rindex);
        reader.close();
        writer.close();
    }
}
