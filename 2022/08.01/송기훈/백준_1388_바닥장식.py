garo = '-'
sero = '|'

N, M = map(int, input().split())

matrix = []

for _ in range(N):
    matrix.append(list(input()))

count = 0

for y in range(N):
    for x in range(1, M):
        if matrix[y][x] == garo and matrix[y][x - 1] == garo:
            count += 1

for x in range(M):
    for y in range(1, N):
        if matrix[y][x] == sero and matrix[y - 1][x] == sero:
            count += 1

print(N * M - count)
