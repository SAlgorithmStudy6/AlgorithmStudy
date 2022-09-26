import sys

sys.stdin = open("09.26/줄기세포배양.txt", "r", encoding="UTF-8")

T = int(input())

# 상 하 좌 우
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

for test_case in range(1, T+1):
    n, m, k = map(int, input().split())
    inputList = [list(map(int, input().split())) for _ in range(n)]

    matrix = [[0]*(m+k*2) for _ in range(n+k*2)]

    cells = []
    for y in range(n):
        for x in range(m):
            # 값이 있다면 matrix 중간으로 이동, 세포 위치 저장
            life = inputList[y][x]
            if life > 0:
                # [생명력 카운터 , 활성화 카운터]
                matrix[y+k][x+k] = [life, life]
                cells.append([y+k, x+k])

    for i in range(k):
        newCells = []
        for cell in cells:
            y, x = cell[0], cell[1]
            # 카운트 다운
            if matrix[y][x][1] > 0:
                matrix[y][x][1] -= 1
            # 활성화
            elif matrix[y][x][1] == 0:
                life = matrix[y][x][0]
                for i in range(4):
                    nY = y + dy[i]
                    nX = x + dx[i]
                    # 아무도 안 건든 칸이면 세포 추가
                    if matrix[nY][nX] == 0:
                        newCells.append([nY, nX, life])
                matrix[y][x][0] -= 1
                matrix[y][x][1] -= 1
            # 생명력 줄이기
            else:
                if matrix[y][x][0] > 0:
                    matrix[y][x][0] -= 1

        # 새로 생긴 세포 순회
        for newCell in newCells:
            y, x, life = newCell
            # 아무도 안 건든 칸이면 세포 추가
            if matrix[y][x] == 0:
                matrix[y][x] = [life, life]
                cells.append([y, x])
            # 이미 건든 칸이면 값을 비교하고 큰 값이 들어간다.
            else:
                if matrix[y][x][0] < life:
                    matrix[y][x] = [life, life]

    result = 0
    for y in range(n+k*2):
        for x in range(m+k*2):
            if matrix[y][x] != 0 and matrix[y][x][0] > 0:
                result += 1

    print("#{} {}".format(test_case, result))





