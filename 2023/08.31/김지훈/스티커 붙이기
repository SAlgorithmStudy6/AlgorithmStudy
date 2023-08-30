

import java.io.*;
import java.util.*;


class Main{


    static int N,M,K,R,C;
    static int[][] notebook;
    static int[][] stickers;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        notebook = new int[N][M];
        count =0;
        while(K-->0){
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            stickers = new int[R][C];
            for(int i=0;i<R;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<C;j++){
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int degree = 0;
            int[][] arr = stickers;
            boolean flag = false;
            while(true){
                //스티커 들어간 경우
                if(isFit(arr)) break;
                //안들어간 경우 회전
                else{
                    arr = rotate(stickers,degree);
                    degree++;
                }
                if(degree==4){
                    flag = true;
                    break;
                }
            }
            //스티커가 다 안들어간경우
            if(flag) continue;
        }
        returnStickers();
        bw.write(count+"");
        bw.close();
        br.close();
    }

    //스티커가 노트북에 들어갈수 있는지 확인
    static boolean isFit(int[][] arr){
        boolean result = true;
        int r=0;
        int c=0;
        boolean flag;
        while(true){
            flag = true;
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[0].length;j++){
                    if(r+i>=N||c+j>=M) {
                        flag = false;
                        break;
                    }
                    if(arr[i][j]==1&&notebook[i+r][j+c]==1){
                        flag = false;
                        break;
                    }
                }
            }
            //붙는 경우
            if(flag) break;
            //안붙는 경우
            else{
                //한칸식 다밀어봐도 안되는경우
                if(r>=N) return false;
                else{
                    if(c>=M){
                        r++;
                        c=0;
                    }else{
                        c++;
                    }
                }
            }
        }


        //노트북에 스티커 붙이기
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j]==1) notebook[i+r][j+c] = 1;
            }
        }
        return result;
    }
    //노트북 스티커 개수
    static void returnStickers(){
        for(int i=0;i<notebook.length;i++){
            for(int j=0;j<notebook[0].length;j++){
                if(notebook[i][j]==1) count++;
            }
        }
    }
    //배열 회전
    static int[][] rotate(int arr[][],int degree){
        //0 : 90
        //1 : 180
        //2: 270
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate;
        switch(degree){
            case 0:{

            }
            case 2:{
                rotate = new int[m][n];
                break;
            }
            case 1:{
                rotate = new int[n][m];
                break;
            }
            default:{
                rotate = new int[0][0];
            }
        }
        for(int i=0;i<rotate.length;i++){
            for(int j=0;j<rotate[0].length;j++){
                switch (degree){
                    case 0:{
                        rotate[i][j] = arr[n-1-j][i];
                        break;
                    }
                    case 1:{
                        rotate[i][j] = arr[n-1-i][m-1-j];
                        break;
                    }
                    case 2:{
                        rotate[i][j] = arr[j][m-1-i];
                        break;
                    }
                }
            }
        }

        return rotate;
    }


}
