import sys
import copy
from itertools import combinations


sys.stdin = open("./input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

n, m, d = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(n)]
answer = 0

archer = [i for i in range(m)]
archerPos = tuple(combinations(archer, 3))

# 사거리가 긴 경우 끝까지 탐색
distance = -1
# 보통의 사거리
if d <= n:
    distance = n - 1 - d


# (0,1,2)
for position in archerPos:
    kill = 0
    copyMatrix = copy.deepcopy(matrix)
    for _ in range(n):
        killed = []
        for p in position:
            enemyInRange = d
            enemyX = m
            enemyY = n
            for y in range(n - 1, distance, -1):
                for x in range(m):
                    if (
                        copyMatrix[y][x] == 1
                        and abs(y - n) + abs(x - p) <= enemyInRange
                    ):
                        # 1. 사정거리 안
                        if abs(y - n) + abs(x - p) < enemyInRange:
                            enemyX = x
                            enemyY = y
                            enemyInRange = abs(y - n) + abs(x - p)
                        # 2. 사정거리 동일할 땐 제일 작은 x를 픽
                        else:
                            if x < enemyX:
                                enemyX = x
                                enemyY = y
                                enemyInRange = abs(y - n) + abs(x - p)

            if enemyX != m:
                killed.append((enemyY, enemyX))

        if len(killed) > 0:
            killed = list(set(killed))
            for k in killed:
                copyMatrix[k[0]][k[1]] = 0
                kill += 1
        copyMatrix = [[0 for _ in range(m)]] + copyMatrix[0 : n - 1]

    answer = max(answer, kill)

print(answer)
