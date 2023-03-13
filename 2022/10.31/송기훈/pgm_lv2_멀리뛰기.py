import math


def solution(n):
    count1 = 0
    count2 = n // 2
    answer = 0

    for i in range(count2 + 1):
        count1 = n - i * 2
        answer += math.factorial(count1 + i) // (math.factorial(count1) * math.factorial(i))

    return answer % 1234567


"""
def solution(n):
    count1 = 0
    count2 = n // 2
    answer = 0

    for i in range(count2+1):
        count1 = n - i * 2
        answer += factorial(count1+i) // (factorial(count1) * factorial(i))

    return answer % 1234567

def factorial(x):
    if x > 1:
        return x * factorial(x-1)
    else:
        return 1
"""
