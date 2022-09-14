import sys
sys.stdin = open("09.15/점심식사시간.txt", "r", encoding="UTF-8")

T = int(input())


def selection(depth, pick, m):
    global stairSelect
    if depth == m:
        stairSelect.append(pick)
        return

    selection(depth + 1, pick + [0], m)
    selection(depth + 1, pick + [1], m)

# stair: 계단에 들어갈 사람들. time: 내려가는데 걸리는 시간


def downStairs(stair, time):
    t = 1
    down = []
    while stair or down:
        delete = []
        # 내려가는 사람들을 처리해야 계단에 사람을 올릴 수 있다
        if down:
            for i in range(len(down)):
                down[i][1] += 1
                if down[i][1] == time:
                    delete.append(i)

            for _ in range(len(delete)):
                down.pop(0)

        delete = []
        if stair:
            for i in range(len(stair)):
                if stair[i][0] <= t:
                    if len(down) < 3:
                        down.append(stair[i])
                        delete.append(i)
            for _ in range(len(delete)):
                stair.pop(0)
        t += 1
    return t


for test_case in range(1, T+1):
    n = int(input())
    manList = []
    stairList = []
    stairSelect = []
    stair0, stair1 = [], []
    times = []
    minTime = 999999999
    for y in range(n):
        temp = list(map(int, input().split()))
        for x in range(n):
            if temp[x] == 1:
                manList.append([y, x])
            elif temp[x] > 1:
                stairList.append([y, x])
                times.append(temp[x])

    selection(0, [], len(manList))

    for select in stairSelect:
        for i in range(len(manList)):
            if select[i] == 0:
                temp = abs(manList[i][0] - stairList[0][0]) + \
                    abs(manList[i][1] - stairList[0][1])
                stair0.append([temp, 0])
            else:
                temp = abs(manList[i][0] - stairList[1][0]) + \
                    abs(manList[i][1] - stairList[1][1])
                stair1.append([temp, 0])
        # 거리가 짧은 것이 먼저 오도록 정렬
        stair0.sort()
        stair1.sort()

        # 두 계단 중 큰 값이 이번 조합의 시간
        time = max(downStairs(stair0, times[0]), downStairs(stair1, times[1]))

        # 최소 시간을 갱신
        minTime = min(time, minTime)

    print("#{} {}".format(test_case, minTime))
