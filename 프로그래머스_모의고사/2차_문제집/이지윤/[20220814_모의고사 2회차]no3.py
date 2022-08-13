
def dfs(_count, _node, _finish_node, _arr, _n) -> int:
    min_answer, stack = 100000, []
    if _arr[_node][0] <= 0 : return -1
    stack.append(_node)
    count = 0
    while stack:
        present_node = stack.pop()
        if present_node == _finish_node :
            min_answer = min(min_answer, count)
            count = 0
        for next_index in range(1, len(_arr[_node])):
            if _arr[_node][next_index] > 0 :
                stack.append(next_index)
                _arr[_node][0] -= 1
                _arr[_node][next_index] = 0
                count +=1
    return -1 if min_answer==100000 else min_answer


def make_linked_matrix(_n, _arr) -> list:
    arr = [[0] for _ in range(_n+1)]
    for elements in _arr:
        e1, e2 = elements[0], elements[1]
        arr[e1][0], arr[e2][0] = arr[e1][0]+1, arr[e2][0]+1
        arr[e1].append(e2)
        arr[e2].append(e1)
    return arr

def solution(n, roads, sources, destination):
    answer = []
    arr = make_linked_matrix(n, roads)
    for vertex in sources:
        answer.append(dfs(0, vertex, destination, arr, n))
    return answer