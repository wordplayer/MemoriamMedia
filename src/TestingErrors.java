import java.io.*;
public class TestingErrors {
    public static void main(String args[])throws Exception
    {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        System.out.println("Enter value of x in radians");
        double x=Double.parseDouble(br.readLine());
        double tr=Math.sin(x);
        double es=0.000001;
        int maxit=100;
        int p=0;
        double resp=0.0;
        double app=0.0,ter=0.0,tera=0.0,aer=0.0,aera=0;
        System.out.println("True value= "+tr);
        while(true)
        {
            app=resp+((Math.pow(-1,p)*Math.pow(x,(2*p)+1))/new TestingErrors().fact((2*p)+1));
            //System.out.println(app);
            ter=(tr-app)/tr;
            //System.out.println(ter);
            //if(ter<0)
            //ter=-1.0*ter;    
            ter=Math.abs(ter);
            aer=(app-resp)/app;
            //if(aer<0)
            //aer=-1.0*aer;    
            resp=app;
            aer=Math.abs(aer);
            if(aer<es)
            {
                System.out.println("Estimate of sin(x)= "+app);
                System.out.println("True relative error= "+ter);
                System.out.println("Approxmate relative error= "+aer);
                System.out.println("Number of iterations required= "+p);
                //System.out.println("Reached 1");
                break;
            }
            else if(p+1>maxit)
            {
                System.out.println("Estimate of sin(x)= "+app);
                System.out.println("True relative error= "+ter);
                System.out.println("Approxmate relative error= "+aer);
                System.out.println("Number of iterations required= "+p);
                break;
            }
            else
            p++;
       }    
    }
    public int fact(int n)
    {
        if(n==1)
        return 1;
        else
        return n*fact(n-1);    
    }        
}
