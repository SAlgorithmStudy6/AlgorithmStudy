from itertools import combinations

def solution(number):
    answer = 0
    for three_students in combinations(number, 3):
        if sum(three_students) == 0 : answer+=1
    return answer