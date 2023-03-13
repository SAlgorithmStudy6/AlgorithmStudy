import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

r, c, t = map(int, input().split())
matrix = []
cleaner = []


for y in range(r):
    temp = list(map(int, input().split()))
    for x in range(c):
        if (temp[x] == -1):
            cleaner.append([y, x])
    matrix.append(temp)


def dust():
    dust = [[0] * c for _ in range(r)]
    # 상 하 좌 우
    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]

    for y in range(r):
        for x in range(c):
            if matrix[y][x] > 0:
                count = 0
                for i in range(4):
                    nY = y + dy[i]
                    nX = x + dx[i]
                    if 0 <= nY < r and 0 <= nX < c and [nY, nX] not in cleaner:
                        count += 1
                        dust[nY][nX] += matrix[y][x] // 5
                matrix[y][x] -= (matrix[y][x] // 5 * count)

    for y in range(r):
        for x in range(c):
            matrix[y][x] += dust[y][x]


def counterClockwise():
    # 우 상 좌 하
    dy = [0, -1, 0, 1]
    dx = [1, 0, -1, 0]

    delta = 0
    startY, startX = cleaner[0]
    y = startY
    x = 1
    cleanAir = 0
    while True:
        nY = y + dy[delta]
        nX = x + dx[delta]
        if y == startY and x == startX:
            break
        if nY >= r or nY < 0 or nX >= c or nX < 0:
            delta += 1
            continue
        matrix[y][x], cleanAir = cleanAir, matrix[y][x]
        y, x = nY, nX
    return


def clockwise():
    # 우 하 좌 상
    dy = [0, 1, 0, -1]
    dx = [1, 0, -1, 0]
    delta = 0
    startY, startX = cleaner[1]
    y = startY
    x = 1
    cleanAir = 0
    while True:
        nY = y + dy[delta]
        nX = x + dx[delta]
        if y == startY and x == startX:
            break
        if nY >= r or nY < 0 or nX >= c or nX < 0:
            delta += 1
            continue
        matrix[y][x], cleanAir = cleanAir, matrix[y][x]
        y, x = nY, nX
    return


for _ in range(t):
    dust()
    counterClockwise()
    clockwise()


result = 0
for y in range(r):
    for x in range(c):
        if matrix[y][x] > 0:
            result += matrix[y][x]


print(result)
