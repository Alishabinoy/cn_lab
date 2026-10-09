import java.util.Scanner;
public class java_main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("LEAKY BUCKET");
        int outputrate = 0, bucketsize = 0, time = 0;
        int s = 0, r = 0;
        System.out.print("Enter Bucketsize: ");
        bucketsize = sc.nextInt();
        System.out.print("Enter OutputRate: ");
        outputrate = sc.nextInt();
        int duration[] = {1, 2, 3};
        int arrival[] = {100, 110, 20};
        for (int i = 0; i < 3; i++) {
            System.out.println("BLOCK " + (i + 1) + "\n");
            for (int j = 0; j < duration[i]; j++) {
                time++;
                int incoming = arrival[i];
                int space = bucketsize - s;
                // Store excess packets in r instead of discarding
                if (space < incoming) {
                    r += incoming - space;
                    s = bucketsize;
                } else {
                    s += incoming;
                }
                // Transmit packets from the bucket
                int transmitted = Math.min(s, outputrate);
                s -= transmitted;
                // Move waiting packets into the available bucket space
                space = bucketsize - s;
                int accepted = Math.min(space, r);
                s += accepted;
                r -= accepted;
                System.out.println("Time: " + time + "s");
                System.out.println("Transmitted: " + transmitted + "KB");
                System.out.println("Remaining: " + s + "KB");
                System.out.println("Retransmission pending: " + r + "KB");
            }
        }
        // Continue until all packets have been transmitted
        while (s > 0 || r > 0) {
            time++;
            // Move pending packets into the bucket
            int space = bucketsize - s;
            int accepted = Math.min(space, r);
            s += accepted;
            r -= accepted;
            // Transmit packets
            int transmitted = Math.min(s, outputrate);
            s -= transmitted;
            System.out.println("Time: " + time + "s");
            System.out.println("Transmitted: " + transmitted + "KB");
            System.out.println("Remaining: " + s + "KB");
            System.out.println("Retransmission pending: " + r + "KB");
        }
        System.out.println("All packets transmitted at " + time + "s");
        sc.close();
    }
}