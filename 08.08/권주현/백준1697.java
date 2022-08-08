package etc;

import java.util.LinkedList;

import java.util.Queue;

import java.util.Scanner;

class Position{

    int me;

    int count;

    Position(int me, int count){

        this.me=me;

        this.count=count;

    }

    

}

 

public class 백준1697 {

    static int n,k;

    static boolean [] visited;

    public static void main(String[] args) {

        // TODO Auto-generated method stub
            Scanner scan=new Scanner(System.in);
            visited=new boolean[100001];
            n=scan.nextInt();

            k=scan.nextInt();
            if(n==k) System.out.print(0);
            else System.out.println(bfs(n));

    }

    public static int bfs(int n) {

        Queue<Position> q=new LinkedList<>();

        q.offer(new Position(n,0));

        

        while(!q.isEmpty()) {          

            Position p=q.poll();
//            System.out.println(p.me+" "+p.count);
            if( p.me*2<=100000&&!visited[p.me*2]) {
            	visited[p.me*2]=true;
//                System.out.println("*2");

            	 if(p.me*2==k) {

                     return p.count+1;

                 }
            	q.offer(new Position(p.me*2,p.count+1));
            }
            if(p.me+1<=100000&&!visited[p.me+1]) {
            	visited[p.me+1]=true;
//            	System.out.println("+1");
            	 if(p.me+1==k) {

                     return p.count+1;

                 }
            	q.offer(new Position(p.me+1,p.count+1));
            }
            if(p.me-1>=0&&!visited[p.me-1]) {
            	visited[p.me-1]=true;
//            	System.out.println("-1");
            	 if(p.me-1==k) {

                     return p.count+1;

                 }
            	q.offer(new Position(p.me-1,p.count+1));
            }
 

 

        }

        return 0;

    }

}
