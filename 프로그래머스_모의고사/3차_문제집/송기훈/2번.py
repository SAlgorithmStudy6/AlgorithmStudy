# 47.1점, 시간초과
def solution(ingredient):
    # 1:빵 2:야채 3:고기
    # 빵-야채-고기-빵 / 1 2 3 1
    answer = 0
    burger = [1, 2, 3, 1]
    stack = []
    bIdx = 0
    i = 0
    while i < len(ingredient):
        if i >= 3 and ingredient[i] == burger[3]:
            if ingredient[i - 3 : i + 1] == burger[:]:
                answer += 1
                ingredient = ingredient[: i - 3] + ingredient[i + 1 :]
                i = 0
        i += 1
    return answer


# 100점
def solution(ingredient):
    # 1:빵 2:야채 3:고기
    # 빵-야채-고기-빵 / 1 2 3 1
    answer = 0
    burger = [1, 2, 3, 1]
    i = 0
    while i < len(ingredient):
        if i >= 3 and ingredient[i] == burger[3]:
            if ingredient[i - 3 : i + 1] == burger[:]:
                answer += 1
                ingredient.pop(i - 3)
                ingredient.pop(i - 3)
                ingredient.pop(i - 3)
                ingredient.pop(i - 3)
                if i - 5 < 0:
                    i = -1
                else:
                    i -= 5
        i += 1
    return answer
