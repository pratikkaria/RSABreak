import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.*;
public class rsa
{
    public static BigInteger goToNextPrime(BigInteger number)
    {
        while(true)
        {
            number = number.add(BigInteger.ONE);
            if(returnPrime(number))
                return number;
        }
    }
    public static boolean returnPrime(BigInteger number) 
    {
        if (!number.isProbablePrime(5))
            return false;
        BigInteger two = new BigInteger("2");
        if (!two.equals(number) && BigInteger.ZERO.equals(number.mod(two)))
            return false;
        for (BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(number) < 1; i = i.add(two)) {
            if (BigInteger.ZERO.equals(number.mod(i)))
                return false;
        }
        return true;
    }
    public static BigDecimal takeRoot(int root, BigDecimal n, BigDecimal maxError) {
        int MAXITER = 5000;
        MathContext mc = new MathContext(8);
        BigDecimal x;
        x=new BigDecimal("1",mc);
        BigDecimal prevX = null;
        BigDecimal rootBD = new BigDecimal(root,mc);
        for(int i=0; i < MAXITER; ++i) {
            x = x.subtract(x.pow(root,mc)
                   .subtract(n,mc)
                   .divide(rootBD.multiply(x.pow(root-1,mc),mc),mc),mc);
            if(prevX!=null && prevX.subtract(x).abs().compareTo(maxError) < 0)
                break;
            prevX = x;
        }
        return x;
    }
    public static void main(String args[])throws IOException
    {
        long startTime = System.currentTimeMillis();
        BufferedReader xy = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the encrypion key ");
        int e = Integer.parseInt(xy.readLine());
        System.out.println("Enter the value of n ");
        String n = xy.readLine();
        BigDecimal num = new BigDecimal(n);
        System.out.println("Enter the cipher text ");
        String c = xy.readLine();
        BigDecimal cipher = new BigDecimal(c);
        BigInteger i = new BigInteger("2");
        BigInteger limit = new BigInteger("2000000");
        BigInteger t = new BigInteger("0");
        BigDecimal msg = new BigDecimal ("0");
        BigDecimal cube = new BigDecimal("0");
        int flag = 0;
        BigDecimal copy = new BigDecimal("0");
        BigInteger check = new BigInteger("2");
        for(;i.compareTo(limit)<0;i=i.add(BigInteger.ONE))
        {        
            msg=num.multiply(new BigDecimal(i)).add(cipher);
            msg= takeRoot(e,msg,cube);
             System.out.println(msg+"              "+i);
            if(msg.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0)
            {
                t =i;
                break;
            }   
            if(returnPrime(i))
            {
                flag = 1;
            }   
            if(flag == 1)
            {
                if(copy.toBigInteger().compareTo(msg.toBigInteger())==0)
                {
                    //System.out.println("hello");
                    i = goToNextPrime(i);
                }
                copy = msg;
            }
            flag = 0;
        }
        System.out.println(msg+ "          "+i);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TIME: "+elapsedTime);
    }
}
