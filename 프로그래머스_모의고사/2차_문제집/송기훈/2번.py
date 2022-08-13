# 10점
# def solution(topping):
#     answer = 0

#     for i in range(len(topping)):
#         a = topping[:i]
#         b = topping[i:]

#         setA = set(a)
#         setB = set(b)

#         if len(setA) == len(setB):
#             answer += 1

#     return answer


def solution(topping):
    answer = 0

    a = dict()
    b = dict()

    for t in topping:
        if t not in b:
            b[t] = 1
        else:
            b[t] += 1

    for i in range(len(topping)):
        temp = topping[i]
        b[temp] -= 1

        if temp not in a:
            a[temp] = 1
        else:
            a[temp] += 1

        if b[temp] == 0:
            del b[temp]
        if len(a) == len(b):
            answer += 1

    return answer
