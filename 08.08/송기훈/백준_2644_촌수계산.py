import sys

input = sys.stdin.readline

# 전체사람 수 n
n = int(input())
# 찾을 관계 a, b
a, b = map(int, input().split())
# 관계 수
m = int(input())

arr = [[] for _ in range(n + 1)]
visited = [0] * (n + 1)

# x가 부모, y가 자식
for _ in range(m):
    x, y = map(int, input().split())
    arr[x].append(y)
    arr[y].append(x)


def dfs(first, index):
    visited[first] = 1

    if first == b:
        print(index)
        exit()

    for i in arr[first]:
        if visited[i] == 0:
            dfs(i, index + 1)


dfs(a, 0)
print(-1)

# 11725 트리의 부모찾기 참고
