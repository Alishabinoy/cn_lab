
public class leakyTime_1 {
    public static void main(String[] args) {
        int bucketSize = 150;
        int outputRate = 40;

        int stored = 0;
        int resend = 0;

        int space, transmitted;
        int time = 0;

        int[] arrivalRate = {100, 110, 20};
        int[] duration = {1, 2, 3};

        for (int i = 0; i < 3; i++) {

            for (int j = 1; j <= duration[i]; j++) {
                time++;

                int incoming = arrivalRate[i] + resend;
                resend = 0;

                // STORE THE INCOMING PACKETS
                space = bucketSize - stored;

                if (incoming <= space) {
                    stored = stored + incoming;
                } else {
                    stored = bucketSize;
                    resend = incoming - space;
                }

                // TRANSMIT THE PACKETS
                if (stored >= outputRate) {
                    transmitted = outputRate;
                } else {
                    transmitted = stored;
                }

                stored = stored - transmitted;

                System.out.println("Time: " + time + "s");
                System.out.println("Incoming packets: " + incoming + "KB");
                System.out.println("Packets transmitted: " + transmitted + "KB");
                System.out.println("Packets in bucket: " + stored + "KB");
                System.out.println("Overflow packets: " + resend + "KB");
                System.out.println();
            }
        }

        // PROCESS THE OVERFLOW PACKETS
        while (resend > 0) {
            time++;

            // STORE THE PACKETS
            space = bucketSize - stored;

            if (resend <= space) {
                stored = stored + resend;
                resend = 0;
            } else {
                stored = bucketSize;
                resend = resend - space;

                System.out.println("Overflow packets remaining: "
                        + resend + "KB");
            }

            // TRANSMIT THE PACKETS
            if (stored >= outputRate) {
                transmitted = outputRate;
            } else {
                transmitted = stored;
            }

            stored = stored - transmitted;

            System.out.println("Time: " + time + "s");
            System.out.println("Resent packets transmitted: "
                    + transmitted + "KB");
            System.out.println("Packets in bucket: " + stored + "KB");
            System.out.println("Overflow remaining: " + resend + "KB");
            System.out.println();
        }

        // TRANSMIT REMAINING PACKETS IN THE BUCKET
        while (stored > 0) {
            time++;

            if (stored >= outputRate) {
                transmitted = outputRate;
            } else {
                transmitted = stored;
            }

            stored = stored - transmitted;

            System.out.println("Time: " + time + "s");
            System.out.println("Bucket packets transmitted: "
                    + transmitted + "KB");
            System.out.println("Packets in bucket: " + stored + "KB");
            System.out.println();
        }

        System.out.println("Bucket completely empty at " + time + "s");
    }
}
