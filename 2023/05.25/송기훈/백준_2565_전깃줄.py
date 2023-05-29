import sys

sys.stdin = open("input.txt", "r", encoding="utf-8")

n = int(input())
wires = []
dp = [1 for _ in range(n)]

for _ in range(n):
    a, b = map(int, input().split())
    wires.append([a, b])

# a를 오름차순으로 정렬
wires.sort()

# 연결 조건
# A1 - B1, A2 - B2에서 A1 < A2 이면 B1 < B2 가 성립해야 함
# B에서 증가하는 가장 긴 부분 수열을 구한다
# https://zidarn87.tistory.com/291
# https://zidarn87.tistory.com/285

# for문 2개를 돌면서 해당 조건에 맞는 것들을 찾고 더 해준다
for i in range(1, n):
    for j in range(0, i):
        if wires[j][1] < wires[i][1]:
            dp[i] = max(dp[i], dp[j] + 1)

# 총 전깃줄 수 - 최대 연결 가능한 전깃줄 수
print(n - max(dp))
