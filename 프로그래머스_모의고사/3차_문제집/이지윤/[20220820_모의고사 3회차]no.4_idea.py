def makeAdjacencyList(_arr) -> dict:
    graph = dict()
    for element in _arr:
        e1, e2 = element[0], element[1]
        graph[e1] = graph.get(e1, []) + [e2]
        graph[e2] = graph.get(e2, []) + [e1]
    return graph

def solution(n, lighthouse):
    answer_list, max_lightInfo = [False]*(n+1), [0,0]
    graph = makeAdjacencyList(lighthouse)
    graph_keys = graph.keys()
    for key in graph_keys:
        neighbors = graph.get(key, [])
        len_neighbors = len(neighbors)
        if len_neighbors == 1 and not answer_list[neighbors[0]]:
            answer_list[neighbors[0]] = True
    return answer_list.count(True)
