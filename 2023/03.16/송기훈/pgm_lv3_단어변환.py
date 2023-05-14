from collections import deque


def solution(begin, target, words):
    answer = 0
    visited = [False for _ in range(len(words))]
    q = deque()
    # 단어, depth
    q.append((begin, 0))
    while q:
        word, depth = q.popleft()
        if word == target:
            answer = depth
            break
        for i in range(len(words)):
            same_cnt = 0
            # 방문하지 않은 단어와 한 글자 차이인가?
            if not visited[i]:
                for j in range(len(word)):
                    if word[j] != words[i][j]:
                        same_cnt += 1
                    if same_cnt > 1:
                        break
                # 한 글자 차이라면 큐에 넣고 돌림
                if same_cnt == 1:
                    q.append((words[i], depth + 1))
                    visited[i] = True
    return answer


print(solution("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"]))
