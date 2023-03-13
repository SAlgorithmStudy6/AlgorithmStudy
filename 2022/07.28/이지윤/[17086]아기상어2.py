from collections import deque


dx = [0, 1, 1, 1, 0, -1, -1, -1] # 가능한 모든 방향 : 8방향
dy = [-1, -1, 0, 1, 1, 1, 0, -1] # 가능한 모든 방향 : 8방향
search_order = deque()


def bfs(_n, _m, _visited):
    while search_order:                     # 종료 조건 : 더 이상 방문할 곳이 없는 경우. (즉, 모든 곳을 방문한 경우)
        x, y = search_order.popleft()
        for i in range(8):
            nx, ny = x + dx[i], y + dy[i]   # 이동할 방향 생성
            if nx < 0 or nx >= _n or ny < 0 or ny >= _m: continue   # 배열을 벗어 나면 X.
            if _visited[nx][ny] == 0:                               # 상어도 방문한 적도 없을 때
                _visited[nx][ny] = _visited[x][y] + 1
                search_order.append([nx, ny])


def solution(_n, _m, _inputs) -> int:
    for x in range(_n):
        for y in range(_m):
            if _inputs[x][y] :
                search_order.append([x,y])
    bfs(_n,_m, _inputs)
    max_val = max(map(max, _inputs))
    return max_val -1


def main():
    n, m = map(int, input().split())
    input_list = [list(map(int, input().split())) for i in range(n)]
    answer = solution(n, m, input_list)
    print(answer)


# -- 실행 함수 ------------------------------------------------------------------------------------
if __name__ == '__main__':
    main()