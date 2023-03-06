import sys

sys.setrecursionlimit(10**8)

# d l r u
# 하 좌 우 상, 알파벳 순으로 탐색
# 탐색 성공하면 최단 거리
# answer는 사전순으로 제일 끝에 것을 초기값으로
dX = [1, 0, 0, -1]
dY = [0, -1, 1, 0]
letter = ['d', 'l', 'r', 'u']
answer = "z"


def dfs(n, m, x, y, r, c, path, depth, k):
    global answer
    # k < 앞으로 가야할 거리 + 지금까지 온 거리이면 return
    if k < depth + abs(x - r) + abs(y - c):
        return
    # 무사히 도착
    if x == r and y == c and depth == k:
        answer = path
        return
    for i in range(4):
        nX = x + dX[i]
        nY = y + dY[i]
        # 미로 안이고 answer보다 사전 순으로 앞이면 dfs
        if 0 <= nX < n and 0 <= nY < m and path < answer:
            dfs(n, m, nX, nY, r, c, path+letter[i], depth + 1, k)


def solution(n, m, x, y, r, c, k):
    distance = abs(x - r) + abs(y - c)
    if distance > k or (k - distance) % 2 == 1:
        return "impossible"

    dfs(n, m, x-1, y-1, r-1, c-1, "", 0, k)

    return answer
