# import sys; input = sys.stdin.readline
import sys; sys.stdin = open("input.txt")
n= int(input())
n_list = [list(map(int, input().split())) for _ in range(n)]

dp = [0] * (n+1)

for i in range(n-1, -1, -1):
    d, p  = n_list[i][0], n_list[i][1]
    if d + i <= n:
        dp[i] = max(p + dp[d+i], dp[i+1])
    else:
        dp[i] = dp[i+1]

print(dp[0])

