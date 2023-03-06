import sys

sys.setrecursionlimit(10**8)

# d l r u
# 하 좌 우 상, 알파벳 순으로 탐색
# 탐색 성공하면 최단 거리
dX = [1, 0, 0, -1]
dY = [0, -1, 1, 0]
letter = ["d", "l", "r", "u"]


def solution(n, m, x, y, r, c, k):
    global answer

    answer = "impossible"

    distance = abs(x - r) + abs(y - c)
    if distance > k or (k - distance) % 2 == 1:
        return "impossible"

    def dfs(n, m, x, y, r, c, path, depth, k):
        global answer, dX, dY, letter
        # answer가 값이 있거나 depth가 k보다 크면 return
        if answer != "impossible" or depth > k:
            return
        # k < 앞으로 가야할 거리 + 지금까지 온 거리이면 return
        if k < depth + abs(x - r) + abs(y - c):
            return

        if x == r and y == c and depth == k:
            answer = path
            return

        for i in range(4):
            nX = x + dX[i]
            nY = y + dY[i]
            if (0 <= nX < n and 0 <= nY < m):
                dfs(n, m, nX, nY, r, c, path + letter[i], depth + 1, k)

    dfs(n, m, x-1, y-1, r-1, c-1, "", 0, k)

    return answer
