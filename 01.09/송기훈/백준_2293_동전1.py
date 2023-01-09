import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n, k = map(int, input().split())
coin = []
# dp[i]는 i원을 만드는 경우의 수
dp = [0 for _ in range(k+1)]
dp[0] = 1

for _ in range(n):
    coin.append(int(input()))
coin.sort()

for c in coin:
    # i원이 c원보다 크다면
    for i in range(k+1):
        if i >= c:
            dp[i] += dp[i-c]

print(dp[k])

# 메모리 초과
# def dfs(index, money):
#     global count
#     if index == n:
#         if money == 0:
#             count += 1
#         return

#     nowCoin = coin[index]
#     divisor = money // nowCoin

#     for i in range(divisor, -1, -1):
#         moneyLeft = money - nowCoin * i
#         if moneyLeft >= 0:
#             dfs(index+1, moneyLeft)


# n, k = map(int, input().split())
# coin = []
# count = 0
# for _ in range(n):
#     coin.append(int(input()))
# coin.sort(reverse=True)

# dfs(0, k)

# print(count)
