import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n, k = map(int, input().split())

"""
6 4
6 = (0 + (6을 4분해)) + (1 + (5를 3분해)) + ... ((6을 4분해) + 0)
"""

dp = [[0 for _ in range(n+1)] for _ in range(k+1)]

dp[0][0] = 1

for i in range(1, k+1):
    for j in range(0, n+1):
        dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000

print(dp[k][n])
