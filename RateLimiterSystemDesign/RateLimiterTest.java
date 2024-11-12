package systemDesign.rateLimiter;

public class RateLimiterTest {
    public static void main(String[] args) {
        RateLimiter rateLimiter=new RateLimiter(10,10,1000);

        int noOfConsumed=0;
        long startTime=System.currentTimeMillis();
        int totalTime=5*1000;

        while((System.currentTimeMillis()-startTime)<totalTime){
            boolean isRequestConsumed= rateLimiter.tryConsume();
            if(isRequestConsumed){
                noOfConsumed++;
            }
        }

        System.out.println("Consumed request in 5 second "+noOfConsumed);

    }
}
