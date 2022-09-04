# import sys; sys.stdin = open("input.txt")
from collections import deque

move = [(0, -1), (0, 1), (-1, 0), (1, 0)]
moves = [[0, 0], [(0, -1), (0, 1), (-1, 0), (1, 0)], [(0, -1), (0, 1)],
        [(-1, 0), (1, 0)], [(0, -1), (1, 0)], [(0, 1), (1, 0)], 
        [(0, 1), (-1, 0)], [(0, -1),(-1, 0)]]

def count_visited_area(_arr) -> int:
    count = 0
    for y in range(n):
        for x in range(m):
            if _arr[y][x] : count+=1
    return count

def is_available(_delta, _next_pipe) -> bool :
    if _delta == move[0] and _next_pipe in [1, 2, 5, 6]: return True
    if _delta == move[1] and _next_pipe in [1, 2, 4, 7]: return True
    if _delta == move[2] and _next_pipe in [1, 3, 4, 5]: return True
    if _delta == move[3] and _next_pipe in [1, 3, 6, 7]: return True
    return False

def bfs(_start_x, _start_y, _time) -> int:
    visited = [[0]*m for _ in range(n)]
    queue = deque()
    queue.append((c, r)); visited[r][c] = 1
    while queue:
        present = queue.popleft()
        present_pipe_type = arr[present[1]][present[0]]
        for delta in moves[present_pipe_type]:
            next_x, next_y = present[0]+delta[0], present[1]+delta[1]
            if visited[present[1]][present[0]] == _time: continue
            if 0<=next_x<m and 0<=next_y<n and not visited[next_y][next_x] and arr[next_y][next_x]!=0:
                if is_available(delta, arr[next_y][next_x]) :
                    queue.append((next_x, next_y))
                    visited[next_y][next_x] = visited[present[1]][present[0]] + 1
    return count_visited_area(visited)

    

T = int(input())
for t in range(1, T+1):
    n,m,r,c,l = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]
    answer = bfs(c, r, l)
    print(f"#{t} {answer}")
