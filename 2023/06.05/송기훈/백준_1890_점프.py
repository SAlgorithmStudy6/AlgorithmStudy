import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n = int(input())

# 입력, 도착할 수 있는 방법의 수
matrix = []
dp = [[0 for _ in range(n)] for _ in range(n)]

for _ in range(n):
    matrix.append(list(map(int, input().split())))

dp[0][0] = 1

for i in range(n):
    for j in range(n):
        if matrix[i][j] != 0 and dp[i][j] != 0:
            # 이동 시키기
            if matrix[i][j] + i < n:
                dp[i + matrix[i][j]][j] += dp[i][j]
            if matrix[i][j] + j < n:
                dp[i][j + matrix[i][j]] += dp[i][j]

print(dp[n-1][n-1])
