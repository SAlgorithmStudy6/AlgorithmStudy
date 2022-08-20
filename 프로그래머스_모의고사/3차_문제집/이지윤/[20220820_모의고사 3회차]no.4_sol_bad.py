
def makeAdjacencyList(_arr) -> dict:
    graph = dict()
    for element in _arr:
        e1, e2 = element[0], element[1]
        graph[e1] = graph.get(e1, []) + [e2]
        graph[e2] = graph.get(e2, []) + [e1]
    return graph

def dfs(_node, _graph:dict, _visited, _need_lights) -> int:
    global answer
    neighbors = _graph.get(_node)
    if len(neighbors) == 1:
        lights[neighbors[0]] = True
        _need_lights[neighbors[0]] = False
        # _need_lights[_node] = False
        if True not in _need_lights :
            answer = min(answer, lights.count(True))
        for neighbors_of_light in _graph.get(neighbors[0]):
            _need_lights[neighbors_of_light] = False
        return 1
    for next_node in neighbors:
        # if lights[next_node] :
        #     _need_lights[_node] = False
        if not _visited[next_node]:
            _visited[next_node] = True
            dfs(next_node, _graph, _visited, _need_lights)
            if _need_lights[_node]:
                lights[_node] = True
                dfs(next_node, _graph, _visited, _need_lights)
                lights[_node] = False
    return 0

def solution(n, lighthouse):
    graph = makeAdjacencyList(lighthouse)
    visited = [False]*(n+1)
    need_lights = [True]*(n+1)

    visited[1] = True
    dfs(1, graph, visited, need_lights)
    return

lights = [False]*(100000+1)
answer = 100000


