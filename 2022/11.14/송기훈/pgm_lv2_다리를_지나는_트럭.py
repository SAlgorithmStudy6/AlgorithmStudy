from collections import deque

def solution(bridge_length, weight, truck_weights):
    t = 0
    bridge = [0 for _ in range(bridge_length)]
    bridge_q_weight = 0

    bridge_q = deque(bridge)
    truck_q = deque(truck_weights)
    while bridge_q:
        t += 1
        bridge_q_weight -= bridge_q[0]
        bridge_q.popleft()

        if truck_q:
            if bridge_q_weight + truck_q[0] <= weight:
                bridge_q_weight += truck_q[0]
                bridge_q.append(truck_q.popleft())
            else:
                bridge_q.append(0)
    return t

# 시간 초과. 조건문의 sum을 빼냈다.
# def solution(bridge_length, weight, truck_weights):
#     t = 0
#     bridge = [0 for _ in range(bridge_length)]
#     bridge_q = deque(bridge)
#     truck_q = deque(truck_weights)
#     while bridge_q:
#         t += 1
#         bridge_q.popleft()

#         if truck_q:
#             if sum(bridge_q) + truck_q[0] <= weight:
#                 bridge_q.append(truck_q.popleft())
#             else:
#                 bridge_q.append(0)

#     return t

print(solution(2, 10, [7, 4, 5, 6]))
