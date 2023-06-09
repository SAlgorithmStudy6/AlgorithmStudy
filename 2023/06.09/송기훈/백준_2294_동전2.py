import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n, k = map(int, input().split())

coins = []

# 낸 동전의 수를 dp 배열
# 최소값을 구해야 하므로 기본 값은 큰 값으로 세팅
dp = [float('inf') for _ in range(k+1)]

# 0원은 동전 수 0개
dp[0] = 0

for _ in range(n):
    coins.append(int(input()))
coins.sort()

for coin in coins:
    for target in range(coin, k+1):
        # dp[target]과 dp[target-coin]에서 coin을 하나 더한 것을 비교
        dp[target] = min(dp[target], dp[target-coin]+1)

if (dp[k] == float('inf')):
    print(-1)
else:
    print(dp[k])
