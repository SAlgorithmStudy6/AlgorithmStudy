import sys

sys.stdin = open("C:/SSAFY/clone/Algorithm_SSAFY/input.txt",
                 "r", encoding="utf-8")

input = sys.stdin.readline

n = int(input())
matrix = [list(map(int, input().split())) for _ in range(n)]
visited = [False for _ in range(n)]
half = n // 2
minValue = 999999999


def dfs(depth, idx):
    global minValue

    if depth == half:
        totalA, totalB = 0, 0
        for i in range(n):
            for j in range(n):
                if visited[i] and visited[j]:
                    totalA += matrix[i][j]
                elif not visited[i] and not visited[j]:
                    totalB += matrix[i][j]
        temp = abs(totalA - totalB)
        if temp < minValue:
            minValue = temp

    for i in range(idx, n):
        visited[i] = True
        dfs(depth + 1, i + 1)
        visited[i] = False


dfs(0, 0)

print(minValue)
