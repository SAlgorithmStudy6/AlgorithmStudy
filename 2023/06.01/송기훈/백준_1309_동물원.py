import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n = int(input())

# 1차원 배열로 줄이기
dp = [0 for _ in range(n + 1)]
dp[0] = 1
dp[1] = 3

for i in range(2, n+1):
    # for문을 돌 때 % 연산을 안하니까 메모리 초과
    dp[i] = (dp[i - 2] + dp[i - 1] * 2) % 9901

print(dp[n])


""" 메모리 초과
# 안 넣기, 왼쪽, 오른쪽
dp = [[0, 0] for _ in range(n+1)]
dp[1] = [1, 1]

# 안 넣기 = 안 넣기 + 왼쪽 + 오른쪽
# 왼쪽 = 안 넣기 + 오른쪽
# 오른쪽 = 안 넣기 + 왼쪽

dp = [[0, 0, 0] for _ in range(n+1)]
dp[1] = [1, 1, 1]

for i in range(2, n + 1):
    dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]
    dp[i][1] = dp[i - 1][0] + dp[i - 1][2]
    dp[i][2] = dp[i - 1][0] + dp[i - 1][1]
"""
