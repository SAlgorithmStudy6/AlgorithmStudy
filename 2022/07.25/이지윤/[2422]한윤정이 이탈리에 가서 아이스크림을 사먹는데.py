import itertools

def solution(_n, _m, _arr) -> int :
    count = 0
    for combs in itertools.combinations(range(1, _n+1), 3):
        flag = 0
        for badComb in _arr:
            if badComb[0] in combs and badComb[1] in combs:
                flag = 1
                break
        if flag: continue
        else : count += 1
    return count

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for i in range(m)]
answer = solution(n, m, arr)
print(answer)