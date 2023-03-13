import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

T = int(input())

for test_case in range(1, T+1):
    n, m, k, a, b = map(int, input().split())
    aList = list(map(int, input().split()))
    bList = list(map(int, input().split()))
    tList = [0] + list(map(int, input().split()))

    nList = [[0,0] for _ in range(n)]
    mList = [[0,0] for _ in range(m)]

    waiting = []
    complete = []
    result = 0
    # 사용했던 desk를 저장하는 배열
    usedDesk = [[0,0] for _ in range(k+1)]
    tIdx = 1
    time = 0
    while True:
        for i in range(n):
            # 빈자리가 아니면 1초 추가
            if nList[i][0] != 0:
                nList[i][1] += 1
            # 시간을 다 채웠다면 waiting으로
            if nList[i][1] == aList[i]:
                waiting.append(nList[i][0])
                nList[i] = [0, 0]
            # tIdx 하나씩 밀어주면서 빈자리가 있으면 추가
            if tIdx <= k:
                if nList[i] == [0, 0]:
                    if tList[tIdx] <= time:
                        nList[i] = [tIdx, 0]
                        usedDesk[tIdx][0] = i
                        tIdx += 1
        if waiting:
            for i in range(m):
                # 빈자리라면 waiting에서 하나 추가
                if mList[i] == [0, 0]:
                    idx = waiting.pop(0)
                    usedDesk[idx][1] = i
                    mList[i] = [idx, 0]
                    if not waiting:
                        break
    
        for i in range(m):
            # 빈자리가 아니면 1초 추가
            if mList[i][0] != 0:
                mList[i][1] += 1
            # 시간을 다 채웠다면 complete로
            if mList[i][1] == bList[i]:
                complete.append(mList[i][0])
                mList[i] = [0, 0]
        # 모두 다 complete에 들어왔는지 체크하고 while 탈출
        if len(complete) == k:
            break
        time += 1
    
    # 조건에 맞다면 result에 더해주기
    for i in range(1, len(usedDesk)):
        if usedDesk[i] == [a-1, b-1]:
            result += i
    
    if result == 0:
        print("#{} -1".format(test_case))
    else:
        print("#{} {}".format(test_case, result))