# 모든 경우를 다 탐색할 필요 있는가? X.
# 만약 목표 지점에 도달하지 못한다면 다 탐색해보긴 해야 하지만, 그냥 이미 목표 지점에 도달했다면 더 이상 돌 필요 X.
# 따라서 BFS + 재귀 구현시 , exit으로 해당 상황 가지치기 해주기.
# => 최대, 최소가 아닌 되냐 안되냐, 즉 Yes or No이므로 global 변수가 필요 없다.

#------------------------------------------------------------------------------------------------
dx = [1, 0]
dy = [0, 1]
direction = len(dx)
visited = []
n_list = []

def dfs(_x, _y, _n) :
    if _x < 0 or _x >= _n or  _y < 0 or _y >= _n: return
    if visited[_x][_y] == 1 : return
    if n_list[_x][_y] == 0 : return
    if n_list[_x][_y] == -1 :
        print('HaruHaru')
        exit()
    visited[_x][_y] = 1
    value = n_list[_x][_y]
    for i in range(direction):
        nx = _x + dx[i]*value
        ny = _y + dy[i]*value
        dfs(nx, ny, _n)
    return


def main():
    n = int(input())
    for i in range(n):
        n_list.append(list(map(int, input().split())))
        visited.append([0]*n)
    dfs(0, 0, n)
    print('Hing')


# -- 실행 함수 ------------------------------------------------------------------------------------
if __name__ == '__main__':
    main()