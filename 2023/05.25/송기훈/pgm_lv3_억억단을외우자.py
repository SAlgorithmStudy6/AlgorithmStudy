# from collections import defaultdict
# import math

"""
# 70점
def solution(e, starts):
    answer = []

    dict = defaultdict(int)
    min_start = min(starts)

    for num in range(min_start, e+1):
        dict[num] = submultiple(num)

    for start in starts:
        temp_key = float('inf')
        temp_value = -1
        for (key, value) in dict.items():
            if key < start:
                continue
            if value > temp_value:
                temp_key = key
                temp_value = value
        answer.append(temp_key)
    return answer
"""

"""
# 80점
def solution(e, starts):
    answer = []

    dict = defaultdict(int)
    min_start = min(starts)

    for num in range(min_start, e+1):
        dict[num] = submultiple(num)


    sorted_dict = sorted(dict.items(), key= lambda x: x[1], reverse=True)

    for start in starts:
        for key, value in sorted_dict:
            if key >= start:
                answer.append(key)
                break
    return answer
"""


"""
def submultiple(num):
    root_num = math.sqrt(num)
    count = 0

    for i in range(1, int(root_num)+1):
        if (num % i == 0):
            count += 2

    if (num % root_num == 0):
        count -= 1

    return count
"""

"""
# 90점 -> 약수 구하는 방식도 느리고 답을 찾는 과정도 느림
from collections import defaultdict

def solution(e, starts):
    answer = []

    dict = defaultdict(lambda: 1)

    dict[1] = 1

    for i in range(2, e+1):
        for j in range(1, e+1):
            if (i * j > e):
                break
            dict[i * j] += 1

    sorted_dict = sorted(dict.items(), key= lambda x: x[1], reverse=True)

    for start in starts:
        for key, value in sorted_dict:
            if (key >= start):
                answer.append(key)
                break

    return answer
"""


def solution(e, starts):
    answer = []
    divisors = [0 for _ in range(e+1)]
    answers = [0 for _ in range(e+1)]
    max_value = 0

    for n in range(1, int(e**0.5)+1):
        divisors[n*n] += 1
        for i in range(n*(n+1), e+1, n):
            divisors[i] += 2

    for i in range(e, -1, -1):
        if divisors[i] >= max_value:
            max_value = divisors[i]
            answers[i] = i
        else:
            answers[i] = answers[i+1]

    for s in starts:
        answer.append(answers[s])

    return answer
