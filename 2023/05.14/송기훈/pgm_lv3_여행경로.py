from collections import deque


def solution(tickets):
    answer = []

    q = deque()
    # 출발지, 경로, visited
    q.append(("ICN", ["ICN"], []))

    while q:
        start, path, visited = q.popleft()
        # 티켓을 모두 소모했다면 answer에 추가
        if len(visited) == len(tickets):
            answer.append(path)

        for idx, ticket in enumerate(tickets):
            if ticket[0] == start and not idx in visited:
                q.append((ticket[1], path+[ticket[1]], visited+[idx]))

    # 알파벳순으로 정렬
    answer.sort()

    return answer[0]
