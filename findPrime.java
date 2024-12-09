
public class findPrime {


  /***
   * 
   * Prime number brute force
   * 
   * 
   * @param num
   * @return bool
   */
  public static boolean isPrime(int num) {
    int i = 2;

    if (num == 2) { // exception for 2
      return true;
    }
    
    if (num == 1) {
      return false; 
    }
    
    while (i * i <= num) { // test numbers from 2 up
      // System.out.println("num is: " + num + ", i is: " + i);
      if (num % i == 0) { // if divisible
        return false; // it is not prime!
      }

      i++;

    }

    return true;
  }


  /**
   * 
   * Prime number counter
   * 
   * 
   * @param num1
   * @param num2
   * @return count
   */
  public static int countPrime(int num1, int num2) {

    int count = 0;

    for (int i = num1; i < num2; i++) { // for each num in range...
      if (isPrime(i)) {
        // System.out.println(i);
        count++;
      }

    }

    return count;
  }



  public static void main(String[] args) {

    // number of threads
    int numThreads = 4;
    
    //results
    int[] results = new int[numThreads];
    int totalPrimes = 0;

    // range
    int num1 = 1000;
    int num2 = 1000000;
    int totalNums = num2 - num1 + 1;
    int stop;
    
   


    Thread[] threads = new Thread[numThreads]; // create new list of threads

    
    long startTime = System.currentTimeMillis(); // start timer

    for (int i = 0; i < numThreads; i++) {
      int start = num1 + (i * (totalNums / numThreads));
      if (i == numThreads - 1 && (totalNums / numThreads % 1 != 0)) {
        stop = num1 + ((i + 1) * (totalNums / numThreads)) + 2;
      } else {
      stop = num1 + ((i + 1) * (totalNums / numThreads));
      }
      
      primeThread pThread = new primeThread(i, start, stop, results);
      Thread newThread = new Thread(pThread);
      threads[i] = newThread;
      newThread.start(); // fork
    }

    for (int i = 0; i < numThreads; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    long endTime = System.currentTimeMillis();
    System.out.println("Time took to compute threads: " + (endTime - startTime));


    // count total primes
    for (int i = 0; i < results.length; i++) {
      
      totalPrimes += results[i];
      
    }

    System.out.println("Total Primes: " + totalPrimes);
    
    
    // TEST isPrime
    System.out.println("Test isPrime: ");
    System.out.println("85: " + isPrime(85));
    System.out.println("7: " + isPrime(7));
    System.out.println("2: " + isPrime(2));
    System.out.println("9: " + isPrime(9));
    System.out.println("1087: " + isPrime(1087));
    System.out.println("733260091: " + isPrime(733260091));

    
    System.out.println(" ");

    // TEST countPrime
    System.out.println("Test countPrime(one thread): ");
    System.out.println(num1 + ", "+  num2 + ": " + countPrime(num1, num2));
    // System.out.println(countPrime(0,3));

    // TIME countPrime
    startTime = System.currentTimeMillis(); // start timer
    countPrime(num1, num2);
    endTime = System.currentTimeMillis();
    System.out.println("Time took to compute serial(one thread): " + (endTime - startTime));
    // TIME: 7755



  }



}


