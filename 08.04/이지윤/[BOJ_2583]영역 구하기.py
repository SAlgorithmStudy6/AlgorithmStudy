from collections import deque

m,n,k = map(int,input().split())

graph = [[0]*n for i in range(m)]

for _ in range(k):
    x1,y1,x2,y2= map(int,input().split())
    for i in range(y1,y2):
        for j in range(x1,x2):
            graph[i][j] = 1

move = [(-1, 0), (1,0), (0,1), (0,-1)] # 좌 우 상 하


def bfs(_y,_x):
    queue = deque()
    queue.append((_y,_x))
    graph[_y][_x]=1 # 다시 방문하지 않기 위해 1로 변경
    cnt=1
    while queue :
        current = queue.popleft()
        cx,cy = current[0],current[1]
        for dx, dy in move:
            nx = cx+dx
            ny = cy+dy
            if (0 <= nx < m) and (0 <= ny < n):
                if graph[nx][ny] == 0:
                    graph[nx][ny] = 1
                    queue.append((nx, ny))
                    cnt += 1
    return cnt

area = 0 # 영역의 개수
cnts = [] # 영역의 넓이
for y in range(m):
    for x in range(n):
        if graph[y][x]==0:
            cnts.append(bfs(y,x))
            area+=1
print(area)
cnts.sort()
print(*cnts)