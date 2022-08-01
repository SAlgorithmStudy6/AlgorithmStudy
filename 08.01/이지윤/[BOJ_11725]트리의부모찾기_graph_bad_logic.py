# 모든 경우를 다 탐색할 필요 있는가? X.
# 만약 목표 지점에 도달하지 못한다면 다 탐색해보긴 해야 하지만, 그냥 이미 목표 지점에 도달했다면 더 이상 돌 필요 X.
# 따라서 BFS + 재귀 구현시 , exit으로 해당 상황 가지치기 해주기.
# => 최대, 최소가 아닌 '부모 노드'만 찾기이므로, global 변수가 필요 없다.

# 위 과정의 여러 번일 뿐이다.
#------------------------------------------------------------------------------------------------
import sys
sys.stdin = open("input.txt", "r")

answer = []

def make_linked_list(_arr) -> dict:
    tree = {}                                          # or tree = {i: [] for i in range(1, n+1)}  => 인접 딕셔너리
    for row in _arr:
        m1, m2 = row[0], row[1]
        tree[m1] = tree.get(m1, []) + [m2]
        tree[m2] = tree.get(m2, []) + [m1]
    return tree

def dfs(_stack, _graph) :
    if not _stack : return
    node = _stack.pop()
    for _v in _graph.get(node):
        if _v :
            print("dd" , _v , node)
            answer.append(_v)
            return
        _stack.append(_v)
    dfs(_stack, _graph)

def main():
    n = int(input())
    n_list = [list(map(int, input().split())) for _ in range(n-1)]
    graph = make_linked_list(n_list)
    for i in range(2, n+1):
        stack = [i]
        dfs(stack, graph)
    print(*answer, sep="\n")



# -- 실행 함수 ------------------------------------------------------------------------------------
if __name__ == '__main__':
    main()