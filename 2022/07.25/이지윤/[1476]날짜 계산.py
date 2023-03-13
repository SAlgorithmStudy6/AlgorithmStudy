
def check_e(earth, year_val) -> True:
    if year_val % 15 == 0 and earth == 15:
        return True
    elif year_val % 15 == earth:
        return True
    else:
        return False


def check_s(sun, year_val) -> True:
    if year_val % 28 == 0 and sun == 28:
        return True
    elif year_val % 28 == sun:
        return True
    else:
        return False


def check_m(moon, year_val) -> True:
    if year_val % 19 == 0 and moon == 19:
        return True
    elif year_val % 19 == moon:
        return True
    else:
        return False


e, s, m = map(int, input().split())

year = 1
while True:
    if check_e(e, year) and check_s(s, year) and check_m(m, year):
        print(year)
        break
    else:
        year += 1
