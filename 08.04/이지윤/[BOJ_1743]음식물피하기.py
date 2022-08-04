# 인덱스 범위가 1부터 시작이므로, 귀찮으니까 배열+1해주어 한칸 씩 밀것.
# 배열 내 데이터가 0또는 1이므로, visited 표시를 2로 가능하다. 따라서 굳이 visited 배열 만들 필요 X.
# ------------------------------------------------------------------------------------
# from collections import deque
# trash = deque()
import sys
sys.setrecursionlimit(10**7)
sys.stdin = open("input.txt", "r")


move = [(0, -1), (1, 0), (0, 1), (-1, 0)] # 방향(4) : 위, 오, 아, 왼0

n, m, k = map(int, input().split())
road = [[0]*(m+1) for _ in range(n+1)]

def dfs(_x, _y)  :
    global count
    print( _x, _y)
    if _x<=0 or m<_x or _y<=0 or n<_y: return False
    if road[_y][_x]:
        count += 1
        road[_y][_x] = -1
        for dx, dy in move:
            dfs(_x+dx, _y+dy)
        return True
    return False


def init() :
    for _ in range(k):
        y, x = map(int, input().split())
        road[y][x] = 1
        # trash.append([x,y])


init()
answer, count = 0, 0
for row in range(1, n+1):
    for col in range(1, m+1):
        if dfs(row, col):
            answer = max(answer, count)
            count = 0
print(answer)


