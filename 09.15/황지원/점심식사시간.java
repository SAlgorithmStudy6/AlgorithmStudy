import java.io.*;
import java.util.*;

public class 점심식사시간 {
    static long Astairs[], Bstairs[], arr[][];
    static ArrayList<Person> Person, Person2;
    static LinkedList<Person> Await, Bwait;
    static LinkedList<Time> Agoing, Bgoing; // 계단안에 있는 사람
    static long N, min;
    static Deque<Person> Ateam, Bteam; // A, B 조합
    static int[] temp;
    static long lunch, lunchA, lunchB;
    static boolean visit[];

    static void solve() {

        // 1. 조합으로 A계단, B계단 갈 사람 구하기, A계단 사람 0명부터 골라주기(조합)
        for (int r = 0; r <= Person.size() / 2 + 1; r++) {
            makeTeam(new int[Person.size()], 0, 0, r, r); 
        }

    }

    static void makeTeam(int[] temp, int index, int depth, int R, int r) {
        if (r == 0) {
            Ateam.clear();
            Bteam.clear();
            for (int i = 0; i < Person.size(); i++) {
                if (temp[i] == 1) {
                    Ateam.offer(Person.get(i));
                }
            }
            for (int i = 0; i < Person.size(); i++) {
                if (!Ateam.contains(Person.get(i))) {
                    Bteam.offer(Person.get(i));
                }
            }
            lunchA = Ateam.size();
            lunchB = Bteam.size();
        

            lunch = 0;
            // 3. 이제 for문으로 시간 count 스따뜨
            long time = 1;
            while (lunch < Person.size() || !(lunchA == 0 && lunchB == 0)) {
                // // 4. A, B배열에 time out(시간, 배열에 적힌 값) 있나?
                timeout(time);

                // // 5. A, B 입구에 있는 애들 timeA, B에 넣자, 값은 현재 시간 + 계단에 적힌 값. 넣었으면 빼주고
                stairEnter(time);

                // // 6. 거리 == 지금 시간인 사람 , Aent, Bent에 넣자. 그리고 값 삭제
                makeWaitList(time);

                time++;
            }
            int maxA = Integer.MIN_VALUE, maxB = Integer.MAX_VALUE;
            for (int i = 0; i < Agoing.size(); i++) maxA = Math.max(maxA, (int) Agoing.get(i).outTime);
            for (int i = 0; i < Bgoing.size(); i++) maxB = Math.max(maxB, (int) Bgoing.get(i).outTime);
            int t = Math.max(maxA, maxB);
            if (t != 0) {
                time = time - 1;
            } else {
                time = Math.min(time - 1, t);
            }
            min = Math.min(min, time);
            

            // A, B 집합 바꾸기 

            lunch = 0;
            time = 1;
            
            Deque<Person> tt = Ateam;
            Ateam = Bteam;
            Bteam = tt;

            lunchA = Ateam.size();
            lunchB = Bteam.size();

            while (lunch < Person.size() || !(lunchA == 0 && lunchB == 0)) {
                // // 4. A, B배열에 time out(시간, 배열에 적힌 값) 있나?
                timeout(time);

                // // 5. A, B 입구에 있는 애들 timeA, B에 넣자, 값은 현재 시간 + 계단에 적힌 값. 넣었으면 빼주고
                stairEnter(time);

                // // 6. 거리 == 지금 시간인 사람 , Aent, Bent에 넣자. 그리고 값 삭제
                makeWaitList(time);

                time++;
            }
            maxA = Integer.MIN_VALUE; maxB = Integer.MAX_VALUE;
            for (int i = 0; i < Agoing.size(); i++) maxA = Math.max(maxA, (int) Agoing.get(i).outTime);
            for (int i = 0; i < Bgoing.size(); i++) maxB = Math.max(maxB, (int) Bgoing.get(i).outTime);
            t = Math.max(maxA, maxB);
            if (t != 0) 
                time = time - 1;
            else 
                time = Math.min(time - 1, t);
            min = Math.min(min, time);
            return;
        }
        if (depth == Person.size()){
            return;
        } else {
            temp[index] = 1;
            makeTeam(temp, index + 1, depth + 1, R, r - 1);
            temp[index] = 0;
            makeTeam(temp, index + 1, depth + 1, R, r);
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/2383.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            init();
            // 입력
            for (long i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (long j = 1; j <= N; j++) {
                    int read = Integer.parseInt(st.nextToken());
                    arr[(int) i][(int) j] = read;
                }
            }

            int s = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][j] > 1) {
                        if (s == 0) {
                            Astairs[0] = i;
                            Astairs[1] = j;
                            Astairs[2] = arr[i][j];
                            s++;
                        } else {
                            Bstairs[0] = i;
                            Bstairs[1] = j;
                            Bstairs[2] = arr[i][j];
                        }
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][j] == 1) {
                        long toA = Math.abs(Astairs[0] - i) + Math.abs(Astairs[1] - j);
                        long toB = Math.abs(Bstairs[0] - i) + Math.abs(Bstairs[1] - j);
                        Person.add(new Person(i, j, toA, toB));
                    }
                }
            }
            Person2 = Person;
            visit = new boolean[Person.size()];
            temp = new int[Person.size()];

            solve();

            System.out.println("#" + tc + " " + (min)); 
        }

    }

    // 6. 거리 == 지금 시간인 사람 , Aent, Bent에 넣자. 그리고 값 삭제
    static void makeWaitList(long currentTime) {
        for (Person a : Ateam) {
            if (currentTime == a.toA) {
                Await.add(a);
                lunchA--;
            }
        }

        for (Person b : Bteam) {
            if (currentTime == b.toB) {
                Bwait.add(b);
                lunchB--;
            }
        }
    }

    // 5. A, B 입구에 있는 애들 timeA, B에 넣자, 값은 현재 시간 + 계단에 적힌 값. 넣었으면 빼주고
    static void stairEnter(long currentTime) {
        // 계단에는 3명까지 들어갈 수 있다.
        while (!Await.isEmpty() && Agoing.size() < 3) {
            Await.remove(0);
            Agoing.add(new Time(currentTime + Astairs[2])); // Agoing에 종료시간을 적어준다.
        }

        while (!Bwait.isEmpty() && Bgoing.size() < 3) {
            Bwait.remove(0);
            Bgoing.add(new Time(currentTime + Bstairs[2])); // Bgoing에 종료시간을 적어준다.
        }
    }

    // 4. A, B배열에 time out(시간, 배열에 적힌 값) 있나?
    static void timeout(long currentTime) {
        while (Agoing.size() > 0) {
            if (currentTime >= Agoing.get(0).outTime) {
                lunch++;
                Agoing.remove(0);
            } else {
                break;
            }
        }
        while (Bgoing.size() > 0) {
            if (currentTime >= Bgoing.get(0).outTime) {
                lunch++;
                Bgoing.remove(0);
            } else {
                break;
            }
        }
    }

    static void init() {
        min = Long.MAX_VALUE;
        lunch = 0;

        arr = new long[(int) N + 1][(int) N + 1];
        Person = new ArrayList<>();
        Person2 = new ArrayList<>();

        Astairs = new long[3];
        Bstairs = new long[3];

        Await = new LinkedList<>();
        Bwait = new LinkedList<>();

        Ateam = new LinkedList<>();
        Bteam = new LinkedList<>();

        Agoing = new LinkedList<>();
        Bgoing = new LinkedList<>();

    }
}

class Time {
    long outTime;

    Time(long outTime) {
        this.outTime = outTime;
    }

    @Override
    public String toString() {
        return "[out = " + outTime + "]";
    }

}

class Person {
    long i;
    long j;
    long toA;
    long toB;

    Person(long i, long j, long toA, long toB) {
        this.i = i;
        this.j = j;
        this.toA = toA;
        this.toB = toB;
    }

    @Override
    public String toString() {
        return "(" + i + "," + j + "," + toA + "," + toB + ")";
    }

}

class Stair {
    long x;
    long y;
    long time;

    Stair(long x, long y, long time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}
