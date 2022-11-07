import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

dy = [0, -1, 0, 1]
dx = [1, 0, -1, 0]

# 전 세대 + (전 세대 뒤집기+1), generation 만들기
def makeGen(d, g):
    generation = [d]
    for _ in range(g):
        temp = []
        for i in range(len(generation)):
            temp.append((generation[-i -1] + 1) % 4)
        generation += temp
    return generation

n = int(input())
result = 0
matrix = [[0] * 101 for _ in range(101)]

for _ in range(n):
    x, y, d, g = map(int, input().split())
    matrix[x][y] = 1

    moveGen = makeGen(d, g)

    for move in moveGen:
        nY = y + dy[move]
        nX = x + dx[move]
        matrix[nX][nY] = 1
        y, x = nY, nX

for i in range(100):
    for j in range(100):
        if (matrix[i][j] == 1
        and matrix[i+1][j] == 1
        and matrix[i][j+1] == 1
        and matrix[i+1][j+1] == 1):
            result += 1

print(result)
