import java.io.*;
import java.util.*;

public class rental {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader("rental.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
        //Scanner reader = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[] cows = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(reader.readLine());
        }
        store[] stores = new store[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            stores[i] = new store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Integer[] renters = new Integer[R];
        for (int i = 0; i < R; i++)
        {
            renters[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(stores);
        Arrays.sort(cows);
        Arrays.sort(renters, Comparator.reverseOrder());
        //renters prefix
        long[] rentprefix = new long[N];
        rentprefix[0] = renters[0];
        for(int i = 1; i < Math.min(R, N); i++)
        {
            rentprefix[i] = rentprefix[i-1] + renters[i];
        }
        for(int i = R; i < N; i++)
        {
            rentprefix[i] = rentprefix[i-1];
        }
        //store prefix
        long[] storeprefix = new long[N+1];
        long total;
        int j = 0;
        int gallons = 0;
        int remainder = -1;
        for(int i = N-1; i >= 0; i--)
        {
            gallons = cows[i];
            total = 0;
            while(gallons >= stores[j].quantity)
            {
                total += stores[j].quantity * stores[j].price;
                gallons -= stores[j].quantity;
                j++;
                if(j >= M) break;
            }
            if(j >= M) {
                storeprefix[i] = storeprefix[i+1] + total;
                remainder = i-1;
                break;
            }
            stores[j].quantity -= gallons;
            total += gallons * stores[j].price;
            storeprefix[i] = storeprefix[i+1] + total;
        }
        for(int i = remainder; i >= 0; i--)
        {
            storeprefix[i] = storeprefix[i+1];
        }
        long max = 0;
        for(int i = 0; i < N-1; i++)
        {
            max = Math.max(max, rentprefix[i] + storeprefix[i+1]);
        }
        max = Math.max(max, storeprefix[0]);
        max = Math.max(max, rentprefix[N-1]);
        writer.println(max);
        reader.close();
        writer.close();
    }
}
class store implements Comparable<store>
{
    long quantity, price;
    public store(long q, long p)
    {
        quantity = q;
        price = p;
    }
    @Override
    public int compareTo(store s)
    {
        return (int) (s.price - price);
    }
}