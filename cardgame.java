import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class cardgame {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
        //Scanner reader = new Scanner(System.in);
        int N = Integer.parseInt(reader.readLine());
        ArrayList<Integer> cards = new ArrayList<Integer>();
        for(int i = 1; i <= 2*N; i++)
        {
            cards.add(i);
        }
        int[] efirsthalf = new int[N/2];
        int[] esecondhalf = new int[N/2];
        //ArrayList<Integer> efirstcards = new ArrayList<Integer>();
        //ArrayList<Integer> esecondcards = new ArrayList<Integer>();
        int card;
        for(int i = 0; i < N/2; i++)
        {
            card = Integer.parseInt(reader.readLine());
            efirsthalf[i] = card;
            cards.remove(Integer.valueOf(card));
        }
        for(int i = 0; i < N/2; i++)
        {
            card = Integer.parseInt(reader.readLine());
            esecondhalf[i] = card;
            cards.remove(Integer.valueOf(card));
        }
/*
        for(int i = 0; i < N/2; i++)
        {
            efirsthalf[i] = efirstcards.get(i);
        }
        for(int i = 0; i < N/2; i++)
        {
            esecondhalf[i] = esecondcards.get(i);
        }

 */
        Arrays.sort(efirsthalf);
        Arrays.sort(esecondhalf);
        int[] firsthalf = new int[N/2];
        int[] secondhalf = new int[N/2];
        for(int i = 0; i < N/2; i++)
        {
            secondhalf[i] = cards.get(i);
        }
        for(int i = 0; i < N/2; i++)
        {
            firsthalf[i] = cards.get(i + N/2);
        }
        int points = 0;
        int cardnum = 0;
        for(int i : efirsthalf)
        {
            while(cardnum < N/2 && firsthalf[cardnum] < i)
            {
                cardnum++;
            }
            if(cardnum >= N/2) break;
            points++;
            cardnum++;
        }
        cardnum = N/2 - 1;
        for(int i = N/2 - 1; i >= 0; i--)
        {
            while(cardnum >= 0 && secondhalf[cardnum] > esecondhalf[i])
            {
                cardnum--;
            }
            if(cardnum < 0) break;
            points++;
            cardnum--;
        }
        writer.println(points);
        reader.close();
        writer.close();
    }
}
