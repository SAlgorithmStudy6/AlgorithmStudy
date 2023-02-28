def dfs(nqueen, y, n):
    count = 0
    if n == y:
        return 1
    for x in range(n):
        # (y, x)에 말을 둔다
        nqueen[y] = x
        # 0 ~ y까지 체크
        for i in range(y):
            # 세로에 있는가?
            if nqueen[i] == nqueen[y]:
                break
            # 대각선에 있는가?
            if abs(nqueen[i] - nqueen[y]) == y - i:
                break
        # break로 탈출하지 않으면 dfs
        else:
            count += dfs(nqueen, y+1, n)
    return count


def solution(n):
    return dfs([0 for _ in range(n)], 0, n)
