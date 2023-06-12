import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n, k = map(int, input().split())

things = [(0, 0)]

for i in range(n):
    w, v = map(int, input().split())
    things.append((w, v))

things = sorted(things, key=lambda x: x[0])

dp = [[0 for _ in range(k+1)] for _ in range(n+1)]

for i in range(1, n+1):
    w, v = things[i]
    for j in range(1, k+1):
        if j < w:
            # 꺼낸 무게가 j보다 크면 이전 물건의 값으로 대체
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-w] + v)

print(dp[n][k])
