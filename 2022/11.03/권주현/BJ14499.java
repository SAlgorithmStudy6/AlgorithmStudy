import java.util.Scanner;

 

public class BJ14499 {

    static Scanner scan=new Scanner(System.in);

    static int N,M,x,y,K;

    static int[][]arr;

    static int [] move;

    static int[]dx= {0,0,-1,1};

    static int[]dy= {1,-1,0,0};

    static class pos2{

        int x,y;

        public pos2(int x,int y) {

            this.x=x;

            this.y=y;

        }

    }

    static class dto{
        pos2 pos; //리턴할 좌표
        int[] dice;
        boolean issuccess;
        public dto(pos2 pos,int[] dice,boolean issuccess) {
            this.pos=pos;
            this.dice=dice;
            this.issuccess=issuccess;
        }
        
    }
    public static void main(String[] args) {

    

        //격자의 크기

        N=scan.nextInt();

        M=scan.nextInt();

        //좌표

        x=scan.nextInt();

        y=scan.nextInt();   

        K=scan.nextInt();   //명령의 개수

    

        arr=new int[N][M];

        move=new int[K];

        for(int i=0;i<N;i++) {

            for(int j=0;j<M;j++) {

                arr[i][j]=scan.nextInt();

            }

        }//격자입력

        

        for(int i=0;i<K;i++) {

            move[i]=scan.nextInt();

        }   //이동명령 입력  오:1 왼:2 위:3 아래:4

        

        sol();

        

        

    

    }

    public static void sol() {

        int [] dice=new int[7];

        int X=x,Y=y;
       
        for(int i=0;i<K;i++) {
            boolean flag=true;
            pos2 pos=new pos2(X,Y);
            //명령대로 움직이기
            dto info=move(dice,move[i],pos);
            
            //데이터 갱신
            X=info.pos.x;
            Y=info.pos.y;
            dice=info.dice;
            flag=info.issuccess;
            if(flag) {
                //격자, 주사위 갱신
                if(arr[X][Y]==0) {
                    arr[X][Y]=dice[6];
                }else {
                    dice[6]=arr[X][Y];  
                    arr[X][Y]=0;
                }
                
                
                System.out.println(dice[1]);
            }

        }

    }

    public static dto move(int [] dice ,int dir,pos2 pos) {

        int[] dice_cp=new int[7];

        if(dir==1) {

            dice_cp[1]=dice[4];

            dice_cp[2]=dice[2];

            dice_cp[3]=dice[1];

            dice_cp[4]=dice[6];

            dice_cp[5]=dice[5];

            dice_cp[6]=dice[3];

            

            int x=pos.x+dx[dir-1];

            int y=pos.y+dy[dir-1];

            if(x<0||y<0||x>=N||y>=M) {      //격자 탈출할라카면
                return new dto(new pos2(pos.x,pos.y),dice,false);
            }
            else return new dto(new pos2(x,y),dice_cp,true);

        }

        else if(dir==2) {

            dice_cp[1]=dice[3];

            dice_cp[2]=dice[2];

            dice_cp[3]=dice[6];

            dice_cp[4]=dice[1];

            dice_cp[5]=dice[5];

            dice_cp[6]=dice[4];

            int x=pos.x+dx[dir-1];

            int y=pos.y+dy[dir-1];
            if(x<0||y<0||x>=N||y>=M) {      //격자 탈출할라카면
                return new dto(new pos2(pos.x,pos.y),dice,false);
            }
            else return new dto(new pos2(x,y),dice_cp,true);
        }

        else if(dir==3) {

            dice_cp[1]=dice[5];

            dice_cp[2]=dice[1];

            dice_cp[3]=dice[3];

            dice_cp[4]=dice[4];

            dice_cp[5]=dice[6];

            dice_cp[6]=dice[2];

            int x=pos.x+dx[dir-1];

            int y=pos.y+dy[dir-1];
            if(x<0||y<0||x>=N||y>=M) {      //격자 탈출할라카면
                return new dto(new pos2(pos.x,pos.y),dice,false);
            }
            else return new dto(new pos2(x,y),dice_cp,true);
            }

        else if(dir==4) {

            dice_cp[1]=dice[2];

            dice_cp[2]=dice[6];

            dice_cp[3]=dice[3];

            dice_cp[4]=dice[4];

            dice_cp[5]=dice[1];

            dice_cp[6]=dice[5];

            int x=pos.x+dx[dir-1];

            int y=pos.y+dy[dir-1];
            if(x<0||y<0||x>=N||y>=M) {      //격자 탈출할라카면
                return new dto(new pos2(pos.x,pos.y),dice,false);
            }
            else return new dto(new pos2(x,y),dice_cp,true);

        }
        return null;

    }

 

}
