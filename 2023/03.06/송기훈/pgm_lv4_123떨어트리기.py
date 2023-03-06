# https://magentino.tistory.com/74

from collections import defaultdict
import sys

sys.setrecursionlimit(10**8)


def solution(edges, target):
    edgeDict = defaultdict(list)  # [연결된 모든 노드들]
    treeDict = defaultdict(list)  # [부모노드, [자식노드들]]
    visited = [False for _ in range(len(target))]
    visited[0] = True

    for a, b in edges:
        edgeDict[a-1].append(b-1)
        edgeDict[b-1].append(a-1)

    def dfs(node):
        childList = list()
        for child in edgeDict[node]:
            if visited[child]:
                continue
            visited[child] = True
            childList.append(child)

        if not childList:
            return [node]

        treeDict[node].extend([0, sorted(childList)])
        leafList = list()
        for child in childList:
            leafList.extend(dfs(child))
        return leafList

    def search(node):
        if len(treeDict[node]) == 0:
            return node
        idx = treeDict[node][0]
        treeDict[node][0] = (treeDict[node][0] + 1) % len(treeDict[node][1])
        return search(treeDict[node][1][idx])

    leafList = dfs(0)
    # 방문 횟수를 저장하는 dict
    leafVisitedDict = {key: 0 for key in leafList}

    # 유효성 검사
    isLeafSucceed = {key: False if target[key]
                     > 0 else True for key in leafList}

    orderList = list()
    while False in isLeafSucceed.values():
        currentNode = search(0)
        print("tree", treeDict)
        leafVisitedDict[currentNode] += 1

        if leafVisitedDict[currentNode] > target[currentNode]:
            return [-1]
        if not isLeafSucceed[currentNode] and leafVisitedDict[currentNode] * 3 >= target[currentNode]:
            isLeafSucceed[currentNode] = True

        orderList.append(currentNode)

    answer = [0 for _ in range(len(orderList))]
    for leaf in leafList:
        leafResult = list()
        diff = target[leaf] - leafVisitedDict[leaf]
        leafResult = [1]*(leafVisitedDict[leaf] - diff % 2 -
                          diff // 2) + [2]*(diff % 2) + [3]*(diff // 2)

        for idx, node in enumerate(orderList):
            if node == leaf:
                answer[idx] = leafResult.pop(0)

    return answer


# solution([[1, 2], [1, 3]], [0, 7, 3])

solution([[2, 4], [1, 2], [6, 8], [1, 3], [5, 7], [2, 5], [
         3, 6], [6, 10], [6, 9]], [0, 0, 0, 3, 0, 0, 5, 1, 2, 3])
