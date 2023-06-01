import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n = int(input())
numberList = list(map(int, input().split()))
dp = [1 for _ in range(n)]

for i in range(1, n):
    for j in range(0, i):
        # j가 i보다 작으면 1 추가
        if numberList[j] < numberList[i]:
            dp[i] = max(dp[i], dp[j] + 1)

print(max(dp))
