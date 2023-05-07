import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n, m = map(int, input().split())
matrix = []

for _ in range(n):
    matrix.append(list(map(int, list(input()))))

k = int(input())

# 모든 열이 켜져 있는 최대 행의 수
# 행의 패턴이 같다 = 같이 다 켜짐
# 꺼진 전등 -> 홀수번 = 켜짐, 짝수번 = 꺼짐
# 한 행을 기준으로 불을 다 켰을 때, 다 켜진 곳은 어디인가??

count = [0] * n

if k % 2 == 1:
    for i in range(n):
        zero = matrix[i].count(0)
        print(i, zero)
        if zero % 2 == 1 and zero <= k:
            for j in range(n):
                if matrix[i] == matrix[j]:
                    count[i] += 1
else:
    for i in range(n):
        zero = matrix[i].count(0)
        if zero % 2 == 0 and zero <= k:
            for j in range(n):
                if matrix[i] == matrix[j]:
                    count[i] += 1

print(max(count))
