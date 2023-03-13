import sys
from collections import deque
sys.stdin = open("10.26/뱀.txt", "r", encoding="UTF-8")

# 상 하 좌 우
delta = [(-1, 0), (1, 0), (0, -1), (0, 1)]

# 왼 오
direction = [[2, 3], [3, 2], [1, 0], [0, 1]]

n = int(input())
k = int(input())

matrix = [[0 for _ in range(n)] for _ in range(n)]

# 사과는 9로 마킹
for _ in range(k):
    y, x = map(int, input().split())
    matrix[y-1][x-1] = 9

l = int(input())
changeList = []
for _ in range(l):
    time, d = input().split()
    changeList.append((int(time), d))

# 초기 설정
t, y, x = 0, 0, 0
nowDir = 3
matrix[y][x] = 1
q = deque()
q.append((y, x))

while True:
    t += 1
    y += delta[nowDir][0]
    x += delta[nowDir][1]

    if 0 <= y < n and 0 <= x < n:
        # 사과 있으면 꼬리는 가만히 있고 머리만 늘어남
        if matrix[y][x] == 9:
            matrix[y][x] = 1
            q.append((y, x))
        # 아무 것도 없으면 하나 빼서 길이 유지
        elif matrix[y][x] == 0:
            matrix[y][x] = 1
            q.append((y, x))
            tailY, tailX = q.popleft()
            matrix[tailY][tailX] = 0
        else:
            break
        if changeList:
            if t == changeList[0][0]:
                if changeList[0][1] == 'L':
                    nowDir = direction[nowDir][0]
                else:
                    nowDir = direction[nowDir][1]
                changeList.pop(0)
    else:
        break

print(t)
