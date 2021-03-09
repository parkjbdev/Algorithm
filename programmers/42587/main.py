def test42587(function):
    priorities = [[2, 1, 3, 2], [1, 1, 9, 1, 1, 1]]
    location = [2, 0]
    for i in range(0, 2):
        print(f"TestCase {i} input: {priorities[i]} {location[i]}")
        print("output:", function(priorities[i], location[i]))


def solution42587(priorities, location):
    pList = [0 for _ in range(9)]

    for i in range(0, len(priorities)):
        priorities[i] -= 1
        pList[priorities[i]] += 1

    priority = len(pList) - 1
    pCnt = 0
    print(pList)

    cnt = 0

    while True:
        # Init Priority vars when all completed
        if pCnt == pList[priority]:
            priority -= 1
            while pList[priority] == 0:
                priority -= 1
            pCnt = 0

        if priority == priorities[0]:
            pCnt += 1
            cnt += 1
            if location == 0:
                break

        # 꺼내서 뒤에 붙히기
        location = location - 1 if location > 0 else location + len(priorities) - 1
        tmp = priorities.pop(0)
        priorities.append(tmp)

    return cnt


test42587(solution42587)
