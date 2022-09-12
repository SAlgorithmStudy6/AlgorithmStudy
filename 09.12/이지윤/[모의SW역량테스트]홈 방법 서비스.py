import sys; sys.stdin = open("input.txt")
from collections import deque
move = [(1,0), (0,1), (-1,0), (0,-1)]

def bfs(_depth, _center, _cost) -> int:
    queue = deque(); visited = [[0]*n for _ in range(n)]
    queue.append(_center); visited[_center[1]][_center[0]] = 1
    count = 0
    while queue:
        present = queue.popleft()
        if arr_home[present[1]][present[0]] : count +=1
        for dx, dy in move:
            next_x, next_y = present[0]+dx, present[1]+dy
            if 0<=next_x<n and 0<=next_y<n and not visited[next_y][next_x]:
                if visited[present[1]][present[0]]+1>_depth:continue
                queue.append((next_x, next_y))
                visited[next_y][next_x] = visited[present[1]][present[0]] + 1
    return count if m*count-_cost>=0 else -1

def solution():
    global answer
    k = 1
    while True:
        cost = k*k + (k-1)*(k-1)
        if cost > count_home*m: break
        for y in range(n):
            for x in range(n):
                center = (x, y)
                count = bfs(k, center, cost)
                if count>=0: answer = max(answer, count)
        k+=1
    return

T = int(input())
for t in range(1,T+1):
    n, m = map(int, input().split())
    arr_home = [] ; count_home = 0
    for row_index in range(n):
        row = list(map(int, input().split()))
        for col in row:
            if col : count_home+=1
        arr_home.append(row)
    answer = 0
    solution()
    print(f"#{t} {answer}")