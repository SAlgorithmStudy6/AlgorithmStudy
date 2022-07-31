N = int(input())

matrix = []

for i in range(N):
    matrix.append(list(map(int, input().split())))

visited = [[False] * N for _ in range(N)]


def dfs(x, y):
    if x >= N or y >= N:
        return

    nowJelly = matrix[y][x]
    if nowJelly == -1:
        print("HaruHaru")
        exit()

    if not visited[y][x]:
        visited[y][x] = True
        dfs(x + nowJelly, y)
        dfs(x, y + nowJelly)


dfs(0, 0)
print("Hing")
