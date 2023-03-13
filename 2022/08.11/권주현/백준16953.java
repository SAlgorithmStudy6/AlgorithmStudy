import java.util.LinkedList;

import java.util.Queue;

import java.util.Scanner;

 

class position{

    long p;

    long cnt;

    position(long p, long cnt){

        this.p=p;

        this.cnt=cnt;

    }

}

public class Main {

    public static void main(String[] args) {

        // TODO Auto-generated method stub

        Scanner scan=new Scanner(System.in);

        long a=scan.nextLong();

        long b=scan.nextLong();

        if(a==b) {
        	System.out.println(0);
        	return;
        }

        Queue<position> q=new LinkedList<>();

        

        q.offer(new position(a,1));
        long res = -1;

        while(!q.isEmpty()) {

            position now=q.poll();
             if(now.p == b) {
                res = now.cnt;
                break;
            }

            if(now.p > b) {
                break;
            }

            if(now.p*2<=b) {

                q.offer(new position(now.p*2,now.cnt+1));

            }

           

            if((now.p*10)+1<=b) {

                q.offer(new position((now.p*10)+1,now.cnt+1));

            }


        } 
System.out.println(res);
    }

 

}
