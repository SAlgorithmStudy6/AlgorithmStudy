import sys
from collections import deque
sys.stdin = open("09.19/특이한자석.txt", "r", encoding="UTF-8")

T = int(input())

def checkRight(target):
    result = []
    i = target
    # 오른쪽의 것을 확인
    while i < 4:
        if magnets[i][2] == magnets[i+1][6]:
            break
        else:
            result.append(i+1)
        i += 1
    
    return result

def checkLeft(target):
    result = []
    i = target
    # 왼쪽의 것을 확인
    while 1 < i:
        if magnets[i][6] == magnets[i-1][2]:
            break
        else:
            result.append(i-1)
        i -= 1
    return result


for test_case in range(1, T+1):
    k = int(input())
    magnets = [deque()]
    for _ in range(4):
        q = deque(map(int, input().split()))
        magnets.append(q)
    turning = [list(map(int, input().split())) for _ in range(k)]

    for turn in turning:
        target, dir = turn[0], turn[1]
        # 타겟의 오른쪽, 왼쪽의 자석을 보고 같이 돌릴 것을 체크
        right = checkRight(target)
        left = checkLeft(target)

        # 타겟을 먼저 돌림
        magnets[target].rotate(dir)

        # 오른쪽으로 돌릴 것들을 방향을 바꿔가며 돌림
        for change in right:
            dir *= -1
            magnets[change].rotate(dir)
        
        # 방향을 초기값으로 돌려놓고 왼쪽도 반복
        dir = turn[1]
        for change in left:
            dir *= -1
            magnets[change].rotate(dir)

    # 순회하며 점수 계산
    point = 0
    points = [0, 1, 2, 4, 8]
    for i in range(1,5):
        point += magnets[i][0] * points[i]

    print("#{} {}".format(test_case, point))