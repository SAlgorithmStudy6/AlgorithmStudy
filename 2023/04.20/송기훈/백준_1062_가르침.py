import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n, k = map(int, input().split())
words = [set(list(input())) for _ in range(n)]
answer = 0


def solution(n, k, words):
    global answer
    # a n t i c
    # 5 미만이면 배울 수 있는게 없다.
    if k < 5:
        print(answer)
        return
    elif k == 26:
        print(n)
        return
    else:
        alphabet = [False] * 26

        for char in ('a', 'n', 't', 'i', 'c'):
            alphabet[ord(char) - ord('a')] = True

        dfs(0, 0, k, words, alphabet)
        print(answer)
        return


def dfs(index, count, k, words, alphabet):
    global answer
    # 탈출 조건
    if count == k - 5:
        tempAnswer = 0
        for word in words:
            flag = True
            # 알파벳을 배웠나 체크
            for c in word:
                if not alphabet[ord(c) - ord('a')]:
                    flag = False
                    break
            # 모두 배웠으면 +1
            if flag == True:
                tempAnswer += 1
        answer = max(answer, tempAnswer)
        return

    for i in range(index, 26):
        # 안 배운 알파벳이면 배우고 원상 복구
        if not alphabet[i]:
            alphabet[i] = True
            dfs(i, count+1, k, words, alphabet)
            alphabet[i] = False


solution(n, k, words)
