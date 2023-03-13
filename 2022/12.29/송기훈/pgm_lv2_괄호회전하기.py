from collections import deque


def solution(s):
    answer = 0
    wordList = list(s)
    length = len(wordList)
    for i in range(length):
        q = deque()
        for j in range(length):
            # 스택에 비어있으면 추가
            if not q:
                q.append(wordList[j])
            # 짝이 맞으면 스택에서 삭제
            elif (
                (wordList[j] == ')' and q[-1] == '(')
                or (wordList[j] == ']' and q[-1] == '[')
                or (wordList[j] == '}' and q[-1] == '{')
            ):
                q.pop()
            else:
                q.append(wordList[j])
        if len(q) == 0:
            answer += 1

        # 첫 문자를 뒤로 보냄
        first = wordList.pop(0)
        wordList.append(first)

    return answer
