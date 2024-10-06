N = int(input())

def hanoi(N, start, end):
    if N == 1:
        print(start, end)
        return

    mid = 6 - start - end
    hanoi(N - 1, start, mid)
    print(start, end)
    hanoi(N - 1, mid, end)

print(2 ** N - 1)
hanoi(N, 1, 3)

