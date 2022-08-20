# 6.3점
# import itertools

# n = 8
# lighthouse = [[1, 2], [1, 3], [1, 4], [1, 5], [5, 6], [5, 7], [5, 8]]

n = 10
lighthouse = [[4, 1], [5, 1], [5, 6], [7, 6], [1, 2], [1, 3], [6, 8], [2, 9], [9, 10]]

# def solution(n, lighthouse):
#     node = [[] for _ in range(n + 1)]
#     numbers = [i for i in range(1, n + 1)]
#     for light in lighthouse:
#         node[light[0]].append(light[1])
#         node[light[1]].append(light[0])

#     for i in range(1, n + 1):
#         combiList = [list(x) for x in itertools.combinations(numbers, i)]
#         for combi in combiList:
#             temp = []
#             for x in combi:
#                 temp.append(x)
#                 temp += node[x]
#             temp = set(temp)
#             temp = list(temp)
#             if temp == numbers:
#                 return i
#     return n


import sys


def solution(n, lighthouse):
    sys.setrecursionlimit(10**9)
    node = [[] for _ in range(n + 1)]
    numbers = [i for i in range(1, n + 1)]
    visited = [False for _ in range(n + 1)]
    global result
    result = []
    answer = 0

    for light in lighthouse:
        node[light[0]].append(light[1])
        node[light[1]].append(light[0])

    def dfs(depth, temp, index):
        global result
        if depth == n:
            check = []
            for j in temp:
                # 자기 자신을 켜주고 포함된 연결된 노드도 켜줌
                check += [j]
                check += node[j]
            check = set(check)
            check = list(check)
            if check == numbers:
                result.append(len(temp))
                return
            return

        for i in range(index, n + 1):
            # temp 리스트에 포함시키는 경우
            visited[i] = True
            temp.append(i)
            dfs(depth + 1, temp, i + 1)

            # 포함 안 시키는 경우 / 원상복구
            visited[i] = False
            temp.pop()
            dfs(depth + 1, temp, i + 1)

    dfs(0, [], 1)

    if not result:
        answer = n
    else:
        answer = min(result)
    return answer


print(solution(n, lighthouse))
