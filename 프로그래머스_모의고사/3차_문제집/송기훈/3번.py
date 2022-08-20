distance = 12
scope = [[7, 8], [4, 6], [11, 10]]
times = [[2, 2], [2, 4], [3, 3]]

# distance = 10
# scope = [[3, 4], [5, 8]]
# times = [[2, 5], [4, 3]]

# 14.3점 시간초과
def solution(distance, scope, times):
    guardNum = len(scope)
    guard = [[] for _ in range(guardNum)]
    scopeRange = [[] for _ in range(guardNum)]
    result = []

    for i in range(guardNum):
        scope[i].sort()
        scopeRange[i] += [x for x in range(scope[i][0], scope[i][1] + 1)]
        guard[i] += [x for x in range(1, times[i][0] + 1)]
        tempGuard = guard[i][:]
        for g in tempGuard:
            j = 1
            while j * (times[i][0] + times[i][1]) + g <= distance:
                guard[i].append(j * (times[i][0] + times[i][1]) + g)
                j += 1
        for s in scopeRange[i]:
            if s in guard[i]:
                result.append(s)
                break

    if not result:
        answer = distance
    else:
        answer = min(result)
    return answer


# 35.7점
def solution(distance, scope, times):
    for i in range(len(scope)):
        minScope = min(scope[i])
        maxScope = max(scope[i])

        loop = times[i][0] + times[i][1]
        loopCount = minScope // loop
        startScope = minScope - loop * loopCount
        endScope = maxScope - loop * loopCount

        if startScope <= times[i][0]:
            return minScope
        if endScope > loop:
            return maxScope

    return distance


print(solution(distance, scope, times))
