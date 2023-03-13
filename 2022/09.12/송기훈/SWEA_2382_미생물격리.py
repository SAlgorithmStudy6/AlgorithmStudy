import sys

sys.stdin = open("09.12/미생물격리.txt", "r", encoding="UTF-8")

T = int(input())

delta = [[0, 0], [-1, 0], [1, 0], [0, -1], [0, 1]]

for test_case in range(1, T+1):
    n, m, k = map(int, input().split())
    virus = []
    for _ in range(k):
        y, x, number, dir = map(int, input().split())
        virus.append([y, x, number, dir])
    t = 0
    while t < m:
        cleanData = [[[0, 0, 0] for _ in range(n)] for _ in range(n)]
        for i in range(k):
            # 합쳐진 값은 패스
            if virus[i][0] == -1:
                continue
            y, x, number, dir = virus[i]
            # 다음 좌표로 이동
            nY = y + delta[dir][0]
            nX = x + delta[dir][1]
            # 가장자리일 때 처리
            if nY == 0 or nX == 0 or nY == n-1 or nX == n-1:
                if dir == 1:
                    dir = 2
                elif dir == 2:
                    dir = 1
                elif dir == 3:
                    dir = 4
                elif dir == 4:
                    dir = 3
                number = number // 2
            # 가야할 곳에 뭔가 있다
            if cleanData[nY][nX] != [0, 0, 0]:
                # cleanData에 있던게 더 크면 값을 virus 배열에 전해주고 내 것을 삭제
                if cleanData[nY][nX][0] > number:
                    index = cleanData[nY][nX][2]
                    virus[index][2] += number
                    nY, nX, number, dir = -1, -1, -1, -1
                # 내 것이 더 크면 같은 데이터 찾아서 삭제, 인덱스 덮어쓰기
                # cleanData에는 내 값을 비교용으로 남겨두고
                # number에서 미생물 수 합치기
                else:
                    deleteIndex = cleanData[nY][nX][2]
                    oldNumber = virus[deleteIndex][2]
                    virus[deleteIndex] = [-1, -1, -1, -1]
                    cleanData[nY][nX][0] = number
                    cleanData[nY][nX][1] = dir
                    cleanData[nY][nX][2] = i
                    number += oldNumber
            else:
                cleanData[nY][nX] = [number, dir, i]
            # virus 배열 최신화
            virus[i] = [nY, nX, number, dir]
        t += 1

    # 결과값 출력
    result = 0
    for i in range(k):
        if virus[i][2] > 0:
            result += virus[i][2]

    print("#{} {}".format(test_case, result))
