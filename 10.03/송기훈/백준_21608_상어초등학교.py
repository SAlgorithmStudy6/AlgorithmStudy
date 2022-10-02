import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

# 상 하 좌 우
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
resultArr = [0, 1, 10, 100, 1000]

n = int(input())
likeList = [[] for _ in range(n*n + 1)]
order = [0]
matrix = [[[] for _ in range(n)] for _ in range(n)]

for _ in range(n*n):
    like = list(map(int, input().split()))
    order.append(like[0])
    likeList[like[0]] = like[1:]


# 첫번째 학생은 항상 1, 1
matrix[1][1] = order[1]

# 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
# 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
# 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로,
#    그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.

for i in range(2, n*n+1):
    student = order[i]
    like = likeList[student]
    candidate = []
    maxCount = -1
    # matrix을 돌며 상하좌우에 좋아하는 학생이 있는지 탐색
    for y in range(n):
        for x in range(n):
            if matrix[y][x] != []:
                continue
            count = 0
            for i in range(4):
                nY = y + dy[i]
                nX = x + dx[i]
                if 0 <= nY < n and 0 <= nX < n:
                    if matrix[nY][nX] in like:
                        count += 1
            # count가 크면 maxCount를 갱신하고 candidate 초기화
            if count > maxCount:
                candidate = [[y, x]]
                maxCount = count
            # count가 maxCount와 같으면 candidate에 추가
            elif count == maxCount:
                candidate.append([y, x])
    # candidate에 빈 자리 갯수 추가하고 sort
    if candidate:
        newCandidate = []
        for c in candidate:
            y, x = c[0], c[1]
            count = 0
            for i in range(4):
                nY = y + dy[i]
                nX = x + dx[i]
                if 0 <= nY < n and 0 <= nX < n:
                    if matrix[nY][nX] == []:
                        count += 1
            newCandidate.append([c[0], c[1], count])
        newCandidate = sorted(newCandidate, key=lambda x: [-x[2], x[0], x[1]])
        matrix[newCandidate[0][0]][newCandidate[0][1]] = student
    # 없다면 제일 처음 만나는 빈 자리를 선택
    else:
        for y in range(n):
            for x in range(n):
                if matrix[y][x] != []:
                    matrix[y][x] = student
                    break


# 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000이다
result = 0
for y in range(n):
    for x in range(n):
        student = matrix[y][x]
        like = likeList[student]
        count = 0
        for i in range(4):
            nY = y + dy[i]
            nX = x + dx[i]
            if 0 <= nY < n and 0 <= nX < n:
                if matrix[nY][nX] in like:
                    count += 1

        result += resultArr[count]

print(result)
