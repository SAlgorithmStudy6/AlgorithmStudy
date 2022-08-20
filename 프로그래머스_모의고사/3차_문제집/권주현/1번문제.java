class Solution {
    static int ans;
    public int solution(int a, int b, int n) {
        cola(a,b,n,0);
        return ans;
    }

    public static void cola (int a, int b, int n,int sum) {
        if(n<a){
            ans=sum;
            return;
        }
        int left= n%a;
        int nextn= n/a*b+left;
        int get=n/a*b;
        cola(a,b,nextn,sum+get);
    }
}
//    n/a*b => 1회 했을 때 받는 병 => 다시 n으로 재귀
//n =20 a=2 b=1
