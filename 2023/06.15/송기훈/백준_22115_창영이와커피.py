import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n, k = map(int, input().split())

c_list = [0] + list(map(int, input().split()))

dp = [[0 for _ in range(k+1)] for _ in range(n+1)]

for i in range(1, k+1):
    dp[0][i] = float('inf')


for idx in range(1, n+1):
    now_caffeine = c_list[idx]
    for caffeine in range(1, k+1):
        # 현재 커피의 카페인이 dp-카페인보다 크면 이전 값으로 대체
        if caffeine < now_caffeine:
            dp[idx][caffeine] = dp[idx-1][caffeine]
        else:
            dp[idx][caffeine] = min(
                dp[idx-1][caffeine], dp[idx-1][caffeine - now_caffeine] + 1)

if dp[n][k] == float('inf'):
    print(-1)
else:
    print(dp[n][k])

"""
1. c_list에 [0]을 추가한 것
- dp 2차원 배열에서 0개를 골랐다는 개념을 위해서 필요하다.

2. dp 2차원 배열은 커피 n개를 골라서 k의 카페인을 만족하는 커피의 수

3. dp[0]에서...
- dp[0][0]은 커피 0개를 골라서 카페인이 0인 경우는 커피 "0"개를 먹으면 되니까 0으로 세팅
- dp[0][1:k+1]은 커피 0개를 골라서 카페인이 i인 경우는 불가능하므로 inf로 세팅
"""
