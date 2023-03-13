import sys

sys.stdin = open("09.19/요리사.txt", "r", encoding="UTF-8")

T = int(input())

def combination(target, arr):
    result = []
    
    def nowCombi(idx, nowPick):
        if len(nowPick) == target:
            result.append(nowPick)
        for i in range(idx, len(arr)):
            nowCombi(i+1, nowPick + [arr[i]])

    nowCombi(0, [])
    return result


def check(teamA, teamB):
    global aCombi, bCombi
    aCombi = combination(2, teamA)
    bCombi = combination(2, teamB)

    sumA, sumB = 0, 0
    for element in aCombi:
        i, j = element[0], element[1]
        sumA += matrix[i][j] + matrix[j][i]

    for element in bCombi:
        i, j = element[0], element[1]
        sumB += matrix[i][j] + matrix[j][i]

    return abs(sumA - sumB)

for test_case in range(1, T+1):
    n = int(input())
    matrix = [list(map(int, input().split())) for _ in range(n)]
    numbers = [i for i in range(n)]
    
    combiList = combination(n//2, numbers)
    idx = 0
    minDiff = 10**9
    halfCombi = len(combiList) // 2
    for combi in combiList:
        if idx == halfCombi:
            break
            
        otherCombi = [x for x in numbers if x not in combi]
        diff = check(combi, otherCombi)

        minDiff = min(diff, minDiff)

        idx += 1
    
    print("#{} {}".format(test_case, minDiff))
