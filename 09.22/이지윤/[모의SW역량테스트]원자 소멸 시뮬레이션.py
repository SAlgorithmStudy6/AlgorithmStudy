import sys;sys.stdin = open("input.txt")

MATRIX_N = 1000
MATRIX_CHANGED_N = 2000
MATRIX_CHANGED_SIZE = 4001
move = [(0,1), (0,-1), (-1,0), (1,0)]   # [x,y 좌표] 상, 하, 좌, 우

class atom :
    def __init__(self, _x, _y, _d, _k):
        self.x = 2*(_x + MATRIX_N)
        self.y = 2*(_y + MATRIX_N)
        self.direction = _d
        self.energy = _k
    def setPosition(self, _x, _y):
        self.x = _x
        self.y = _y

# ------------------------------------------------------------------------------------------

def deleteAtomFromArray(_atom_arr, _delete_arr) -> (list, int, int) :
    energy, count = 0, 0
    _delete_arr.sort(reverse=True)
    for delete_info in _delete_arr:
        count += 1
        if delete_info[1] :
            energy += _atom_arr[delete_info[0]].energy
        _atom_arr.pop(delete_info[0])
    return _atom_arr, energy, count


def move_atom(_atom : atom) -> atom:
    dx, dy = move[_atom.direction]
    _atom.setPosition(_atom.x+dx, _atom.y+dy)
    return _atom

def solution(_arr) -> int:
    total_energy, total_count = 0, 0
    while total_count!=n:
        arr_len = len(_arr)
        remove_index_arr = []
        visited = dict()
        for arr_index in range(arr_len):
            present_e = _arr[arr_index]
            next_e = move_atom(present_e)
            if 0<=next_e.x<MATRIX_CHANGED_SIZE and 0<=next_e.y<MATRIX_CHANGED_SIZE:
                search_result = visited.get((next_e.x, next_e.y), [])
                if not search_result:
                    visited[(next_e.x, next_e.y)] = [arr_index]
                    continue
                elif len(search_result)==1:
                    first_index = search_result[0]
                    remove_index_arr.append([first_index, True])
                visited[(next_e.x, next_e.y)] = search_result + [arr_index]
                remove_index_arr.append([arr_index, True])
            else :
                remove_index_arr.append([arr_index, False])
        _arr, energy, count = deleteAtomFromArray(_arr, remove_index_arr)
        total_energy, total_count = total_energy+energy, total_count+count
    return total_energy


T = int(input())
for t in range(1, T + 1):
    n = int(input())
    arr = []
    for _ in range(n):
        row = list(map(int, input().split()))
        element = atom(row[0], row[1], row[2], row[3])
        arr.append(element)
    answer = solution(arr)
    print(f"#{t} {answer}")
