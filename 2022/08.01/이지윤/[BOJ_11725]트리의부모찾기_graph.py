# 모든 경우를 다 탐색할 필요 있는가? X.
# 만약 목표 지점에 도달하지 못한다면 다 탐색해보긴 해야 하지만, 그냥 이미 목표 지점에 도달했다면 더 이상 돌 필요 X.
# 따라서 BFS + 재귀 구현시 , exit으로 해당 상황 가지치기 해주기.
# => 최대, 최소가 아닌 '부모 노드'만 찾기이므로, global 변수가 필요 없다.

# 위 과정의 여러 번일 뿐이다.
#------------------------------------------------------------------------------------------------
import sys
sys.stdin = open("input.txt", "r")

def dfs(_n, _graph) -> list:
    parentNode = [0]*(_n+1)     # 노드 번호 = 인덱스+1
    parentNode[1],stack = 1, [1]
    while stack:
        node = stack.pop()
        for _v in _graph[node]:
            if parentNode[_v] : continue
            parentNode[_v] = node
            stack.append(_v)
    return parentNode

def main():
    n = int(input())
    graph = [[] for _ in range(n+1)]
    for _ in range(n - 1):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)
    answer = dfs(n, graph)
    print(*answer[2:], sep="\n")



# -- 실행 함수 ------------------------------------------------------------------------------------
if __name__ == '__main__':
    main()