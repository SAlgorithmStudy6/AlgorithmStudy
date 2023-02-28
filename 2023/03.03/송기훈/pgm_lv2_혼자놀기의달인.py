def solution(cards):
    answer = 0
    for card in cards:
        visited = [False for _ in range(len(cards))]
        count_1 = 0
        idx = card
        while True:
            # False면 True로, count += 1, 도착한 곳을 idx로
            if visited[idx-1] == False:
                visited[idx - 1] = True
                count_1 += 1
                idx = cards[idx - 1]
            else:
                break
        # 안 뽑힌 카드들
        notSelectedCards = [cards[i]
                            for i in range(len(cards)) if not visited[i]]
        if len(notSelectedCards) == 0:
            break
        count_2 = 0
        # 안 뽑힌 카드들 중 최고점을 같은 로직으로 찾아보자
        for card in notSelectedCards:
            tempCount = 0
            idx = card
            while True:
                if visited[idx-1] == False:
                    visited[idx - 1] = True
                    tempCount += 1
                    idx = cards[idx - 1]
                else:
                    count_2 = max(count_2, tempCount)
                    break
        answer = max(answer, count_1 * count_2)
    return answer


solution([8, 6, 3, 7, 2, 5, 1, 4])
