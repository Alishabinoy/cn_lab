public class leakyTime {
    public static void main(String[] args) {
        int bucketSize = 150;
        int outputRate  = 40;

        int stored = 0;
        int discarded = 0;

        int space, transmitted;

        int time=0;

        int[] arrivalRate = {100,110,20};
        int[] duration = {1,2,3};

        for(int i=0; i<3; i++){
            time++;
            for(int j=1; j<=duration[i]; j++){
                int incoming = arrivalRate[i];
                space = bucketSize - stored;

                if(incoming<=space){
                    stored = stored + incoming;
                }
                else{
                    discarded = discarded + (incoming - space);
                    stored = bucketSize;

                }

                if(stored<=outputRate)
                    transmitted = stored;
                else
                    transmitted = outputRate;

                stored = stored - transmitted;

                System.out.println("Time: "+time+"s");
                System.out.println("Packets transmitted: "+transmitted+"KB");
                System.out.println("Data stored: "+stored+"KB");
                System.out.println("Total Data Discarded "+discarded+"KB");
                System.out.println();

            }

            System.out.println("Final data remaining in Bucket: "+stored+"KB");
            System.out.println("Total Data discarded due to overflow: "+discarded+"KB");

            while(stored>0){
                time++;
                if(stored<=outputRate)
                    transmitted = stored;
                else
                    transmitted = outputRate;

                stored = stored - transmitted;

                System.out.println("Time: "+time+"s");
            System.out.println("Data Transmitted: "+transmitted+"KB");
            System.out.println("Data remaining in bucket: "+stored+"KB");
            System.out.println();
            }

            System.out.println("At "+time+"s bucket becomes empty");
        }
        
    }
}
