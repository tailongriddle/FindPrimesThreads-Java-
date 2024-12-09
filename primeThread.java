
public class primeThread extends Thread{
  
  private int threadNum;
  private int start;
  private int stop;
  private int results[];

  public primeThread(int threadNum, int start, int stop, int results[]) {
      
        this.threadNum = threadNum;
        this.start = start;
        this.stop = stop;
        this.results = results;
   

    
  }
  
  
  @Override
  public void run() {
    results[threadNum] = findPrime.countPrime(start,stop);
    System.out.println("Thread " + threadNum + " Range: " + start + " - " + stop + " | Primes Found: " + results[threadNum]);
    
    // TODO Auto-generated method stub
    
  }

}
