import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

T = int(input())

dy = [-1, 1, 1, -1]
dx = [1, 1, -1, -1]


def makeDiamond(y, x, dir, path, dessert):
    global diamonds, desserts
    nY = y + dy[dir]
    nX = x + dx[dir]
    # 탈출 조건
    if dir == 3 and path[0] == (nY, nX):
        if len(diamonds) < len(path):
            diamonds = path[:]
            desserts = dessert[:]
    # 직진
    if 0 <= nY < n and 0 <= nX < n:
        if matrix[nY][nX] not in dessert:
            makeDiamond(nY, nX, dir, path + [(nY, nX)], dessert + [matrix[nY][nX]])

    # 방향 전환
    if dir < 3:
        nY = y + dy[dir + 1]
        nX = x + dx[dir + 1]
        if 0 <= nY < n and 0 <= nX < n:
            # 탈출 조건
            if dir == 2 and path[0] == (nY, nX):
                if len(diamonds) < len(path):
                    diamonds = path[:]
                    desserts = dessert[:]
            else:
                if matrix[nY][nX] not in dessert:
                    makeDiamond(nY, nX, dir + 1, path + [(nY, nX)], dessert + [matrix[nY][nX]])


for test_case in range(1, T+1):
    n = int(input())
    matrix = [list(map(int, input().split())) for _ in range(n)]
    diamonds = []
    desserts = []
    for y in range(n):
        for x in range(n):
            makeDiamond(y, x, 0, [(y, x)], [matrix[y][x]])

    if not desserts:
        print("#{} -1".format(test_case))
    else:
        print("#{} {}".format(test_case, len(desserts)))


