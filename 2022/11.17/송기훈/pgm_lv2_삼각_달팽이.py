def solution(n):
    answer = []
    triangle = [[0 for _ in range(n)] for _ in range(n)]

    y, x = -1, 0
    number = 1

    for i in range(n):
        for j in range(i, n):
            # 아래
            if i % 3 == 0:
                y += 1
            # 오른쪽
            elif i % 3 == 1:
                x += 1
            # 위
            elif i % 3 == 2:
                y -= 1
                x -= 1
            triangle[y][x] = number
            number += 1

    for tri in triangle:
        for t in tri:
            if t != 0:
                answer.append(t)

    return answer
