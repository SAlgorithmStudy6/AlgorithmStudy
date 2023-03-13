def solution(n, k, cmd):
    # 0: [-1, 2]....n-1: [n-2, n]
    linked_list = {i: [i-1, i+1] for i in range(n)}
    now_select = k
    delete = []
    answer = ["O" for _ in range(n)]
    for command in cmd:
        command = command.split()

        if command[0] == "U":
            # 이전 링크 n번만큼
            for _ in range(int(command[1])):
                now_select = linked_list[now_select][0]
        elif command[0] == "D":
            # 다음 링크 n번만큼
            for _ in range(int(command[1])):
                now_select = linked_list[now_select][1]
        # 삭제
        elif command[0] == "C":
            prev_link, next_link = linked_list[now_select]
            answer[now_select] = "X"
            delete.append([prev_link, now_select, next_link])

            # 선택된 곳이 마지막이라면 이전 링크로
            if next_link == n:
                now_select = linked_list[now_select][0]
            else:
                now_select = linked_list[now_select][1]

            # 선택된 곳이 제일 처음이라면 삭제된 다음 곳을 제일 처음으로 최신화
            if prev_link == -1:
                linked_list[next_link][0] = prev_link
            # 선택된 곳이 제일 마지막이라면 삭제된 이전 곳을 제일 마지막으로 최신화
            elif next_link == n:
                linked_list[prev_link][1] = next_link
            # 일반 삭제
            else:
                linked_list[prev_link][1] = next_link
                linked_list[next_link][0] = prev_link

        # 복구
        else:
            prev_link, now_link, next_link = delete.pop()
            answer[now_link] = "O"

            # 첫번째를 복구하면
            if prev_link == -1:
                linked_list[next_link][0] = now_link
            # 마지막를 복구하면
            elif next_link == n:
                linked_list[prev_link][1] = now_link
            # 일반 복구
            else:
                linked_list[prev_link][1] = now_link
                linked_list[next_link][0] = now_link

    temp = ""
    for c in answer:
        temp += c
    return temp
