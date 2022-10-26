import sys; sys.stdin = open("input.txt")
from collections import deque

ROAD = 0
APPLE = 1
SNAKE = 2
MOVE = [(0,1),(-1,0),(0,-1),(1,0)] # 하, 좌, 상, 우

def checkBoundary(_x, _y) -> bool:
    if 0<=_x<n and 0<=_y<n : return False
    else : return True

def checkSnakeCrash(_x, _y, _arr) -> bool:
    if _arr[_y][_x] == SNAKE : return True
    else : return False

def solution(time) -> int:
    snake = deque([(0,0)])
    direction_index = -1
    board_arr[0][0] = SNAKE
    while True:
        if direction_queue and direction_queue[0][0]==str(time) :
            direction = direction_queue.popleft()
            direction_index = (direction_index-1)%4 if direction[1]=='L' else (direction_index+1)%4
        head_x, head_y = snake[-1][0]+MOVE[direction_index][0], snake[-1][1]+MOVE[direction_index][1]
        snake.append((head_x, head_y))
        if checkBoundary(head_x, head_y) or checkSnakeCrash(head_x, head_y, board_arr) :
            return time+1
        if not (board_arr[head_y][head_x] == APPLE):
            tail = snake.popleft()
            board_arr[tail[1]][tail[0]] = ROAD
        board_arr[head_y][head_x] = SNAKE
        time += 1

n = int(input())
board_arr = [[0]*n for _ in range(n)]
k = int(input())
apple_arr = []
for _ in range(k):
    apple_y, apple_x = map(int, input().split())
    apple_y, apple_x = apple_y-1, apple_x-1
    board_arr[apple_y][apple_x] = APPLE
    apple_arr.append((apple_x, apple_y))
l = int(input())
direction_queue = deque([input().split() for _ in range(l)])

answer = solution(0)
print(answer)
