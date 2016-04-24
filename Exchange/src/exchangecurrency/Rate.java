
import java.time.LocalDateTime;



public class Rate {
    private double rate;
    private LocalDateTime timeStamp;
    
    //Constructor for rate with past timestamp
    public Rate(double rate, LocalDateTime time)
    {
        this.rate = rate;
        this.timeStamp = time;
    }
    
    //Constructor for rate with current time
    public Rate(double rate)
    {
        this.rate = rate;
        timeStamp = LocalDateTime.now();
    }
    
    //Method to update rate with current time
    public void updateRate(double rate)
    {
        this.rate = rate;
        timeStamp = LocalDateTime.now();
    }
    
    //Method to set rate given another rate
    public void setRate(Rate newRate)
    {
        this.rate = newRate.getRate();
        timeStamp = newRate.getTime();
    }
    
    //Method to get the rate
    public double getRate()
    {
        return this.rate;
    }
    
    //method to get the timeStamp
    public LocalDateTime getTime()
    {
        return this.timeStamp;
    }
}
