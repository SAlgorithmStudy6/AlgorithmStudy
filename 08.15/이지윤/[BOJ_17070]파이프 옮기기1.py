def dfs(_x, _y, _shape):
    global answer
    if _x==n and _y==n:
        answer+=1
        return
    if _shape==0 or _shape==2:  # 가로 모양으로 회전
        if _x+1<=n and arr[_y][_x+1]==0: dfs(_x+1, _y, 0)
    if _shape==1 or _shape==2:  # 세로 모양으로 회전
        if _y+1<=n and arr[_y+1][_x]==0: dfs(_x, _y+1, 1)
    # 대각선 모양으로 회전
    if _x+1<=n and _y+1<=n and arr[_y][_x+1] == 0  and arr[_y+1][_x]==0 and arr[_y+1][_x+1]==0:
        dfs(_x+1, _y+1, 2)

n = int(input())
arr = [[0]*(n+1)]
for row in range(n):                                    # arr = [list(map(int, input().split())) for _ in range(n)]
    temp_arr = [0]
    temp_arr += list(map(int, input().split()))
    arr.append(temp_arr)

answer = 0
dfs(2, 1, 0)
print(answer)